/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceMosaic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;


public class SaveImage {

    private JFileChooser seleccionado = new JFileChooser();
    private File archivo;
   

    private void save(String arch) {
        Image image; // Para usar el metodo write debo castear a BufferedImage
        Graphics graph; // Para modificar la imagen
        File file=  new File(arch); // Salida.
        String formato = "png"; // Formato.
        try {
            ImageIO.write(createImage(), formato, file);
        } catch (IOException e) {
            System.out.println("Mistake");
        } // Try
    } // Guarda()

    private BufferedImage createImage() {
        BufferedImage bi2 = new BufferedImage((Principal.heigth * Principal.rectSize), (Principal.width * Principal.rectSize), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi2.createGraphics();
        Principal.scrollPaneGrid.paint(g);
        return bi2;
    } // CreateImage

    public void imageSave() {
        if (seleccionado.showDialog(null, "Save Image") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionado.getSelectedFile();
            if (archivo.getName().endsWith("jpg") || archivo.getName().endsWith("png") || archivo.getName().endsWith("gif")) {
                save(archivo.toPath().toString());
            } else {
                save(archivo.toPath().toString()+".png");
            } // If else
        } // If else
    } // Guarda
} // Guarda()
