package zyklon;

import java.util.ArrayList;
import java.util.HashMap;

public class TileInfo {
    private static HashMap<Integer, HashMap<String, Object>> tileInfo = new HashMap<Integer, HashMap<String, Object>>();

    static {
        setTileProperty(12, "blocked", true);
        setTileProperty(16, "blocked", true);
        setTileProperty(21, "blocked", true);
        setTileProperty(13, "blocked", true);
        setTileProperty(11, "blocked", true);
        setTileProperty(15, "blocked", true);
        setTileProperty(23, "blocked", true);
        setTileProperty(22, "blocked", true);
        setTileProperty(14, "blocked", true);
        setTileProperty(41, "event", 1);
        setTileProperty(28, "pickup", 1);
        setTileProperty(31, "pickup", 1);
        setTileProperty(32, "pickup", 1);
        setTileProperty(33, "pickup", 1);
        setTileProperty(34, "pickup", 1);
        setTileProperty(35, "pickup", 1);
        setTileProperty(39, "pickup", 1);
        setTileProperty(40, "pickup", 1);
    }

    public static Object getTileProperty(int tileID, String propertyName) {
        HashMap<String, Object> info = tileInfo.get(tileID);
        if (info == null) return null;
        return info.get(propertyName);
    }

    public static void setTileProperty(int tileID, String propertyName, Object property) {
        if (!tileInfo.containsKey(tileID)) {
            tileInfo.put(tileID, new HashMap<String, Object>());
        }
        tileInfo.get(tileID).put(propertyName, property);
    }

    public static boolean tilePropertyExists(int tileID, String propertyName) {
        HashMap<String, Object> properties = tileInfo.get(tileID);
        return properties != null && properties.containsKey(propertyName);
    }
}
