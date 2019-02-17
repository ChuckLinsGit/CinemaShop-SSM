package cinemaModule.entity;

public class ProjectRoom {
	private Integer roomNumb;
	private String roomName;
	private Integer typeNumb;
	private String instruction;
	public Integer getRoomNumb() {
		return roomNumb;
	}
	public void setRoomNumb(Integer roomNumb) {
		this.roomNumb = roomNumb;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Integer getTypeNumb() {
		return typeNumb;
	}
	public void setTypeNumb(Integer typeNumb) {
		this.typeNumb = typeNumb;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public ProjectRoom() {
		super();
	}
	public ProjectRoom(Integer roomNumb, String roomName, Integer typeNumb, String instruction) {
		super();
		this.roomNumb = roomNumb;
		this.roomName = roomName;
		this.typeNumb = typeNumb;
		this.instruction = instruction;
	}
	@Override
	public String toString() {
		return "ProjectRoom [roomNumb=" + roomNumb + ", roomName=" + roomName + ", typeNumb=" + typeNumb
				+ ", instruction=" + instruction + "]";
	}
	
}
