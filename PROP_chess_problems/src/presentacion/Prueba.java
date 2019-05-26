package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.*;
public class Prueba extends JPanel {
	private JFrame f;
	private JList list;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	
	public Prueba() {
      setLayout(new BorderLayout());
      list = new JList();
      JPanel header = new JPanel();
      b1 = new JButton("b1");
      header.add(b1);
      JPanel bajo = new JPanel();
      b2 = new JButton("b2");
      b3 = new JButton("b3");
      bajo.setLayout(new FlowLayout());
      bajo.add(b2);
      bajo.add(b3);
      
      JScrollPane scroll = new JScrollPane(list);
      add(scroll,BorderLayout.CENTER);
      add(bajo,BorderLayout.SOUTH);
      add(header,BorderLayout.NORTH);    
		
	}
    public void setListData( Vector vect ) {
        list.setListData( vect );
    }
	
	public static void main(String[] args) {
		JFrame frame = new JFrame( "JList with Header" );
        Prueba ListayBotones = new Prueba();
        Vector v = new Vector();
        for ( int i = 0; i < 10; i++ ) {
            v.add( "Data " + i );
        }
        ListayBotones.setListData( v );
        JPanel p = new JPanel();
        p.add( ListayBotones );
        frame.getContentPane().add( p, BorderLayout.EAST );
        
        JButton M = new JButton("Menu");
        JPanel menu = new JPanel();
        menu.add(M);
        frame.getContentPane().add(menu, BorderLayout.WEST);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.pack();
        frame.show();

	}

}
