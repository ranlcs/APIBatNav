//by Lcs
// 5 novembre 2018 : Case,Bateau1,2,3,4 : constructeur(sans/Case) methode aCoulee
// 6 novembre 2018 : methode liee,sontLiee, ; class Flotte: consttructeur, estDetruite
// 7 novembre 2018 : AjouterBateau->setVisible(false):ne pas compiler
// 8 novembre 2018 : methode remplirFlotte(sans lieext:condition)+afficherCase/Bateau + MsgErreur
// 9 novembre 2018 : lieBateau + flotteLie. idee: ne pas utiliser while pour remplirFlotte en utilisant des incrementations dans la fentre avec boutons
// 12 novembre 2018 : changer remplirFlotte pour enlever while
// 13 novembre 2018 : essai d'interface avec FramePrinc.java + essai d'assemblage
// 14 novembre 2018 : amelioration de FramePrinc.java + 2eme essai d'assemblage (idee utiliser des buttons pour passer à l'étape suivante(remplir flotte J1,J2)
// 15 novembre 2018 : debut d'assemblage, premier plan,InterfaceJ du page2 ne s'affiche pas????
// 16 novembre 2018 : probleme resolu par un layout + deuxieme page probleme avec insertion des bateaux(incrementation,flotte toujours liée voir APIBatNavS
// 17 novembre 2018 : recherche en vain du probleme d'insertion(essayer S en ajoutant une variable flotte
// 19 novembre 2018 : recherche du probleme valide est execute 4fois rearrangementdu prog=>3fois (essayer de rearanger le programme.reste plus que 1 a enlevé
// 20 novembre Z018 : fallait changer fenetre en non statique + pb:toucher/Couler
// 21 novembre 2018 : pb valeur de flotte ne sont pas transmis avec remplirFlotte + pb toucher/Couler + isequals(Case) et equals(Bateau)
// 22 novembre 2018 : erreur sur valeur de temp1(x,x)au lieu de (x,y)
// 24 novembre 2018 : ajout class Gagne et utilisation + essai eau autour couler(les initialisations
// 26 novembre 2018 : incoherence entre tab[i][j] et leur valeur=>erreur sur couler autour voir initialiser
// 27 novembre 2018 : resolu(couler autour,entrer rien boucle sur fin) + tour + bateau restant  (pour gagne utiliser un timer au lieu des threads pour les feu d'arts)
//novembre-decembre 2018 : essai de FeuArt
//14 decembre 2018 : ajout du feu d'artifice de fin
//2 janvier 2019 : ajout du aide avec la touche F1
//3 janvier 2019 : ajout du var boolean pret
//11 janvoer 2019 : ajout du menu joueur1,2
//14 janvier 2019 : ajout des ecouteMenu et de recommencer(avec erreur)
//15 janvier 2019 : rectification de recommencer

//remarque:apprendre a inserer une classe pour eviter de creer beaucoup de frame et pour la lisibilité du code

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Vector;

class Case{
	int x;
	int y;
	boolean toucher;

	Case(){
		toucher = false;
		x=-7;
		y=-7;
	}

	Case(int x,int y){
		this.x=x;
		this.y=y;
		toucher = false;
	}

	void setX(int x){this.x = x;}
	void setY(int y){this.y = y;}
	void setXY(int x,int y){this.x = x;		this.y = y;}
	void setToucher(boolean t){toucher = t;}

	int getX(){return x;}
	int getY(){return y;}
	boolean getToucher(){return toucher;}

	boolean liee(Case b){
		return ((Math.abs(x-b.x)==1 && (Math.abs(y-b.y)==1 || y-b.y==0)) ||
					(Math.abs(x-b.x)==0 && (Math.abs(y-b.y)==1)));
	}

	void afficherCase(){
		System.out.println("       ("+x+","+y+")");
	}

	boolean isequals(Case b){
		return(x==b.x && y==b.y);
	}

	void equals(Case b){
		this.x = b.x;
		this.y = b.y;
	}
}

interface Bateau {
	public boolean aCoulee();
	public boolean sontLiee();
	public Case getB1();
	public Case getB2();
	public Case getB3();
	public Case getB4();
	public void afficherBateau();
	public boolean lieBateau(Bateau b);
	public boolean appartient(Case b);
	public void equals(Bateau b);
	public void initialiser();
}
class Bateau1 implements Bateau{
	protected Case b1;

	Bateau1(){
		b1 = new Case();
	}

	Bateau1(Case p){
		b1 = p;
	}

	public Case getB1(){return b1;}
	public Case getB2(){return new Case(-14,-14);}
	public Case getB3(){return new Case(-14,-14);}
	public Case getB4(){return new Case(-14,-14);}

	public boolean aCoulee(){
		return b1.getToucher();
	}

	public boolean sontLiee(){return true;}

	public void afficherBateau(){
		b1.afficherCase();
	}

	public boolean lieBateau(Bateau b){
		return ((b1.liee(b.getB1())) || (b1.liee(b.getB2())) || (b1.liee(b.getB3())) || (b1.liee(b.getB4())));
	}

	public boolean appartient(Case b){
		if(b1.isequals(b))
			b1.setToucher(true);
		return b1.isequals(b);
	}

	public void equals(Bateau b){
		b1.equals(b.getB1());
		b1.setToucher(b.getB1().getToucher());
	}
	public void initialiser(){
		b1.setXY(-14,-14);
		b1.setToucher(false);
	}
}
class Bateau2 extends Bateau1{
	protected Case b2;

	Bateau2(){
		super();
		b2 = new Case();
	}

	Bateau2(Case b1,Case b2){
		super(b1);
		this.b2=b2;
	}

	public Case getB1(){return super.getB1();}
	public Case getB2(){return b2;}
	public Case getB3(){return new Case(-14,-14);}
	public Case getB4(){return new Case(-14,-14);}

	public boolean aCoulee(){
		return ((b2.getToucher()) && (super.aCoulee()));
	}

	public boolean sontLiee(){
		return (b2.liee(b1));
	}

