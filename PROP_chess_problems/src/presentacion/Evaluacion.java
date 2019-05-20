package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Evaluacion {
	JFrame f;
	JPanel evaluacio;
	JList list;
	JLabel t1;
	JLabel t2;
	
	public Evaluacion() {
		f = new JFrame();
		SetFrame();
		JPanel menu = Menu.MenuPeque("MAQUINA");
		f.getContentPane().add(menu, BorderLayout.WEST);
		evaluacio = new JPanel();
		evaluacio.setBackground(Color.WHITE);
		setPanelEvaluacio();
		f.getContentPane().add(evaluacio, BorderLayout.CENTER);
		
	}
	private void setPanelEvaluacio() {
		Font f1 = new Font (Font.MONOSPACED, Font.BOLD,30);
		evaluacio.setLayout(new BorderLayout());
		evaluacio.setBounds(0,0, 1000, 700);
		//PANEL 1:
		JPanel P1 = new JPanel();
		P1.setLayout(new GridLayout(2, 3));
		t1 = new JLabel();
		t2 = new JLabel();
		t1.setFont(f1);
		t2.setFont(f1);
		JLabel vs = new JLabel(" vs ");
		JLabel p1 = new JLabel("Player 1");
		JLabel p2 = new JLabel("Player 2");
		P1.add(t1);
		P1.add(vs);
		P1.add(t2);
		P1.add(p1);
		P1.add(Box.createRigidArea(new Dimension(0, 30)));
		P1.add(p2);
		
		//PANEL 2:	
		list = new JList();
		 JScrollPane scroll = new JScrollPane(list);
		 
		//PANEL 3:
		JPanel P3 = new JPanel();
		JButton NewE = new JButton ("NUEVA EVALUACION");
		NewE.setBounds(297,650,347,42);
		NewE.setBackground(Color.CYAN);
		Font f2 = new Font (Font.DIALOG_INPUT, Font.BOLD, 25);
		NewE.setFont(f2);
		P3.add(NewE);
		
		//Add panels to panel maquina:
		evaluacio.add(P1,BorderLayout.NORTH);
		evaluacio.add(scroll,BorderLayout.CENTER);	         
		evaluacio.add(P3, BorderLayout.SOUTH);
				
	}
	public  void setusers(String s1, String s2) {
		t1.setText(s1);
		t2.setText(s2);
	}
	private void SetFrame() {
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
		Evaluacion e = new Evaluacion();
		 Vector v = new Vector();
	        for ( int i = 0; i < 100; i++ ) {
	            v.add( "Data " + i );
	        }
	    e.setListData( v );
	    e.setusers("Maquina 1", "Maquina 2");
	}
	
}

