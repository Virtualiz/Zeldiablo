package monJeu;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import monJeu.Personnage;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

/**
 * un afficheur graphique associe au JeuTest fourni
 * 
 * @author vthomas
 */
public class MonDessin implements DessinJeu {

	/**
	 * constante pour gerer la taille des cases
	 */
	public static int TAILLE_CASE = 28;

	/**
	 * lien vers le jeu a afficher
	 */
	private MonJeu jeu;

	/**
	 * appelle constructeur parent
	 * 
	 * @param j
	 *            le jeutest a afficher
	 */
	public MonDessin(MonJeu j) {
		this.jeu = j;
	}

	/**
	 * dessiner un objet consiste a dessiner sur l'image suivante methode
	 * redefinie de Afficheur
	 */
	private void dessinerObjet(String s, int x, int y, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		switch (s) {
		case "PJ":
			crayon.drawImage(new ImageIcon("projet_zeldiablo/sprite/hero.png").getImage(), x*TAILLE_CASE, y*TAILLE_CASE, null);
			//crayon.setColor(Color.blue);
			//crayon.fillOval(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE,TAILLE_CASE);
			crayon.setColor(Color.black);
			crayon.fillRect(x*TAILLE_CASE-2, y*TAILLE_CASE-5,TAILLE_CASE, 5);
			crayon.setColor(Color.green);
			crayon.fillRect(x*TAILLE_CASE-1, y*TAILLE_CASE-4, (int)(((double)jeu.getHeros().getPv()/Heros.PV_MAX)*(TAILLE_CASE-2)), 3);
			//if(((Heros) jeu.getHeros()).possedeAmulette()){
			//	crayon.setColor(Color.yellow);
			//	crayon.fillOval(x*TAILLE_CASE+5, y*TAILLE_CASE+5, 15, 15);
			//	crayon.setColor(Color.black);
			//	crayon.drawOval(x*TAILLE_CASE+5, y*TAILLE_CASE+5, 15, 15);
			//}

			break;
		case "MUR":
			//crayon.setColor(Color.gray);
			//crayon.fillRect(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE,TAILLE_CASE);
			crayon.drawImage(new ImageIcon("projet_zeldiablo/sprite/wall.png").getImage(), x*TAILLE_CASE, y*TAILLE_CASE, null);
			break;
		case "MONSTRE":
			//crayon.setColor(Color.red);
			//crayon.fillOval(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE,TAILLE_CASE);
			//crayon.drawImage(new ImageIcon("sprite/monster.png").getImage(), x*TAILLE_CASE, y*TAILLE_CASE, null);
			crayon.drawImage(new ImageIcon("projet_zeldiablo/sprite/monster.gif").getImage(), x*TAILLE_CASE, y*TAILLE_CASE, null);
			break;
		case "FANTOME":
			crayon.drawImage(new ImageIcon("projet_zeldiablo/sprite/ghost.png").getImage(), x*TAILLE_CASE, y*TAILLE_CASE, null);
			break;
		case "ENTREE":
			crayon.setColor(Color.cyan);
			crayon.fillRect(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
			crayon.setColor(Color.black);
			crayon.drawRect(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
			//crayon.drawImage(new ImageIcon("sprite/portal.png").getImage(), x*TAILLE_CASE, y*TAILLE_CASE, null);
			break;
		case "AMULETTE":
			//crayon.setColor(Color.yellow);
			//crayon.fillOval(x*TAILLE_CASE+5, y*TAILLE_CASE+5, 15, 15);
			//crayon.setColor(Color.black);
			//crayon.drawOval(x*TAILLE_CASE+5, y*TAILLE_CASE+5, 15, 15);
			crayon.drawImage(new ImageIcon("projet_zeldiablo/sprite/amulet.png").getImage(), x*TAILLE_CASE, y*TAILLE_CASE, null);
			break;
		case "POTION":
			//crayon.setColor(Color.magenta);
			//crayon.fillOval(x*TAILLE_CASE+6,  y*TAILLE_CASE+6, 13, 13);
			//crayon.setColor(Color.black);
			//crayon.drawOval(x*TAILLE_CASE+6, y*TAILLE_CASE+6, 13, 13);
			crayon.drawImage(new ImageIcon("projet_zeldiablo/sprite/potion.png").getImage(), x*TAILLE_CASE, y*TAILLE_CASE, null);
			break;
		case "SOL":
			// dessiner le sol
			crayon.drawImage(new ImageIcon("projet_zeldiablo/sprite/grass.png").getImage(), x*TAILLE_CASE, y*TAILLE_CASE, null);
			break;
		case"FAUX":
			crayon.drawImage(new ImageIcon("projet_zeldiablo/sprite/scythe.gif").getImage(), x*TAILLE_CASE, y*TAILLE_CASE, null);
			break;
		case"EPEE":
			crayon.drawImage(new ImageIcon("projet_zeldiablo/sprite/sword.png").getImage(), x*TAILLE_CASE, y*TAILLE_CASE, null);
			break;
		default:
			throw new AssertionError("objet inexistant");
		}
	}

	/**
	 * methode dessiner redefinie de Afficheur retourne une image du jeu
	 */
	public void dessiner(BufferedImage im) {
		// on sait que c'est un jeuTest
		MonJeu j = (MonJeu) jeu;
		Personnage pj = j.getHeros();
		for (int i = 0 ; i < jeu.getLabyrinthe().TAILLE ; i++)
		{
			for (int k = 0 ; k < jeu.getLabyrinthe().TAILLE ; k++)
			{
				if (!jeu.getLabyrinthe().getCase(i, k).estTraversable())
				{
					dessinerObjet("MUR", i, k, im);
				}
				else
				{
					if (jeu.getLabyrinthe().getCase(i, k).etreEntree())
					{
						dessinerObjet("ENTREE", i, k, im);
					}
					else
					{
						dessinerObjet("SOL", i, k, im);
					}
					if (jeu.getLabyrinthe().getCase(i, k).getObjet() != null)
					{
						if (jeu.getLabyrinthe().getCase(i, k).getObjet().getNomObjet().equals("Amulette"))
						{
							dessinerObjet("AMULETTE", i, k, im);
						}
						if (jeu.getLabyrinthe().getCase(i, k).getObjet().getNomObjet().equals("Potion"))
						{
							dessinerObjet("POTION", i, k, im);
						}
						if (jeu.getLabyrinthe().getCase(i, k).getObjet().getNomObjet().equals("Faux"))
						{
							dessinerObjet("FAUX",i,k,im);
						}
						if(jeu.getLabyrinthe().getCase(i, k).getObjet().getNomObjet().equals("Epee"))
						{
							dessinerObjet("EPEE",i,k,im);
						}
					}
				}
			}
		}
		for(Monstre m : j.getPersonnage()){
			dessinerObjet(m.getType(), m.x, m.y, im);
		};
		this.dessinerObjet("PJ", pj.x, pj.y, im);
	}

	@Override
	public void dessinerFin(BufferedImage im,boolean etreGagne) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		// on sait que c'est un jeuTest
		MonJeu j = (MonJeu) jeu;
		Personnage pj = j.getHeros();
		for (int i = 0 ; i < jeu.getLabyrinthe().TAILLE ; i++)
		{
			for (int k = 0 ; k < jeu.getLabyrinthe().TAILLE ; k++)
			{
				if (!jeu.getLabyrinthe().getCase(i, k).estTraversable())
				{
					dessinerObjet("MUR", i, k, im);
				}
				else
				{
					if (jeu.getLabyrinthe().getCase(i, k).etreEntree())
					{
						dessinerObjet("ENTREE", i, k, im);
					}
					else
					{
						dessinerObjet("SOL", i, k, im);
					}
					if (jeu.getLabyrinthe().getCase(i, k).getObjet() != null)
					{
						if (jeu.getLabyrinthe().getCase(i, k).getObjet().getNomObjet().equals("Amulette"))
						{
							dessinerObjet("AMULETTE", i, k, im);
						}
						if (jeu.getLabyrinthe().getCase(i, k).getObjet().getNomObjet().equals("Potion"))
						{
							dessinerObjet("POTION", i, k, im);
						}
					}
				}
			}
		}
		for(Monstre m : j.getPersonnage()){
			dessinerObjet(m.getType(), m.x, m.y, im);
		};
		this.dessinerObjet("PJ", pj.x, pj.y, im);

		if(etreGagne){
			crayon.setColor(new Color(255,255,255,200));
			crayon.fillRect(0, 0, im.getWidth(), im.getHeight());
			crayon.setColor(Color.black);
			crayon.setFont(new Font("Arial", Font.BOLD, 80));
			crayon.drawString("VICTOIRE !", im.getWidth()/2 - 250, im.getHeight()/2 +40);
		}else
		{
			crayon.setColor(new Color(0,0,0,200));
			crayon.fillRect(0, 0, im.getWidth(), im.getHeight());
			crayon.setColor(Color.red);
			crayon.setFont(new Font("Arial", Font.BOLD, 80));
			crayon.drawString("Wasted !", im.getWidth()/2 - 200, im.getHeight()/2 + 40);
		}

	}

}
