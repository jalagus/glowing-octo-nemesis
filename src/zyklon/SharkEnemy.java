package zyklon;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SharkEnemy extends GraphicEntity {
    private static final int ANIMATION_SPEED = 150;

    public SharkEnemy(float x, float y) {
        super("Gashunk", 100, 100, x, y, 0.15f, 128, 64);
    }

    public void init() throws SlickException {
        super.init(new Image("assets/graphics/taistelureunat_hai.png"));

        Animation left = new Animation(new Image[]{
                new Image("assets/graphics/hai_left_stationary.png"),
                new Image("assets/graphics/hai_left_swim.png")
        }, ANIMATION_SPEED);
        Animation right = new Animation(new Image[]{
                new Image("assets/graphics/hai_right_stationary.png"),
                new Image("assets/graphics/hai_right_swim.png")
        }, ANIMATION_SPEED);
        setAnimations(left, right, null, null);
        currentSprite = left.getCurrentFrame();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        super.update(container, game, delta);
        runAi(GameBaseState.player.x, GameBaseState.player.y, delta);
    }

    public void runAi(float px, float py, int delta) {
        if (py > y) {
            y += scale * delta;
            if (downAnimation != null) currentSprite = downAnimation.getCurrentFrame();
        }
        if (px > x) {
            x += scale * delta;
            if (rightAnimation != null) currentSprite = rightAnimation.getCurrentFrame();
        }
        if (py < y) {
            y -= scale * delta;
            if (upAnimation != null) currentSprite = upAnimation.getCurrentFrame();
        }
        if (px < x) {
            x -= scale * delta;
            if (leftAnimation != null) currentSprite = leftAnimation.getCurrentFrame();
        }
    }

}
