package fr.istic.tpcamembert.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.istic.tpcamembert.generic.IRelay;
import fr.istic.tpcamembert.model.IModel;

public class TableView extends JPanel implements Observer {
	
	/*
	 * TableView est un panel comprenant une JTable
	 * c'est la classe de base de notre vue table
	 */

	private AbstractTableView abstractTable;
	private JTable table;
	private JScrollPane scrollPane;
	
	IModel model;

	public TableView (IModel im, IRelay ic) {
		model = im;
		
		setLayout(new GridLayout());
		abstractTable = new AbstractTableView(im, ic);
		table = new JTable(abstractTable);
		scrollPane = new JScrollPane(table);
		add(scrollPane);
		refresh();
	}

	// l'update d'Observer permets d'actualiser
	// la vue lors d'un changement
	@Override
	public void update(Observable arg0, Object arg1) {
		refresh();
	}
	
	// au début puis à chaque modif, on resize
	// le scrollPane pour avoir un affichage de
	// toutes les lignes (nombre d'items) mais pas plus
	private void refresh() {
		scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth(), 25 + 16 * model.getSize()));
		abstractTable.fireTableStructureChanged();
	}
	
}
