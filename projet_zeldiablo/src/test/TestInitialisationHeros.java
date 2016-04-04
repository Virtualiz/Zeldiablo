package test;
import static org.junit.Assert.*;
import monJeu.*;

import org.junit.Test;

public class TestInitialisationHeros {

	@Test
	public void test_position_initial() {
		Heros hero = new Heros(5,5);
		assertEquals("La position du heros n'est pas la bonne position",5,hero.getX());
		assertEquals("La position du heros n'est pas la bonne position",5,hero.getY());
	}
	
	@Test
	public void test_position_initial_fausse() {
		Heros hero = new Heros(-5,19);
		assertEquals("La position du heros n'est pas la bonne position",15,hero.getX());
		assertEquals("La position du heros n'est pas la bonne position",15,hero.getY());
	}

}
