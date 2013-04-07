package zyklon;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

public class GameBaseState extends BasicGameState {

    Sound gameMusic;

    public static TiledMap map;
    public static Player player = new Player(2590, 2590);
    public ArrayList<GraphicEntity> entities;
    public static int mapWidth;
    public static int mapHeight;
    public static int mapXPosition = 0;
    public static int mapYPosition = 0;
    Inventory inventory = new Inventory();

    int stateId = -1;

    Animation lightMask;

    public GameBaseState(int stateId) {
        this.stateId = stateId;
        entities = new ArrayList<GraphicEntity>();
        entities.add(player);
        entities.add(new PaussiEnemy(4000, 4000));
        entities.add(new SharkEnemy(0, 0));
        entities.add(new BearEnemy(3000, 1000));
    }

    @Override
    public int getID() {
        return this.stateId;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        map = new TiledMap("assets/area.tmx");
        gameMusic = new Sound("assets/audio/gameMusic.ogg");
        mapHeight = map.getHeight() * 64;
        mapWidth = map.getWidth() * 64;
        for (GraphicEntity ge : entities) {
            ge.init();
        }
        inventory.init();
        MoveTile.move();

        lightMask = new Animation(new Image[]{
                new Image("assets/graphics/lightMask1.png"),
                new Image("assets/graphics/lightMask2.png"),
                new Image("assets/graphics/lightMask3.png"),
                new Image("assets/graphics/lightMask4.png"),
                new Image("assets/graphics/lightMask5.png")
        }, 2000);

        lightMask.start();
        lightMask.stopAt(4);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException {
        map.render(-mapXPosition, -mapYPosition);
        for (GraphicEntity ge : entities) {
            ge.render(container, game, graphics);
        }

        lightMask.getCurrentFrame().draw(0, 0);
        inventory.render();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (!gameMusic.playing()) {
            gameMusic.loop(1.0f, 0.2f);
        }

        lightMask.update(delta);

        for (GraphicEntity ge : entities) {
            ge.update(container, game, delta);
        }

        mapXPosition = (int) player.x - 542;
        mapYPosition = (int) player.y - 426;

        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            container.exit();
        }
    }

}