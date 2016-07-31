
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
 * Clase que nos permitirá crear un elemento(pelicula o serie) el cual tendra un
 * nombre,una imagen y una interfaz.
 * 
 * @author ANTONIO DAVID LÓPEZ MACHADO
 */
public class Elemento {
    String nombre,imagen,descripcion;
    int año;
    private JPanel panelElemento;
    
   
    public Elemento(String elemento,String img) throws IOException{
        nombre=elemento;
        imagen=img;
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
        ImageIcon imagenElemento= new ImageIcon(imagen+".jpg");
        Image image = imagenElemento.getImage(); // transform it
        Image newimg = image.getScaledInstance(105, 135,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        imagenElemento = new ImageIcon(newimg);  // transform it back
        return imagenElemento;
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
