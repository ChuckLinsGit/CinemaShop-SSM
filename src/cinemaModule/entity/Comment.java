package cinemaModule.entity;

public class Comment {
	private Integer commentNumb;
	private Integer orderNumb;
	private String content;
	public Integer getCommentNumb() {
		return commentNumb;
	}
	public void setCommentNumb(Integer commentNumb) {
		this.commentNumb = commentNumb;
	}
	public Integer getOrderNumb() {
		return orderNumb;
	}
	public void setOrderNumb(Integer orderNumb) {
		this.orderNumb = orderNumb;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Comment() {
		super();
	}
	public Comment(Integer commentNumb, Integer orderNumb, String content) {
		super();
		this.commentNumb = commentNumb;
		this.orderNumb = orderNumb;
		this.content = content;
	}
	@Override
	public String toString() {
		return "Comment [commentNumb=" + commentNumb + ", orderNumb=" + orderNumb + ", content=" + content + "]";
	}
	
	
}
