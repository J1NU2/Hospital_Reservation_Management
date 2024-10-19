package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.DBdao;

public class MainFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel();
	private JLabel titleLabel = new JLabel("병원 예약 관리 시스템");
	private JButton patientLoginBtn = new JButton("일반회원 로그인");
	private JButton doctorLoginBtn = new JButton("관계자 로그인");
	
	private PatientLoginFrame patientLogin = null;
	private DoctorCheckFrame doctorCheck = null;
	
	DBdao dbdao = null;
	public MainFrame(DBdao dao) {
		this.dbdao = dao;
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		titleLabel.setFont(new Font(getName(), Font.BOLD, 18));
		titleLabel.setBounds(150, 30, 180, 50);
		mainPanel.add(titleLabel);
		
		patientLoginBtn.setBounds(110, 120, 125, 40);
		mainPanel.add(patientLoginBtn);
		
		doctorLoginBtn.setBounds(255, 120, 125, 40);
		mainPanel.add(doctorLoginBtn);
		
		patientLoginBtn.addActionListener(this);
		doctorLoginBtn.addActionListener(this);
		
		this.add(mainPanel);
		
		this.setTitle("병원 예약 관리 시스템 : 메인");
		this.setSize(500, 300);				// 화면 크기 500x300
		this.setLocationRelativeTo(null);	// 화면 중앙 배치
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 이벤트 발생 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == patientLoginBtn) {			// 일반회원 로그인 버튼 클릭
			System.out.println("메인 화면 → 일반회원 로그인 화면");
			if (patientLogin == null) {
				patientLogin = new PatientLoginFrame(dbdao);
			}
			this.setVisible(false);
			patientLogin.setVisible(true);
		} else if (e.getSource() == doctorLoginBtn) {	// 관계자 로그인 버튼 클릭
			System.out.println("메인 화면 → 관계자 인증 팝업");
			if (doctorCheck == null) {
				doctorCheck = new DoctorCheckFrame(dbdao);
			}
			this.setVisible(false);
			doctorCheck.setVisible(true);
		}
	}
}