	public void afficherBateau(){
		System.out.println("        ("+b1.getX()+","+b1.getY()+");("+b2.getX()+","+b2.getY()+")");
	}

	public boolean lieBateau(Bateau b){
		return (super.lieBateau(b) && ((b2.liee(b.getB1())) || (b2.liee(b.getB2())) || (b2.liee(b.getB3())) || (b2.liee(b.getB4()))));
	}

	public boolean appartient(Case b){
		if(b2.isequals(b))
			b2.setToucher(true);
		return (super.appartient(b) || b2.isequals(b));
	}

	public void equals(Bateau b){
		super.equals(b);
		b2.equals(b.getB2());
		b2.setToucher(b.getB2().getToucher());
	}

	public void initialiser(){
		super.initialiser();
		b2.setXY(-14,-14);
		b2.setToucher(false);
	}
}
class Bateau3 extends Bateau2{
	protected Case b3;

	Bateau3(){
		super();
		b3 = new Case();
	}

	Bateau3(Case b1,Case b2,Case b3){
		super(b1,b2);
		this.b3 = b3;
	}

	public Case getB1(){return super.getB1();}
	public Case getB2(){return super.getB2();}
	public Case getB3(){return b3;}
	public Case getB4(){return new Case(-14,-14);}

	public boolean aCoulee(){
		return((super.aCoulee()) && (b3.getToucher()));
	}

	public boolean sontLiee(){
		return (super.sontLiee() && ((b3.liee(b2)) || (b3.liee(b1))));
	}

	public void afficherBateau(){
		System.out.println( "        ("+b1.getX()+","+b1.getY()+");("+b2.getX()+","+b2.getY()+");("+b3.getX()+","+b3.getY()+")");
	}

	public boolean lieBateau(Bateau b){
		return (super.lieBateau(b) && ((b3.liee(b.getB1())) || (b3.liee(b.getB2())) || (b3.liee(b.getB3())) || (b3.liee(b.getB4()))));
	}

	public boolean appartient(Case b){
		if(b3.isequals(b))
			b3.setToucher(true);
		return (super.appartient(b) || b3.isequals(b));
	}

	public void equals(Bateau b){
		super.equals(b);
		b3.equals(b.getB3());
		b3.setToucher(b.getB3().getToucher());
	}

	public void initialiser(){
		super.initialiser();
		b3.setXY(-14,-14);
		b3.setToucher(false);
	}
}
class Bateau4 extends Bateau3{
	protected Case b4;
	Bateau4(){
		super();
		b4 = new Case();
	}

	Bateau4(Case b1,Case b2,Case b3,Case b4){
		super(b1,b2,b3);
		this.b4 = b4;
	}

	public Case getB1(){return super.getB1();}
	public Case getB2(){return super.getB2();}
	public Case getB3(){return super.getB3();}
	public Case getB4(){return b4;}

	public boolean aCoulee(){
		return (super.aCoulee() && b4.getToucher());
	}

	public boolean sontLiee(){
		return (super.sontLiee() && ((b4.liee(b1)) || (b4.liee(b2)) || (b4.liee(b3))));
	}

	public void afficherBateau(){
		System.out.println( "        ("+b1.getX()+","+b1.getY()+");("+b2.getX()+","+b2.getY()+");("+b3.getX()+","+b3.getY()+");("+b4.getX()+","+b4.getY()+")");
	}

	public boolean lieBateau(Bateau b){
		return (super.lieBateau(b) && ((b4.liee(b.getB1())) || (b4.liee(b.getB2())) || (b4.liee(b.getB3())) || (b4.liee(b.getB4()))));
	}

	public boolean appartient(Case b){
		if(b4.isequals(b))
			b4.setToucher(true);
		return (super.appartient(b) || b4.isequals(b));
	}

	public void equals(Bateau b){
		super.equals(b);
		b4.equals(b.getB4());
		b4.setToucher(b.getB4().getToucher());
	}

	public void initialiser(){
		super.initialiser();
		b4.setXY(-14,-14);
		b4.setToucher(false);
	}
}

class Erreur extends JDialog{
	JButton valide;
	JLabel msg;
	Erreur(){
		setLayout(new GridLayout(0,1));
		setSize(300,150);
		setLocation(380,280);
		setTitle("ATTENTION");
		setVisible(false);
		msg = new JLabel("");
		add(msg);
		JPanel j = new JPanel();
		valide = new JButton("OK");
		valide.addActionListener(new Fermer());
		add(j);
		j.add(valide);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	void afficherErreur(String t){
		msg.setText(t);
		setVisible(true);
		requestFocus();
		addKeyListener(new Ferme());
		Timer temp = new Timer(10000,new Fermer());
		temp.setRepeats(false);
		temp.start();
	}

	class Fermer implements ActionListener{
		public void actionPerformed(ActionEvent a){
			setVisible(false);
		}
	}

	class Ferme extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
				setVisible(false);
		}
	}
}

class AjouterBateau extends JFrame{
	TextField donne;
	JButton valide;

