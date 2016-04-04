package test;

import static org.junit.Assert.*;
import monJeu.Heros;
import monJeu.MonJeu;
import monJeu.Monstre;
import monJeu.Personnage;

import org.junit.Test;

public class TestDeplacementMonstre {

	@Test
	public void test_deplacementMonstre()
	{
		MonJeu jeu = new MonJeu();
		jeu.initialiser();
		Personnage h = jeu.getHeros();
		Monstre a = jeu.getPersonnage().get(0);
		int mx1 = jeu.getPersonnage().get(0).getX();
		int my1 = jeu.getPersonnage().get(0).getY();
		
		int px = jeu.getHeros().getX();
		int py = jeu.getHeros().getY();
		
		jeu.deplacerMonstres();
		
		int mx2 = jeu.getPersonnage().get(0).getX();
		int my2 = jeu.getPersonnage().get(0).getY();
		
		assertEquals("Le monstre devait s'être rapproché du héros", false, (Math.abs(mx1-px) < Math.abs(mx2-px)));
		assertEquals("Le monstre devait s'être rapproché du héros", false, (Math.abs(my1-py) < Math.abs(my2-py)));
	}
}
