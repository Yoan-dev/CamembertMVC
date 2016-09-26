package fr.istic.tpcamembert.controller;

import fr.istic.tpcamembert.generic.IRelay;

public class Controller implements IRelay {
	
	/*
	 * Le but de la classe Controller est de relayer
	 * les modifications de données à la classe Adapter 
	 * (appelées par la vue)
	 */
	
	private IRelay adapter;
	
	public Controller(IRelay adapter) {
		this.adapter = adapter;
	}
	
	@Override
	public void addItem(String key, String text, float value) {
		adapter.addItem(key, text, value);
	}

	@Override
	public void removeItem(String key) {
		adapter.removeItem(key);
	}

	@Override
	public void modifyKey(String oldKey, String newKey) {
		adapter.modifyKey(oldKey, newKey);	
	}

	@Override
	public void modifyText(String key, String text) {
		adapter.modifyText(key, text);	
	}

	@Override
	public void modifyValue(String key, float value) {
		adapter.modifyValue(key, value);	
	}
	
}
