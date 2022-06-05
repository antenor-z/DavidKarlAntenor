package Model;
import Model.Money;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class LuckSetbackTest {
    @Test
    public void AddValueCardTest() throws DeckException, PlayerException {
        ArrayList<Player> players = new ArrayList<Player>();
        Player player = new Player(500, 5, PlayerColor.Blue, "john01");
        players.add(player);

        OnlyAddValueCardDeck addDecj = new OnlyAddValueCardDeck();
        LuckSetback luckSetback = new LuckSetback(players, addDecj);
        luckSetback.pickCard(player);
        assertEquals("LuckSetbackTest: add 200 to player cash (500)", 700, player.getCash());
    }

    @Test
    public void SubValueCardTest() throws DeckException, PlayerException{
        ArrayList<Player> players = new ArrayList<Player>();
        Player player = new Player(500, 5, PlayerColor.Blue, "john01");
        players.add(player);

        OnlySubValueCardDeck subDeck = new OnlySubValueCardDeck();
        LuckSetback luckSetback = new LuckSetback(players, subDeck);
        luckSetback.pickCard(player);
        assertEquals("LuckSetbackTest: sub 200 to player cash (500)", 300, player.getCash());
    }

    @Test
    public void MoveCardTest() {
    }
}