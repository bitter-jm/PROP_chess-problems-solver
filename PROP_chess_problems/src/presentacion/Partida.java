package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import presentacion.Tablero;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Partida implements ActionListener {
	JFrame f;
	Tablero t;
	JButton cancel;
	
	Color c1= new Color (239, 255, 254);
	Color c2= new Color (197, 25, 25);
	Color darkblue = new Color(0, 119, 204);
	Font f1 = new Font ("Britannic Bold", Font.PLAIN,25);
	
	public Partida() {
		f = new JFrame();
		t = new Tablero("PARTIDA",this);
		SetFrame();
		SetPanel();
		f.setVisible(true);
	}
	public void SetFrame() {
		f.setBounds (400,150,1089,803);
		f.setTitle("Chess Game");
		f.setLayout(null);
		f.setResizable(false);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	public void SetPanel() {
		JPanel cp1 = new JPanel();
		cp1.setLayout(null);
		cp1.setBounds(0, 0, 1089,803);
		cp1.setBackground(c1);
		
		JLabel text = new JLabel("Turno de: ");
		text.setBounds(300,10,660,54);
		text.setFont(f1);
		text.setForeground(Color.LIGHT_GRAY);
		cp1.add(text);
		
		JPanel n = t.t;
		n.setBounds(250, 70, 580, 580);
		cp1.add(n);
		
		cancel = new JButton("Cancelar Partida");
		cancel.setBounds(300, 680, 450, 50);
		cancel.setBackground(c2);
		cancel.setForeground(Color.WHITE);
		cancel.setFont(f1);
		cp1.add(cancel);
		f.add(cp1);
	}

	public static void main(String[] args) {
		Partida p = new Partida();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void conectaControlador(CtrlPartida c) {
		// TODO Auto-generated method stub
		
	}

}
