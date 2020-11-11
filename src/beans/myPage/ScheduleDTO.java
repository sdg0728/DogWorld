package beans.myPage;

public class ScheduleDTO {

	private int no;
	private String content;
	private String start_date;
	private String end_date;
	private int s_check;
	private String id;
	
	public ScheduleDTO() {
		super();
	}

	public ScheduleDTO(int no, String content, String start_date, String end_date, int s_check, String id) {
		super();
		this.no = no;
		this.content = content;
		this.start_date = start_date;
		this.end_date = end_date;
		this.s_check = s_check;
		this.id = id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public int getS_check() {
		return s_check;
	}

	public void setS_check(int s_check) {
		this.s_check = s_check;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ScheduleDTO [no=" + no + ", content=" + content + ", start_date=" + start_date + ", end_date="
				+ end_date + ", s_check=" + s_check + ", id=" + id + "]";
	}
	
}
