package fr.istic.tpcamembert.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	public MainFrame(CamembertView camembert, TableView table) {

		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.add(camembert, BorderLayout.CENTER);
		panel.add(table, BorderLayout.SOUTH);
		
		add(panel);
		
		setTitle("The Interactive Camembert");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
}
