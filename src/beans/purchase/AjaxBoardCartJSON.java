package beans.purchase;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AjaxBoardCartJSON {
	int count;
	String status;
	
	@JsonIgnore
	String memo;
	
	@JsonProperty("data")
	List<GoodsDTO> list;
	
	@JsonProperty("cart")
	List<PurchaseDTO> cart;
	
	//기본생성자
	public AjaxBoardCartJSON() {
		super();
	}
	
	//매개변수 생성자
	public AjaxBoardCartJSON(int count, String status, String memo, List<GoodsDTO> list, List<PurchaseDTO> cart) {
		super();
		this.count = count;
		this.status = status;
		this.memo = memo;
		this.list = list;
		this.cart = cart;
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
	
	public List<GoodsDTO> getList() {
		return list;
	}

	public void setList(List<GoodsDTO> list) {
		this.list = list;
	}
	
	public List<PurchaseDTO> getCart() {
		return cart;
	}

	public void setCart(List<PurchaseDTO> cart) {
		this.cart = cart;
	}
	
}
