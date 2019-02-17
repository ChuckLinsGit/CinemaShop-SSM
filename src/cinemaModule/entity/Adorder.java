package cinemaModule.entity;

import javax.management.loading.PrivateClassLoader;

import sun.nio.cs.ext.TIS_620;

public class Adorder {

	private Integer customNumb;
	private Integer scheduleNumb;
	private String seat;
	private Integer ticketAmount;
	private Float totalvalue;
	private Integer adOrderNumb;
	private Integer isOverDue;
	private Integer isDeal;
	private Integer roomNumb;

	public Integer getAdOrderNumb() {
		return adOrderNumb;
	}

	public void setAdOrderNumb(Integer adOrderNumb) {
		this.adOrderNumb = adOrderNumb;
	}

	public Integer getIsOverDue() {
		return isOverDue;
	}

	public void setIsOverDue(Integer isOverDue) {
		this.isOverDue = isOverDue;
	}

	public Integer getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(Integer isDeal) {
		this.isDeal = isDeal;
	}

	public Integer getCustomNumb() {
		return this.customNumb;
	}

	public Integer getScheduleNumb() {
		return this.scheduleNumb;
	}

	public String getSeat() {
		return this.seat;
	}

	public Integer getTicketAmount() {
		return this.ticketAmount;
	}

	public Float getTotalValue() {
		return this.totalvalue;
	}

	public Integer getRoomNumb() {
		return this.roomNumb;
	}
	
	public void setRoomNumb(Integer roomNumb) {
		this.roomNumb = roomNumb;
	}

	public Float getTotalvalue() {
		return totalvalue;
	}

	public void setTotalvalue(Float totalvalue) {
		this.totalvalue = totalvalue;
	}

	public void setCustomNumb(Integer customNumb) {
		this.customNumb = customNumb;
	}

	public void setScheduleNumb(Integer scheduleNumb) {
		this.scheduleNumb = scheduleNumb;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public void setTicketAmount(Integer ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public Adorder() {
		super();
	}

	public Adorder(Integer customNumb, Integer scheduleNumb, String seat, Integer ticketAmount, Float totalvalue,
			Integer adOrderNumb, Integer isOverDue, Integer isDeal, Integer roomNumb) {
		super();
		this.customNumb = customNumb;
		this.scheduleNumb = scheduleNumb;
		this.seat = seat;
		this.ticketAmount = ticketAmount;
		this.totalvalue = totalvalue;
		this.adOrderNumb = adOrderNumb;
		this.isOverDue = isOverDue;
		this.isDeal = isDeal;
		this.roomNumb = roomNumb;
	}

	@Override
	public String toString() {
		return "Adorder [customNumb=" + customNumb + ", scheduleNumb=" + scheduleNumb + ", seat=" + seat
				+ ", ticketAmount=" + ticketAmount + ", totalvalue=" + totalvalue + ", adOrderNumb=" + adOrderNumb
				+ ", isOverDue=" + isOverDue + ", isDeal=" + isDeal + ", roomNumb=" + roomNumb + "]";
	}


}
