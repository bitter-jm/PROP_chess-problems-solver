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

public class Evaluacion {
	JFrame f;
	JPanel cp1;
	
	Color c1= new Color (239, 255, 254);
	Color darkgreen = new Color (57,155,85);

	Font f1 = new Font ("Britannic Bold", Font.PLAIN,28);
	Font f2 = new Font ("Segoe Script", Font.PLAIN,45);
	
	String[] columnNames = {"Problema",
            "Descripcion",
            "Dificultad",
            "Ganador"};
	Object[][] data = {
		    {"Problema 3", "Mate en 3 por parte de las blancas",
		     "Hard", "Jugador 1"},
		    {"Problema 5", "Mate en 4 por parte de las blancas",
		     "Medium", "Jugador 2"},
		    {"Problema 7", "Mate en 2 por parte de las blancas",
		     "Easy", "Jugador 1"},
		    {"Problema 9", "Mate en 3 por parte de las blancas",
		     "Hard", "Jugador 2"},
		    {"Problema 11", "Mate en 4 por parte de las blancas",
		     "Easy", "Jugador 2"}
		};
	
	public Evaluacion() {
		f = new JFrame();
		SetFrame();
		JPanel menu = Men2.MenuPeque("MAQUINA");
		f.add(menu);
		SetPanelRanking();
		f.setVisible(true);
	}
	
	public void SetFrame() {
		f.setBounds (400,150,1089,803);
		f.setTitle("Chess Game");
		f.setLayout(null);
		f.setResizable(false);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	public void SetPanelRanking() {
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
		
		JLabel text4 = new JLabel("Maquina 2");
		text4.setBounds(90, 100, 280, 50);
		text4.setFont(f2);
		cp1.add(text4);
		JLabel text5 = new JLabel("Maquina 1");
		text5.setBounds(480, 100, 280, 50);
		text5.setFont(f2);
		cp1.add(text5);
		
	
		JTable tabla = new JTable(data, columnNames);
		tabla.setBounds(90, 200, 660, 350);
		tabla.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
		
		cp1.add(tabla);
		
		JLabel text6 = new JLabel("Ganador de la evaluacion:");
		text6.setBounds(90, 600, 500, 50);
		text6.setFont(f1);
		cp1.add(text6);
		
		JLabel text7 = new JLabel("Maquina 1");
		text7.setBounds(480, 600, 280, 50);
		text7.setFont(f2);
		cp1.add(text7);
		
		ImageIcon house = new ImageIcon (new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/home.png").getImage().getScaledInstance(40, 40 ,java.awt.Image.SCALE_SMOOTH));
		JButton home = new JButton(house);
		home.setBounds(755, 670, 68, 68);
		home.setBackground(c1);
		home.setBorder(null);
		cp1.add(home);
		

		f.add(cp1);
	
	}
	
	public static void main(String[] args) {
		Evaluacion e = new Evaluacion();
		
	}
}
