package maingame;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import zyklon.TileInfo;

public class Player {
    Image leftSprite;
    Image rightSprite;
    Image upSprite;
    Image downSprite;
    Image currentSprite;
    public float x = 0;
    public float y = 0;
    final float scale = 0.3f;

    public Player() {

    }

    public void init() throws SlickException {
        leftSprite = new Image("assets/player_left.png");
        rightSprite = new Image("assets/player_right.png");
        upSprite = new Image("assets/player_up.png");
        downSprite = new Image("assets/player_down.png");
        currentSprite = downSprite;
    }

    public void update(Input input, int delta) {
        float xMovement = 0;
        float yMovement = 0;

        if (input.isKeyDown(Input.KEY_A)) {
            xMovement = -scale * delta;
            currentSprite = leftSprite;
        } else if (input.isKeyDown(Input.KEY_D)) {
            xMovement = scale * delta;
            currentSprite = rightSprite;
        }

        int tileID = GameBaseState.map.getTileId((int) (x + xMovement + 20) / 64, (int) (y + 20) / 64, 1);
        if (!TileInfo.tilePropertyExists(tileID, "blocked")){
            x += xMovement;
            if (x < 0) x = 0;
            else if (x > 964) x = 964;
        }

        if (input.isKeyDown(Input.KEY_W)) {
            yMovement = -scale * delta;
            currentSprite = upSprite;
        } else if (input.isKeyDown(Input.KEY_S)) {
            yMovement = scale * delta;
            currentSprite = downSprite;
        }

        tileID = GameBaseState.map.getTileId((int) (x + 20) / 64, (int) (y + yMovement + 20) / 64, 1);
        if (!TileInfo.tilePropertyExists(tileID, "blocked")){
            y += yMovement;
            if (y < 0) y = 0;
            else if (y > 728) y = 728;
        }
    }

    public void render() {
        currentSprite.draw(x, y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
