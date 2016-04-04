package moteurJeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import monJeu.Heros;
import monJeu.MonJeu;

public class PanelInfos extends JPanel{

	private int width, height;
	private MonJeu jeu;
	private JLabel obj1,obj2,obj3,obj4,obj5,obj6;
	private PanelItem inv1,inv2,inv3,inv4,inv5,inv6;
	private JPanel vie;
	private String pvHeros;
	
	public PanelInfos(final Jeu jeu, int x, int y) {
		super();
		this.jeu = (MonJeu) jeu;
		this.setPreferredSize(new Dimension(x, y));
		this.width = x;
		this.height = y;
		this.setLayout(new GridLayout(10,1));
		
		pvHeros ="0/0";
		vie = new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.setColor(Color.black);
				g.fillRect(0, 0, vie.getWidth(), vie.getHeight());
				g.setColor(Color.green);
				g.fillRect(0, 0, (int)(( (double)((Heros)((MonJeu)jeu).getHeros()).getPv()/Heros.PV_MAX)*vie.getWidth()), vie.getHeight());
				g.setColor(Color.white);
				g.drawRect(0, 0, vie.getWidth()-1, vie.getHeight()-1);
				
				
				
				g.setColor(Color.white);
				g.setFont(new Font("Arial", Font.BOLD, vie.getHeight()-20));
				g.drawString(pvHeros, vie.getWidth()/2-80, vie.getHeight()/2+20);
			}	
		};
		vie.setPreferredSize(new Dimension(x,y/10));
		this.add(vie);
		
		JPanel j1= new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				this.setBackground(Color.black);
				g.setColor(Color.white);
				g.drawRect(0, 0, this.getWidth(), this.getHeight());
			}
		};
		this.add(j1);
		
		inv1=new PanelItem("1");
		this.add(inv1);
		inv2=new PanelItem("2");
		this.add(inv2);
		inv3=new PanelItem("3");
		this.add(inv3);
		inv4=new PanelItem("4");
		this.add(inv4);
		inv5=new PanelItem("5");
		this.add(inv5);
		inv6=new PanelItem("6");
		this.add(inv6);
		
		this.setBackground(Color.black);
		this.setLayout(new GridLayout(14,1));

	}
	
	private class PanelItem extends JPanel{
		
		String raccourcie;
		Image im;
		String name;
		
		PanelItem(String rac){
			super();
			raccourcie = rac;
			name="";
		}
		
		public void setObjet(String nomObj){

			name=nomObj;
			switch(nomObj){
			case "Potion":
				im=new ImageIcon("projet_zeldiablo/sprite/potion.png").getImage();
				break;
			case "Amulette":
				im=new ImageIcon("projet_zeldiablo/sprite/amulet.png").getImage();
				break;
			case "Faux":
				im=new ImageIcon("projet_zeldiablo/sprite/scythe.gif").getImage();
				break;
			case "Epee":
				im=new ImageIcon("projet_zeldiablo/sprite/sword.png").getImage();
				break;
			default:
				im=null;
				break;
			}
			
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			this.setBackground(Color.black);
			g.setColor(Color.white);
			g.drawRect(0, 0, this.getWidth(), this.getHeight());
			g.drawRect(1, 1, this.getWidth()-2, this.getHeight()-2);
			g.drawRect(3, 3, this.getWidth()-6, this.getHeight()-6);
			g.drawImage(im, (this.getHeight()-8)/2-12, (this.getHeight()-8)/2-16, null);
			g.drawRect(3, 3, (this.getHeight()-8)/2+32+3, this.getHeight()-6);
			
			g.setFont(new Font("Arial", Font.BOLD, this.getHeight()/3));
			g.drawString(name, getWidth() - (getWidth()-((this.getHeight()-8)/2+45)) , 5*(getHeight()/8));
			
			g.setColor(new Color(255,255,255, 200));
			g.setFont(new Font("Arial", Font.BOLD, 32 ));
			g.drawString(raccourcie, (this.getHeight()-8)/2-24, (this.getHeight()-8)/2+16);
			
			
		}	
	
	}

	public void actualiser() {
		
		repaint();
		vie.repaint();
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		
		pvHeros = ((Heros) jeu.getHeros()).getPv()+"/"+Heros.PV_MAX;
		inv1.repaint();
		inv2.repaint();
		inv3.repaint();
		inv4.repaint();
		inv5.repaint();
		inv6.repaint();
		
		if(((Heros) jeu.getHeros()).getInventaire(0)!=null){
			inv1.setObjet(((Heros) jeu.getHeros()).getInventaire(0).getNomObjet());
		}else inv1.setObjet("");
		
		if(((Heros) jeu.getHeros()).getInventaire(1)!=null){
			inv2.setObjet(((Heros) jeu.getHeros()).getInventaire(1).getNomObjet());
		}else inv2.setObjet("");
		
		if(((Heros) jeu.getHeros()).getInventaire(2)!=null){
			inv3.setObjet(((Heros) jeu.getHeros()).getInventaire(2).getNomObjet());
		}else inv3.setObjet("");
		
		if(((Heros) jeu.getHeros()).getInventaire(3)!=null){
			inv4.setObjet(((Heros) jeu.getHeros()).getInventaire(3).getNomObjet());
		}else inv4.setObjet("");
		if(((Heros) jeu.getHeros()).getInventaire(4)!=null){
			inv5.setObjet(((Heros) jeu.getHeros()).getInventaire(4).getNomObjet());
		}else inv5.setObjet("");
		if(((Heros) jeu.getHeros()).getInventaire(5)!=null){
			inv6.setObjet(((Heros) jeu.getHeros()).getInventaire(5).getNomObjet());
		}else inv6.setObjet("");
		
	}

}
