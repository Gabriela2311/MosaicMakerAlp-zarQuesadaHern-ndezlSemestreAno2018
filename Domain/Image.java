package Domain;

public class Image {
    //coordenas
    private int variableX;
    private int VariableY;
    private String pathImage;//ruta del archivo
    private String name;//nombre de imagen

    public Image() {
    }

    public Image(int variableX, int VariableY, String pathImage, String name) {
        this.variableX = variableX;
        this.VariableY = VariableY;
        this.pathImage = pathImage;
        this.name = name;
    }

    public int getVariableX() {
        return variableX;
    }

    public void setVariableX(int variableX) {
        this.variableX = variableX;
    }

    public int getVariableY() {
        return VariableY;
    }

    public void setVariableY(int VariableY) {
        this.VariableY = VariableY;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Image{" + "variableX=" + variableX + ", VariableY=" + VariableY + ", pathImage=" + pathImage + ", name=" + name + '}';
    }
    
}
