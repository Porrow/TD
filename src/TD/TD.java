package TD;

import TD.UI.*;
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
    public int choice = 0;                                                      //Choix de ce qu'il faut afficher : 0:Menu, 1:Jeu, 2:Pause, 3:Options
    
    
    public static void main(String[] args)
    {
        PApplet.main(new String[]{TD.class.getName()});
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
        //surface.setIcon(loadImage(IMGPATH + "icon.jpg"));                       //Modifie l'icone de la fen
    }

    @Override
    public void draw()                                                          //Affiche tout ce qu'il y a de visible à l'écran (appelé après setup())
    {
        //scale();                                                              //Echelle
        PGraphics g2 = createGraphics(w, h);
        g2.beginDraw();
        //g2.background(0);                                                       //Couleur du fond
        switch(choice)
        {
            case 0:
                Menu.draw(g2);
                break;
            case 1:
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
        g2.endDraw();
        image(g2, 0, 0, width, height);
    }

    @Override
    public void mousePressed() 
    {
        //noLoop();                                                             //Stop la réactualisation de l'affichage
    }

    @Override
    public void mouseReleased() 
    {
        //loop();                                                               //Fait repartir la réactualisation
    }
}
