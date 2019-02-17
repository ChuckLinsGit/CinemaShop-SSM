package actionHandlers.systemHandlers;

import java.io.BufferedReader;	
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.javafx.css.CssError.InlineStyleParsingError;

import actionHandlers.systemHandlers.ServletException;
import cinemaModule.entity.CinemaMovie;
import cinemaModule.entity.TimeInterval;
import systemModule.entity.Cinema;
import systemModule.service.SystemService;


@Controller
@RequestMapping("Sys/")
public class CinemaSearchHandler{
	
	private SystemService sysServ;

	/**
	 * 根据用户提交的位置获取附近的电影院:用户位置在jsp中通过调用大厂提供的API来获取或者用户主动输入
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("customerGetCinemas.do")
	public void getCinemas(String city,String Town,HttpServletRequest request){
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		List<Cinema> cinemas = sysServ.getCinemas(city,Town);
		modelAndView.addObject("cinemas", cinemas);
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 选择电影院返回正在上映的电影列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("customerSelectCinema.do")
	public void getCinema(Integer cinameNumb,HttpServletRequest request){
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		List<CinemaMovie> cinemaMovies = sysServ.getMoviesInCinema(cinameNumb);
		modelAndView.addObject("cinemaMovies", cinemaMovies);
		modelAndView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 用户选择电影，进入放映时段展示
	 */
	@RequestMapping("customerSelectMovie")
	public void selectMoive(Integer movieId,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		List<TimeInterval> intervals = sysServ.getMovieSchedule(movieId);
		modelAndView.addObject("intervals", intervals);
		modelAndView.setViewName("redirect:"+postURI);
	}
	

}
