package monJeu;

public class Amulette implements Objet
{
	public String getNomObjet()
	{
		return "Amulette";
	}

	@Override
	public boolean etreUtilise(Heros h) {
		return false;
	}
	
	public String getType(){
		return "Amulette";
	}
}
