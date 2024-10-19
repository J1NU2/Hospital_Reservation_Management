package dto;

public class DoctorDTO {
	private String num = null;		// 의사번호
	private String pwd = null;		// 비밀번호
	private String name = null;		// 이름
	private String major = null;	// 전공
	
	// 의사번호 Getter/Setter
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
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
	// 전공 Getter/Setter
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	// 의사 정보
	@Override
	public String toString() {
		return "DoctorDTO [num=" + num + ", pwd=" + pwd + ", name=" + name + ", major=" + major + "]";
	}
}
