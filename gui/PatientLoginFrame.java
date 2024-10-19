package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DBdao;
import dto.PatientDTO;

public class PatientLoginFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel();
	private JLabel titleLabel = new JLabel("병원 예약 관리 시스템");
	private JLabel idLabel = new JLabel("아이디");
	private JLabel pwdLabel = new JLabel("비밀번호");
	private JTextField idInput = new JTextField();
	private JTextField pwdInput = new JTextField();
	private JButton loginBtn = new JButton("로그인");
	private JButton signupBtn = new JButton("회원가입");
	private JButton backBtn = new JButton("뒤로가기");
	
	private MainFrame mainF = null;
	private PatientSignUpFrame patientSignUp = null;
	private LoginFailFrame loginFail = null;
	private PatientReservationFrame patientReservation = null;
	
	private DBdao dbdao = null;
	private PatientDTO patientdto = null;
	public PatientLoginFrame(DBdao db) {
		this.dbdao = db;
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		titleLabel.setFont(new Font(getName(), Font.BOLD, 18));
		titleLabel.setBounds(150, 30, 180, 50);
		mainPanel.add(titleLabel);
		
		idLabel.setBounds(130, 72, 40, 50);
		mainPanel.add(idLabel);
		pwdLabel.setBounds(125, 112, 50, 50);
		mainPanel.add(pwdLabel);
		
		idInput.setBounds(190, 85, 170, 25);
		mainPanel.add(idInput);
		pwdInput.setBounds(190, 125, 170, 25);
		mainPanel.add(pwdInput);
		
		loginBtn.setBounds(113, 180, 70, 30);
		mainPanel.add(loginBtn);
		signupBtn.setBounds(193, 180, 85, 30);
		mainPanel.add(signupBtn);
		backBtn.setBounds(288, 180, 85, 30);
		mainPanel.add(backBtn);
		
		loginBtn.addActionListener(this);
		signupBtn.addActionListener(this);
		backBtn.addActionListener(this);
		
		this.add(mainPanel);
		
		this.setTitle("병원 예약 관리 시스템 : 일반회원 로그인");
		this.setSize(500, 300);				// 화면 크기 500x300
		this.setLocationRelativeTo(null);	// 화면 중앙 배치
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 이벤트 발생 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginBtn) {
			// 로그인
			if (LoginCheck()) {
				System.out.println("◆ 로그인한 DTO 정보: " + patientdto);
				// 로그인 성공
				System.out.println("일반회원 로그인 화면 → 예약하기 화면");
				if (patientReservation == null) {
					patientReservation = new PatientReservationFrame(dbdao, patientdto);
				}
				this.setVisible(false);
				patientReservation.setVisible(true);
			} else {
				// 로그인 실패
				System.out.println("일반회원 로그인 화면 → 로그인 실패 팝업");
				if (loginFail == null) {
					loginFail = new LoginFailFrame(dbdao, false);
				}
				this.setVisible(false);
				loginFail.setVisible(true);
			}
		} else if (e.getSource() == signupBtn) {
			System.out.println("일반회원 로그인 화면 → 회원가입 화면");
			if (patientSignUp == null) {
				patientSignUp = new PatientSignUpFrame(dbdao);
			}
			this.setVisible(false);
			patientSignUp.setVisible(true);
		} else if (e.getSource() == backBtn) {
			System.out.println("일반회원 로그인 화면 → 메인 화면");
			if (mainF == null) {
				mainF = new MainFrame(dbdao);
			}
			this.setVisible(false);
			mainF.setVisible(true);
		}
	}
	
	// 로그인 시 DB에 회원 정보가 없으면 true, 있으면 false
	public boolean LoginCheck() {
		patientdto = dbdao.patientLogin(idInput.getText(), pwdInput.getText());
		if (patientdto == null) {
			return false;
		}
		return true;
	}
}
