package monJeu;
import java.util.*;

import moteurJeu.Commande;
import moteurJeu.Jeu;


public class MonJeu implements Jeu {
	private List<Monstre> elements;
	private Heros heros;
	private Labyrinthe labyrinthe;
	private boolean gagne;

	public MonJeu(){
		elements = new ArrayList<Monstre>();
		gagne = false;
	}

	public void initialiser(){
		labyrinthe = new Labyrinthe();

		int[] coord = emplacementAleatoire(Labyrinthe.TAILLE);
		this.heros = new Heros(coord[0],coord[1]);
		labyrinthe.getCase(coord[0],coord[1]).setPersonnage(heros);
		labyrinthe.getCase(coord[0],coord[1]).setEntree();

		// placement de l'amulette
		coord = emplacementAleatoire(Labyrinthe.TAILLE);
		int lim=Labyrinthe.TAILLE/3;
		lim *= lim;
		lim *=2;
		lim = (int)Math.sqrt(lim);
		while (calculerDistance(heros.getX(), heros.getY(), coord[0], coord[1]) < lim )
		{
			coord = emplacementAleatoire(Labyrinthe.TAILLE);
		}
		labyrinthe.getCase(coord[0],coord[1]).setObjet(new Amulette());
		//Generation des armes
		coord = emplacementAleatoire(Labyrinthe.TAILLE);
		labyrinthe.getCase(coord[0],coord[1]).setObjet(new Faux());
		
		coord = emplacementAleatoire(Labyrinthe.TAILLE);
		labyrinthe.getCase(coord[0],coord[1]).setObjet(new Epee());
		// on génère les monstres
		int nbmonstre = (int) (Math.random()*3+5);

		for (int i = 0 ; i < nbmonstre/2 ; i++)
		{
			coord = emplacementAleatoire(Labyrinthe.TAILLE);
			Monstre m = new Monstre(coord[0], coord[1]);
			labyrinthe.getCase(coord[0], coord[1]).setPersonnage(m);
			elements.add(m);
		}
		for (int j = nbmonstre/2; j <= nbmonstre; j++){
			
			coord = emplacementAleatoire(Labyrinthe.TAILLE);
			Fantome f = new Fantome(coord[0], coord[1]);
			labyrinthe.getCase(coord[0], coord[1]).setPersonnage(f);
			elements.add(f);
		}
		
		// on génère les potions
		int nbpotion = (int) (Math.random()*2+3);

		for (int i = 0 ; i <= nbpotion ; i++)
		{
			coord = emplacementAleatoire(Labyrinthe.TAILLE);
			Objet obj = new Potion();
			labyrinthe.getCase(coord[0], coord[1]).setObjet(obj);
		}
	}


	public String toString(){
		String res = heros.toString() + "\n";
		for(Personnage monstre: elements){
			res += monstre.toString()+"\n";
		}
		return res;
	}

	public Personnage getHeros() {
		return heros;
	}

	@Override
	public void evoluer(Commande commandeUser) {
		if(!commandeUser.ramasse){
			if(!commandeUser.attaque){
				if(commandeUser.consObj1||commandeUser.consObj2||commandeUser.consObj3||commandeUser.consObj4||commandeUser.consObj5||commandeUser.consObj6){
					if(commandeUser.consObj1) heros.consommer(0);
					if(commandeUser.consObj2) heros.consommer(1);
					if(commandeUser.consObj3) heros.consommer(2);
					if(commandeUser.consObj4) heros.consommer(3);
					if(commandeUser.consObj5) heros.consommer(4);
					if(commandeUser.consObj6) heros.consommer(5);
				}
				if (heros.deplacerPossible(commandeUser, labyrinthe))

				{
					heros.deplacer(commandeUser, labyrinthe);
				}
			}else{
				heros.attaquer(labyrinthe);
			}
		}else{
			heros.ramasser(labyrinthe);
		}
	

		List<Monstre> aRetirer = new ArrayList<Monstre>();
		for(Monstre mons : elements){
			if(mons.getPv()<=0) {
				aRetirer.add(mons);
				labyrinthe.getCase(mons.getX(), mons.getY()).setPersonnage(null);
			}
		}
		for(Monstre mons : aRetirer){
			elements.remove(mons);
		}

		deplacerMonstres();
	}

