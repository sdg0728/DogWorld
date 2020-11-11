package beans.purchase;

public class CardDTO {
	private String card_num;
	private int cvc;
	private int card_lim_year;
	private int card_lim_month;
	private int password;
	private String user_id;
	private int bank;
	
	public CardDTO() {
		super();
	}

	public CardDTO(String card_num, int cvc, int card_lim_year, int card_lim_month, int password, String user_id, int bank) {
		super();
		this.card_num = card_num;
		this.cvc = cvc;
		this.card_lim_year = card_lim_year;
		this.card_lim_month = card_lim_month;
		this.password = password;
		this.user_id = user_id;
		this.bank = bank;
	}

	public String getCard_num() {
		return card_num;
	}

	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	public int getCard_lim_year() {
		return card_lim_year;
	}

	public void setCard_lim_year(int card_lim_year) {
		this.card_lim_year = card_lim_year;
	}

	public int getCard_lim_month() {
		return card_lim_month;
	}

	public void setCard_lim_month(int card_lim_month) {
		this.card_lim_month = card_lim_month;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getBank() {
		return bank;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}
	
}
