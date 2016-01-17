package TD;

import TD.UI.Ground;
import TD.UI.Interface;
import TD.UI.Unit;

public class Move extends Thread {

    @Override
    public void run() {
        long tisl, respawn = 0, chrono = 0, inter = 0;
        Unit uni = null;
        while (true) {
            tisl = Math.round(1000. / TD.fps);
            synchronized (Unit.units) {
                if (!Unit.units.isEmpty()) {
                    if (!Unit.toSpawn.isEmpty()) {
                        respawn += tisl;
                        if (respawn > 700) {
                            respawn = 0;
                            Unit.units.add(Unit.toSpawn.get(0));
                            Unit.toSpawn.remove(0);
                        }
                    }
                    for (Unit u : Unit.units) {

                        if (Ground.tabMap[Ground.map][u.yc][u.xc] == Ground.DESPAWN) {
                            --TD.life;
                            uni = u;
                        } else {
                            switch (u.direc) {
                                case 0:
                                    u.y -= (float) (u.speed) / TD.fps;
                                    if (u.y <= u.yc * Ground.W) {
                                        u.direc = u.getDirection();
                                    }
                                    break;
                                case 1:
                                    u.y += (float) (u.speed) / TD.fps;
                                    if (u.y >= u.yc * Ground.W) {
                                        u.direc = u.getDirection();
                                    }
                                    break;
                                case 2:
                                    u.x -= (float) (u.speed) / TD.fps;
                                    if (u.x <= u.xc * Ground.W) {
                                        u.direc = u.getDirection();
                                    }
                                    break;
                                case 3:
                                    u.x += (float) (u.speed) / TD.fps;
                                    if (u.x >= u.xc * Ground.W) {
                                        u.direc = u.getDirection();
                                    }
                                    break;
                            }
                        }

                        //System.out.println(u.time);
                        if (Unit.tabAnim[u.type][u.img] <= (int)(u.time)) {
                            u.time -= Unit.tabAnim[u.type][u.img];
                            u.img = (u.img + 1) % Unit.tabAnim[u.type].length;
                        }
                        u.time += tisl;
                        //System.out.println("x = "+u.x+"\ny = "+u.y);
                    }
                    if (uni != null) {
                        Unit.units.remove(uni);
                    }
                } else {
                    inter=java.lang.System.currentTimeMillis() - chrono;
                    if (chrono == 0) {
                        chrono = java.lang.System.currentTimeMillis();
                    } else if (inter >= 5000) {
                        Unit.loadEnemies(Unit.number);
                        Unit.units.add(Unit.toSpawn.get(0));
                        Unit.toSpawn.remove(0);
                        chrono = 0;
                    } else {
                        Interface.inter=5000-inter;                                  //afficher le temps restant avant la prochaine vague    
                    }
                }
            }
            try {
                Thread.sleep(tisl);
            } catch (InterruptedException e) {
            }
        }
    }
}