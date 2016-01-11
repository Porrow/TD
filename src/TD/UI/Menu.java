package TD.UI;

import processing.core.PGraphics;

public class Menu 
{
    public static int col = 0;
    public static void draw(PGraphics g)
    {
        //g.color(col);
        g.fill(col % 255);
        g.ellipse(150, 150, 100, 100);
    }
}
