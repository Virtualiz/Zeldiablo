package monJeu;

public class Monstre extends Personnage
{

	protected int vision;
	
	public Monstre(int a, int o) 
	{
		super(a, o);
		this.attaque=2;
		this.pdv=8;
		this.count = 3;
		this.vision=7;
	}


	@Override
	public String toString() {
		return "Le monstre se trouve en " + this.x +", "+this.y;
	}


	public int countMax(){
		return 5;
	}
	public boolean attaquer(Labyrinthe laby) {
		if(count==0){
			count=countMax();
			count--;
			Heros h = laby.getHeros(x, y);

			if(h != null){
				h.subirDegat(attaque);
				return true;
			}
			else{
				return false;
			}
		}
		count--;
		return false;
		
	}	
	
	public int getVision(){
		return vision;
	}
	
	public String getType(){
		return "MONSTRE";
	}

}
