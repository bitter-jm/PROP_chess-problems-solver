package presentacion;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	String[] columnNames = {"Nombre","Puntuacion"};
	Object[][] data = {
		    {"Annita.gibbs", "9.1"},
		    {"Annita.gibbs", "9.1"},
		    {"Annita.gibbs", "9.1"},
		    {"Annita.gibbs", "9.1"},
		    {"Annita.gibbs", "9.1"},
		    {"Annita.gibbs", "9.1"},
		    {"Annita.gibbs", "9.1"},
		    {"Annita.gibbs", "9.1"},
		    {"Annita.gibbs", "9.1"},
		    {"Annita.gibbs", "9.1"},
		    {"Annita.gibbs", "9.1"},
		    {"Annita.gibbs", "9.1"},
		    {"Annita.gibbs", "9.1"},
		    {"Annita.gibbs", "9.1"},
		};
	
	//Elements:
	JButton home;
	JTable tabla;
	public Ranking(String prob) {
		//f = new JFrame();
		this.prob = prob;
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
		
		//tengo que ponerlo en un scrollpane
		DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
		tabla = new JTable(dtm);
		tabla.setBounds(90, 150, 660, 450);
		tabla.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cp1.add(tabla);
		
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
	

	public void conectaControlador (Controller c) {
		home.addActionListener(c);
		home.setActionCommand("HOME");
		tabla.addMouseListener(c);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
}