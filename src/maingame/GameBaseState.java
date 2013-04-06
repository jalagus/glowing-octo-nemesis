package maingame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
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
    public static Player player = new Player();
    PaussiEnemy paussiEnemy = new PaussiEnemy(1000, 1000);
    SharkEnemy sharkEnemy = new SharkEnemy(0, 0);
    BearEnemy bearEnemy = new BearEnemy(0, 1500);
    public static int mapWidth;
    public static int mapHeight;
    public static int mapXPosition = 0;
    public static int mapYPosition = 0;
    Inventory inventory = new Inventory();

    private int elapsedTime = 0;
    private float fading = 0;
    
    int stateId = -1;
    
    Image testMask;
    
    public GameBaseState(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public int getID() {
        return this.stateId;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player = new Player();
        player.init();
        inventory.init();
        map = new TiledMap("assets/area.tmx");
        gameMusic = new Sound("assets/audio/gameMusic.ogg");
        mapHeight = map.getHeight() * 64;
        mapWidth = map.getWidth() * 64;
        paussiEnemy.init();
        sharkEnemy.init();
        bearEnemy.init();
        
        testMask = new Image("assets/graphics/lightMask5.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        map.render(-mapXPosition, -mapYPosition);
        player.render();
        paussiEnemy.render();
        sharkEnemy.render();
        bearEnemy.render();
        
        Color fade = new Color(0, 0, 0, fading);
        testMask.draw(0, 0, fade);
        
        inventory.render();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        elapsedTime += delta;
        
        if (!gameMusic.playing()) {
            gameMusic.loop(1.0f, 0.2f);
        }
        
        if (elapsedTime > 500) {
            if (fading < 1) {
                fading += 0.02f;
            }
            elapsedTime = 0;
        }
        
        
        //lightMask.update(delta);
        
        Input input = gc.getInput();
        player.update(input, delta);
        paussiEnemy.update(delta);
        sharkEnemy.update(delta);
        bearEnemy.update(delta);
        mapXPosition = (int) player.x - 542;
        mapYPosition = (int) player.y - 426;
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit();
        }
    }

}