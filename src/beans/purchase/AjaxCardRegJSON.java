package beans.purchase;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AjaxCardRegJSON {
	int count;
	String status;
	
	@JsonIgnore
	String memo;
	
	@JsonProperty("data")
	List<Integer> list;
	
	//기본생성자
	public AjaxCardRegJSON() {
		super();
	}
	
	//매개변수 생성자
	public AjaxCardRegJSON(int count, String status, String memo, List<Integer> list) {
		super();
		this.count = count;
		this.status = status;
		this.memo = memo;
		this.list = list;
	}
	
	
	//getter / setter
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}
}