	AjouterBateau(){
		super();
		setTitle("Entrer votre nom:");
		setLocation(380,120);
		JPanel P1 = new JPanel();
		JPanel P2 = new JPanel();
		JLabel aEntre = new JLabel("");
		donne = new TextField("",10);
		valide = new JButton("Recommencer");
		setSize(300,150);
		setLayout(new GridLayout(0,1));
		add(aEntre);
		P1.add(donne);
		P2.add(valide);
		add(P1);
		add(P2);
		setVisible(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	JButton getValide(){return valide;}
	TextField getDonne(){return donne;};
}

class Flotte{
	AjouterBateau fenetre = new AjouterBateau();
	static Erreur msgErreur = new Erreur();
	public boolean pret=false;
	Bateau[][] flotte={ {new Bateau1(),new Bateau1(),new Bateau1(),new Bateau1()},
						{new Bateau2(),new Bateau2(),new Bateau2()},
						{new Bateau3(),new Bateau3()},
						{new Bateau4()}
					};
	String nom;
	int evolL;
	int evolC;

	Flotte(String ana){
		nom = ana;
		fenetre.getDonne().addKeyListener(new Enregistre());
		fenetre.getValide().addActionListener(new Enregis());
		evolL=-1;
		evolC=0;
	}

	Flotte(){
		fenetre.getDonne().addKeyListener(new Enregistre());
		fenetre.getValide().addActionListener(new Enregis());
		evolL=-1;
		evolC=0;
	}
	void initialiserFlotte(){
		for(int i=0;i<flotte.length;i++)
			for(int j=0;j<flotte[i].length;j++){
				flotte[i][j].initialiser();
			}
		evolL=0;
		evolC=0;
		fenetre.setTitle(nom+" Bateau"+(evolL+1)+(": ")+(evolC+1));
		fenetre.getDonne().setText("");
	}

	boolean estDetruite(){
		for(int i=0;i<flotte.length;i++)
			for(int j=0;j<flotte[i].length;j++){
				if(!flotte[i][j].aCoulee())
					return false;
				}
		return true;
	}

	String getNom(){return nom;}
	void setNom(String anarana){nom = anarana;}
	AjouterBateau getFenetre(){return fenetre;}

	void remplirFlotte(){
		fenetre.setVisible(true);
	}

	void afficherFlotte(){
		for(int i=0;i<flotte.length;i++)
			for(int j=0;j<flotte[i].length;j++)
			{
				System.out.println("Bateau" + (i+1) + " numero "+ (j+1) + ":");
					flotte[i][j].afficherBateau();
			}
	}

	boolean flotteLie(){
		Bateau temp[]={flotte[0][0],flotte[0][1],flotte[0][2],flotte[0][3],
						flotte[1][0],flotte[1][1],flotte[1][2],
						flotte[2][0],flotte[2][1],
						flotte[3][0]
					};
		for(int i=0;i<temp.length;i++)
			for(int j=i+1;j<temp.length;j++)
				if(temp[i].lieBateau(temp[j]))
					return true;
		return false;
	}

	class Enregistre implements KeyListener{
		public void keyPressed(KeyEvent k){
			if(k.getKeyCode()==KeyEvent.VK_ENTER){
				{
					if(evolL==-1)
					{
						nom=fenetre.getDonne().getText();
						evolL=0;
						fenetre.setTitle(nom+" Bateau"+(evolL+1)+(": ")+(evolC+1));

					}
					else {

						String temp = fenetre.getDonne().getText()+"00000000000000000";

						if((temp.charAt(0)>64 && temp.charAt(0)<79 && temp.charAt(1)<58)&&(temp.charAt(3)<79 && temp.charAt(4)<58)&&(temp.charAt(6)<79 && temp.charAt(7)<58)&&(temp.charAt(9)<79 && temp.charAt(10)<58)){
							Bateau4 btemp = new Bateau4(new Case(temp.charAt(0)-64,temp.charAt(1)-48),
													new Case(temp.charAt(3)-64,temp.charAt(4)-48),
													new Case(temp.charAt(6)-64,temp.charAt(7)-48),
													new Case(temp.charAt(9)-64,temp.charAt(10)-48));
							flotte[evolL][evolC].equals(btemp);
							if(!flotte[evolL][evolC].sontLiee())
								msgErreur.afficherErreur("Desole, les Bateaux"+(evolL+1)+" doivent être liées.Recommenecez");
							else{
								evolC++;
							}
							if(evolC==flotte[evolL].length){
								evolL++;
								evolC=0;
							}
							if(evolL==flotte.length && !flotteLie()){
								evolL=-1;
								fenetre.setTitle("Entrer votre nom:");
								fenetre.setVisible(false);
								msgErreur.afficherErreur("Bravo "+nom+" votre flotte est prête à attaquer");
								pret=true;
							}
							else if(evolL==flotte.length && flotteLie()){
								msgErreur.afficherErreur("Desole, quelques uns des bateaux1,2,3,4 sont liés. Recommencez il y a une erreur");
								evolL=0;
								evolC=0;
								pret=false;
							}
							fenetre.setTitle(nom+" Bateau"+(evolL+1)+(": ")+(evolC+1));
						}
						else
							msgErreur.afficherErreur("Vous êtes en dehors de la grille.Recommencez");
					}
					fenetre.getDonne().setText("");
				}
			}
		}

		public void keyTyped(KeyEvent j){}
		public void keyReleased(KeyEvent j){}

	}


	class Enregis implements ActionListener{
		public void actionPerformed(ActionEvent e){
			initialiserFlotte();
			fenetre.getDonne().requestFocus();
		}
	}

	Bateau[][] getFlotte(){return flotte;};

	void setFlotte(Flotte g){
		nom=g.nom;
		for(int i=0;i<flotte.length;i++)
			for(int j=0;j<flotte[i].length;j++)
				flotte[i][j].equals(g.flotte[i][j]);
	};
}

class Case2 extends JButton{
	Point D;
	String text;

	Case2(int x,int y,String t){
		D = new Point(x,y);
		setText(t);
		setSize(30,30);
		setBorder(BorderFactory.createLineBorder(Color.black,1));
		setVisible(true);
	}

	Case2(int x,int y){
		this(x,y,"  ");
	}

	Point getD(){return D;}
}

class PanG extends JPanel{
	PanG(LayoutManager l){
		super(l);
	}

	public Dimension getPreferredSize(){
		return new Dimension(150,0);
	}
}

class Gagne extends JFrame{

	Gagne(String nom){
		setTitle("Congratulation "+nom);
		setLocation(-5,-5);
		setSize(1032,750);
		setBackground(Color.black);
		setVisible(false);
		add(new Explosion());
	}
	void setNomJoueur(String nom){
		setTitle("Felicitation "+nom+". Tu as gagné");
	}

	public void setVisible(boolean t){
		setBackground(Color.black);
		super.setVisible(t);
	}
}

class InterfaceJ extends JPanel{
	public static int tour=0;
	static String[] lettre={"A","A","B","C","D","E","F","G","H","I","J","K","L","M","N"};
	final int numero;
	Color[] couleur={Color.blue,Color.green,Color.red};

	int[] nombreRestant = {4,3,2,1};
	JLabel[] InfoJ = new JLabel[4];
	Case2 tab[][] = new Case2[10][15];
	Flotte f;
	JLabel nom = new JLabel("Grille de : ");
	Gagne fin;

	Clique click = new Clique();
	InterfaceJ(Flotte l,int num){
		setVisible(true);
		numero = num;
		f=l;
		fin = new Gagne(f.getNom());
		setLayout(new BorderLayout());
		PanG P1 = new PanG(new GridLayout(12,1));
		JPanel P2 = new JPanel(new GridLayout(0,15));
		JPanel P3 = new JPanel(new FlowLayout());
		String temp = "Grille de "+f.getNom();
		P1.add(nom);
		P1.add(new JLabel("Bateau Restant:"),"Center");
		for(int i=0;i<4;i++)
		{
			InfoJ[i] = new JLabel(" Bateau"+(i+1)+":  "+nombreRestant[i]);
			P1.add(InfoJ[i]);
		}
		P2.setSize(150,100);
		for(int i=0;i<10;i++)
			for(int j=0;j<15;j++)
				if(i==0&&j!=0){
					tab[i][j] = new Case2(j,i," "+lettre[j]);
					tab[i][j].setOpaque(true);
					tab[i][j].setBackground(Color.white);
					P2.add(tab[i][j]);
				}
				else if(i!=0&&j==0){
					tab[i][j] = new Case2(j,i," "+Integer.toString(i));
					tab[i][j].setOpaque(true);
					tab[i][j].setBackground(Color.white);
					P2.add(tab[i][j]);
				}
				else {
					tab[i][j] = new Case2(j,i);
					tab[i][j].setOpaque(true);
					tab[i][j].setBackground(Color.white);
					P2.add(tab[i][j]);
				}
		P3.add(new JLabel(f.getNom()));
		add(P3,"North");
		add(P1,"West");
		add(P2,"Center");
	}

	InterfaceJ(int num){
		this(new Flotte(),num);
	}

	public Dimension getPreferredSize(){
		return new Dimension(350,300);
	}

	void initialiserAvec(Flotte g){
		f.setFlotte(g);
		nom.setText("Grille de :"+g.getNom());
		for(int i=1;i<tab.length;i++)
			for(int j=1;j<tab[i].length;j++)
				tab[i][j].addMouseListener(click);
	}

	void initialiserSans(Flotte g){
		f.setFlotte(g);
		nom.setText("Grille de :"+g.getNom());
		for(int i=1;i<tab.length;i++)
			for(int j=1;j<tab[i].length;j++)
				tab[i][j].removeMouseListener(click);
		for(int i=0;i<4;i++)
			nombreRestant[i] = 4-i;
	}

	void initialiserTab(){
		String[] lettre={"A","A","B","C","D","E","F","G","H","I","J","K","L","M","N"};
		for(int i=0;i<10;i++)
			for(int j=0;j<15;j++){
				tab[i][j].setBackground(Color.white);
				if(i==0&&j!=0){
					tab[i][j].setText(lettre[j]);
					tab[i][j].setBackground(Color.white);
				}
				else if(i!=0&&j==0){
					tab[i][j].setText(Integer.toString(i));
					tab[i][j].setBackground(Color.white);
				}
				else
					tab[i][j].setText("");
			}
		for(int i=0;i<4;i++)
			nombreRestant[i] = 4-i;
	}

	 Flotte getF(){return f;}
	 Gagne getFin(){return fin;}
	 JLabel setNom(){return nom;}
	 Case2[][] getTab(){return tab;}

	 void setCouleur(int k,Color t){
		if(t!= couleur[(k+1)%3] && t!=  couleur[(k+2)%3]){
			for(int i=0;i<10;i++)
				for(int j=0;j<15;j++)
					if(tab[i][j].getBackground()==couleur[k])
						tab[i][j].setBackground(t);
			couleur[k]=t;
		}
	}

	class Clique extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			if(e.getButton()==MouseEvent.BUTTON1){
				if(tour==numero){
					Case2 temp = (Case2) e.getSource();
					temp.setOpaque(true);
					Case temp1 = new Case(temp.getD().x,temp.getD().y);
					int posI=-1;
					int posJ=-1;
					for(int i=0;i<f.getFlotte().length;i++)
						for(int j=0;j<f.getFlotte()[i].length;j++){
							if(f.getFlotte()[i][j].appartient(temp1)){
								posI=i;
								posJ=j;
							}
						}
					if(posI<f.getFlotte().length && posI>=0){
						if(f.getFlotte()[posI][posJ].aCoulee()){
							temp.setBackground( couleur[2]);
							temp.setText("Couler");
							if(nombreRestant[posI]>0)
								nombreRestant[posI]--;
							InfoJ[posI].setText(" Bateau"+(posI+1)+":  "+nombreRestant[posI]);
							switch(posI){
								case 3:{
									for(int i=f.getFlotte()[posI][posJ].getB4().getX()-1;i<=f.getFlotte()[posI][posJ].getB4().getX()+1;i++)
										for(int j=f.getFlotte()[posI][posJ].getB4().getY()-1;j<=f.getFlotte()[posI][posJ].getB4().getY()+1;j++)
											if((i>0 && i<15) && (j>0 && j<10))
												tab[j][i].setBackground( couleur[0]);
								}
								case 2:{
									for(int i=f.getFlotte()[posI][posJ].getB3().getX()-1;i<=f.getFlotte()[posI][posJ].getB3().getX()+1;i++)
										for(int j=f.getFlotte()[posI][posJ].getB3().getY()-1;j<=f.getFlotte()[posI][posJ].getB3().getY()+1;j++)
											if((i>0 && i<15) && (j>0 && j<10))
													tab[j][i].setBackground( couleur[0]);
								}
								case 1:{
									for(int i=f.getFlotte()[posI][posJ].getB2().getX()-1;i<=f.getFlotte()[posI][posJ].getB2().getX()+1;i++)
										for(int j=f.getFlotte()[posI][posJ].getB2().getY()-1;j<=f.getFlotte()[posI][posJ].getB2().getY()+1;j++)
												if((i>0 && i<15) && (j>0 && j<10))
													tab[j][i].setBackground( couleur[0]);
								}
								case 0:{
									for(int i=f.getFlotte()[posI][posJ].getB1().getX()-1;i<=f.getFlotte()[posI][posJ].getB1().getX()+1;i++)
										for(int j=f.getFlotte()[posI][posJ].getB1().getY()-1;j<=f.getFlotte()[posI][posJ].getB1().getY()+1;j++){
											if((i>0 && i<15) && (j>0 && j<10))
												tab[j][i].setBackground( couleur[0]);
											}
								}
							}
							switch(posI){
								case 3:{
									tab[f.getFlotte()[posI][posJ].getB4().getY()][f.getFlotte()[posI][posJ].getB4().getX()].setBackground( couleur[2]);
									tab[f.getFlotte()[posI][posJ].getB4().getY()][f.getFlotte()[posI][posJ].getB4().getX()].setText("Couler");
								}
								case 2:{
									tab[f.getFlotte()[posI][posJ].getB3().getY()][f.getFlotte()[posI][posJ].getB3().getX()].setBackground(couleur[2]);
									tab[f.getFlotte()[posI][posJ].getB3().getY()][f.getFlotte()[posI][posJ].getB3().getX()].setText("Couler");
								}
								case 1:{
									tab[f.getFlotte()[posI][posJ].getB2().getY()][f.getFlotte()[posI][posJ].getB2().getX()].setBackground( couleur[2]);
									tab[f.getFlotte()[posI][posJ].getB2().getY()][f.getFlotte()[posI][posJ].getB2().getX()].setText("Couler");
								}
								case 0:{
									tab[f.getFlotte()[posI][posJ].getB1().getY()][f.getFlotte()[posI][posJ].getB1().getX()].setBackground( couleur[2]);
									tab[f.getFlotte()[posI][posJ].getB1().getY()][f.getFlotte()[posI][posJ].getB1().getX()].setText("Couler");
								}
							}
							if(f.estDetruite()){
								fin.setVisible(true);
								fin.requestFocus();
							}
						}
						else{
							temp.setBackground( couleur[1]);
							temp.setText("Toucher");
						}
					}
					else{
						temp.setBackground( couleur[0]);
						tour = (tour+1)%2;
					}

				}
			}
		}
	}
}

