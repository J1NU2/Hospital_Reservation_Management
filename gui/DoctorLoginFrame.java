package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DBdao;

public class DoctorLoginFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel();
	private JLabel titleLabel = new JLabel("병원 예약 관리 시스템");
	private JLabel numLabel = new JLabel("의사번호");
	private JLabel pwdLabel = new JLabel("비밀번호");
	private JTextField idInput = new JTextField();
	private JTextField pwdInput = new JTextField();
	private JButton loginBtn = new JButton("로그인");
	private JButton signupBtn = new JButton("관계자 등록");
	private JButton backBtn = new JButton("뒤로가기");
	
	private MainFrame mainF = null;
	private DoctorSignUpFrame doctorSignUp = null;
	private LoginFailFrame loginFail = null;
	private DoctorMedicalListFrame doctorMedicalList = null;
	
	private DBdao dbdao = null;
	public DoctorLoginFrame(DBdao db) {
		this.dbdao = db;
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		titleLabel.setFont(new Font(getName(), Font.BOLD, 18));
		titleLabel.setBounds(150, 30, 180, 50);
		mainPanel.add(titleLabel);
		
		numLabel.setBounds(125, 72, 50, 50);
		mainPanel.add(numLabel);
		pwdLabel.setBounds(125, 112, 50, 50);
		mainPanel.add(pwdLabel);
		
		idInput.setBounds(190, 85, 170, 25);
		mainPanel.add(idInput);
		pwdInput.setBounds(190, 125, 170, 25);
		mainPanel.add(pwdInput);
		
		loginBtn.setBounds(108, 180, 70, 30);
		mainPanel.add(loginBtn);
		signupBtn.setBounds(188, 180, 100, 30);
		mainPanel.add(signupBtn);
		backBtn.setBounds(298, 180, 85, 30);
		mainPanel.add(backBtn);
		
		loginBtn.addActionListener(this);
		signupBtn.addActionListener(this);
		backBtn.addActionListener(this);
		
		this.add(mainPanel);
		
		this.setTitle("병원 예약 관리 시스템 : 관계자 로그인");
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
			if (true) {
				// 로그인 성공
				System.out.println("관계자 로그인 화면 → 예약 목록 화면");
				if (doctorMedicalList == null) {
					doctorMedicalList = new DoctorMedicalListFrame(dbdao);
				}
				this.setVisible(false);
				doctorMedicalList.setVisible(true);
			} else {
				// 로그인 실패
				System.out.println("관계자 로그인 화면 → 로그인 실패 팝업");
				if (loginFail == null) {
					loginFail = new LoginFailFrame(dbdao, true);
				}
				this.setVisible(false);
				loginFail.setVisible(true);
			}
		} else if (e.getSource() == signupBtn) {
			System.out.println("관계자 로그인 화면 → 관계자 등록 화면");
			if (doctorSignUp == null) {
				doctorSignUp = new DoctorSignUpFrame(dbdao);
			}
			this.setVisible(false);
			doctorSignUp.setVisible(true);
		} else if (e.getSource() == backBtn) {
			System.out.println("관계자 로그인 화면 → 메인 화면");
			if (mainF == null) {
				mainF = new MainFrame(dbdao);
			}
			this.setVisible(false);
			mainF.setVisible(true);
		}
	}
}
