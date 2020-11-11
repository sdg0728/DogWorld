package beans.myPage;

public class DietDTO {
	
	private int weight;
	private String regdate;
	private int petId;
	
	public DietDTO() {
		super();
	}

	public DietDTO(int weight, int petId) {
		super();
		this.weight = weight;
		this.petId = petId;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	@Override
	public String toString() {
		return "DietDTO [weight=" + weight + ", regdate=" + regdate + ", petId=" + petId + "]";
	}
		
}
