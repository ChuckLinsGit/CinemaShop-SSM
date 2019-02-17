package systemModule.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cinemaModule.dao.CinemaSetDao;
import cinemaModule.entity.CinemaMovie;
import cinemaModule.entity.TimeInterval;
import systemModule.dao.SystemDao;
import systemModule.entity.ApplyCinema;
import systemModule.entity.ApplyCinemaManager;
import systemModule.entity.Authority;
import systemModule.entity.Carousal;
import systemModule.entity.Catalog;
import systemModule.entity.Cinema;
import systemModule.entity.Manager;
import systemModule.entity.Movie;
import systemModule.entity.Role;

public class systemServiceImpl implements SystemService {

	private SystemDao systemDao;
	private CinemaSetDao cinemaMovieDao;

	//查询城市中的电影院：可能出现只能精确到城市的问题，因此，在查询语句中应该判断town是否未null，为null不查询
	@Override
	public List<Cinema> getCinemas(String city, String town) {
		Map<String , String> locationMap=new HashMap<String, String>();
		locationMap.put("city", city);
		locationMap.put("town", town);
		return systemDao.getCinemas(locationMap);
	}

	//返回正在上映的电影：因此需要甄别是否在上映
	@Override
	public List<CinemaMovie> getMoviesInCinema(Integer cinameNumb) {
		return systemDao.getMoivesInCinemaOnProject(cinameNumb);
	}

	@Override
	public List<TimeInterval> getMovieSchedule(Integer movieId) {
		Map<String,Integer> wayMap=new HashMap<String,Integer>();
		wayMap.put("movieId", movieId);
		return cinemaMovieDao.getSchedule(wayMap);
	}

	@Override
	public void addCatalog(String catalogName, String catalogDetail) {
		systemDao.addCatalog(catalogName,catalogDetail);
	}

	@Override
	public void changeCatalog(Integer catalogNumb, String catalogName, String catalogDetail) {
		systemDao.modifyCatalog(catalogNumb,catalogName,catalogDetail);
	}

	@Override
	public void deleteCataolg(Integer catalogNumb) {
		systemDao.deleteCatalog(catalogNumb);
	}

	@Override
	public List<Catalog> getCatalogs() {
		return systemDao.getCatalogs();
	}

	@Override
	public Catalog getCatalogByName(String catalogName) {
		return systemDao.getCatalogByName(catalogName);
	}

	@Override
	public void addNewMovie(Movie movie) {
		systemDao.addMovie(movie);
	}

	@Override
	public void changeMovie(Movie movie) {
		systemDao.modifyMovie(movie);
	}

	@Override
	public void deleteMovie(Integer movieNumb) {
		systemDao.deleteMovie(movieNumb);
	}

	@Override
	public Movie getMovieByName(String movieName) {
		return systemDao.getMovieByName(movieName);
	}

	@Override
	public Movie getMovieByNumb(Integer movieNumb) {
		return systemDao.getMovieByNumb(movieNumb);
	}

	@Override
	public List<Movie> getMovieInCatalog(Integer movieCatalogNumb) {
		return systemDao.getMovieInCatalog(movieCatalogNumb);
	}

	@Override
	public void setCarousal(Integer movieNumb) {
		systemDao.addCarousal(movieNumb);
	}

	@Override
	public void cancalCarousal(Integer movieNumb) {
		systemDao.cancalCarousal(movieNumb);
	}

	@Override
	public void removeCarousal(Integer movieNumb) {
		systemDao.removeCarousal(movieNumb);
	}

	@Override
	public List<Carousal> getCarousal() {
		return systemDao.getCarousal();
	}

	@Override
	public List<Carousal> getBeCarousal() {
		return systemDao.getBeCarousal();
	}

	@Override
	public boolean ifExists(Integer managerNumb) {
		String managerName=systemDao.ifExists(managerNumb);
		if (!managerName.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkPassword(Integer managerNumb, String password) {
		String managerName=systemDao.checkPassword(managerNumb,password);
		if (!managerName.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public void addRole(Role newRole) {
		systemDao.addRole(newRole);
	}

	@Override
	public void changeRoleData(Role role) {
		systemDao.modifyRole(role);
	}

	//同时需要删除角色-权限表中的记录
	@Override
	public void deleteRole(Integer roleNumb) {
		systemDao.deleteRole(roleNumb);
	}

	@Override
	public List<Role> getRoles() {
		return systemDao.getRoles();
	}

	@Override
	public void addAuthority(String newAuthority) {
		systemDao.addAuthority(newAuthority);
	}

	@Override
	public void changeAuthority(Authority authority) {
		systemDao.modifyAuthority(authority);
	}

	@Override
	public void deleteAuthority(Integer authorityNumb) {
		systemDao.deleteAuthority(authorityNumb);
	}

	@Override
	public List<Authority> getAuthorities() {
		return systemDao.getAuthorities();
	}

	@Override
	public void grant(Integer roleNumb, Integer authorityNumb) {
		systemDao.grant(roleNumb,authorityNumb);
	}

	@Override
	public void revoke(Integer roleNumb, Integer authorityNumb) {
		systemDao.revoke(roleNumb,authorityNumb);
	}

	@Override
	public void addCinemaApply(ApplyCinema apply) {
		systemDao.addCinameApply(apply);
	}

	@Override
	public void changeCinemaApply(ApplyCinema newApply) {
		systemDao.modifyCinemaApply(newApply);
	}

	@Override
	public void cancalCinemaApply(Integer cinemaApplyNumb) {
		systemDao.deleteCinemaApply(cinemaApplyNumb);
	}

	@Override
	public List<ApplyCinema> getCinemaApply() {
		return systemDao.getCinemasApply();
	}

	@Override
	public void addCManagerApply(ApplyCinemaManager apply) {
		systemDao.addCManagerApoly(apply);
	}

	@Override
	public void changeCManagerApply(ApplyCinemaManager newApply) {
		systemDao.modigyCManagerApply(newApply);
	}

	@Override
	public void cancalCManagerApply(Integer managerApplyNumb) {
		systemDao.deleteCManagerApply(managerApplyNumb);
	}

	@Override
	public List<ApplyCinemaManager> getCinemaManagersApply() {
		return systemDao.getCinemaManagersApply();
	}

	@Override
	public ApplyCinemaManager getCinemaManagerApply(Integer cinemaApplyNumb) {
		return systemDao.getCinemaManagerApply(cinemaApplyNumb);
	}

	@Override
	public void setCinemaInagree(Integer cinemaApplyNumb) {
		ApplyCinema applyCinema=systemDao.setCinemaInagree(cinemaApplyNumb);
		systemDao.addCinemaInagree(applyCinema);
		ApplyCinemaManager applyCinemaManager=systemDao.getCinemaManagerApply(cinemaApplyNumb);
		systemDao.addCinemaManagerInagree(applyCinemaManager);
	}

	@Override
	public void modifyCinemaInagree(Cinema inAgree) {
		systemDao.modigyCinemaInagree(inAgree);
	}

	@Override
	public void modifyManagerInagree(Manager inAgree) {
		systemDao.modifyManagerInagree(inAgree);
	}

	@Override
	public void delCinemaInagree(Integer cinemaNumb) {
		systemDao.deleteCinemaInagree(cinemaNumb);
		systemDao.deleteCinemaManager(cinemaNumb);
	}

}
