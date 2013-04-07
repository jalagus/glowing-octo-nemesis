package zyklon;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import static zyklon.MoveTile.map;

public class Player extends GraphicEntity {
    private static final int ANIMATION_SPEED = 150;

    Sound fuck;

    public Player(float x, float y) {
        super("Mummu", 200, 200, x, y, 0.3f, 64, 64);
    }

    public void init() throws SlickException {
        super.init(new Image("assets/paussi.jpg"));
        Animation up = new Animation(new Image[]{
                new Image("assets/graphics/mummo_back_walk.png"),
                new Image("assets/graphics/mummo_back_walk_mirror.png")
        }, ANIMATION_SPEED);

        Animation down = new Animation(new Image[]{
                new Image("assets/graphics/mummo_front_walk_mirror.png"),
                new Image("assets/graphics/mummo_front_walk.png")
        }, ANIMATION_SPEED);

        Animation left = new Animation(new Image[]{
                new Image("assets/graphics/mummo_left_walk.png"),
                new Image("assets/graphics/mummo_left_stationary.png")
        }, ANIMATION_SPEED);

        Animation right = new Animation(new Image[]{
                new Image("assets/graphics/mummo_right_walk.png"),
                new Image("assets/graphics/mummo_right_stationary.png")
        }, ANIMATION_SPEED);
        setAnimations(left, right, up, down);
        currentSprite = down.getCurrentFrame();

        fuck = new Sound("assets/fuck.ogg");
    }

    int prevTileID = -1;

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        super.update(container, game, delta);

        float xMovement = 0;
        float yMovement = 0;
        Input input = container.getInput();

        if (input.isKeyDown(Input.KEY_A)) {
            xMovement = -scale * delta;
            currentSprite = leftAnimation.getCurrentFrame();
        }
        else if (input.isKeyDown(Input.KEY_D)) {
            xMovement = scale * delta;
            currentSprite = rightAnimation.getCurrentFrame();
        }

        int tileID = GameBaseState.map.getTileId((int) (x + xMovement) / 64, (int) y / 64, 1);
        if (!TileInfo.tilePropertyExists(tileID, "blocked")) {
            x += xMovement;
        }

        if (input.isKeyDown(Input.KEY_W)) {
            yMovement = -scale * delta;
            currentSprite = upAnimation.getCurrentFrame();
        }
        else if (input.isKeyDown(Input.KEY_S)) {
            yMovement = scale * delta;
            currentSprite = downAnimation.getCurrentFrame();
        }

        tileID = GameBaseState.map.getTileId((int) x / 64, (int) (y + yMovement) / 64, 1);
        if (!TileInfo.tilePropertyExists(tileID, "blocked")) {
            y += yMovement;
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException {
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
}
