package zyklon;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Inventory {

    Image inv;
    Image lakka;
    Image voi;
    Image jauho;
    Image maito;
    Image munat;
    Image mustikka;
    Image puolukka;
    Image sokeri;

    static boolean lakkab = false;
    static boolean voib = false;
    static boolean jauhob = false;
    static boolean maitob = false;
    static boolean munatb = false;
    static boolean mustikkab = false;
    static boolean puolukkab = false;
    static boolean sokerib = false;

    public Inventory() {
    }

    public void init() throws SlickException {
        inv = new Image("assets/graphics/inventory.png");
        lakka = new Image("assets/graphics/lakka.png");
        voi = new Image("assets/graphics/voi.png");
        jauho = new Image("assets/graphics/jauhopussi.png");
        maito = new Image("assets/graphics/maito.png");
        munat = new Image("assets/graphics/munat.png");
        mustikka = new Image("assets/graphics/mustikka.png");
        puolukka = new Image("assets/graphics/puolukka.png");
        sokeri = new Image("assets/graphics/sokeri.png");
    }

    public static void pickup(int i) {
        if (i == 40)
            sokerib = true;
        if (i == 28)
            voib = true;
        if (i == 39)
            puolukkab = true;
        if (i == 35)
            mustikkab = true;
        if (i == 34)
            munatb = true;
        if (i == 33)
            maitob = true;
        if (i == 32)
            lakkab = true;
        if (i == 31)
            jauhob = true;
    }

    public void render() {
        inv.draw(754, 0);
        if (lakkab)
            lakka.draw(960, 67);
        if (jauhob)
            jauho.draw(895, 67);
        if (maitob)
            maito.draw(892, 4);
        if (munatb)
            munat.draw(830, -1);
        if (mustikkab)
            mustikka.draw(830, 65);
        if (puolukkab)
            puolukka.draw(960, 0);
        if (sokerib)
            sokeri.draw(766, -1);
        if (voib)
            voi.draw(768, 65);
    }

}
