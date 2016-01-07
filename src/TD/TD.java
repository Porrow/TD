package TD;

import TD.Event.Event;
import TD.IO.Write;
import TD.UI.*;
import java.io.File;
import processing.core.*;

public class TD extends PApplet
{
    //Constantes :
    public static final String TITLE = "Tower Defense";                         //Nom fen
    public static final int scale = 80;                                         //Echelle de taille de fen : 80 = 720p
    public static final int w = 16*scale;                                       //Largeur par defaut fen
    public static final int h = 9*scale;                                        //Hauteur par defaut fen
    public static final String IMGPATH = "res/img/";                            //Chemin d'accès aux images
    
    //Variables :
    public static int choice = 1;                                               //Choix de ce qu'il faut afficher : 0:Menu, 1:Jeu, 2:Pause, 3:Options
    public static PImage[] tabImg;
    
    public static void main(String[] args)                                      //Début du programme
    {
        PApplet.main(new String[]{TD.class.getName()});
    }
    //public static loadFiles()
    private void loadImages()
    {   
        File f = new File(IMGPATH+"ground/");
        File[] files = f.listFiles();
        tabImg = new PImage[files.length];
        for(int i = 0, ind; i < files.length; i++)
        {
            ind = Integer.parseInt(files[i].getName().substring(0, 2));
            tabImg[ind] = requestImage(IMGPATH+"ground/"+files[i].getName());
        }
    }
    @Override
    public void settings()                                                      //Paramétrage (appelé en premier)
    {
        size(w, h);                                                             //Taille de la fenêtre
        //fullScreen();                                                           //Plein écran
    }
    
    @Override
    public void setup()                                                         //Initialisation (appelé après settings())
    {
        frameRate(60);                                                          //Nombre d'images par seconde
        surface.setTitle(TITLE);                                                //Modifie le titre de la fen
        surface.setResizable(true);                                             //False : on ne peut pas retailler la fen
        
        //cursor(loadImage(IMGPATH + "cursor.gif"), mouseX, mouseY);              //Modifie l'apparence du curseur
        surface.setIcon(loadImage(IMGPATH + "icon/icon.png"));                       //Modifie l'icone de la fen
        loadImages();                                                           //Charge les images
        Ground.loadMaps();                                                      //Charge les fichiers maps
        new Unit(0);
    }

    @Override
    public void draw()                                                          //Affiche tout ce qu'il y a de visible à l'écran (appelé après setup())
    {
        
        //scale();                                                              //Echelle
        PGraphics g2 = createGraphics(w, h);
        g2.beginDraw();                                                         //Permet de dessiner sur g2
        g2.background(0);                                                       //Couleur du fond
        switch(choice)                                                          //Choix de l'affichage
        {
            case 0:                                                             //Menu
                Menu.draw(g2);
                break;
            case 1:                                                             //Jeu
                Ground.draw(g2);
                Tower.draw(g2);
                Unit.draw(g2);
                Interface.draw(g2);
                break;
            case 2:                                                             
                break;
            case 3:
                break;
        }
        g2.endDraw();                                                           //Plus le droit de dessiner sur g2
        image(g2, 0, 0, width, height);                                         //Affichage de g2 redimenssionné à la taille de la fenêtre
    }

    @Override
    public void mousePressed() 
    {
        //noLoop();                                                             //Stop la réactualisation de l'affichage
        Event.mousePressed(mouseX, mouseY);                                     //On traite les évênements dans Event
    }

    @Override
    public void mouseReleased() 
    {
        //loop();                                                               //Fait repartir la réactualisation
    }
    
    @Override
    public void keyPressed()
    {
        System.out.println(key+" = "+keyCode);
    }
}