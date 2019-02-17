package cinemaModule.entity;

import java.security.KeyStore.PrivateKeyEntry;
import java.sql.Date;

public class CinemaMovie {
	private Integer movieNumb;
	private Integer cinemaNumb;
	private String movieName;
	private Integer movieDuration;
	private Float ticketPrice;
	private Integer isOverDue;
	private Date startDate;
	public Integer getMovieNumb() {
		return movieNumb;
	}
	public void setMovieNumb(Integer movieNumb) {
		this.movieNumb = movieNumb;
	}
	public Integer getCinemaNumb() {
		return cinemaNumb;
	}
	public void setCinemaNumb(Integer cinemaNumb) {
		this.cinemaNumb = cinemaNumb;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public Integer getMovieDuration() {
		return movieDuration;
	}
	public void setMovieDuration(Integer movieDuration) {
		this.movieDuration = movieDuration;
	}
	public Float getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(Float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public Integer getIsOverDue() {
		return isOverDue;
	}
	public void setIsOverDue(Integer isOverDue) {
		this.isOverDue = isOverDue;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public CinemaMovie() {
		super();
	}
	public CinemaMovie(Integer movieNumb, Integer cinemaNumb, String movieName, Integer movieDuration,
			Float ticketPrice, Integer isOverDue, Date startDate) {
		super();
		this.movieNumb = movieNumb;
		this.cinemaNumb = cinemaNumb;
		this.movieName = movieName;
		this.movieDuration = movieDuration;
		this.ticketPrice = ticketPrice;
		this.isOverDue = isOverDue;
		this.startDate = startDate;
	}
	@Override
	public String toString() {
		return "CinemaMovie [movieNumb=" + movieNumb + ", cinemaNumb=" + cinemaNumb + ", movieName=" + movieName
				+ ", movieDuration=" + movieDuration + ", ticketPrice=" + ticketPrice + ", isOverDue=" + isOverDue
				+ ", startDate=" + startDate + "]";
	}
	
}
