/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceMosaic;

import Domain.ImageList;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class OpenImage extends ImageList {

    // Atributos
    private JFileChooser jFileChooser;
    private static ImageList imageList;

    // Muestra un mensaje de error avisando que no se ha podido cargar la imagen
    private void mistakeImage() {
        JOptionPane.showMessageDialog(null, "Unsupported format", "Mistake", JOptionPane.ERROR_MESSAGE);
    } // MistakeImage

    // Método que es para abrir la ventana que permite escoger un archivo
    private boolean openJFileChooser() {
        // Instanciamos a JFileChooser para proceder a abrir la ventana
        this.jFileChooser = new JFileChooser();
        // Le enviamos un titulo a la ventana
        this.jFileChooser.setDialogTitle("Choose an imagen");
        // Llamamos a la ventana para la seleccion del fichero
        int selection = this.jFileChooser.showOpenDialog(null);
        // Si se ha dado click en aceptar devuelve verdadero sino falso
        if (selection == JFileChooser.APPROVE_OPTION) {
            return true;
        } else {
            return false;
        }
    } // OpenJFileChooser

    // Método que obtiene  la imagen que se escoge del computador
    public BufferedImage openImageComputer() throws IOException {
        // Creamos una variable tipo BufferedImage
        BufferedImage image = null;
        if (this.openJFileChooser() == true) {
            // Si en realidad se dio click en aceptar procedemos
            image = ImageIO.read(this.jFileChooser.getSelectedFile());
            // Obtenemos el fichero seleccionado y lo almacenamos en la variable imagen
            //sino esta vacio
            if (image != null) {
                imageList.insertImage(image, "Image open from file");
                // Almacena la imagen con su respectiva informacion en la lista
            } else {
                System.out.print("enters else" + "\n");
                mistakeImage();
            } // If else
        } // If
        return image; // Devuelve la imagen que se eligio
    } // OpenImageComputer

}
