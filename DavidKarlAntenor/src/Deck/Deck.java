package Deck;

import java.util.ArrayList;
import java.util.List;

import Deck.Card.*;
import Deck.Exception.DeckException;
import org.json.*;

import Utils.Files;
import java.util.Random;

public class Deck {
    private final List<ICard> cards = new ArrayList<ICard>();
    private final Random rand = new Random();

    public Deck() throws DeckException {
        this._initialiseCardArray("./cards.json");
    }

    // PUBLIC METHODS

    public ICard withdraw() {
        int randomPick = rand.nextInt(cards.size());
        ICard pickedCard = cards.get(randomPick);

        cards.remove(pickedCard);
        if (pickedCard == null) {
            System.out.println("CARD IS DEFINITELY NULL ASSHOLE");
        }
        return pickedCard;
    }

    public void add(ICard card) {
        if (card != null) {
            cards.add(card);
        }
    }

    // PRIVATE METHODS

    private void _initialiseCardArray(String pathToJson) throws DeckException {
        String jsonString = Files.getFileContent(pathToJson);

        if (jsonString != null) {
            try {
                JSONObject obj = new JSONObject(jsonString);
                JSONArray jsonCards = obj.getJSONArray("cards");

                for (Object jsonCard: jsonCards) {
                    if (jsonCard instanceof JSONObject) {
                        this.addCardFromJSON((JSONObject) jsonCard);
                    }
                }
            } catch (Exception e) {
                throw new DeckException("Fail to create deck from json file: " + pathToJson);
            }
        }
    }

    private void addCardFromJSON(JSONObject jsonCard) throws DeckException {
        CardType type = CardType.fromInteger(jsonCard.getInt("type"));
        String description = jsonCard.getString("description");
        String name = jsonCard.getString("name");
        Integer value = jsonCard.getInt("value");

        if (type == null) {
            throw new DeckException();
        }
        switch (type) {
            case VALUE -> cards.add(new ValueCard(description, name, value));
            case GO_TO_JAIL -> cards.add(new GoToJailCard(description, name));
            case OUT_OF_JAIL -> cards.add(new OutOfJailCard(description, name));
        }
    }
}
