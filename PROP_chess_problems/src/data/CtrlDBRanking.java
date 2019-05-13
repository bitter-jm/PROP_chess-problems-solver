package data;

public class CtrlDBRanking {
	
	private static CtrlDBRanking single_instance = null;

	public static CtrlDBRanking getInstance() { 
		if (single_instance == null) 
			single_instance = new CtrlDBRanking(); 
		return single_instance; 
	}

}
