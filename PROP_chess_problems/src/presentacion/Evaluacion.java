package presentacion;
import presentacion.Men2;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Evaluacion {
	JFrame f;
	JPanel cp1;
	String s1, s2;
	Color c1= new Color (239, 255, 254);
	Color darkgreen = new Color (57,155,85);

	Font f1 = new Font ("Britannic Bold", Font.PLAIN,28);
	Font f2 = new Font ("Segoe Script", Font.PLAIN,45);

	String[] columnNames = {"Problema","Descripcion","Dificultad","Ganador"};
	 private JScrollPane scroll; //Panel de scroll que contiene la tabla
	 public static String[][] datos; //Cuerpo de la tabla
	 protected String[] cabecera;    //Cabecera de la tabla
	 protected DefaultTableModel dtm;//Unión de la cabecera y la tabla
	 protected JTable tabla; //Tabla propiamente dicha

	public Evaluacion(String a, String b,String[][] datam) {
		s1 = a;
		s2 = b;
		f = new JFrame();
		datos = datam;
		SetFrame();
		JPanel menu = Men2.MenuPeque("MAQUINA");
		f.add(menu);
		SetPanelEvaluacion();
		f.setVisible(true);
	}
	
	public void SetFrame() {
		f.setBounds (400,150,1089,803);
		f.setTitle("Chess Game");
		f.setLayout(null);
		f.setResizable(false);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	public void SetPanelEvaluacion() {
		cp1 = new JPanel();
		cp1.setLayout(null);
		cp1.setBounds(230, 0, 859,803);
		cp1.setBackground(c1);
		
		JLabel text1 = new JLabel("Jugador1");
		text1.setBounds(170, 45, 120, 50);
		text1.setFont(f1);
		cp1.add(text1);
		JLabel text2 = new JLabel("vs");
		text2.setBounds(410, 45, 80, 50);
		text2.setFont(f1);
		cp1.add(text2);
		JLabel text3 = new JLabel("Jugador2");
		text3.setBounds(550, 45, 120, 50);
		text3.setFont(f1);
		cp1.add(text3);
		
		JLabel text4 = new JLabel(s1);
		text4.setBounds(90, 100, 280, 50);
		text4.setFont(f2);
		cp1.add(text4);
		JLabel text5 = new JLabel(s2);
		text5.setBounds(480, 100, 280, 50);
		text5.setFont(f2);
		cp1.add(text5);
		
		scroll = new JScrollPane();
	    cabecera = new String[] {"Problema","Descripcion","Dificultad","Ganador"};
	    dtm= new DefaultTableModel(datos,cabecera);
	    tabla= new JTable(dtm);
	    scroll.setViewportView(tabla);
	    scroll.setBounds(90, 200, 660, 350);
	    scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cp1.add(scroll);
		
		cp1.add(tabla);
		/*
		JLabel text6 = new JLabel("Ganador de la evaluacion:");
		text6.setBounds(90, 600, 500, 50);
		text6.setFont(f1);
		cp1.add(text6);
		
		JLabel text7 = new JLabel("Maquina 1");
		text7.setBounds(480, 600, 280, 50);
		text7.setFont(f2);
		cp1.add(text7);*/
		
		ImageIcon house = new ImageIcon (new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/home.png").getImage().getScaledInstance(40, 40 ,java.awt.Image.SCALE_SMOOTH));
		JButton home = new JButton(house);
		home.setBounds(755, 670, 68, 68);
		home.setBackground(c1);
		home.setBorder(null);
		cp1.add(home);
		
		f.add(cp1);
	
	}
	
}
