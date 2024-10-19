package gui;

import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DBdao;

public class DoctorSignUpFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel();
	private JLabel titleLabel = new JLabel("관계자 등록");
	private JLabel numLabel = new JLabel("의사번호");
	private JLabel pwd1Label = new JLabel("비밀번호");
	private JLabel pwd2Label = new JLabel("비밀번호 확인");
	private JLabel nameLabel = new JLabel("이름");
	private JLabel majorLabel = new JLabel("전공");
	private JTextField numInput = new JTextField();
	private JTextField pwd1Input = new JTextField();
	private JTextField pwd2Input = new JTextField();
	private JTextField nameInput = new JTextField();
	private Choice majorChoice = new Choice();
	private String[] majorList = {"내과", "일반외과", "정형외과", "소아과", "안과", 
								"이비인후과", "피부과", "비뇨기과", "정신과", "성형외과"};
	private JButton signupBtn = new JButton("등록");
	private JButton backBtn = new JButton("뒤로가기");
	
	private SignUpFailFrame signupFail = null;
	private SignUpSuccessFrame signupSuccess = null;
	private DoctorLoginFrame doctorLogin = null;
	
	private DBdao dbdao = null;
	public DoctorSignUpFrame(DBdao db) {
		this.dbdao = db;
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		titleLabel.setFont(new Font(getName(), Font.BOLD, 18));
		titleLabel.setBounds(190, 10, 95, 50);
		mainPanel.add(titleLabel);
		
		numLabel.setBounds(125, 60, 50, 50);
		mainPanel.add(numLabel);
		pwd1Label.setBounds(125, 100, 50, 50);
		mainPanel.add(pwd1Label);
		pwd2Label.setBounds(110, 140, 75, 50);
		mainPanel.add(pwd2Label);
		nameLabel.setBounds(138, 180, 25, 50);
		mainPanel.add(nameLabel);
		majorLabel.setBounds(138, 220, 25, 50);
		mainPanel.add(majorLabel);
		
		numInput.setBounds(200, 73, 170, 25);
		mainPanel.add(numInput);
		pwd1Input.setBounds(200, 113, 170, 25);
		mainPanel.add(pwd1Input);
		pwd2Input.setBounds(200, 153, 170, 25);
		mainPanel.add(pwd2Input);
		nameInput.setBounds(200, 193, 170, 25);
		mainPanel.add(nameInput);
		majorChoice.setBounds(200, 233, 170, 25);
		for (int i=0; i<majorList.length; i++) {
			majorChoice.addItem(majorList[i]);
		}
		mainPanel.add(majorChoice);
		
		signupBtn.setBounds(155, 290, 60, 30);
		mainPanel.add(signupBtn);
		backBtn.setBounds(245, 290, 85, 30);
		mainPanel.add(backBtn);
		
		signupBtn.addActionListener(this);
		backBtn.addActionListener(this);
		
		this.add(mainPanel);
		
		this.setTitle("병원 예약 관리 시스템 : 관계자 등록");
		this.setSize(500, 400);				// 화면 크기 500x400
		this.setLocationRelativeTo(null);	// 화면 중앙 배치
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 이벤트 발생 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == signupBtn) {
			// 관계자 등록
			if (true) {
				// 관계자 등록 성공
				/* 테스트 */
				System.out.println("관계자 등록 성공");
				System.out.println("관계자 등록 화면 → 관계자 등록 성공 팝업");
				if (signupSuccess == null) {
					signupSuccess = new SignUpSuccessFrame(dbdao, true, "등록되었습니다.");
				}
				this.setVisible(false);
				signupSuccess.setVisible(true);
			} else {
				// 관계자 등록 실패
				System.out.println("관계자 등록 실패");
				System.out.println("관계자 등록 화면 → 관계자 등록 실패 팝업");
				if (signupFail == null) {
					signupFail = new SignUpFailFrame(dbdao, true);
				}
				this.setVisible(false);
				signupFail.setVisible(true);
			}
		} else if (e.getSource() == backBtn) {
			System.out.println("관계자 등록 화면 → 관계자 로그인 화면");
			if (doctorLogin == null) {
				doctorLogin = new DoctorLoginFrame(dbdao);
			}
			this.setVisible(false);
			doctorLogin.setVisible(true);
		}
	}
}
