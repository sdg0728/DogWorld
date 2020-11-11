package beans.myPage;

public class DiaryDTO {

	private int no;
	private String title;
	private String regdate;
	private String content;
	private String img;
	private String id;
	private boolean isImage;
	
	public DiaryDTO() {
		super();
	}

	public DiaryDTO(int no, String title, String regdate, String content, String img, String id) {
		super();
		this.no = no;
		this.title = title;
		this.regdate = regdate;
		this.content = content;
		this.img = img;
		this.id = id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isImage() {
		return isImage;
	}

	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}

	@Override
	public String toString() {
		return "DiaryDAO [no=" + no + ", title=" + title + ", regdate=" + regdate + ", content=" + content + ", img="
				+ img + ", id=" + id + ", isImage=" + isImage + "]";
	}
}
