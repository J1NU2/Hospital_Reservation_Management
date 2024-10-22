package gui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DBdao;
import dto.DoctorDTO;

public class DoctorSignUpFrame extends JFrame implements ActionListener, ItemListener {
	private JPanel mainPanel = new JPanel();
	
	private JLabel titleLabel = new JLabel("관계자 등록");
	private JLabel numLabel = new JLabel("의사번호");
	private JLabel numCheckLabel = new JLabel();
	private JLabel pwdLabel1 = new JLabel("비밀번호");
	private JLabel pwdLabel2 = new JLabel("비밀번호 확인");
	private JLabel pwdCheckLabel = new JLabel();
	private JLabel nameLabel = new JLabel("이름");
	private JLabel majorLabel = new JLabel("전공");
	
	private JTextField numInput = new JTextField();
	private JTextField pwdInput1 = new JTextField();
	private JTextField pwdInput2 = new JTextField();
	private JTextField nameInput = new JTextField();
	
	private Choice majorChoice = new Choice();
	private String[] majorList = {"내과", "일반외과", "정형외과", "소아과", "안과", 
								"이비인후과", "피부과", "비뇨기과", "정신과", "성형외과"};
	
	private JButton numCheckBtn = new JButton("검사");
	private JButton signupBtn = new JButton("등록");
	private JButton backBtn = new JButton("뒤로가기");
	
	private DoctorLoginFrame doctorLogin = null;
	private SignUpSuccessFrame signupSuccess = null;
	private SignUpFailFrame signupFail = null;
	
	private DBdao dbdao = null;
	
	private boolean numCheck = false;
	private boolean pwdCheck = false;
	private String major = null;
	
