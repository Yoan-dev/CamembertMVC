package fr.istic.tpcamembert.model;

import java.util.HashMap;
import java.util.Map;

import fr.istic.tpcamembert.generic.IRelay;

public class Model implements IRelay, IModel {
	
	/*
	 * l'implémentation de notre modèle de données
	 */
	
	private String title;
	private Map<String, Item> data = new HashMap<String, Item>();
	
	public Model(String title) {
		this.title = title;
	}

	@Override
	public void addItem(String key, String text, float value) {
		data.put(key, new Item(text, value));
		
	}

	@Override
	public void removeItem(String key) {
		data.remove(key);
		
	}

	@Override
	public void modifyKey(String oldKey, String newKey) {
		Item temp = data.get(oldKey);
		data.remove(oldKey);
		data.put(newKey, temp);		
	}

	@Override
	public void modifyText(String key, String text) {
		data.get(key).setText(text);
	}

	@Override
	public void modifyValue(String key, float value) {
		data.get(key).setValue(value);
	}

	@Override
	public float getSum() {
		float res = 0;
		for (Item item : data.values())
			res += item.getValue();
		return res;
	}

	@Override
	public float[] getPercentages() {
		float[] res = new float[data.size()];
		float sum = getSum();
		int i = 0;
		for (Item item : data.values()) {
			res[i] = item.getValue() / sum * 100;
			i++;
		}
		return res;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String[] getKeys() {
		String[] res = new String[data.size()];
		int i = 0;
		for (String key : data.keySet()) {
			res[i] = key;
			i++;
		}
		return res;
	}

	@Override
	public String[] getTexts() {
		String[] res = new String[data.size()];
		int i = 0;
		for (Item item : data.values()) {
			res[i] = item.getText();
			i++;
		}
		return res;
	}

	@Override
	public float[] getValues() {
		float[] res = new float[data.size()];
		int i = 0;
		for (Item item : data.values()) {
			res[i] = item.getValue();
			i++;
		}
		return res;
	}

	@Override
	public String getKey(int i) {
		int j = 0;
		for (String key : data.keySet()) {
			if (i == j) return key;
			j++;
		}
		return "";
			
	}

	@Override
	public String getText(int i) {
		int j = 0;
		for (Item item : data.values()) {
			if (i == j) return item.getText();
			j++;
		}
		return "";
	}

	@Override
	public float getValue(int i) {
		int j = 0;
		for (Item item : data.values()) {
			if (i == j) return item.getValue();
			j++;
		}
		return -1;
	}

	@Override
	public int getSize() {
		return data.size();
	}
}
