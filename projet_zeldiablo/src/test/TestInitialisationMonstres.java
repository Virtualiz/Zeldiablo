package test;
import static org.junit.Assert.*;
import monJeu.*;

import org.junit.Test;

public class TestInitialisationMonstres {

	@Test
	public void test_position_initial() {
		Monstre monstre = new Monstre(5,5);
		assertEquals("La position du heros n'est pas la bonne position",5,monstre.getX());
		assertEquals("La position du heros n'est pas la bonne position",5,monstre.getY());
	}
	
	@Test
	public void test_position_initial_fausse() {
		Monstre monstre = new Monstre(-5,19);
		assertEquals("La position du heros n'est pas la bonne position",15,monstre.getX());
		assertEquals("La position du heros n'est pas la bonne position",15,monstre.getY());
	}

}
