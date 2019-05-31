package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import presentacion.Tablero;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class Jugar implements ActionListener{
	JFrame f;
	JComboBox<String> cb1, cb2;
	JButton play, home, ord_tema, ord_hard;
	JList<String> list;
	Tablero t;
	Men2 m;
	
	Font f0 = new Font ("AR BONNIE", Font.BOLD,70);
	Font f1 = new Font ("Britannic Bold", Font.PLAIN,25);
	Font f2 = new Font ("Consolas", Font.BOLD,18);
	
	Color c1= new Color (239, 255, 254);
	Color green = new Color (163, 255, 186);
	Color darkgreen = new Color (57,155,85);
	Color blue = new Color(122, 221, 255);
	Color darkblue = new Color(0, 119, 204);
	Color violet = new Color(207, 186, 255);
	
	 private JScrollPane scroll; //Panel de scroll que contiene la tabla
	 public static Object[][] datos; //Cuerpo de la tabla
	 protected String[] cabecera;    //Cabecera de la tabla
	 protected DefaultTableModel dtm;//Unión de la cabecera y la tabla
	 protected JTable tabla; //Tabla propiamente dicha
	
	public Jugar() {
		f = new JFrame();
		//datos = d;
		t = new Tablero("JUGAR", this);
		SetFrame();
		m= new Men2();
		JPanel menu = m.MenuPeque("JUGAR");
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
		
		scroll = new JScrollPane();
	    cabecera = new String[] {"NOMBRE","NºJUGADAS", "COLOR", "DIFICULTAD","VECES JUGADO"};
	    dtm= new DefaultTableModel(datos,cabecera);
	    tabla= new JTable(dtm);
	    scroll.setViewportView(tabla);
	    scroll.setBounds(40, 150, 470, 460);
	    scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cp1.add(scroll);
		
		ord_tema = new JButton("Ordenar por tema");
		ord_tema.setBounds(520, 350, 160, 30);
		ord_tema.setBackground(violet);
		ord_tema.setForeground(Color.white);
		cp1.add(ord_tema);
		ord_hard = new JButton("Ordenar por dificultad");
		ord_hard.setBounds(680, 350, 160, 30);
		ord_hard.setBackground(violet);
		ord_hard.setForeground(Color.white);
		cp1.add(ord_hard);
		
		play = new JButton("¡JUGAR!");
		play.setBounds(40, 650, 460, 50);
		play.setBackground(blue);
		play.setForeground(darkblue);
		play.setFont(f1);
		cp1.add(play);
		
		ImageIcon house = new ImageIcon (new ImageIcon(getClass().getResource("home.png")).getImage().getScaledInstance(50, 50 ,java.awt.Image.SCALE_SMOOTH));
		home = new JButton(house);
		home.setBounds(755, 670, 68, 68);
		home.setBackground(Color.WHITE);
		home.setBorder(null);
		cp1.add(home);
			
		JPanel n = t.t;
		n.setBounds(550, 400, 250, 250);
		cp1.add(n);
		f.add(cp1);
		
	}
	public void conectaControlador (CtrlPartida c) {
		play.addActionListener(c);
		play.setActionCommand("JUGAR");
		home.addActionListener(c);
		home.setActionCommand("HOME");
		ord_tema.addActionListener(c);
		ord_tema.setActionCommand("TEMA");
		ord_hard.addActionListener(c);
		ord_hard.setActionCommand("HARD");
		tabla.addMouseListener(c);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}	
	
	public void setData( String[][] d ) {
        datos = d;
        dtm= new DefaultTableModel(datos,cabecera);
        tabla= new JTable(dtm);
        scroll.setViewportView(tabla);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
