/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioncine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO
 */
public class ListaElementos extends JPanel{
     private JPanel mainList;
        private ArrayList<String> peliculas;
        public ListaElementos(int numElem) throws IOException{
            int elemPorFila=9; //numero de elementos por cada fila
            ArrayList<Elemento> linea= new ArrayList<Elemento>();
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

            //int resto=numElem%elemPorFila;
            //int nFilas=numElem/elemPorFila;
            for(int i=0;i<numElem;i=i+elemPorFila){
                JPanel panel = new JPanel();
                linea.clear();
                
                //Creamos una fila
                for(int j=0;j<elemPorFila ;j++){
                    //if(i!=(nFilas*elemPorFila) || j<resto)
                        panel.add((new Elemento("prueba"+j)).getelemento());
                }
                panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
                gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.gridx = GridBagConstraints.NORTH;
                //La insertamos en nuestro jpanel
                mainList.add(panel, gbc, 0);
                
            }
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
