package fr.istic.tpcamembert.generic;

public class Tools {
	
	/*
	 * méthode pour vérifier si une chaine
	 * de caractères peut être cast en float
	 */
	
	private static char[] numbers = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	
	public static boolean isFloat (String s) {
		boolean coma = false;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '.')
				if (coma) return false;
				else coma = true;
			else if (!isNumber(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isNumber (char c) {
		for (char n : numbers)
			if (c == n)
				return true;
		return false;
	}

}
