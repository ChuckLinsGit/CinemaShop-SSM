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
	 * 增加电影分类目录条
	 */
	@RequestMapping("AddCatalog")
	public ModelAndView addCatalog(String catalogName,String catalogDetail,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.addCatalog(catalogName,catalogDetail);
		modelAndView.addObject("AddCatalogMsg", "添加完成");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * 修改电影分类目录条
	 */
	@RequestMapping("ChangeCatalog")
	public ModelAndView changeCatalog(Integer catalogNumb,String catalogName,String catalogDetail,HttpServletRequest request) {
		
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.changeCatalog(catalogNumb,catalogName,catalogDetail);
		modelAndView.addObject("ChangeCatalogMsg", "修改完成");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * 删除电影分类目录条
	 */
	@RequestMapping("DelCatalog")
	public  ModelAndView deleteCatalog(Integer catalogNumb,HttpServletRequest request) {
		
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.deleteCataolg(catalogNumb);
		modelAndView.addObject("DelCatalogMsg", "修改完成");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * 获取电影分类目录条
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
	 * 获取电影分类目录条
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
	 * 增加电影
	 */
	@RequestMapping("AddNewMovies")
	public ModelAndView addNewMovies(Movie movie,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.addNewMovie(movie);
		modelAndView.addObject("AddNewMoviesMsg", "添加成功");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * 修改电影
	 */
	@RequestMapping("ChangeMovie")
	public ModelAndView  changeMovie(Movie movie,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.changeMovie(movie);
		modelAndView.addObject("ChangeMovieMsg", "修改成功");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * 删除电影
	 */
	@RequestMapping("DelMovie")
	public ModelAndView deleteMovie(Integer movieNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.deleteMovie(movieNumb);
		modelAndView.addObject("DelMovieMsg", "删除成功");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * 获取电影
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
	 * 获取电影
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
	 * 获取电影
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
	 * 设置轮播
	 */
	@RequestMapping("SetCarousal")
	public ModelAndView setCarousal(Integer movieNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.setCarousal(movieNumb);
		modelAndView.addObject("SetCarousalMsg","设置成功");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * 取消轮播
	 */
	@RequestMapping("CancalCarousal")
	public ModelAndView cancalCarousal(Integer movieNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.cancalCarousal(movieNumb);
		modelAndView.addObject("CancalCarousalMsg","取消成功");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * 删除轮播记录
	 */
	@RequestMapping("RemoveCarousal")
	public  ModelAndView removeCarousal(Integer movieNumb,HttpServletRequest request) {
		String postURI = request.getParameter("postURI");
		ModelAndView modelAndView=new ModelAndView();
		sysServ.removeCarousal(movieNumb);
		modelAndView.addObject("RemoveCarousalMsg","删除成功");
		modelAndView.setViewName("redirect:"+postURI);
		return modelAndView;
	}
	
	/**
	 * 获取轮播记录
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
	 * 获取正在轮播记录
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



