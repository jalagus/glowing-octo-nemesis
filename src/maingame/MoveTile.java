
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

        int tileID1 = map.getTileId(9, 9, 1);
        int tileID2 = map.getTileId(11, 9, 1);
        int tileID3 = map.getTileId(13, 9, 1);
        int tileID4 = map.getTileId(15, 9, 1);
        int tileID5 = map.getTileId(17, 9, 1);
        int tileID6 = map.getTileId(19, 9, 1);
        int tileID7 = map.getTileId(21, 9, 1);
        int tileID8 = map.getTileId(23, 9, 1);

        hmap.put(1, tileID1);
        hmap.put(2, tileID2);
        hmap.put(3, tileID3);
        hmap.put(4, tileID4);
        hmap.put(5, tileID5);
        hmap.put(6, tileID6);
        hmap.put(7, tileID7);
        hmap.put(8, tileID8);

        for (int i = 0; i < 8; ) {
            num1 = rndm.nextInt(8) + 1;
            if (!itemSet.contains(num1)) {
                loop:
                for (int j = 0; j < 8; ) {
                    num2 = rndm.nextInt(16) + 1;
                    if (!placeSet.contains(num2)) {
                        if (num2 == 1) {
                            map.setTileId(63, 67, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 2) {
                            map.setTileId(50, 69, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 3) {
                            map.setTileId(65, 43, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 4) {
                            map.setTileId(63, 13, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 5) {
                            map.setTileId(38, 10, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 6) {
                            map.setTileId(12, 12, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 7) {
                            map.setTileId(17, 27, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 8) {
                            map.setTileId(11, 41, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 9) {
                            map.setTileId(21, 61, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 10) {
                            map.setTileId(44, 57, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 11) {
                            map.setTileId(54, 25, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 12) {
                            map.setTileId(32, 32, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 13) {
                            map.setTileId(31, 50, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 14) {
                            map.setTileId(59, 57, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 15) {
                            map.setTileId(53, 19, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                        else if (num2 == 16) {
                            map.setTileId(34, 45, 1, (Integer) hmap.get(num1));
                            placeSet.add(num2);
                            j++;
                            break loop;
                        }
                    }
                }
                itemSet.add(num1);
                i++;
            }
        }

            map.setTileId(9, 9, 1, 0);
            map.setTileId(11, 9, 1, 0);
            map.setTileId(13, 9, 1, 0);
            map.setTileId(15, 9, 1, 0);
            map.setTileId(17, 9, 1, 0);
            map.setTileId(19, 9, 1, 0);
            map.setTileId(21, 9, 1, 0);
            map.setTileId(23, 9, 1, 0);
        }
    }

