package zyklon;

import org.newdawn.slick.*;

public class Inventory {
    
    Image lakka;
    Image voi;
    static boolean lakkab = false;
    static boolean voib = false;
    
    public Inventory() {
    }
    
    public void init() throws SlickException {
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
        if(lakkab)
            lakka.draw(960,0);
        if(voib)
            voi.draw(896,0);
    }
    
}
