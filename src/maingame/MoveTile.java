
package maingame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

public class MoveTile {

    static TiledMap map = GameBaseState.map;
    static HashMap hmap = new HashMap();
    static HashSet itemSet = new HashSet();
    static HashSet placeSet = new HashSet();

    public static void move() {
        random();
        GameBaseState.map = map;
    }

    private static void random() {
        Random rndm = new Random();
        int num1;
        int num2;

        int tileID1 = map.getTileId(0, 0, 1);
        int tileID2 = map.getTileId(1, 0, 1);
        int tileID3 = map.getTileId(2, 0, 1);

        hmap.put(1, tileID1);
        hmap.put(2, tileID2);
        hmap.put(3, tileID3);

        for (int i = 0; i < 3; ) {
            num1 = rndm.nextInt(3) + 1;
            if (!itemSet.contains(num1)) {
                loop:
                for (int j = 0; j < 3; ) {
                    num2 = rndm.nextInt(3) + 1;
                    if (!placeSet.contains(num2)) {
                        if (num2 == 1) {
                            map.setTileId(0, 1, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 2) {
                            map.setTileId(1, 2, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 3) {
                            map.setTileId(2, 3, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                    }
                }
                itemSet.add(num1);
                i++;
            }

            map.setTileId(0, 0, 1, 0);
            map.setTileId(1, 0, 1, 0);
            map.setTileId(2, 0, 1, 0);
        }
    }
}
