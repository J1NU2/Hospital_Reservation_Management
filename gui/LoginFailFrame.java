package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.DBdao;

public class LoginFailFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel();
	
	private JLabel failLabel = new JLabel("아이디 또는 비밀번호가 틀렸습니다.");
	
	private JButton closeBtn = new JButton("닫기");
	
	private PatientLoginFrame patientLogin = null;
	private DoctorLoginFrame doctorLogin = null;
	
	private DBdao dbdao = null;
	
	private boolean check = false;
	
	public LoginFailFrame(DBdao db, boolean frame) {
		this.dbdao = db;
		check = frame;
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		failLabel.setFont(new Font(getName(), Font.BOLD, 14));
		failLabel.setBounds(55, 25, 230, 50);
		mainPanel.add(failLabel);
		
		closeBtn.setBounds(135, 90, 60, 30);
		closeBtn.addActionListener(this);
		mainPanel.add(closeBtn);
		
		this.add(mainPanel);
		
		this.setTitle("로그인 실패");
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
			System.out.println("팝업창 닫기");		// 닫기 버튼 클릭
			if (check) {
				System.out.println("로그인 실패 팝업 → 관계자 로그인 화면");
				if (doctorLogin == null) {
					doctorLogin = new DoctorLoginFrame(dbdao);
				}
				this.setVisible(false);
				doctorLogin.setVisible(true);
			} else {
				System.out.println("로그인 실패 팝업 → 일반회원 로그인 화면");
				if (patientLogin == null) {
					patientLogin = new PatientLoginFrame(dbdao);
				}
				this.setVisible(false);
				patientLogin.setVisible(true);
			}
		}
	}
}
