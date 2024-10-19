package gui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.DBdao;
import dto.DoctorDTO;
import dto.PatientDTO;
import dto.ReservationDTO;

public class PatientReservationFrame extends JFrame implements ActionListener, ItemListener {
	private JPanel mainPanel = new JPanel();
	private JPanel reservPanel = new JPanel();
	private JPanel reservListPanel = new JPanel();
	private JPanel reservListAllPanel = new JPanel();
	
	private JLabel nameLabel = new JLabel("이름");
	private JLabel idenLabel = new JLabel("주민등록번호");
	private JLabel reservLabel = new JLabel("예약하기");
	private JLabel doctorNameLabel = new JLabel("의사이름");
	private JLabel doctorNumLabel = new JLabel("의사번호");
	private JLabel doctorMajorLabel = new JLabel("전공");
	private JLabel reservDateLabel = new JLabel("예약날짜");
	private JLabel reservTimeLabel = new JLabel("예약시간");
	private JLabel reservCheckLabel = new JLabel();
	private JLabel reservListLabel = new JLabel("현재 예약 내역");
	private JLabel reservListAllLabel = new JLabel("전체 예약 내역");
	
	private JTextField nameText = new JTextField();
	private JTextField idenText = new JTextField();
	private JTextField doctorNumText = new JTextField();
	private JTextField doctorMajorText = new JTextField();
	
	private Choice doctorNameChoice = new Choice();
	private Choice yearChoice = new Choice();
	private Choice monthChoice = new Choice();
	private Choice dayChoice = new Choice();
	private Choice hourChoice = new Choice();
	private Choice minuteChoice = new Choice();
	
