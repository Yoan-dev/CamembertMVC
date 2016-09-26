package fr.istic.tpcamembert.model;

public interface IModel {
	
	/*
	 * Cette interface est implémentée par la classe Model
	 * Elle sera donnée à la vue plutôt que l'interface
	 * IRelay, fournissant ainsi une meilleure abstraction
	 * car la vue n'aura pas accès aux méthodes de modification
	 * de données
	 */
	
	public float getSum();
	
	public float[] getPercentages();
	
	public String getTitle();
	
	public String[] getKeys();
	
	public String[] getTexts();
	
	public float[] getValues();
	
	public String getKey(int i);
	
	public String getText(int i);
	
	public float getValue(int i);
	
	public int getSize();
	
}
