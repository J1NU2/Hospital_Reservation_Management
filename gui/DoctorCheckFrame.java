package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DBdao;

public class DoctorCheckFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel();
	
	private JLabel checkLabel = new JLabel("인증번호");
	
	private JTextField checkInput = new JTextField();
	
	private JButton checkBtn = new JButton("확인");
	private JButton closeBtn = new JButton("닫기");
	
	private MainFrame mainF = null;
	private DoctorLoginFrame doctorLogin = null;
	private DoctorCheckFailFrame doctorCheckFail = null;
	
	private DBdao dbdao = null;
	
	private String checkNum = "486";			// 관계자 인증 번호
	
	public DoctorCheckFrame(DBdao db) {
		this.dbdao = db;
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		checkLabel.setBounds(45, 25, 50, 50);
		mainPanel.add(checkLabel);
		
		checkInput.setBounds(120, 38, 170, 25);
		mainPanel.add(checkInput);
		
		checkBtn.setBounds(90, 90, 65, 30);
		checkBtn.addActionListener(this);
		mainPanel.add(checkBtn);
		closeBtn.setBounds(180, 90, 65, 30);
		closeBtn.addActionListener(this);
		mainPanel.add(closeBtn);
		
		this.add(mainPanel);
		
		this.setTitle("관계자 인증");
		this.setSize(350, 200);				// 화면 크기 350x200
		this.setLocationRelativeTo(null);	// 화면 중앙 배치
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 이벤트 발생 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == checkBtn) {			// 확인 버튼 클릭
			System.out.println("인증번호 확인 ↓");
			if (checkInput.getText().equals(checkNum)) {
				System.out.println("인증번호 맞음");
				System.out.println("인증 성공 → 관계자 로그인 화면");
				if (doctorLogin == null) {
					doctorLogin = new DoctorLoginFrame(dbdao);
				}
				this.setVisible(false);
				doctorLogin.setVisible(true);
			} else {
				System.out.println("인증번호 틀림");
				if (doctorCheckFail == null) {
					doctorCheckFail = new DoctorCheckFailFrame(dbdao);
				}
				this.setVisible(false);
				doctorCheckFail.setVisible(true);
			}
		} else if (e.getSource() == closeBtn) {		// 닫기 버튼 클릭
			System.out.println("팝업창 닫기 → 메인 화면");
			if (mainF == null) {
				mainF = new MainFrame(dbdao);
			}
			this.setVisible(false);
			mainF.setVisible(true);
		}
	}
}
