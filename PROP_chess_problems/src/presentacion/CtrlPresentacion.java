package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JOptionPane;

import domain.CtrlPersona;
import domain.CtrlProblema;
import presentacion.CtrlRanking;
import presentacion.LogIn2;
public class CtrlPresentacion implements ActionListener {
	public static  CtrlPresentacion single_instance = null;
	private CtrlPersona CDpers; 
	private CtrlRanking CtrlR;
	private CtrlEvaluaciones CtrlE;
	private CtrlProblemas CtrlP;
	protected Men2 menu;
	protected LogIn2 l;
	boolean log = false;
	
	public CtrlPresentacion () {
		CDpers = CtrlPersona.getInstance();
			l = new LogIn2();
		//l.f.show(true);
			l.conectaControlador(this);
	}
	//hey
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String comando = arg0.getActionCommand();
		if(comando =="LOGIN") {
			String u = l.tusernamelog.getText();
			String p= l.tpasswordlog.getText();
			if(CDpers.login(u,p)) {
				//l.f.show(false);
				menu = new Men2();
				menu.MenuGrande();
				menu.cual ="BIG";
				menu.conectaControlador(this);
				 l.f.dispose();	
			}
			else {
				int op = JOptionPane.showConfirmDialog(null, "Usuario o contraseña incorrecto","WARNING", JOptionPane.CLOSED_OPTION);
				l.tusernamelog.setText(null);
				l.tpasswordlog.setText(null);
			}
		}
		if (comando == "SIGNIN") {
			String u = l.tusernamesign.getText();
			String p= l.tpasswordsign.getText();
			String p2= l.tpasswordsign2.getText();
			if (p.equals(p2)) {
				if(CDpers.signIn(u,p)) {
					menu = new Men2();
					menu.MenuGrande();
					menu.cual ="BIG";
					menu.conectaControlador(this);
					 l.f.dispose();	
				}
				else {
					int op = JOptionPane.showConfirmDialog(null, "Ya existe un usuario con ese nombre","WARNING", JOptionPane.CLOSED_OPTION);
					 l.tusernamesign.setText(null);
					 l.tpasswordsign.setText(null);
					 l.tpasswordsign2.setText(null);
				}
			}
			else {
				int op = JOptionPane.showConfirmDialog(null, "Las contraseñas no coinciden","WARNING", JOptionPane.CLOSED_OPTION);
				 l.tusernamesign.setText(null);
				 l.tpasswordsign.setText(null);
				 l.tpasswordsign2.setText(null);
			}
		}
		if(comando =="JUGAR");
		else if (comando =="PROBLEMAS") {
			if (menu.cual =="BIG") menu.f.show(false);
			CtrlP = CtrlProblemas.getInstance();
			CtrlP.show();
		}
		else if (comando == "RANKING") {
		
			if (menu.cual =="BIG") menu.f.show(false);
	
			CtrlR = CtrlRanking.getInstance();
			CtrlR.show();
			
		}
		else if (comando == "MAQUINAS") {
			if (menu.cual =="BIG") menu.f.show(false);
			CtrlE = CtrlEvaluaciones.getInstance();
			CtrlE.show();
		}
		else if (comando == "SIGNOUT") {
			int op = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cerrar sesion?","WARNING", JOptionPane.OK_CANCEL_OPTION);
			
			//CtrlP = CtrlPresentacion.getInstance();
		}
	
	}
	public static CtrlPresentacion getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlPresentacion(); 
        return single_instance;
    }

	public static void main(String[] args) {
		CtrlPresentacion CtrlP = CtrlPresentacion.getInstance();
	}
	
}