	private String[] reservListCol = {"환자명", "주민등록번호", "의사이름", "의사번호", "예약날짜", "예약시간"};
//	private String[][] reservListRow = new String[1][6];
	// 테스트값
	private String[][] reservListRow = {{"환자명", "주민등록번호", "의사이름", "의사번호", "예약날짜", "예약시간"}};
	private DefaultTableModel modelOne = new DefaultTableModel(reservListRow, reservListCol) {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTable reservListOne = new JTable(modelOne);
	private JTableHeader reservListColName = reservListOne.getTableHeader();
	private String[] reservListAllCol = {"환자명", "주민번호", "의사이름", "의사번호", 
										"예약날짜", "예약시간", "증상메모","취소사유"};
	private String[][] reservListAllRow = null;
	// 테스트값
//	private String[][] reservListAllRow = {{"환자명", "주민번호", "의사이름", "의사번호", 
//		"예약날짜", "예약시간", "증상메모","취소사유"},{"환자명", "주민번호", "의사이름", "의사번호", 
//			"예약날짜", "예약시간", "증상메모","취소사유"},{"환자명", "주민번호", "의사이름", "의사번호", 
//				"예약날짜", "예약시간", "증상메모","취소사유"},{"환자명", "주민번호", "의사이름", "의사번호", 
//					"예약날짜", "예약시간", "증상메모","취소사유"},{"환자명", "주민번호", "의사이름", "의사번호", 
//						"예약날짜", "예약시간", "증상메모","취소사유"},{"환자명", "주민번호", "의사이름", "의사번호", 
//							"예약날짜", "예약시간", "증상메모","취소사유"},{"환자명", "주민번호", "의사이름", "의사번호", 
//								"예약날짜", "예약시간", "증상메모","취소사유"},{"환자명", "주민번호", "의사이름", "의사번호", 
//									"예약날짜", "예약시간", "증상메모","취소사유"},{"환자명", "주민번호", "의사이름", "의사번호", 
//										"예약날짜", "예약시간", "증상메모","취소사유"},{"환자명", "주민번호", "의사이름", "의사번호", 
//											"예약날짜", "예약시간", "증상메모","취소사유"},{"환자명", "주민번호", "의사이름", "의사번호", 
//												"예약날짜", "예약시간", "증상메모","취소사유"},{"환자명", "주민번호", "의사이름", "의사번호", 
//													"예약날짜", "예약시간", "증상메모","취소사유"}};
	private DefaultTableModel modelAll = new DefaultTableModel(reservListAllRow, reservListAllCol) {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTable reservListAll = new JTable(modelAll);
	private JScrollPane reservListAllScrollPane = new JScrollPane(reservListAll);
	
	private JButton logoutBtn = new JButton("로그아웃");
	private JButton reservBtn = new JButton("예약하기");
	private JButton cancleBtn = new JButton("취소하기");
	
	private PatientLoginFrame patientLogin = null;
	
	private DBdao dbdao = null;
	private PatientDTO patientdto = null;
	private DoctorDTO doctordto = null;
	private ArrayList<DoctorDTO> dList = null;
	
	private Calendar current = Calendar.getInstance();
	
	private String doctorName = null;
	private String year = null;
	private String month = null;
	private String day = null;
	private String hour = null;
	private String minute = null;
	
	public PatientReservationFrame(DBdao db, PatientDTO patientdto) {
		this.dbdao = db;
		this.patientdto = patientdto;
		this.dList = dbdao.doctorAll();
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		nameLabel.setBounds(70, 10, 25, 50);
		mainPanel.add(nameLabel);
		idenLabel.setBounds(270, 10, 75, 50);
		mainPanel.add(idenLabel);
		
		nameText.setBounds(100, 23, 100, 25);
		nameText.setBackground(Color.WHITE);
		nameText.setForeground(Color.BLACK);
		nameText.setText(patientdto.getName());
		nameText.setEditable(false);
		mainPanel.add(nameText);
		idenText.setBounds(348, 23, 120 ,25);
		idenText.setBackground(Color.WHITE);
		idenText.setForeground(Color.BLACK);
		idenText.setText(patientdto.getIdentityNum());
		idenText.setEditable(false);
		mainPanel.add(idenText);
		
		logoutBtn.setBounds(530, 18, 85, 30);
		mainPanel.add(logoutBtn);
		
		// 예약하기
		reservPanel.setLayout(null);
		reservPanel.setBounds(60, 65, 545, 200);
		reservPanel.setBackground(Color.LIGHT_GRAY);
		reservLabel.setBounds(15, -5, 50, 50);
		reservPanel.add(reservLabel);
		
		doctorNameLabel.setBounds(25, 35, 50, 50);
		reservPanel.add(doctorNameLabel);
		doctorNameChoice.setBounds(85, 48, 100, 25);
		doctorNameChoice.addItem("--------------------");
		for (int i=0; i<dList.size(); i++) {
			doctorNameChoice.addItem(dList.get(i).getName() + "(" + dList.get(i).getNum() + ")");
		}
		doctorNameChoice.addItemListener(this);
		reservPanel.add(doctorNameChoice);
		
		doctorNumLabel.setBounds(25, 75, 50, 50);
		reservPanel.add(doctorNumLabel);
		doctorNumText.setBounds(85, 88, 100, 25);
		doctorNumText.setBackground(Color.WHITE);
		doctorNumText.setForeground(Color.BLACK);
		doctorNumText.setEditable(false);
		reservPanel.add(doctorNumText);
		doctorMajorLabel.setBounds(25, 115, 25, 50);
		reservPanel.add(doctorMajorLabel);
		doctorMajorText.setBounds(85, 128, 100, 25);
		doctorMajorText.setBackground(Color.WHITE);
		doctorMajorText.setForeground(Color.BLACK);
		doctorMajorText.setEditable(false);
		reservPanel.add(doctorMajorText);
		
		reservDateLabel.setBounds(200, 55, 50, 50);
		reservPanel.add(reservDateLabel);
		yearChoice.setBounds(260, 68, 80, 25);
		yearChoice.addItem("---- 년 ----");
		for (int i=0; i<=3; i++) {
			yearChoice.addItem((current.get(Calendar.YEAR) + i) + "년");
		}
		yearChoice.addItemListener(this);
		reservPanel.add(yearChoice);
		monthChoice.setBounds(350, 68, 80, 25);
		monthChoice.addItem("---- 월 ----");
		for (int i=1; i<=12; i++) {
			if (i < 10) {
				monthChoice.addItem("0" + i + "월");
			} else {
				monthChoice.addItem(i + "월");
			}
		}
		monthChoice.addItemListener(this);
		reservPanel.add(monthChoice);
		dayChoice.setBounds(440, 68, 80, 25);
		dayChoice.addItem("---- 일 ----");
		dayChoice.addItemListener(this);
		reservPanel.add(dayChoice);
		
		// 오전 9시 ~ 오후 5시 (09시~17시)
		reservTimeLabel.setBounds(200, 95, 50, 50);
		reservPanel.add(reservTimeLabel);
		hourChoice.setBounds(260, 108, 80, 25);
		hourChoice.addItem("---- 시 ----");
		for (int i=9; i<=17; i++) {
			if (i < 10) {
				hourChoice.addItem("0" + i + "시");
			} else {
				hourChoice.addItem(i + "시");
			}
		}
		hourChoice.addItemListener(this);
		reservPanel.add(hourChoice);
		minuteChoice.setBounds(350, 108, 80, 25);
		minuteChoice.addItem("---- 분 ----");
		for (int i=0; i<60; i+=30) {
			if (i < 10) {
				minuteChoice.addItem("0" + i + "분");
			} else {
				minuteChoice.addItem(i + "분");
			}
		}
		minuteChoice.addItemListener(this);
		reservPanel.add(minuteChoice);
		
		reservPanel.add(reservCheckLabel);
		
		reservBtn.setBounds(440, 150, 85, 30);
		reservPanel.add(reservBtn);
		
		mainPanel.add(reservPanel);
		
		// 현재 예약 내역
		reservListPanel.setLayout(null);
		reservListPanel.setBounds(60, 285, 545, 200);
		reservListPanel.setBackground(Color.LIGHT_GRAY);
		reservListLabel.setBounds(15, -5, 80, 50);
		reservListPanel.add(reservListLabel);
		
		reservListColName.setBounds(25, 50, 500, 30);
		reservListPanel.add(reservListColName);
		reservListOne.setBounds(25, 80, 500, 50);
		reservListOne.setRowHeight(50);
		reservListPanel.add(reservListOne);
		
		cancleBtn.setBounds(440, 150, 85, 30);
		reservListPanel.add(cancleBtn);
		
		mainPanel.add(reservListPanel);
		
		// 전체 예약 내역
		reservListAllPanel.setLayout(null);
		reservListAllPanel.setBounds(60, 505, 545, 230);
		reservListAllPanel.setBackground(Color.LIGHT_GRAY);
		reservListAllLabel.setBounds(15, -5, 80, 50);
		reservListAllPanel.add(reservListAllLabel);
		
		reservListAllScrollPane.setBounds(25, 40, 500, 170);
		reservListAllPanel.add(reservListAllScrollPane);
		
		mainPanel.add(reservListAllPanel);
		
		logoutBtn.addActionListener(this);
		reservBtn.addActionListener(this);
		cancleBtn.addActionListener(this);
		
		this.add(mainPanel);
		
		this.setTitle("병원 예약 관리 시스템 : 관계자 등록");
		this.setSize(680, 800);				// 화면 크기 680x800
		this.setLocationRelativeTo(null);	// 화면 중앙 배치
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 이벤트 발생 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) {
			System.out.println("로그아웃");
			System.out.println("예약하기 화면 → 일반회원 로그인 화면");
			if (patientLogin == null) {
				patientLogin = new PatientLoginFrame(dbdao);
			}
			this.setVisible(false);
			patientLogin.setVisible(true);
		} else if (e.getSource() == reservBtn) {
			System.out.println("예약하기 성공");
			// 코드 작성
			if (reservationBlankCheck()) {
				ReservationDTO reservdto = new ReservationDTO();
				reservdto.setIdentityNum(patientdto.getIdentityNum());
				reservdto.setDoctorNum(doctorNumText.getText());
				String reservDate = year + "." + month + "." + day;
				reservdto.setDate(reservDate);
				String reservTime = hour + ":" + minute;
				reservdto.setTime(reservTime);
				dbdao.reservationAdd(reservdto);
				
				reservCheckLabel.setText("");
				
				this.setVisible(false);
				new PatientReservationFrame(dbdao, patientdto);
			} else {
				System.out.println("예약하기 실패");
				reservCheckLabel.setBounds(270, 140, 155, 50);
				reservCheckLabel.setText("빈칸이 있는지 확인해주세요.");
				reservCheckLabel.setForeground(Color.RED);
			}
		} else if (e.getSource() == cancleBtn) {
			System.out.println("현재 예약 취소");
			// 코드 작성
			super.repaint();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == doctorNameChoice) {
			if (!(e.toString().contains("-"))) {
				int doctorNameIndex = doctorNameChoice.getSelectedItem().indexOf("(");
				int doctorNumIndex = doctorNameChoice.getSelectedItem().indexOf(")");
				
				doctorName = doctorNameChoice.getSelectedItem().substring(0, doctorNameIndex);
				String doctorNum = doctorNameChoice.getSelectedItem().substring((doctorNameIndex + 1), doctorNumIndex);
				
				doctordto = dbdao.doctorOne(doctorNum);
				
				doctorNumText.setText(doctordto.getNum());
				doctorMajorText.setText(doctordto.getMajor());
			} else {
				doctorName = null;
				doctorNumText.setText("");
				doctorMajorText.setText("");
			}
		} else if (e.getSource() == yearChoice) {
			if (!(e.toString().contains("-"))) {
				int yearIndex = yearChoice.getSelectedItem().indexOf("년");
				year = yearChoice.getSelectedItem().substring(0, yearIndex);
				
				int yearSelect = Integer.parseInt(year);
				current.set(Calendar.YEAR, yearSelect);
				
				monthChoice.select(0);
				dayChoice.select(0);
			} else {
				year = null;
			}
		} else if (e.getSource() == monthChoice) {
			if (!(e.toString().contains("-"))) {
				int monthIndex = monthChoice.getSelectedItem().indexOf("월");
				month = monthChoice.getSelectedItem().substring(0, monthIndex);
				
				int monthSelect = Integer.parseInt(month);
				current.set(Calendar.MONTH, (monthSelect - 1));
				
				dayChoice.select(0);
				
				for (int i=1; i<=current.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
					if (i < 10) {
						dayChoice.addItem("0" + i + "일");
					} else {
						dayChoice.addItem(i + "일");
					}
				}
			} else {
				month = null;
			}
		} else if (e.getSource() == dayChoice) {
			if (!(e.toString().contains("-"))) {
				int dayIndex = dayChoice.getSelectedItem().indexOf("일");
				day = dayChoice.getSelectedItem().substring(0, dayIndex);
			} else {
				day = null;
			}
		} else if (e.getSource() == hourChoice) {
			if (!(e.toString().contains("-"))) {
				int hourIndex = hourChoice.getSelectedItem().indexOf("시");
				hour = hourChoice.getSelectedItem().substring(0, hourIndex);
			} else {
				hour = null;
			}
		} else if (e.getSource() == minuteChoice) {
			if (!(e.toString().contains("-"))) {
				int minuteIndex = minuteChoice.getSelectedItem().indexOf("분");
				minute = minuteChoice.getSelectedItem().substring(0, minuteIndex);
			} else {
				minute = null;
			}
		}
	}
	// 예약 시 누락된 정보가 없으면 true, 하나라도 있으면 false
	public boolean reservationBlankCheck() {
		if (doctorName == null || doctorNumText.getText().isEmpty() || doctorMajorText.getText().isEmpty() || 
				year == null || month == null || day == null || hour == null || minute == null) {
			return false;
		}
		return true;
	}
}
