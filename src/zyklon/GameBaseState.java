package zyklon;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

public class GameBaseState extends BasicGameState {

    public static float insane = 0;
    Sound gameMusic;

    public static TiledMap map;
    public static Player player = new Player(2590, 2590);
    public static ArrayList<GraphicEntity> entities;
    public static int mapWidth;
    public static int mapHeight;
    public static int mapXPosition = 0;
    public static int mapYPosition = 0;
    Inventory inventory = new Inventory();
    Animation insaneAnimation;
    Sound gachunk;

    int stateId = -1;

    Image shadowMask;

    private int elapsedTime = 0;
    private float fading = 0;

    public GameBaseState(int stateId) {
        Main.points = 10000;
        
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
        insaneAnimation = new Animation(new Image[] {
                new Image("assets/insanity.png"),
                new Image("assets/insanity2.png")
        }, 200);
        shadowMask = new Image("assets/graphics/lightMask5.png");
        gachunk = new Sound("assets/gachunk.ogg");
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
        if (insane != 0) {
            insaneAnimation.draw(0, 0, new Color(0, 0, 0, insane * 0.004f));
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        elapsedTime += delta;
        insaneAnimation.update(delta);
        insane -= delta * 0.01f;
        if (insane < 0) insane = 0;

        if (!gameMusic.playing()) {
            gameMusic.loop(1.0f, 0.2f);
        }

        if (elapsedTime > 500) {
            if (fading < 1) {
                fading += 0.02f;
            }
            elapsedTime = 0;
            
            if (Main.points > 0) {
                Main.points -= 10;
            }
        }

        for (GraphicEntity ge : entities) {
            if (ge instanceof Player) {
                ge.update(container, game, delta);
                continue;
            }
            if (ge.active) {
                ge.update(container, game, delta);

                if (checkCollision(ge, player)) {
                    gameMusic.stop();
                    PokemonFightState pks = (PokemonFightState) game.getState(Main.FIGHTSTATE);
                    pks.setEnemyAndPlayer(player, ge);
                    if (ge instanceof SharkEnemy) {
                        gachunk.play(1, 2);
                    }
                    int rand = (int) (Math.random() * 4);
                    Transition t;
                    if (rand == 0) t = new VerticalSplitTransition();
                    else if (rand == 1) t = new HorizontalSplitTransition();
                    else if (rand == 2) t = new RotateTransition();
                    else t = new BlobbyTransition();
                    game.enterState(Main.FIGHTSTATE, new EmptyTransition(), t);

                    ge.x = (float) Math.random() * 4000;
                    ge.y = (float) Math.random() * 4000;
                    ge.hp = ge.maxHp;
                }
            }
        }

        mapXPosition = (int) player.x - 542;
        mapYPosition = (int) player.y - 426;

        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            gameMusic.stop();
            game.enterState(Main.MAINMENUSTATE);
        }
    }

    private boolean checkCollision(GraphicEntity enemy, Player player) {
        return Math.abs(player.x - enemy.x) < enemy.width / 2.0 && Math.abs(player.y - enemy.y) < enemy.height / 2.0;
    }

}