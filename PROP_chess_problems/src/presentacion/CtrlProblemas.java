package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import data.CtrlData;
import domain.CtrlPersona;
import domain.CtrlProblema;
import presentacion.Evaluacion;
import presentacion.EvMaq;
import presentacion.Men2;

public class CtrlProblemas extends CtrlPresentacion implements ActionListener, MouseListener {
	private static  CtrlProblemas single_instance = null;
	private CtrlProblema CP;
	private CtrlData CData; 
	private Prob mp;
	private Editar e;
	private String prob;
	
	public CtrlProblemas() {
		CData = CtrlData.getInstance();
		CP = CtrlProblema.getInstance();
		String[][] data = CData.getMisProblemas();
		mp = new Prob(data);
		mp.m.conectaControlador(super.getInstance());
		mp.f.show(true);
		mp.conectaControlador(this);
	}
	public void show() {
		mp.f.show();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String comando = arg0.getActionCommand();
		if(comando =="HOME") {
			
		}
		if (comando == "NUEVO") {
			
		}
		if(comando =="EDIT") {
			CP.cargarProblema(prob);
			String FEN = CP.getFEN();
			e = new Editar();
			e.t.initializeBoardFEN(FEN);
			
		}
		if (comando == "ELIMINAR") {
			CP.cargarProblema(prob);
			CP.eliminarProblema();
			String[][] data = CData.getMisProblemas();
			mp.setdata(data);
		}
		
	}
	
	public static CtrlProblemas getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlProblemas(); 
        return single_instance;
    }
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		 int column = 0;
    	 int row = mp.tabla.getSelectedRow();
    	this.prob = mp.tabla.getModel().getValueAt(row, column).toString();
	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
