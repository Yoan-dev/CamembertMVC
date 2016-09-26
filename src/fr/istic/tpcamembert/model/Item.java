package fr.istic.tpcamembert.model;

public class Item {
	
	/*
	 * un item de notre modèle de données
	 * est composé d'un texte descriptif
	 * et d'une valeur
	 */
	
	private String text;
	private float value;
	
	public Item (String text, float value) {
		this.text = text;
		this.value = value;
	}
	
	// Accessors
	
	public String getText () {
		return text;
	}
	
	public float getValue () {
		return value;
	}
	
	public void setText (String text) {
		this.text = text;
	}
	
	public void setValue (float value) {
		this.value = value;
	}
	
	// /Accessors

}
