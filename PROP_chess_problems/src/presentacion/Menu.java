package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.*;

public class Menu {
	JButton Jugar;
	JButton MisProb;
	JButton Ranking;
	JButton Maquinas;
	JLabel t;
	public Menu() {
	}
	
	public void MenuGrande() {
		//FRAME
		JFrame f = new JFrame();
		f.setBounds (400,150,1089,803);
		f.setVisible(true);
		f.setTitle("Chess Game");
		f.setLayout(new BorderLayout());
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		f.setBackground(Color.YELLOW);
		
		//PANEL 1: cpane
		JPanel cpane = new JPanel();
		cpane.setBackground(Color.YELLOW);
		//cpane.setOpaque(false);
		//cpane.setLayout(new GridLayout(2,2,200,200));
		cpane.setBounds(0,0,1089,803);
		
		//BOTONES!
		Font  f1  = new Font(Font.SANS_SERIF, Font.BOLD,  20);
		JButton Jugar = new JButton("Jugar"/*icono*/);
		Jugar.setBounds(80,392,420,79); //x, y, width, height
		//Jugar.setSize(420,79);
		Jugar.setFont(f1);
		cpane.add(Jugar);
		
		JButton Ranking = new JButton("Rankings");
		Ranking.setBounds(580,392,420,79); //x, y, width, height
		//Ranking.setSize(420,79);
		Ranking.setFont(f1);
		cpane.add(Ranking);
		
		JButton MisProb = new JButton("Mis Problemas");
		MisProb.setBounds(80,520,420,79); //x, y, width, height
		//MisProb.setSize(420,79);
		MisProb.setFont(f1);
		cpane.add(MisProb);
		
		JButton Maquinas = new JButton("Evaluar Maquinas");	
		Maquinas.setBounds(580,520,420,79);
		//Maquinas.setSize(420,79);
		Maquinas.setFont(f1);
		cpane.add(Maquinas);
	
		
		//TITULO DE LA PAGINA:
		JLabel titulo = new JLabel("Main Menu"/*Icono, alineacion*/);
		titulo.setLocation(401,120);
		titulo.setBounds(400,10,300,300);
		Font auxFont = titulo.getFont();
		Font f2 = new Font (auxFont.getFontName(), auxFont.getStyle(),50);
		titulo.setFont(f2);
		cpane.add(titulo);
		
		
		//TITULO DE LA PAGINA:
		t = new JLabel();
		t.setBounds(400,100,300,300);
		//o poner su font aqui
		t.setFont(f2);
		cpane.add(t);
		f.add(cpane);
	}
	
	public  void setuser(String s) {
		t.setText("Welcome "+ s/*Icono, alineacion*/);
	}
	//FALTA FUNCION PARA ESTABLECER EL NOMBRE 
	public static JPanel MenuPeque(String s) {
		
		JPanel cpmenu = new JPanel();
		cpmenu.setBackground(Color.LIGHT_GRAY);
		cpmenu.setLayout( new BoxLayout (cpmenu, BoxLayout.Y_AXIS));;
		cpmenu.setBounds(0, 0, 230, 1000);
		
		JButton Jugar = new JButton ("JUGAR");
		Jugar.setBounds(0,150,230,54);
		JButton MisProb = new JButton ("MIS PROBLEMAS");
		MisProb.setBounds(0,204,230,54);
		JButton Ranking = new JButton ("RANKING");
		Ranking.setBounds(0,258,230,54);
		JButton Maquinas = new JButton ("EVALUAR MAQUINAS");
		Maquinas.setBounds(0,312,230,54);
		
		JButton SignOut = new JButton("Sign Out");
		SignOut.setBounds(0, 650, 230, 54);
		
		if (s=="JUGAR") Jugar.setEnabled(false);
		else if (s=="PROBLEMAS") MisProb.setEnabled(false);
		else if (s=="RANKING") Ranking.setEnabled(false);
		else if (s=="MAQUINA") Maquinas.setEnabled(false);
		
		cpmenu.add(Jugar);
		cpmenu.add(MisProb);
		cpmenu.add(Ranking);
		cpmenu.add(Maquinas);
		//cpmenu.add(Box.createRigidArea(new Dimension(0, 30)));
		cpmenu.add(SignOut);
		
		return cpmenu;
	
	}
	

	public static void main(String[] args) {
		Menu em = new Menu();
		em.MenuGrande();
		em.setuser("Marta");
		
	}

}
