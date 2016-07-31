
package aplicacioncine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

/**
 * Clase que nos permitira realizar una card de CardsLayout, en esta card habra
 * un conjunto de objetos de la clase Elemento, es decir, sera por 
 * ejemplo nuestra lista de peliculas 
 * 
 * @author ANTONIO DAVID LÓPEZ MACHADO
 */
public class ListaElementos extends JPanel{
     private JPanel mainList;
        private ArrayList<String> peliculas;
        public ListaElementos(ResultSet lista,int tamTotal) throws IOException, SQLException{
            int elemPorFila=9; //numero de elementos por cada fila
            
            setLayout(new BorderLayout());
            mainList = new JPanel(new GridBagLayout());
            //Realizamos la configuracion 
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = 1;
            //Creamos un nuevo jPanel a Lista elementos
            mainList.add(new JPanel(), gbc);
            add(new JScrollPane(mainList)); //Añadimos nuestro mainlist al objeto

            //Este entero nos permitira hacer las lineas en las que va dividiendose
            //la lista de elementos
            int peliActualFila=0;
            JPanel panel = new JPanel();
            
            int totalActual=0;
            System.out.println("tamTotal : "+tamTotal);
            
            //Recorremos el numero de elementos
            while(lista.next()){
                
                //mientras no este la linea completa
                if(peliActualFila!=elemPorFila){
                        panel.add((new Elemento(lista.getString("nombre"),lista.getString("imagen"))).getelemento());
                        peliActualFila++;
                }
                
                if(peliActualFila==elemPorFila || totalActual+1==tamTotal){ //cuando lo esta la añadimos
                    peliActualFila=1;
                    panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
                    gbc = new GridBagConstraints();
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    gbc.gridx = GridBagConstraints.NORTH;
                    //La insertamos en nuestro jpanel
                    mainList.add(panel, gbc, 0);
                    panel = new JPanel();
                    panel.add((new Elemento(lista.getString("nombre"),lista.getString("imagen"))).getelemento());
                }
                totalActual++;
            };
            
            System.out.println("totalActual :"+totalActual);
            
            validate();
            repaint();
        }
        
        private JPanel cargarLista(){
            return new JPanel();
            
        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }
}
