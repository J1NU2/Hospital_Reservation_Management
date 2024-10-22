package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dao.DBdao;
import dto.DoctorDTO;
import dto.PatientDTO;
import dto.ReservationDTO;

public class DoctorMedicalListFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel();
	private JPanel reservListPanel = new JPanel();
	
	private JLabel doctorNameLabel = new JLabel("이름");
	private JLabel doctorNumLabel = new JLabel("의사번호");
	private JLabel reservListLabel = new JLabel("환자 예약 내역");
	private JLabel selectRowCheckLabel = new JLabel();
	
	private JTextField doctorNameText = new JTextField();
	private JTextField doctorNumText = new JTextField();
	
	private String[] reservListCol = {"환자명", "주민번호", "나이", "성별", "예약날짜", "예약시간", "진료여부", "증상메모"};
	private String[][] reservListRow = null;
	private DefaultTableModel model = new DefaultTableModel(reservListRow, reservListCol) {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTable reservList = new JTable(model);
	private JScrollPane reservListScrollPane = new JScrollPane(reservList);
	
	private JButton logoutBtn = new JButton("로그아웃");
	private JButton medicalMemoBtn = new JButton("증상메모 변경");
	private JButton cancelBtn = new JButton("예약취소");
	
	private DoctorLoginFrame doctorLogin = null;
	private TextInsertFrame textInsert = null;
	
	private DBdao dbdao = null;
	private DoctorDTO doctordto = null;
	private ReservationDTO reservdto = null;
	private ArrayList<ReservationDTO> rList = null;
	
	private int rowSel = -1;
	
	public DoctorMedicalListFrame(DBdao db, DoctorDTO doctordto) {
		this.dbdao = db;
		this.doctordto = doctordto;
		this.rList = dbdao.reservationDoctorAll(doctordto.getNum());
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		doctorNameLabel.setBounds(70, 10, 25, 50);
		mainPanel.add(doctorNameLabel);
		doctorNumLabel.setBounds(270, 10, 50, 50);
		mainPanel.add(doctorNumLabel);
		
		doctorNameText.setBounds(100, 23, 100, 25);
		doctorNameText.setBackground(Color.WHITE);
		doctorNameText.setForeground(Color.BLACK);
		doctorNameText.setText(doctordto.getName());
		doctorNameText.setEditable(false);
		mainPanel.add(doctorNameText);
		doctorNumText.setBounds(325, 23, 100 ,25);
		doctorNumText.setBackground(Color.WHITE);
		doctorNumText.setForeground(Color.BLACK);
		doctorNumText.setText(doctordto.getNum());
		doctorNumText.setEditable(false);
		mainPanel.add(doctorNumText);
		
		logoutBtn.setBounds(530, 18, 85, 30);
		logoutBtn.addActionListener(this);
		mainPanel.add(logoutBtn);
		
		// 환자 예약 내역
		reservListPanel.setLayout(null);
		reservListPanel.setBounds(60, 70, 545, 640);
		reservListPanel.setBackground(Color.LIGHT_GRAY);
		reservListLabel.setBounds(15, -5, 80, 50);
		reservListPanel.add(reservListLabel);
		reservListPanel.add(selectRowCheckLabel);
		try {
			for (int i=0; i<rList.size(); i++) {
				PatientDTO reservPatient = dbdao.patientOne(rList.get(i).getIdentityNum());
				String[] rowData = new String[8];
				rowData[0] = reservPatient.getName();
				rowData[1] = reservPatient.getIdentityNum();
				rowData[2] = reservPatient.getAge() + "세";
				rowData[3] = reservPatient.getGender();
				rowData[4] = rList.get(i).getDate().substring(2);
				rowData[5] = rList.get(i).getTime();
				rowData[6] = rList.get(i).getMedicalCheck();
				rowData[7] = rList.get(i).getSymptomsMemo();
				model.addRow(rowData);
			}
		} catch (Exception e) {}
		reservList.setRowHeight(30);
		reservList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectRowCheckLabel.setText("");
				rowSel = reservList.getSelectedRow();
				System.out.println(rowSel + "번 행 선택");
				System.out.println(rList.get(rowSel).toString());
			}
		});
		reservList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reservListScrollPane.setBounds(25, 40, 500, 520);
		reservListPanel.add(reservListScrollPane);
		
		medicalMemoBtn.setBounds(320, 585, 110, 30);
		medicalMemoBtn.addActionListener(this);
		reservListPanel.add(medicalMemoBtn);
		cancelBtn.setBounds(440, 585, 85, 30);
		cancelBtn.addActionListener(this);
		reservListPanel.add(cancelBtn);
		
		mainPanel.add(reservListPanel);
		
		this.add(mainPanel);
		
		this.setTitle("병원 예약 관리 시스템 : 예약 내역 확인하기");
		this.setSize(680, 800);				// 화면 크기 680x800
		this.setLocationRelativeTo(null);	// 화면 중앙 배치
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 이벤트 발생 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) {						// 로그아웃 버튼 클릭
			System.out.println("로그아웃");
			System.out.println("예약 목록 화면 → 관계자 로그인 화면");
			if (doctorLogin == null) {
				doctorLogin = new DoctorLoginFrame(dbdao);
			}
			this.setVisible(false);
			doctorLogin.setVisible(true);
		} else if (e.getSource() == medicalMemoBtn) {	// 증상메모 변경 버튼 클릭
			if (selectRowCheck()) {
				System.out.println("예약 목록 화면 → 증상메모 변경 팝업");
				reservdto = rList.get(rowSel);
				if (textInsert == null) {
					textInsert = new TextInsertFrame(dbdao, doctordto, reservdto, 1);
				}
				this.setVisible(false);
				textInsert.setVisible(true);
			} else {
				System.out.println(rowSel + "번 행 선택");
			}
		} else if (e.getSource() == cancelBtn) {				// 예약취소 버튼 클릭
			if (selectRowCheck()) {
				System.out.println("예약 목록 화면 → 취소사유 등록 팝업");
				reservdto = rList.get(rowSel);
				if (textInsert == null) {
					textInsert = new TextInsertFrame(dbdao, doctordto, reservdto, 2);
				}
				this.setVisible(false);
				textInsert.setVisible(true);
			} else {
				System.out.println(rowSel + "번 행 선택");
			}
		}
	}
	
	// 테이블 행 선택 여부, 선택되면 true, 선택되지 않으면 false
	private boolean selectRowCheck() {
		if (rowSel >= 0) {
			return true;
		} else {
			selectRowCheckLabel.setBounds(30, 575, 145, 50);
			selectRowCheckLabel.setForeground(Color.RED);
			selectRowCheckLabel.setText("행이 선택되지 않았습니다.");
			return false;
		}
	}
}
