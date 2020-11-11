package beans.SNS;

import java.util.ArrayList;

// DTO : Data Transfer Object
public class WriteDTO {
	private int post_id;    // post_id
	private String content;  // content
	private String img;  // img
	private String postdate;   // regdate
	private String user_id;   // user_id

	// 웹개발시..
	// 가능한, 다음의 3가지 이름을 일치시켜 주는게 좋습니다.
	// DB필드명 = 클래스필드명 = form 의 name 명
	
	// 기본생성자
	public WriteDTO() {
		super();
//		System.out.println("WriteDTO() 객체 생성");
	}
	
	// 매개변수 받는 생성자  //코멘트 
	public WriteDTO(int post_id, String user_id, String content, String img) {
		super();
		this.post_id = post_id;
		this.user_id = user_id;
		this.content = content;
		this.img = img;
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
	public String getImg() {
//		System.out.println("getContent() 호출");
		return img;
	}
	public void setImg(String img) {
//		System.out.println("setContent(" + content + ") 호출");
		this.img = img;
	}


	public String getPostdate() {
//		System.out.println("getRegDate() 호출");
		return postdate;
	}
	public void setPostdate(String postdate) {
//		System.out.println("setRegDate(" + regDate + ") 호출");
		this.postdate = postdate;
	}

	@Override
	public String toString() {
		return "WriteDTO [post_id=" + post_id + ", content=" + content + ", img=" + img + ", postdate=" + postdate
				+ ", user_id=" + user_id + "]";
	}
	

	// 개발할때  Class 의   toString() 오버라이딩 해주면
	// 디버깅이나 테스트 하기 좋다.

	
	
	

		
} // end DTO

