class Titre extends Canvas{
	String mot;
	Titre(String titre){
		setSize(1032,750);
		setBackground (Color.cyan);
		setVisible(true);
		setForeground(Color.red);
		mot = titre;
	}

	public void paint(Graphics g){
		Font gras = new Font("Helvetica",Font.BOLD+Font.ITALIC,75);
		g.setColor(Color.black);
		g.drawString("Appuyez sur F1 pour l'aide",450,600);
		g.setFont(gras);
		g.setColor(Color.red);
		g.drawString(mot,150,255);


	}
}

class Segment{
	Point origine;
	Point fin;
	Color loko;

	private void construit(Point o,Point f,Color l){
		origine = new Point(o.x,o.y);
		fin = new Point(f.x,f.y);
		loko = l;
	}

	static Point pointSur(Point centre,int angle,double longueur,int numCouche){
		int px = (int)((double)numCouche*longueur*Math.cos(angle*Math.PI/180));
		int py = (int)((double)numCouche*longueur*Math.sin(angle*Math.PI/180));
		if((angle>=90 && angle<180) || (angle>=180 && angle< 270))
			return new Point(centre.x+px+2,centre.y+py+2);
		else
			return new Point(centre.x+px+2,centre.y-py-2);
	}


	Segment(Point o,Point f,Color l){
		construit(o,f,l);
	}

