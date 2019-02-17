package actionHandlers.cinemaHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cinemaModule.service.CinemaVipService;

@Controller
@RequestMapping("cinema/")
public class CustomerLevelHandler {
	
	private CinemaVipService cinemaVipServ;

	/**
	 * ���û�Ա�ȼ�
	 */
	@RequestMapping("LevelSet")
	public ModelAndView cinemaLevelTotalSet(Integer levelCatalogy,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaVipServ.totalSet(levelCatalogy);
		modelAndView.addObject("LevelSetMsg", "�������");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * ���û�Ա����ȼ�����
	 */
	@RequestMapping("EachLevelSet")
	public ModelAndView eachLevelSet(Integer level,Float discount,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaVipServ.eachLevelSet(level,discount);
		modelAndView.addObject("EachLevelSetMsg", "�������");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;	
	}
	
	/**
	 * ���Ļ�Ա�ȼ�����
	 */
	@RequestMapping("ChangeLevelSet")
	public ModelAndView changeCustomLevelSet(Integer level,Float discount,HttpServletRequest request) {		
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaVipServ.ChangeLevelSet(level,discount);
		modelAndView.addObject("ChangeLevelSetMsg", "�������");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * ���Ļ�Ա�ȼ�
	 */
	@RequestMapping("ChangeCustomLevel")
	public ModelAndView changeCustomLevel(Integer customNumb,Integer newLevel,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaVipServ.changeCustomLevel(customNumb,newLevel);
		modelAndView.addObject("ChangeLevelSetMsg", "�������");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
}
