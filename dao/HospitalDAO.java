package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.print.Doc;

import dto.DoctorDTO;
import dto.PatientDTO;
import dto.ReservationDTO;

public class HospitalDAO implements DBdao {
	// DB 정보
	private String dbDriver = "oracle.jdbc.driver.OracleDriver";
	private String dbName = "system";
	private String dbPwd = "11111111";
	private String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private Connection conn = null;					// 커넥션 자원 변수
	public static HospitalDAO hospitalDAO = null;	// 싱글톤 작업을 위한 자기 자신의 객체 주소 변수
	
	// 생성자
	public HospitalDAO() {
		init();
	}
	// 싱글톤 작업
	public static HospitalDAO getInstance() {
		if (hospitalDAO == null) {
			hospitalDAO = new HospitalDAO();
		}
		return hospitalDAO;
	}
	// 드라이버 로드
	private void init() {
		try {
			Class.forName(dbDriver);
			System.out.println("★ DB 드라이버 로드 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("☆ DB 드라이버 로드 실패");
		}
	}
	// 커넥션 로드
	private boolean conn() {
		try {
			conn = DriverManager.getConnection(dbURL, dbName, dbPwd);
			System.out.println("★ 커넥션 자원 로드 성공");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("☆ 커넥션 자원 로드 실패");
		}
		return false;
	}
	// 일반회원 등록
	@Override
	public void patientSignUp(PatientDTO patientdto) {
		if (conn()) {
			System.out.println("★ 데이터베이스 연결 성공");
			try {
				// 주민등록번호,아이디,비밀번호,이름,나이,성별,휴대전화번호,가입날짜
				String sql = "INSERT INTO patient VALUES(?,?,?,?,?,?,?,"
						+ "TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'))";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, patientdto.getIdentityNum());
				pstmt.setString(2, patientdto.getId());
				pstmt.setString(3, patientdto.getPwd());
				pstmt.setString(4, patientdto.getName());
				pstmt.setInt(5, patientdto.getAge());
				pstmt.setString(6, patientdto.getGender());
				pstmt.setString(7, patientdto.getPhone());
				
				int resultInt = pstmt.executeUpdate();
				
				if (resultInt > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("☆ 데이터베이스 작업 에러 발생");
			} finally {
				if (conn != null) {
					try {
						conn.close();
						System.out.println("★ 커넥션 자원 반납 성공");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("☆ 커넥션 자원 반납 실패");
					}
				}
			}
		} else {
			System.out.println("★ 데이터베이스 연결 실패");
		}
	}
	// 일반회원 로그인 시 DB에 있는지 조회
	@Override
	public PatientDTO patientLogin(String findId, String findPwd) {
		if (conn()) {
			System.out.println("★ 데이터베이스 연결 성공");
			try {
				String sql = "SELECT * FROM patient WHERE patient_id=? AND patient_pwd=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, findId);
				pstmt.setString(2, findPwd);
				
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					PatientDTO temp = new PatientDTO();
					temp.setIdentityNum(rs.getString("identity_num"));
					temp.setId(rs.getString("patient_id"));
					temp.setPwd(rs.getString("patient_pwd"));
					temp.setName(rs.getString("patient_name"));
					temp.setAge(rs.getInt("patient_age"));
					temp.setGender(rs.getString("patient_gender"));
					temp.setPhone(rs.getString("patient_phone"));
					temp.setCreatedAt(rs.getString("created_at"));
					return temp;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("☆ 데이터베이스 작업 에러 발생");
			} finally {
				if (conn != null) {
					try {
						conn.close();
						System.out.println("★ 커넥션 자원 반납 성공");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("☆ 커넥션 자원 반납 실패");
					}
				}
			}
		} else {
			System.out.println("★ 데이터베이스 연결 실패");
		}
		return null;
	}
	// 회원가입 시 ID가 DB에 있는지 조회
	@Override
	public boolean patientIdCheck(String findId) {
		if (conn()) {
			System.out.println("★ 데이터베이스 연결 성공");
			try {
				String sql = "SELECT * FROM patient WHERE patient_id=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, findId);
				
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("☆ 데이터베이스 작업 에러 발생");
			} finally {
				if (conn != null) {
					try {
						conn.close();
						System.out.println("★ 커넥션 자원 반납 성공");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("☆ 커넥션 자원 반납 실패");
					}
				}
			}
		} else {
			System.out.println("★ 데이터베이스 연결 실패");
		}
		return false;
	}
	// 회원가입 시 주민번호가 DB에 있는지 조회
	@Override
	public boolean patientIdenCheck(String findIden) {
		if (conn()) {
			System.out.println("★ 데이터베이스 연결 성공");
			try {
				String sql = "SELECT * FROM patient WHERE identity_num=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, findIden);
				
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("☆ 데이터베이스 작업 에러 발생");
			} finally {
				if (conn != null) {
					try {
						conn.close();
						System.out.println("★ 커넥션 자원 반납 성공");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("☆ 커넥션 자원 반납 실패");
					}
				}
			}
		} else {
			System.out.println("★ 데이터베이스 연결 실패");
		}
		return false;
	}
	
	// 관계자 등록
	@Override
	public void doctorSignUp(DoctorDTO doctordto) {
		if (conn()) {
			try {
				System.out.println("★ 데이터베이스 연결 성공");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("☆ 데이터베이스 작업 에러 발생");
			} finally {
				if (conn != null) {
					try {
						conn.close();
						System.out.println("★ 커넥션 자원 반납 성공");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("☆ 커넥션 자원 반납 실패");
					}
				}
			}
		} else {
			System.out.println("★ 데이터베이스 연결 실패");
		}
	}
	
	// 예약 시 의사 DB 정보 전체 조회
	@Override
	public ArrayList<DoctorDTO> doctorAll() {
		ArrayList<DoctorDTO> dList = new ArrayList<DoctorDTO>();
		if (conn()) {
			try {
				System.out.println("★ 데이터베이스 연결 성공");
				String sql = "SELECT * FROM doctor";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					DoctorDTO temp = new DoctorDTO();
					temp.setNum(rs.getString("doctor_num"));
					temp.setPwd(rs.getString("doctor_pwd"));
					temp.setName(rs.getString("doctor_name"));
					temp.setMajor(rs.getString("doctor_major"));
					dList.add(temp);
				}
				return dList;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("☆ 데이터베이스 작업 에러 발생");
			} finally {
				if (conn != null) {
					try {
						conn.close();
						System.out.println("★ 커넥션 자원 반납 성공");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("☆ 커넥션 자원 반납 실패");
					}
				}
			}
		} else {
			System.out.println("★ 데이터베이스 연결 실패");
		}
		return dList;
	}
	// 예약 시 의사를 선택하면 해당 의사의 정보가 DB에 있는지 조회
	@Override
	public DoctorDTO doctorOne(String findNum) {
		if (conn()) {
			try {
				System.out.println("★ 데이터베이스 연결 성공");
				String sql = "SELECT * FROM doctor WHERE doctor_num=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, findNum);
				
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					DoctorDTO temp = new DoctorDTO();
					temp.setNum(rs.getString("doctor_num"));
					temp.setPwd(rs.getString("doctor_pwd"));
					temp.setName(rs.getString("doctor_name"));
					temp.setMajor(rs.getString("doctor_major"));
					return temp;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("☆ 데이터베이스 작업 에러 발생");
			} finally {
				if (conn != null) {
					try {
						conn.close();
						System.out.println("★ 커넥션 자원 반납 성공");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("☆ 커넥션 자원 반납 실패");
					}
				}
			}
		} else {
			System.out.println("★ 데이터베이스 연결 실패");
		}
		return null;
	}
	// 예약하기
	@Override
	public void reservationAdd(ReservationDTO reservdto) {
		if (conn()) {
			System.out.println("★ 데이터베이스 연결 성공");
			try {
				// 예약날짜,예약시간,예약자주민번호,의사번호,진료여부,증상메모,취소사유
				String sql = "INSERT INTO reservation VALUES (?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, reservdto.getDate());
				pstmt.setString(2, reservdto.getTime());
				pstmt.setString(3, reservdto.getIdentityNum());
				pstmt.setString(4, reservdto.getDoctorNum());
				
				int resultInt = pstmt.executeUpdate();
				
				if (resultInt > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("☆ 데이터베이스 작업 에러 발생");
			} finally {
				if (conn != null) {
					try {
						conn.close();
						System.out.println("★ 커넥션 자원 반납 성공");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("☆ 커넥션 자원 반납 실패");
					}
				}
			}
		} else {
			System.out.println("★ 데이터베이스 연결 실패");
		}
	}
	// 일반회원이 현재 예약한 내역 조회
	@Override
	public ReservationDTO selectOne(PatientDTO patientdto) {
		if (conn()) {
			try {
				System.out.println("★ 데이터베이스 연결 성공");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("☆ 데이터베이스 작업 에러 발생");
			} finally {
				if (conn != null) {
					try {
						conn.close();
						System.out.println("★ 커넥션 자원 반납 성공");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("☆ 커넥션 자원 반납 실패");
					}
				}
			}
		} else {
			System.out.println("★ 데이터베이스 연결 실패");
		}
		return null;
	}
	// 예약 내역 전체 조회
	@Override
	public ArrayList<ReservationDTO> selectAll() {
		if (conn()) {
			try {
				System.out.println("★ 데이터베이스 연결 성공");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("☆ 데이터베이스 작업 에러 발생");
			} finally {
				if (conn != null) {
					try {
						conn.close();
						System.out.println("★ 커넥션 자원 반납 성공");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("☆ 커넥션 자원 반납 실패");
					}
				}
			}
		} else {
			System.out.println("★ 데이터베이스 연결 실패");
		}
		return null;
	}


}