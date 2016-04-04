package moteurJeu;

/**
 * permet de representer une commande de l'utilisateur
 * 
 * @author vthomas
 *
 */
public class Commande {

	/**
	 * boolean representant la commande de l'utilisateur
	 */
	public boolean gauche;
	public boolean droite;
	public boolean haut;
	public boolean bas;
	public boolean attaque;
	public boolean ramasse;
	public boolean consObj1;
	public boolean consObj2;
	public boolean consObj3;
	public boolean consObj4;
	public boolean consObj5;
	public boolean consObj6;
	

	public Commande()
	{
		
	}
	
	/**
	 * constructeur par copie
	 * copie la commande pour en creer une nouvelle
	 * @param commandeACopier
	 */
	public Commande(Commande commandeACopier)
	{
		this.bas=commandeACopier.bas;
		this.haut=commandeACopier.haut;
		this.gauche=commandeACopier.gauche;
		this.droite=commandeACopier.droite;
		this.attaque=commandeACopier.attaque;
		this.ramasse=commandeACopier.ramasse;
		this.consObj1=commandeACopier.consObj1;
		this.consObj2=commandeACopier.consObj2;
		this.consObj3=commandeACopier.consObj3;
		this.consObj4=commandeACopier.consObj4;
		this.consObj5=commandeACopier.consObj5;
		this.consObj6=commandeACopier.consObj6;
		
	}
	
}
