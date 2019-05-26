package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import domain.CtrlPersona;
import domain.CtrlProblema;
import presentacion.Controller;
import presentacion.LogIn2;
public class CtrlPresentacion implements ActionListener {
	private static  CtrlPresentacion single_instance = null;
	private CtrlPersona CDpers; 
	private Men2 menu;
	private LogIn2 l;
	public CtrlPresentacion () {
		CDpers = CtrlPersona.getInstance();
		l = new LogIn2();
		l.f.show(true);
		l.conectaControlador(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String comando = arg0.getActionCommand();
		if(comando =="LOGIN") {
			String u = l.tusernamelog.getText();
			String p= l.tpasswordlog.getText();
			if(CDpers.login(u,p)) {
				menu = new Men2();
				menu.MenuGrande();
				l.f.show(false);
			}
		}
		if (comando == "SIGNIN") {
			
		}
	}
	public static CtrlPresentacion getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlPresentacion(); 
        return single_instance;
    }

	public static void main(String[] args) {
		//CtrlPresentacion c = new CtrlPresentacion();
		CtrlPresentacion CtrlP = CtrlPresentacion.getInstance();
	}
	
}
