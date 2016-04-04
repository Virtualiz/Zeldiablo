package monJeu;

public abstract class Case {

	private Personnage personnage;
	private boolean entree;
	private Objet objet;
	
	public Personnage getPersonnage() {
		return personnage;
	}

	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}

	public abstract boolean estTraversable();
	
	public boolean etreEntree()
	{
		return entree;
	}
	
	public void setEntree()
	{
		entree = true;
	}
	
	public Objet getObjet()
	{
		return objet;
	}
	
	public void setObjet(Objet o)
	{
		objet = o;
	}
}
