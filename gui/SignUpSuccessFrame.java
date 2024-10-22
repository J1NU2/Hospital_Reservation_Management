package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.DBdao;

public class SignUpSuccessFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel();
	
	private JLabel successLabel = new JLabel();
	
	private JButton checkBtn = new JButton("확인");
	
	private PatientLoginFrame patientLogin = null;
	private DoctorLoginFrame doctorLogin = null;
	
	private DBdao dbdao = null;
	
	private boolean check = false;
	
	public SignUpSuccessFrame(DBdao db, boolean frame, String text) {
		this.dbdao = db;
		check = frame;
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		successLabel.setFont(new Font(getName(), Font.BOLD, 14));
		successLabel.setBounds(115, 30, 220, 50);
		successLabel.setText(text);
		mainPanel.add(successLabel);
		
		checkBtn.setBounds(135, 90, 60, 30);
		checkBtn.addActionListener(this);
		mainPanel.add(checkBtn);
		
		this.add(mainPanel);
		
		this.setTitle("가입/등록 성공");
		this.setSize(350, 200);				// 화면 크기 350x200
		this.setLocationRelativeTo(null);	// 화면 중앙 배치
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 이벤트 발생 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == checkBtn) {		// 확인 버튼 클릭
			if (check) {
				System.out.println("관계자 등록 성공 팝업 → 관계자 로그인 화면");
				if (doctorLogin == null) {
					doctorLogin = new DoctorLoginFrame(dbdao);
				}
				this.setVisible(false);
				doctorLogin.setVisible(true);
			} else {
				System.out.println("회원가입 성공 팝업 → 일반회원 로그인 화면");
				if (patientLogin == null) {
					patientLogin = new PatientLoginFrame(dbdao);
				}
				this.setVisible(false);
				patientLogin.setVisible(true);
			}
		}
	}
}
