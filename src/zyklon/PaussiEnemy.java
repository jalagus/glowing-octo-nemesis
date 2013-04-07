package zyklon;

import maingame.GameBaseState;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PaussiEnemy {
    
    float x;
    float y;
    final float scale = 0.1f;
    Image sprite;
    Animation anim;
    private static final int animSpeed = 150;
    
    public PaussiEnemy(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void init() throws SlickException {
        sprite = new Image("assets/paussi.jpg");
        anim = new Animation(new Image[] {
                new Image("assets/paussi_uus.png"),
                new Image("assets/paussi_uus_mirror.png")
        }, animSpeed);
    }

    public void update(int delta) {
        anim.update(delta);
        runAi(GameBaseState.player.x, GameBaseState.player.y, delta);
    }

    public void runAi(float px, float py, int delta) {
        if (py > y) {
            y += scale * delta;
        }
        
        if (px > x) {
            x += scale * delta;
        }
        
        if (py < y) {
            y -= scale * delta;
        }
        
        if (px < x) {
            x -= scale * delta;
        }        
    }

    public void render() {
        sprite = anim.getCurrentFrame();
        sprite.draw(x - GameBaseState.mapXPosition - 32, y - GameBaseState.mapYPosition - 64);
    }

    @Override
    public String toString() {
        return "PaussiEnemy{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
