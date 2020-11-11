package beans.SNS;

// DTO : Data Transfer Object
public class CommentDTO {
	private int post_id;    // post_id
	private int comment_id;    // post_id
	private String content;  // content
	private String regdate;   // regdate
	private String user_id;   // user_id

	// 기본생성자
	public CommentDTO() {
		super();
//		System.out.println("WriteDTO() 객체 생성");
	}
	
	// 매개변수 받는 생성자  //코멘트 
	public CommentDTO(int post_id, String user_id, String content, String regdate, int comment_id) {
		super();
		this.post_id = post_id;
		this.content = content;
		this.regdate = regdate;
		this.user_id = user_id;
		this.comment_id = comment_id;
//		System.out.printf("WriteDTO(%d, %s, %s, %s, %d) 객체 생성", 
//				uid, subject, content, name, viewCnt);
	}

	// getter & setter
	public int getPost_id() {
//		System.out.println("getUid() 호출");
		return post_id;
	}
	public void setPost_id(int post_id) {
//		System.out.println("setUid(" + uid + ") 호출");
		this.post_id = post_id;
	}
	
	public int getComment_id() {
//		System.out.println("getUid() 호출");
		return comment_id;
	}
	public void setComment_id(int comment_id) {
//		System.out.println("setUid(" + uid + ") 호출");
		this.comment_id = comment_id;
	}
	
	
	public String getUser_id() {
//		System.out.println("getSubject() 호출");
		return user_id;
	}
	public void setUser_id(String user_id) {
//		System.out.println("setSubject(" + subject + ") 호출");
		this.user_id = user_id;
	}
	
	public String getContent() {
//		System.out.println("getSubject() 호출");
		return content;
	}
	public void setContent(String content) {
//		System.out.println("setSubject(" + subject + ") 호출");
		this.content = content;
	}


	public String getRegdate() {
//		System.out.println("getRegDate() 호출");
		return regdate;
	}
	public void setRegdate(String regdate) {
//		System.out.println("setRegDate(" + regDate + ") 호출");
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "CommentDTO [post_id=" + post_id + ", comment_id=" + comment_id + ", content=" + content + ", regdate="
				+ regdate + ", user_id=" + user_id + "]";
	}
	

	// 개발할때  Class 의   toString() 오버라이딩 해주면
	// 디버깅이나 테스트 하기 좋다.
	
	

		
} // end DTO

















