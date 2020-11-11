package beans.purchase;

public class OrdersDTO {
	private int goods_id;
	private int purchase_id;
	private String name;
	private int price;
	private String img;
	private String detail_img;
	private int count;
	private int p_check;
	private String buydate;
	
	public OrdersDTO() {
		super();
	}
	
	public OrdersDTO(int goods_id, int purchase_id, String name, int price, String img, String detail_img,
			int count, String buydate, int p_check) {
		super();
		this.goods_id = goods_id;
		this.purchase_id = purchase_id;
		this.name = name;
		this.price = price;
		this.img = img;
		this.detail_img = detail_img;
		this.count = count;
		this.buydate = buydate;
		this.p_check = p_check;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDetail_img() {
		return detail_img;
	}

	public void setDetail_img(String detail_img) {
		this.detail_img = detail_img;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	@Override
	public String toString() {
		return "OrdersDTO [goods_id=" + goods_id + ", name=" + name + ", price=" + price + ", img=" + img + ", detail_img=" + detail_img + ", count=" + count + ", p_check="
				+ p_check + "]";
	}
	
}
