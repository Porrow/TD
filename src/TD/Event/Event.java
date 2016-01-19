package TD.Event;

import TD.Move;
import TD.Sound.Sound;
import TD.TD;
import TD.UI.GameOver;
import TD.UI.Ground;
import TD.UI.Interface;
import TD.UI.Menu;
import TD.UI.Pause;
import TD.UI.Score;
import TD.UI.Select;
import TD.UI.Tower;

public class Event
{
    private static int remember = -1;
    
    public static boolean inRect(int mx, int my, int x1, int y1, int x2, int y2)//True si (posX, posY) est dans le carré définie par x1,y1,x2,y2
    {
        return mx >= x1 && mx <= x2 && my >= y1 && my <= y2;
    }
    
    public static boolean inCircle(int mx, int my, int x, int y, int r)         //Distance 2 entre les 2 points
    {
        int vx = Math.abs(mx - x);
        int vy = Math.abs(my - y);
        return Math.sqrt(vx * vx + vy * vy) <= r;
    }
    
    public static boolean inBuildableCase(int mx, int my)
    {
        int x = mx / Ground.W;
        int y = my / Ground.W;
        boolean cond1 = y < Ground.tabMap[Ground.map].length;
        boolean cond2 = x < Ground.tabMap[Ground.map][0].length;
        if(cond1 && cond2 && Interface.selec != 0)
        {
            int c = Ground.tabMap[Ground.map][y][x];
            for(Tower t : Tower.towers)
                if(x == t.xc && y == t.yc)
                    return false;
            return Ground.tabProp[c][1] == 1;
        }
        return false;
    }
    
    public static boolean inTowerCase(int mx, int my)
    {
        int x = mx / Ground.W;
        int y = my / Ground.W;
        for(Tower t : Tower.towers)
        {
            if(x == t.xc && y == t.yc)
            {
                Tower.selec = Tower.towers.indexOf(t);
                return true;
            }
        }
        return false;
    }
    
    public static boolean inDestroyButton(int mx, int my)
    {
        if(Tower.selec != -1 && !Tower.towers.isEmpty())
        {
            int w = Ground.W;
            Tower t = Tower.towers.get(Tower.selec);
            if(inRect(mx, my, (t.xc-1) * w, t.yc * w, t.xc * w, t.yc * w + 30)) 
                return true;
        }
        return false;
    }
    
    public static boolean inImproveButton(int mx, int my)
    {
        if(Tower.selec != -1 && !Tower.towers.isEmpty())
        {
            int w = Ground.W;
            Tower t = Tower.towers.get(Tower.selec);
            if(inRect(mx, my, (t.xc+1) * w, t.yc * w,(t.xc+2) * w, t.yc * w + 30)) 
                return true;
        }
        return false;
    }
    
    public static void mousePressed(int mx, int my)
    {
        switch(TD.choice)
        {
            case 0:
                if(inRect(mx, my, 530, 300, 730, 380))                          //Bouton jouer
                {
                    TD.choice = 4;
                }
                else if(inRect(mx, my, 520, 490, 720, 530))                     //Bouton score
                    TD.choice = 3;
                else if(inRect(mx, my, 560, 650, 700, 700))                     //Bouton quitter
                    TD.choice = -1;
                break;
            case 1:                                                             
                if(inRect(mx, my, 1086, 60, 1155, 131))                         ////
                {                                                               //
                    Interface.selec = 1;                                        //
                    Tower.selec = -1;                                           //
                }                                                               //
                else if(inRect(mx, my, 1205, 59, 1274, 130))                    //
                {                                                               //
                    Interface.selec = 2;                                        //
                    Tower.selec = -1;                                           //
                }                                                               //
                else if(inRect(mx, my, 1145, 169, 1214, 240))                   // Clics dans l'interface / séléction d'une tourelle
                {                                                               //
                    Interface.selec = 3;                                        //
                    Tower.selec = -1;                                           //
                }                                                               //
                else if(inRect(mx, my, 1089, 282, 1158, 353))                   //
                {                                                               //
                    Interface.selec = 4;                                        //
                    Tower.selec = -1;                                           //
                }                                                               //
                else if(inRect(mx, my, 1204, 282, 1273, 353))                   //
                {                                                               //
                    Interface.selec = 5;                                        //
                    Tower.selec = -1;                                           //
                }                                                               ////
                else if (inRect(mx, my, 10, 10, 50, 50)) 
                {
                    Move.running = false;
                    TD.choice = 2;
                }
                else if(inBuildableCase(mx, my))
                {
                    Tower.towers.add(new Tower(Interface.selec - 1, mx / Ground.W, my / Ground.W));
                    Tower.selec = Tower.towers.size() - 1;
                    Interface.selec = 0;
                }
                else if(inDestroyButton(mx, my))
                {
                    Tower.towers.remove(Tower.selec);
                    Tower.selec = -1;
                }
                else if(inImproveButton(mx, my))
                {
                    Tower.towers.get(Tower.selec).levelUp();
                    Tower.selec = -1;
                }
                else if(inTowerCase(mx, my));                                   //C'est normal panic pas...
                else if(inCircle(mx, my, 1065,700,15))
                    Sound.mute();
                else
                {
                    Tower.selec = -1;
                    Interface.selec = 0;                                        //Déselection
                }
                break;
            case 2:
                if (inRect(mx, my, 490, 330, 800, 360)) {
                    Move.running = !Move.running;
                    Pause.background = null;
                    TD.choice = 1;
                } else if (inRect(mx, my, 450, 470, 830, 500)) {
                    Move.running = !Move.running;
                    Pause.background = null;
                    Move.gameOver();
                } else if (inRect(mx, my, 530, 600, 750, 640)) {
                    TD.choice = -1;
                } else {
                    Pause.colR = TD.COLOR1;
                    Pause.colQ = TD.COLOR1;
                    Pause.colS = TD.COLOR1;
                }
                break;
            case 3:
                if(inRect(mx, my, 1000, 620, 1250, 680))
                {
                    Menu.colS = TD.COLOR1;
                    TD.choice = 0;
                }
                break;
            case 4:
                for (int i = 0; i < Ground.tabMap.length; ++i) {
                    if (inRect(mx, my, 640 - (int) Select.size[i] / 2, ((i + 1) * (600 / Ground.tabMap.length)), 640 + (int) Select.size[i] / 2, ((i + 1) * (600 / Ground.tabMap.length)) + 60)) {
                       Ground.map=i;
                       TD.gameInit();
                       TD.choice=1;
                    } 
                }
                break;
            case 5:
                if(inRect(mx, my, 420, 610, 860, 650))
                {
                    Sound.stop();
                    if(!Sound.isMute)
                        Sound.play(0);
                    TD.choice = 0;

                }
                break;
        }
    }

