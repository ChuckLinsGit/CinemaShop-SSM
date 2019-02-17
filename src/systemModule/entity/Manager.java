package systemModule.entity;

public class Manager {
	private Integer managerNumb;
	private Integer roleNumb;
	private String managerName;
	private String managerIDcard;
	private String managerPhoneNumb;
	private String managerPhotoPath;
	private Integer cinemaNumb;
	public Integer getManagerNumb() {
		return managerNumb;
	}
	public void setManagerNumb(Integer managerNumb) {
		this.managerNumb = managerNumb;
	}
	public Integer getRoleNumb() {
		return roleNumb;
	}
	public void setRoleNumb(Integer roleNumb) {
		this.roleNumb = roleNumb;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerIDcard() {
		return managerIDcard;
	}
	public void setManagerIDcard(String managerIDcard) {
		this.managerIDcard = managerIDcard;
	}
	public String getManagerPhoneNumb() {
		return managerPhoneNumb;
	}
	public void setManagerPhoneNumb(String managerPhoneNumb) {
		this.managerPhoneNumb = managerPhoneNumb;
	}
	public String getManagerPhotoPath() {
		return managerPhotoPath;
	}
	public void setManagerPhotoPath(String managerPhotoPath) {
		this.managerPhotoPath = managerPhotoPath;
	}
	public Integer getCinemaNumb() {
		return cinemaNumb;
	}
	public void setCinemaNumb(Integer cinemaNumb) {
		this.cinemaNumb = cinemaNumb;
	}
	public Manager() {
		super();
	}
	public Manager(Integer managerNumb, Integer roleNumb, String managerName, String managerIDcard,
			String managerPhoneNumb, String managerPhotoPath, Integer cinemaNumb) {
		super();
		this.managerNumb = managerNumb;
		this.roleNumb = roleNumb;
		this.managerName = managerName;
		this.managerIDcard = managerIDcard;
		this.managerPhoneNumb = managerPhoneNumb;
		this.managerPhotoPath = managerPhotoPath;
		this.cinemaNumb = cinemaNumb;
	}
	@Override
	public String toString() {
		return "Manager [managerNumb=" + managerNumb + ", roleNumb=" + roleNumb + ", managerName=" + managerName
				+ ", managerIDcard=" + managerIDcard + ", managerPhoneNumb=" + managerPhoneNumb + ", managerPhotoPath="
				+ managerPhotoPath + ", cinemaNumb=" + cinemaNumb + "]";
	}
	
}
