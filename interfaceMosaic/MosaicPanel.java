package interfaceMosaic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MosaicPanel extends JPanel implements MouseListener, MouseMotionListener {

    //atributos
    Graphics2D graphics2D;
    BufferedImage image;
    public static int counterLeft = 0;
    private int x;
    private int y;
    private int xR;
    private int yR;
    public static int counter = 0;
    public static ArrayList positionX = new ArrayList();
    public static ArrayList positionY = new ArrayList();
    private int squareX;
    private int squareY;
    private int wideSquare;
    private int heithgSquare;

    public MosaicPanel() {
        super();
        this.setPreferredSize(new Dimension(2000, 2000));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }

    //Metodo donde declaramos o intanciamos las variables 
    public void init() {

        image = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_RGB);
        graphics2D = (Graphics2D) image.getGraphics();

    }

    //toma las cordenadas donde doy click al lado de Mosaic
    public int[] catchCoordinate() {
        int[] arreglo = new int[2];
        //Recorre de acuerdo a la cantidad en que se divide la imagen por pixeles
        for (int i = 0; i < (40 * Principal.rectSize) - Principal.rectSize; i += Principal.rectSize) {
            for (int j = 0; j < (40 * Principal.rectSize) - Principal.rectSize; j += Principal.rectSize) {
                if (x > i && x < i + Principal.rectSize) {
                    arreglo[0] = i;
                }//if(x>i && x<i)
                if (y > j && y < j + Principal.rectSize) {
                    arreglo[1] = j;
                }//if(y>j && y<j)
            }//for j

        }//for i
        return arreglo;
    }
    //Metodo que nos permite dibujar

    protected void paintComponent(Graphics g) {
        int temp = 0;
        graphics2D = (Graphics2D) g;
        super.paintComponent(g);
        //Dibuja  la cantidad de cuadros tanto horizontales como verticales con las cantidades dadas 

        for (int i = 0; i < Principal.heigth * Principal.rectSize; i += Principal.rectSize) {
            for (int j = 0; j < Principal.width * Principal.rectSize; j += Principal.rectSize) {
                g.setColor(Color.BLACK);
                g.drawRect(i, j, Principal.rectSize, Principal.rectSize);
            }
        }// hasta aqui se dibujan las lineas del mosaico
        //Verifica que las imagenes han sido selecionadas
        if (Principal.selectImage != null) {
            if (ImageLoadPanel.subImage != null) {
                if (ImageLoadPanel.subImage.getHeight() < Principal.rectSize) {
                    if (counter <= temp) {

                        if (ImageLoadPanel.imagesList.size() > 0 && positionX.size() > 0 && positionY.size() > 0) {
                            //Recorre la lista de imagenes 
                            for (int i = 0; i < ImageLoadPanel.imagesList.size() && i < positionX.size(); i++) {
                                //Nos permite dibujar la imagen en el panel mosaic
                                g.drawImage(ImageLoadPanel.imagesList.get(i), (int) positionX.get(i), (int) positionY.get(i), null);
                            }//for
                            temp = 1;
                        }//if diferente de null imagen 
                    }//if counter
                } else if (ImageLoadPanel.subImage.getWidth() < Principal.rectSize) {
                    g.setColor(Color.WHITE);
                    if (counter <= temp) {
                        if (ImageLoadPanel.imagesList.size() > 0 && positionX.size() > 0 && positionY.size() > 0) {
                            for (int i = 0; i < ImageLoadPanel.imagesList.size() && i < positionX.size(); i++) {//prueba de recorrer la lista de imagenes
                                g.drawImage(ImageLoadPanel.imagesList.get(i), (int) positionX.get(i), (int) positionY.get(i), null);
                            }//for
                            temp = 1;
                        }//if diferente de null imagen 
                    }//if counter
                } else {
                    if (counter <= temp) {
                        if (ImageLoadPanel.imagesList.size() > 0 && positionX.size() > 0 && positionY.size() > 0) {
                            for (int i = 0; i < ImageLoadPanel.imagesList.size() && i < positionX.size(); i++) {//prueba de recorrer la lista de imagenes
                                g.drawImage(ImageLoadPanel.imagesList.get(i), (int) positionX.get(i), (int) positionY.get(i), null);
                            }//for
                            temp = 1;
                        }//if diferente de null imagen 
                    }//if counter
                }
            }
        }
        repaint();

    }
    //Guarda las coordenadas X y Y    

    public int[] rightClick() {
        int[] arreglo = new int[2];
        for (int i = 0; i < (40 * Principal.rectSize) - Principal.rectSize; i += Principal.rectSize) {
            for (int j = 0; j < (40 * Principal.rectSize) - Principal.rectSize; j += Principal.rectSize) {
                if (xR > i && xR < i + Principal.rectSize) {
                    arreglo[0] = i;
                }//if(x>i && x<i)
                if (yR > j && yR < j + Principal.rectSize) {
                    arreglo[1] = j;
                }//if(y>j && y<j)
            }//for j

        }//for i
        return arreglo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override

    //Realiza los metodos para hacer flip, rotation o remove al dar click izquierdo
    public void mousePressed(MouseEvent me) {
        if (!me.isMetaDown()) {
            //si no se dio clik derecho
            this.x = me.getX();
            this.y = me.getY();
            int[] a = catchCoordinate();
            positionX.add(a[0]);
            positionY.add(a[1]);
            counterLeft++;//nuevo
            if (counterLeft > 1) {//nuevo
                ImageLoadPanel.imagesList.add(ImageLoadPanel.subImage);//nuevo
            }//if(counterLeft>1)

        }//if (!me.isMetaDown())
        if (me.isMetaDown()) {
            xR = me.getX();
            yR = me.getY();
            //array de las acciones que pude realizar al hacer click izquierdo
            String[] options = {"Rotate", "Flip", "Remove", "Cancel"};
            int seleccion = JOptionPane.showOptionDialog(null, "You need to select an option", "Titulo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            //se verifica que accion selecciono y que realiza la accion necesaria
            switch (seleccion) {
                //Rotate
                case 0: {

                    //arreglo para establecer las coordenadas 
                    int arre[] = rightClick();
                    //Recorre la lista de imagenes
                    for (int i = 0; i <= ImageLoadPanel.imagesList.size() && i < positionX.size(); i++) {
                        int a = (int) positionX.get(i);
                        int b = (int) positionY.get(i);
                        if (a == arre[0]) {
                            if (b == arre[1]) {
                                //Obtiene la imagen de acuerdo a la posición
                                image = ImageLoadPanel.imagesList.get(i);
                                //Permite girar la imagen cada 90 grados
                                AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(90), image.getWidth() / 2, image.getHeight() / 2);

                                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

                                op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                                //Aplica la rotacion a la imagen
                                image = op.filter(image, null);
                                //le establece la imgen con la rotación
                                ImageLoadPanel.imagesList.set(i, image);

                            }
                        }
                    }
                    break;
                }
                case 1: {
                    //voltear
                    //arreglo para establecer las coordenadas 
                    int arre[] = rightClick();
                    //recorre la lista
                    for (int i = 0; i <= ImageLoadPanel.imagesList.size() && i < positionX.size(); i++) {
                        //Obtiene las coordenadas de acuerdo a la posición
                        int a = (int) positionX.get(i);
                        int b = (int) positionY.get(i);
                        if (a == arre[0]) {
                            if (b == arre[1]) {

                                image = ImageLoadPanel.imagesList.get(i);

                                AffineTransform tx = AffineTransform.getScaleInstance(1, -1);

                                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                                // Flip the image horizontal
                                tx = AffineTransform.getScaleInstance(-1, 1);
                                tx.translate(-image.getWidth(null), 0);
                                op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                                image = op.filter(image, null);
                                //le establece la imgen con el flip
                                ImageLoadPanel.imagesList.set(i, image);
                            }

                        }
                    }
                    break;
                }
                case 2: {
                    //remove
                    //Obtiene las cooerdenadas 
                    int arre[] = rightClick();
                    //Recorre la lista de imagenes
                    for (int i = 0; i <= ImageLoadPanel.imagesList.size() && i < positionX.size(); i++) {
                        int a = (int) positionX.get(i);
                        int b = (int) positionY.get(i);
                        if (a == arre[0]) {
                            if (b == arre[1]) {
                                //elimina el elemento que deseamos
                                ImageLoadPanel.imagesList.remove(i);
                                positionX.remove(i);
                                positionY.remove(i);
                            }//if(b==arre[1])
                        }//if (a == arre[0]  )
                    }//for
                    break;
                }
                case 3: {
                    //Cancel 
                    break;
                }

                default:
                    break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
