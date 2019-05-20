package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Vector;

import javax.swing.*;

public class MisProblemas {
	JFrame f;
	JPanel problemas;
	JList list;
	
	public MisProblemas() {
		f = new JFrame();
		SetFrame();
		JPanel menu = Menu.MenuPeque("MAQUINA");
		f.getContentPane().add(menu, BorderLayout.WEST);
		problemas = new JPanel();
		problemas.setBackground(Color.WHITE);
		SetPanelProblemas();
		f.getContentPane().add(problemas, BorderLayout.CENTER);
		
	}	
	private void SetPanelProblemas() {
		
		Font f1 = new Font (Font.DIALOG_INPUT, Font.BOLD, 25);
		problemas.setLayout(new BorderLayout());
		problemas.setBounds(0,0, 1000, 700);
		//PANEL 1:
		JPanel P1 = new JPanel();
		JButton CrearP = new JButton ("CREAR PROBLEMA");
		CrearP.setBounds(250,60,753,54);
		CrearP.setBackground(Color.GREEN);	
		CrearP.setFont(f1);
		P1.add(CrearP);
		//PANEL 2:
		list = new JList();
		JScrollPane scroll = new JScrollPane(list);
		
		//PANEL 3:
		JPanel P3 = new JPanel();
		P3.setLayout(new FlowLayout());
		
		JButton EditP = new JButton ("EDITAR PROBLEMA");
		//EditP.setBounds(297,650,347,42);
		EditP.setBackground(Color.CYAN);
		EditP.setFont(f1);
		P3.add(EditP);
		
		JButton EliminarP = new JButton ("ELIMINAR PROBLEMA");
		//EliminarP.setBounds(670,650,347,42);
		EliminarP.setBackground(Color.RED);	
		EliminarP.setFont(f1);
		P3.add(EliminarP);
		
		
		//Add panels to panel maquina:
		problemas.add(P1,BorderLayout.NORTH);
		problemas.add(scroll,BorderLayout.CENTER);	         
		problemas.add(P3, BorderLayout.SOUTH);
	}
	
	public void SetFrame() {
		f.setBounds (400,150,1089,803);
		f.setVisible(true);
		f.setTitle("Chess Game");
		f.setLayout(new BorderLayout());
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	}
	
    public void setListData( Vector vect ) {
        list.setListData( vect );
    }
    
	public static void main(String[] args) {		
		MisProblemas p = new MisProblemas();
		 Vector v = new Vector();
	        for ( int i = 0; i < 100; i++ ) {
	            v.add( "Data " + i );
	        }
	    p.setListData( v );
	}

}
