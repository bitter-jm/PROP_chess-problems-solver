package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import presentacion.RankMen;
import presentacion.Ranking;
import presentacion.Men2;

public class Controller implements ActionListener, MouseListener {
	private static  Controller single_instance = null;
	public  RankMen m;
	public Ranking r;
	public String prob;
	private Men2 menu;
	//CONTROLLER

	public Controller() {
		//get de dominio los problemas
		Vector<String> v = new Vector<String>();
        for ( int i = 0; i < 100; i++ ) {
            v.add( "Problema " + i + "   Mate en " + (i+1)*2 + " de las blancas" );
        }
        
		this.m = new RankMen();
		//m.setListData( v );
		//m.setTable(JTable);
		m.conectaControlador(this);
		
	}

	/**
	 * Obtiene la instacia del singleton CtrlRanking
	 * @return Objeto CtrlProblema
	 */
	public static Controller getInstance() { 
        if (single_instance == null) 
            single_instance = new Controller(); 
        return single_instance;
    }
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Necesito un objeto para ejecutar los procedimientos
		//en el controlador de dominio
		//CtrlDom d;
		
		//COMANDO EJECUTADO
		String comando = arg0.getActionCommand();
		
		if(comando =="HOME") {
			
			menu = new Men2();
			menu.MenuGrande();
			m.f.show(false);
			r.show(false);
			
		}
		if (comando == "RANKING") {
			
			if (prob != null) {
				this.r = new Ranking(prob);
				r.prob=prob;
				
				//
				m.f.show(false);
				r.conectaControlador(this);
			}
		}
		
		//limpiarformulario
		//limpia();
		//cargartabla nueva

	}
	//Método para limpiar los campos de la ventana
    /*private void limpia(){
        
    	this.m.txtNombre.setText("");
        this.m.txtApellido.setText("");
        this.m.txtNIF.setText("");
    }*/

	@Override
	public void mouseClicked(MouseEvent c) {
		// TODO Auto-generated method stub
	    	 int column = 0;
	    	 int row = m.tabla.getSelectedRow();
	    	this.prob = m.tabla.getModel().getValueAt(row, column).toString();
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
