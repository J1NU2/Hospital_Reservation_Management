package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.DBdao;
import dto.PatientDTO;

public class PatientSignUpFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel();
	
	private JLabel titleLabel = new JLabel("회원가입");
	private JLabel idLabel = new JLabel("아이디");
	private JLabel idCheckLabel = new JLabel();
	private JLabel pwdLabel1 = new JLabel("비밀번호");
	private JLabel pwdLabel2 = new JLabel("비밀번호 확인");
	private JLabel pwdCheckLabel = new JLabel();
	private JLabel nameLabel = new JLabel("이름");
	private JLabel idenLabel = new JLabel("주민등록번호");
	private JLabel idenCheckLabel = new JLabel();
	private JLabel ageLabel = new JLabel("나이");
	private JLabel genderLabel = new JLabel("성별");
	private JLabel phoneLabel = new JLabel("휴대전화번호");
	
	private JTextField idInput = new JTextField();
	private JPasswordField pwdInput1 = new JPasswordField();
	private JPasswordField pwdInput2 = new JPasswordField();
	private JTextField nameInput = new JTextField();
	private JTextField idenInput1 = new JTextField();
	private JTextField idenInput2 = new JTextField();
	private JTextField ageText = new JTextField();
	private JTextField genderText = new JTextField();
	private JTextField phoneInput1 = new JTextField();
	private JTextField phoneInput2 = new JTextField();
	private JTextField phoneInput3 = new JTextField();
	
	private JButton idCheckBtn = new JButton("검사");
	private JButton idenCheckBtn = new JButton("검사");
	private JButton signupBtn = new JButton("가입");
	private JButton backBtn = new JButton("뒤로가기");
	
	private PatientLoginFrame patientLogin = null;
	private SignUpSuccessFrame signupSuccess = null;
	private SignUpFailFrame signupFail = null;
	
	private DBdao dbdao = null;
	
	private boolean idCheck = false;
	private boolean pwdCheck = false;
	private boolean idenCheck = false;
	private String identity = null;
	
	public PatientSignUpFrame(DBdao db) {
		this.dbdao = db;
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		titleLabel.setFont(new Font(getName(), Font.BOLD, 18));
		titleLabel.setBounds(200, 10, 75, 50);
		mainPanel.add(titleLabel);
		idLabel.setBounds(130, 60, 40, 50);
		mainPanel.add(idLabel);
		idCheckLabel.setBounds(204, 82, 145, 50);
		mainPanel.add(idCheckLabel);
		pwdLabel1.setBounds(125, 105, 50, 50);
		mainPanel.add(pwdLabel1);
		pwdLabel2.setBounds(110, 150, 75, 50);
		mainPanel.add(pwdLabel2);
		pwdCheckLabel.setBounds(204, 172, 165, 50);
		mainPanel.add(pwdCheckLabel);
		nameLabel.setBounds(138, 195, 25, 50);
		mainPanel.add(nameLabel);
		idenLabel.setBounds(112, 240, 75, 50);
		mainPanel.add(idenLabel);
		idenCheckLabel.setBounds(204, 262, 155, 50);
		mainPanel.add(idenCheckLabel);
		ageLabel.setBounds(150, 285, 25, 50);
		mainPanel.add(ageLabel);
		genderLabel.setBounds(250, 285, 25, 50);
		mainPanel.add(genderLabel);
		phoneLabel.setBounds(112, 330, 75, 50);
		mainPanel.add(phoneLabel);
		
		idInput.setBounds(200, 73, 110, 25);
		idInput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField leng = (JTextField) e.getSource();
				char text = e.getKeyChar();
				if (leng.getText().length() >= 8) e.consume();
				// 아스키코드값
				// 48 : 0 ~ 57 : 9
				// 65 : A ~ 90 : Z
				// 97 : a ~ 122 : z
				if (!((text >= 48 && text <= 57) || (text >= 65 && text <= 90) || 
						(text >= 97 && text <= 122))) {
					e.consume();
				}
			}
		});
		mainPanel.add(idInput);
		pwdInput1.setBounds(200, 118, 170, 25);
		pwdInput1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JPasswordField leng = (JPasswordField) e.getSource();
				char text = e.getKeyChar();
				String password = new String(leng.getPassword());
				if (password.length() >= 20) e.consume();
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
				String pwd1 = new String(pwdInput1.getPassword());
				String pwd2 = new String(pwdInput2.getPassword());
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
				JPasswordField leng = (JPasswordField) e.getSource();
				char text = e.getKeyChar();
				String password = new String(leng.getPassword());
				if (password.length() >= 20) e.consume();
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
				String pwd1 = new String(pwdInput1.getPassword());
				String pwd2 = new String(pwdInput2.getPassword());
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
		idenInput1.setBounds(200, 253, 50, 25);
		idenInput1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField leng = (JTextField) e.getSource();
				char text = e.getKeyChar();
				if (leng.getText().length() >= 6) e.consume();
				// 아스키코드값
				// 48 : 0 ~ 57 : 9
				if (!(text >= 48 && text <= 57)) {
					e.consume();
				}
			}
		});
		mainPanel.add(idenInput1);
		idenInput2.setBounds(256, 253, 58, 25);
		idenInput2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField leng = (JTextField) e.getSource();
				char text = e.getKeyChar();
				if (leng.getText().length() >= 7) e.consume();
				// 아스키코드값
				// 48 : 0 ~ 57 : 9
				if (!(text >= 48 && text <= 57)) {
					e.consume();
				}
			}
		});
		mainPanel.add(idenInput2);
		ageText.setBounds(185, 298, 50, 25);
		ageText.setBackground(Color.WHITE);
		ageText.setForeground(Color.BLACK);
		ageText.setEditable(false);
		mainPanel.add(ageText);
		genderText.setBounds(285, 298, 50, 25);
		genderText.setBackground(Color.WHITE);
		genderText.setForeground(Color.BLACK);
		genderText.setEditable(false);
		mainPanel.add(genderText);
		phoneInput1.setBounds(200, 343, 45, 25);
		phoneInput1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField leng = (JTextField) e.getSource();
				char text = e.getKeyChar();
				if (leng.getText().length() >= 3) e.consume();
				// 아스키코드값
				// 48 : 0 ~ 57 : 9
				if (!(text >= 48 && text <= 57)) {
					e.consume();
				}
			}
		});
		mainPanel.add(phoneInput1);
		phoneInput2.setBounds(255, 343, 50, 25);
		phoneInput2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField leng = (JTextField) e.getSource();
				char text = e.getKeyChar();
				if (leng.getText().length() >= 4) e.consume();
				// 아스키코드값
				// 48 : 0 ~ 57 : 9
				if (!(text >= 48 && text <= 57)) {
					e.consume();
				}
			}
		});
		mainPanel.add(phoneInput2);
		phoneInput3.setBounds(315, 343, 50, 25);
		phoneInput3.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField leng = (JTextField) e.getSource();
				char text = e.getKeyChar();
				if (leng.getText().length() >= 4) e.consume();
				// 아스키코드값
				// 48 : 0 ~ 57 : 9
				if (!(text >= 48 && text <= 57)) {
					e.consume();
				}
			}
		});
		mainPanel.add(phoneInput3);
		
		idCheckBtn.setBounds(320, 70, 65, 30);
		idCheckBtn.addActionListener(this);
		mainPanel.add(idCheckBtn);
		idenCheckBtn.setBounds(320, 250, 65, 30);
		idenCheckBtn.addActionListener(this);
		mainPanel.add(idenCheckBtn);
		signupBtn.setBounds(155, 400, 65, 30);
		signupBtn.addActionListener(this);
		mainPanel.add(signupBtn);
		backBtn.setBounds(245, 400, 85, 30);
		backBtn.addActionListener(this);
		mainPanel.add(backBtn);
		
		this.add(mainPanel);
		
		this.setTitle("병원 예약 관리 시스템 : 회원가입");
		this.setSize(500, 500);				// 화면 크기 500x500
		this.setLocationRelativeTo(null);	// 화면 중앙 배치
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 이벤트 발생 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == signupBtn) {			// 가입 버튼 클릭
			if (signupBlankCheck()) {
				if (!idCheck && pwdCheck && !idenCheck) {
					// 회원가입 성공
					System.out.println("회원가입 성공");
					System.out.println("회원가입 화면 → 회원가입 성공 팝업");
					if (signupSuccess == null) {
						signupSuccess = new SignUpSuccessFrame(dbdao, false, "가입되었습니다.");
					}
					
					PatientDTO patientdto = new PatientDTO();
					String identityNum = idenInput1.getText() + "-" + idenInput2.getText();
					patientdto.setIdentityNum(identityNum);
					patientdto.setId(idInput.getText());
					String password = new String(pwdInput2.getPassword());
					patientdto.setPwd(password);
					patientdto.setName(nameInput.getText());
					patientdto.setAge(Integer.parseInt(ageText.getText()));
					patientdto.setGender(genderText.getText());
					String phone = phoneInput1.getText() + "-" 
								+ phoneInput2.getText() + "-" 
								+ phoneInput3.getText();
					patientdto.setPhone(phone);
					dbdao.patientSignUp(patientdto);
					
					this.setVisible(false);
					signupSuccess.setVisible(true);
				} else {
					// 회원가입 실패
					System.out.println("회원가입 실패");
					System.out.println("회원가입 화면 → 회원가입 실패 팝업");
					if (signupFail == null) {
						signupFail = new SignUpFailFrame(dbdao, false);
					}
					this.setVisible(false);
					signupFail.setVisible(true);
				}
			} else {
				// 회원가입 실패
				System.out.println("회원가입 실패");
				System.out.println("회원가입 화면 → 회원가입 실패 팝업");
				if (signupFail == null) {
					signupFail = new SignUpFailFrame(dbdao, false);
				}
				this.setVisible(false);
				signupFail.setVisible(true);
			}
		} else if (e.getSource() == backBtn) {		// 뒤로가기 버튼 클릭
			System.out.println("회원가입 화면 → 일반회원 로그인 화면");
			if (patientLogin == null) {
				patientLogin = new PatientLoginFrame(dbdao);
			}
			this.setVisible(false);
			patientLogin.setVisible(true);
		} else if (e.getSource() == idCheckBtn) {	// 아이디 중복 검사 버튼 클릭
			System.out.println("아이디 중복 검사");
			if (idInput.getText().isEmpty()) {
				idCheck = true;
				idCheckLabel.setText("아이디를 입력해주세요.");
				idCheckLabel.setForeground(Color.RED);
				return;
			} else {
				idCheck = dbdao.patientIdCheck(idInput.getText());
			}
			if (idCheck) {
				idCheckLabel.setText("중복된 아이디가 있습니다.");
				idCheckLabel.setForeground(Color.RED);
			} else {
				idCheckLabel.setText("OK");
				idCheckLabel.setForeground(Color.GREEN);
			}
		} else if (e.getSource() == idenCheckBtn) {	// 주민번호 중복 검사 버튼 클릭
			System.out.println("주민번호 중복 검사");
			if (idenInput1.getText().isEmpty() && idenInput2.getText().isEmpty()) {
				idenCheck = true;
				idenCheckLabel.setText("주민번호를 입력해주세요.");
				idenCheckLabel.setForeground(Color.RED);
				return;
			} else if (!(idenInput1.getText().length() == 6 && idenInput2.getText().length() == 7)) {
				idenCheck = true;
				idenCheckLabel.setText("올바른 주민번호가 아닙니다.");
				idenCheckLabel.setForeground(Color.RED);
				ageText.setText("");
				genderText.setText("");
				return;
			} else {
				identity = idenInput1.getText() + "-" + idenInput2.getText();
				idenCheck = dbdao.patientIdenCheck(identity);
			}
			if (idenCheck) {
				idenCheckLabel.setText("중복된 주민번호가 있습니다.");
				idenCheckLabel.setForeground(Color.RED);
				ageText.setText("");
				genderText.setText("");
			} else {
				idenCheckLabel.setText("OK");
				idenCheckLabel.setForeground(Color.GREEN);
				ageText.setText(Integer.toString(identityAge(idenInput1.getText())));
				genderText.setText(identityGender(idenInput2.getText()));
			}
		}
	}
	
	// 주민등록번호 입력 시 나이/성별 자동 기입
	// 생월 생일을 비교하여 나이를 구하는 메서드
	private int identityAge(String identity) {
		int age = 0;
		int myMonth = Integer.parseInt(identity.substring(2,4));
		int myDay = Integer.parseInt(identity.substring(4,6));
		
		Calendar current = Calendar.getInstance();
		String year = Integer.toString(current.get(Calendar.YEAR));
		int month = current.get(Calendar.MONTH) + 1;
		int day = current.get(Calendar.DAY_OF_MONTH);
		
		if (birthdayAge(identity, year) == 0) {
			return age;
		}
		if (((myMonth * 100) + myDay) > ((month * 100) + day)) {
			age = birthdayAge(identity, year) - 1;
		} else {
			age = birthdayAge(identity, year);
		}
		return age;
	}
	// 생년을 비교하여 나이를 구하는 메서드
	private int birthdayAge(String identity, String year) {
		int age = 0;
		String myYear = null;
		if (Integer.parseInt(identity.substring(0,2)) == Integer.parseInt(year.substring(2,4))) {
			return age;
		}
		if (Integer.parseInt(identity.substring(0,2)) >= Integer.parseInt(year.substring(2,4))) {
			myYear = "19" + identity.substring(0,2);
			age = Integer.parseInt(year) - Integer.parseInt(myYear);
			return age;
		} else {
			myYear = "20" + identity.substring(0,2);
			age = Integer.parseInt(year) - Integer.parseInt(myYear);
			return age;
		}
	}
	// 주민번호 뒷자리를 통해 성별을 구하는 메서드
	private String identityGender(String identity) {
		if ((Integer.parseInt(identity.substring(0,1)) % 2) != 0) {
			return "M";
		} else {
			return "F";
		}
	}
	
	// 회원가입 시 누락된 정보가 없으면 true, 하나라도 있으면 false
	private boolean signupBlankCheck() {
		String pwd1 = new String(pwdInput1.getPassword());
		String pwd2 = new String(pwdInput2.getPassword());
		if (idInput.getText().isEmpty() || pwd1.length() == 0 || pwd2.length() == 0 || 
				nameInput.getText().isEmpty() || idenInput1.getText().isEmpty() || 
				idenInput1.getText().isEmpty() || ageText.getText().isEmpty() || 
				genderText.getText().isEmpty() || phoneInput1.getText().isEmpty() || 
				phoneInput2.getText().isEmpty() || phoneInput3.getText().isEmpty()) {
			return false;
		}
		return true;
	}
}
