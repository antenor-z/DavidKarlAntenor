package Model.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GameLoader {
    public List<GameObject> loadGames() {
        String filePath = "./save.json";
        String fileContentAsString = ImportFile.getFileContent(filePath);
        List<GameObject> gamesList = new ArrayList<GameObject>();

        if (fileContentAsString != null) {
            JSONObject fileContentObj = new JSONObject(fileContentAsString);
            JSONArray gamesArray = fileContentObj.getJSONArray("games");

            for (Object jsonGame: gamesArray) {
                if (jsonGame instanceof JSONObject) {
                    this._addGameToArray((JSONObject) jsonGame, gamesList);
                }
            }
        }
        return gamesList;
    }

    private void _addGameToArray(JSONObject jsonGame, List<GameObject> list) {
        String gameName = jsonGame.getString("name");
        Integer timestamp = jsonGame.getInt("timestamp");

        list.add(new GameObject(gameName, timestamp));
    }
}
