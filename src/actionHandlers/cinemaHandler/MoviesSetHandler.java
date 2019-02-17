package actionHandlers.cinemaHandler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cinemaModule.entity.ProjectRoom;
import cinemaModule.entity.ProjectRoomType;
import cinemaModule.entity.TimeInterval;
import cinemaModule.service.CinemaMovieService;
import customModule.entity.Customer;

/**
 * ��ӳ������ҵ���������£�����Ա���ӷ�ӳ������<{@code}setProjectRoomTypes()>������ѡ���ӳ�䲼��
 * <{@code}setType()>��������Ӧ�����ķ��䣬֮������й���Ա��ÿ�������������߼���˵��<{@code}setRoom()>
 * ����֮�⣬�����޸ĺ�ɾ��ָ���ķ�ӳ������ͷ��䣬����ɾ����ӳ������ʱ��ɾ��������ķ��䣬��������ӳ��¼����
 * ɾ�������Ӧ�÷�ӳ�������Ӧ�ķ���������һ
 * @author www25
 *
 */
@Controller
@RequestMapping("cinema/")
public class MoviesSetHandler {
	
	private CinemaMovieService cinemaServ;

	/**
	 * ���÷�ӳ������ࣺ���ֺ�˵��
	 */
	@RequestMapping("setProjRoomTypes")
	public void setProjectRoomTypes(String alias,String  instroduce,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaServ.setProjectRoomTypes(alias,instroduce);
		modelAndView.addObject("setProjRoomTypesMsg", "�������");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * �޸ķ�ӳ�������
	 */
	@RequestMapping("modifyProjRoomTypes")
	public void modifyProjRoomTypes(Integer typeNumb,String alias,String  instroduce,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaServ.changeProjRoomTypes(typeNumb,alias,instroduce);
		modelAndView.addObject("setProjRoomTypesMsg", "�������");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ɾ����ӳ�������
	 */
	@RequestMapping("deleteProjRoomTypes")
	public void deleteProjRoomTypes(Integer typeNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaServ.deleteProjRoomTypes(typeNumb);//���⻹��ɾ����Ӧ�ķ��䵫������ӳ��¼
		modelAndView.addObject("setProjRoomTypesMsg", "�������");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ��ѯ��ӳ�������
	 */
	@RequestMapping("getProjRoomTypes")
	public void getProjectRoomTypes(Integer typeNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		List<ProjectRoomType> roomTypesList=cinemaServ.getProjRoomTypes(typeNumb);
		modelAndView.addObject("roomTypesList", roomTypesList);
		modelAndView.setViewName("redirect:"+postURI);
	}

	/**
	 * ��ӰԺ����Ա���÷�ӳ�������ͣ���ӳ�����λ�����Լ��������͵�����
	 */
	@RequestMapping("setType")
	public void setType(Integer typeNumb,Integer roomAmount,Integer seatAmount,Integer[] setArray,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		//���÷�ӳ��������
		cinemaServ.setType(typeNumb,roomAmount,seatAmount,setArray);
		modelAndView.addObject("setTypeMsg", "�������");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ��ӰԺ����Ա�޸ķ�ӳ��������:�ɸĽ�
	 */
	@RequestMapping("changeType")
	public void changeType(Integer typeNumb,Integer roomAmount,Integer[] setList,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		//���÷�ӳ��������
		cinemaServ.changeType(typeNumb,roomAmount,setList);
		modelAndView.addObject("setTypeMsg", "�������");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ��ӰԺ����Ա��ӳ�������Ͳ�������
	 */
	@RequestMapping("setRoom")
	public void setRoom(Integer roomNumb,String roomName,String instruction,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		//���÷����������ֺ�˵��
		cinemaServ.setRoom(roomNumb,roomName,instruction);
		modelAndView.addObject("setRoomMsg", "�������");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ��ӰԺ����Ա��ӳ�������Ͳ�������
	 */
	@RequestMapping("delRoom")
	public void delRoom(Integer roomNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaServ.delRoom(roomNumb);//ɾ����Ӧ�÷�ӳ�������Ӧ�ķ���������һ
		modelAndView.addObject("setRoomMsg", "�������");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ��ѯ��ӳ��
	 */
	@RequestMapping("getProjRoom")
	public void getRoom(Integer roomNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		ProjectRoom room=cinemaServ.getProjectRoom(roomNumb);
		modelAndView.addObject("ProjRoom", room);
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ��ѯĳ����ķ�ӳ��
	 */
	@RequestMapping("getProjRooms")
	public void getProjRooms(Integer typeNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		List<ProjectRoom> roomList=cinemaServ.getProjRooms(typeNumb);
		modelAndView.addObject("ProjRooms", roomList);
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ��ӰԺ����Ա�ύ��Ӱ��ӳ�������ã���ӳ������ʱ��
	 */
	@RequestMapping("setSchedule")
	public void setSchedule(Integer movieId,Integer roomNumb,TimeInterval projectInterval,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaServ.setSchedule(movieId,roomNumb,projectInterval);
		modelAndView.addObject("setRoomMsg", "�������");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ��ӰԺ����Ա�޸ĵ�Ӱ��ӳ��������
	 */
	@RequestMapping("changeSchedule")
	public void changeSchedule(Integer movieId,Integer roomNumb,TimeInterval projectInterval,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaServ.changeSchedule(movieId,roomNumb,projectInterval);
		modelAndView.addObject("changeSchedule", "�޸����");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ��ӰԺ����Աɾ����Ӱ��ӳ��������
	 */
	@RequestMapping("delSchedule")
	public void delSchedule(Integer movieId,Integer roomNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaServ.delSchedule(movieId,roomNumb);
		modelAndView.addObject("setRoomMsg", "ɾ�����");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	@RequestMapping("getSchedule")
	public ModelAndView getSchedule(String loginWay,String loginWayValue,HttpServletRequest request){
		HttpSession session=request.getSession();
		//���������uri��http:\\**\**.jsp,���������url���Ƿ��������URI����Ҫ�ڷ�������ʱ�Ž�request������
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		
		List<TimeInterval> projectIntervalList=cinemaServ.getSchedule(loginWay,loginWayValue);
		if(projectIntervalList==null){
			mAndView.addObject("getScheduleMsg", "δ�и�ʱ��ΰ��ţ�");
			mAndView.setViewName("redirect:login.jsp");
			return mAndView;
		}else{
			mAndView.addObject("scheduleList", projectIntervalList);
			mAndView.setViewName("redirect:"+postURI);
			return mAndView;
		}

	}
}
