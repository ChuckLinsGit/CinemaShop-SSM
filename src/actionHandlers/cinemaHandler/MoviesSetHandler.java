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
 * 放映间设置业务流程如下：管理员增加放映间种类<{@code}setProjectRoomTypes()>，接着选择放映间布局
 * <{@code}setType()>并生产相应数量的房间，之后可以有管理员给每个房间命名或者加上说明<{@code}setRoom()>
 * 除此之外，还能修改和删除指定的放映间种类和房间，其中删除放映间种类时会删除其包含的房间，但保留放映记录；而
 * 删除房间后应让放映间种类对应的房间数量减一
 * @author www25
 *
 */
@Controller
@RequestMapping("cinema/")
public class MoviesSetHandler {
	
	private CinemaMovieService cinemaServ;

	/**
	 * 设置放映间的种类：名字和说明
	 */
	@RequestMapping("setProjRoomTypes")
	public void setProjectRoomTypes(String alias,String  instroduce,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaServ.setProjectRoomTypes(alias,instroduce);
		modelAndView.addObject("setProjRoomTypesMsg", "设置完成");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 修改放映间的种类
	 */
	@RequestMapping("modifyProjRoomTypes")
	public void modifyProjRoomTypes(Integer typeNumb,String alias,String  instroduce,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaServ.changeProjRoomTypes(typeNumb,alias,instroduce);
		modelAndView.addObject("setProjRoomTypesMsg", "设置完成");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 删除放映间的种类
	 */
	@RequestMapping("deleteProjRoomTypes")
	public void deleteProjRoomTypes(Integer typeNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaServ.deleteProjRoomTypes(typeNumb);//另外还需删除相应的房间但保留放映记录
		modelAndView.addObject("setProjRoomTypesMsg", "设置完成");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 查询放映间的种类
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
	 * 电影院管理员设置放映房间类型：放映间的座位设置以及该种类型的数量
	 */
	@RequestMapping("setType")
	public void setType(Integer typeNumb,Integer roomAmount,Integer seatAmount,Integer[] setArray,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		//设置放映房间类型
		cinemaServ.setType(typeNumb,roomAmount,seatAmount,setArray);
		modelAndView.addObject("setTypeMsg", "设置完成");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 电影院管理员修改放映房间类型:可改进
	 */
	@RequestMapping("changeType")
	public void changeType(Integer typeNumb,Integer roomAmount,Integer[] setList,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		//设置放映房间类型
		cinemaServ.changeType(typeNumb,roomAmount,setList);
		modelAndView.addObject("setTypeMsg", "设置完成");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 电影院管理员放映房间类型补充设置
	 */
	@RequestMapping("setRoom")
	public void setRoom(Integer roomNumb,String roomName,String instruction,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		//设置房间具体的名字和说明
		cinemaServ.setRoom(roomNumb,roomName,instruction);
		modelAndView.addObject("setRoomMsg", "设置完成");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 电影院管理员放映房间类型补充设置
	 */
	@RequestMapping("delRoom")
	public void delRoom(Integer roomNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaServ.delRoom(roomNumb);//删除后应让放映间种类对应的房间数量减一
		modelAndView.addObject("setRoomMsg", "设置完成");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 查询放映间
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
	 * 查询某种类的放映间
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
	 * 电影院管理员提交电影放映批量设置：放映间号码和时段
	 */
	@RequestMapping("setSchedule")
	public void setSchedule(Integer movieId,Integer roomNumb,TimeInterval projectInterval,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaServ.setSchedule(movieId,roomNumb,projectInterval);
		modelAndView.addObject("setRoomMsg", "设置完成");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 电影院管理员修改电影放映批量设置
	 */
	@RequestMapping("changeSchedule")
	public void changeSchedule(Integer movieId,Integer roomNumb,TimeInterval projectInterval,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaServ.changeSchedule(movieId,roomNumb,projectInterval);
		modelAndView.addObject("changeSchedule", "修改完成");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 电影院管理员删除电影放映批量设置
	 */
	@RequestMapping("delSchedule")
	public void delSchedule(Integer movieId,Integer roomNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaServ.delSchedule(movieId,roomNumb);
		modelAndView.addObject("setRoomMsg", "删除完成");
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	@RequestMapping("getSchedule")
	public ModelAndView getSchedule(String loginWay,String loginWayValue,HttpServletRequest request){
		HttpSession session=request.getSession();
		//发起请求的uri：http:\\**\**.jsp,并非请求的url而是发起请求的URI，需要在发起请求时放进request参数中
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		
		List<TimeInterval> projectIntervalList=cinemaServ.getSchedule(loginWay,loginWayValue);
		if(projectIntervalList==null){
			mAndView.addObject("getScheduleMsg", "未有该时间段安排！");
			mAndView.setViewName("redirect:login.jsp");
			return mAndView;
		}else{
			mAndView.addObject("scheduleList", projectIntervalList);
			mAndView.setViewName("redirect:"+postURI);
			return mAndView;
		}

	}
}
