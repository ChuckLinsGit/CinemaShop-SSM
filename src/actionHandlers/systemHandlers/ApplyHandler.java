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
	 * ����ӰԺ����
	 */
	@RequestMapping("AcceptCinemaApply")
	public ModelAndView  acceptCinemaApply(ApplyCinema apply,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		apply.setApplyDate((Date)(Calendar.getInstance()).getTime());
		sysServ.addCinemaApply(apply);
		modelAndView.addObject("AcceptCinemaApply"+"Msg", "�����ѱ��ɹ�����");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * ����ӰԺ��������޸�
	 */
	@RequestMapping("AcceptCinemaModApply")
	public ModelAndView acceptCienmaModifyApply(ApplyCinema newApply,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.changeCinemaApply(newApply);
		modelAndView.addObject("AcceptCinemaModApply"+"Msg", "�޸������ѱ��ɹ�����");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * ɾ��ӰԺ����
	 */
	@RequestMapping("CancalCinemaApply")
	public ModelAndView cancalCinemaApply(Integer cinemaApplyNumb,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.cancalCinemaApply(cinemaApplyNumb);
		modelAndView.addObject("AcceptCinemaModApply"+"Msg", "ɾ�������ѱ��ɹ�����");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * ��ѯӰԺ����
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
	 * ��������ӰԺ����Ա����Ϣ
	 */
	@RequestMapping("AcceptCManagerApply")
	public ModelAndView  acceptCManagerApply(ApplyCinemaManager apply,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.addCManagerApply(apply);
		modelAndView.addObject("AcceptCManagerApply"+"Msg", "�����ѱ��ɹ�����");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * �޸�����ӰԺ����Ա����Ϣ
	 */
	@RequestMapping("AcceptCManagerModApply")
	public ModelAndView acceptCManagerModifyApply(ApplyCinemaManager newApply,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.changeCManagerApply(newApply);
		modelAndView.addObject("AcceptCManagerModApply"+"Msg", "�޸������ѱ��ɹ�����");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * ɾ������ӰԺ����Ա����Ϣ
	 */
	@RequestMapping("CancalCManagerApply")
	public ModelAndView cancalCManagerApply(Integer managerApplyNumb,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.cancalCManagerApply(managerApplyNumb);
		modelAndView.addObject("CancalCManagerApply"+"Msg", "ɾ�������ѱ��ɹ�����");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * ��ѯ����ӰԺ�����˵�����
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
	 * ��ѯĳ��ӰԺ�����˵�����
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
	 * ͬ��ӰԺ����
	 */
	@RequestMapping("SetInagree")
	public ModelAndView setCinemaInagree(Integer cinemaApplyNumb,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.setCinemaInagree(cinemaApplyNumb);
		modelAndView.addObject("SetInagree"+"Msg", "��ͬ������");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * �޸Ĵ��Э���ӰԺ��Ϣ
	 */
	@RequestMapping("ModifyCinemaInagree")
	public ModelAndView modifyCinemaInagree(Cinema inAgree,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.modifyCinemaInagree(inAgree);
		modelAndView.addObject("ModifyCinemaInagree"+"Msg", "�޸������ѱ��ɹ�����");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * �޸Ĵ��Э���ӰԺ��������Ϣ
	 */
	@RequestMapping("ModifyManagerInagree")
	public ModelAndView modifyManagerInagree(Manager inAgree,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.modifyManagerInagree(inAgree);
		modelAndView.addObject("ModifyManagerInagree"+"Msg", "�޸������ѱ��ɹ�����");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
	
	/**
	 * ɾ�����Э���ӰԺ��Ϣ:ɾ��ӰԺ��¼��ͬʱ����ɾ��ӰԺ����Ա�ļ�¼
	 */
	@RequestMapping("DelCinemaInagree")
	public ModelAndView delCinemaInagree(Integer cinemaNumb,HttpServletRequest request) {
		String URIPost=(String) request.getAttribute("URIPost");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.delCinemaInagree(cinemaNumb);
		modelAndView.addObject("DelCinemaInagree"+"Msg", "ɾ�������ѱ��ɹ�����");
		modelAndView.setViewName("redirect:"+URIPost);
		return modelAndView;
	}
}
