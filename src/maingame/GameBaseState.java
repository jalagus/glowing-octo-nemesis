package maingame;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import zyklon.Enemy;

public class GameBaseState extends BasicGameState {

    Sound gameMusic;
    
    public static TiledMap map;
    public static Player player = new Player();
    Enemy enemy = new Enemy(1, 1000, 1000);
    public static int mapWidth;
    public static int mapHeight;
    public static int mapXPosition = 0;
    public static int mapYPosition = 0;

    int stateId = -1;
    
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
        map = new TiledMap("assets/tilemap.tmx");
        gameMusic = new Sound("assets/audio/gameMusic.ogg");
        mapHeight = map.getHeight() * 64;
        mapWidth = map.getWidth() * 64;
        enemy.init();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        map.render(-mapXPosition, -mapYPosition);
        player.render();
        enemy.render();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (!gameMusic.playing()) {
            gameMusic.loop(1.0f, 0.2f);
        }
        
        Input input = gc.getInput();
        player.update(input, delta);
        enemy.update(delta);
        mapXPosition = (int) player.x - 542;
        mapYPosition = (int) player.y - 426;
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit();
        }
    }

}