	@Override
	public boolean etreFini() {
		gagne = heros.possedeAmulette() && labyrinthe.getCase(heros.getX(), heros.getY()).etreEntree();
		return (heros.getPv() <= 0 || (heros.possedeAmulette() && labyrinthe.getCase(heros.getX(), heros.getY()).etreEntree()));
	}


	public Labyrinthe getLabyrinthe(){
		return labyrinthe;
	}

	public List<Monstre> getPersonnage(){
		return elements;
	}

	public void deplacerMonstres(){
		for(Monstre mons : elements){
			Commande c = new Commande();
			if(calculerDistance(heros.getX(), heros.getY(), mons.getX(),mons.getY()) < mons.getVision()){
				int[] opti = new int[2];
				int cheminC = 9000;
				List<int[]> voisins = new ArrayList<int[]>();
				for(int i = -1; i<2;i= i+2){
					int[] x = {mons.getX()+i,mons.getY()};
					voisins.add(x);
					int[] y = {mons.getX(),mons.getY()+i};
					voisins.add(y);
				}
				for(int[] vois : voisins){
					if(mons.getType().equals("MONSTRE")){
						if(labyrinthe.getCase(vois[0], vois[1]).estTraversable()){
							int cheminA = calculerDistance(vois[0],vois[1],heros.getX(),heros.getY());
							if(cheminA < cheminC){
								cheminC = cheminA;
								opti[0] = vois[0];
								opti[1] = vois[1];
							}
						}
					}else{
						int cheminA = calculerDistance(vois[0],vois[1],heros.getX(),heros.getY());
						if(cheminA < cheminC){
							cheminC = cheminA;
							opti[0] = vois[0];
							opti[1] = vois[1];
						}


					}
				}
				if(opti[0] == mons.getX()+1 && opti[1] == mons.getY()){
					c.droite = true;
				}else if(opti[0] == mons.getX()-1 && opti[1] == mons.getY()){
					c.gauche = true;
				}else if(opti[0] == mons.getX() && opti[1] == mons.getY()+1){
					c.bas = true;
				}else if(opti[0] == mons.getX() && opti[1] == mons.getY()-1){
					c.haut = true;
				}
			}else{
				int i = (int)Math.round(Math.random()*4);
				switch(i){
				case 0:
					break;

				case 1:
					c.haut = true;
					break;
				case 2:
					c.bas = true;
					break;
				case 3:
					c.gauche = true;
					break;
				case 4:
					c.droite = true;
					break;
				default : 
					break;
				}
			}
			if(!mons.attaquer(labyrinthe)){
				if (mons.deplacerPossible(c, labyrinthe))
				{
					mons.deplacer(c, labyrinthe);
				}	
			}
		}
	}
	




	public int calculerDistance(int x1, int y1, int x2, int y2)
	{
		return (int) (Math.sqrt((x1-x2)*(x1-x2) + (y1 - y2)*(y1 - y2)));
	}

	public int[] emplacementAleatoire(int taille)
	{
		int[] coord = new int[2];
		boolean coordOK = false;


		coord[0] = (int) (Math.random()*(taille-1) + 1);
		coord[1] = (int) (Math.random()*(taille-1) + 1);
		coord[0] = (int) (Math.random()*(taille-2) + 1);
		coord[1] = (int) (Math.random()*(taille-2) + 1);

		while (!coordOK)
		{
			if (!(labyrinthe.getCase(coord[0], coord[1]).estTraversable() && labyrinthe.getCase(coord[0], coord[1]).getPersonnage() == null && labyrinthe.getCase(coord[0], coord[1]).getObjet() == null))
			{
				coord[0] = (int) (Math.random()*(taille-1) + 1);
				coord[1] = (int) (Math.random()*(taille-1) + 1);
			}
			else
			{
				coordOK = true;
			}
		}

		return coord;
	}

	public boolean etreGagne()
	{
		return gagne;
	}
	
}
