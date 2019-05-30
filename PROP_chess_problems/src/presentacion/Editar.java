package presentacion;

import presentacion.Tablero;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Editar {
	JFrame f;
	JTextField num, color, fen;
	JButton home, eliminar, pawn, pawnN, knight, knightN, bishop, bishopN, rook, rookN, queen, queenN, king, kingN;
	Font f1 = new Font ("Britannic Bold", Font.PLAIN,25);
	Font f2 = new Font ("Consolas", Font.BOLD,18);
	Color c1= new Color (255, 250, 227);
	Tablero t;
	
	public Editar() {
		f = new JFrame();
		t = new Tablero("EDITAR");
		SetFrame();
		JPanel menu = Men2.MenuPeque("PROBLEMAS");
		f.add(menu);
		SetPanelEditar1();
		SetPanelLinea();
		SetPanelEditar2();
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
		num.setBounds(415, 70, 80, 25);
		num.setBackground(Color.WHITE);
		num.setFont(f2);
		cp1.add(num);
		
		JLabel text2 = new JLabel("Introduce el color a hacer mate:");
		text2.setBounds(70,102,350,20);
		text2.setFont(f2);
		cp1.add(text2);
		
		
		color = new JTextField();
		color.setBounds(415, 100, 80, 25);
		color.setBackground(Color.WHITE);
		color.setFont(f2);
		cp1.add(color);
		
		JLabel text3 = new JLabel("Estado del problema: ");
		text3.setBounds(70,135,300,20);
		text3.setFont(f2);
		cp1.add(text3);
		
		
		eliminar = new JButton("basura");
		eliminar.setBounds(450, 670, 68, 68);
		eliminar.setBackground(c1);
		eliminar.setBorder(null);
		cp1.add(eliminar);
		
		JPanel n = t.t;
		n.setBounds(60,200,450,450);
		cp1.add(n);
		f.add(cp1);
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
		text4.setBounds(30,15,180,20);
		text4.setFont(f2);
		cp2.add(text4);
		fen = new JTextField();
		fen.setBounds(30, 45, 250, 30);
		fen.setBackground(Color.WHITE);
		fen.setFont(f2);
		cp2.add(fen);
		
		JLabel text5 = new JLabel("o");
		text5.setBounds(30,90,10,20);
		text5.setFont(f2);
		cp2.add(text5);
		JLabel text6 = new JLabel("Añade al tablero:");
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
	
	
	public static void main(String[] args) {
		Editar e = new Editar();
	}

}
