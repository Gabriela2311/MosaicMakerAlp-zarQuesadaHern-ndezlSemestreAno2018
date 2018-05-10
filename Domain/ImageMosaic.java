/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import javax.swing.JPanel;

public class ImageMosaic extends Image {

    // Atributos
    private int heigth;
    private int width;
    private int counter;

    // Constructores
    public ImageMosaic() {
        super();
    }

    public ImageMosaic(int heigth, int width, int counter) {
        this.heigth = heigth;
        this.width = width;
        this.counter = counter;
    }

    public ImageMosaic(int heigth, int width, int counter, int variableX, int VariableY, String pathImage, String name) {
        super(variableX, VariableY, pathImage, name);
        this.heigth = heigth;
        this.width = width;
        this.counter = counter;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int sizeImageMosaic() {
        return (this.getName().length() * 2) + 4 + 4 + 4 + 4 + (this.getPathImage().length() * 2) + 4;
    }

    @Override
    public String toString() {
        return "ImageMosaic{" + "heigth=" + heigth + ", width=" + width + ", counter=" + counter + '}';
    }

} // Class
