package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class Men2 /*implements ActionListener */{
	private CtrlRanking CtrlR;
	private CtrlPresentacion CtrlP;
	JFrame f;
	JButton Jugar, MisProb, Ranking, Maquinas, SignOut;
	static JButton JugarP ,MisProbP,RankingP,MaquinasP,SignOutP;
	JLabel titulo1, titulo2;
	static String cual;
	
	Font f1 = new Font ("AR BONNIE", Font.BOLD,100);
	Font f2 = new Font ("Segoe Script", Font.PLAIN,50);
	static Font f3 = new Font ("Consolas", Font.BOLD,20);
	Font f4 = new Font ("AR BONNIE", Font.BOLD,50);
	static Font f5 = new Font ("Arial", Font.BOLD,15);
	
	Color cbutton = new Color (252,177,85);
	static Color mpeque = new Color (239, 247, 247);
		
	public Men2() {	}
	
	public void MenuGrande() {
		//FRAME
		f = new JFrame();
		f.show(true);
		f.setBounds (400,150,1089,803);
		f.setTitle("Chess Game");
		f.setLayout(null);
		f.setResizable(false);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		cual = "BIG";
		
		//PANEL 1: Main Menu
		JPanel cp1 = new JPanel();
		cp1.setBackground(Color.WHITE);
		cp1.setBounds(0,0, 1089, 180);
		cp1.setLayout(null);
				
		ImageIcon house = new ImageIcon (new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/home.png").getImage().getScaledInstance(100, 100 ,java.awt.Image.SCALE_SMOOTH));
		JLabel label = new JLabel(house, JLabel.CENTER);
		label.setBounds(330, 70, 80, 80);
		cp1.add(label);
		
		titulo1 = new JLabel("Main Menu");
		titulo1.setBounds(430,70,380,80);
		titulo1.setFont(f1);
		titulo1.setForeground(cbutton);
		cp1.add(titulo1);
		f.add(cp1);
		
		//PANEL 2:
		JPanel cp2 = new JPanel();
		cp2.setBackground(Color.ORANGE);
		cp2.setBounds(0,180, 1089, 3);
		cp2.setLayout(null);
		f.add(cp2);
		
		//PANEL 3: Welcome
		JPanel cp3 = new JPanel();
		cp3.setBackground(Color.WHITE);
		cp3.setBounds(0,183,1089,620);
		cp3.setLayout(null);
		
		//BOTONES
		Jugar = new JButton("JUGAR");
		Jugar.setBounds(80,210,420,70);
		Jugar.setFont(f4);
		Jugar.setBackground(cbutton);
		Jugar.setForeground(Color.WHITE);
		cp3.add(Jugar);
		
		Ranking = new JButton("RANKINGS");
		Ranking.setBounds(580,210,420,70); 
		Ranking.setFont(f4);
		Ranking.setBackground(cbutton);
		Ranking.setForeground(Color.WHITE);
		cp3.add(Ranking);
		
		MisProb = new JButton("MIS PROBLEMAS");
		MisProb.setBounds(80,330,420,70); 
		MisProb.setFont(f4);
		MisProb.setBackground(cbutton);
		MisProb.setForeground(Color.WHITE);
		cp3.add(MisProb);
		
		Maquinas = new JButton("EVALUAR MAQUINAS");	
		Maquinas.setBounds(580,330,420,70);
		Maquinas.setFont(f4);
		Maquinas.setBackground(cbutton);
		Maquinas.setForeground(Color.WHITE);
		cp3.add(Maquinas);
		
		ImageIcon out = new ImageIcon (new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/sign-out.png").getImage().getScaledInstance(30, 30 ,java.awt.Image.SCALE_SMOOTH));
		SignOut = new JButton("Sign Out",out);
		SignOut.setBounds(800, 500, 200, 30);
		SignOut.setFont(f3);
		SignOut.setBackground(Color.WHITE);
		SignOut.setBorderPainted(false);
		SignOut.setForeground(Color.MAGENTA);
		cp3.add(SignOut);
		
		//TITULO DE LA PAGINA:
		titulo2 = new JLabel();
		titulo2.setBounds(80,80,800,60);
		titulo2.setFont(f2);
		cp3.add(titulo2);
		f.add(cp3);
		f.setVisible(true);
	//	AccionesGrande();
		//return f;
		

	}
	/*
	public void setuser(String s) {
		titulo2.setText("Welcome "+ s);
	}*/
	
	public static JPanel MenuPeque(String s) {
		
		JPanel cpmenu = new JPanel();
		cpmenu.setBackground(mpeque);
		cpmenu.setLayout( new BoxLayout (cpmenu, BoxLayout.Y_AXIS));;
		cpmenu.setLayout(null);
		cpmenu.setBounds(0, 0, 230, 803);
		cual = "SMALL";
		
		JLabel logged_as = new JLabel("Logged as:");
		logged_as.setBounds(5, 0, 200, 20);
		logged_as.setFont(f5);
		
		String nomuser = "getnomusuario";
		JLabel logname = new JLabel(nomuser);
		logname.setBounds(5, 20, 200, 20);
		logname.setFont(f5);
		
		
		JugarP = new JButton ("JUGAR");
		JugarP.setBounds(0,150,230,54);
		JugarP.setFont(f3);
		MisProbP = new JButton ("MIS PROBLEMAS");
		MisProbP.setBounds(0,204,230,54);
		MisProbP.setFont(f3);
		RankingP = new JButton ("RANKING");
		RankingP.setBounds(0,258,230,54);
		RankingP.setFont(f3);
		MaquinasP = new JButton ("EVALUAR MAQUINAS");
		MaquinasP.setBounds(0,312,230,54);
		MaquinasP.setFont(f3);
		
		SignOutP = new JButton("SIGN OUT");
		SignOutP.setBounds(0, 650, 230, 54);
		SignOutP.setFont(f3);
		
		if (s=="JUGAR") JugarP.setEnabled(false);
		else if (s=="PROBLEMAS") MisProbP.setEnabled(false);
		else if (s=="RANKING") RankingP.setEnabled(false);
		else if (s=="MAQUINA") MaquinasP.setEnabled(false);
		
		cpmenu.add(logged_as);
		cpmenu.add(logname);
		cpmenu.add(JugarP);
		cpmenu.add(MisProbP);
		cpmenu.add(RankingP);
		cpmenu.add(MaquinasP);
		//cpmenu.add(Box.createRigidArea(new Dimension(0, 30)));
		cpmenu.add(SignOutP);
		return cpmenu;
	
	}
	/*
	private void AccionesGrande() {
		Jugar.addActionListener( this);
		Jugar.setActionCommand("JUGAR");
		MisProb.addActionListener(this);
		MisProb.setActionCommand("PROBLEMAS");
		Ranking.addActionListener(this);
		Ranking.setActionCommand("RANKING");
		Maquinas.addActionListener(this);
		Maquinas.setActionCommand("MAQUINAS");
		SignOut.addActionListener(this);
		SignOut.setActionCommand("SIGNOUT");
	}
	public void AccionesPeque() {
		JugarP.addActionListener( this);
		JugarP.setActionCommand("JUGAR");
		MisProbP.addActionListener(this);
		MisProbP.setActionCommand("PROBLEMAS");
		RankingP.addActionListener(this);
		RankingP.setActionCommand("RANKING");
		MaquinasP.addActionListener(this);
		MaquinasP.setActionCommand("MAQUINAS");
		SignOutP.addActionListener(this);
		SignOutP.setActionCommand("SIGNOUT");
	}*/
	public void conectaControlador (CtrlPresentacion c) {
		if (cual == "BIG") {
		Jugar.addActionListener( c);
		Jugar.setActionCommand("JUGAR");
		MisProb.addActionListener(c);
		MisProb.setActionCommand("PROBLEMAS");
		Ranking.addActionListener(c);
		Ranking.setActionCommand("RANKING");
		Maquinas.addActionListener(c);
		Maquinas.setActionCommand("MAQUINAS");
		SignOut.addActionListener(c);
		SignOut.setActionCommand("SIGNOUT");
		}
		if (cual =="SMALL") {
		JugarP.addActionListener( c);
		JugarP.setActionCommand("JUGAR");
		MisProbP.addActionListener(c);
		MisProbP.setActionCommand("PROBLEMAS");
		RankingP.addActionListener(c);
		RankingP.setActionCommand("RANKING");
		MaquinasP.addActionListener(c);
		MaquinasP.setActionCommand("MAQUINAS");
		SignOutP.addActionListener(c);
		SignOutP.setActionCommand("SIGNOUT");
		}
	}/*
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if(comando =="JUGAR");
		else if (comando =="PROBLEMAS") ;
		else if (comando == "RANKING") {
			//donde = "RANKING";
			
			if (cual =="BIG") f.show(false);// f.dispose();
			CtrlR = new CtrlRanking();
			//CtrlR = CtrlRanking.getInstance();
			
		}
		else if (comando == "MAQUINAS") ;
		else if (comando == "SIGNOUT") CtrlP = CtrlPresentacion.getInstance();
	}*/
}
