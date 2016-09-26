package fr.istic.tpcamembert.view;

import javax.swing.table.AbstractTableModel;

import fr.istic.tpcamembert.generic.IRelay;
import fr.istic.tpcamembert.model.IModel;

public class AbstractTableView extends AbstractTableModel {
	
	/*
	 * classe servant de modèle lors
	 * de la création de notre JTable
	 */
	
	private String[] columns = { "Key", "Value", "Text" };

	private IModel model;
	private IRelay controller;

	public AbstractTableView(IModel im, IRelay ic) {
		super();
		model = im;
		controller = ic;
	}

	@Override
    public String getColumnName(int i) {
        return columns[i];
    }
	
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return model.getSize();
	}

	// récupération des valeurs
	@Override
	public Object getValueAt(int arg0, int arg1) {
		if (arg1 == 0) {
			return model.getKey(arg0);
		}
		else if (arg1 == 1) {
			return model.getValue(arg0);			
		}
		else if (arg1 == 2) {
			return model.getText(arg0);			
		}
		else return null;
	}
	
	// on permet la modification des cellules
	@Override
	public boolean isCellEditable(int i, int j) {
		return true;
	}
	

	// lorsqu'on modifie une cellule,
	// on déclenche le comportement
	// voulue sur notre modèle
	@Override
	public void setValueAt(Object v, int i, int j) {
		if (j == 0) {
			controller.modifyKey(model.getKey(i), (String) v);
		}
		else if (j == 1) {
			// vérifier si c'est un float car sinon ça fait boum
			controller.modifyValue(model.getKey(i), Float.parseFloat((String) v));
		}
		else if (j == 2) {
			controller.modifyText(model.getKey(i), (String) v);
		}
	}

}
