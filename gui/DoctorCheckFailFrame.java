package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.DBdao;

public class DoctorCheckFailFrame extends JFrame implements ActionListener {
	private JPanel mainPanel = new JPanel();
	
	private JLabel failLabel = new JLabel("관계자 인증 확인에 실패하였습니다.");
	
	private JButton closeBtn = new JButton("닫기");
	
	private DoctorCheckFrame doctorCheck = null;
	
	private DBdao dbdao = null;
	
	public DoctorCheckFailFrame(DBdao db) {
		this.dbdao = db;
		
		// AbsoluteLayout
		mainPanel.setLayout(null);
		
		failLabel.setFont(new Font(getName(), Font.BOLD, 14));
		failLabel.setBounds(55, 25, 230, 50);
		mainPanel.add(failLabel);
		
		closeBtn.setBounds(135, 90, 60, 30);
		closeBtn.addActionListener(this);
		mainPanel.add(closeBtn);
		
		this.add(mainPanel);
		
		this.setTitle("관계자 인증 실패");
		this.setSize(350, 200);				// 화면 크기 350x200
		this.setLocationRelativeTo(null);	// 화면 중앙 배치
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 이벤트 발생 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == closeBtn) {	// 닫기 버튼 클릭
			System.out.println("팝업창 닫기");
			System.out.println("인증 실패 팝업 → 관계자 인증 팝업");
			if (doctorCheck == null) {
				doctorCheck = new DoctorCheckFrame(dbdao);
			}
			this.setVisible(false);
			doctorCheck.setVisible(true);
		}
	}
}
