import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player {
    Image leftSprite;
    Image rightSprite;
    Image upSprite;
    Image downSprite;
    Image currentSprite;
    float x = 0;
    float y = 0;
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
        if (input.isKeyDown(Input.KEY_A)) {
            x += -scale * delta;
            currentSprite = leftSprite;
        }
        if (input.isKeyDown(Input.KEY_D)) {
            x += scale * delta;
            currentSprite = rightSprite;
        }
        if (input.isKeyDown(Input.KEY_W)) {
            y += -scale * delta;
            currentSprite = upSprite;
        }
        if (input.isKeyDown(Input.KEY_S)) {
            y += scale * delta;
            currentSprite = downSprite;
        }
    }

    public void render() {
        currentSprite.draw(x, y);
    }
}
