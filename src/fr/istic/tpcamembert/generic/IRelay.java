package fr.istic.tpcamembert.generic;

public interface IRelay {
	
	/*
	 * Cette interface est commune aux classes
	 * Controller, Adapter et Model qui vont
	 * implémenter les méthodes de leur propre
	 * manière.
	 */
	
	public void addItem (String key, String text, float value);
	
	public void removeItem (String key);
	
	public void modifyKey (String oldKey, String newKey);
	
	public void modifyText (String key, String text);
	
	public void modifyValue (String key, float value);

}