	Segment(Point o,int angle,double longueur,Color l,int numCouche){
			construit(o,pointSur(o,angle,longueur,numCouche),l);
	}

	void draw(Graphics g){
		g.setColor(loko);
		g.drawLine(origine.x,origine.y,fin.x,fin.y);
	}

	public String toString(){
		return ("ox="+origine.x+"...oy="+origine.y+"    fx="+fin.x+"...fy="+fin.y);
	}
}
class Explosion extends JPanel{
	static int PAR_COUCHE=1;
	final int LONG = 10;
	Vector<Segment> liste = new Vector<Segment>();
	Point centre = new Point();
	int nbCouche;

	int posXLast;
	int posYLast;
	int lonXLast;
	int lonYLast;

	Explosion(){
		super();
		genererSegment();
		(new Timer(900,new efface())).start();
		setBackground(Color.black);
	}

	public void demarrer(){
		(new Timer(900,new efface())).start();
	}

	void ajouterSegment(Point p,int angle,int longueur,Color l,int numCouche){
		liste.addElement(new Segment(p,angle,longueur,l,numCouche));
	}
	int aleat(int nb){
		return ((int)(Math.random()*997)%nb)+1;
	}
	void genererSegment(){
		nbCouche =  aleat(10)+6;
		Color[] loko = {Color.red,Color.green,Color.yellow,Color.blue,Color.cyan,Color.white,Color.magenta,Color.gray,new Color(255,100,0),new Color(100,70,50),new Color(125,255,0),new Color(170,100,0),new Color(100,0,100)};
		centre.x = aleat(1032);
		centre.y = aleat(700);
		for(int i=0;i<nbCouche;i++){
			Color lokoAleat = loko[aleat(loko.length)-1];
			int nbS =  aleat(8)+11;
			for(int j=1;j<=nbS;j++)
				ajouterSegment(Segment.pointSur(centre,360*j/nbS,LONG,i),360*j/nbS,LONG,lokoAleat,i);
		}
	}

	public void draw(Graphics g){
		for(int i=0;i<liste.size();i++){
			liste.elementAt(i).draw(g);
		}
	}

