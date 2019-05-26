package presentacion;

import java.awt.Color;
import java.awt.Font;
//import java.awt.Image;

import javax.swing.*;


public class LogIn2{
	JFrame f;
	JPanel cp;
	JPanel cplog;
	JPanel cpsign;
	JPanel fondo;
	JButton submit1;
	JButton submit2;
	Font f1 = new Font ("Segoe Script", Font.BOLD,30);
	Font f2 = new Font ("Consolas", Font.PLAIN,20);
	Font f3 = new Font ("AR ESSENCE", Font.PLAIN,100);
	Color cbutton = new Color(72,173,229);
	Color cinsert = new Color(242,242,242);
	JTextField tusernamelog;
	JTextField tpasswordlog;
	public LogIn2() {
		f = new JFrame();
		SetFrame();
		SetFondoPic();
		SetTitle();
		SetPanelLogin();
		SetPanelSignin();
		f.setResizable(true);
		f.setVisible(true);
	}
	
	private void SetFrame () {
		f.setBounds (400,150,1089,803);
		f.setTitle("Chess Game");
		f.setLayout(null);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	}
	
	private void SetFondoPic() {
		f.setContentPane(new JLabel(new ImageIcon (new ImageIcon("C:/Users/natal/eclipse-workspace/Tali/src/presentacion/Board.png").getImage().getScaledInstance(1200, 1200 ,java.awt.Image.SCALE_SMOOTH))));
		
	}
	
	public void SetTitle() {
		cp = new JPanel();
		cp.setBackground(null);
		cp.setBounds(500, 0, 351, 200);
		cp.setLayout(null);
		 cp.setOpaque(false);
		
		JLabel title = new JLabel("Chess");
		title.setBounds(10,40, 200, 80);
		title.setFont(f3);
		title.setForeground(Color.ORANGE);
		cp.add(title);
		JLabel title2 = new JLabel("Problems");
		title2.setBounds(10, 120, 350, 80);
		title2.setFont(f3);
		title2.setForeground(Color.ORANGE);
		cp.add(title2);
		f.add(cp);
	}
	public void SetPanelLogin() {
		cplog = new JPanel();
		cplog.setBackground(Color.WHITE);
		cplog.setBounds(150, 270, 351, 428);
		cplog.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cplog.setLayout(null);
		f.add(cplog);
		
		JLabel log = new JLabel("Log In");
		log.setBounds(130, 20, 110, 50);
		log.setFont(f1);
		
		JLabel username = new JLabel("Username:");
		username.setBounds(40, 100, 110, 20);
		username.setFont(f2);
		cplog.add(username);
		
		tusernamelog = new JTextField(30);
		tusernamelog.setBounds(40, 120, 268, 40);
		tusernamelog.setBackground(cinsert);
		cplog.add(tusernamelog);
		
		JLabel password = new JLabel("Password:");
		password.setBounds(40, 185, 110, 20);
		password.setFont(f2);
		cplog.add(password);
		
		tpasswordlog = new JTextField();
		tpasswordlog.setBounds(40, 205, 268, 40);
		tpasswordlog.setBackground(cinsert);
		cplog.add(tpasswordlog);
		
		submit1 = new JButton("SUBMIT");
		submit1.setBounds(75, 375, 200, 35);
		submit1.setBackground(cbutton);
		submit1.setForeground(Color.white);
		submit1.setFont(f2);
		cplog.add(submit1);
		cplog.add(log);
	}
	
	public void SetPanelSignin() {
		cpsign = new JPanel();
		cpsign.setBackground(Color.WHITE);
		cpsign.setBounds(600, 270, 351, 428);
		cpsign.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cpsign.setLayout(null);
		f.add(cpsign);
		
		JLabel sign = new JLabel("Sign In");
		sign.setBounds(125, 20, 160, 50);
		sign.setFont(f1);
		cpsign.add(sign);
		
		JLabel username2 = new JLabel("Username:");
		username2.setBounds(40, 100, 110, 20);
		username2.setFont(f2);
		cpsign.add(username2);
				
		JTextField tusernamesign = new JTextField();
		tusernamesign.setBounds(40, 120, 268, 40);
		tusernamesign.setBackground(cinsert);
		cpsign.add(tusernamesign);
		
		JLabel cpassword = new JLabel("Password:");
		cpassword.setBounds(40, 185, 110, 20);
		cpassword.setFont(f2);
		cpsign.add(cpassword);
		
		JTextField tpasswordsign = new JTextField();
		tpasswordsign.setBounds(40, 205, 268, 40);
		tpasswordsign.setBackground(cinsert);
		cpsign.add(tpasswordsign);
		
		JLabel password2 = new JLabel("Confirm Password:");
		password2.setBounds(40, 270, 268, 20);
		password2.setFont(f2);
		cpsign.add(password2);
		
		JTextField tpasswordsign2 = new JTextField();
		tpasswordsign2.setBounds(40, 290, 268, 40);
		tpasswordsign2.setBackground(cinsert);
		cpsign.add(tpasswordsign2);
		
		submit2 = new JButton("SUBMIT");
		submit2.setBounds(75, 375, 200, 35);
		submit2.setBackground(cbutton);
		submit2.setForeground(Color.white);
		submit2.setFont(f2);
		cpsign.add(submit2);
	}
	


	public void conectaControlador(CtrlPresentacion ctrlPresentacion) {
		// TODO Auto-generated method stub
		submit1.addActionListener(ctrlPresentacion);
		submit1.setActionCommand("LOGIN");
		submit2.addActionListener(ctrlPresentacion);
		submit2.setActionCommand("SIGNIN");
	}

}
