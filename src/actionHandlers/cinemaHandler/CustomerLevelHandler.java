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
	 * 设置会员等级
	 */
	@RequestMapping("LevelSet")
	public ModelAndView cinemaLevelTotalSet(Integer levelCatalogy,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaVipServ.totalSet(levelCatalogy);
		modelAndView.addObject("LevelSetMsg", "设置完成");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * 设置会员具体等级内容
	 */
	@RequestMapping("EachLevelSet")
	public ModelAndView eachLevelSet(Integer level,Float discount,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaVipServ.eachLevelSet(level,discount);
		modelAndView.addObject("EachLevelSetMsg", "设置完成");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;	
	}
	
	/**
	 * 更改会员等级内容
	 */
	@RequestMapping("ChangeLevelSet")
	public ModelAndView changeCustomLevelSet(Integer level,Float discount,HttpServletRequest request) {		
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaVipServ.ChangeLevelSet(level,discount);
		modelAndView.addObject("ChangeLevelSetMsg", "设置完成");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * 更改会员等级
	 */
	@RequestMapping("ChangeCustomLevel")
	public ModelAndView changeCustomLevel(Integer customNumb,Integer newLevel,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		cinemaVipServ.changeCustomLevel(customNumb,newLevel);
		modelAndView.addObject("ChangeLevelSetMsg", "设置完成");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
}
