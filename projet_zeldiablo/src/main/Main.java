package main;

import monJeu.Labyrinthe;
import monJeu.MonDessin;
import monJeu.MonJeu;
import moteurJeu.Jeu;
import moteurJeu.MoteurGraphique;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		MonJeu jeu = new MonJeu();
		jeu.initialiser();
		MonDessin aff = new MonDessin(jeu);

		// classe qui lance le moteur de jeu generique
		MoteurGraphique moteur = new MoteurGraphique(jeu, aff);
		moteur.lancerJeu(MonDessin.TAILLE_CASE*Labyrinthe.TAILLE,MonDessin.TAILLE_CASE*Labyrinthe.TAILLE);
	}

}
