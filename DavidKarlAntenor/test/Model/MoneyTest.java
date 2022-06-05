package Model;
import Model.Money;
import static org.junit.Assert.*;

import org.junit.Test;

public class MoneyTest {
    @Test
    public void initMoneyTest() {
        Money money = new Money(200);
        assertEquals("init money to 200", 200, money.getAmmount());
        Money money2 = new Money(-200);
        assertEquals("init money to -200", -200, money.getAmmount(money2));
    }

    public void executeMoneyOnPlayerTest() {
        Player player = new Player(1000, 0, PlayerColor.Blue, "test");
        Money moneyAdd = new Money(500);
        Money moneySub = new Money(-500);

        moneyAdd.execute(player);
        assertEquals("add 500 to player money", 1500, player.getCash());
        moneySub.execute(player);
        assertEquals("subtract 500 to player money", 1000, player.getCash());
    }
}