package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Dictionary {

	
	List<String> dizionario = new LinkedList<>();
	List<WordCheck> testoInput = new LinkedList<>();
	
	public void loadDictionary(String language) {
		
		if(language.compareTo("English")==0) {
		try {
			FileReader fr = new FileReader("src/main/resources/English.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			
			while((word = br.readLine()) != null) {
				dizionario.add(word);
			}
			br.close();
		}catch(IOException e) {
			System.out.println("Errore in lettura file English.txt!");
		}
		}else if(language.compareTo("Italian")==0) {
		try {
			FileReader fr = new FileReader("src/main/resources/Italian.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			
			while((word = br.readLine()) != null) {
				dizionario.add(word);
			}
			br.close();
		}catch(IOException e) {
			System.out.println("Errore in lettura file Italian.txt!");
		}
		}
	}

	public List<WordCheck> checkTxt(String testo) {
		
		testo.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_'()\\[\\]\"]", "");
		
		StringTokenizer st = new StringTokenizer(testo, " ");
		while(st.hasMoreTokens()) {
			WordCheck parola = new WordCheck(st.nextToken());
			for(String w: dizionario) {
				if(w.compareTo(parola.getParola().toLowerCase())==0) {
					parola.setStatus(true);
				}
			}
			testoInput.add(parola);	
		}
		
		return testoInput;
	}

	public List<WordCheck> checkTxtDichotomic(String testo) {
		
		testo.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_'()\\[\\]\"]", "");

		StringTokenizer st = new StringTokenizer(testo, " ");
		while (st.hasMoreTokens()) {
			WordCheck parola = new WordCheck(st.nextToken());
			
			for (String w : dizionario) {
				if (w.compareTo(parola.getParola().toLowerCase()) == 0) {
					parola.setStatus(true);
				}
			}
			testoInput.add(parola);
		}

		return testoInput;
	}
}
