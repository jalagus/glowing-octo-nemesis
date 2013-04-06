package zyklon;

import maingame.GameBaseState;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PaussiEnemy {
    
    float x;
    float y;
    final float scale = 0.1f;
    Image sprite;
    
    public PaussiEnemy(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void init() throws SlickException {
        sprite = new Image("assets/paussi.jpg");
    }

    public void update(int delta) {
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
        sprite.draw(x - GameBaseState.mapXPosition - 200, y - GameBaseState.mapYPosition - 115);
    }

    @Override
    public String toString() {
        return "PaussiEnemy{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
