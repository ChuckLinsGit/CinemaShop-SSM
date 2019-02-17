package systemModule.dao;

import java.util.List;
import java.util.Map;

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

public interface SystemDao {

	List<Cinema> getCinemas(Map<String, String> locationMap);

	List<CinemaMovie> getMoivesInCinemaOnProject(Integer cinameNumb);

	void addCatalog(String catalogName, String catalogDetail);

	void modifyCatalog(Integer catalogNumb, String catalogName, String catalogDetail);

	void deleteCatalog(Integer catalogNumb);

	List<Catalog> getCatalogs();

	Catalog getCatalogByName(String catalogName);

	void addMovie(Movie movie);

	void modifyMovie(Movie movie);

	void deleteMovie(Integer movieNumb);

	Movie getMovieByName(String movieName);

	Movie getMovieByNumb(Integer movieNumb);

	List<Movie> getMovieInCatalog(Integer movieCatalogNumb);

	void addCarousal(Integer movieNumb);

	void cancalCarousal(Integer movieNumb);

	void removeCarousal(Integer movieNumb);

	List<Carousal> getCarousal();

	List<Carousal> getBeCarousal();

	String ifExists(Integer managerNumb);

	String checkPassword(Integer managerNumb, String password);

	void addRole(Role newRole);

	void modifyRole(Role role);

	void deleteRole(Integer roleNumb);

	List<Role> getRoles();

	void addAuthority(String newAuthority);

	void modifyAuthority(Authority authority);

	void deleteAuthority(Integer authorityNumb);

	List<Authority> getAuthorities();

	void grant(Integer integer, Integer authorityNumb);

	void revoke(Integer roleNumb, Integer authorityNumb);

	void addCinameApply(ApplyCinema apply);

	void modifyCinemaApply(ApplyCinema newApply);

	void deleteCinemaApply(Integer cinemaApplyNumb);

	List<ApplyCinema> getCinemasApply();

	void addCManagerApoly(ApplyCinemaManager apply);

	void modigyCManagerApply(ApplyCinemaManager newApply);

	void deleteCManagerApply(Integer managerApplyNumb);

	List<ApplyCinemaManager> getCinemaManagersApply();

	ApplyCinemaManager getCinemaManagerApply(Integer cinemaApplyNumb);

	ApplyCinema setCinemaInagree(Integer cinemaApplyNumb);

	void addCinemaInagree(ApplyCinema applyCinema);

	void addCinemaManagerInagree(ApplyCinemaManager applyCinemaManager);

	void modigyCinemaInagree(Cinema inAgree);

	void modifyManagerInagree(Manager inAgree);

	void deleteCinemaInagree(Integer cinemaNumb);

	void deleteCinemaManager(Integer cinemaNumb);

}
