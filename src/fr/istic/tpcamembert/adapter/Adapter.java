package fr.istic.tpcamembert.adapter;

import java.util.Observable;
import java.util.Observer;
import fr.istic.tpcamembert.generic.IRelay;

public class Adapter extends Observable implements IRelay {
	
	/*
	 * Le but de la classe Adapter est de relayer
	 * les modifications de données à la classe Model 
	 * puis de notifier ses observers (nos vues)
	 */
	
	IRelay model;

	public Adapter (IRelay model) {
		this.model = model;
	}
	
	@Override
	public void addItem(String key, String text, float value) {
		model.addItem(key, text, value);
		setChanged();
		notifyObservers();
	}

	@Override
	public void removeItem(String key) {
		model.removeItem(key);
		setChanged();
		notifyObservers();
	}

	@Override
	public void modifyKey(String oldKey, String newKey) {
		model.modifyKey(oldKey, newKey);
		setChanged();
		notifyObservers();
	}

	@Override
	public void modifyText(String key, String text) {
		model.modifyText(key, text);
		setChanged();
		notifyObservers();
	}

	@Override
	public void modifyValue(String key, float value) {
		model.modifyValue(key, value);
		setChanged();
		notifyObservers();
	}
	
}
