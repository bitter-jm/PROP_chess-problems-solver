package presentacion;
import presentacion.Men2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

public class Prob {
	JFrame f;
	JPanel cp1, cp2, cp3;
	JList list;
	JButton CrearP, EditP, EliminarP,casa;
	Men2 m;
	
	Font f1 = new Font ("Britannic Bold", Font.PLAIN,25);

	Color c1= new Color (255, 201, 168);
	Color c2= new Color (239, 255, 254);
	Color green = new Color (163, 255, 186);
	Color darkgreen = new Color (57,155,85);
	Color yellow = new Color (250, 255, 183);
	Color red = new Color(255, 126, 117);
	Color darkred = new Color(255, 184, 183);

	
	 private JScrollPane scroll; //Panel de scroll que contiene la tabla
	 public static Object[][] datos; //Cuerpo de la tabla
	 protected String[] cabecera;    //Cabecera de la tabla
	 protected DefaultTableModel dtm;//Unión de la cabecera y la tabla
	 protected JTable tabla; //Tabla propiamente dicha
	
	public Prob(String[][] datam) {
		f = new JFrame();
		datos = datam;
		SetFrame();
		m= new Men2();
		JPanel menu = m.MenuPeque("PROBLEMAS");
		f.add(menu);
		SetPanelProblemas();
		f.setVisible(true);
	}
	
	public void SetFrame() {
		f.setBounds (400,150,1089,803);
		f.setTitle("Chess Game");
		f.setLayout(null);
		f.setResizable(false);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	private void SetPanelProblemas() {
		//PANEL 1:
		cp1 = new JPanel();
		cp1.setBounds(230,0,859,100);
		cp1.setBackground(c2);
		cp1.setLayout(null);
		
		CrearP = new JButton ("CREAR PROBLEMA NUEVO");
		CrearP.setBounds(90,30,660,54);
		CrearP.setBackground(green);
		CrearP.setForeground(darkgreen);
		CrearP.setFont(f1);
		cp1.add(CrearP);
		f.add(cp1);
		
		//PANEL 2:
		cp2 = new JPanel();
		cp2.setBounds(230, 100, 859, 525);
		cp2.setBackground(c2);
		cp2.setLayout(null);
		
		scroll = new JScrollPane();
	    cabecera = new String[] {"NOMBRE","Nº JUGADAS","COLOR","VALID","VECES JUGADO"};
	    dtm= new DefaultTableModel(datos,cabecera);
	    tabla= new JTable(dtm);
	    scroll.setViewportView(tabla);
	    scroll.setBounds(90, 0, 660, 525);
	    scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cp2.add(scroll);
		f.add(cp2);
		
		//PANEL 3:
		cp3 = new JPanel();
		cp3.setBounds(230, 625, 859, 203);
		cp3.setBackground(c2);
		cp3.setLayout(null);
		
		EditP = new JButton ("EDITAR PROBLEMA");
		EditP.setBounds(90,25,315,54);
		EditP.setBackground(yellow);
		EditP.setForeground(c1);
		EditP.setFont(f1);
		cp3.add(EditP);
		
		EliminarP = new JButton ("ELIMINAR PROBLEMA");
		EliminarP.setBounds(435,25,315,54);
		EliminarP.setBackground(red);
		EliminarP.setForeground(Color.WHITE);
		EliminarP.setFont(f1);
		cp3.add(EliminarP);
		
		ImageIcon house = new ImageIcon (new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/home.png").getImage().getScaledInstance(40, 40 ,java.awt.Image.SCALE_SMOOTH));
		casa = new JButton(house);
		casa.setBounds(770, 70, 60, 60);
		casa.setBackground(Color.WHITE);
		casa.setForeground(Color.WHITE);
		cp3.add(casa);
		f.add(cp3);
	}
	  public void conectaControlador (CtrlProblemas c) {
		  CrearP.addActionListener(c);
		  CrearP.setActionCommand("NUEVO");
		  EditP.addActionListener(c);
		  EditP.setActionCommand("EDIT");
		  EliminarP.addActionListener(c);
		  EliminarP.setActionCommand("ELIMINAR");
		  casa.addActionListener(c);
		  casa.setActionCommand("HOME");
		  tabla.addMouseListener(c);
		  tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	  }
	  
	  public void setdata( String[][] datam ) {
	        datos=datam;
	        f.getContentPane().revalidate();
	       f.getContentPane().repaint();
	    }


 
  /*  
   *    public void setListData( Vector<String> vect ) {
        list.setListData( vect );
    }
    
	public static void main(String[] args) {		
		Prob p = new Prob();
		 Vector<String> v = new Vector<String>();
	        for ( int i = 0; i < 100; i++ ) {
	            v.add( "Data " + i );
	        }
	    p.setListData( v );
	}*/
}
