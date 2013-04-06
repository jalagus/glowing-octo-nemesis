package zyklon;

import java.util.ArrayList;
import java.util.HashMap;

public class TileInfo {
    private static HashMap<Integer, HashMap<String, Object>> tileInfo = new HashMap<Integer, HashMap<String, Object>>();

    static {
        setTileProperty(219, "blocked", true);
        setTileProperty(220, "blocked", true);
        setTileProperty(235, "blocked", true);
        setTileProperty(236, "blocked", true);
        setTileProperty(251, "blocked", true);
        setTileProperty(252, "blocked", true);
        setTileProperty(41, "event", 1);
        setTileProperty(79, "pickup", 1);
        setTileProperty(69, "pickup", 1);
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
