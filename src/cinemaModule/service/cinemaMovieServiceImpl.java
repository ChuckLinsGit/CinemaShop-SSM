package cinemaModule.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cinemaModule.dao.CinemaSetDao;
import cinemaModule.entity.ProjectRoom;
import cinemaModule.entity.ProjectRoomType;
import cinemaModule.entity.TimeInterval;

/*ע��������Щ֮���û��鿴��ӳ����λ��ʱ�����Ǵ�����������õ�λ�ñ���ȡ������λ��λ�ã�֮��ӷ�����λ��ȡ����Щ
��λ�ѱ�Ԥ��*/

public class cinemaMovieServiceImpl implements CinemaMovieService {

	private CinemaSetDao cinemaMovieDao;

	//���÷�ӳ������
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

	//��ӳ���������
	@Override
	public void setType(Integer typeNumb,Integer roomAmount, Integer seatAmount,Integer[] setArray) {
		cinemaMovieDao.addType(typeNumb,roomAmount,seatAmount);
		//����λ���÷�Ϊ���ű�
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
		//������Ӧ�ķ���
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
		//�ı���λ��ṹ
		cinemaMovieDao.deleteSetIn1(typeNumb);
		cinemaMovieDao.addSetIn1(typeNumb,set1);
		cinemaMovieDao.deleteSetIn2(typeNumb);
		cinemaMovieDao.addSetIn2(typeNumb,set2);
		//�������ɷ���
		recreateRooms(typeNumb,roomAmount);
	}



	//���õ�������
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

	//���ӷ�ӳʱ��
	@Override
	public void setSchedule(Integer movieId, Integer roomNumb, TimeInterval projectInterval) {
		//�ڷ�ӳʱ����в����¼
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
		//ͬʱ�Է����ʱ������λҲ���г�ʼ��������ԭʼ����:XY->X��ʾ�Ƿ�������λ��Y��ʾ�Ƿ�Ԥ��
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
		//ɾ��ʱ����¼ҲҪɾ����λ���¼
		cinemaMovieDao.deleteSchedule(scheduleNumb,roomNumb);
	}


	@Override
	public List<TimeInterval> getSchedule(String way, Integer value) {
		Map<String, Integer> wayMap=new HashMap<String, Integer>();
		wayMap.put(way, value);
		//��ȡʱ����¼��ҲҪ��ȡ��λ���¼
		List<TimeInterval> scheduleList=cinemaMovieDao.getSchedule(wayMap);
		return scheduleList;
	}

	/*������Ӧ�����Ķ�Ӧ����*/
	private void createRooms(Integer typeNumb, Integer roomAmount) {
		for(int i=0;i<roomAmount;i++) {
			createRoom(typeNumb);
		}
	}

	private void createRoom(Integer typeNumb) {
		//�ٷ���ͳ�Ʊ������ӷ�����Ϣ
		Integer roomNumb = cinemaMovieDao.addRoom(typeNumb);
		//���ɸ÷�����Ӧ����λ�������Ϳ������ӷ�ӳ��Ӱʱ��ʱֱ�ӶԷ�����λ����в���
		//ʵ�ʶ����ݿ�Ĳ����ǽ���ӳ������������õ���λ���Ƹ��������
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
