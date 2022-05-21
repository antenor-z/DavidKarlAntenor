package Model.Deck;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import Model.Deck.Card.*;
import Model.Deck.Exception.DeckException;
import Model.Utils.ImportFile;

import org.json.*;
import java.util.Random;

public class Deck {
    private final List<ICard> cards = new ArrayList<ICard>();
    private final Random rand = new Random();
    private OutOfJailCard outOfJailCard;

    public Deck(String jsonPath) throws DeckException {
        this._initialiseCardArray(jsonPath);
    }

    // PUBLIC METHODS

    public ICard withdraw() {
        int randomPick = rand.nextInt(cards.size());
        ICard pickedCard = cards.get(randomPick);
        
        return pickedCard;
    }

    public void add(ICard card) {
        if (card != null) {
            cards.add(card);
        }
    }

    public Integer size() {
        return cards.size();
    }

    // PRIVATE METHODS

    private void _initialiseCardArray(String pathToJson) throws DeckException {
    	String jsonString = ImportFile.getFileContent(pathToJson);
    	if(jsonString != null)
    	{
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
            case VALUE : cards.add(new ValueCard(description, name, value));
            case GO_TO_JAIL : cards.add(new GoToJailCard(description, name));
            case OUT_OF_JAIL :
            	outOfJailCard = new OutOfJailCard(description, name);
            	cards.add(outOfJailCard); 
            case VALUE_PER_PLAYER : cards.add(new ValuePerPlayer(description, name, value));
            case MOVE : cards.add(new MoveCard(description, name, value));
        }
    }
    
    public OutOfJailCard getOutOfJailCard() {
    	return outOfJailCard;
    }
}
