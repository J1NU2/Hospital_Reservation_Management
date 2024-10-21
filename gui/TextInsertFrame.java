package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.DBdao;
import dto.DoctorDTO;
import dto.ReservationDTO;

public class TextInsertFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel();
	private JLabel insertLabel = new JLabel();
	private JLabel checkLabel = new JLabel();
	private JTextArea textArea = new JTextArea("여기에 입력해주세요.");
	private JScrollPane textScroll = new JScrollPane(textArea, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton checkBtn = new JButton("확인");
	private JButton closeBtn = new JButton("닫기");
	
	private DoctorMedicalListFrame doctorMedicalList = null;
	
	private int number = 0;
	
	private DBdao dbdao = null;
	private DoctorDTO doctordto = null;
	private ReservationDTO reservdto = null;
	
	public TextInsertFrame(DBdao db, DoctorDTO doctordto, ReservationDTO reservdto, int num) {
		this.dbdao = db;
		this.doctordto = doctordto;
		this.reservdto = reservdto;
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		insertLabel.setFont(new Font(getName(), Font.PLAIN, 14));
		insertLabel.setBounds(45, 25, 90, 50);
		if (num == 1) {
			insertLabel.setText("증상메모 입력");
		} else if (num == 2) {
			insertLabel.setText("취소사유 입력");
		}
		mainPanel.add(insertLabel);
		checkLabel.setBounds(170, 26, 115, 50);
		mainPanel.add(checkLabel);
		
		textArea.setFont(new Font(getName(), Font.PLAIN, 14));
		textArea.setMargin(new Insets(5, 5, 5, 5));
		textArea.setLineWrap(true);
		textArea.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (textArea.getText().contains("여기에 입력해주세요.")) {
					textArea.setText("");
				}
			}
		});
		textArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (textArea.getText().contains("여기에 입력해주세요.")) {
					textArea.setText("");
				}
			}
		});
		textScroll.setBounds(45, 70, 245, 120);
		mainPanel.add(textScroll);
		
		checkBtn.setBounds(90, 205, 65, 30);
		mainPanel.add(checkBtn);
		closeBtn.setBounds(180, 205, 65, 30);
		mainPanel.add(closeBtn);
		
		checkBtn.addActionListener(this);
		closeBtn.addActionListener(this);
		
		number = num;
		this.add(mainPanel);
		
		this.setTitle("증상메모");
		this.setSize(350, 300);				// 화면 크기 350x200
		this.setLocationRelativeTo(null);	// 화면 중앙 배치
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 이벤트 발생 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		if (number == 1) {
			// 증상메모 변경
			if (e.getSource() == checkBtn) {
				System.out.println("증상메모 변경");
				System.out.println("증상메모 변경 팝업 → 예약 목록 화면");
				// 코드 작성
				if (!(textArea.getText().equals("") || textArea.getText().equals("여기에 입력해주세요."))) {
					if (doctorMedicalList == null) {
						doctorMedicalList = new DoctorMedicalListFrame(dbdao, doctordto);
					}
					dbdao.reservationMod(reservdto, textArea.getText());
					
					checkLabel.setText("");
					this.setVisible(false);
					doctorMedicalList.setVisible(true);
				} else {
					checkLabel.setText("증상을 입력해주세요.");
					checkLabel.setForeground(Color.RED);
				}
			} else if (e.getSource() == closeBtn) {
				System.out.println("증상메모 변경 팝업 → 예약 목록 화면");
				if (doctorMedicalList == null) {
					doctorMedicalList = new DoctorMedicalListFrame(dbdao, doctordto);
				}
				checkLabel.setText("");
				this.setVisible(false);
				doctorMedicalList.setVisible(true);
			}
		} else if (number == 2) {
			// 취소사유 입력
			if (e.getSource() == checkBtn) {
				System.out.println("취소사유 등록");
				System.out.println("취소사유 등록 팝업 → 예약 목록 화면");
				// 코드 작성
				if (!(textArea.getText().equals("") || textArea.getText().equals("여기에 입력해주세요."))) {
					if (doctorMedicalList == null) {
						doctorMedicalList = new DoctorMedicalListFrame(dbdao, doctordto);
					}
					dbdao.reservationCancel(reservdto, textArea.getText());
					
					checkLabel.setText("");
					this.setVisible(false);
					doctorMedicalList.setVisible(true);
				} else {
					checkLabel.setText("사유를 입력해주세요.");
					checkLabel.setForeground(Color.RED);
				}
			} else if (e.getSource() == closeBtn) {
				System.out.println("취소사유 등록 팝업 → 예약 목록 화면");
				if (doctorMedicalList == null) {
					doctorMedicalList = new DoctorMedicalListFrame(dbdao, doctordto);
				}
				checkLabel.setText("");
				this.setVisible(false);
				doctorMedicalList.setVisible(true);
			}
		}
	}
}
