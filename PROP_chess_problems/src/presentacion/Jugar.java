package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import presentacion.Tablero;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Jugar {
	JFrame f;
	JComboBox<String> cb1, cb2;
	JButton play, home;
	JList<String> list;
	Tablero t;
	
	Font f0 = new Font ("AR BONNIE", Font.BOLD,70);
	Font f1 = new Font ("Britannic Bold", Font.PLAIN,25);
	Font f2 = new Font ("Consolas", Font.BOLD,18);
	
	Color c1= new Color (239, 255, 254);
	Color green = new Color (163, 255, 186);
	Color darkgreen = new Color (57,155,85);
	Color blue = new Color(122, 221, 255);
	Color darkblue = new Color(0, 119, 204);
	
	public Jugar() {
		f = new JFrame();
		t = new Tablero("JUGAR");
		SetFrame();
		JPanel menu = Men2.MenuPeque("JUGAR");
		f.add(menu);
		SetPanelJugar();
		f.setVisible(true);
	}
	
	public void SetFrame() {
		f.setBounds (400,150,1089,803);
		f.setTitle("Chess Game");
		f.setLayout(null);
		f.setResizable(false);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	public void SetPanelJugar() {
		JPanel cp1 = new JPanel();
		cp1.setLayout(null);
		cp1.setBounds(230, 0, 859,803);
		cp1.setBackground(c1);
		
		JLabel titulo = new JLabel("CONFIGURA LA PARTIDA:");
		titulo.setBounds(90,50,660,54);
		titulo.setFont(f0);
		titulo.setForeground(darkblue);
		cp1.add(titulo);
		
		JLabel text1 = new JLabel("Escoge los jugadores:");
		text1.setBounds(530, 150, 500, 25);
		text1.setFont(f1);
		text1.setForeground(darkblue);
		cp1.add(text1);
		JLabel text2 = new JLabel("Jugador 1:");
		text2.setBounds(530, 190, 100, 30);
		text2.setFont(f2);
		text2.setForeground(darkblue);
		cp1.add(text2);
		JLabel text3 = new JLabel("Jugador 2:");
		text3.setBounds(530, 265, 100, 30);
		text3.setFont(f2);
		text3.setForeground(darkblue);
		cp1.add(text3);
		
		Vector<String> v = new Vector<String>();
		v.add("Current_user");
		v.add("Guest");
		v.add("Maquina 1");
		v.add("Maquina 2");
		
		cb1 = new JComboBox<String>(v);
		cb1.setBounds( 530, 220, 280, 30);
		cp1.add(cb1);
		cb2 = new JComboBox<String>(v);
		cb2.setBounds(530, 290, 280, 30);
		cp1.add(cb2);
		
		list = new JList<String>();
		JScrollPane scroll = new JScrollPane(list);
		scroll.setBounds(40, 150, 470, 460);
		scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cp1.add(scroll);
		
		play = new JButton("¡JUGAR!");
		play.setBounds(200, 650, 450, 50);
		play.setBackground(blue);
		play.setForeground(darkblue);
		play.setFont(f1);
		cp1.add(play);
		
		home = new JButton("iconohome");
		home.setBounds(755, 670, 68, 68);
		home.setBackground(c1);
		home.setBorder(null);
		cp1.add(home);
			
		JPanel n = t.t;
		n.setBounds(550, 350, 250, 250);
		cp1.add(n);
		f.add(cp1);
		
	}
	public void setListData( Vector<String> vect ) {
        list.setListData(vect);
    }
	
	public static void main(String[] args) {
		Jugar j = new Jugar();
	     Vector<String> v = new Vector<String>();
	        for ( int i = 0; i < 100; i++ ) {
	            v.add( "Data " + i );
	        }
	        j.setListData( v );
	}
}
