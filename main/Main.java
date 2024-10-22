package main;

import dao.DBdao;
import dao.HospitalDAO;
import gui.MainFrame;

public class Main {

	public static void main(String[] args) {
		DBdao dao = new HospitalDAO();
		MainFrame mf = new MainFrame(dao);
	}

}
