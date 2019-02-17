package systemModule.entity;

public class Authority {
	private Integer authorityNumb;
	private String authorityDetail;
	public Integer getAuthorityNumb() {
		return authorityNumb;
	}
	public void setAuthorityNumb(Integer authorityNumb) {
		this.authorityNumb = authorityNumb;
	}
	public String getAuthorityDetail() {
		return authorityDetail;
	}
	public void setAuthorityDetail(String authorityDetail) {
		this.authorityDetail = authorityDetail;
	}
	public Authority() {
		super();
	}
	public Authority(Integer authorityNumb, String authorityDetail) {
		super();
		this.authorityNumb = authorityNumb;
		this.authorityDetail = authorityDetail;
	}
	@Override
	public String toString() {
		return "Authority [authorityNumb=" + authorityNumb + ", authorityDetail=" + authorityDetail + "]";
	}
	
}
