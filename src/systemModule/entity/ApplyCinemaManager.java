package systemModule.entity;

public class ApplyCinemaManager {
	private Integer managerApplyNumb;
	private String managerName;
	private String managerIDcard;
	private String managerPhoneNumb;
	private String managerPhotoPath;
	private Integer cinemaApplyNumb;
	public Integer getManagerApplyNumb() {
		return managerApplyNumb;
	}
	public void setManagerApplyNumb(Integer managerApplyNumb) {
		this.managerApplyNumb = managerApplyNumb;
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
	public Integer getCinemaApplyNumb() {
		return cinemaApplyNumb;
	}
	public void setCinemaApplyNumb(Integer cinemaApplyNumb) {
		this.cinemaApplyNumb = cinemaApplyNumb;
	}
	public ApplyCinemaManager() {
		super();
	}
	public ApplyCinemaManager(Integer managerApplyNumb, String managerName, String managerIDcard,
			String managerPhoneNumb, String managerPhotoPath, Integer cinemaApplyNumb) {
		super();
		this.managerApplyNumb = managerApplyNumb;
		this.managerName = managerName;
		this.managerIDcard = managerIDcard;
		this.managerPhoneNumb = managerPhoneNumb;
		this.managerPhotoPath = managerPhotoPath;
		this.cinemaApplyNumb = cinemaApplyNumb;
	}
	@Override
	public String toString() {
		return "ApplyCinemaManager [managerApplyNumb=" + managerApplyNumb + ", managerName=" + managerName
				+ ", managerIDcard=" + managerIDcard + ", managerPhoneNumb=" + managerPhoneNumb + ", managerPhotoPath="
				+ managerPhotoPath + ", cinemaApplyNumb=" + cinemaApplyNumb + "]";
	}
	
}
