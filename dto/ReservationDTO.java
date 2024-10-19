package dto;

public class ReservationDTO {
	private String date = null;				// 예약날짜
	private String time = null;				// 예약시간
	private String identityNum = null;		// 예약자 주민번호
	private String doctorNum = null;		// 의사번호
	private String medicalCheck = null;		// 진료여부
	private String symptomsMemo = null;		// 증상메모
	private String cancelReason = null;		// 취소사유
	
	// 예약날짜 Getter/Setter
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	// 예약시간 Getter/Setter
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	// 예약자 주민번호 Getter/Setter
	public String getIdentityNum() {
		return identityNum;
	}
	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}
	// 의사번호 Getter/Setter
	public String getDoctorNum() {
		return doctorNum;
	}
	public void setDoctorNum(String doctorNum) {
		this.doctorNum = doctorNum;
	}
	// 진료여부 Getter/Setter
	public String getMedicalCheck() {
		return medicalCheck;
	}
	public void setMedicalCheck(String medicalCheck) {
		this.medicalCheck = medicalCheck;
	}
	// 증상메모 Getter/Setter
	public String getSymptomsMemo() {
		return symptomsMemo;
	}
	public void setSymptomsMemo(String symptomsMemo) {
		this.symptomsMemo = symptomsMemo;
	}
	// 취소사유 Getter/Setter
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	
	// 예약 정보
	@Override
	public String toString() {
		return "ReservationDTO [date=" + date + ", time=" + time + ", identityNum=" + identityNum + ", doctorNum="
				+ doctorNum + ", medicalCheck=" + medicalCheck + ", symptomsMemo=" + symptomsMemo + ", cancelReason="
				+ cancelReason + "]";
	}
}
