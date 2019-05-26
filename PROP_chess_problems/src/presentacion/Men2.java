package presentacion;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.Border;

public class Men2 {
	
	JButton Jugar, MisProb, Ranking, Maquinas, SignOut;
	JLabel titulo1, titulo2;

	Font f1 = new Font ("AR BONNIE", Font.BOLD,100);
	Font f2 = new Font ("Segoe Script", Font.PLAIN,50);
	static Font f3 = new Font ("Consolas", Font.BOLD,20);
	Font f4 = new Font ("AR BONNIE", Font.BOLD,50);
	 
	Color cbutton = new Color (252,177,85);
	static Color mpeque = new Color (239, 247, 247);
	
	
	public Men2() {}
	
	public JFrame MenuGrande() {
		//FRAME
		JFrame f = new JFrame();
		f.setBounds (400,150,1089,803); //x, y, width, height
		f.setTitle("Chess Game");
		f.setLayout(null);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		
		//PANEL 1: Main Menu
		JPanel cp1 = new JPanel();
		cp1.setBackground(Color.WHITE);
		cp1.setBounds(0,0, 1089, 180);
		cp1.setLayout(null);
				
		JButton home1 = new JButton("iconocasa");
		home1.setBounds(330, 70, 80,80);
		cp1.add(home1);
				
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
		return f;
		

	}
	
	public void setuser(String s) {
		titulo2.setText("Welcome "+ s);
	}
	
	public static JPanel MenuPeque(String s) {
		
		JPanel cpmenu = new JPanel();
		cpmenu.setBackground(mpeque);
		cpmenu.setLayout( new BoxLayout (cpmenu, BoxLayout.Y_AXIS));;
		cpmenu.setLayout(null);
		cpmenu.setBounds(0, 0, 230, 803);
		
		JButton Jugar = new JButton ("JUGAR");
		Jugar.setBounds(0,150,230,54);
		Jugar.setFont(f3);
		JButton MisProb = new JButton ("MIS PROBLEMAS");
		MisProb.setBounds(0,204,230,54);
		MisProb.setFont(f3);
		JButton Ranking = new JButton ("RANKING");
		Ranking.setBounds(0,258,230,54);
		Ranking.setFont(f3);
		JButton Maquinas = new JButton ("EVALUAR MAQUINAS");
		Maquinas.setBounds(0,312,230,54);
		Maquinas.setFont(f3);
		
		JButton SignOut = new JButton("SIGN OUT");
		SignOut.setBounds(0, 650, 230, 54);
		SignOut.setFont(f3);
		
		if (s=="JUGAR") Jugar.setEnabled(false);
		else if (s=="PROBLEMAS") MisProb.setEnabled(false);
		else if (s=="RANKING") Ranking.setEnabled(false);
		else if (s=="MAQUINA") Maquinas.setEnabled(false);
		
		cpmenu.add(Jugar);
		cpmenu.add(MisProb);
		cpmenu.add(Ranking);
		cpmenu.add(Maquinas);
		//cpmenu.add(Box.createRigidArea(new Dimension(0, 30)));
		cpmenu.add(SignOut);
		
		return cpmenu;
	
	}
	
/*
	public static void main(String[] args) {
		Men2 em = new Men2();
		em.MenuGrande();
		//em.MenuPeque("Maquina");
		em.setuser("anita.gibbs");
		
	}*/

}
