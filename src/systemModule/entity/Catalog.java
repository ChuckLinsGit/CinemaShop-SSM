package systemModule.entity;

public class Catalog {
	private Integer catalogNumb;
	private String catalogName;
	private String catalogDetail;
	private Integer movieAmount;
	public Integer getCatalogNumb() {
		return catalogNumb;
	}
	public void setCatalogNumb(Integer catalogNumb) {
		this.catalogNumb = catalogNumb;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getCatalogDetail() {
		return catalogDetail;
	}
	public void setCatalogDetail(String catalogDetail) {
		this.catalogDetail = catalogDetail;
	}
	public Integer getMovieAmount() {
		return movieAmount;
	}
	public void setMovieAmount(Integer movieAmount) {
		this.movieAmount = movieAmount;
	}
	public Catalog(Integer catalogNumb, String catalogName, String catalogDetail, Integer movieAmount) {
		super();
		this.catalogNumb = catalogNumb;
		this.catalogName = catalogName;
		this.catalogDetail = catalogDetail;
		this.movieAmount = movieAmount;
	}
	public Catalog() {
		super();
	}
	@Override
	public String toString() {
		return "Catalog [catalogNumb=" + catalogNumb + ", catalogName=" + catalogName + ", catalogDetail="
				+ catalogDetail + ", movieAmount=" + movieAmount + "]";
	}
	
}
