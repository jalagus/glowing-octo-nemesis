package maingame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import zyklon.TileInfo;

public class Player {
    Image currentSprite;
    
    Animation upAnim;
    Animation downAnim;
    Animation rightAnim;
    Animation leftAnim;
    
    public float x = 0;
    public float y = 0;
    final float scale = 0.3f;
    
    private int animSpeed = 250;

    public Player() {

    }

    public void init() throws SlickException {
        upAnim = new Animation(new Image[] {
            new Image("assets/graphics/mummo_back_walk.png"),
            new Image("assets/graphics/mummo_back_walk_mirror.png")
            }, animSpeed);
        
        downAnim = new Animation(new Image[] {
            new Image("assets/graphics/mummo_front_walk_mirror.png"),
            new Image("assets/graphics/mummo_front_walk.png")
            }, animSpeed);
        
        leftAnim = new Animation(new Image[] {
            new Image("assets/graphics/mummo_left_walk.png"),
            new Image("assets/graphics/mummo_left_stationary.png")
            }, animSpeed);
        
        rightAnim = new Animation(new Image[] {
            new Image("assets/graphics/mummo_right_walk.png"),
            new Image("assets/graphics/mummo_right_stationary.png")
            }, animSpeed);        
        
        currentSprite = downAnim.getCurrentFrame();
    }

    public void update(Input input, int delta) {
        rightAnim.update(delta);
        downAnim.update(delta);
        upAnim.update(delta);
        leftAnim.update(delta);
        
        float xMovement = 0;
        float yMovement = 0;

        if (input.isKeyDown(Input.KEY_A)) {
            xMovement = -scale * delta;
            currentSprite = leftAnim.getCurrentFrame();
        } else if (input.isKeyDown(Input.KEY_D)) {
            xMovement = scale * delta;
            currentSprite = rightAnim.getCurrentFrame();
        }

        int tileID = GameBaseState.map.getTileId((int) (x + xMovement + 20 + 512) / 64, (int) (y + 20 + 384) / 64, 1);
        if (!TileInfo.tilePropertyExists(tileID, "blocked")){
            x += xMovement;
        }

        if (input.isKeyDown(Input.KEY_W)) {
            yMovement = -scale * delta;
            currentSprite = upAnim.getCurrentFrame();
        } else if (input.isKeyDown(Input.KEY_S)) {
            yMovement = scale * delta;
            currentSprite = downAnim.getCurrentFrame();
        }

        tileID = GameBaseState.map.getTileId((int) (x + 20 + 512) / 64, (int) (y + yMovement + 20 + 384) / 64, 1);
        if (!TileInfo.tilePropertyExists(tileID, "blocked")){
            y += yMovement;
        }
    }

    public void render() {
        currentSprite.draw(512, 384);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
