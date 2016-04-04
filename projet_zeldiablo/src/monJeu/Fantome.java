package monJeu;

import moteurJeu.Commande;

public class Fantome extends Monstre {

	public Fantome(int a, int o) {
		super(a, o);
		this.vision=8;
		this.attaque=1;
	}
	
	public boolean deplacerPossible(Commande c, Labyrinthe lab)
	{	
		int nx=x,ny=y;
		
		if (c.haut==true)
		{
			ny--;
		}
		
		if (c.bas==true)
		{
			ny++;
		}
		
		if (c.gauche==true)
		{
			nx--;
		}
		
		if (c.droite==true)
		{
			nx++;
		}
		
		boolean res;
		if(nx >= Labyrinthe.TAILLE-1 || nx <= 0 || ny >= Labyrinthe.TAILLE-1|| ny<= 0){
			res = false;
		}
		else{
			res = lab.getCase(nx, ny).getPersonnage()==null;
		}
		
		return res;
		
	}
	
	public String getType(){
		return "FANTOME";
	}

}
