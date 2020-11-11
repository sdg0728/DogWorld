package beans.purchase;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AjaxGoodsUpdateJSON {
	int count;
	String status;
	
	@JsonIgnore
	String memo;
	
	@JsonProperty("data")
	int cnt;
	
	//기본생성자
	public AjaxGoodsUpdateJSON() {
		super();
	}
	
	//매개변수 생성자
	public AjaxGoodsUpdateJSON(int count, String status, String memo, int cnt) {
		super();
		this.count = count;
		this.status = status;
		this.memo = memo;
		this.cnt = cnt;
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

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}
