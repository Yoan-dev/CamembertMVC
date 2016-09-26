package fr.istic.tpcamembert.view;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import fr.istic.tpcamembert.generic.IRelay;
import fr.istic.tpcamembert.model.IModel;

public class CamembertView extends JPanel implements Observer {
	
	/*
	 * La vue camembert est séparée en deux panels
	 * le panel camembert (camembert visuel)
	 * et la partie description (avec saisie et boutons)
	 */
	
	private Camembert cam;
	private Description desc;
	
	public CamembertView (IModel im, IRelay ic) {
		
		cam = new Camembert(im, ic);
		desc = new Description(im, ic);
		cam.setDescription(desc);
		desc.setCamembert(cam);
		
		setLayout(new BorderLayout());
		add(cam, BorderLayout.CENTER);
		add(desc, BorderLayout.EAST);
	}

	// si on est notifié en tant qu'observer
	// on repaint nos deux panels
	@Override
	public void update(Observable o, Object arg) {
		cam.repaint();
		desc.repaint();
	}

}
