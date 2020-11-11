package beans.purchase;

public class PurchaseDTO {
	private String user_id;
	private int goods_id;
	private String name;
	private String delever;
	private String phone;
	private String buydate;
	private int p_check;
	private int count;
	private int purchase_id;
	
	public PurchaseDTO() {
		super();
	}

	public PurchaseDTO(String user_id, int goods_id, String name, String delever, String phone, String buydate,
			int p_check, int count, int purchase_id) {
		super();
		this.user_id = user_id;
		this.goods_id = goods_id;
		this.name = name;
		this.delever = delever;
		this.phone = phone;
		this.buydate = buydate;
		this.p_check = p_check;
		this.count = count;
		this.purchase_id = purchase_id;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDelever() {
		return delever;
	}

	public void setDelever(String delever) {
		this.delever = delever;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBuydate() {
		return buydate;
	}

	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}

	public int getP_check() {
		return p_check;
	}

	public void setP_check(int p_check) {
		this.p_check = p_check;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}
	
}
