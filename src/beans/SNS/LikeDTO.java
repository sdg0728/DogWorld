package beans.SNS;

 
public class LikeDTO {
	private int post_id;   
	private String user_id;   	

	// 웹개발시..
	// 가능한, 다음의 3가지 이름을 일치시켜 주는게 좋습니다.
	// DB필드명 = 클래스필드명 = form 의 name 명
	
	// 기본생성자
	public LikeDTO() {
		super();
//		System.out.println("WriteDTO() 객체 생성");
	}
	
	// 매개변수 받는 생성자  //코멘트 
	public LikeDTO(int post_id, String user_id) {
		super();
		this.post_id = post_id;
		this.user_id = user_id;
		
	}

	
	
	public int getPost_id() {
		return post_id;
	}
	
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	
	@Override
	public String toString() {
		return "LikeDTO [post_id=" + post_id + ", user_id=" + user_id + "]";
	}
	

	

	
	
} // end DTO

















