/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;


import File.ImageMosaicFile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import interfaceMosaic.MosaicPanel;
import interfaceMosaic.ImageLoadPanel;
import interfaceMosaic.Principal;

public class ImageList {

    public static ArrayList<BufferedImage> images = new ArrayList<>();
    private static ArrayList<String> imagesInformation = new ArrayList<>();

    public static ArrayList<BufferedImage> getAllImages() {
        return images;
    } // Obtiene todas las imagenes de la image list

    public static ArrayList<String> getInformationAllImages() {
        return imagesInformation;
    } // obtiene la InformationAllImagesIL de image list

    // Método que se encarga de almacenar imagenes en la lista
    public static void insertImage(BufferedImage image, String information) {
        images.add(image); // Agrega una imagen a la lista
        imagesInformation.add(information); // Agrega la manera en la que realizo la busqueda de dicha imagen
    } // InsertImage

    //metodo que guarda las pequeñas imagenes en el archivo
    public void saveSubImage() {
        //Archivo de mosaico
        ImageMosaicFile imageGridFile = new ImageMosaicFile();
        //contructor
        ImageMosaic imageGrid = new ImageMosaic();
        int contador = 1;
        //recorrela lista
        for (int i = 0; i < ImageLoadPanel.imagesList.size(); i++) {
            imageGrid.setVariableX((int) MosaicPanel.positionX.get(i));
            imageGrid.setVariableY((int) MosaicPanel.positionY.get(i));
            imageGrid.setName("imageGrid" + contador);
            imageGrid.setHeigth(Principal.heigth);
            imageGrid.setWidth(Principal.width);
            imageGrid.setCounter(contador);
            imageGrid.setPathImage("imageGrid" + contador + ".png");
           
            File fichero = new File("imageGrid" + contador + ".png");
            try {
                ImageIO.write(ImageLoadPanel.imagesList.get(i), "png", fichero);
                 contador++;
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            imageGridFile.addEnRecord(imageGrid);

            //este me da las cordenadas 
            System.out.println(imageGrid.toString());
        }

    }

} // ImageList
