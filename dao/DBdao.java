package dao;

import java.util.ArrayList;

import dto.DoctorDTO;
import dto.PatientDTO;
import dto.ReservationDTO;

public interface DBdao {
	// 추상메서드
	
	// 일반회원(환자)
	public void patientSignUp(PatientDTO patientdto);				// 일반회원(환자) 회원가입
	public PatientDTO patientLogin(String findId, String findPwd);	// 일반회원(환자) 로그인
	public boolean patientIdCheck(String findId);					// 회원가입 시 아이디 중복 체크
	public boolean patientIdenCheck(String findIden);				// 회원가입 시 주민번호 중복 체크
	
	// 관계자(의사)
	public void doctorSignUp(DoctorDTO doctordto);					// 관계자(의사) 등록
	public DoctorDTO doctorLogin(String findNum, String findPwd);	// 관계자(의사) 로그인
	public boolean doctorNumCheck(String findNum);					// 관계자 등록 시 의사번호 중복 체크
	
	// 예약
	public ArrayList<DoctorDTO> doctorAll();						// 예약 시 의사 목록 조회
	public PatientDTO patientOne(String findIden);					// 일반회원 한명의 정보 조회
	public DoctorDTO doctorOne(String findNum);						// 의사 한명의 정보 조회
	public void reservationAdd(ReservationDTO reservdto);			// 예약하기
	public ReservationDTO reservationCurrent(String findIden);		// 일반회원(환자)이 현재 예약한 내역 조회
	// 선택한 의사에게 예약하려는 날짜/시간이 겹치는지 조회
	public ReservationDTO reservationCurrentDoctor(String findDate, String findTime, String findNum);
	public void reservationDel(String findIden);					// 현재 예약 내역 취소
	public void reservationMod(ReservationDTO reservdto, String modMemo);		// 진료여부, 증상메모 변경
	public void reservationCancel(ReservationDTO reservdto, String modReason);	// 취소사유 변경
	public ArrayList<ReservationDTO> reservationPatientAll(String findIden);	// 로그인한 일반회원(환자)이 예약한 모든 내역 조회
	public ArrayList<ReservationDTO> reservationDoctorAll(String findNum);		// 로그인한 관계자(의사)에게 예약한 모든 내역 조회
}
