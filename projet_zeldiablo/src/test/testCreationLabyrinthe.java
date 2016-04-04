package test;

import static org.junit.Assert.*;
import monJeu.*;

import org.junit.Test;

public class testCreationLabyrinthe {

	@Test
	public void testTaille() {
		MonJeu jeu = new MonJeu();
		jeu.initialiser();
		Case casee = jeu.getLabyrinthe().getCase(100,100);
	}
	
	// La génération du personnage étant aléatoire, ce test ne convient plus
//	@Test
//	public void testPositionPersonnage() {
//		MonJeu jeu = new MonJeu();
//		jeu.initialiser();
//		assertEquals("Le personnage n'est pas a la bonne place",jeu.getHeros().getX(), 5);
//		assertEquals("Le personnage n'est pas a la bonne place",jeu.getHeros().getY(), 5);
//	}

	// La génération des monstres étant aléatoire, ce test ne convient plus
//	@Test
//	public void testPositionMonstres(){
//		MonJeu jeu = new MonJeu();
//		jeu.initialiser();
//		Monstre a = jeu.getPersonnage().get(0);
//		Monstre b = jeu.getPersonnage().get(1);
//		assertEquals("Le personnage n'est pas a la bonne place",a.getX(), 5);
//		assertEquals("Le personnage n'est pas a la bonne place",a.getY(), 3);
//		assertEquals("Le personnage n'est pas a la bonne place",b.getX(), 1);
//		assertEquals("Le personnage n'est pas a la bonne place",b.getY(), 1);
//	}
	
	@Test
	public void testPositionEntree()
	{
		MonJeu jeu = new MonJeu();
		jeu.initialiser();
		Personnage heros = jeu.getHeros();
		Case entree = jeu.getLabyrinthe().getCase(heros.getX(), heros.getY());
		assertTrue("La case sous le héros devrait être l'entrée", entree.etreEntree());
	}
	
	@Test
	public void testPositionAmulette()
	{
		MonJeu jeu = new MonJeu();
		jeu.initialiser();
		
		for (int i = 0 ; i < Labyrinthe.TAILLE ; i++)
		{
			for (int j = 0 ; j < Labyrinthe.TAILLE ; j++)
			{
				if (jeu.getLabyrinthe().getCase(i, j).getObjet() != null)
				{
					if (jeu.getLabyrinthe().getCase(i, j).getObjet().getNomObjet().equals("Amulette"))
					{
						assertEquals("l'amulette devrait se trouver sur une case traversable", true, jeu.getLabyrinthe().getCase(i, j).estTraversable());
					}
				}
			}
		}
	}
	
	@Test
	public void testPositionAmulette_DistanceAvecLeSpawn()
	{
		MonJeu jeu = new MonJeu();
		jeu.initialiser();
		
		int lim=Labyrinthe.TAILLE/2;
		lim *= lim;
		lim *=2;
		lim = (int)Math.sqrt(lim);
		
		for (int i = 0 ; i < Labyrinthe.TAILLE ; i++)
		{
			for (int j = 0 ; j < Labyrinthe.TAILLE ; j++)
			{
				if (jeu.getLabyrinthe().getCase(i, j).getObjet() != null)
				{
					if (jeu.getLabyrinthe().getCase(i, j).getObjet().getNomObjet().equals("Amulette"))
					{
						assertEquals("l'amulette devrait se trouver à une plus grande distance", true, jeu.calculerDistance(i, j, jeu.getHeros().getX(), jeu.getHeros().getY()) >= lim);
					}
				}
			}
		}
	}
}
