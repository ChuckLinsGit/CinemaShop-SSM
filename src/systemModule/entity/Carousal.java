package systemModule.entity;

public class Carousal {
	private Integer inCarousal;
	private Integer movieNumb;
	public Integer getInCarousal() {
		return inCarousal;
	}
	public void setInCarousal(Integer inCarousal) {
		this.inCarousal = inCarousal;
	}
	public Integer getMovieNumb() {
		return movieNumb;
	}
	public void setMovieNumb(Integer movieNumb) {
		this.movieNumb = movieNumb;
	}
	public Carousal() {
		super();
	}
	public Carousal(Integer inCarousal, Integer movieNumb) {
		super();
		this.inCarousal = inCarousal;
		this.movieNumb = movieNumb;
	}
	@Override
	public String toString() {
		return "Carousal [inCarousal=" + inCarousal + ", movieNumb=" + movieNumb + "]";
	}
	
}
