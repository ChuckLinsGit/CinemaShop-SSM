package cinemaModule.entity;

public class Order {

	private Integer orderNumb;
	private Integer customNUmb;
	private Integer scheduleNumb;
	private Integer isOverdue;
	private Integer isDealed;
	private String seat;
	private Integer ticketAmount;
	private Float totalvalue;
	public Integer getOrderNumb() {
		return orderNumb;
	}
	public void setOrderNumb(Integer orderNumb) {
		this.orderNumb = orderNumb;
	}
	public Integer getCustomNUmb() {
		return customNUmb;
	}
	public void setCustomNUmb(Integer customNUmb) {
		this.customNUmb = customNUmb;
	}
	public Integer getScheduleNumb() {
		return scheduleNumb;
	}
	public void setScheduleNumb(Integer scheduleNumb) {
		this.scheduleNumb = scheduleNumb;
	}
	public Integer getIsOverdue() {
		return isOverdue;
	}
	public void setIsOverdue(Integer isOverdue) {
		this.isOverdue = isOverdue;
	}
	public Integer getIsDealed() {
		return isDealed;
	}
	public void setIsDealed(Integer isDealed) {
		this.isDealed = isDealed;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public Integer getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(Integer ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	public Float getTotalvalue() {
		return totalvalue;
	}
	public void setTotalvalue(Float totalvalue) {
		this.totalvalue = totalvalue;
	}
	public Order() {
		super();
	}
	public Order(Integer orderNumb, Integer customNUmb, Integer scheduleNumb, Integer isOverdue, Integer isDealed,
			String seat, Integer ticketAmount, Float totalvalue) {
		super();
		this.orderNumb = orderNumb;
		this.customNUmb = customNUmb;
		this.scheduleNumb = scheduleNumb;
		this.isOverdue = isOverdue;
		this.isDealed = isDealed;
		this.seat = seat;
		this.ticketAmount = ticketAmount;
		this.totalvalue = totalvalue;
	}
	@Override
	public String toString() {
		return "Order [orderNumb=" + orderNumb + ", customNUmb=" + customNUmb + ", scheduleNumb=" + scheduleNumb
				+ ", isOverdue=" + isOverdue + ", isDealed=" + isDealed + ", seat=" + seat + ", ticketAmount="
				+ ticketAmount + ", totalvalue=" + totalvalue + "]";
	}


}
