package zyklon;

public class Enemy {
    
    float x;
    float y;
    
    int hp;
    
    public Enemy(int hp, float x, float y) {
        this.hp = hp;
        this.x = x;
        this.y = y;
    }
    
    public void runAi(int px, int py, float delta) {
        if (py > y) {
            y += 1 * delta;
        }
        
        if (px > x) {
            x += 1 * delta;
        }
        
        if (py < y) {
            y -= 1 * delta;
        }
        
        if (px < x) {
            x -= 1 * delta;
        }        
    }

}
