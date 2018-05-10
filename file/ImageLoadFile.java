package File;

import Domain.ImageLoad;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ImageLoadFile {

    private RandomAccessFile randomAcessFile;
    private int regsQuantity; // cantidad de registro, tamaño archivo
    private int regsSize; //  tamaño registros
    private String myFilePath; // ruta del archivo

    public ImageLoadFile() {
        try {
            this.myFilePath = "imagesPanel.dat"; // ruta del archivo
            File file = new File(myFilePath); //  esta declarando el archivo
            start(file);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error 1: Creación del archivo");
        }
    }

    // para probar si hay errores o crear el achivo
    // manejo de excepciones, delegandolo al constructor
    private void start(File file) throws IOException {
        this.myFilePath = file.getParent(); // la direccion del archivo que recibe por parametro
        this.regsSize = 200; // tamaño de la línea de archivo según atributos
        if (file.exists() && !file.isFile()) { // si existe o si esta dañando
            throw new IOException(file.getName() + " no es válido"); // excepcion si el archivo da problemas
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
    public boolean putValue(int position, ImageLoad imageLoad) {
        // para guardar a partir de 0 al limite del registor
        if (position >= 0 && position <= regsQuantity) {
            if (imageLoad.sizeImageLoad() > regsSize) {
                JOptionPane.showMessageDialog(null, "Error 2: Tamaño del registro se pasó del límite");
                return false;
            } else {
                try {
                    // escribir en el archivo
                    // se esta escribiendo en el archivo con el orden y metodos necesarios segun el objeto person
                    randomAcessFile.seek(position * regsSize); // lectura de registro
                    randomAcessFile.writeUTF(imageLoad.getName());
                    randomAcessFile.writeInt(imageLoad.getVariableX());
                    randomAcessFile.writeInt(imageLoad.getVariableY());
                    randomAcessFile.writeUTF(imageLoad.getPathImage());
                    randomAcessFile.writeInt(imageLoad.getN());
                    return true;
                } catch (IOException ex) {
                    return false;
                } // try catch
            } // if else
        } // if
        return true; // nunca será tirando en el metodo por la estructura que tiene
    } // putValue

    // insertar al final de archivo, agregar
    public boolean addEnRecord(ImageLoad imagesIP) {
        boolean success = putValue(regsQuantity, imagesIP); // tamaño archivo.
        if (success) {
            ++regsQuantity;
            return success;
        } else {
            return success;
        } // if else
        // return sucess; // el return ya sea dos o uno al final no afecta
    } // addEnRecord

    // ira a un posicion especifica para obtener el objeto que se necesita, obtener
    public ImageLoad getImage(int position) {
        if (position >= 0 && position <= regsQuantity) {
            try {
                // colocamos el puntero en el lugar correspondiente
                randomAcessFile.seek(position * regsSize); // getPosition
                // crear instancia de persona para que se guarde lo que se saque
                ImageLoad imagesIP = new ImageLoad();
                // llenar los atributos con valores del archivo
                // se ponen los valores segun el orden del constructor
                imagesIP.setName(randomAcessFile.readUTF());
                imagesIP.setVariableX(randomAcessFile.readInt());
                imagesIP.setVariableY(randomAcessFile.readInt());
                imagesIP.setPathImage(randomAcessFile.readUTF());
                imagesIP.setN(randomAcessFile.readInt());
                // para el metodo de borrar ya que no se puede borrar directamente entonces
                // se indica que esta borrado, si esta borrado retorna nada sino retorna persona
                if (imagesIP.getVariableX()== -1) {
                    return null;
                } else {
                    return imagesIP;
                } // if else
            } catch (IOException ex) {
                return null;
            } // try catch
        } // if
        return null;
    } // getPerson

    // retorna la lista de imagenes del arreglo
    public List<ImageLoad> getAllImage() {
        // variable a retornar
        List<ImageLoad> imagesPanel = new ArrayList<>();
        // recorre todos los registros e inserta en la lista
        for (int i = 1; i < regsQuantity; i++) {
            ImageLoad imageLoad = this.getImage(i);
            if (imageLoad != null) {
                // agrega la imagen al arrayList
                imagesPanel.add(imageLoad);
            }
        } // for
        return imagesPanel;
    } // getAllImage

    // cerrar archivo
    public void close() throws IOException {
        randomAcessFile.close();
    } // close

} // class