	public void paint(Graphics g){
		g.setColor(Color.black);
		g.fillRect(posXLast,posYLast,lonXLast,lonYLast);
		draw(g);
	}

	class efface implements ActionListener{
		public void actionPerformed(ActionEvent a){
			posXLast=(int)centre.x-LONG*2*nbCouche-10;
			posYLast=(int)centre.y-LONG*2*nbCouche-10;
			lonXLast=(int)4*LONG*nbCouche+10;
			lonYLast=(int)4*LONG*nbCouche+10;
			liste.removeAllElements();
			genererSegment();
			repaint();
		}
	}
}

class Rens extends JPanel{
	final static int PAGE_1=0;
	final static int DERNIERE_PAGE=1;

	JPanel dessin;
	JButton flecheDroite;
	JButton flecheGauche;

	Rens(int num){
		setOpaque(false);
		setLayout(new BorderLayout());
		JPanel centre = new JPanel(new BorderLayout());
		dessin= new JPanel(new GridLayout(0,1));
		centre.add(dessin,"Center");

		JPanel sud = new JPanel(new FlowLayout());
		flecheDroite = new JButton(new ImageIcon(getClass().getResource("sary/flecheDroite.png")));
		flecheDroite.setBackground(Color.white);
		flecheGauche = new JButton(new ImageIcon(getClass().getResource("sary/flecheGauche.png")));
		flecheGauche.setBackground(Color.white);
		add(centre,"Center");
		add(sud,"South");
		if(num==PAGE_1)
			flecheGauche.setVisible(false);
		if(num==DERNIERE_PAGE)
			flecheDroite.setVisible(false);
		sud.add(flecheGauche);
		sud.add(new JPanel());
		sud.add(flecheDroite);
		setVisible(true);
	}

	Rens(){
		this(2);
	}

	JButton getFlecheGauche(){
		return flecheGauche;
	}

	JButton getFlecheDroite(){
		return flecheDroite;
	}

	void setDessin(JPanel p){
		dessin.add(p);
	}
}
class Page extends JPanel{
	protected int numL;
	Page(){
		super();
		numL=0;
	}

	void carre(Graphics g,int x,int y){
		g.setColor(Color.blue);
		g.fillRect(x,y,30,30);
		g.setColor(Color.black);
		g.drawRect(x,y,30,30);
	}

	void italic(Graphics g){
		g.setColor(Color.black);
		g.setFont(new Font("Helvetica",Font.ITALIC,17));
	}

	void titre(Graphics g,String t){
		g.setColor(Color.red);
		g.setFont(new Font("Helevetica",Font.BOLD,35));
		g.drawString(t,150,180);
	}

	void ecrireSur(Graphics g,String t,int x,int numLigne){
		g.drawString(t,x,225+(21*numLigne));
		numL=numLigne+1;
	}

	void ecrireSur(Graphics g,String t,int x){
		g.drawString(t,x,225+(21*numL));
		numL++;
	}

	void ecrireSur(Graphics g,String t){
		g.drawString(t,250,225+(21*numL));
		numL++;
	}

}
class Page1 extends Page{
	Page1(){
		super();
	}

	public void paint(Graphics g){
		titre(g,"Principe");
		italic(g);
		ecrireSur(g,"Le principe du jeu est facile.",250,0);
		ecrireSur(g,"Chaque joueur possède une flotte composée de 10 bateaux:");
		ecrireSur(g,"->4 bateau1",330);
		ecrireSur(g,"->3 bateau2",330);
		ecrireSur(g,"->2 bateau3",330);
		ecrireSur(g,"->1 bateau4",330);
		carre(g,250,360);
		g.drawString("bateau1",238,406);
		carre(g,330,360);
		carre(g,360,360);
		g.drawString("bateau2",330,406);
		for(int i=0;i<3;i++)
			carre(g,440+i*30,360);
		g.drawString("bateau3",456,406);
		for(int i=0;i<4;i++)
			carre(g,610+i*30,360);
		g.drawString("bateau4",638,406);
		ecrireSur(g,"Le but est de détruire les bateaux adverses qui sont situés sur des cases numerotées de A à N sur la",200,12);
		ecrireSur(g,"colonne et 1 à 9 sur la ligne.",200);
		ecrireSur(g,"Si un bateau adverse est touchée, le joueur a encore le tour.",200);
	}
}
class Page2 extends Page{
	Page2(){
		super();
	}

	public void paint(Graphics g){
		titre(g,"Remplir sa flotte");
		italic(g);
		ecrireSur(g,"Pour entrer un bateau1,il faut entrer lettre-chiffre",250,0);
		ecrireSur(g,"Exemple: A3,G7,...",225);
		ecrireSur(g,"Exemple pour bateau2: A3 A4 ou E3 E2");
		ecrireSur(g,"Les deux cases doivent être liées: le cas A3 A5 n'est donc pas autorisé");
		ecrireSur(g,"Pour un bateau3, il faut faire comme ceci: E1 E2 E3 ou E1 E3 E2");
		ecrireSur(g,"Un bateau4: H1 H2 H3 H4 ou H3 G3 G2 H2");
		ecrireSur(g,"");
		ecrireSur(g,"Attention,seule les lettres majuscules sont autorisées, pas les monuscules;");
		ecrireSur(g,"Les valeurs autres(minuscules, en dehors de la grille,..) génereront une message d'eereur");
	}
}
class Page3 extends Page{
	Page3(){
		super();
	}

	public void paint(Graphics g){
		titre(g,"Exemple de Bateau2");
		italic(g);
		ecrireSur(g,"Voici quelques bateau2:",350,0);
		carre(g,250,275);
		carre(g,250,305);

		carre(g,340,275);
		carre(g,370,305);

		carre(g,460,305);
		carre(g,490,275);
	}
}
class Page4 extends Page{
	Page4(){
		super();
	}

