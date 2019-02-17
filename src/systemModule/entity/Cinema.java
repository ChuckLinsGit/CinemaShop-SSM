package systemModule.entity;

import java.sql.Date;

public class Cinema {
	private Integer cinemaNumb;
	private String cinamaName;
	private Date applyDate;
	private String cinemaLocationCity;
	private String cinemaLocationTown;
	private String detail;
	private Integer isOverdue;
	private Date durationBegin;
	private Date durationEnd;
	public Integer getCinemaNumb() {
		return cinemaNumb;
	}
	public void setCinemaNumb(Integer cinemaNumb) {
		this.cinemaNumb = cinemaNumb;
	}
	public String getCinamaName() {
		return cinamaName;
	}
	public void setCinamaName(String cinamaName) {
		this.cinamaName = cinamaName;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
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
	public Integer getIsOverdue() {
		return isOverdue;
	}
	public void setIsOverdue(Integer isOverdue) {
		this.isOverdue = isOverdue;
	}
	public Date getDurationBegin() {
		return durationBegin;
	}
	public void setDurationBegin(Date durationBegin) {
		this.durationBegin = durationBegin;
	}
	public Date getDurationEnd() {
		return durationEnd;
	}
	public void setDurationEnd(Date durationEnd) {
		this.durationEnd = durationEnd;
	}
	public Cinema() {
		super();
	}
	public Cinema(Integer cinemaNumb, String cinamaName, Date applyDate, String cinemaLocationCity,
			String cinemaLocationTown, String detail, Integer isOverdue, Date durationBegin, Date durationEnd) {
		super();
		this.cinemaNumb = cinemaNumb;
		this.cinamaName = cinamaName;
		this.applyDate = applyDate;
		this.cinemaLocationCity = cinemaLocationCity;
		this.cinemaLocationTown = cinemaLocationTown;
		this.detail = detail;
		this.isOverdue = isOverdue;
		this.durationBegin = durationBegin;
		this.durationEnd = durationEnd;
	}
	@Override
	public String toString() {
		return "Cinema [cinemaNumb=" + cinemaNumb + ", cinamaName=" + cinamaName + ", applyDate=" + applyDate
				+ ", cinemaLocationCity=" + cinemaLocationCity + ", cinemaLocationTown=" + cinemaLocationTown
				+ ", detail=" + detail + ", isOverdue=" + isOverdue + ", durationBegin=" + durationBegin
				+ ", durationEnd=" + durationEnd + "]";
	}
	
}
