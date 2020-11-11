package beans.purchase;

public class ReviewDTO {
	private String user_id;
	private int goods_id;
	private String content;
	private String image;
	private String regdate;
	private int review_id;
	
	//기본 생성자
	public ReviewDTO() {
		super();
	}
	
	//매개변수 생성자
	public ReviewDTO(String user_id, int goods_id, String content, String image, String regdate, int review_id) {
		super();
		this.user_id = user_id;
		this.goods_id = goods_id;
		this.content = content;
		this.image = image;
		this.regdate = regdate;
		this.review_id = review_id;
	}
	
	//getter / setter
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	@Override
	public String toString() {
		return "ReviewDTO [user_id=" + user_id + ", goods_id=" + goods_id + ", content=" + content + ", image=" + image
				+ ", regdate=" + regdate + ", review_id=" + review_id + "]";
	}

}
