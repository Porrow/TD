package TD;

import TD.UI.Ground;
import TD.UI.Unit;

public class Move extends Thread
{
    @Override
    public synchronized void run()
    {
        long tisl;
        while(true)
        {
            tisl = Math.round(1000. / TD.fps);
            for(Unit u : Unit.units)
            {
                switch(u.direc)
                {
                    case 0:
                        u.y -= (float)(u.speed) / TD.fps;
                        if(u.y <= u.yc * Ground.W)
                            u.direc = u.getDirection();
                        break;
                    case 1:
                        u.y += (float)(u.speed) / TD.fps;
                        if(u.y >= u.yc * Ground.W)
                            u.direc = u.getDirection();
                        break;
                    case 2:
                        u.x -= (float)(u.speed) / TD.fps;
                        if(u.x <= u.xc * Ground.W)
                            u.direc = u.getDirection();
                        break;
                    case 3:
                        u.x += (float)(u.speed) / TD.fps;
                        if(u.x >= u.xc * Ground.W)
                            u.direc = u.getDirection();
                        break;
                        
                }
                //System.out.println(u.time);
                if(Unit.tabAnim[u.type][u.img] <= (int)(u.time))
                {
                    u.time -= Unit.tabAnim[u.type][u.img];
                    u.img = (u.img+1) % Unit.tabAnim[u.type].length;
                }
                u.time += tisl;
                //System.out.println("x = "+u.x+"\ny = "+u.y);
            }
            try{Thread.sleep(tisl);}
            catch (InterruptedException e){}
        }
    }
}
