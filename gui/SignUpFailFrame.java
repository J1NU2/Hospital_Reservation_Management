package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.DBdao;

public class SignUpFailFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel();
	
	private JLabel failLabel1 = new JLabel();
	private JLabel failLabel2 = new JLabel("확인 후 다시 입력 부탁드립니다.");
	
	private JButton closeBtn = new JButton("닫기");
	
	private PatientSignUpFrame patientSignUp = null;
	private DoctorSignUpFrame doctorSignUp = null;
	
	private DBdao dbdao = null;
	
	private boolean check = false;
	
	public SignUpFailFrame(DBdao db, boolean frame, int text) {
		this.dbdao = db;
		check = frame;
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		if (frame && text == 1) {
			// 관계자(의사) : 의사번호 중복 검사 필요
			failLabel1.setFont(new Font(getName(), Font.BOLD, 14));
			failLabel1.setBounds(59, 25, 220, 50);
			failLabel1.setText("의사번호 중복 확인이 필요합니다.");
		} else if (frame && text == 2) {
			// 관계자(의사) : 일치하지 않는 비밀번호
			failLabel1.setFont(new Font(getName(), Font.BOLD, 14));
			failLabel1.setBounds(67, 25, 220, 50);
			failLabel1.setText("비밀번호가 일치하지 않습니다.");
		} else if (!(frame) && text == 1) {
			// 일반회원(환자) : 아이디 중복 검사 필요
			failLabel1.setFont(new Font(getName(), Font.BOLD, 14));
			failLabel1.setBounds(65, 25, 220, 50);
			failLabel1.setText("아이디 중복 확인이 필요합니다.");
		} else if (!(frame) && text == 2) {
			// 일반회원(환자) : 일치하지 않는 비밀번호
			failLabel1.setFont(new Font(getName(), Font.BOLD, 14));
			failLabel1.setBounds(67, 25, 220, 50);
			failLabel1.setText("비밀번호가 일치하지 않습니다.");
		} else if (!(frame) && text == 3) {
			// 일반회원(환자) : 주민번호 중복 검사 필요
			failLabel1.setFont(new Font(getName(), Font.BOLD, 14));
			failLabel1.setBounds(59, 25, 220, 50);
			failLabel1.setText("주민번호 중복 확인이 필요합니다.");
		} else {
			// 빈 칸 또는 잘못된 정보 입력
			failLabel1.setFont(new Font(getName(), Font.BOLD, 14));
			failLabel1.setBounds(57, 25, 220, 50);
			failLabel1.setText("빈 칸 또는 잘못된 정보가 있습니다.");
		}
		mainPanel.add(failLabel1);
		failLabel2.setFont(new Font(getName(), Font.BOLD, 14));
		failLabel2.setBounds(64, 45, 205, 50);
		mainPanel.add(failLabel2);
		
		closeBtn.setBounds(137, 100, 60, 30);
		closeBtn.addActionListener(this);
		mainPanel.add(closeBtn);
		
		this.add(mainPanel);
		
		this.setTitle("등록 실패");
		this.setSize(350, 200);				// 화면 크기 350x200
		this.setLocationRelativeTo(null);	// 화면 중앙 배치
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 이벤트 발생 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == closeBtn) {		// 닫기 버튼 클릭
			if (check) {
				System.out.println("관계자 등록 실패 팝업 → 관계자 등록 화면");
				if (doctorSignUp == null) {
					doctorSignUp = new DoctorSignUpFrame(dbdao);
				}
				this.setVisible(false);
				doctorSignUp.setVisible(true);
			} else {
				System.out.println("회원가입 실패 팝업 → 회원가입 화면");
				if (patientSignUp == null) {
					patientSignUp = new PatientSignUpFrame(dbdao);
				}
				this.setVisible(false);
				patientSignUp.setVisible(true);
			}
		}
	}
}
