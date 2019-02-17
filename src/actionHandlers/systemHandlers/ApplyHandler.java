package actionHandlers.systemHandlers;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import systemModule.entity.ApplyCinema;
import systemModule.entity.ApplyCinemaManager;
import systemModule.entity.Cinema;
import systemModule.entity.Manager;
import systemModule.service.SystemService;
import systemModule.service.systemServiceImpl;


@Controller
@RequestMapping("Sys/")
public class ApplyHandler {
	
	private SystemService sysServ;

	/**
	 * 接收影院申请
	 */
	@RequestMapping("AcceptCinemaApply")
	public ModelAndView  acceptCinemaApply(ApplyCinema apply,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		apply.setApplyDate((Date)(Calendar.getInstance()).getTime());
		sysServ.addCinemaApply(apply);
		modelAndView.addObject("AcceptCinemaApply"+"Msg", "申请已被成功接收");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * 接收影院对申请的修改
	 */
	@RequestMapping("AcceptCinemaModApply")
	public ModelAndView acceptCienmaModifyApply(ApplyCinema newApply,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.changeCinemaApply(newApply);
		modelAndView.addObject("AcceptCinemaModApply"+"Msg", "修改申请已被成功接收");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * 删除影院申请
	 */
	@RequestMapping("CancalCinemaApply")
	public ModelAndView cancalCinemaApply(Integer cinemaApplyNumb,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.cancalCinemaApply(cinemaApplyNumb);
		modelAndView.addObject("AcceptCinemaModApply"+"Msg", "删除申请已被成功接收");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * 查询影院申请
	 */
	@RequestMapping("GetCinemaApply")
	public ModelAndView getCinemaApply(Integer cinemaApplyNumb,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		List<ApplyCinema> applyCinemaList=sysServ.getCinemaApply();
		modelAndView.addObject("applyCinemaList", applyCinemaList);
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * 接收申请影院管理员的信息
	 */
	@RequestMapping("AcceptCManagerApply")
	public ModelAndView  acceptCManagerApply(ApplyCinemaManager apply,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.addCManagerApply(apply);
		modelAndView.addObject("AcceptCManagerApply"+"Msg", "申请已被成功接收");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * 修改申请影院管理员的信息
	 */
	@RequestMapping("AcceptCManagerModApply")
	public ModelAndView acceptCManagerModifyApply(ApplyCinemaManager newApply,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.changeCManagerApply(newApply);
		modelAndView.addObject("AcceptCManagerModApply"+"Msg", "修改申请已被成功接收");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * 删除申请影院管理员的信息
	 */
	@RequestMapping("CancalCManagerApply")
	public ModelAndView cancalCManagerApply(Integer managerApplyNumb,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.cancalCManagerApply(managerApplyNumb);
		modelAndView.addObject("CancalCManagerApply"+"Msg", "删除申请已被成功接收");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * 查询所有影院管理人的申请
	 */
	@RequestMapping("GetCinemaManagersApply")
	public ModelAndView getCinemaManagersApply(HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		List<ApplyCinemaManager> applyCinemaManagerList=sysServ.getCinemaManagersApply();
		modelAndView.addObject("applyCinemaManagerList", applyCinemaManagerList);
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * 查询某个影院管理人的申请
	 */
	@RequestMapping("GetCinemaManagerApply")
	public ModelAndView getCinemaManagerApply(Integer cinemaApplyNumb,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		ApplyCinemaManager applyCinemaManager=sysServ.getCinemaManagerApply(cinemaApplyNumb);
		modelAndView.addObject("applyCinemaManager", applyCinemaManager);
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * 同意影院申请
	 */
	@RequestMapping("SetInagree")
	public ModelAndView setCinemaInagree(Integer cinemaApplyNumb,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.setCinemaInagree(cinemaApplyNumb);
		modelAndView.addObject("SetInagree"+"Msg", "已同意申请");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * 修改达成协议的影院信息
	 */
	@RequestMapping("ModifyCinemaInagree")
	public ModelAndView modifyCinemaInagree(Cinema inAgree,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.modifyCinemaInagree(inAgree);
		modelAndView.addObject("ModifyCinemaInagree"+"Msg", "修改申请已被成功接收");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * 修改达成协议的影院管理人信息
	 */
	@RequestMapping("ModifyManagerInagree")
	public ModelAndView modifyManagerInagree(Manager inAgree,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.modifyManagerInagree(inAgree);
		modelAndView.addObject("ModifyManagerInagree"+"Msg", "修改申请已被成功接收");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * 删除达成协议的影院信息:删除影院记录的同时必须删除影院管理员的记录
	 */
	@RequestMapping("DelCinemaInagree")
	public ModelAndView delCinemaInagree(Integer cinemaNumb,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.delCinemaInagree(cinemaNumb);
		modelAndView.addObject("DelCinemaInagree"+"Msg", "删除申请已被成功接收");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
}
