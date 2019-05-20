package presentacion;

import presentacion.Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.*;

public class EvaluarMaquinas {

	JFrame f;
	JPanel maquina;
	JList list;
	
	public EvaluarMaquinas() {
		f = new JFrame();
		SetFrame();
		JPanel menu = Menu.MenuPeque("MAQUINA");
		f.getContentPane().add(menu, BorderLayout.WEST);
		maquina = new JPanel();
		maquina.setBackground(Color.WHITE);
		SetPanelMaquina();
		f.getContentPane().add(maquina, BorderLayout.CENTER);
		
	}
	private void SetPanelMaquina() {
		maquina.setLayout(new BorderLayout());
		//maquina.setBounds(230, 0, 859, 803);
		maquina.setBounds(0,0, 1000, 700);
		//PANEL 1:
		JPanel P1 = new JPanel();
		P1.setLayout(new GridLayout(4,2));
		
		JLabel text1 = new JLabel("Escoge las dos maquinas a evaluar:");
		JLabel text2 = new JLabel("Jugador 1:");
		JLabel text3 = new JLabel("Jugador 2:");
		JLabel text4 = new JLabel("Escoge los problemas a evaluar:");
		
		Vector maq = new Vector();
		maq.add("Maquina 1");
		maq.add("Maquina 2");
		
		JComboBox cb1 = new JComboBox(maq);
		JComboBox cb2 = new JComboBox(maq);
		
		P1.add(text1);
		P1.add(Box.createRigidArea(new Dimension(0, 30)));
		P1.add(text2);
		P1.add(cb1);
		P1.add(text3);
		P1.add(cb2);
		P1.add(text4);
		
		
		//PANEL 2:
		list = new JList();
		 JScrollPane scroll = new JScrollPane(list);
		
		//PANEL 3:
		JPanel P3 = new JPanel();
		JButton M = new JButton("Evaluar");
		JButton H = new JButton("Home");
		//M.setBounds(297,650,347,42);
		//H.setBounds(1000, 700, 230, 54);
		M.setBackground(Color.YELLOW);
		Font f1 = new Font (Font.DIALOG_INPUT, Font.BOLD, 25);
		M.setFont(f1);
		P3.add(M);
		P3.add(Box.createRigidArea(new Dimension(300, 30)));
		P3.add(H);
		
		
		
		//Add panels to panel maquina:
		maquina.add(P1,BorderLayout.NORTH);
		maquina.add(scroll,BorderLayout.CENTER);	         
        maquina.add(P3, BorderLayout.SOUTH);
	}
	public void SetFrame() {
		f.setBounds (400,150,1089,803);
		f.setVisible(true);
		f.setTitle("Chess Game");
		f.setLayout(new BorderLayout());
		
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		/*f.pack();
        f.show();*/

	}
    public void setListData( Vector vect ) {
        list.setListData( vect );
    }
	public static void main(String[] args) {
		EvaluarMaquinas em = new EvaluarMaquinas();
		 Vector v = new Vector();
	        for ( int i = 0; i < 100; i++ ) {
	            v.add( "Data " + i );
	        }
	    em.setListData( v );
	}

}
