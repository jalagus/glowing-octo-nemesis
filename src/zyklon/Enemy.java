package zyklon;

import maingame.GameBaseState;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy {
    
    float x;
    float y;
    final float scale = 0.1f;
    int hp;
    Image sprite;
    float xScalar;
    float yScalar;
    
    public Enemy(int hp, float x, float y) {
        this.hp = hp;
        this.x = x;
        this.y = y;
    }

    public void init() throws SlickException {
        sprite = new Image("assets/paussi.jpg");
        xScalar = GameBaseState.mapWidth / 1024f;
        yScalar = GameBaseState.mapHeight / 768f;
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
        return "Enemy{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
