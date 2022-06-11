package Model;
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.File;


public class GameStateTest {
    @Test
    public void test(){
        String path = new String();
        path = "c:/testGameState";
        File file = new File(path);
        GameState.getInstance().saveGame(path);
        assertTrue("Json file was not generated", file.exists());   
            
        }

}
