package presentacion;
import presentacion.Men2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EvMaq {
	JFrame f;
	JList list;
	
	Font f1 = new Font ("Britannic Bold", Font.PLAIN,25);
	Font f2 = new Font ("Consolas", Font.BOLD,18);
	
	Color c1= new Color (239, 255, 254);
	Color green = new Color (163, 255, 186);
	Color darkgreen = new Color (57,155,85);
	
	public EvMaq() {
		f = new JFrame();
		SetFrame();
		JPanel menu = Men2.MenuPeque("MAQUINA");
		f.add(menu);
		SetPanelMaquina();
		f.setVisible(true);
	}
	
	public void SetFrame() {
		f.setBounds (400,150,1089,803);
		f.setTitle("Chess Game");
		f.setLayout(null);
		f.setResizable(false);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	public void SetPanelMaquina() {
		JPanel cp1 = new JPanel();
		cp1.setLayout(null);
		cp1.setBounds(230, 0, 859,803);
		cp1.setBackground(c1);
		
		JLabel text1 = new JLabel("Escoge las maquinas a evaluar:");
		text1.setBounds(90, 25, 500, 40);
		text1.setFont(f1);
		cp1.add(text1);
		JLabel text2 = new JLabel("Jugador 1:");
		text2.setBounds(180, 75, 100, 30);
		text2.setFont(f2);
		cp1.add(text2);
		JLabel text3 = new JLabel("Jugador 2:");
		text3.setBounds(180, 125, 100, 30);
		text3.setFont(f2);
		cp1.add(text3);
		JLabel text4 = new JLabel("Escoge los problemas a evaluar:");
		text4.setBounds(90, 155, 500, 40);
		text4.setFont(f1);
		cp1.add(text4);
		
		Vector<String> maq = new Vector<String>();
		maq.add("Maquina 1");
		maq.add("Maquina 2");
		JComboBox cb1 = new JComboBox(maq);
		cb1.setBounds( 330, 75, 420, 30);
		cp1.add(cb1);
		JComboBox cb2 = new JComboBox(maq);
		cb2.setBounds( 330, 125, 420, 30);
		cp1.add(cb2);
				
		list = new JList();
		JScrollPane scroll = new JScrollPane(list);
		scroll.setBounds(90, 200, 660, 450);
		scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cp1.add(scroll);
			
		JButton Ev = new JButton("EVALUAR");
		Ev.setBounds(200, 670, 450, 50);
		Ev.setBackground(green);
		Ev.setForeground(darkgreen);
		Ev.setFont(f1);
		cp1.add(Ev);
		
		ImageIcon house = new ImageIcon (new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/home.png").getImage().getScaledInstance(40, 40 ,java.awt.Image.SCALE_SMOOTH));
		JButton home = new JButton(house);
		home.setBounds(755, 670, 68, 68);
		home.setBackground(c1);
		home.setBorder(null);
		cp1.add(home);
			
		f.add(cp1);
	}
	
    public void setListData( Vector<String> vect ) {
        list.setListData(vect);
    }
	
	public static void main(String[] args) {
		EvMaq e = new EvMaq();
	     Vector<String> v = new Vector<String>();
	        for ( int i = 0; i < 100; i++ ) {
	            v.add( "Data " + i );
	        }
	        e.setListData( v );
	}
}
