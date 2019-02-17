package cinemaModule.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cinemaModule.dao.CinemaSetDao;
import cinemaModule.entity.ProjectRoom;
import cinemaModule.entity.ProjectRoomType;
import cinemaModule.entity.TimeInterval;

/*注：定义这些之后，用户查看放映房间位置时，先是从种类具体设置的位置表中取出有座位的位置，之后从房间座位表取出那些
座位已被预订*/

public class cinemaMovieServiceImpl implements CinemaMovieService {

	private CinemaSetDao cinemaMovieDao;

	//设置放映间种类
	@Override
	public void setProjectRoomTypes(String alias, String instroduce) {
		cinemaMovieDao.addProjectRoomTypes(alias,instroduce);
	}

	@Override
	public void changeProjRoomTypes(Integer typeNumb,String alias, String instroduce) {
		cinemaMovieDao.modifyProjRoomTypes(typeNumb,alias,instroduce);
	}

	@Override
	public void deleteProjRoomTypes(Integer typeNumb) {
		cinemaMovieDao.deleteProjRoomTypes(typeNumb);
	}
	
	@Override
	public List<ProjectRoomType> getProjRoomTypes(Integer typeNumb) {
		return cinemaMovieDao.getProjectRoomTypes(typeNumb);
	}

	//放映间具体设置
	@Override
	public void setType(Integer typeNumb,Integer roomAmount, Integer seatAmount,Integer[] setArray) {
		cinemaMovieDao.addType(typeNumb,roomAmount,seatAmount);
		//将座位设置分为两张表
		List<Integer> set1=new ArrayList<Integer>();
		List<Integer> set2=new ArrayList<Integer>();
		for(int i=0;i<setArray.length;i++) {
			if(i>=4||i<=11) {
				set1.add(setArray[i]);
				continue;
			}
			set2.add(setArray[i]);
		}
		cinemaMovieDao.addSetIn1(typeNumb,set1);
		cinemaMovieDao.addSetIn2(typeNumb,set2);
		//生成相应的房间
		createRooms(typeNumb,roomAmount);
	}

	@Override
	public void changeType(Integer typeNumb, Integer roomAmount, Integer[] setArray) {
		cinemaMovieDao.modifyType(typeNumb,roomAmount);
		List<Integer> set1=new ArrayList<Integer>();
		List<Integer> set2=new ArrayList<Integer>();
		for(int i=0;i<setArray.length;i++) {
			if(i>=4||i<=11) {
				set1.add(setArray[i]);
				continue;
			}
			set2.add(setArray[i]);
		}
		//改变座位表结构
		cinemaMovieDao.deleteSetIn1(typeNumb);
		cinemaMovieDao.addSetIn1(typeNumb,set1);
		cinemaMovieDao.deleteSetIn2(typeNumb);
		cinemaMovieDao.addSetIn2(typeNumb,set2);
		//重新生成房间
		recreateRooms(typeNumb,roomAmount);
	}



	//设置单个房间
	public void addRoom(Integer typeNumb){
		createRoom(typeNumb);
	}
	
	@Override
	public void setRoom(Integer roomNumb, String roomName, String instruction) {
		cinemaMovieDao.modifyRoom(roomNumb,roomName,instruction);
	}

	@Override
	public void delRoom(Integer roomNumb) {
		cinemaMovieDao.deleteRoom(roomNumb);
		Map<String,Integer> roomNumbMap=new HashMap<String,Integer>();
		roomNumbMap.put("roomNumb", roomNumb);
		cinemaMovieDao.deleteRoomSeatset1(roomNumbMap);
		cinemaMovieDao.deleteRoomSeatset2(roomNumbMap);
	}
	
	@Override
	public ProjectRoom getProjectRoom(Integer roomNumb) {
		return cinemaMovieDao.getProjectRoom(roomNumb);
	}
	
	@Override
	public List<ProjectRoom> getProjRooms(Integer typeNumb) {
		return cinemaMovieDao.getProjectRooms(typeNumb);
	}