	public void paint(Graphics g){
		titre(g,"Exemple de Bateau3");
		italic(g);
		carre(g,200,275);
		carre(g,200,305);
		carre(g,200,335);

		carre(g,290,275);
		carre(g,320,275);
		carre(g,350,305);

		carre(g,440,305);
		carre(g,470,275);
		carre(g,500,305);

		carre(g,590,275);
		carre(g,620,275);
		carre(g,590,305);

		for(int i=0;i<3;i++)
			carre(g,680+(i*30),275+(i*30));
	}
}
class Page5 extends Page{
	Page5(){
		super();
	}

	public void paint(Graphics g){
		titre(g,"Exemple de Bateau4");
		italic(g);
		for(int i=0;i<4;i++)
			carre(g,200,275+(30*i));

		carre(g,290,275);
		carre(g,320,305);
		carre(g,350,275);
		carre(g,380,305);

		for(int i=0;i<4;i++)
			carre(g,470+(i*30),275+(i*30));

		carre(g,650,305);
		carre(g,680,275);
		carre(g,680,335);
		carre(g,710,305);

		carre(g,800,275);
		carre(g,830,275);
		carre(g,830,305);
		carre(g,860,335);
	}
}
class Page6 extends Page{
	Page6(){
		super();
	}

	public void paint(Graphics g){
		titre(g,"Astuce");
		italic(g);
		ecrireSur(g,"Les bateaux qui ont coulé seront marqués ROUGE",250,0);
		ecrireSur(g,"Les bateaux qui sont touchés seront marqués en VERT");
		ecrireSur(g,"Les tirs qui font mouche seront marqués BLEU");
		ecrireSur(g,"Les cases n'ayant subit aucun tir seront en BLANC");
		g.setFont(new Font("Courrier",Font.ITALIC,45));
		g.setColor(Color.red);
		g.drawString("Bonne Chance",350,480);
	}
}
class RensMobile extends JPanel{
	Rens[] tabRens;
	int courant=0;
	JPanel[] tabPan={new Page1(),new Page2(),new Page3(),new Page4(),new Page5(),new Page6()};;

	RensMobile(int nb){
		setLayout(new GridLayout(1,0));
		tabRens = new Rens[nb];
		tabRens[0] = new Rens(Rens.PAGE_1);
		tabRens[tabRens.length-1] = new Rens(Rens.DERNIERE_PAGE);

		for(int i=1;i<tabRens.length-1;i++)
			tabRens[i] = new Rens();
		for(int i=0;i<tabRens.length;i++){
			tabRens[i].setDessin(tabPan[i]);
			tabRens[i].getFlecheGauche().addActionListener(new gauche());
			tabRens[i].getFlecheDroite().addActionListener(new droite());
		}

		add(tabRens[0]);
		setVisible(true);
	}

	class gauche implements ActionListener{
		public void actionPerformed(ActionEvent a){
			setVisible(false);
			remove(tabRens[courant]);
			courant--;
			add(tabRens[courant]);
			setVisible(true);
		}
	}

	class droite implements ActionListener{
		public void actionPerformed(ActionEvent a){
			setVisible(false);
			remove(tabRens[courant]);
			courant++;
			add(tabRens[courant]);
			setVisible(true);
		}
	}
}
class AffichRens extends JFrame{
	AffichRens(){
		setSize(1032,750);
		setLocation(-5,-5);
		setLayout(new GridLayout(1,0));
		add(new RensMobile(6));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] arg){
		new AffichRens();
	}
}

class MenuJoueur extends JMenu{
	static final String[] NOMLOKO = {"black","red","green","blue" ,"cyan" ,"orange","pink","gray",									"yellow","magenta","darkGray","marron"};
	static final Color[] LOKO = {Color.black,Color.red,Color.green,Color.blue,Color.cyan,         						new Color(255,100,0),new Color(255,70,100),Color.gray,Color.yellow,Color.magenta,								Color.darkGray,new Color(100,70,50)};
	static final String[] OPTION = {"Nom","Couleur eau","Couleur toucher","Couleur couler"};
	int[] ancien={3,2,1};
	JMenuItem[][] couleur;
	JMenu option[];

	MenuJoueur(String nom){
		super(nom);
		couleur = new JMenuItem[3][];
		for(int i=0;i<3;i++){
			couleur[i] = new JMenuItem[NOMLOKO.length];
		}
		for(int i=0;i<3;i++)
			for(int j=0;j<NOMLOKO.length;j++)
				couleur[i][j] = new JMenuItem(NOMLOKO[j]);
		option = new JMenu[OPTION.length];
		option[0] = new JMenu(OPTION[0]);
		add(option[0]);
		for(int i=1;i<OPTION.length;i++){
			option[i] = new JMenu(OPTION[i]);
			for(int j=0;j<LOKO.length;j++)
				option[i].add(couleur[i-1][j]);
			add(option[i]);
		}
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				couleur[i][ancien[j]].setEnabled(false);
	}

	JMenuItem getMenuItem(int i,int j){
		return couleur[i][j];
	}
	int[] getAncien(){
		return ancien;
	}

	void addAction(ActionListener[] re){
		for(int i=1;i<OPTION.length;i++)
			for(int j=0;j<NOMLOKO.length;j++)
				couleur[i-1][j].addActionListener(re[i-1]);
	}

	void changerCouleur(int numCouleur,int numCoul){
		for(int i=0;i<3;i++){
			couleur[i][ancien[numCouleur]].setEnabled(true);
			couleur[i][numCoul].setEnabled(false);
		}
		ancien[numCouleur] = numCoul;
	}

}


class APIBatNav extends JFrame{
	static Flotte flotteJ1 = new Flotte("Joueur1");
	static Flotte flotteJ2 = new Flotte("Joueur2");
	InterfaceJ[] interfJ = {new InterfaceJ(0),new InterfaceJ(1)};
	JPanel P;
	JButton boutton;
	JFrame aide = new JFrame();

	Suivant s = new Suivant();
	Entrer touche = new Entrer();
	Souris relache = new Souris();

