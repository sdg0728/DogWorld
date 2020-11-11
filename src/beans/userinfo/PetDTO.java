package beans.userinfo;

public class PetDTO {
	
	private int petId;
	private String petName;
	private String dogBreed;
	private int petAge;
	private String img;
	private String id;
	private boolean isImage;
	
	public PetDTO() {
		super();
	}

	public PetDTO(int petId, String petName, String dogBreed, int petAge, String img, String id) {
		super();
		this.petId = petId;
		this.petName = petName;
		this.dogBreed = dogBreed;
		this.petAge = petAge;
		this.img = img;
		this.id = id;
	}
	
	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}
	
	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getDogBreed() {
		return dogBreed;
	}

	public void setDogBreed(String dogBreed) {
		this.dogBreed = dogBreed;
	}

	public int getPetAge() {
		return petAge;
	}

	public void setPetAge(int petAge) {
		this.petAge = petAge;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public boolean isImage() {
		return isImage;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}
	
	

	@Override
	public String toString() {
		return "PetDTO [petId=" + petId + ", petName=" + petName + ", dogBreed=" + dogBreed + ", petAge=" + petAge
				+ ", img=" + img + ", isImage=" + isImage + "]";
	}
	
}
