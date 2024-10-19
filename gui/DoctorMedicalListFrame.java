package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.DBdao;

public class DoctorMedicalListFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel();
	private JPanel reservListPanel = new JPanel();
	
	private JLabel doctorNameLabel = new JLabel("이름");
	private JLabel doctorNumLabel = new JLabel("의사번호");
	private JLabel reservListLabel = new JLabel("환자 예약 내역");
	
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
	private JButton medicalCheckChangeBtn = new JButton("진료여부 변경");
	private JButton symptomsMemoChangeBtn = new JButton("증상메모 변경");
	private JButton cancleBtn = new JButton("예약취소");
	
	private DoctorLoginFrame doctorLogin = null;
	private TextInsertFrame textInsert = null;
	
	private DBdao dbdao = null;
	public DoctorMedicalListFrame(DBdao db) {
		this.dbdao = db;
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		doctorNameLabel.setBounds(70, 10, 25, 50);
		mainPanel.add(doctorNameLabel);
		doctorNumLabel.setBounds(270, 10, 50, 50);
		mainPanel.add(doctorNumLabel);
		
		doctorNameText.setBounds(100, 23, 100, 25);
		doctorNameText.setBackground(Color.WHITE);
		doctorNameText.setForeground(Color.BLACK);
		doctorNameText.setEditable(false);
		mainPanel.add(doctorNameText);
		doctorNumText.setBounds(348, 23, 100 ,25);
		doctorNumText.setBackground(Color.WHITE);
		doctorNumText.setForeground(Color.BLACK);
		doctorNumText.setEditable(false);
		mainPanel.add(doctorNumText);
		
		logoutBtn.setBounds(530, 18, 85, 30);
		mainPanel.add(logoutBtn);
		
		// 환자 예약 내역
		reservListPanel.setLayout(null);
		reservListPanel.setBounds(60, 70, 545, 640);
		reservListPanel.setBackground(Color.LIGHT_GRAY);
		reservListLabel.setBounds(15, -5, 80, 50);
		reservListPanel.add(reservListLabel);
		
		reservList.setRowHeight(30);
		reservListScrollPane.setBounds(25, 40, 500, 520);
		reservListPanel.add(reservListScrollPane);
		
		medicalCheckChangeBtn.setBounds(200, 585, 110, 30);
		reservListPanel.add(medicalCheckChangeBtn);
		symptomsMemoChangeBtn.setBounds(320, 585, 110, 30);
		reservListPanel.add(symptomsMemoChangeBtn);
		cancleBtn.setBounds(440, 585, 85, 30);
		reservListPanel.add(cancleBtn);
		
		mainPanel.add(reservListPanel);
		
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
			System.out.println("예약 목록 화면 → 관계자 로그인 화면");
			if (doctorLogin == null) {
				doctorLogin = new DoctorLoginFrame(dbdao);
			}
			this.setVisible(false);
			doctorLogin.setVisible(true);
		} else if (e.getSource() == medicalCheckChangeBtn) {
			System.out.println("진료여부 변경");
			// 코드 작성
			
		} else if (e.getSource() == symptomsMemoChangeBtn) {
			System.out.println("예약 목록 화면 → 증상메모 변경 팝업");
			if (textInsert == null) {
				textInsert = new TextInsertFrame(dbdao, 1, "증상메모 입력");
			}
			this.setVisible(false);
			textInsert.setVisible(true);
		} else if (e.getSource() == cancleBtn) {
			System.out.println("예약 목록 화면 → 취소사유 등록 팝업");
			if (textInsert == null) {
				textInsert = new TextInsertFrame(dbdao, 2, "취소사유 입력");
			}
			this.setVisible(false);
			textInsert.setVisible(true);
		}
	}
}
