package File;

import Domain.ImageMosaic;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ImageMosaicFile {

    private RandomAccessFile randomAcessFile;
    private int regsQuantity; // cantidad de registro, tamaño archivo
    private int regsSize; //  tamaño registros
    private String myFilePath; // ruta del archivo

    public ImageMosaicFile() {
        try {
            this.myFilePath = "imagesGridPanel.dat"; // ruta del archivo
            File file = new File(myFilePath); //  esta declarando el archivo
            start(file);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error 1: Creación del archivo");
        }
    }

    // para probar si hay errores o crear el achivo
    // manejo de excepciones, delegandolo al constructor
    private void start(File file) throws IOException {
        // la direccion del archivo que recibe por parametro
        this.myFilePath = file.getParent();
        // tamaño de la línea de archivo según atributos
        this.regsSize = 500;
        // si existe o si esta dañando
        if (file.exists() && !file.isFile()) {
            // excepcion si el archivo da problemas
            throw new IOException(file.getName() + " it is not valid");
        } // if file
        // crear la nueva instancia de randomAcessFile
        randomAcessFile = new RandomAccessFile(file, "rw");
        // el segundo archivo es el modo ya se solo lectura (r) o modo lectura o escritura (rw)
        // necesitamos indicar cuantos registros tiene el archivo
        this.regsQuantity = (int) Math.ceil((double) randomAcessFile.length() / (double) regsSize);
        // tamaño del archivo /  tamaño de registros
    } // start

    // cantidad de registro que se acaban de guardar o cargar
    public int fileSize() {
        return regsQuantity;
    } // fileSize

    // si se puede escribir o no en el archivo, tira error si se se pasa del limite de la linea o escribe en el archivo
    public boolean putValue(int position, ImageMosaic imageMosaic) {
        // para guardar a partir de 0 al limite del registor
        if (position >= 0 && position <= regsQuantity) {
            if (imageMosaic.sizeImageMosaic() > regsSize) {
                return false;
            } else {
                try {
                    // escribir en el archivo
                    // se esta escribiendo en el archivo con el orden y metodos necesarios segun el objeto person
                    randomAcessFile.seek(position * regsSize); // lectura de registro
                    randomAcessFile.writeUTF(imageMosaic.getName());
                    randomAcessFile.writeInt(imageMosaic.getVariableX());
                    randomAcessFile.writeInt(imageMosaic.getVariableY());
                    randomAcessFile.writeInt(imageMosaic.getHeigth());
                    randomAcessFile.writeInt(imageMosaic.getWidth());
                    randomAcessFile.writeUTF(imageMosaic.getPathImage());
                    randomAcessFile.writeInt(imageMosaic.getCounter());
                    return true;
                } catch (IOException ex) {
                    return false;
                } // try catch
            } // if else
        } // if
        return true;
    } // putValue

    // insertar al final de archivo
    public boolean addEnRecord(ImageMosaic imageGrid) {
        // tamaño archivo
        boolean success = putValue(regsQuantity, imageGrid);
        if (success) {
            ++regsQuantity;
            return success;
        } else {
            return success;
        } // if else
        // return sucess; // el return ya sea dos o uno al final no afecta
    } // addEnRecord

    // ira a un posicion especifica para obtener el objeto que se necesita, obtener
    public ImageMosaic getImage(int position) {
        if (position >= 0 && position <= regsQuantity) {
            try {
                // colocamos el puntero en el lugar correspondiente
                randomAcessFile.seek(position * regsSize); // getPosition
                // crear instancia de persona para que se guarde lo que se saque
                ImageMosaic imageGrid = new ImageMosaic();
                // llenar los atributos con valores del archivo
                // se ponen los valores segun el orden del constructor
                imageGrid.setName(randomAcessFile.readUTF());
                imageGrid.setVariableX(randomAcessFile.readInt());
                imageGrid.setVariableY(randomAcessFile.readInt());
                imageGrid.setHeigth(randomAcessFile.readInt());
                imageGrid.setWidth(randomAcessFile.readInt());
                imageGrid.setPathImage(randomAcessFile.readUTF());
                imageGrid.setCounter(randomAcessFile.readInt());
                // para el metodo de borrar ya que no se puede borrar directamente entonces
                // se indica que esta borrado, si esta borrado retorna nada sino retorna imagen
                if (imageGrid.getVariableX()== -1) {
                    return null;
                } else {
                    return imageGrid;
                } // if else
            } catch (IOException ex) {
                return null;
            } // try catch
        } // if
        return null;
    } // getImage

    // retorna la lista de imagenes del arreglo
    public List<ImageMosaic> getAllImage() {
        // variable a retornar
        List<ImageMosaic> imageObjects = new ArrayList<ImageMosaic>();
        // recorre todos los registros e inserta en la lista
        for (int i = 1; i < regsQuantity; i++) {
            ImageMosaic imsgeMosaic = this.getImage(i);
            if (imsgeMosaic != null) {
                // agrega la imagen al arrayList
                imageObjects.add(imsgeMosaic);
            }
        } // for
        return imageObjects;
    } // getAllImages

    // cerrar archivo
    public void close() throws IOException {
        randomAcessFile.close();
    } // close

} // class
