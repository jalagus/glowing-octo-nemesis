package maingame;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class GameBaseState extends BasicGameState {

    public static TiledMap map;
    Player player = new Player();

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
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        map.render((int) -player.x, (int) -player.y);
        player.render();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        player.update(input, delta);
        
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit();
        }
    }

}