	MenuJoueur[] j;
	JMenuItem recommencer;
	JMenuItem save;
	JMenuBar menuBar;
	APIBatNav(){
		aide.add(new RensMobile(6));
		aide.setVisible(false);
		aide.setSize(1032,750);
		aide.setLocation(-5,-5);
		aide.setTitle("Aide");
		setLayout(new BorderLayout());
		setSize(1032,750);
		setLocation(-5,-5);
		setTitle("Bataille Navale");
		setBackground(Color.cyan);
		setVisible(true);
		P = new JPanel(new BorderLayout());
		P.setVisible(true);
		P.add(new Titre("BATAILLE NAVALE"),"Center");
		addKeyListener(touche);
		add(P);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		recommencer = new JMenuItem("Recommencer");
		save = new JMenuItem("Suvegarder");
		recommencer.addActionListener(new Recommencer());
		ActionListener[][] ecouteMenu;
		ecouteMenu = new ActionListener[2][];

		j = new MenuJoueur[2];

		for(int i=0;i<2;i++){
			j[i] = new MenuJoueur("Joueur"+(i+1));
			ecouteMenu[i] = new ActionListener[3];
			for(int j=0;j<3;j++)
				ecouteMenu[i][j] = new ChangerCouleur(i,j);
			j[i].addAction(ecouteMenu[i]);
		}
		menuBar = new JMenuBar();
		JMenu opt = new JMenu("Option");
		opt.add(recommencer);
		opt.add(save);
		menuBar.add(j[0]);
		menuBar.add(j[1]);
		menuBar.add(opt);
		menuBar.setVisible(false);
		setJMenuBar(menuBar);
	}

	class Recommencer implements ActionListener{
		public void actionPerformed(ActionEvent a){
			setTitle("Bataille Navale");
			interfJ[0].initialiserTab();
			interfJ[1].initialiserTab();
			flotteJ1.initialiserFlotte();
			interfJ[0].initialiserSans(flotteJ1);
			flotteJ2.initialiserFlotte();
			interfJ[1].initialiserSans(flotteJ2);
			remove(interfJ[0]);
			remove(interfJ[1]);
			removeMouseListener(relache);
			InterfaceJ.tour=0;
			add(P);
			addKeyListener(touche);
			requestFocus();
			setBackground(Color.cyan);
			menuBar.setVisible(false);
		}
	}

	class ChangerCouleur implements ActionListener{
		int numJoueur;
		int numCouleur;

		ChangerCouleur(int jou,int coul){
			numJoueur = jou;
			numCouleur = coul;
		}

		public void actionPerformed(ActionEvent a){
			JMenuItem temp = (JMenuItem) a.getSource();
			int i=0;
			while((temp!=j[numJoueur].getMenuItem(numCouleur,i) && i!=MenuJoueur.LOKO.length))
				i++;
			j[numJoueur].changerCouleur(numCouleur,i);
			interfJ[numJoueur].setCouleur(numCouleur,MenuJoueur.LOKO[i]);
		}
	}

	class Souris extends MouseAdapter{
		public void mouseEnterred(MouseEvent m){
			requestFocus();
		}

	}

	class Entrer extends KeyAdapter{
		public void keyPressed(KeyEvent  t){
			switch(t.getKeyCode()){
				case KeyEvent.VK_ENTER:{
					remove(P);
					removeKeyListener(touche);
					boutton = new JButton("Joueur suivant");
					boutton.addActionListener(s);
					add(boutton,"Center");
					add(interfJ[0],"South");
					setVisible(true);
					flotteJ1.remplirFlotte();
				}
				break;
				case KeyEvent.VK_F1:
					aide.setVisible(true);
				break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
				break;
			}
		}
	}

	class Suivant implements ActionListener{
		public void actionPerformed(ActionEvent a){
			if(!flotteJ1.flotteLie() && flotteJ1.pret){
				setVisible(false);
				remove(boutton);
				remove(interfJ[0]);
				interfJ[0].initialiserAvec(flotteJ1);
				boutton.setText("Commencer");
				boutton.removeActionListener(s);
				boutton.addActionListener(new Next());
				add(boutton,"Center");
				add(interfJ[1],"South");
				setVisible(true);
				flotteJ2.remplirFlotte();

			}
			else{
				Flotte.msgErreur.afficherErreur("Entrer d'abord votre armée");
				interfJ[0].getF().getFenetre().getDonne().setText("");
				flotteJ1.initialiserFlotte();
				flotteJ1.remplirFlotte();
			}
		}
	}

	class Next implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(!flotteJ2.flotteLie() && flotteJ2.pret){
				remove(boutton);
				interfJ[1].initialiserAvec(flotteJ2);
				interfJ[0].getFin().setNomJoueur(interfJ[1].getF().getNom());
				interfJ[1].getFin().setNomJoueur(interfJ[0].getF().getNom());
				interfJ[0].getFin().addKeyListener(new Fin());
				interfJ[1].getFin().addKeyListener(new Fin());
				add(interfJ[0],"North");
				add(interfJ[1],"South");
				setTitle("Champ de Bataille");
				setVisible(true);
				menuBar.setVisible(true);
			}
			else{
				Flotte.msgErreur.afficherErreur("Entrer d'abord votre armée");
				interfJ[1].getF().getFenetre().getDonne().setText("");
				flotteJ2.initialiserFlotte();
				flotteJ2.remplirFlotte();
			}
		}
	}

	class Fin extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
				interfJ[0].getFin().setVisible(false);
				interfJ[1].getFin().setVisible(false);
				interfJ[0].initialiserTab();
				interfJ[1].initialiserTab();
				flotteJ1.initialiserFlotte();
				interfJ[0].initialiserSans(flotteJ1);
				flotteJ2.initialiserFlotte();
				interfJ[1].initialiserSans(flotteJ2);
				remove(interfJ[0]);
				remove(interfJ[1]);
				removeMouseListener(relache);
				InterfaceJ.tour=0;
				menuBar.setVisible(false);
				add(P);
				addKeyListener(touche);
				requestFocus();
		}
	}

	public static void main(String[] arg){
		new APIBatNav();
	}
}
