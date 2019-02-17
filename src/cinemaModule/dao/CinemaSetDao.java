package cinemaModule.dao;

import java.util.List;
import java.util.Map;

import cinemaModule.entity.ProjectRoom;
import cinemaModule.entity.ProjectRoomType;
import cinemaModule.entity.TimeInterval;

public interface CinemaSetDao {

	//���÷�ӳ��
	void addProjectRoomTypes(String alias, String instroduce);

	void modifyProjRoomTypes(Integer typeNumb,String alias, String instroduce);

	void deleteProjRoomTypes(Integer typeNumb);

	//��ӳ���������
	void addType(Integer typeNumb,Integer roomAmount,Integer seatAmount);

	void addSetIn1(Integer typeNumb, List<Integer> set1);

	void addSetIn2(Integer typeNumb, List<Integer> set2);

	void modifyType(Integer typeNumb, Integer roomAmount);
	
	List<Integer> getTypeSeatSet1(Integer roomNumb);

	List<Integer> getTypeSeatSet2(Integer roomNumb);

	void deleteSetIn1(Integer typeNumb);

	void deleteSetIn2(Integer typeNumb);

	//���ɷ���
	Integer addRoom(Integer typeNumb);
	
	void creatRoomSeatset1(Map roomNumb);

	void creatRoomSeatset2(Map roomNumb);
	
	void modifyRoom(Integer roomNumb, String roomName, String instruction);
	
	List<ProjectRoomType> getProjectRoomTypes(Integer typeNumb);
	
	List<Integer> getRoomInType(Integer typeNumb);
	//ɾ������
	void deleteRooms(Integer typeNumb);

	void deleteRoom(Integer roomNumb);
	
	void deleteRoomSeatset1(Map<String, Integer> roomNumbMap);
	
	void deleteRoomSeatset2(Map<String, Integer> roomNumbMap);
	
	ProjectRoom getProjectRoom(Integer roomNumb);
	
	List<ProjectRoom> getProjectRooms(Integer typeNumb);

	//���ӷ�ӳʱ��
	Integer addSchedule(Map intervalMap);

	void initSeatSet1(Integer scheduleNumb, List<Integer> typeSeatSet1, Map<String, Integer> roomNumbMap);
	
	void initSeatSet2(Integer scheduleNumb,List<Integer> typeSeatSet2, Map<String, Integer> roomNumbMap);

	void updateSchedule(Map intervalMap);

	void deleteSchedule(Integer scheduleNumb,Integer roomNumb);

	List<TimeInterval> getSchedule(Map<String, Integer> wayMap);

}
