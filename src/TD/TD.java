package TD;

import TD.Event.Event;
import TD.Sound.Sound;
import TD.UI.*;
import java.io.File;
import ddf.minim.*;
import processing.core.*;

public class TD extends PApplet
{
    //Constantes :
    public static final String TITLE = "Tower Defense";                         //Nom fen
    public static final int scale = 80;                                         //Echelle de taille de fen : 80 = 720p
    public static final int w = 16*scale;                                       //Largeur par defaut fen
    public static final int h = 9*scale;                                        //Hauteur par defaut fen
    public static final int fps = 60;                                           //Nombre d'ips
    public static final String ICONPATH = "res/img/icon/icon.png";              //Chemin d'accès à l'icone
    public static final int[] COLOR1 = {200, 0, 0};                             //Color non sélectionner
    public static final int[] COLOR2 = {255, 255, 255};                         //Color sélectionner
    
    //Variables :
    public static int choice = 0;                                               //Choix de ce qu'il faut afficher : 0:Menu, 1:Jeu, 2:Pause, 3:Score
    public static int life,gold,score;
    private static PGraphics g2;
    
    private PFont font;
    public Minim minim;
    
    public static void main(String[] args)                                      //Début du programme
    {
        PApplet.main(new String[]{TD.class.getName()});
    }
    
    private void loadAll()
    {   
        Ground.tabImg = loadImages(Ground.IMGPATH);                             //Charge les images du terrain
        Tower.tabImg = cut(loadImage(Tower.IMGFILE));                           //Charge les images des tours
        Tower.imgDestroy = loadImage(Tower.IMGDFILE);                           //Charge bouton destroy
        Tower.imgImprove = loadImage(Tower.IMGIFILE);                           //Charge bouton improve
        Unit.tabBaseImg = loadImages(Unit.IMGPATH);                             //Charge les images des unités
        for(int i = 0; i < Unit.tabBaseImg.length; i++)
            Unit.tabImg[i] = cut(Unit.tabBaseImg[i]);
        Interface.tabImg = loadImages(Interface.IMGPATH);                       //Charge les images de l'interface
        Menu.tabImg = loadImages(Menu.IMGPATH);
        Score.tabImg = loadImages(Score.IMGPATH);
        GameOver.tabImg = loadImages(GameOver.IMGPATH);
        Bullet.tabImg = loadImages(Bullet.IMGPATH);
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
    
    public void save(int score) {
        boolean change = false;
        int i;
        for (i = 0; i < 8; ++i) {
            if (score > Score.score[i]) {
                change = true;
                break;
            }
        }
        if (change) {
            int aux=Score.score[i];
            for(int j=i;j<7;++j) {
                aux=Score.score[j];
                Score.score[j]=score;
                score=aux;
            }
            Score.score[i] = score;
            String[] all = new String[8];
            for(i=0;i<8;++i)
                all[i]= ""+Score.score[i];
            saveStrings(Score.SCOREPATH+"score.txt", all);
        }
    }
    
    private PImage[][]cut(PImage base)
    {
        int w = Ground.W;
        PImage[][] tfin = new PImage[base.height/w][base.width/w];
        for(int i=0;i<base.height/w; ++i)
        {
            for(int j=0;j<base.width/w; ++j)
            {
                tfin[i][j] = createImage(w, w, ARGB);
                tfin[i][j].copy(base, j*w, i*w, w, w, 0, 0, w, w);
            }
        }
        return tfin;
    }
    
    public static void gameInit()                                               //Initialisation du jeu
    {
        TD.score=0;
        TD.life = 10;
        TD.gold = 200;
        Tower.selec = -1;
        Sound.stop();
        if(!Sound.isMute)
            Sound.play(1);
        Ground.loadMap(g2);
        Unit.spawn = Ground.getSpawn();
        Unit.newWave(Unit.wave = 1);
        Move.stop = false;
        new Move().start();
    }
    
    @Override
    public void settings()                                                      //Paramétrage (appelé en premier)
    {
        size(w, h);                                                             //Taille de la fenêtre
    }

    @Override
    public void setup()                                                         //Initialisation (appelé après settings())
    {
        frameRate(fps);                                                         //Nombre d'images par seconde
        surface.setTitle(TITLE);                                                //Modifie le titre de la fen
        surface.setResizable(false);                                            //False : on ne peut pas retailler la fen
        surface.setIcon(loadImage(ICONPATH));                                   //Modifie l'icone de la fen
        //cursor(loadImage(IMGPATH + "cursor.gif"), mouseX, mouseY);              //Modifie l'apparence du curseur

        loadAll();                                                              //Charge toutes les ressources graphiques
        
        font = createFont("FreeMonoBold", 60);                                  //Initialisation graphique
        g.textFont(font);
        g.smooth();
        
        Ground.init();                                                          //Initialise le terrain
        Select.init();
        Tower.init();                                                           //Initialise les tours
        Unit.init();                                                            //Initialise les unités
        Score.init();                                                           //Initialise les scores
        
        minim = new Minim(this);                                                //Initialisation Minim
        Sound.init(minim);                                                      //Charge toutes les ressources sonores
        Sound.play(0);                                                          //Joue le premier son
        
        g2 = createGraphics(w, h);
    }

    @Override
    public void draw()                                                          //Affiche tout ce qu'il y a de visible à l'écran (appelé après setup())
    {
        textSize(60);
        switch(choice)                                                          //Choix de l'affichage
        {
            case 0:                                                             //Menu
                Menu.draw(g);
                break;
            case 1:                                                             //Jeu
                Ground.draw(g);
                Unit.draw(g);
                Tower.draw(g);
                Bullet.draw(g);
                Interface.draw(g);
                break;
            case 2:       
                if(Pause.background == null)
                    Pause.background = g.copy();
                Pause.draw(g);
                break;
            case 3:
                Score.draw(this);
                break;
            case 4:
                Select.draw(g);
                break;
            case 5:
                GameOver.draw(g);
                break;
            default:
                exit();
                break;
        }
        /*textSize(15);
        textAlign(LEFT);
        text((int)(frameRate)+" FPS", 10, 15);*/
    }

    @Override
    public void mousePressed() 
    {
        super.mousePressed();
        System.out.println("x = "+mouseX+"; y = "+mouseY);
        Event.mousePressed(mouseX, mouseY);                                     //On traite les évênements dans Event
    }
    @Override
    public void mouseMoved()
    {
        super.mouseMoved();
        Event.mouseMoved(mouseX, mouseY);
    }
    
    @Override
    public void keyPressed()
    {
        System.out.println(key+" = "+keyCode);
    }
    
    @Override
    public void exit()
    {
        super.exit();
        minim.stop();
    }
}