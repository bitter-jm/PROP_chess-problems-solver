package presentacion;

import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


import domain.Ficha;

	//Esto sera de ser de mi clase de button que extenderea
	//JButton y implementara los listners
public class Tablero {
	JFrame f;
	JPanel t;
	ImageIcon peon, torre, caballo, alfil, reina, rey;
	ImageIcon peonN, torreN, caballoN, alfilN, reinaN, reyN;
	boolean hay= false;
	JButton buttons[] = new JButton[64];
	int numclicksJ =0;
	int numclicksE =0;
	int source;
	int destination;
	int sourceE, destinationE;
	public String caller;
	
	
	public Tablero(String s, Object e) {
		//f = new JFrame();
		caller = s;
		t = new JPanel();
		
		//setFrame();
		int x=0;
		if (caller == "PARTIDA") {
			x=50;
		}
		else if (caller=="EDITAR") {
			x=56;
		}
		else if (caller=="JUGAR") {
			x=31;
		}
		setIcons(x);
		setTablero(e);
		//f.add(t);
		//f.setVisible(true);
	}
	public void setIcons(int x) {
		peon = new ImageIcon (new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/Peon.svg.png").getImage().getScaledInstance(x, x ,java.awt.Image.SCALE_SMOOTH));
		peonN = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/PeonN.svg.png").getImage().getScaledInstance(x, x ,java.awt.Image.SCALE_SMOOTH));
		torre = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/Torre.svg.png").getImage().getScaledInstance(x, x ,java.awt.Image.SCALE_SMOOTH));
		torreN = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/TorreN.svg.png").getImage().getScaledInstance(x, x ,java.awt.Image.SCALE_SMOOTH));
		caballo = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/Caballo.svg.png").getImage().getScaledInstance(x, x ,java.awt.Image.SCALE_SMOOTH));
		caballoN = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/CaballoN.svg.png").getImage().getScaledInstance(x, x ,java.awt.Image.SCALE_SMOOTH));
		alfil = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/Alfil.svg.png").getImage().getScaledInstance(x, x ,java.awt.Image.SCALE_SMOOTH));
		alfilN = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/AlfilN.svg.png").getImage().getScaledInstance(x, x ,java.awt.Image.SCALE_SMOOTH));
		reina = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/Reina.svg.png").getImage().getScaledInstance(x, x ,java.awt.Image.SCALE_SMOOTH));
		reinaN = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/ReinaN.svg.png").getImage().getScaledInstance(x, x ,java.awt.Image.SCALE_SMOOTH));
		rey = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/King.svg.png").getImage().getScaledInstance(x, x ,java.awt.Image.SCALE_SMOOTH));
		reyN = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/KingN.png").getImage().getScaledInstance(x, x ,java.awt.Image.SCALE_SMOOTH));	
	}
	private void setTablero(Object e) {
		t.setLayout(new GridLayout(8,8));
		boolean b = false;
		for (int i=0; i<64; ++i) {
			buttons[i] = new JButton();
			if (i%8==0 && i!=0) b=!b;
			b=!b;
			if (b) buttons[i].setBackground(Color.WHITE);
			else buttons[i].setBackground(Color.GRAY);
			buttons[i].addActionListener( (ActionListener) e);
			buttons[i].putClientProperty("index", i);
			t.add(buttons[i]);
		}
		//f.add(t);
	}
	
	private void setFrame() {
		f.setTitle("Chess Game");
		f.setSize(600,600);
		f.setResizable(true);
		f.setLayout(new BorderLayout());
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );		
	}
	private int PosToSquare (int fila, int columna) {
		int num;
		num = fila*8 +columna;
		return num;
	}
	public void limpiarTablero() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				removeIcon(i,j);
			}
		}
	}
	
	public void initializeBoardFEN (String FEN) {
		this.limpiarTablero();
		boolean stop = false;
		int i = 0;
		int j = 0;
		for (int it = 0; it < FEN.length() && !stop; ++it) {
			String c = String.valueOf(FEN.charAt(it));
			Character c1 = c.charAt(0);
			if (Character.isDigit(c.charAt(0))) {
				j += Integer.parseInt(""+c.charAt(0));
			}
			else if (c1.equals('/')) {
				i++;
				j = 0;
			}
			else if (c1.equals(' ')) stop = true;
			else {		
			/*	K	White King	k	Black King
				Q	White Queen	q	Black Queen
				R	White Rook	r	Black Rook
				B	White Bishop	b	Black Bishop
				N	White Knight	n	Black Knight
				P	White Pawn	p	Black Pawn*/
				if (c1=='K') setIconBoard("BLANCAS", "rey",i,j);
				else if (c1=='Q') setIconBoard("BLANCAS", "reina",i,j);
				else if (c1=='R') setIconBoard("BLANCAS", "torre",i,j);
				else if (c1=='B') setIconBoard("BLANCAS", "alfil",i,j);
				else if (c1=='N') setIconBoard("BLANCAS", "caballo",i,j);
				else if (c1=='P') setIconBoard("BLANCAS", "peon",i,j);
				else if (c1=='k') setIconBoard("NEGRAS", "rey",i,j);
				else if (c1=='q') setIconBoard("NEGRAS", "reina",i,j);
				else if (c1=='r') setIconBoard("NEGRAS", "torre",i,j);
				else if (c1=='b') setIconBoard("NEGRAS", "alfil",i,j);
				else if (c1=='n') setIconBoard("NEGRAS", "caballo",i,j);
				else if (c1=='p') setIconBoard("NEGRAS", "peon",i,j);
				if (j < 7) j++;
			}
		}
	}
	public void setIconBoard (String Color, String figura, int fila, int columna) {
		ImageIcon fig= getIcon(figura,Color);
		buttons[PosToSquare(fila,columna)].setIcon(fig);
	}
	public void removeIcon(int x, int y) {
		buttons[PosToSquare(x,y)].setIcon(null);
	}
	private ImageIcon getIcon(String s, String c) {
		if (c == "NEGRAS") {
			if (s=="peon") return peonN;
			if (s== "torre") return torreN;
			if (s== "caballo") return caballoN;
			if (s== "alfil") return alfilN;
			if (s== "reina") return reinaN;
			if (s== "rey") return reyN;
		}
		else {
			if (s=="peon") return peon;
			else if (s=="torre") return torre;
			else if (s== "caballo") return caballo;
			else if (s== "alfil") return alfil;
			else if (s== "reina") return reina;
			else if (s== "rey") return rey;
		}
		return null;
	}
	public String getFenFromMatrix() {
		String fen = "/";
		int x = 0;
		for (int i=0; i<8; ++i) {
			x=0;
			for (int j=0; j<8; ++j) {
				int b = PosToSquare (i,j);
				if (buttons[b].getIcon() == null) {
					++x;
					if(j==7) fen +=x; 
				}
				else {
					if (x>0) {
						fen += x;
						x=0;
					}
					if (buttons[b].getIcon() == peon) fen += 'P';
					else if (buttons[b].getIcon() == peonN) fen += 'p';
					else if (buttons[b].getIcon() == caballo) fen += 'N';
					else if (buttons[b].getIcon() == caballoN) fen += 'n';
					else if (buttons[b].getIcon() == torre) fen += 'R';
					else if (buttons[b].getIcon() == torreN) fen += 'r';
					else if (buttons[b].getIcon() == alfil) fen += 'B';
					else if (buttons[b].getIcon() == alfilN) fen += 'b';
					else if (buttons[b].getIcon() == reina) fen += 'Q';
					else if (buttons[b].getIcon() == reinaN) fen += 'q';
					else if (buttons[b].getIcon() == rey) fen += 'K';
					else if (buttons[b].getIcon() == reyN) fen += 'k';	
				}
			
			}
			
			fen+='/';
		}
		return fen;
		
	}/*
	public void actionPerformed(ActionEvent e) {
		
		if (caller == "PLAYER") {
			++numclicksJ;
			JButton b= (JButton) e.getSource();
			if (numclicksJ == 1) {
				source =(int) b.getClientProperty("index");
			}
			else if (numclicksJ == 2) {
				numclicksJ =0;
				if (caller == "PLAYER") {
				destination =(int) b.getClientProperty("index");
				buttons[destination].setIcon(buttons[source].getIcon());
				buttons[source].setIcon(null);
				}
			}
		}
		if (caller == "EDITAR") {
			++numclicksE;
			JButton bE= (JButton) e.getSource();
			if (numclicksE == 1) sourceE =(int) bE.getClientProperty("index");
			else if (numclicksE == 2) {
				numclicksE =0;
				destinationE =(int) bE.getClientProperty("index");
				//si el primero picado es uno de los del lateral
				if (sourceE != 112 && sourceE >99) {
					//si el segundo picado no es una basura sino uno del tablero
					if (destinationE <100) {
						if (sourceE ==100) buttons[destinationE].setIcon(peon);
						else if (sourceE ==101) buttons[destinationE].setIcon(peonN);
						else if (sourceE ==102) buttons[destinationE].setIcon(caballo);
						else if (sourceE ==103) buttons[destinationE].setIcon(caballoN);
						else if (sourceE ==104) buttons[destinationE].setIcon(alfil);
						else if (sourceE ==105) buttons[destinationE].setIcon(alfilN);
						else if (sourceE ==106) buttons[destinationE].setIcon(torre);
						else if (sourceE ==107) buttons[destinationE].setIcon(torreN);
						else if (sourceE ==108) buttons[destinationE].setIcon(reina);
						else if (sourceE ==109) buttons[destinationE].setIcon(reinaN);
						else if (sourceE ==110) buttons[destinationE].setIcon(rey);
						else if (sourceE ==111) buttons[destinationE].setIcon(reyN);
					}
				}
				//si es del tablero
				if (sourceE<100) {
					//a la basura
					if (destinationE==112) 	buttons[sourceE].setIcon(null);
					//a otro del tablero
					else if (destinationE <100) buttons[destination].setIcon(buttons[source].getIcon());
				}
					
			}
		}

	}
	/*
	public static void main(String[] args) {
		String s = "EDITAR";
		Tablero tab = new Tablero(s);
	}
	/*
	public String getCaller() {
		return caller;
	}
	public void setCaller(String caller) {
		this.caller = caller;
	}*/
	 //tab.setIconBoard("NEGRAS", "peon",1,0);
}
