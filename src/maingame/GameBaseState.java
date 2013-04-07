package maingame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import zyklon.BearEnemy;
import zyklon.PaussiEnemy;
import zyklon.Inventory;
import zyklon.SharkEnemy;

public class GameBaseState extends BasicGameState {

    Sound gameMusic;
    
    public static TiledMap map;
    public static Player player = new Player(2590, 2590);
    PaussiEnemy paussiEnemy = new PaussiEnemy(4000, 4000);
    SharkEnemy sharkEnemy = new SharkEnemy(0, 0);
    BearEnemy bearEnemy = new BearEnemy(3000, 1000);
    public static int mapWidth;
    public static int mapHeight;
    public static int mapXPosition = 0;
    public static int mapYPosition = 0;
    Inventory inventory = new Inventory();

    int stateId = -1;

    Animation lightMask;
    
    public GameBaseState(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public int getID() {
        return this.stateId;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player.init();
        inventory.init();
        map = new TiledMap("assets/tilemap.tmx");
        gameMusic = new Sound("assets/audio/gameMusic.ogg");
        mapHeight = map.getHeight() * 64;
        mapWidth = map.getWidth() * 64;
        paussiEnemy.init();
        sharkEnemy.init();
        
        lightMask = new Animation(new Image[] {
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
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        map.render(-mapXPosition, -mapYPosition);
        player.render();
        paussiEnemy.render();
        sharkEnemy.render();
        
        lightMask.getCurrentFrame().draw(0, 0);
        inventory.render();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (!gameMusic.playing()) {
            gameMusic.loop(1.0f, 0.2f);
        }
        
        lightMask.update(delta);
        
        Input input = gc.getInput();
        player.update(input, delta);
        paussiEnemy.update(delta);
        sharkEnemy.update(delta);
        mapXPosition = (int) player.x - 542;
        mapYPosition = (int) player.y - 426;
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit();
        }
    }

}