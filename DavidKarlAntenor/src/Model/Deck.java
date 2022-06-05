package Model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import Model.Utils.ImportFile;

import org.json.*;
import java.util.Random;

public class Deck {
    protected List<ACard> cards = new ArrayList<ACard>();
    protected Random rand = new Random();
    protected OutOfJailCard outOfJailCard;

    Deck(String jsonPath) throws DeckException {
        this._initialiseCardArray(jsonPath);
    }

    ACard withdraw() {
        int randomPick = rand.nextInt(cards.size());
        ACard pickedCard = cards.get(randomPick);
        
        return pickedCard;
    }
    
    List<ACard> getCards() {
    	return cards;
    }

    void add(ACard card) {
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
        String image = jsonCard.getString("image");

        if (type == null) {
            throw new DeckException();
        }
        switch (type) {
            case VALUE :
            	cards.add(new ValueCard(description, name, value, image));
            	break;
            case GO_TO_JAIL :
            	cards.add(new GoToJailCard(description, name, image));
            	break;
            case OUT_OF_JAIL :
            	outOfJailCard = new OutOfJailCard(description, name, image);
            	cards.add(outOfJailCard); 
            	break;
            case VALUE_PER_PLAYER :
            	cards.add(new ValuePerPlayer(description, name, value, image));
            	break;
            case MOVE :
            	cards.add(new MoveCard(description, name, value, image));
            	break;
        }
    }
    
    public OutOfJailCard getOutOfJailCard() {
    	return outOfJailCard;
    }
}
