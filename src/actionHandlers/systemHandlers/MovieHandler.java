package actionHandlers.systemHandlers;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import systemModule.entity.Carousal;
import systemModule.entity.Catalog;
import systemModule.entity.Movie;
import systemModule.service.SystemService;


@Controller
@RequestMapping("Sys/")
public class MovieHandler  {
	
	private SystemService sysServ;
	
	/**
	 * ���ӵ�Ӱ����Ŀ¼��
	 */
	@RequestMapping("AddCatalog")
	public ModelAndView addCatalog(String catalogName,String catalogDetail,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.addCatalog(catalogName,catalogDetail);
		modelAndView.addObject("AddCatalogMsg", "������");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * �޸ĵ�Ӱ����Ŀ¼��
	 */
	@RequestMapping("ChangeCatalog")
	public ModelAndView changeCatalog(Integer catalogNumb,String catalogName,String catalogDetail,HttpServletRequest request) {
		
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.changeCatalog(catalogNumb,catalogName,catalogDetail);
		modelAndView.addObject("ChangeCatalogMsg", "�޸����");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * ɾ����Ӱ����Ŀ¼��
	 */
	@RequestMapping("DelCatalog")
	public  ModelAndView deleteCatalog(Integer catalogNumb,HttpServletRequest request) {
		
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.deleteCataolg(catalogNumb);
		modelAndView.addObject("DelCatalogMsg", "�޸����");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * ��ȡ��Ӱ����Ŀ¼��
	 */
	@RequestMapping("GetCatalogs")
	public ModelAndView getCatalogs(HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		List<Catalog> catalogList=sysServ.getCatalogs();
		modelAndView.addObject("catalogList", catalogList);
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * ��ȡ��Ӱ����Ŀ¼��
	 */
	@RequestMapping("GetCatalogByName")
	public ModelAndView getCatalogByName(String CatalogName,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		Catalog catalog=sysServ.getCatalogByName(CatalogName);
		modelAndView.addObject("catalogList", catalog);
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}

	/**
	 * ���ӵ�Ӱ
	 */
	@RequestMapping("AddNewMovies")
	public ModelAndView addNewMovies(Movie movie,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.addNewMovie(movie);
		modelAndView.addObject("AddNewMoviesMsg", "��ӳɹ�");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * �޸ĵ�Ӱ
	 */
	@RequestMapping("ChangeMovie")
	public ModelAndView  changeMovie(Movie movie,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.changeMovie(movie);
		modelAndView.addObject("ChangeMovieMsg", "�޸ĳɹ�");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * ɾ����Ӱ
	 */
	@RequestMapping("DelMovie")
	public ModelAndView deleteMovie(Integer movieNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.deleteMovie(movieNumb);
		modelAndView.addObject("DelMovieMsg", "ɾ���ɹ�");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * ��ȡ��Ӱ
	 */
	@RequestMapping("GetMovieByName")
	public ModelAndView getMovieByName(String movieName,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		Movie movie=sysServ.getMovieByName(movieName);
		modelAndView.addObject("movie",movie);
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * ��ȡ��Ӱ
	 */
	@RequestMapping("GetMovieByNumb")
	public ModelAndView getMovieByNumb(Integer movieNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		Movie movie=sysServ.getMovieByNumb(movieNumb);
		modelAndView.addObject("getMovieByNumb",movie);
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * ��ȡ��Ӱ
	 */
	@RequestMapping("GetMovieInCatalog")
	public ModelAndView getMovieInCatalog(Integer movieCatalogNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		List<Movie> movieList=sysServ.getMovieInCatalog(movieCatalogNumb);
		modelAndView.addObject("getMomovieListvieByNumb",movieList);
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * �����ֲ�
	 */
	@RequestMapping("SetCarousal")
	public ModelAndView setCarousal(Integer movieNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.setCarousal(movieNumb);
		modelAndView.addObject("SetCarousalMsg","���óɹ�");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * ȡ���ֲ�
	 */
	@RequestMapping("CancalCarousal")
	public ModelAndView cancalCarousal(Integer movieNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.cancalCarousal(movieNumb);
		modelAndView.addObject("CancalCarousalMsg","ȡ���ɹ�");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * ɾ���ֲ���¼
	 */
	@RequestMapping("RemoveCarousal")
	public  ModelAndView removeCarousal(Integer movieNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.removeCarousal(movieNumb);
		modelAndView.addObject("RemoveCarousalMsg","ɾ���ɹ�");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * ��ȡ�ֲ���¼
	 */
	@RequestMapping("GetCarousal")
	public  ModelAndView getCarousal(HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		List<Carousal> carousalList=sysServ.getCarousal();
		modelAndView.addObject("carousalList",carousalList);
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * ��ȡ�����ֲ���¼
	 */
	@RequestMapping("GetBeCarousal")
	public  ModelAndView getBeCarousal(HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		List<Carousal> carousalList=sysServ.getBeCarousal();
		modelAndView.addObject("carousal",carousalList);
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	
}



