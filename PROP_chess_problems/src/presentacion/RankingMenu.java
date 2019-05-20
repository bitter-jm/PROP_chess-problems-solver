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

public class RankingMenu {
	JFrame f;
	JPanel ranking;
	JList list;
	
	public RankingMenu() {
		f = new JFrame();
		setFrame();
		JPanel menu = Menu.MenuPeque("RANKING");
		ranking = new JPanel();
		ranking.setBackground(Color.WHITE);
		setPanelRanking();
		f.getContentPane().add(menu, BorderLayout.WEST);
		f.getContentPane().add(ranking, BorderLayout.CENTER);
	}
	public void setFrame() {
		f.setBounds (400,150,1089,803);
		f.setVisible(true);
		f.setTitle("Chess Game");
		f.setLayout(new BorderLayout());
		
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		/*f.pack();
        f.show();*/
	}
	
	public void setPanelRanking() {
		ranking.setLayout(new BorderLayout());
		//ranking.setBounds(230, 0, 859, 803);
		//PANEL 1:
		JPanel P1 = new JPanel();
		JLabel text1 = new JLabel("Elige el problema:");
		P1.add(text1);
		//PANEL 2:
		list = new JList();
		 JScrollPane scroll = new JScrollPane(list);
		//PANEL 3:
		JPanel P3 = new JPanel();
		JButton M = new JButton("Ver Ranking");
		JButton H = new JButton("Home");
		M.setBackground(Color.YELLOW);
		Font f1 = new Font (Font.DIALOG_INPUT, Font.BOLD, 25);
		M.setFont(f1);
		P3.add(M);
		P3.add(H);
		//Add panels to panel maquina:
		ranking.add(P1,BorderLayout.NORTH);
		ranking.add(scroll,BorderLayout.CENTER);	         
        ranking.add(P3, BorderLayout.SOUTH);
	}
	
	
    public void setListData( Vector vect ) {
        list.setListData( vect );
    }
	public static void main(String[] args) {
		RankingMenu rm= new RankingMenu();
		Vector v = new Vector();
        for ( int i = 0; i < 100; i++ ) {
            v.add( "Data " + i );
        }
        rm.setListData( v );

	}

}
