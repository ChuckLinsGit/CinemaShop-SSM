package cinemaModule.service;

import java.util.List;

import cinemaModule.entity.ProjectRoom;
import cinemaModule.entity.ProjectRoomType;
import cinemaModule.entity.TimeInterval;

public interface CinemaMovieService {

	void setProjectRoomTypes(String alias, String instroduce);

	void changeProjRoomTypes(Integer typeNumb,String alias, String instroduce);

	void deleteProjRoomTypes(Integer typeNumb);
	
	List<ProjectRoomType> getProjRoomTypes(Integer typeNumb);

	void setType(Integer typeNumb, Integer roomAmount,Integer seatAmount, Integer[] setArray);

	void changeType(Integer typeNumb, Integer roomAmount, Integer[] setArray);

	void setRoom(Integer roomNumb, String roomName, String instruction);

	void delRoom(Integer roomNumb);

	ProjectRoom getProjectRoom(Integer roomNumb);

	List<ProjectRoom> getProjRooms(Integer typeNumb);

	void setSchedule(Integer movieId, Integer roomNumb, TimeInterval projectInterval);
	
	void changeSchedule(Integer movieId, Integer roomNumb, TimeInterval projectInterval);
	
	void delSchedule(Integer scheduleNumb, Integer roomNumb);

	List<TimeInterval> getSchedule(String Way, Integer value);

}
