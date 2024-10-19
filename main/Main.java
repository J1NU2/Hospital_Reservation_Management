package main;

import dao.DBdao;
import dao.HospitalDAO;
import dto.PatientDTO;
import gui.PatientReservationFrame;

public class Main {

	public static void main(String[] args) {
		DBdao dao = new HospitalDAO();
//		MainFrame mf = new MainFrame(dao);
		
		PatientDTO pDTO = new PatientDTO();
		
		// 일반회원
//		new PatientSignUpFrame(dao);
//		new PatientLoginFrame(dao);
		new PatientReservationFrame(dao, pDTO);
		
		// 관계자(의사)
//		new DoctorCheckFrame();
//		new DoctorCheckFailFrame();
//		new DoctorSignUpFrame();
//		new DoctorLoginFrame();
//		new DoctorMedicalListFrame();
//		new SymptomsFrame();
//		new CancelFrame();
		
		// 회원가입 & 로그인 관련 프레임
//		new SignUpSuccessFrame(false, "등록되었습니다.");
//		new SignUpFailFrame(false);
//		new LoginFailFrame(false);
	}

}
