package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import data.CtrlData;
import domain.CtrlPersona;
import presentacion.RankMen;
import presentacion.Ranking;
import presentacion.Men2;
//extends CtrlPresentacion 
public class CtrlRanking extends CtrlPresentacion implements ActionListener, MouseListener {
	private static  CtrlRanking single_instance = null;
	private CtrlData CData; 
	private  RankMen mr;
	private Ranking r;
	private String prob;
	//private Men2 menu;
	//CONTROLLER

	public CtrlRanking() {
		l.f.show(false);
		CData = CtrlData.getInstance();
        String [][] data = CData.getProblemasJugables();
		this.mr = new RankMen(data);
		mr.m.conectaControlador(super.getInstance());
		mr.f.show(true);
		mr.conectaControlador(this);
		
	}
	

	/**
	 * Obtiene la instacia del singleton CtrlRanking
	 * @return Objeto CtrlProblema
	 */
	public static CtrlRanking getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlRanking(); 
        return single_instance;
    }
	
	public void show(Boolean b) {
		mr.f.show(b);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		//COMANDO EJECUTADO
		String comando = arg0.getActionCommand();
		
		if(comando =="HOME") {
			
			//menu = new Men2();
			//menu.MenuGrande();
		//	super.menu.f.show(true);
			//m.f.dispose();
			//m.f.show(false);
			if (r != null) r.dispose();//r.show(false);
			
		}
		if (comando == "RANKING") {
			
			//CENTRAR DATOS
			/*String s = CData.getRanking(prob);
			s=s.replace("?", "\n");
			String[] data = s.split("\n");
			int n = data.length/2;
			String[][] datam =  new String[n][2];
			for (int i=0; i<n; ++i) {
				datam[i][0]=data[i*2];
				datam[i][1]=data[(i*2)+1];
				
			}*/
			
			r = new Ranking(prob, CData.getMatrixRanking(prob));
			mr.f.dispose();
			//m.f.show(false);
			r.conectaControlador(this);
				
			}
		}
		
		//limpiarformulario
		//limpia();l
		//cargartabla nueva


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
	    	 int row = mr.tabla.getSelectedRow();
	    	this.prob = mr.tabla.getModel().getValueAt(row, column).toString();
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
