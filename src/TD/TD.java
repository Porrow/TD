package TD;

import TD.Event.Event;
import TD.Sound.Sound;
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
    public static final int fps = 60;                                           //Nombre d'ips
    public static final String ICONPATH = "res/img/icon/icon.png";              //Chemin d'accès aux images des tours
    
    //Variables :
    public static int choice = 1;                                               //Choix de ce qu'il faut afficher : 0:Menu, 1:Jeu, 2:Pause, 3:Options
    
    public static void main(String[] args)                                      //Début du programme
    {
        //System.out.println(Math.round(1000. / TD.fps));
        PApplet.main(new String[]{TD.class.getName()});
    }

    private PImage[] loadImages(String path)
    {   
        String na;
        File f = new File(path);
        File[] files = f.listFiles();
        PImage[] tabImg = new PImage[files.length];
        for(int i = 0, ind; i < files.length; i++)
        {
            na = files[i].getName();
            String name = na.substring(0, na.indexOf("."));
            if(!name.equals("Thumbs"))
            {
                ind = Integer.parseInt(name);
                tabImg[ind] = loadImage(path + na);
            }
        }
        return tabImg;
    }
    public static void b(){System.out.println("Bonjour");}
    @Override
    public void settings()                                                      //Paramétrage (appelé en premier)
    {
        size(w, h);                                                             //Taille de la fenêtre
        //fullScreen();                                                           //Plein écran
    }
    
    @Override
    public void setup()                                                         //Initialisation (appelé après settings())
    {
        frameRate(fps);                                                          //Nombre d'images par seconde
        surface.setTitle(TITLE);                                                //Modifie le titre de la fen
        surface.setResizable(true);                                             //False : on ne peut pas retailler la fen
        
        //cursor(loadImage(IMGPATH + "cursor.gif"), mouseX, mouseY);              //Modifie l'apparence du curseur
        surface.setIcon(loadImage(ICONPATH));                                   //Modifie l'icone de la fen
        Ground.tabImg = loadImages(Ground.IMGPATH);                             //Charge les images du terrain
        Tower.tabImg = loadImages(Tower.IMGPATH);                               //Charge les images des tours
        Unit.tabImg = loadImages(Unit.IMGPATH);                                 //Charge les images des unités
        Ground.init(0, createGraphics(w, h));                                   //Initialise le terrain
        Unit.init();
        Sound.init();
        //Sound.play(0);
        new Move();
    }

    @Override
    public void draw()                                                          //Affiche tout ce qu'il y a de visible à l'écran (appelé après setup())
    {
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