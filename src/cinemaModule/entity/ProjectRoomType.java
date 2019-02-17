package cinemaModule.entity;

public class ProjectRoomType {
	private Integer typeNumb;
	private String typeAlias;
	private String typeInstruction;
	private Integer roomAmount;
	private Integer seatAmount;
	public Integer getTypeNumb() {
		return typeNumb;
	}
	public void setTypeNumb(Integer typeNumb) {
		this.typeNumb = typeNumb;
	}
	public String getTypeAlias() {
		return typeAlias;
	}
	public void setTypeAlias(String typeAlias) {
		this.typeAlias = typeAlias;
	}
	public String getTypeInstruction() {
		return typeInstruction;
	}
	public void setTypeInstruction(String typeInstruction) {
		this.typeInstruction = typeInstruction;
	}
	public Integer getRoomAmount() {
		return roomAmount;
	}
	public void setRoomAmount(Integer roomAmount) {
		this.roomAmount = roomAmount;
	}
	public Integer getSeatAmount() {
		return seatAmount;
	}
	public void setSeatAmount(Integer seatAmount) {
		this.seatAmount = seatAmount;
	}
	public ProjectRoomType() {
		super();
	}
	public ProjectRoomType(Integer typeNumb, String typeAlias, String typeInstruction, Integer roomAmount,
			Integer seatAmount) {
		super();
		this.typeNumb = typeNumb;
		this.typeAlias = typeAlias;
		this.typeInstruction = typeInstruction;
		this.roomAmount = roomAmount;
		this.seatAmount = seatAmount;
	}
	@Override
	public String toString() {
		return "ProjectRoomType [typeNumb=" + typeNumb + ", typeAlias=" + typeAlias + ", typeInstruction="
				+ typeInstruction + ", roomAmount=" + roomAmount + ", seatAmount=" + seatAmount + "]";
	}
	
}
