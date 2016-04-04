package monJeu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Labyrinthe {

	private Case[][] carte;
	public final static int TAILLE = 30;
	
	public Labyrinthe(){
		try {
			int i = (int)Math.round(Math.random()*4);
			switch(i){
			case 0:
				carte = chargerLabyrinthe("projet_zeldiablo/src/pattern_labyrinthe1.txt");
				break;
			case 1:
				carte = chargerLabyrinthe("projet_zeldiablo/src/pattern_labyrinthe2.txt");
				break;
			case 2:
				carte = chargerLabyrinthe("projet_zeldiablo/src/pattern_labyrinthe3.txt");
				break;
			case 3:
				carte = chargerLabyrinthe("projet_zeldiablo/src/pattern_labyrinthe4.txt");
				break;
			case 4:
				carte = chargerLabyrinthe("projet_zeldiablo/src/pattern_labyrinthe5.txt");
				break;
			}		
		} catch (IOException e) {
			carte = new Case[TAILLE][TAILLE];
			for(int i = 0; i < TAILLE; i++){
				for(int j = 0; j < TAILLE; j++){
					carte[i][j] = new Sol();
				}
			}
			for(int i = 0; i<TAILLE; i++){
				carte[i][0] = new Mur();
				carte[i][TAILLE-1] = new Mur();
				carte[0][i] = new Mur();
				carte[TAILLE-1][i] = new Mur();
			}	
			for(int j = 0; j < 4; j++){
				carte[4][j] = new Mur();
			}
			for(int k = 2; k < 7; k++){
				carte[7][k] = new Mur();
			}
			for(int j = 6; j < 10; j++){
				carte[5][j] = new Mur();
			}
			for(int j = 0; j < 4; j++){
				carte[j][5] = new Mur();
			}
			carte[8][6] = new Mur();
		}
	}
	
	public Case getCase(int x, int y){
		if(x<TAILLE && x >= 0 && y < TAILLE && y >= 0){			
		return carte[x][y];
		}else{
			return null;
		}
	}
	
	public ArrayList<Monstre>getMonstres(int x,int y){
		ArrayList<Monstre> liste_monstres = new ArrayList<Monstre>();
		
		if((Monstre) this.getCase(x+1, y).getPersonnage() != null)
		liste_monstres.add((Monstre) this.getCase(x+1, y).getPersonnage());
		
		if((Monstre) this.getCase(x, y+1).getPersonnage() != null)
		liste_monstres.add((Monstre) this.getCase(x, y+1).getPersonnage());
		
		if((Monstre) this.getCase(x-1, y).getPersonnage() != null)
		liste_monstres.add((Monstre) this.getCase(x-1, y).getPersonnage());
		
		if((Monstre) this.getCase(x, y-1).getPersonnage() != null)
		liste_monstres.add((Monstre) this.getCase(x, y-1).getPersonnage());
		
		return liste_monstres;
	}
	
	public Heros getHeros(int x, int y){
		if(this.getCase(x+1, y).getPersonnage() != null && this.getCase(x+1, y).getPersonnage().getType().equals("PJ"))
			return (Heros) this.getCase(x+1, y).getPersonnage();
		if(this.getCase(x, y+1).getPersonnage() != null && this.getCase(x, y+1).getPersonnage().getType().equals("PJ"))
			return (Heros) this.getCase(x, y+1).getPersonnage();
		if(this.getCase(x-1, y).getPersonnage() != null && this.getCase(x-1, y).getPersonnage().getType().equals("PJ"))
			return (Heros) this.getCase(x-1, y).getPersonnage();
		if(this.getCase(x, y-1).getPersonnage() != null && this.getCase(x, y-1).getPersonnage().getType().equals("PJ"))
			return (Heros) this.getCase(x, y-1).getPersonnage();
		else{
			return null;
		}
	}

	public Case[][] chargerLabyrinthe(String source) throws IOException{
		Case[][] nouvelle_carte = new Case[TAILLE][TAILLE];
		BufferedReader fichierlu = new BufferedReader(new FileReader(source));
		String ligne;
		int j=0;
		while ((ligne = fichierlu.readLine()) != null) {
			for(int i =0; i< TAILLE; i++){
				char c = ligne.charAt(i);
				if(c == 'S'){
					nouvelle_carte[i][j] = new Sol();
				}
				if(c == 'M'){
					nouvelle_carte[i][j] = new Mur();
				}
			}
			j++;
		}
		fichierlu.close();
		return nouvelle_carte;
	}

}
