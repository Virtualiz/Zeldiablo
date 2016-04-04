package moteurJeu;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * cree une interface graphique avec son controleur et son afficheur
 * @author Graou
 *
 */
public class InterfaceGraphique  {
	
	/**
	 * panel lie a la JFrame
	 */
	private JPanel jp;
	
	/**
	 * le Panel qui affiche le jeu
	 */
	private PanelDessin panel;
	
	/**
	 * panel avec les infos
	 */
	private PanelInfos infos;
	
	/**
	 * le controleur lie a la JFrame
	 */
	private Controleur controleur;
	
	/**
	 * la construction de l'interface grpahique
	 * - construit la JFrame
	 * - construit les Attributs
	 * 
	 * @param afficheurUtil l'afficheur a utiliser dans le moteur
	 * 
	 */
	public InterfaceGraphique(DessinJeu afficheurUtil,int x,int y, Jeu jeu)
	{
		//creation JFrame
		JFrame f=new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// creation panels
		jp = new JPanel();
		jp.setPreferredSize(new Dimension(x+250,y));
		
		infos = new PanelInfos(jeu,250,y);
		
		this.panel=new PanelDessin(x, y,afficheurUtil);

		jp.setLayout(new BorderLayout());
		jp.add(panel,BorderLayout.CENTER);
		jp.add(infos,BorderLayout.EAST);
		
		f.setContentPane(jp);
		
		//ajout du controleur
		Controleur controlleurGraph=new Controleur();
		this.controleur=controlleurGraph;
		jp.addKeyListener(controlleurGraph);	
		
		//recuperation du focus
		f.pack();
		f.setVisible(true);
		f.getContentPane().setFocusable(true);
		f.getContentPane().requestFocus();
	}
	
	
	/**
	 * retourne le controleur de l'affichage construit
	 * @return
	 */
	public Controleur getControleur() {
		return controleur;
	}

	/**
	 * demande la mise a jour du dessin
	 */
	public void dessiner() {
		this.panel.dessinerJeu();
		this.infos.actualiser();
	}


	public void dessinerFin(boolean etreGagne) {
		panel.dessinerFin(etreGagne);
		
	}


	

	
}
