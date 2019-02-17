package cinemaModule.entity;

/**
 * 选择将座位表从放映时间表分离的原因：从写入角度来看分不分离问题不大，都是根据主键scheduleNumb写入即可；但从
 * 查询角度来看，分不分离的差别很大——首先，不分离查询难度很大，因为需要根据字段名获取数据，而座位表字段名多大几百个
 * 查询语句会显得很大而且很难编写；其次，不分离查询之后还要封装进TimeInterval对象内，现阶段学习得到的知识无法应付这个
 * 问题，而且当需要调用某个座位的情况时无法直接指定，需要使用算法确定。因此选择将座位表同放映时间表分离，更符合物理模型
 * @author www25
 *
 */
public class TimeInterval {

	private Integer roomNumb;
	private Integer scheduleNumb;
	private Integer movieID;
	private SeatSet1 seatSet1;
	private SeatSet2 seatSet2;
	private Integer startDate;
	private Integer startHour;
	private Integer startMin;
	private Integer endHour;
	private Integer endDate;
	private Integer endMin;
	private Integer beOrder;

	
	public Integer getMovieID() {
		return movieID;
	}

	public void setMovieID(Integer movieID) {
		this.movieID = movieID;
	}

	public SeatSet1 getSeatSet1() {
		return seatSet1;
	}

	public void setSeatSet1(SeatSet1 seatSet1) {
		this.seatSet1 = seatSet1;
	}

	public SeatSet2 getSeatSet2() {
		return seatSet2;
	}

	public void setSeatSet2(SeatSet2 seatSet2) {
		this.seatSet2 = seatSet2;
	}

	public Integer getScheduleNumb() {
		return scheduleNumb;
	}

	public void setScheduleNumb(Integer scheduleNumb) {
		this.scheduleNumb = scheduleNumb;
	}

	public Integer getTicketAmount() {
		return movieID;
	}

	public void setTicketAmount(Integer movieID) {
		this.movieID = movieID;
	}

	public Integer getStartDate() {
		return this.startDate;
	}

	public Integer getStartHour() {
		return this.startHour;
	}

	public Integer getStartMin() {
		return this.startMin;
	}

	public Integer getEndHour() {
		return this.endHour;
	}

	public Integer getEndDate() {
		return this.endDate;
	}

	public Integer getEndMin() {
		return this.endMin;
	}
	
	public Integer getRoomNumb() {
		return this.roomNumb;
	}
	
	public Integer getBeOrder() {
		return this.beOrder;
	}

	public void setBeOrder(Integer beOrder) {
		this.beOrder = beOrder;
	}

	public void setRoomNumb(Integer roomNumb) {
		this.roomNumb = roomNumb;
	}

	public void setStartDate(Integer startDate) {
		this.startDate = startDate;
	}

	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}

	public void setStartMin(Integer startMin) {
		this.startMin = startMin;
	}

	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}

	public void setEndDate(Integer endDate) {
		this.endDate = endDate;
	}

	public void setEndMin(Integer endMin) {
		this.endMin = endMin;
	}
	

	public TimeInterval() {
		super();
	}

	public TimeInterval(Integer roomNumb, Integer scheduleNumb, Integer movieID, SeatSet1 seatSet1, SeatSet2 seatSet2,
			Integer startDate, Integer startHour, Integer startMin, Integer endHour, Integer endDate, Integer endMin,
			Integer beOrder) {
		super();
		this.roomNumb = roomNumb;
		this.scheduleNumb = scheduleNumb;
		this.movieID = movieID;
		this.seatSet1 = seatSet1;
		this.seatSet2 = seatSet2;
		this.startDate = startDate;
		this.startHour = startHour;
		this.startMin = startMin;
		this.endHour = endHour;
		this.endDate = endDate;
		this.endMin = endMin;
		this.beOrder = beOrder;
	}

	@Override
	public String toString() {
		return "TimeInterval [roomNumb=" + roomNumb + ", scheduleNumb=" + scheduleNumb + ", movieID=" + movieID
				+ ", seatSet1=" + seatSet1 + ", seatSet2=" + seatSet2 + ", beOrder=" + beOrder + "{" +
				startDate + ":" + startHour + ":" + startMin + "--" + endDate + ":" + endHour + ":" + endMin + "}]";
	}

	
	
}
