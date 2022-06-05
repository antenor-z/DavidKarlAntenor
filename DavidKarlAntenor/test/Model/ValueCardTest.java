package Model;

import Model.Money;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValueCardTest {

    @Test
    public void getValueTest() {
        ValueCard valueCard = new ValueCard("desc", "name", 250, "path");
        assertEquals("init ValueCard value to 250", 250, valueCard.getValue());
        assertEquals("check ValueCard type", CardType.VALUE, valueCard.getType());
        assertEquals("check ValueCard desc", "desc", valueCard.getDescription());
        assertEquals("check ValueCard name", "name", valueCard.getName());
        assertEquals("check ValueCard path", "path", valueCard.getImage());
    }
}