package fr.istic.tpcamembert.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.istic.tpcamembert.generic.IRelay;
import fr.istic.tpcamembert.generic.Tools;
import fr.istic.tpcamembert.model.IModel;

public class Description extends JPanel {
	
	private Camembert camembert;
	private IModel model;
	private IRelay controller;

	private JPanel description = new JPanel();
	private JLabel keyLabel = new JLabel("Item");
	private JLabel valueLabel = new JLabel("Value");
	private JLabel textLabel = new JLabel("Description");
	private JTextField key = new JTextField();
	private JTextField value = new JTextField();
	private JTextField text = new JTextField();

	private JPanel buttons  = new JPanel();
	private JPanel topButtons  = new JPanel();
	private JPanel bottomButtons  = new JPanel();
	private JButton forward = new JButton(">");
	private JButton backward = new JButton("<");
	private JButton delete = new JButton("Delete");
	private JButton add = new JButton("Add");
	
	// création du descriptif de la vue camembert
	// avec des JTextArea (informations et saisie)
	// et des boutons (navigation et moidifications)
	public Description(IModel im, IRelay ic) {
		
		model = im;
		controller = ic;
		
		// initialisation des boutons
		initialize();
		
		description.setLayout(new GridLayout(2, 3));
		description.add(keyLabel);
		description.add(valueLabel);
		description.add(textLabel);
		description.add(key);
		description.add(value);
		description.add(text);
		key.setBorder(BorderFactory.createLineBorder(Color.black));
		value.setBorder(BorderFactory.createLineBorder(Color.black));
		text.setBorder(BorderFactory.createLineBorder(Color.black));
		
		buttons.setLayout(new GridLayout(2, 1));
		topButtons.setLayout(new GridLayout(1, 2));
		topButtons.add(backward);
		topButtons.add(forward);
		buttons.add(topButtons);
		bottomButtons.setLayout(new GridLayout(2, 1));		
		bottomButtons.add(delete);
		bottomButtons.add(add);
		buttons.add(bottomButtons);
		
		setLayout(new GridLayout(3,1));
		add(description);
		add(buttons);
	}
	
	public void setCamembert(Camembert cam) {
		camembert = cam;
	}
	
	// chaque bouton a une action sur le modèle
	// ou sur le camembert
	private void initialize() {	
		
		// avancer la sélection
		forward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				camembert.selectionForward();
			}
		});
		
		// reculer la sélection
		backward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				camembert.selectionBackward();
			}
		});
		
		// supprimer un item
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.removeItem(model.getKey(camembert.getSelection()));
				camembert.rebootSelection();
			}
		});
		
		// ajouter un item (à partir des JTextArea)
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on ajoute seulement si la key est présente et la value aussi sous forme de float
				if (!key.getText().equals("") && !value.getText().equals("") && Tools.isFloat(value.getText()))
					controller.addItem(key.getText(), text.getText(), Float.parseFloat(value.getText()));
			}
		});
	}
	
	// on override le repaint
	@Override
	public void repaint() {
		// si le camembert a été set
		if (camembert != null) {
			// et qu'on a aucune sélection,
			// certains boutons deviennent accessibles / non-accessibles
			if (camembert.getSelection() == -1) {
				forward.setEnabled(false);
				backward.setEnabled(false);
				delete.setEnabled(false);
				add.setEnabled(true);
				key.setText("");
				text.setText("");
				value.setText("");	
			}
			// et inversement
			else {
				forward.setEnabled(true);
				backward.setEnabled(true);
				delete.setEnabled(true);
				add.setEnabled(false);
				key.setText(model.getKey(camembert.getSelection()));
				text.setText(model.getText(camembert.getSelection()));
				value.setText(String.valueOf(model.getValue(camembert.getSelection())));
			}
			// on resize le panel complet
			setPreferredSize(new Dimension((int)(getParent().getSize().width / 2.5f), getSize().height));
		}

		super.repaint();
	}

}