    public static void mouseMoved(int mx, int my)
    {
        switch(TD.choice)
        {
            case 0:
                if(inRect(mx, my, 530, 300, 730, 380)) {                        //Bouton jouer
                    if (remember != 1) {
                        Sound.playSounds(1);
                        remember=1;
                    }
                    Menu.colJ = TD.COLOR2;
                }
                else if(inRect(mx, my, 520, 490, 720, 530))  {                  //Bouton score
                    if (remember != 2) {
                        Sound.playSounds(1);
                        remember=2;
                    }
                    Menu.colS = TD.COLOR2;
                }
                else if(inRect(mx, my, 560, 650, 700, 700))  {                  //Bouton quitter
                    if (remember != 3) {
                        Sound.playSounds(1);
                        remember=3;
                    }
                    Menu.colQ = TD.COLOR2;
                }
                else
                {
                    Menu.colJ = TD.COLOR1;
                    Menu.colS = TD.COLOR1;
                    Menu.colQ = TD.COLOR1;
                    remember=-1;
                }
                break;
            case 1:
                Interface.mx = mx;                                                      //Transmission de la position de la souris à Interface.
                Interface.my = my;
                break;
            case 2:
                if (inRect(mx, my, 490, 330, 800, 360)) {
                    Pause.colR = TD.COLOR2;
                } else if (inRect(mx, my, 450, 470, 830, 500)) {
                    Pause.colS = TD.COLOR2;
                } else if (inRect(mx, my, 530, 600, 750, 640)) {
                    Pause.colQ = TD.COLOR2;
                } else {
                    Pause.colR = TD.COLOR1;
                    Pause.colQ = TD.COLOR1;
                    Pause.colS = TD.COLOR1;
                }
                break;
            case 3:
                if(inRect(mx, my, 1000, 620, 1250, 680)) {
                    if (remember != 1) {
                        Sound.playSounds(1);
                        remember=1;
                    }
                    Score.colR = TD.COLOR2;
                }
                else {
                    remember=-1;
                    Score.colR = TD.COLOR1;
                }
                break;
            case 4:
                for (int i = 0; i < Ground.tabMap.length; ++i) {
                    if (inRect(mx, my, 640 - (int) Select.size[i] / 2, ((i + 1) * (600 / Ground.tabMap.length)), 640 + (int) Select.size[i] / 2, ((i + 1) * (600 / Ground.tabMap.length)) + 60)) {
                        if (remember != 1) {
                            Sound.playSounds(1);
                            remember = 1;
                        }
                        Select.col[i] = TD.COLOR2;
                    } else {
                        Select.col[i] = TD.COLOR1;
                    }
                }
                if (remember != -1) {
                    for (int i = 0; i < Select.col.length; ++i) {
                        if (Select.col[i] == TD.COLOR1) {
                            ++remember;
                        }
                    }
                    if (remember - Select.col.length == remember) {
                        remember = -1;
                    }
                    else {
                        remember=remember-(Select.col.length-1);
                    }
                }
                break;
            case 5:
                if(inRect(mx, my, 420, 610, 860, 650)) {
                    if (remember != 1) {
                        Sound.playSounds(1);
                        remember=1;
                    }
                    GameOver.colR = TD.COLOR2;
                }
                else {
                    remember=-1;
                    GameOver.colR = TD.COLOR1;
                }
                break;
        }
    }
}