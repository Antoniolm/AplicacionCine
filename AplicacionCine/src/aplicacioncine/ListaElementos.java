
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
import javax.swing.JButton;
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
            mainList.setBackground(new Color(215,215,240));
            add(new JScrollPane(mainList)); //Añadimos nuestro mainlist al objeto

            //Creamos la configuracion de cada elemento
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.gridx = GridBagConstraints.NORTH;
            
            //Este entero nos permitira hacer las lineas en las que va dividiendose
            //la lista de elementos
            int peliActualFila=0;
            int totalActual=0;
            JPanel panel = new JPanel();
            panel.setBackground(new Color(215,215,240));
            
            //Recorremos el numero de elementos
            while(lista.next()){
                
                //mientras no este la linea completa
                if(peliActualFila!=elemPorFila){
                        panel.add((new Elemento(lista.getString("nombre"),lista.getString("imagen"),lista.getInt("anio"),lista.getString("descripcion"))).getelemento());
                        peliActualFila++;
                }
                
                if(peliActualFila==elemPorFila || totalActual+1==tamTotal){ //cuando lo esta la añadimos
                    peliActualFila=1;
                    panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY)); //Border del panel
                    //Insertamos la linea en el mainList
                    mainList.add(panel, gbc, 0);
                    //Creamos un nuevo Jpanel y cargamos el elemento leido en este ciclo del while
                    panel = new JPanel();
                    panel.setBackground(new Color(215,215,240));
                    panel.add((new Elemento(lista.getString("nombre"),lista.getString("imagen"),lista.getInt("anio"),lista.getString("descripcion"))).getelemento());
                }
                totalActual++;
            };
            
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
