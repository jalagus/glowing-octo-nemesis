package zyklon;

import maingame.GameBaseState;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BearEnemy extends Enemy {

    float x;
    float y;
    final float scale = 0.12f;
    Image currentSprite;
    Animation leftAnim;
    Animation rightAnim;
    private static final int animSpeed = 150;

    public BearEnemy(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void init() throws SlickException {
        super.hp = 100;
        super.maxHp = 100;        
        super.fightGraphic = new Image("assets/graphics/taistelureunat_bmur.png");
        
        
        leftAnim = new Animation(new Image[]{
                new Image("assets/graphics/urho_left_stationary.png"),
                new Image("assets/graphics/urho_left_walk.png")
        }, animSpeed);
        rightAnim = new Animation(new Image[]{
                new Image("assets/graphics/urho_right_stationary.png"),
                new Image("assets/graphics/urho_right_walk.png")
        }, animSpeed);
        currentSprite = leftAnim.getCurrentFrame();
    }

    public void update(int delta) {
        leftAnim.update(delta);
        rightAnim.update(delta);
        runAi(GameBaseState.player.x, GameBaseState.player.y, delta);
    }

    public void runAi(float px, float py, int delta) {
        if (py > y) {
            y += scale * delta;
        }
        if (px > x) {
            x += scale * delta;
            currentSprite = rightAnim.getCurrentFrame();
        }
        if (py < y) {
            y -= scale * delta;
        }
        if (px < x) {
            x -= scale * delta;
            currentSprite = leftAnim.getCurrentFrame();
        }
    }

    public void render() {
        currentSprite.draw(x - GameBaseState.mapXPosition - 64, y - GameBaseState.mapYPosition - 32);
    }

    @Override
    public String toString() {
        return "BearEnemy{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
