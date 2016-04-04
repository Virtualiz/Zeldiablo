package test;

import static org.junit.Assert.*;
import monJeu.Heros;
import monJeu.Labyrinthe;
import monJeu.Monstre;
import moteurJeu.Commande;

import org.junit.Test;

public class TestAttaque {

	@Test
	public void test_attaque_heros_sur_monstre_a_portee() {
		
		Labyrinthe lab = new Labyrinthe();
		Heros heros = new Heros(5,5);
		lab.getCase(5, 5).setPersonnage(heros);
		Monstre monstre = new Monstre(5,6);
		lab.getCase(5, 6).setPersonnage(monstre);
		heros.attaquer(lab);
		
		assertEquals("Les pv du monstre devraient être à 3",monstre.getPv(),3);
	}
	
	@Test
	public void test_attaque_heros_sur_monstre_hors_portee() {
		
		Labyrinthe lab = new Labyrinthe();
		Heros heros = new Heros(5,5);
		lab.getCase(5, 5).setPersonnage(heros);
		Monstre monstre = new Monstre(5,7);
		lab.getCase(5, 7).setPersonnage(monstre);
		
		heros.attaquer(lab);
		
		assertEquals("Les pv du monstre devraient être à 5",monstre.getPv(),5);
	}
	
	@Test
	public void test_attaque_monstre_sur_heros_a_portee() {
		
		Labyrinthe lab = new Labyrinthe();
		Heros heros = new Heros(5,5);
		lab.getCase(5, 5).setPersonnage(heros);
		Monstre monstre = new Monstre(5,6);
		lab.getCase(5, 6).setPersonnage(monstre);
		
		monstre.attaquer(lab);
		
		assertEquals("Les pv du héros devraient être à 19",heros.getPv(),19);
	}
	
	@Test
	public void test_attaque_monstre_sur_heros_hors_portee() {
		
		Labyrinthe lab = new Labyrinthe();
		Heros heros = new Heros(5,5);
		lab.getCase(5, 5).setPersonnage(heros);
		Monstre monstre = new Monstre(5,7);
		lab.getCase(5, 7).setPersonnage(monstre);
		
		monstre.attaquer(lab);
		
		assertEquals("Les pv du héros devraient être à 20",heros.getPv(),20);
	}


}
