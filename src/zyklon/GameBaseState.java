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
    
    Image shadowMask;

    private int elapsedTime = 0;
    private float fading = 0;    
    
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
        
        shadowMask = new Image("assets/graphics/lightMask5.png");

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException {
        map.render(-mapXPosition, -mapYPosition);
        for (GraphicEntity ge : entities) {
            if (ge.active) {    
                ge.render(container, game, graphics);
            }
        }
        
        Color fade = new Color(0, 0, 0, fading);
        shadowMask.draw(0, 0, fade);        
        
        inventory.render();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
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
        
        for (GraphicEntity ge : entities) {
            if (ge.active) {
                ge.update(container, game, delta);

                if (checkCollision(ge, player)) {
                    gameMusic.stop();
                    PokemonFightState pks = (PokemonFightState) game.getState(Main.FIGHTSTATE);

                    pks.setEnemyAndPlayer(player, ge);

                    game.enterState(Main.FIGHTSTATE);

                    ge.active = false;
                }
            }
        }

        mapXPosition = (int) player.x - 542;
        mapYPosition = (int) player.y - 426;

        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(Main.MAINMENUSTATE);
        }
    }
    
    private boolean checkCollision(GraphicEntity enemy, Player player) {
        int colpoint_x = (int) (player.x - enemy.x);
        int colpoint_y = (int) (player.y - enemy.y);
        
        
        if (colpoint_x > -10 && colpoint_x < 10 && colpoint_y > -10 && colpoint_y < 10) {
            return true;
        }
        return false;
    }

}