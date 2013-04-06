package maingame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import zyklon.TileInfo;

import java.util.HashMap;

import static maingame.MoveTile.map;

public class Player {
    Image currentSprite;

    Animation upAnim;
    Animation downAnim;
    Animation rightAnim;
    Animation leftAnim;
    Sound fuck;
    public float x;
    public float y;
    final float scale = 0.3f;

    private int animSpeed = 150;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void init() throws SlickException {
        upAnim = new Animation(new Image[]{
                new Image("assets/graphics/mummo_back_walk.png"),
                new Image("assets/graphics/mummo_back_walk_mirror.png")
        }, animSpeed);

        downAnim = new Animation(new Image[]{
                new Image("assets/graphics/mummo_front_walk_mirror.png"),
                new Image("assets/graphics/mummo_front_walk.png")
        }, animSpeed);

        leftAnim = new Animation(new Image[]{
                new Image("assets/graphics/mummo_left_walk.png"),
                new Image("assets/graphics/mummo_left_stationary.png")
        }, animSpeed);

        rightAnim = new Animation(new Image[]{
                new Image("assets/graphics/mummo_right_walk.png"),
                new Image("assets/graphics/mummo_right_stationary.png")
        }, animSpeed);

        currentSprite = downAnim.getCurrentFrame();

        fuck = new Sound("assets/fuck.ogg");
    }

    int prevTileID = -1;

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
        }
        else if (input.isKeyDown(Input.KEY_D)) {
            xMovement = scale * delta;
            currentSprite = rightAnim.getCurrentFrame();
        }

        int tileID = GameBaseState.map.getTileId((int) (x + xMovement) / 64, (int) y / 64, 1);
        if (!TileInfo.tilePropertyExists(tileID, "blocked")) {
            x += xMovement;
        }

        if (input.isKeyDown(Input.KEY_W)) {
            yMovement = -scale * delta;
            currentSprite = upAnim.getCurrentFrame();
        }
        else if (input.isKeyDown(Input.KEY_S)) {
            yMovement = scale * delta;
            currentSprite = downAnim.getCurrentFrame();
        }

        tileID = GameBaseState.map.getTileId((int) x / 64, (int) (y + yMovement) / 64, 1);
        if (!TileInfo.tilePropertyExists(tileID, "blocked")) {
            y += yMovement;
        }

    }

    public void render() throws SlickException {
        currentSprite.draw(512, 384);

        int tileID = GameBaseState.map.getTileId((int) x / 64, (int) y / 64, 1);
        if (tileID != prevTileID) {
            System.out.println(tileID);
            prevTileID = tileID;
        }
        if (TileInfo.tilePropertyExists(tileID, "event")) {
            int eventID = (Integer) TileInfo.getTileProperty(tileID, "event");
            if (!fuck.playing()) fuck.play();
        }
        if (TileInfo.tilePropertyExists(tileID, "pickup")) {
            int eventID = (Integer) TileInfo.getTileProperty(tileID, "pickup");
            zyklon.Inventory.pickup(tileID);
            map.setTileId((int) x / 64, (int) y / 64, 1, 0);
        }
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