	//增加放映时间
	@Override
	public void setSchedule(Integer movieId, Integer roomNumb, TimeInterval projectInterval) {
		//在放映时间表中插入记录
		Map<String,Integer> intervalMap=new HashMap<String,Integer>();
		intervalMap.put("startDate", projectInterval.getStartDate());
		intervalMap.put("startHour", projectInterval.getStartHour());
		intervalMap.put("startMin", projectInterval.getStartMin());
		intervalMap.put("endDate", projectInterval.getEndDate());
		intervalMap.put("endHour", projectInterval.getEndHour());
		intervalMap.put("endMin", projectInterval.getEndMin());
		intervalMap.put("movieId", movieId);
		intervalMap.put("roomNumb", roomNumb);
		Integer scheduleNumb=cinemaMovieDao.addSchedule(intervalMap);
		//同时对房间该时间表的座位也进行初始化，插入原始数据:XY->X表示是否设置座位，Y表示是否被预定
		List<Integer> typeSeatSet1 = cinemaMovieDao.getTypeSeatSet1(roomNumb);
		for (Integer seat : typeSeatSet1) {
			if(seat==1)seat=10;
		}
		List<Integer> typeSeatSet2 = cinemaMovieDao.getTypeSeatSet2(roomNumb);
		for (Integer seat : typeSeatSet2) {
			if(seat==1)seat=10;
		}
		Map<String,Integer> roomNumbMap=new HashMap<String,Integer>();
		roomNumbMap.put("roomNumb", roomNumb);
		cinemaMovieDao.initSeatSet1(scheduleNumb,typeSeatSet1,roomNumbMap);
		cinemaMovieDao.initSeatSet2(scheduleNumb,typeSeatSet2,roomNumbMap);
	}
	
	@Override
	public void changeSchedule(Integer movieId, Integer roomNumb, TimeInterval projectInterval) {
		Map<String,Integer> intervalMap=new HashMap<String,Integer>();
		intervalMap.put("startDate", projectInterval.getStartDate());
		intervalMap.put("startHour", projectInterval.getStartHour());
		intervalMap.put("startMin", projectInterval.getStartMin());
		intervalMap.put("endDate", projectInterval.getEndDate());
		intervalMap.put("endHour", projectInterval.getEndHour());
		intervalMap.put("endMin", projectInterval.getEndMin());
		intervalMap.put("movieId", movieId);
		intervalMap.put("roomNumb", roomNumb);
		cinemaMovieDao.updateSchedule(intervalMap);
	}

	@Override
	public void delSchedule(Integer scheduleNumb, Integer roomNumb) {
		//删除时间表记录也要删除座位表记录
		cinemaMovieDao.deleteSchedule(scheduleNumb,roomNumb);
	}


	@Override
	public List<TimeInterval> getSchedule(String way, Integer value) {
		Map<String, Integer> wayMap=new HashMap<String, Integer>();
		wayMap.put(way, value);
		//获取时间表记录，也要获取座位表记录
		List<TimeInterval> scheduleList=cinemaMovieDao.getSchedule(wayMap);
		return scheduleList;
	}

	/*生成相应数量的对应房间*/
	private void createRooms(Integer typeNumb, Integer roomAmount) {
		for(int i=0;i<roomAmount;i++) {
			createRoom(typeNumb);
		}
	}

	private void createRoom(Integer typeNumb) {
		//再房间统计表中增加房间信息
		Integer roomNumb = cinemaMovieDao.addRoom(typeNumb);
		//生成该房间相应的座位表，这样就可在增加放映电影时间时直接对房间座位表进行操作
		//实际对数据库的操作是将放映间种类具体设置的座位表复制给房间表即可
		Map<String,Integer> roomNumbMap=new HashMap<String,Integer>();
		roomNumbMap.put("roomNumb", roomNumb);
		cinemaMovieDao.creatRoomSeatset1(roomNumbMap);
		cinemaMovieDao.creatRoomSeatset2(roomNumbMap);
	}


	private void recreateRooms(Integer typeNumb, Integer roomAmount) {
		deleteRooms(typeNumb);
		createRooms(typeNumb, roomAmount);
	}

	
	private void deleteRooms(Integer typeNumb) {
		List<Integer> roomNumbList=cinemaMovieDao.getRoomInType(typeNumb);
		cinemaMovieDao.deleteRooms(typeNumb);
		Map<String,Integer> roomNumbMap=new HashMap<String,Integer>();
		for (Integer roomNumb : roomNumbList) {
			roomNumbMap.put("roomNumb", roomNumb);
			cinemaMovieDao.deleteRoomSeatset1(roomNumbMap);
			cinemaMovieDao.deleteRoomSeatset2(roomNumbMap);
		}
	}
}