	public DoctorSignUpFrame(DBdao db) {
		this.dbdao = db;
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		titleLabel.setFont(new Font(getName(), Font.BOLD, 18));
		titleLabel.setBounds(190, 10, 95, 50);
		mainPanel.add(titleLabel);
		
		numLabel.setBounds(125, 60, 50, 50);
		mainPanel.add(numLabel);
		numCheckLabel.setBounds(204, 82, 145, 50);
		mainPanel.add(numCheckLabel);
		pwdLabel1.setBounds(125, 105, 50, 50);
		mainPanel.add(pwdLabel1);
		pwdLabel2.setBounds(110, 150, 75, 50);
		mainPanel.add(pwdLabel2);
		pwdCheckLabel.setBounds(204, 172, 165, 50);
		mainPanel.add(pwdCheckLabel);
		nameLabel.setBounds(138, 195, 25, 50);
		mainPanel.add(nameLabel);
		majorLabel.setBounds(138, 240, 25, 50);
		mainPanel.add(majorLabel);
		
		numInput.setBounds(200, 73, 110, 25);
		numInput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField leng = (JTextField) e.getSource();
				char text = e.getKeyChar();
				if (leng.getText().length() >= 6) e.consume();
				// 아스키코드값
				// 48 : 0 ~ 57 : 9
				// 65 : A ~ 90 : Z
				if (!((text >= 48 && text <= 57) || (text >= 65 && text <= 90))) {
					e.consume();
				}
			}
		});
		mainPanel.add(numInput);
		pwdInput1.setBounds(200, 118, 170, 25);
		pwdInput1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField leng = (JTextField) e.getSource();
				char text = e.getKeyChar();
				if (leng.getText().length() >= 20) e.consume();
				// 아스키코드값
				// 48 : 0 ~ 57 : 9
				// 65 : A ~ 90 : Z
				// 97 : a ~ 122 : z
				if (!((text >= 48 && text <= 57) || (text >= 65 && text <= 90) || 
						(text >= 97 && text <= 122))) {
					e.consume();
				}
			}
			public void keyReleased(KeyEvent e) {
				String pwd1 = pwdInput1.getText();
				String pwd2 = pwdInput2.getText();
				if (pwd1.equals(pwd2) && !(pwd1.isEmpty()) && !(pwd2.isEmpty())) {
					System.out.println("패스워드 일치");
					pwdCheckLabel.setText("비밀번호가 일치합니다.");
					pwdCheckLabel.setForeground(Color.GREEN);
					pwdCheck = true;
				} else if (pwd1.isEmpty() || pwd2.isEmpty()) {
					pwdCheckLabel.setText("비밀번호를 입력해주세요.");
					pwdCheckLabel.setForeground(Color.LIGHT_GRAY);
					pwdCheck = false;
				} else {
					System.out.println("패스워드 불일치");
					pwdCheckLabel.setText("비밀번호가 일치하지 않습니다.");
					pwdCheckLabel.setForeground(Color.RED);
					pwdCheck = false;
				}
			}
		});
		mainPanel.add(pwdInput1);
		pwdInput2.setBounds(200, 163, 170, 25);
		pwdInput2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField leng = (JTextField) e.getSource();
				char text = e.getKeyChar();
				if (leng.getText().length() >= 20) e.consume();
				// 아스키코드값
				// 48 : 0 ~ 57 : 9
				// 65 : A ~ 90 : Z
				// 97 : a ~ 122 : z
				if (!((text >= 48 && text <= 57) || (text >= 65 && text <= 90) || 
						(text >= 97 && text <= 122))) {
					e.consume();
				}
			}
			public void keyReleased(KeyEvent e) {
				String pwd1 = pwdInput1.getText();
				String pwd2 = pwdInput2.getText();
				if (pwd1.equals(pwd2) && !(pwd1.isEmpty()) && !(pwd2.isEmpty())) {
					System.out.println("패스워드 일치");
					pwdCheckLabel.setText("비밀번호가 일치합니다.");
					pwdCheckLabel.setForeground(Color.GREEN);
					pwdCheck = true;
				} else if (pwd1.isEmpty() || pwd2.isEmpty()) {
					pwdCheckLabel.setText("비밀번호를 입력해주세요.");
					pwdCheckLabel.setForeground(Color.LIGHT_GRAY);
					pwdCheck = false;
				} else {
					System.out.println("패스워드 불일치");
					pwdCheckLabel.setText("비밀번호가 일치하지 않습니다.");
					pwdCheckLabel.setForeground(Color.RED);
					pwdCheck = false;
				}
			}
		});
		mainPanel.add(pwdInput2);
		nameInput.setBounds(200, 208, 170, 25);
		nameInput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField leng = (JTextField) e.getSource();
				char text = e.getKeyChar();
				if (leng.getText().length() >= 8) e.consume();
				// 아스키코드값
				// 44032 : 가 ~ 55203 : 힣
				// 65 : A ~ 90 : Z
				// 97 : a ~ 122 : z
				if (!((text >= 65 && text <= 90) || (text >= 97 && text <= 122) || 
						(text >= 44032 && text <= 55203))) {
					e.consume();
				}
			}
		});
		mainPanel.add(nameInput);
		majorChoice.setBounds(200, 253, 170, 25);
		majorChoice.addItem("-------- 선택해주세요. --------");
		for (int i=0; i<majorList.length; i++) {
			majorChoice.addItem(majorList[i]);
		}
		majorChoice.addItemListener(this);
		mainPanel.add(majorChoice);
		
		numCheckBtn.setBounds(320, 70, 65, 30);
		numCheckBtn.addActionListener(this);
		mainPanel.add(numCheckBtn);
		signupBtn.setBounds(155, 300, 60, 30);
		signupBtn.addActionListener(this);
		mainPanel.add(signupBtn);
		backBtn.setBounds(245, 300, 85, 30);
		backBtn.addActionListener(this);
		mainPanel.add(backBtn);
		
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
		if (e.getSource() == signupBtn) {			// 등록 버튼 클릭
			// 관계자 등록
			if (signupBlankCheck()) {
				if (!numCheck && pwdCheck) {
					// 관계자 등록 성공
					System.out.println("관계자 등록 성공");
					System.out.println("관계자 등록 화면 → 관계자 등록 성공 팝업");
					if (signupSuccess == null) {
						signupSuccess = new SignUpSuccessFrame(dbdao, true, "등록되었습니다.");
					}
					
					DoctorDTO doctordto = new DoctorDTO();
					doctordto.setNum(numInput.getText());
					doctordto.setPwd(pwdInput2.getText());
					doctordto.setName(nameInput.getText());
					doctordto.setMajor(major);
					dbdao.doctorSignUp(doctordto);
					
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
		} else if (e.getSource() == backBtn) {		// 뒤로가기 버튼 클릭
			System.out.println("관계자 등록 화면 → 관계자 로그인 화면");
			if (doctorLogin == null) {
				doctorLogin = new DoctorLoginFrame(dbdao);
			}
			this.setVisible(false);
			doctorLogin.setVisible(true);
		} else if (e.getSource() == numCheckBtn) {	// 의사번호 중복 검사 버튼 클릭
			System.out.println("의사번호 중복 검사");
			if (numInput.getText().isEmpty()) {
				numCheck = true;
				numCheckLabel.setText("의사번호를 입력해주세요.");
				numCheckLabel.setForeground(Color.RED);
				return;
			} else {
				numCheck = dbdao.doctorNumCheck(numInput.getText());
			}
			if (numCheck) {
				numCheckLabel.setText("중복된 번호가 존재합니다.");
				numCheckLabel.setForeground(Color.RED);
			} else {
				numCheckLabel.setText("OK");
				numCheckLabel.setForeground(Color.GREEN);
			}
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == majorChoice) {		// 전공 선택 시
			if (!(e.toString().contains("-"))) {
				major = majorChoice.getSelectedItem();
				System.out.println(major);
			} else {
				major = null;
				System.out.println(major);
			}
		}
	}
	
	// 의사 등록 시 누락된 정보가 없으면 true, 있으면 false
	private boolean signupBlankCheck() {
		if (numInput.getText().isEmpty() || pwdInput1.getText().isEmpty() || 
				pwdInput2.getText().isEmpty() || nameInput.getText().isEmpty() || major == null) {
			return false;
		}
		return true;
	}
}
