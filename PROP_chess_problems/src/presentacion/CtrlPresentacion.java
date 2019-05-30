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
		}
		if (comando == "SIGNIN") {
			
		}
		if(comando =="JUGAR");
		else if (comando =="PROBLEMAS") {
			CtrlP = CtrlProblemas.getInstance();
		}
		else if (comando == "RANKING") {
			//donde = "RANKING";
			
			if (menu.cual =="BIG") menu.f.show(false);// f.dispose();
		//	CtrlR = new CtrlRanking();
			CtrlR = CtrlRanking.getInstance();
			
		}
		else if (comando == "MAQUINAS") {
			CtrlE = CtrlEvaluaciones.getInstance();
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
