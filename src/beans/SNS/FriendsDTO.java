package beans.SNS;

 
public class FriendsDTO {
	private String follower;   	
	private String following;   

	// 웹개발시..
	// 가능한, 다음의 3가지 이름을 일치시켜 주는게 좋습니다.
	// DB필드명 = 클래스필드명 = form 의 name 명
	
	// 기본생성자
	public FriendsDTO() {
		super();
//		System.out.println("WriteDTO() 객체 생성");
	}
	
	// 매개변수 받는 생성자  //코멘트 
	public FriendsDTO(String follower, String following) {
		super();
		this.follower = follower;
		this.following = following;
		
	}

	// getter & setter
	public String getFollower() {
		return follower;
	}
	public void setFollower(String follower) {
		this.follower = follower;
	}
	

	public String getFollowing() {
		return following;
	}
	public void setFollowing(String following) {
		this.following = following;
	}
	


	// 개발할때  Class 의   toString() 오버라이딩 해주면
	// 디버깅이나 테스트 하기 좋다.
	@Override
	public String toString() {
		return String.format("FriendDTO]  %s : %s :", 
				follower, following);
	}
	

		
} // end DTO

















