package monJeu;
import java.util.ArrayList;


public class Heros extends Personnage {

	public static final int PV_MAX = 20;
	public static final int TAILLE_INVENTAIRE = 6;
	private Objet[] inventaire;

	public Heros(int a, int o) 
	{
		super(a, o);
		this.pdv=20;
		this.attaque=1;
		inventaire = new Objet[6];
	}

	public String toString(){
		return("Le heros se trouve en " + this.x +", "+this.y+" vie : "+this.pdv);
	}


	public boolean attaquer(Labyrinthe laby) {
		if(count==0){
			int degatsarme = 0;
			Arme arme = null;
			for(int i =0; i<6; i++){
				if(inventaire[i] != null && (inventaire[i]).getType().equals("Arme")){
					arme = (Arme) inventaire[i];
					if(arme.getDegats()> degatsarme)
						degatsarme = arme.getDegats();
				}
			}
			count = countMax();
			ArrayList<Monstre> liste = laby.getMonstres(x,y);
			for(Monstre m : liste){
				m.subirDegat(attaque+degatsarme);
			}
		}
		count--;
		return true;
	}

	public boolean possedeAmulette(){
		boolean possede = false;

		for(int i = 0 ; i < Heros.TAILLE_INVENTAIRE ; i++)
		{
			if (inventaire[i] != null)
			{
				if (inventaire[i].getNomObjet().equals("Amulette"))
				{
					possede = true;
					break;
				}
			}
		}
		return possede;
	}

	public void ramasser(Labyrinthe lab)
	{
		if (lab.getCase(x, y).getObjet() != null)
		{
			int place = -1;
			int i = 0;
			boolean trouver = false;
			while (i < Heros.TAILLE_INVENTAIRE && !trouver)
			{
				if (inventaire[i] == null)
				{
					place = i;
					trouver = true;
				}
				else
				{
					i++;
				}
			}

			if (place != -1)
			{
				ajouterObjet(lab.getCase(x, y).getObjet(), place);
				lab.getCase(x, y).setObjet(null);
			}
		}
	}


	public void ajouterObjet(Objet obj, int place)
	{
		inventaire[place] = obj;
	}

	public void consommer(int place)
	{
		if (inventaire[place] != null)
		{
			boolean utiliser = inventaire[place].etreUtilise(this);
			if (utiliser)
			{
				inventaire[place] = null;
			}
		}
	}

	public Objet getInventaire(int place)
	{
		return inventaire[place];
	}

	public String getType(){
		return "PJ";
	}

}

