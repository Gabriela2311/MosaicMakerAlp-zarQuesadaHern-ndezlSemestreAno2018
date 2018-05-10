/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceMosaic;

import Domain.ImageMosaic;
import Domain.ImageList;
import Domain.ImageLoad;
import File.ImageMosaicFile;
import File.ImageLoadFile;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Principal extends JFrame implements ActionListener {

    private JMenuBar menubar; // Menú
    private JMenu menu_create; // Sub menú nueva opción
    private JMenuItem menuItem_loadImage;
    private JMenuItem menuItem_newProject;
    private JMenuItem menuItem_Open;
    private JMenu menu_Save;// Menu guardar
    private JMenuItem menuItem_SaveImage;
    private JMenuItem menuItem_SaveProject;
    private JMenu menu_System;
    private JMenuItem menuItem_About;
    private static JPanel contentPane; // Panel
    public static BufferedImage selectImage; // Imagen seleccionada
    private OpenImage openImage = new OpenImage();
    private SaveImage saveImage = new SaveImage();
    public static JScrollPane scrollPaneImage;
    public static JScrollPane scrollPaneGrid;
    public static int rectSize = 1; // Tamaño n
    public static int width; //ancho del cuadro del mosaico
    public static int heigth;//alto del cuadro del mosaico

    public Principal() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height - 20 * 2);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        this.init();
        this.addComponent();
        this.setJMenuBar(menubar);
        scrollPaneImage = new JScrollPane(new ImageLoadPanel());
        scrollPaneImage.add(new ImageLoadPanel());
        scrollPaneGrid = new JScrollPane(new MosaicPanel());
        scrollPaneGrid.add(new MosaicPanel());

    }

    //Metodo que nos permite inicilizar o instanciar 
    private void init() {
        menubar = new JMenuBar();
        menu_create = new JMenu("Project");
        menu_create.addActionListener(this);
        menuItem_loadImage = new JMenuItem("Load Image");
        menuItem_loadImage.addActionListener(this);
        menuItem_Open = new JMenuItem("Open");
        menuItem_Open.addActionListener(this);
        menuItem_newProject = new JMenuItem("New");
        menuItem_newProject.addActionListener(this);
        menu_Save = new JMenu("Save");
        menu_Save.addActionListener(this);
        menuItem_SaveProject = new JMenuItem("Save project");
        menuItem_SaveProject.addActionListener(this);
        menuItem_SaveImage = new JMenuItem("Save Image");
        menuItem_SaveImage.addActionListener(this);
        menu_System = new JMenu("System");
        menu_System.addActionListener(this);
        menuItem_About = new JMenuItem("About");
        menuItem_About.addActionListener(this);

    }
    //Metodo que nos permite agregar menuItem

    private void addComponent() {
        //Añadimos los submenus a los menus
        menubar.add(menu_create);
        menu_create.add(menuItem_loadImage);
        menu_create.add(menuItem_newProject);
        menu_create.add(menuItem_Open);
        menubar.add(menu_Save);
        menu_Save.add(menuItem_SaveProject);
        menu_Save.add(menuItem_SaveImage);
        menubar.add(menu_System);
        menu_System.add(menuItem_About);

    } // AddComponent

    public void exit() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                } // WindowClosing
            }); // AddWindowListener
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        } // Try catch
    } // Exit

    @Override
    //Acciones a realizar 
    public void actionPerformed(ActionEvent e) {
        //Cargar imagen
        if (e.getSource() == menuItem_loadImage) {

            // Importar imagen desde computadora
            try {
                //llama al metodo que carga la imagen
                selectImage = openImage.openImageComputer();
                if (selectImage != null) {
                    repaint();

                } // If
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } // Try 

            //Crear un nuevo proyecto, solicita tamaño y cantidad de cuadros 
        } else if (e.getSource() == menuItem_newProject) {
            this.rectSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Size of the quadrants in pixels to divide the image"));
            this.heigth = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of long frames"));
            this.width = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of frames width"));

            //Guardar el proyecto
        } else if (e.getSource() == menuItem_SaveProject) {

            //Crea un archivo 
            File fichero = new File("imagePanel.png");

            try {
                //escribe la imagen en el archivo
                ImageIO.write(selectImage, "png", fichero);
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } // Try 
            //constructor establece lo que lee de la imagen seleccionada
            ImageLoad imagesIP = new ImageLoad();
            imagesIP.setName("imagePanel");
            imagesIP.setVariableX(selectImage.getMinX());
            imagesIP.setVariableY(selectImage.getMinY());
            imagesIP.setPathImage(fichero.getPath());
            imagesIP.setN(Principal.rectSize);
            ImageLoadFile file = new ImageLoadFile();
            //Agrega la imagen al final del archivo
            file.addEnRecord(imagesIP);
            //Instanciamos la imagen 
            ImageList il = new ImageList();

            //llama al metodo que guarda en el archivo las pequeñas imagenes
            il.saveSubImage();
            JOptionPane.showMessageDialog(null,"The project is saved correctly");

        } else if (e.getSource() == menuItem_Open) {

            // Abrir proyecto
            ImageLoadFile fileImage = new ImageLoadFile();
            ImageMosaicFile imageGridFile = new ImageMosaicFile();
            ImageMosaic grid;
            ImageLoad imagesIP;
            imagesIP = fileImage.getImage(0);
            grid = imageGridFile.getImage(0);
            File file = new File(imagesIP.getPathImage());
            this.rectSize = imagesIP.getN();
            this.width = grid.getWidth();
            this.heigth = grid.getHeigth();
            try {
                //lee la imagen seleccionada y la agrega en el archivo
                selectImage = ImageIO.read(file);
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } // Try catch
            for (int i = 0; i < imageGridFile.fileSize(); i++) {
                MosaicPanel.positionX.add(imageGridFile.getImage(i).getVariableX());
                MosaicPanel.positionY.add(imageGridFile.getImage(i).getVariableY());
                try {

                    File fileImage2;
                    fileImage2 = new File(imageGridFile.getImage(i).getPathImage());
                    BufferedImage bi;
                    bi = ImageIO.read(fileImage2);
                    ImageLoadPanel.imagesList.add(bi);
                } catch (IOException ex) {
                    System.err.println();
                } // Try catch
            } // For 
            //Arcerca de aqui podra en contrar toda la información referente al proyecto
        } else if (e.getSource() == menuItem_About) {
            JOptionPane.showMessageDialog(null, "This project consists of importing an image from the computer,"
                    + "\nselecting the size in pixels of the frames you want,"
                    + "\nthe number of frames in both width and height.\n\n"
                    + "After the values ​​were entered correctly, you can select any"
                    + "\nquadrant and paint it in the mosaic, except for those that are incomplete.\n\n"
                    + "In the mosaic, these image fragments can be flipped, rotated and removed"
                    + "\nby right clicking on it.\n\n"
                    + "When finished you can save the resulting image or "
                    + "\nthe entire project to continue editing later.", "About", JOptionPane.INFORMATION_MESSAGE);

            //guarda la imagen del Mosaico
        } else if (e.getSource() == menuItem_SaveImage) {
            saveImage.imageSave();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Agregamos al panel principal todos sus componentes 
        Principal frame = new Principal();
        frame.setTitle("Mosaick Maker");
        frame.setLayout(new GridLayout(1, 2));
        frame.setContentPane(contentPane);
        frame.add(scrollPaneImage);
        frame.add(scrollPaneGrid);
        frame.exit();
    }
}
