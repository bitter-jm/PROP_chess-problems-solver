package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class LogIn{
	JFrame f;
	JPanel cplog;
	JPanel cpsign;
	JLabel fondo;
	Font f1 = new Font (Font.MONOSPACED, Font.BOLD,30);
	Font f2 = new Font (Font.DIALOG_INPUT, Font.BOLD,20);
	
	private void SetFrame () {
		f.setBounds (400,150,1089,803);
		f.setVisible(true);
		f.setTitle("Chess Game");
		f.setLayout(null);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	private void SetFondoPic() {
		ImageIcon icic = new ImageIcon (new ImageIcon ("C:/Users/natal/Desktop/Board.png").getImage().getScaledInstance(1100, 1100, Image.SCALE_DEFAULT));
		//JLabel fondo = new JLabel (new ImageIcon("C:/Users/natal/Desktop/Board.png"));
		fondo = new JLabel();
		fondo.setIcon(icic);
	}
	
	public void SetPanelLogin() {
		//Panel
		cplog = new JPanel();
		cplog.setBackground(Color.WHITE);
		cplog.setBounds(150, 270, 351, 428);
		cplog.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel log = new JLabel("Login");
		log.setFont(f1);
		//log.setLocation(160, 30);
		cplog.add(log);
		
		JLabel username = new JLabel("Username:");
		username.setFont(f2);
		//username.setLocation(160, 70);
		cplog.add(username);
		JTextField tusernamelog = new JTextField(30);
		//username.setLocation(160, 70);
		cplog.add(tusernamelog);
		
		JLabel password = new JLabel("Password:");
		password.setFont(f2);
		//password.setLocation(160, 70);
		cplog.add(password);
		JTextField tpasswordlog = new JTextField();
		//tpasswordlog.setLocation(160, 70);
		cplog.add(tpasswordlog);		
	}
	
	public void SetPanelSignin() {
		//Panel
		cpsign = new JPanel();
		cpsign.setBackground(Color.WHITE);
		cpsign.setBounds(600, 270, 351, 428);
		cpsign.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel sign = new JLabel("Sign In");
		sign.setFont(f1);
		//sign.setLocation(100, 30);
		cpsign.add(sign);
		
		JLabel username2 = new JLabel("Username:");
		username2.setFont(f2);
		//username2.setLocation(160, 70);
		cpsign.add(username2);
		JTextField tusernamesign = new JTextField();
		//tusernamesign.setLocation(160, 70);
		cpsign.add(tusernamesign);
		
		JLabel cpassword = new JLabel("Confirm Password");
		cpassword.setFont(f2);
		//cpassword.setLocation(160, 70);
		cpsign.add(cpassword);
		JTextField tpasswordsign = new JTextField();
		//tpasswordsign.setLocation(160, 70);
		cpsign.add(tpasswordsign);
		
		JLabel password2 = new JLabel("Password:");
		password2.setFont(f2);
		//password2.setLocation(160, 70);
		cpsign.add(password2);
		JTextField tpasswordsign2 = new JTextField();
		//tpasswordsign2.setLocation(160, 70);
		cpsign.add(tpasswordsign2);
	}
	public LogIn() {
		//Frame con fondo
		f = new JFrame();
		SetFrame();
		SetFondoPic();
		SetPanelLogin();
		SetPanelSignin();

		f.add(cplog);
		f.add(cpsign);
		f.add(fondo);
	}
	
	
	
	public static void main(String[] args) {
		LogIn l = new LogIn();

	}

}
