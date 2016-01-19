package TD;

import TD.Event.Event;
import TD.Sound.Sound;
import TD.UI.Bullet;
import TD.UI.Ground;
import TD.UI.Interface;
import TD.UI.Tower;
import TD.UI.Unit;

public class Move extends Thread {

    private final int LATENCE = 5000;                                           //Temps entre 2 vagues
    private final int SPACE = 700;                                              //Temps entre 2 spawns d'unités
    private final int W = Ground.W;

    public static boolean stop;
    public static boolean running = true;

    public static float getDistance(float x1, float y1, float x2, float y2) {
        float vx = Math.abs(x1 - x2);
        float vy = Math.abs(y1 - y2);
        return (float) (Math.sqrt(vx * vx + vy * vy));
    }

    public void getFocus(Tower t) {
        float mR = -1;
        float d;
        Unit fU = null;
        for (Unit u : Unit.units) {
            d = getDistance(t.xc * W, t.yc * W, (int) (u.x), (int) (u.y));
            if (mR == -1 || mR > d) {
                mR = d;
                fU = u;
            }
        }
        if (fU != null) {
            if (Event.inCircle(t.xc * W, t.yc * W, (int) (fU.x), (int) (fU.y), t.range)) {
                t.focus = fU;
            }
        }
    }

    public Unit animation(long tisl) {
        Unit uni = null;
        for (Unit u : Unit.units) {
            if (Ground.tabMap[Ground.map][u.yc][u.xc] == Ground.DESPAWN) {
                --TD.life;
                uni = u;
            } else if (u.life <= 0) {
                uni = u;
            } else {
                switch (u.direc) {
                    case 0:
                        u.y -= (float) (u.speed) / TD.fps;
                        if (u.y <= u.yc * W) {
                            u.direc = u.getDirection();
                        }
                        break;
                    case 1:
                        u.y += (float) (u.speed) / TD.fps;
                        if (u.y >= u.yc * W) {
                            u.direc = u.getDirection();
                        }
                        break;
                    case 2:
                        u.x -= (float) (u.speed) / TD.fps;
                        if (u.x <= u.xc * W) {
                            u.direc = u.getDirection();
                        }
                        break;
                    case 3:
                        u.x += (float) (u.speed) / TD.fps;
                        if (u.x >= u.xc * W) {
                            u.direc = u.getDirection();
                        }
                        break;
                }
            }

            if (Unit.tabAnim[u.type][u.img] <= (int) (u.time)) {
                u.time -= Unit.tabAnim[u.type][u.img];
                u.img = (u.img + 1) % Unit.tabAnim[u.type].length;
            }
            u.time += tisl;
        }
        return uni;
    }

    public void focus(long tisl) {
        for (Tower t : Tower.towers) {
            if (t.focus != null) {
                if (!Event.inCircle(t.xc * W, t.yc * W, (int) (t.focus.x), (int) (t.focus.y), t.range)
                        || !Unit.units.contains(t.focus)) {
                    t.focus = null;
                } else if (t.speed <= (int) (t.time)) {
                    t.time -= t.speed;
                    synchronized (Bullet.bullets) {
                        Bullet.bullets.add(new Bullet(t));
                    }
                }
                t.time += tisl;
            } else {
                getFocus(t);
            }
        }
    }

    public Bullet animBullet(long tisl) {
        Bullet bul = null;
        for (Bullet b : Bullet.bullets) {
            if (!Unit.units.contains(b.u)) {
                bul = b;
            } else if (b.speed <= b.time) {
                b.time -= b.speed;
                b.x -= b.vect[0];
                b.y -= b.vect[1];
                b.set();
                if (b.d < Unit.colli) //Collision
                {
                    bul = b;
                    b.u.life -= b.t.att;
                }
            }
            b.time += tisl;
        }
        return bul;
    }

    public static void gameOver() {
        Tower.towers.removeAll(Tower.towers);
        synchronized (Unit.units) {
            Unit.units.removeAll(Unit.units);
        }
        Unit.toSpawn.removeAll(Unit.toSpawn);
        Sound.stop();
        if (!Sound.isMute) {
            Sound.play(2);
        }
        TD.choice = 5;
        stop = true;
    }

    @Override
    public void run() {
        long tisl, respawn = 0, chrono = 0, inter = 0;
        Unit uni = null;
        Bullet bul = null;
        while (!stop) {
            tisl = Math.round(1000. / TD.fps);
            if (running) {
                if (!Unit.units.isEmpty()) {
                    if (!Unit.toSpawn.isEmpty()) {
                        respawn += tisl;
                        if (respawn > SPACE) {
                            respawn = 0;
                            synchronized (Unit.units) {
                                Unit.units.add(Unit.toSpawn.get(0));
                            }
                            Unit.toSpawn.remove(0);
                        }
                    }
                    uni = animation(tisl);
                    focus(tisl);
                    bul = animBullet(tisl);
                    if (uni != null) {
                        synchronized (Unit.units) {
                            Unit.units.remove(uni);
                        }
                    }
                    if (bul != null) {
                        synchronized (Bullet.bullets) {
                            Bullet.bullets.remove(bul);
                        }
                    }
                } else {
                    inter = java.lang.System.currentTimeMillis() - chrono;
                    if (chrono == 0) {
                        ++Unit.wave;
                        chrono = java.lang.System.currentTimeMillis();
                    } else if (inter >= LATENCE) {
                        Unit.newWave(Unit.wave);
                        synchronized (Unit.units) {
                            Unit.units.add(Unit.toSpawn.get(0));
                        }
                        Unit.toSpawn.remove(0);
                        chrono = 0;
                    } else {
                        Interface.inter = LATENCE - inter;                      //afficher le temps restant avant la prochaine vague    
                    }
                }
                if (TD.life <= 0) //Game Over
                {
                    gameOver();
                }
            }
            try {
                Thread.sleep(tisl);
            } catch (InterruptedException e) {
            }
        }
    }
}
