package fr.istic.tpcamembert.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

import fr.istic.tpcamembert.generic.IRelay;
import fr.istic.tpcamembert.model.IModel;


public class Camembert extends JComponent implements MouseListener
{
	
	/*
	 * class permettant l'illustration d'un camembert
	 * NOTE: les valeurs dimensionnelles sont sous la forme
	 * "size" multiplié par quelque-chose
	 * cela permet un resize des éléments en fonction de la
	 * taille de la fenêtre (permets le resize manuel)
	 */

	private Graphics2D g2d;	
	private IModel model;
	private IRelay controller;
	private Description description;
	
	private double size;
	private int oX, oY;
	private int selection = -1;
	
	private String[] keys;
	private float[] percentages;
	
	public Camembert(IModel im, IRelay ic) {
		model = im;
		controller = ic;
		addMouseListener(this);
	}
	
	public void setDescription(Description desc) {
		description = desc;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		Dimension d = getSize();
		
		// on enregistre un certain nombre
		// de variables qui vont être utilisées
		// par la suite
		double width = d.getWidth();
		double height = d.getHeight();
		if (width > height)
			size = height * 0.8;
		else size = width * 0.8;
		// le centre du composant (origine du camembert)
		oX = (int)d.getWidth()/2;
		oY = (int)d.getHeight()/2;
		
		// on récupère les clés et les pourcentages associés
		keys = model.getKeys();
		percentages = model.getPercentages();
		
		// méthode dessinant les portions du camembert
		drawArcs(g2d, d);
		
		// méthode dessinant un vide au milieu des potions
		// (impression de cercle épais)
		drawCircle (g2d, d, 1.25f, getBackground());
		
		// méthode dessinant le cercle central
		drawCircle (g2d, d, 2, Color.BLUE);
		
		g2d.setFont(new Font("Arial", Font.BOLD, (int)(size * 0.045)));
		g2d.setColor(Color.WHITE);
		
		// on récupère le titre de notre modèle
		// et on l'affiche au centre
		String titre = model.getTitle() + ": ";
		String sum = model.getSum() + "";
		g2d.drawString(titre, (int)(oX-titre.length()*(size * 0.012)), (int)(oY - size * 0.025));
		g2d.drawString(sum, (int)(oX-sum.length()*(size * 0.012)), (int)(oY + size * 0.05));
		description.repaint();
	}
	
	// dessine un cercle
	private void drawCircle (Graphics2D g2d, Dimension d, float factor, Color color) {
		g2d.setColor(color);
		int intSize = (int)(size / factor);
		g2d.fillOval(oX-intSize/2, oY-intSize/2, intSize, intSize);
	}
	
	// dessine les parts de camembert
	private void drawArcs (Graphics2D g2d, Dimension d) {
		float temp = 0;
		boolean pair = true;
		int i = 0;
		for (float percentage : percentages) {
			Arc2D arc = new Arc2D.Float(Arc2D.PIE);
			if (i == selection) arc.setFrame(oX-size/2 - size * 0.1 / 2, oY- size / 2 - size * 0.1 / 2, size * 1.1, size * 1.1);
			else arc.setFrame(oX-size/2, oY-size/2, size, size);
			arc.setAngleStart(temp);
			arc.setAngleExtent(percentage / 100 * 360);
			temp += percentage / 100 * 360;
			
			pair = !pair;
			int range = (int) ((temp / 360 * 100) / 100 * 255);
			int r = pair? range : 255 - range;
			int g = pair? 255 - range : range;
			int b = 255 - range;
			g2d.setColor(new Color(r, g, b));
			
			g2d.fill(arc);
			
			float x = (float) (oX+((Math.cos(Math.toRadians(arc.getAngleStart()+arc.getAngleExtent()/2)))*size/1.75));
			float y = (float) (oY-((Math.sin(Math.toRadians(arc.getAngleStart()+arc.getAngleExtent()/2)))*size/1.75));
			if (selection != i) {
				g2d.setFont(new Font("Arial", Font.BOLD, (int)(size * 0.04)));
				g2d.setColor(Color.GRAY);
				g2d.drawString(keys[i], (float)(x - keys[i].length()*(size * 0.012)), y);				
			}
			
			i++;
		}
	}
	
	// permet d'avancer la sélection
	// (appelé par les boutons du descriptif)
	public void selectionForward() {
		if (selection == 0)
			selection = model.getSize() - 1;
		else selection--;
		repaint();
		description.repaint();
	}

	// permet de reculer la sélection
	// (appelé par les boutons du descriptif)
	public void selectionBackward() {
		if (selection == model.getSize() - 1)
			selection = 0;
		else selection++;
		repaint();
		description.repaint();
	}
	
	// remets la sélection à zéro
	public void rebootSelection() {
		selection = -1;
	}
	
	// récupète la sélection courante
	public int getSelection() {
		return selection;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		float temp = 0;
		int x = arg0.getX(), y = arg0.getY();
		int tempSelect = selection;
		rebootSelection();
		
		// on regarde si on click sur un item
		clickItem(arg0, temp, x, y, tempSelect);
		repaint();
	}
	
	private void clickItem(MouseEvent arg0, float temp, int x, int y, int tempSelect) {
		for (int i = 0; i < percentages.length; i++) {
			float temp1 = temp;
			temp += percentages[i] / 100 * 360;
			float a = (float) Math.toDegrees(Math.atan2(y - oY, x - oX));
			if (a < 0) a = -a;
			else a = 360 - a;
			float r = (float) (Math.sqrt(Math.pow(x - oX, 2) + Math.pow(y - oY, 2)));
			if (a < temp && a > temp1 && r > size / 1.25f / 2 && r < ((tempSelect == i)? size * 1.1 / 2 :size / 2)) {
				selection = i;
				break;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
