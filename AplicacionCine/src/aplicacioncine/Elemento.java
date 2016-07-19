/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioncine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO
 */
public class Elemento {
    String nombre;
    private JPanel panelElemento;
    
   
    public Elemento(String elemento) throws IOException{
        panelElemento = new JPanel();
        Dimension dm=new Dimension(105,150); //Le damos la dimension que queramos
        panelElemento.setPreferredSize(dm);
        panelElemento.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
        panelElemento.setLayout(new BoxLayout(panelElemento, BoxLayout.Y_AXIS));        
        panelElemento.setPreferredSize(dm);
        
        //Añadimos al panel tanto la imagen como el nombre del 
        panelElemento.add(new JLabel(cargarImagen())); 
        panelElemento.add(cargarNombre());

    }
    public JPanel getelemento(){
        return panelElemento;
    }
    
    /**
     * Método que nos permite cargar la imagen del objeto
     * @return nos devuelve la imagen creada
     */
    public ImageIcon cargarImagen(){
        //Creamos la imagen pero sin ninguna de momento
        ImageIcon imagen= new ImageIcon(" ");
        Image image = imagen.getImage(); // transform it
        Image newimg = image.getScaledInstance(105, 135,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        imagen = new ImageIcon(newimg);  // transform it back
        return imagen;
    }
    
    /**
     * Metodo que nos permite cargar el nombre del objeto
     * @return 
     */
    public JLabel cargarNombre(){
        JLabel nom=new JLabel(nombre);
        nom.setFont(new java.awt.Font("Tahoma", 0, 11)); 
        return nom;
    }
}
