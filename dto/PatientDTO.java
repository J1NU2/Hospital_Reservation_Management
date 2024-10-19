package dto;

public class PatientDTO {
	private String identityNum = null;	// 주민등록번호
	private String id = null;			// 아이디
	private String pwd = null;			// 비밀번호
	private String name = null;			// 이름
	private int age = 0;				// 나이
	private String gender = null;		// 성별
	private String phone = null;		// 휴대전화번호
	private String createdAt = null;	// 가입날짜
	
	// 주민등록번호 Getter/Setter
	public String getIdentityNum() {
		return identityNum;
	}
	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}
	// 아이디 Getter/Setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	// 비밀번호 Getter/Setter
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	// 이름 Getter/Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	// 나이 Getter/Setter
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	// 성별 Getter/Setter
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	// 휴대전화번호 Getter/Setter
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	// 가입날짜 Getter/Setter
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	// 일반회원 정보
	@Override
	public String toString() {
		return "PatientDTO [identityNum=" + identityNum + ", id=" + id + ", pwd=" + pwd 
				+ ", name=" + name + ", age=" + age + ", gender=" + gender + ", phone=" 
				+ phone + ", createdAt=" + createdAt + "]";
	}
}
