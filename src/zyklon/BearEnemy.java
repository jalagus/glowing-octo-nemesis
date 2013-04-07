package zyklon;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BearEnemy extends GraphicEntity {

    private static final int ANIMATION_SPEED = 150;

    public BearEnemy(float x, float y) {
        super("Urho", 80, 80, x, y, 0.12f, 128, 64);
    }

    public void init() throws SlickException {
        super.init(new Image("assets/graphics/taistelureunat_bmur.png"));
        Animation left = new Animation(new Image[]{
                new Image("assets/graphics/urho_left_stationary.png"),
                new Image("assets/graphics/urho_left_walk.png")
        }, ANIMATION_SPEED);
        Animation right = new Animation(new Image[]{
                new Image("assets/graphics/urho_right_stationary.png"),
                new Image("assets/graphics/urho_right_walk.png")
        }, ANIMATION_SPEED);
        setAnimations(left, right, null, null);
        currentSprite = left.getCurrentFrame();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        super.update(container, game, delta);
        runAi(GameBaseState.player.x, GameBaseState.player.y, delta);
    }

    float sprint = 1;

    public void runAi(float px, float py, int delta) {
        if (Math.abs(px - x) + Math.abs(py - y) < 300 && Math.abs(px - x) + Math.abs(py - y) > 100) {
            sprint = 2f;
        } else sprint = 1;

        if (py > y) {
            y += scale * delta * sprint;
            if (downAnimation != null) currentSprite = downAnimation.getCurrentFrame();
        }
        if (px > x) {
            x += scale * delta * sprint;
            if (rightAnimation != null) currentSprite = rightAnimation.getCurrentFrame();
        }
        if (py < y) {
            y -= scale * delta * sprint;
            if (upAnimation != null) currentSprite = upAnimation.getCurrentFrame();
        }
        if (px < x) {
            x -= scale * delta * sprint;
            if (leftAnimation != null) currentSprite = leftAnimation.getCurrentFrame();
        }
    }

}
