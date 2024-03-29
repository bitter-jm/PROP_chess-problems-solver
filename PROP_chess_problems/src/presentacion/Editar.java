package presentacion;

import presentacion.Tablero;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class Editar  implements ActionListener {
	JFrame f;
	JTextField num, color, fen, nombre;
	JComboBox cb1;
	JButton home, eliminar, validar, cargar, terminar, pawn, pawnN, knight, knightN, bishop, bishopN, rook, rookN, queen, queenN, king, kingN;
	Font f1 = new Font ("Britannic Bold", Font.PLAIN,25);
	Font f2 = new Font ("Consolas", Font.BOLD,18);
	Color c1= new Color (255, 250, 227);
	Color blue = new Color(122, 221, 255);
	Color darkblue = new Color(0, 119, 204);
	Color green = new Color (163, 255, 186);
	Color darkgreen = new Color (57,155,85);
	String colganar;
	JPanel n;
	Tablero t;
	
	
	public Editar(String col) {
		f = new JFrame();
		colganar=col;
		t = new Tablero("EDITAR", this);
		
		SetFrame();
		JPanel menu = Men2.MenuPeque("PROBLEMAS");
		f.add(menu);
		SetPanelEditar1();
		SetPanelLinea();
		SetPanelEditar2();
		conectaTablero();
		f.setVisible(true);
	}
	
	public void SetFrame() {
		f.setBounds (400,150,1089,803);
		f.setTitle("Chess Game");
		f.setLayout(null);
		f.setResizable(false);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	public void SetPanelEditar1() {
		JPanel cp1 = new JPanel();
		cp1.setLayout(null);
		cp1.setBounds(230, 0, 550,803);
		cp1.setBackground(Color.WHITE);
		
		JLabel nomprob = new JLabel("Nom_Prob");
		nomprob.setBounds(30,30,250,20);
		nomprob.setFont(f1);
		cp1.add(nomprob);
		
		JLabel text1 = new JLabel("Introduce el numero de jugadas:");
		text1.setBounds(70,72,350,20);
		text1.setFont(f2);
		cp1.add(text1);
		
		num = new JTextField();
		
		num.setBounds(410, 70, 100, 25);
		num.setBackground(Color.WHITE);
		num.setFont(f2);
		cp1.add(num);
		
		JLabel text2 = new JLabel("Introduce el color a hacer mate:");
		text2.setBounds(70,102,350,20);
		text2.setFont(f2);
		cp1.add(text2);
		
		
		Vector<String> color = new Vector<String>();
		if (colganar =="BLANCAS") {
			color.add("BLANCAS");
			color.add("NEGRAS");
		}
		else {
			color.add("NEGRAS");
			color.add("BLANCAS");
		}
		cb1 = new JComboBox(color);
		
		cb1.setBounds(410, 100, 99, 25);
		cb1.setBackground(Color.WHITE);
		cb1.setFont(f2);
		cp1.add(cb1);
		
		JLabel text3 = new JLabel("Nombre del problema: ");
		text3.setBounds(70,135,300,20);
		text3.setFont(f2);
		cp1.add(text3);
		
		nombre = new JTextField();
		
		nombre.setBounds(300,135,200,20);
		nombre.setBackground(Color.WHITE);
		nombre.setFont(f2);
		cp1.add(nombre);
		
		validar = new JButton("VALIDAR");
		validar.setBounds(60, 670, 150, 68);
		validar.setBackground(green);
		validar.setForeground(darkgreen);
		validar.setBorder(null);
		validar.setFont(f1);
		cp1.add(validar);
		
		terminar = new JButton("TERMINAR");
		terminar.setBounds(250, 670, 150, 68);
		terminar.setBorder(null);
		terminar.setBackground(blue);
		terminar.setForeground(darkblue);
		terminar.setFont(f1);
		cp1.add(terminar);
		
		eliminar = new JButton("basura");
		eliminar.setBounds(440, 670, 68, 68);
		eliminar.setBackground(c1);
		eliminar.setBorder(null);
		cp1.add(eliminar);
		
		n = t.t;
		n.setBounds(60,180,450,450);
		cp1.add(n);
		f.add(cp1);
	}
	public void setInfo(String snum, String scolor) {
		num.setText(snum);
	}
	public void SetPanelLinea() {
		JPanel l = new JPanel();
		l.setLayout(null);
		l.setBounds(780, 0, 3,803);
		l.setBackground(Color.ORANGE);
		f.add(l);
	}
	
	public void SetPanelEditar2() {
		JPanel cp2 = new JPanel();
		cp2.setLayout(null);
		cp2.setBounds(783, 0, 306,803);
		cp2.setBackground(c1);
				
		JLabel text4 = new JLabel("Introduce FEN:");
		text4.setBounds(30,15,200,20);
		text4.setFont(f2);
		cp2.add(text4);
		fen = new JTextField();
		fen.setBounds(30, 45, 170, 30);
		fen.setBackground(Color.WHITE);
		fen.setFont(f2);
		cp2.add(fen);
		cargar = new JButton("set");
		cargar.setBounds(225, 44, 55, 30);
		cargar.setBackground(green);
		cargar.setForeground(darkgreen);
		cp2.add(cargar);
		
		JLabel text5 = new JLabel("o");
		text5.setBounds(30,90,10,20);
		text5.setFont(f2);
		cp2.add(text5);
		JLabel text6 = new JLabel("A�ade al tablero:");
		text6.setBounds(30,125,200,20);
		text6.setFont(f2);
		cp2.add(text6);
		
		pawn = new JButton("pawn");
		pawn.setBounds(50,150,75,75);
		cp2.add(pawn);
		pawnN = new JButton("pawnN");
		pawnN.setBounds(175,150,75,75);
		cp2.add(pawnN);
		
		knight = new JButton("knight");
		knight.setBounds(50,245,75,75);
		cp2.add(knight);
		knightN = new JButton("knightN");
		knightN.setBounds(175,245,75,75);
		cp2.add(knightN);
		
		bishop = new JButton("bishop");
		bishop.setBounds(50,340,75,75);
		cp2.add(bishop);
		bishopN = new JButton("bishopN");
		bishopN.setBounds(175,340,75,75);
		cp2.add(bishopN);
		
		rook = new JButton("rook");
		rook.setBounds(50,435,75,75);
		cp2.add(rook);
		rookN = new JButton("rookN");
		rookN.setBounds(175,435,75,75);
		cp2.add(rookN);
		
		queen = new JButton("queen");
		queen.setBounds(50,530,75,75);
		cp2.add(queen);
		queenN = new JButton("queenN");
		queenN.setBounds(175,530,75,75);
		cp2.add(queenN);
		
		king = new JButton("king");
		king.setBounds(50,625,75,75);
		cp2.add(king);
		kingN = new JButton("kingN");
		kingN.setBounds(175,625,75,75);
		cp2.add(kingN);
				
		home = new JButton("iconohome");
		home.setBounds(210, 700, 68, 68);
		home.setBackground(c1);
		home.setBorder(null);
		cp2.add(home);
		
		f.add(cp2);
	}
	
	public void conectaControlador (CtrlProblemas c) {
		cargar.addActionListener(c);
		cargar.setActionCommand("CARGAR");
		validar.addActionListener(c);
		validar.setActionCommand("VALIDAR");
		terminar.addActionListener(c);
		terminar.setActionCommand("TERMINAR");
		home.addActionListener(c);
		home.setActionCommand("HOME");
	  }
	public void conectaTablero() {
		eliminar.addActionListener(this);
		eliminar.putClientProperty("index",112);
		pawn.addActionListener(this);
		pawn.putClientProperty("index",100);
		pawnN.addActionListener(this);
		pawnN.putClientProperty("index",101);
		knight.addActionListener(this);
		knight.putClientProperty("index",102);
		knightN.addActionListener(this);
		knightN.putClientProperty("index",103);
		bishop.addActionListener(this);
		bishop.putClientProperty("index",104);
		bishopN.addActionListener(this);
		bishopN.putClientProperty("index",105);
		rook.addActionListener(this);
		rook.putClientProperty("index",106);
		rookN.addActionListener(this);
		rookN.putClientProperty("index",107);
		queen.addActionListener(this);
		queen.putClientProperty("index",108);
		queenN.addActionListener(this);
		queenN.putClientProperty("index",109);
		king.addActionListener(this);
		king.putClientProperty("index",110);
		kingN.addActionListener(this);
		kingN.putClientProperty("index",111);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		t.numclicksE++;
		JButton bE= (JButton) e.getSource();
		if (t.numclicksE == 1) t.sourceE =(int) bE.getClientProperty("index");
		else if (t.numclicksE == 2) {
			t.numclicksE =0;
			t.destinationE =(int) bE.getClientProperty("index");
			//si el primero picado es uno de los del lateral
			if (t.sourceE != 112 && t.sourceE >99) {
				//si el segundo picado no es una basura sino uno del tablero
				if (t.destinationE <100) {
					if (t.sourceE ==100) t.buttons[t.destinationE].setIcon(t.peon);
					else if (t.sourceE ==101) t.buttons[t.destinationE].setIcon(t.peonN);
					else if (t.sourceE ==102) t.buttons[t.destinationE].setIcon(t.caballo);
					else if (t.sourceE ==103) t.buttons[t.destinationE].setIcon(t.caballoN);
					else if (t.sourceE ==104) t.buttons[t.destinationE].setIcon(t.alfil);
					else if (t.sourceE ==105) t.buttons[t.destinationE].setIcon(t.alfilN);
					else if (t.sourceE ==106) t.buttons[t.destinationE].setIcon(t.torre);
					else if (t.sourceE ==107) t.buttons[t.destinationE].setIcon(t.torreN);
					else if (t.sourceE ==108) t.buttons[t.destinationE].setIcon(t.reina);
					else if (t.sourceE ==109) t.buttons[t.destinationE].setIcon(t.reinaN);
					else if (t.sourceE ==110) t.buttons[t.destinationE].setIcon(t.rey);
					else if (t.sourceE ==111) t.buttons[t.destinationE].setIcon(t.reyN);
				}
			}
			//si es del tablero a la basura
			if (t.sourceE<100 && t.destinationE==112) {
				  t.buttons[t.sourceE].setIcon(null);
			}
				
		}
		n.revalidate();
	}

}
