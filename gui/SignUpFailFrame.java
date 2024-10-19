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
	private JLabel fail1Label = new JLabel("빈 칸 또는 잘못된 정보가 있습니다.");
	private JLabel fail2Label = new JLabel("확인 후 다시 입력 부탁드립니다.");
	private JButton closeBtn = new JButton("닫기");
	
	private DoctorSignUpFrame doctorSignUp = null;
	private PatientSignUpFrame patientSignUp = null;
	
	private boolean check = false;
	
	private DBdao dbdao = null;
	public SignUpFailFrame(DBdao db, boolean frame) {
		this.dbdao = db;
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		fail1Label.setFont(new Font(getName(), Font.BOLD, 14));
		fail1Label.setBounds(55, 25, 220, 50);
		mainPanel.add(fail1Label);
		fail2Label.setFont(new Font(getName(), Font.BOLD, 14));
		fail2Label.setBounds(62, 45, 205, 50);
		mainPanel.add(fail2Label);
		
		closeBtn.setBounds(135, 100, 60, 30);
		mainPanel.add(closeBtn);
		
		check = frame;
		closeBtn.addActionListener(this);
		
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
		if (e.getSource() == closeBtn) {
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
