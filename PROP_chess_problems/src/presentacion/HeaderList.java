package presentacion;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;
public class HeaderList extends JPanel {
    private JList list;
    private JPanel headerPanel;
    private JLabel header;
    public HeaderList() {
        setLayout( new BorderLayout() );
        list = new JList();
        headerPanel = new JPanel();
        headerPanel.setLayout( new BorderLayout() );
        headerPanel.setBorder( BorderFactory.createEtchedBorder() );
        JScrollPane scroll = new JScrollPane( list );
        add( scroll, BorderLayout.CENTER );
        header = new JLabel( "Header", JLabel.CENTER );
        headerPanel.add( header );
        add( headerPanel, BorderLayout.NORTH );
        JButton EditP = new JButton ("Button1");
        JPanel IzquierdaP = new JPanel();
        IzquierdaP.add(EditP);
        add (IzquierdaP,BorderLayout.WEST);
        
    }
    public void setListData( Vector vect ) {
        list.setListData( vect );
    }
    public static void main( String[] arg ) {
        JFrame frame = new JFrame( "JList with Header" );
        HeaderList myList = new HeaderList();
        Vector v = new Vector();
        for ( int i = 0; i < 10; i++ ) {
            v.add( "Data " + i );
        }
        myList.setListData( v );
        JPanel p = new JPanel();
        p.add( myList );
        frame.getContentPane().add( p );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.pack();
        frame.show();
    }
}