import maingame.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

public class HelloWorld extends BasicGame {

    TiledMap map;
    Player player = new Player();

    public HelloWorld() {
        super("Glowing Octo Nemesis");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        player = new Player();
        player.init();
        map = new TiledMap("assets/tilemap.tmx");
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();
        player.update(input, delta);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        map.render(0, 0);
        player.render();
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new HelloWorld());

        app.setDisplayMode(800, 600, false);
        app.start();
    }
}
