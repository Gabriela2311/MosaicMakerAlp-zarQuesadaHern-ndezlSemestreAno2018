package interfaceMosaic;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class ImageLoadPanel extends JPanel implements MouseListener, MouseMotionListener {

    //atributos
    public static int wideWindow = 800;
    public static int heigthWindow = 1500;
    private int squareX;
    private int squareY;
    private int wideSquare;
    private int heigthSquare;
    private int x;//esto es nuevo
    private int y;//esto es nuevo
    public static Graphics2D graphics2D;
    public static BufferedImage subImage;
    public static ArrayList<BufferedImage> imagesList = new ArrayList<>();

    public ImageLoadPanel() {
        this.setPreferredSize(new Dimension(2000, 2000));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    } // Constructor

    @Override
    //Este metodo nos dibuja las lineas del panel con el respectivo tamaño 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //verifica que se selecione la imagen
        if (Principal.selectImage != null) {
            g.drawImage(Principal.selectImage, 0, 0, this);
            //recorre la imagen para dibujar la cantidad de cuadros necesarios para cubrir la imagen
            for (int i = 0; i < Principal.selectImage.getWidth(); i += Principal.rectSize) {
                for (int j = 0; j < Principal.selectImage.getHeight(); j += Principal.rectSize) {
                    g.setColor(Color.MAGENTA);
                    //dibuja rectangulo de acuerdo a tamaño dado
                    g.drawRect(i, j, Principal.rectSize, Principal.rectSize);
                }
            }
        }

        repaint();
    } // PaintComponent

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override

    public void mousePressed(MouseEvent me) {
        //para agarrar la cordenada donde da click 
        this.x = me.getX();
        this.y = me.getY();
        //arreglo el metodo nos trae las cordenas tomadas de la imagen seleccionada
        int[] a = catchCoordinateImage();

        //Metodo nos trae pequeñas subimagenes y las agrrega al imageList
        if ((me.getY() > Principal.selectImage.getHeight() || me.getX() > Principal.selectImage.getWidth())) {
            if ((me.getY() + Principal.rectSize > Principal.selectImage.getHeight()) || (me.getX() + Principal.rectSize > Principal.selectImage.getWidth())) {
            }

        } else {

            if (me.getY() + Principal.rectSize < Principal.selectImage.getHeight() && me.getX() + Principal.rectSize < Principal.selectImage.getWidth()) {
                subImage = Principal.selectImage.getSubimage(a[0], a[1], Principal.rectSize, Principal.rectSize);//nuevo
            }

            imagesList.add(subImage);
            //Nos lleva la secuencia de subimagenes 
            MosaicPanel.counterLeft = 0;
        } // mousePressed
    }

    //metodo que nos da las cordenadas de las imagen seleccionada
    public int[] catchCoordinateImage() {
        int[] arreglo = new int[2];
        for (int i = 0; i < (Principal.selectImage.getWidth()) - Principal.rectSize; i += Principal.rectSize) {
            for (int j = 0; j < (Principal.selectImage.getHeight()) - Principal.rectSize; j += Principal.rectSize) {
                if (x > i && x < i + Principal.rectSize) {
                    arreglo[0] = i;
                }//if(x>i && x<i)
                if (y > j && y < j + Principal.rectSize) {
                    arreglo[1] = j;
                }//if(y>j && y<j)
            }//for j

        }//for i
        return arreglo;
    }//catchCoordinateImage

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

}//ImagePanel
