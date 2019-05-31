package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
	private int row;
	
	public CtrlProblemas() {
		l.f.show(false);
		CData = CtrlData.getInstance();
		CP = CtrlProblema.getInstance();
		String[][] data = CData.getMisProblemas();
		mp = new Prob(data);
		mp.m.conectaControlador(super.getInstance());
		mp.f.show(true);
		mp.conectaControlador(this);
	}
	public void show(Boolean b) {
		mp.f.show(b);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String comando = arg0.getActionCommand();
		if(comando =="HOME") {
			
		}
		if (comando == "NUEVO") {
			e = new Editar("BLANCAS");
		}
		
		if(comando =="EDIT") {
			//CP.cargarProblema(prob);
			String info =CData.getProblema(prob);
			String[] infovec = info.split("\n");
			String maxmov = infovec[1];
			String FEN = infovec[2];
			String Color = infovec[3];
			//String FEN = CP.getFEN();
			
			e = new Editar(Color);
			e.t.initializeBoardFEN(FEN);
			e.setInfo(maxmov, Color);
			e.conectaControlador(this);
			
		}
		if (comando == "ELIMINAR") {
			CP.cargarProblema(prob);
			CP.eliminarProblema();
			DefaultTableModel modelo = (DefaultTableModel) mp.tabla.getModel();
			modelo.removeRow(row);
		}
		
		if (comando =="CARGAR") {
			String FEN = e.fen.getText();
			e.t.initializeBoardFEN(FEN);
		}
		if (comando =="VALIDAR") {
			CP.cargarProblema(prob);
			CP.guardarProblema();
			Boolean b = CP.validarProblema();
			if (b) JOptionPane.showConfirmDialog(null, "¡El problema es valido!", "WARNING", JOptionPane.CLOSED_OPTION);
			else JOptionPane.showConfirmDialog(null, "¡El problema NO es valido!", "WARNING", JOptionPane.CLOSED_OPTION);
		}
		if (comando =="TERMINAR") {
			String nomp = e.nombre.getText();
			String num = e.num.getText();
			String col = (String) e.cb1.getSelectedItem();
			String w = e.t.getFenFromMatrix();
			String s = nomp + "\n" +num  + "\n" + w  + "\n" + col + "\n";
			//CData.saveProblema(nomp);
			mp.f.show(true);
			e.f.show(false);
			
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
    	 row = mp.tabla.getSelectedRow();
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
