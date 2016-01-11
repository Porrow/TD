package TD.Event;

import TD.TD;
import TD.UI.Menu;

public class Event
{
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
    public static void mousePressed(int mx, int my)
    {
        switch(TD.choice)
        {
            case 0:
                if(inCircle(mx, my, 150, 150, 50))
                    Menu.col += 50;
            case 1:
                
                break;
        }
    }
}
