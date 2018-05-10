/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

public class ImageLoad extends Image {

    private int n;//tamaño de los cuadros 

    public ImageLoad() {
        super();
    }

    public ImageLoad(int n) {
        this.n = n;
    }

    public ImageLoad(int n, int variableX, int VariableY, String pathImage, String name) {
        super(variableX, VariableY, pathImage, name);
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int sizeImageLoad() {
        return (this.getName().length() * 2) + 4 + 4 + (this.getPathImage().length() * 2) + 4;
    }

    @Override
    public String toString() {
        return "ImageLoad{" + "n=" + n + '}';
    }

}
