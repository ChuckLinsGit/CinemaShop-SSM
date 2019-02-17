package systemModule.service;

import java.util.List;

import cinemaModule.entity.CinemaMovie;
import cinemaModule.entity.TimeInterval;
import systemModule.entity.ApplyCinema;
import systemModule.entity.ApplyCinemaManager;
import systemModule.entity.Authority;
import systemModule.entity.Carousal;
import systemModule.entity.Catalog;
import systemModule.entity.Cinema;
import systemModule.entity.Manager;
import systemModule.entity.Movie;
import systemModule.entity.Role;


public interface SystemService {

	List<Cinema> getCinemas(String city, String town);

	List<CinemaMovie> getMoviesInCinema(Integer cinameNumb);

	List<TimeInterval> getMovieSchedule(Integer movieId);

	void addCatalog(String catalogName, String catalogDetail);

	void changeCatalog(Integer catalogNumb, String catalogName, String catalogDetail);

	void deleteCataolg(Integer catalogNumb);

	List<Catalog> getCatalogs();

	Catalog getCatalogByName(String catalogName);

	void addNewMovie(Movie movie);

	void changeMovie(Movie movie);

	void deleteMovie(Integer movieNumb);

	Movie getMovieByName(String movieName);

	Movie getMovieByNumb(Integer movieNumb);

	List<Movie> getMovieInCatalog(Integer movieCatalogNumb);

	void setCarousal(Integer movieNumb);

	void cancalCarousal(Integer movieNumb);

	void removeCarousal(Integer movieNumb);

	List<Carousal> getCarousal();

	List<Carousal> getBeCarousal();

	boolean ifExists(Integer managerNumb);

	boolean checkPassword(Integer managerNumb, String password);

	void addRole(Role newRole);

	void changeRoleData(Role role);

	void deleteRole(Integer roleNumb);

	List<Role> getRoles();

	void addAuthority(String newAuthority);

	void changeAuthority(Authority authority);

	void deleteAuthority(Integer authorityNumb);

	List<Authority> getAuthorities();

	void grant(Integer roleNumb, Integer authorityNumb);

	void revoke(Integer roleNumb, Integer authorityNumb);

	void addCinemaApply(ApplyCinema apply);

	void changeCinemaApply(ApplyCinema newApply);

	void cancalCinemaApply(Integer cinemaApplyNumb);

	List<ApplyCinema> getCinemaApply();

	void addCManagerApply(ApplyCinemaManager apply);

	void changeCManagerApply(ApplyCinemaManager newApply);

	void cancalCManagerApply(Integer managerApplyNumb);

	List<ApplyCinemaManager> getCinemaManagersApply();

	ApplyCinemaManager getCinemaManagerApply(Integer cinemaApplyNumb);

	void setCinemaInagree(Integer cinemaApplyNumb);

	void modifyCinemaInagree(Cinema inAgree);

	void modifyManagerInagree(Manager inAgree);

	void delCinemaInagree(Integer cinemaNumb);

}
