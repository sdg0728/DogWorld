package beans.purchase;

public class GoodsDTO {
	private int goods_id;
	private String name;
	private int price;
	private String img;
	private String detail_img;
	private int category;
	private int count;
	
	public GoodsDTO() {
		super();
	}
	
	public GoodsDTO(int goods_id, String name, int price, String img, String detail_img, int category,
			int count) {
		super();
		this.goods_id = goods_id;
		this.name = name;
		this.price = price;
		this.img = img;
		this.detail_img = detail_img;
		this.category = category;
		this.count = count;
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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "GoodsDTO [goods_id=" + goods_id + ", name=" + name + ", price=" + price + ", img=" + img + ", detail_img=" + detail_img + ", category=" + category + ", count=" + count + "]";
	}
	
	
}
