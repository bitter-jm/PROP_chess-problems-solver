package domain;

public class CtrlProblema {

	private static CtrlProblema single_instance = null;
	
	public static CtrlProblema getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlProblema(); 
        return single_instance; 
    }
	
}
