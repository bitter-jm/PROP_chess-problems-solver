package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class Ranking extends JFrame{
	JFrame f ;
	JPanel cp1;
	Color c1= new Color (239, 255, 254);
	String prob;
	Font f1 = new Font ("Britannic Bold", Font.PLAIN,40);
	Font f2 = new Font ("Consolas", Font.PLAIN,20);
	JList list;
	
	 private JScrollPane scroll; //Panel de scroll que contiene la tabla
	 public static Object[][] datos; //Cuerpo de la tabla
	 protected String[] cabecera;    //Cabecera de la tabla
	 protected DefaultTableModel dtm;//Unión de la cabecera y la tabla
	 protected JTable tabla; //Tabla propiamente dicha
	//Elements:
	JButton home;
	
	public Ranking(String prob, String[][] datam) {
		f = new JFrame();
		this.prob = prob;
		datos = datam;
		SetFrame();
		JPanel menu = Men2.MenuPeque("RANKING");
		this.add(menu);
		SetPanelRanking();
		this.setVisible(true);
	}
	
	public void SetFrame() {
		this.setBounds (400,150,1089,803);
		this.setTitle("Chess Game");
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	public void SetPanelRanking() {
		cp1 = new JPanel();
		cp1.setLayout(null);
		cp1.setBounds(230, 0, 859,803);
		cp1.setBackground(c1);
		
		JLabel text1 = new JLabel("Ranking "+prob+":");
		text1.setBounds(90, 50, 400, 50);
		text1.setFont(f1);
		cp1.add(text1);
		
		scroll = new JScrollPane();
	    cabecera = new String[] {"NOMBRE","PUNTUACION"};
	    dtm= new DefaultTableModel(datos,cabecera);
	    tabla= new JTable(dtm);
	    scroll.setViewportView(tabla);
	    scroll.setBounds(90, 100, 660, 525);
	    scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cp1.add(scroll);
		
		
		JLabel text2 = new JLabel("Top50 jugadores de este problema");
		text2.setBounds(220, 650, 450, 50);
		text2.setFont(f2);
		cp1.add(text2);

		ImageIcon house = new ImageIcon (new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/home.png").getImage().getScaledInstance(40, 40 ,java.awt.Image.SCALE_SMOOTH));
		home = new JButton(house);
		home.setBounds(755, 670, 68, 68);
		home.setBackground(c1);
		home.setBorder(null);
		cp1.add(home);
			
		this.add(cp1);
	}
	

	public void conectaControlador (CtrlRanking c) {
		home.addActionListener(c);
		home.setActionCommand("HOME");
	}	
}