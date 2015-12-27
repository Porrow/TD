package TD;

import TD.UI.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import processing.core.*;

public class TD extends PApplet
{
    //Constantes :
    public static final String TITLE = "Tower Defense";                         //Nom fen
    public static final int w = 700;                                            //Largeur fen
    public static final int h = 700;                                            //Hauteur fen
    public static final String IMGPATH = "res/image/";                          //Chemin d'accès aux images
    
    //Variables :
    public int choice = 0;
    
    
    public static void main(String[] args)
    {
        PApplet.main(new String[]{TD.class.getName()});
    }
    
    @Override
    public void settings()
    {
        size(w, h);                                                             //Taille de la fenêtre
        //fullScreen();                                                           //Plein écran
    }
    
    @Override
    public void setup()
    {
        frameRate(60);                                                          //Nombre d'images par seconde
        surface.setTitle(TITLE);                                                //Modifie le titre de la fen
        surface.setResizable(true);                                             //False : on ne peut pas retailler la fen
        //cursor(loadImage(IMGPATH + "cursor.jpg"), mouseX, mouseY);              //Modifie l'apparence du curseur
        //surface.setIcon(loadImage(IMGPATH + "icon.jpg"));                       //Modifie l'icone de la fen
    }

    @Override
    public void draw() 
    {
        //scale();                                                              //Echelle
        //background(0);                                                        //Couleur du fond
        PGraphics g2 = createGraphics(w, h);
        g2.beginDraw();
        switch(choice)
        {
            case 0:
                g2.line(0,0,w,h);
                break;
            case 1:
                break;
            case 2:
                break;
        }
        g2.endDraw();
        image(g2.get(), 0, 0, width, height);
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
