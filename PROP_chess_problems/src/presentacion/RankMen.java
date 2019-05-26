package presentacion;
import presentacion.Men2;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RankMen {
	JFrame f;
	JPanel cp1;
	JList list;
	Font f1 = new Font ("Britannic Bold", Font.PLAIN,25);
	Color c1= new Color (239, 255, 254);
	Color green = new Color (163, 255, 186);
	Color darkgreen = new Color (57,155,85);
	JButton ver;
	JButton home;
	  //DEFINICIÓN DE LOS OBJETOS PARA LA TABLA
    private JScrollPane scroll; //Panel de scroll que contiene la tabla
    public static Object[][] datos; //Cuerpo de la tabla
    protected String[] cabecera;    //Cabecera de la tabla
    protected DefaultTableModel dtm;//Unión de la cabecera y la tabla
    protected JTable tabla; //Tabla propiamente dicha
 
	public RankMen(Object[][] datos) {
		RankMen.datos = datos;
		f = new JFrame();
		SetFrame();
		JPanel menu = Men2.MenuPeque("RANKING");
		f.add(menu);
		SetPanelRankMenu();
		f.setVisible(true);
	
	}
	public void SetFrame() {
		f.setBounds (400,150,1089,803);
		f.setTitle("Chess Game");
		f.setLayout(null);
		f.setResizable(false);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	public void SetPanelRankMenu() {
		cp1 = new JPanel();
		cp1.setBounds(230,0,859,803);
		cp1.setLayout(null);
		cp1.setBackground(c1);
		
		JLabel text1 = new JLabel("Elige el problema:");
		text1.setBounds(90, 50, 350, 30);
		text1.setFont(f1);
		cp1.add(text1);
		/*
		list = new JList();
		JScrollPane scroll = new JScrollPane(list);
		*//*
		Object[][] data ={
		        {"Kathy","Snowboarding", new Integer(5), new Boolean(false)},
		        {"John","Rowing", new Integer(3), new Boolean(true)},
		        {"Sue","Knitting", new Integer(2), new Boolean(false)},
		        {"Jane","Speed reading", new Integer(20), new Boolean(true)},
		        {"Joe","Pool", new Integer(10), new Boolean(false)}
		    };*/
		 scroll = new JScrollPane();
	     cabecera = new String[] {"PROBLEMA","DESCRIPCION","DIFICULTAD","VECES JUGADO"};
	     dtm= new DefaultTableModel(datos,cabecera);
	     tabla= new JTable(dtm);
	     scroll.setViewportView(tabla);
	     scroll.setBounds(90, 100, 660, 525);
	     scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		 cp1.add(scroll);
		
		ver = new JButton("VER RANKING");
		ver.setBounds(200, 670, 450, 50);
		ver.setBackground(green);
		ver.setForeground(darkgreen);
		ver.setFont(f1);
		cp1.add(ver);
		
		ImageIcon house = new ImageIcon (new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/home.png").getImage().getScaledInstance(40, 40 ,java.awt.Image.SCALE_SMOOTH));
		home = new JButton(house);
		home.setBounds(755, 670, 68, 68);
		home.setBackground(c1);
		home.setBorder(null);
		cp1.add(home);
			
		f.add(cp1);
	}
	
	  public void setListData( Object[][] data) {     
		  list.setListData(data);
	  }
	  
	  public void conectaControlador (Controller c) {
			home.addActionListener(c);
			home.setActionCommand("HOME");
			ver.addActionListener(c);
			ver.setActionCommand("RANKING");
			tabla.addMouseListener(c);
			tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
	  

	public static Object[][] getDatos() {
		return datos;
	}
	
	public static void setDatos(Object[][] datos) {
		RankMen.datos = datos;
	}

}
