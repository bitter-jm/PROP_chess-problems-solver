package presentacion;
import java.awt.*;
import java.util.Vector;

import javax.swing.*;

public class Tablero {
	JFrame f;
	JPanel t;
	//Esto sera de ser de mi clase de button que extenderea
	//JButton y implementara los listners
	JButton buttons[] = new JButton[64];
	public Tablero() {
		f = new JFrame();
		t = new JPanel();
		setFrame();
		setTablero();
		
	}
	private void setTablero() {
		t.setLayout(new GridLayout(8,8));
		
		boolean b = true;
		for (int i=0; i<64; ++i) {
			buttons[i] = new JButton();
			if (b) buttons[i].setBackground(Color.white);
			else buttons[i].setBackground(Color.black);
			t.add(buttons[i]);
			if (i%8!=0) b=!b;
			//b= !b;
		}
		f.add(t);
	}
	private void setFrame() {
		f.setVisible(true);
		f.setTitle("Chess Game");
		f.setSize(600,600);
		f.setResizable(true);
		f.setLayout(new BorderLayout());
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	}
	public static void main(String[] args) {
	 Tablero tab = new Tablero();
	}
}
