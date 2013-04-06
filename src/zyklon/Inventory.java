package zyklon;

import org.newdawn.slick.*;

public class Inventory {
    
    Image lakka;
    Image voi;
    Image inv;
    static boolean lakkab = false;
    static boolean voib = false;
    
    public Inventory() {
    }
    
    public void init() throws SlickException {
        inv = new Image("assets/graphics/inventory.png");
        lakka = new Image("assets/graphics/lakka.png");
        voi = new Image("assets/graphics/voi.png");
    }
    
    public static void pickup(int i) {
        if(i == 79)
            lakkab = true;
        if(i == 69)
            voib = true;
    }
    
    public void render() {
        inv.draw(754,0);
        if(lakkab)
            lakka.draw(960,67);
        if(voib)
            voi.draw(768,65);
    }
    
}
