package test;

import static org.junit.Assert.*;
import monJeu.*;

import org.junit.Test;

public class TestObjet {

	@Test
	public void testRamassageObjet_CaseSeVide() {
		MonJeu jeu = new MonJeu();
		jeu.initialiser();
		jeu.getLabyrinthe().getCase(jeu.getHeros().getX(), jeu.getHeros().getY()).setObjet(new Potion());
		((Heros) jeu.getHeros()).ramasser(jeu.getLabyrinthe());

		assertEquals("La case ne devrait plus avoir d'objet", true, jeu.getLabyrinthe().getCase(jeu.getHeros().getX(), jeu.getHeros().getY()).getObjet() == null);
	}
	
	@Test
	public void testRamassageObjet_InventaireSeRemplit() {
		MonJeu jeu = new MonJeu();
		jeu.initialiser();
		jeu.getLabyrinthe().getCase(jeu.getHeros().getX(), jeu.getHeros().getY()).setObjet(new Potion());
		((Heros) jeu.getHeros()).ramasser(jeu.getLabyrinthe());
		
		assertEquals("Le premier emplacement de l'inventaire du héros ne devrait pas être vide", true, ((Heros) jeu.getHeros()).getInventaire(0) != null);
	}
	
	@Test
	public void testRamassageObjet_ConsommerPotion() {
		MonJeu jeu = new MonJeu();
		jeu.initialiser();
		jeu.getLabyrinthe().getCase(jeu.getHeros().getX(), jeu.getHeros().getY()).setObjet(new Potion());
		((Heros) jeu.getHeros()).ramasser(jeu.getLabyrinthe());
		jeu.getHeros().subirDegat(10);
		((Heros) jeu.getHeros()).consommer(0);

		assertEquals("Les pdv du héros devraient être a 15", true, ((Heros) jeu.getHeros()).getPv() == 15);
		assertEquals("Le premier emplacement de l'inventaire du héros devrait être vide", true, ((Heros) jeu.getHeros()).getInventaire(0) == null);
	}
	
	@Test
	public void testRamassageObjet_ConsommerAmulette() {
		MonJeu jeu = new MonJeu();
		jeu.initialiser();
		jeu.getLabyrinthe().getCase(jeu.getHeros().getX(), jeu.getHeros().getY()).setObjet(new Amulette());
		((Heros) jeu.getHeros()).ramasser(jeu.getLabyrinthe());
		jeu.getHeros().subirDegat(10);
		((Heros) jeu.getHeros()).consommer(0);

		assertEquals("Les pdv du héros devraient être a 10", true, ((Heros) jeu.getHeros()).getPv() == 10);
		assertEquals("Le premier emplacement de l'inventaire du héros ne devrait pas être vide", true, ((Heros) jeu.getHeros()).getInventaire(0) != null);
	}
	
	@Test
	public void testRamassageObjet_ConsommerVide() {
		MonJeu jeu = new MonJeu();
		jeu.initialiser();
		jeu.getHeros().subirDegat(10);
		((Heros) jeu.getHeros()).consommer(0);

		assertEquals("Les pdv du héros devraient être a 10", true, ((Heros) jeu.getHeros()).getPv() == 10);
		assertEquals("Le premier emplacement de l'inventaire du héros devrait être vide", true, ((Heros) jeu.getHeros()).getInventaire(0) == null);
	}
}
