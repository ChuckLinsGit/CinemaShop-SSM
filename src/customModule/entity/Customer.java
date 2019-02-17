package customModule.entity;

public class Customer {
	private int customNumb;
	private String customPhone;
	private String customName;
	private String customMail;
	private String password;
	private String VIPLevel;

	public String getCustomPhone() {
		return customPhone;
	}

	public void setCustomPhone(String customPhone) {
		this.customPhone = customPhone;
	}

	public String getCustomName() {
		return this.customName;
	}
	
	public void  setCustomName(String customName) {
		this.customName=customName;
	}

	public String getCustomMail() {
		return this.customMail;
	}
	
	public void  setCustomMail(String customMail) {
		this.customMail=customMail;
	}

	public int getCustomNumb() {
		return this.customNumb;
	}
	
	public void  setCustomNumb(int customNumb) {
		this.customNumb=customNumb;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void  setPassword(String password) {
		this.password=password;
	}

	public void setVIPLevel(String VIPLevel) {
		this.VIPLevel=VIPLevel;
	}
}
