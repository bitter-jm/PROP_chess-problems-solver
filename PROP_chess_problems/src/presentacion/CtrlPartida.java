package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import data.CtrlData;

public class CtrlPartida extends CtrlPresentacion implements ActionListener, MouseListener{
	private static CtrlPartida single_instance = null;
	private Jugar mpar;
	private Partida p;
	private CtrlData CData;
	String prob = null;
	public CtrlPartida() {
		l.f.show(false);
		CData = CtrlData.getInstance();
		String [][] data = CData.getProblemasJugablesOrdenadosPorMovimientos();
		mpar = new Jugar();
		//mpar.datos=data;
		mpar.setData(data);
		
		mpar.m.conectaControlador(super.getInstance());
		mpar.conectaControlador(this);
		mpar.f.show(true);
	}
	public void inijugar() {
		
		
	}
	public void show(Boolean b) {
		mpar.f.show(b);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String comando = arg0.getActionCommand();
		if(comando =="HOME") {
		
		}
		else if (comando == "JUGAR") {
			p = new Partida();
			mpar.f.dispose();
			//m.f.show(false);
			p.conectaControlador(this);
		}
		if(comando =="TEMA") {
			String [][] data = CData.getProblemasJugablesOrdenadosPorMovimientos();
			mpar.setData(data);
		}
		if(comando =="HARD") {
			String [][] data = CData.getProblemasJugablesOrdenadosPorDificultad();
			mpar.setData(data);
		}
		
	}
	public static CtrlPartida getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlPartida(); 
        return single_instance;
    }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	    	int row = mpar.tabla.getSelectedRow();
	    	String probnew = mpar.tabla.getModel().getValueAt(row, 0).toString();
	    	String info =CData.getProblema(probnew);
		    String[] infovec = info.split("\n");
		    String FEN = infovec[2];
		    System.out.println("fen : "+ FEN);
		    mpar.t.initializeBoardFEN(FEN);
		    prob=probnew;
		    row = mpar.tabla.getSelectedRow();
		    probnew = mpar.tabla.getModel().getValueAt(row, 0).toString();

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

}
