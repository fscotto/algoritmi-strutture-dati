package it.algoritmi.test;

import java.util.HashMap;
import java.util.Map;

public final class PatternMatching {

	/** Restituisce l'indice minimo in cui inizia pattern nel testo (oppure -1). */
	public static int findBrute(char[] text, char[] pattern) {
		int n = text.length;
		int m = pattern.length;
		for (int i = 0; i <= n - m; ++i) { // prova tutti gli indici iniziali nel testo
			int k = 0;
			while (k < m && text[i + k] == pattern[k]) // il k-esimo carattere corrisponde
				++k;
			if (k == m) // se lo abbiamo raggiunto la fine del pattern
				return i; // la sottostringa text[i..i+m-1] corrisponde al pattern
		}
		return -1;
	}

	/** Restituisce l'indice in cui inizia pattern nel testo (oppure -1). */
	public static int findBoyerMoore(char[] text, char[] pattern) {
		int n = text.length;
		int m = pattern.length;
		if (m == 0)
			return 0; // ricerca banale di una stringa vuota
		Map<Character, Integer> last = new HashMap<>(); // la mappa della funzione 'last'
		for (int i = 0; i < n; i++)
			last.put(text[i], -1); // usa -1 come default per tutti i caratteri del testo
		for (int k = 0; k < m; k++)
			last.put(pattern[k], k); // l'occorrenza più a destra sarà l'ultima memorizzata
		// inizia con la fine del pattern allineata con l'indice m-1 del testo
		int i = m - 1; // indice nel testo
		int k = m - 1; // indice nel pattern
		while (i < n) {
			if (text[i] == pattern[k]) { // trovato un carattere che corrisponde
				if (k == 0)
					return i; // trovato un intero pattern che corrisponde
				i--;
				k--;
			} else {
				i += m - Math.min(k, 1 + last.get(text[i])); // decide come fare il salto
				k = m - 1; // riparte dalla fine del pattern
			}
		}

		return -1; // ricerca fallita
	}

	/** Restituisce l'indice minimo in cui inizia pattern nel testo (oppure -1). */
	public static int findKMP(char[] text, char[] pattern) {
		int n = text.length;
		int m = pattern.length;
		if (m == 0)									// ricerca banale di una stringa vuota
			return 0;
		int[] fail = computeFailKMP(pattern);		// funzione calcolata da un metodo privato
		int j = 0;									// indice del testo
		int k = 0;									// indice del pattern
		while (j < n) {
			if (text[j] == pattern[k]) {			// il pattern[0...k] corrisponde fin qui
				if (k == m - 1)
					return j - m + 1;				// corrispondenza completa
				j++;								// altrimenti cerca di estendere la corrispondenza
				k++;
			} else if (k > 0) {
				k = fail[k - 1];					// riutilizza il suffisso di P[0...k-1]
			} else {
				j++;
			}
		}
		return -1; // ricerca fallita
	}

	private static int[] computeFailKMP(char[] pattern) {
		int m = pattern.length;
		int[] fail = new int[m];				// tutte le sovrapposizioni cosi sono uguali a zero
		int j = 1;
		int k = 0;
		while (j < m) {							// in questo passo calcola fail[j], se non è zero
			if (pattern[j] == pattern[k]) {		// finora k+1 caratteri corrispondono
				fail[j] = k + 1;
				j++;
				k++;
			} else if (k > 0) {					// k segue un prefisso che corrisponde
				k = fail[k - 1];
			} else {
				j++;
			}
		}
		return fail;
	}

}
