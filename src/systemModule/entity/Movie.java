package systemModule.entity;

import java.sql.Date;

public class Movie {
	private Integer movieNumb;
	private String movieName;
	private Integer movieCatalogNumb;
	private String movieDirector;
	private String movieChiefActors;
	private Date movieProjectDate;
	private String chiefInstruction;
	private String postLocalPath;
	
	public Integer getMovieNumb() {
		return movieNumb;
	}
	public void setMovieNumb(Integer movieNumb) {
		this.movieNumb = movieNumb;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public Integer getMovieCatalogNumb() {
		return movieCatalogNumb;
	}
	public void setMovieCatalogNumb(Integer movieCatalogNumb) {
		this.movieCatalogNumb = movieCatalogNumb;
	}
	public String getMovieDirector() {
		return movieDirector;
	}
	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}
	public String getMovieChiefActors() {
		return movieChiefActors;
	}
	public void setMovieChiefActors(String movieChiefActors) {
		this.movieChiefActors = movieChiefActors;
	}
	public Date getMovieProjectDate() {
		return movieProjectDate;
	}
	public void setMovieProjectDate(Date movieProjectDate) {
		this.movieProjectDate = movieProjectDate;
	}
	public String getChiefInstruction() {
		return chiefInstruction;
	}
	public void setChiefInstruction(String chiefInstruction) {
		this.chiefInstruction = chiefInstruction;
	}
	public String getPostLocalPath() {
		return postLocalPath;
	}
	public void setPostLocalPath(String postLocalPath) {
		this.postLocalPath = postLocalPath;
	}
	public Movie() {
		super();
	}
	public Movie(Integer movieNumb, String movieName, Integer movieCatalogNumb, String movieDirector,
			String movieChiefActors, Date movieProjectDate, String chiefInstruction, String postLocalPath) {
		super();
		this.movieNumb = movieNumb;
		this.movieName = movieName;
		this.movieCatalogNumb = movieCatalogNumb;
		this.movieDirector = movieDirector;
		this.movieChiefActors = movieChiefActors;
		this.movieProjectDate = movieProjectDate;
		this.chiefInstruction = chiefInstruction;
		this.postLocalPath = postLocalPath;
	}
	@Override
	public String toString() {
		return "Movie [movieNumb=" + movieNumb + ", movieName=" + movieName + ", movieCatalogNumb=" + movieCatalogNumb
				+ ", movieDirector=" + movieDirector + ", movieChiefActors=" + movieChiefActors + ", movieProjectDate="
				+ movieProjectDate + ", chiefInstruction=" + chiefInstruction + ", postLocalPath=" + postLocalPath
				+ "]";
	}
	
	
}
