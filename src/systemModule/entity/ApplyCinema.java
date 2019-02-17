package systemModule.entity;

import java.sql.Date;

public class ApplyCinema {
	private Integer cinemaApplyNumb;
	private String cinamaName;
	private String cinemaLocationCity;
	private String cinemaLocationTown;
	private String detail;
	private Integer isAccepted;
	private Date applyDate;
	public Integer getCinemaApplyNumb() {
		return cinemaApplyNumb;
	}
	public void setCinemaApplyNumb(Integer cinemaApplyNumb) {
		this.cinemaApplyNumb = cinemaApplyNumb;
	}
	public String getCinamaName() {
		return cinamaName;
	}
	public void setCinamaName(String cinamaName) {
		this.cinamaName = cinamaName;
	}
	public String getCinemaLocationCity() {
		return cinemaLocationCity;
	}
	public void setCinemaLocationCity(String cinemaLocationCity) {
		this.cinemaLocationCity = cinemaLocationCity;
	}
	public String getCinemaLocationTown() {
		return cinemaLocationTown;
	}
	public void setCinemaLocationTown(String cinemaLocationTown) {
		this.cinemaLocationTown = cinemaLocationTown;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Integer getIsAccepted() {
		return isAccepted;
	}
	public void setIsAccepted(Integer isAccepted) {
		this.isAccepted = isAccepted;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public ApplyCinema() {
		super();
	}
	public ApplyCinema(Integer cinemaApplyNumb, String cinamaName, String cinemaLocationCity, String cinemaLocationTown,
			String detail, Integer isAccepted, Date applyDate) {
		super();
		this.cinemaApplyNumb = cinemaApplyNumb;
		this.cinamaName = cinamaName;
		this.cinemaLocationCity = cinemaLocationCity;
		this.cinemaLocationTown = cinemaLocationTown;
		this.detail = detail;
		this.isAccepted = isAccepted;
		this.applyDate = applyDate;
	}
	@Override
	public String toString() {
		return "ApplyCinema [cinemaApplyNumb=" + cinemaApplyNumb + ", cinamaName=" + cinamaName
				+ ", cinemaLocationCity=" + cinemaLocationCity + ", cinemaLocationTown=" + cinemaLocationTown
				+ ", detail=" + detail + ", isAccepted=" + isAccepted + ", applyDate=" + applyDate + "]";
	}
	
}
