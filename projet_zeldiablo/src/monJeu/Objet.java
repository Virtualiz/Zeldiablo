package monJeu;

public interface Objet {
	
	public String getNomObjet();
	
	public boolean etreUtilise(Heros h); // renvoi true si l'objet doit être consommé

	public Object getType();
	
}
