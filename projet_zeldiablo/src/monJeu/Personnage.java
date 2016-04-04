package monJeu;

import moteurJeu.Commande;

public abstract class Personnage 
{

	protected int x;
	protected int y;
	protected int pdv;
	protected int porte;
	protected int attaque;
	protected int count;
	
	protected static final int LIM_X = Labyrinthe.TAILLE;
	protected static final int LIM_Y = Labyrinthe.TAILLE;
	
	public void subirDegat(int degat){
		this.pdv = pdv-degat;
		if (pdv < 0) pdv = 0;
	}
	
	public abstract boolean attaquer(Labyrinthe laby);
	
	public boolean etreAPorte(Personnage p){
		return (this.y == p.y && (this.x == p.x-1 || this.x == p.x+1) )||(this.x == p.x && (this.y == p.y-1 || this.y == p.y+1));
	}
	
	public Personnage(int a, int o)
	{
		count=0;
		if (!(a > Labyrinthe.TAILLE || a < 0 || o > Labyrinthe.TAILLE || o < 0))
		{
			x = a;
			y = o;
		}
		else
		{
			x = Labyrinthe.TAILLE/2;
			y = Labyrinthe.TAILLE/2;
		}

	}
	
	public int countMax(){
		return 3;
	}

	public void deplacer(Commande c, Labyrinthe lab){

		if (count==0){ 
			count = countMax();

			int ancien_x = x;
			int ancien_y = y;

			if (c.gauche)
			{
				this.x--;
				if (this.x < 0)
					this.x = 0;
			}

			if (c.droite)
			{
				this.x++;
				if (this.x >LIM_X)
					this.x = LIM_X;
			}
			if (c.bas)
			{
				this.y++;
				if (this.y > LIM_Y)
					this.y = LIM_Y;
			}


			if (c.haut)
			{
				this.y--;
				if (this.y < 0)
					this.y = 0;

			}

			Case casePrecedente = lab.getCase(ancien_x, ancien_y);
			casePrecedente.setPersonnage(null);

			Case caseSuivante = lab.getCase(x, y);
			caseSuivante.setPersonnage(this);
		}
		count --;

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
		
		boolean res = lab.getCase(nx, ny).estTraversable() && lab.getCase(nx, ny).getPersonnage()==null;
		return res;
		
	}
	
	public abstract String toString();

	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getPv()
	{
		return this.pdv;
	}
	
	public abstract String getType();

}
