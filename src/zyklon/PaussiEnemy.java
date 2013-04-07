package zyklon;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PaussiEnemy extends GraphicEntity {
    private static final int ANIMATION_SPEED = 150;

    public PaussiEnemy(float x, float y) {
        super("Paussi", 100, 100, x, y, 0.1f, 64, 128);
    }

    public void init() throws SlickException {
        super.init(new Image("assets/graphics/mustikka.png"));
        Animation a = new Animation(new Image[]{
                new Image("assets/paussi_uus.png"),
                new Image("assets/paussi_uus_mirror.png")
        }, ANIMATION_SPEED);
        currentSprite = a.getCurrentFrame();
        setAnimations(a, null, null, null);
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
