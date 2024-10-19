package gui;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.DBdao;

public class TextInsertFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel();
	private JLabel insertLabel = new JLabel();
	private JTextArea textArea = new JTextArea("여기에 입력해주세요.");
	private JScrollPane textScroll = new JScrollPane(textArea, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton checkBtn = new JButton("확인");
	private JButton closeBtn = new JButton("닫기");
	
	private DoctorMedicalListFrame doctorMedicalList = null;
	
	private int number = 0;
	
	private DBdao dbdao = null;
	public TextInsertFrame(DBdao db, int num, String text) {
		this.dbdao = db;
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		insertLabel.setFont(new Font(getName(), Font.PLAIN, 14));
		insertLabel.setBounds(45, 25, 90, 50);
		insertLabel.setText(text);
		mainPanel.add(insertLabel);
		
		textArea.setFont(new Font(getName(), Font.PLAIN, 14));
		textArea.setMargin(new Insets(5, 5, 5, 5));
		textArea.setLineWrap(true);
		textScroll.setBounds(45, 70, 245, 120);
		mainPanel.add(textScroll);
		
		checkBtn.setBounds(90, 205, 65, 30);
		mainPanel.add(checkBtn);
		closeBtn.setBounds(180, 205, 65, 30);
		mainPanel.add(closeBtn);
		
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
				if (doctorMedicalList == null) {
					doctorMedicalList = new DoctorMedicalListFrame(dbdao);
				}
				this.setVisible(false);
				doctorMedicalList.setVisible(true);
			} else if (e.getSource() == closeBtn) {
				System.out.println("증상메모 변경 팝업 → 예약 목록 화면");
				if (doctorMedicalList == null) {
					doctorMedicalList = new DoctorMedicalListFrame(dbdao);
				}
				this.setVisible(false);
				doctorMedicalList.setVisible(true);
			}
		} else if (number == 2) {
			// 취소사유 입력
			if (e.getSource() == checkBtn) {
				System.out.println("취소사유 등록");
				System.out.println("취소사유 등록 팝업 → 예약 목록 화면");
				// 코드 작성
				if (doctorMedicalList == null) {
					doctorMedicalList = new DoctorMedicalListFrame(dbdao);
				}
				this.setVisible(false);
				doctorMedicalList.setVisible(true);
			} else if (e.getSource() == closeBtn) {
				System.out.println("취소사유 등록 팝업 → 예약 목록 화면");
				if (doctorMedicalList == null) {
					doctorMedicalList = new DoctorMedicalListFrame(dbdao);
				}
				this.setVisible(false);
				doctorMedicalList.setVisible(true);
			}
		}
	}
}
