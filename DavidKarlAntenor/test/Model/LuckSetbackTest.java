package Model;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class LuckSetbackTest {
    @Test
    public void AddValueCardTest() throws DeckException, PlayerException {
        ArrayList<Player> players = new ArrayList<Player>();
        Player player = new Player(500, PlayerColor.Blue, "john01");
        players.add(player);

        OnlyAddValueCardDeck addDecj = new OnlyAddValueCardDeck();
        LuckSetback luckSetback = new LuckSetback(players, addDecj);
        luckSetback.pickCard(player);
        assertEquals("LuckSetbackTest: add 200 to player cash (500)", 700, player.getCash());
    }

    @Test
    public void SubValueCardTest() throws DeckException, PlayerException{
        ArrayList<Player> players = new ArrayList<Player>();
        Player player = new Player(500, PlayerColor.Blue, "john01");
        players.add(player);

        OnlySubValueCardDeck subDeck = new OnlySubValueCardDeck();
        LuckSetback luckSetback = new LuckSetback(players, subDeck);
        luckSetback.pickCard(player);
        assertEquals("LuckSetbackTest: sub 200 to player cash (500)", 300, player.getCash());
    }

    @Test
    public void MoveCardTest() throws DeckException, PlayerException {
        ArrayList<Player> players = new ArrayList<Player>();
        Player player = new Player(500, PlayerColor.Blue, "john01");
        players.add(player);

        OnlyMoveCardDeck moveDeck = new OnlyMoveCardDeck();
        LuckSetback luckSetback = new LuckSetback(players, moveDeck);
        luckSetback.pickCard(player);
        assertEquals("LuckSetbackTest: move player to till 0", 0, player.getTileNumber());
        assertEquals("LuckSetbackTest: add 200 to player", 700, player.getCash());
    }

    @Test
    public void ValuePerPlayerCardtest() throws DeckException, PlayerException {
        ArrayList<Player> players = new ArrayList<Player>();
        Player player01 = new Player(500, PlayerColor.Blue, "john01");
        Player player02 = new Player(500, PlayerColor.Blue, "john02");
        players.add(player01);
        players.add(player02);

        OnlyValuePerPlayerDeck moveDeck = new OnlyValuePerPlayerDeck();
        LuckSetback luckSetback = new LuckSetback(players, moveDeck);
        luckSetback.pickCard(player01);
        assertEquals("LuckSetbackTest: move player to till 0", 0, player01.getTileNumber());
        assertEquals("LuckSetbackTest: remove 50 to player02", 450, player02.getCash());
        assertEquals("LuckSetbackTest: add 50 per players to player01", 550, player01.getCash());
    }

    @Test
    public void GoToJailCardTest() throws DeckException, PlayerException {
        ArrayList<Player> players = new ArrayList<Player>();
        Player player01 = new Player(500, PlayerColor.Blue, "john01");
        Player player02 = new Player(500, PlayerColor.Orange, "john02");
        players.add(player01);
        players.add(player02);

        OnlyGoToJailCardDeck goToJailCardDeck = new OnlyGoToJailCardDeck();
        LuckSetback luckSetback = new LuckSetback(players, goToJailCardDeck);
        luckSetback.pickCard(player01);
        assertEquals("LuckSetbackTest: move player to till 10", 10, player01.getTileNumber());

        JailDeck jailDeck = new JailDeck();
        LuckSetback luckSetback2 = new LuckSetback(players, jailDeck);
        luckSetback2.pickCard(player02);
        luckSetback2.pickCard(player02);
        assertEquals("LuckSetbackTest: do not move player to till 10", 0, player01.getTileNumber());
        OutOfJailCard outOfJailCard = jailDeck.getGoToJailCard();
        //assertEquals("LuckSetbackTest: owner now is null", null, outOfJailCard.getOwner());
    }
}