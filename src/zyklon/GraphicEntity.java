package zyklon;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


public abstract class GraphicEntity {
    public int hp;
    public final int maxHp;
    public final String name;

    public float x;
    public float y;
    public final float width;
    public final float height;
    public final float scale;

    public Image currentSprite;
    public Image fightSprite;

    Animation leftAnimation;
    Animation rightAnimation;
    Animation upAnimation;
    Animation downAnimation;
    
    public boolean active = true;


    public GraphicEntity(String name, int hp, int maxHp, float x, float y, float scale, int width, int height) {
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.width = width;
        this.height = height;
    }

    public abstract void init() throws SlickException;

    public void init(Image fightSprite) {
        this.fightSprite = fightSprite;
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (rightAnimation != null) rightAnimation.update(delta);
        if (leftAnimation != null) leftAnimation.update(delta);
        if (upAnimation != null) upAnimation.update(delta);
        if (downAnimation != null) downAnimation.update(delta);
    }


    public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException {
        currentSprite.draw(x - GameBaseState.mapXPosition - width / 2, y - GameBaseState.mapYPosition - height / 2);
    }

    public void setAnimations(Animation left, Animation right, Animation up, Animation down) {
        leftAnimation = left;
        rightAnimation = right;
        upAnimation = up;
        downAnimation = down;
    }

    @Override
    public String toString() {
        return name + " {" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
