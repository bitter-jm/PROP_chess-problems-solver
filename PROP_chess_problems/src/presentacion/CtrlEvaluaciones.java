package presentacion;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.*;
import data.CtrlData;
import domain.CtrlEvaluador;

public class CtrlEvaluaciones extends CtrlPresentacion implements ActionListener, MouseListener{
	private static  CtrlEvaluaciones single_instance = null;
	private CtrlData CData;
	private CtrlEvaluador CE;
	public EvMaq me;
	public Evaluacion e;
	public List<String> prob;
	public CtrlEvaluaciones() {
		crearMenuEva();
	}
	
	private void crearMenuEva() {
		l.f.show(false);
		// TODO Auto-generated method stub
		CData = CtrlData.getInstance();
		CE = CtrlEvaluador.getInstance();
		String [][] data = CData.getProblemasJugables();
		me = new EvMaq(data);
		me.m.conectaControlador(super.getInstance());
		me.conectaControlador(this);
		me.f.show(true);
		
	}
	public void show(Boolean b) {
		me.f.show(b);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String comando = arg0.getActionCommand();
		if(comando =="EVALUAR") {
			String s1 = me.cb1.getSelectedItem().toString();
			String s2 = me.cb2.getSelectedItem().toString();
			String[][] datam = CE.evaluarProblemas(prob, s1, s2);
			e = new Evaluacion(s1,s2,datam);
			e.f.show();

		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (me.tabla.getSelectedRowCount() > 0) {
			 int[] selectedRow = me.tabla.getSelectedRows();
			 DefaultTableModel modelo = (DefaultTableModel) me.tabla.getModel();
			 for (int i = 0; i < selectedRow.length; ++i) {
				int pos = selectedRow[i];
				prob = new ArrayList<>();
				String nom = (String) modelo.getValueAt(pos, 0);
				prob.add(nom);
			 }
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Obtiene la instacia del singleton CtrlRanking
	 * @return Objeto CtrlProblema
	 */
	public static CtrlEvaluaciones getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlEvaluaciones(); 
        return single_instance;
    }
/*	public static void main(String[] args) {
		CtrlEvaluaciones c = new CtrlEvaluaciones();
	}
*/
}
