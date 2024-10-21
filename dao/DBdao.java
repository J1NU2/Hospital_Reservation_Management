package dao;

import java.util.ArrayList;

import dto.DoctorDTO;
import dto.PatientDTO;
import dto.ReservationDTO;

public interface DBdao {
	// 추상메서드
	// 일반회원
	public void patientSignUp(PatientDTO patientdto);				// 일반회원 회원가입
	public PatientDTO patientLogin(String findId, String findPwd);	// 일반회원 로그인
	public boolean patientIdCheck(String findId);					// 회원가입 시 아이디 중복 체크
	public boolean patientIdenCheck(String findId);					// 회원가입 시 주민번호 중복 체크
	
	// 관계자(의사)
	public void doctorSignUp(DoctorDTO doctordto);					// 의사 등록
	public DoctorDTO doctorLogin(String findNum, String findPwd);	// 의사 로그인
	public boolean doctorNumCheck(String findNum);					// 회원가입 시 아이디 중복 체크
	
	// 예약관련
	public ArrayList<DoctorDTO> doctorAll();							// 예약 시 의사 목록
	public DoctorDTO doctorOne(String findNum);							// 예약 시 의사를 선택하면 해당 의사의 정보 리턴
	public void reservationAdd(ReservationDTO reservdto);				// 예약하기
	public ReservationDTO reservationCurrent(String findIden);			// 일반회원이 현재 예약한 내역
	public ReservationDTO reservationCurrentDoctor(String findDate, String findTime, String findDoc);	// 선택한 의사에게 예약한 날짜/시간이 겹치는 내역
	public void reservationDel(String findIden);						// 현재 예약 취소
	public ArrayList<ReservationDTO> reservationPatientAll(String findIden);	// 로그인한 환자가 예약한 모든 내역
	public ArrayList<ReservationDTO> reservationDoctorAll(String findDoc);		// 로그인한 의사에게 예약한 모든 내역
}
