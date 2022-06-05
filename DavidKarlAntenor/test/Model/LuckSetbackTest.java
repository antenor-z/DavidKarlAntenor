package Model;
import Model.Money;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class OnlyAddValueCardDeck extends Deck {
    public TestDeck() throws DeckException {
        super("./Deck.json");
        this.cards = new ArrayList<ACard>();
        this.cards.add(new ValueCard("desc", "name", 200, "path"));
    }
}

public class OnlySubValueCardDeck extends Deck {
    public TestDeck() throws DeckException {
        super("./Deck.json");
        this.cards = new ArrayList<ACard>();
        this.cards.add(new ValueCard("desc", "name", -200, "path"));
    }
}

public class LuckSetbackTest {
    @Test
    public void AddValueCardTest() {
        ArrayList<Player> players = new ArrayList<Player>();
        Player player = new Player(500, 5, PlayerColor.Blue, "john01")
        players.add(player);

        OnlyAddValueCardDeck addDecj = new OnlyAddValueCardDeck();
        LuckSetback luckSetback = new LuckSetback(players, addDecj);
        luckSetback.pickCard(player);
        assertEquals("LuckSetbackTest: add 200 to player cash (500)", 700, player.getCash());
    }

    @Test
    public void AddValueCardTest() {
        ArrayList<Player> players = new ArrayList<Player>();
        Player player = new Player(500, 5, PlayerColor.Blue, "john01")
        players.add(player);

        OnlySubValueCardDeck subDeck = new OnlySubValueCardDeck();
        LuckSetback luckSetback = new LuckSetback(players, subDeck);
        luckSetback.pickCard(player);
        assertEquals("LuckSetbackTest: sub 200 to player cash (500)", 300, player.getCash());
    }
}