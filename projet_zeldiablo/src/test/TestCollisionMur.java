package test;

import static org.junit.Assert.*;
import monJeu.*;
import moteurJeu.Commande;

import org.junit.Test;

public class TestCollisionMur {
	
	
	@Test
	public void test_deplacement_haut_libre() {
		
		Labyrinthe lab = new Labyrinthe();
		Heros heros = new Heros(5,5);
		Commande com = new Commande();
		com.haut=true;
		
		assertEquals("Le déplacement devrait être possible",heros.deplacerPossible(com,lab),true);
	}
	
	@Test
	public void test_deplacement_gauche_libre() {
		
		Labyrinthe lab = new Labyrinthe();
		Heros heros = new Heros(5,5);
		Commande com = new Commande();
		com.gauche=true;
		
		assertEquals("Le déplacement devrait être possible",heros.deplacerPossible(com,lab),true);
	}
	
	@Test
	public void test_deplacement_droite_libre() {
		
		Labyrinthe lab = new Labyrinthe();
		Heros heros = new Heros(5,5);
		Commande com = new Commande();
		com.droite=true;
		
		assertEquals("Le déplacement devrait être possible",heros.deplacerPossible(com,lab),true);
	}
	
	@Test
	public void test_deplacement_bas_libre() {
		
		Labyrinthe lab = new Labyrinthe();
		Heros heros = new Heros(4,5);
		Commande com = new Commande();
		com.bas=true;
		
		assertEquals("Le déplacement devrait être possible",heros.deplacerPossible(com,lab),true);
	}
	
	@Test
	public void test_deplacement_haut_mur() {
		
		Labyrinthe lab = new Labyrinthe();
		Heros heros = new Heros(1,1);
		Commande com = new Commande();
		com.haut=true;
		
		assertEquals("Le déplacement ne devrait pas être possible",heros.deplacerPossible(com,lab),false);
	}
	
	@Test
	public void test_deplacement_gauche_mur() {
		
		Labyrinthe lab = new Labyrinthe();
		Heros heros = new Heros(1,1);
		Commande com = new Commande();
		com.gauche=true;
		
		assertEquals("Le déplacement ne devrait pas être possible",heros.deplacerPossible(com,lab),false);
	}
	
	@Test
	public void test_deplacement_droite_mur() {
		
		Labyrinthe lab = new Labyrinthe();
		Heros heros = new Heros(8,8);
		Commande com = new Commande();
		com.droite=true;
		
		assertEquals("Le déplacement ne devrait pas être possible",heros.deplacerPossible(com,lab),false);
	}
	
	@Test
	public void test_deplacement_bas_mur() {
		
		Labyrinthe lab = new Labyrinthe();
		Heros heros = new Heros(8,8);
		Commande com = new Commande();
		com.bas=true;
		
		assertEquals("Le déplacement ne devrait pas être possible",heros.deplacerPossible(com,lab),false);
	}

}
