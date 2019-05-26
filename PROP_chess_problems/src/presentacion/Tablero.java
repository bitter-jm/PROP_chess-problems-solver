package presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;

	//Esto sera de ser de mi clase de button que extenderea
	//JButton y implementara los listners
public class Tablero implements ActionListener {
	JFrame f;
	JPanel t;
	ImageIcon peon, torre, caballo, alfil, reina, rey;
	ImageIcon peonN, torreN, caballoN, alfilN, reinaN, reyN;
	boolean hay= false;
	JButton buttons[] = new JButton[64];
	int numclicks =0;
	int source;
	int destination;
	private String caller;
	
	
	public Tablero() {
		f = new JFrame();
		t = new JPanel();
		setFrame();
		setIcons();
		setTablero();
		
		f.setVisible(true);
	}
	public void setIcons() {
		peon = new ImageIcon (new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/Peon.svg.png").getImage().getScaledInstance(50, 50 ,java.awt.Image.SCALE_SMOOTH));
		peonN = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/PeonN.svg.png").getImage().getScaledInstance(50, 50 ,java.awt.Image.SCALE_SMOOTH));
		torre = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/Torre.svg.png").getImage().getScaledInstance(50, 50 ,java.awt.Image.SCALE_SMOOTH));
		torreN = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/TorreN.svg.png").getImage().getScaledInstance(50, 50 ,java.awt.Image.SCALE_SMOOTH));
		caballo = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/Caballo.svg.png").getImage().getScaledInstance(50, 50 ,java.awt.Image.SCALE_SMOOTH));
		caballoN = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/CaballoN.svg.png").getImage().getScaledInstance(50, 50 ,java.awt.Image.SCALE_SMOOTH));
		alfil = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/Alfil.svg.png").getImage().getScaledInstance(50, 50 ,java.awt.Image.SCALE_SMOOTH));
		alfilN = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/AlfilN.svg.png").getImage().getScaledInstance(50, 50 ,java.awt.Image.SCALE_SMOOTH));
		reina = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/Reina.svg.png").getImage().getScaledInstance(50, 50 ,java.awt.Image.SCALE_SMOOTH));
		reinaN = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/ReinaN.svg.png").getImage().getScaledInstance(50, 50 ,java.awt.Image.SCALE_SMOOTH));
		rey = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/King.svg.png").getImage().getScaledInstance(50, 50 ,java.awt.Image.SCALE_SMOOTH));
		reyN = new ImageIcon(new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/KingN.png").getImage().getScaledInstance(50, 50 ,java.awt.Image.SCALE_SMOOTH));	
	}
	private void setTablero() {
		t.setLayout(new GridLayout(8,8));
		boolean b = false;
		for (int i=0; i<64; ++i) {
			buttons[i] = new JButton();
			if (i%8==0 && i!=0) b=!b;
			b=!b;
			if (b) buttons[i].setBackground(Color.WHITE);
			else buttons[i].setBackground(Color.GRAY);
			buttons[i].addActionListener(this);
			buttons[i].putClientProperty("index", i);
			t.add(buttons[i]);
		}
		f.add(t);
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
	
	public void actionPerformed(ActionEvent e) {
		
		++numclicks;
		JButton b= (JButton) e.getSource();
		if (numclicks == 1) {
			source =(int) b.getClientProperty("index");
		}
		else if (numclicks == 2) {
			numclicks =0;
			destination =(int) b.getClientProperty("index");
			//if (caller == "PLAYER") {
			//check if destination is correct 
			//osea aqui hay la llamada de controladores
			buttons[destination].setIcon(buttons[source].getIcon());
			buttons[source].setIcon(null);
			//}
			/*	else if (caller == "EDIT") {
				if (buttons[destination].getIcon()==null) {
					buttons[destination].setIcon(buttons[source].getIcon());
					//if (boton es uno de dentro del tablero)
					//buttons[source].setIcon(null);
					//else if (boton uno del menu)
					//No se hace nada
				}
			}*/
		}
	}
	
	public String getCaller() {
		return caller;
	}
	public void setCaller(String caller) {
		this.caller = caller;
	}
	public static void main(String[] args) {
	 Tablero tab = new Tablero();
	 //Columnas, filas osea quizas esta al reves
	 tab.setIconBoard("NEGRAS", "peon",1,0);
	 tab.setIconBoard("NEGRAS", "peon",1,1);
	 tab.setIconBoard("NEGRAS", "peon",1,2);
	 tab.setIconBoard("NEGRAS", "peon",1,3);
	 tab.setIconBoard("NEGRAS", "peon",1,4);
	 tab.setIconBoard("NEGRAS", "peon",1,5);
	 tab.setIconBoard("NEGRAS", "peon",1,6);
	 tab.setIconBoard("NEGRAS", "peon",1,7);

	 tab.setIconBoard("BLANCAS", "peon",6,0);
	 tab.setIconBoard("BLANCAS", "peon",6,1);
	 tab.setIconBoard("BLANCAS", "peon",6,2);
	 tab.setIconBoard("BLANCAS", "peon",6,3);
	 tab.setIconBoard("BLANCAS", "peon",6,4);
	 tab.setIconBoard("BLANCAS", "peon",6,5);
	 tab.setIconBoard("BLANCAS", "peon",6,6);
	 tab.setIconBoard("BLANCAS", "peon",6,7);
	 
	 tab.setIconBoard("NEGRAS", "torre",0,0);
	 tab.setIconBoard("NEGRAS", "caballo",0,1);
	 tab.setIconBoard("NEGRAS", "alfil",0,2);
	 tab.setIconBoard("NEGRAS", "reina",0,3);
	 tab.setIconBoard("NEGRAS", "rey",0,4);
	 tab.setIconBoard("NEGRAS", "alfil",0,5);
	 tab.setIconBoard("NEGRAS", "caballo",0,6);
	 tab.setIconBoard("NEGRAS", "torre",0,7);
	  
	 tab.setIconBoard("BLANCAS", "torre",7,0);
	 tab.setIconBoard("BLANCAS", "caballo",7,1);
	 tab.setIconBoard("BLANCAS", "alfil",7,2);
	 tab.setIconBoard("BLANCAS", "reina",7,3);
	 tab.setIconBoard("BLANCAS", "rey",7,4);
	 tab.setIconBoard("BLANCAS", "alfil",7,5);
	 tab.setIconBoard("BLANCAS", "caballo",7,6);
	 tab.setIconBoard("BLANCAS", "torre",7,7);
	}

}
