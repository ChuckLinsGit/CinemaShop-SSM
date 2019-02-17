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
	 * �����û��ύ��λ�û�ȡ�����ĵ�ӰԺ:�û�λ����jsp��ͨ�����ô��ṩ��API����ȡ�����û���������
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
	 * ѡ���ӰԺ����������ӳ�ĵ�Ӱ�б�
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
	 * �û�ѡ���Ӱ�������ӳʱ��չʾ
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
