package uk.ac.qub.eeecs.pranc.kingsofqueens.gage;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.Abilities.Ability;
import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.engine.io.AssetStore;
import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.game.Card;

import static android.content.ContentValues.TAG;

/**
 * Created by markm on 06/02/2017.
 */

public class genAlgorithm {


    public enum field {
        TOP,
        BOTTOM
    }
    public static final String ABILITIES_FILENAME = "uk.ac.qub.eeecs.pranc.kingsofqueens.gage.Abilities.";
    // Based on Knuth's shuffle algorithm
    public static void knuthShuffle(List<Card> array){
        int totalSize = array.size();
        for(int count = 0; count < totalSize; count++){
            int randomPos = count + (int) (Math.random() * (totalSize - count));

            Card swap = array.get(randomPos);
            array.set(randomPos,array.get(count));
            array.set(count, swap);
        }
    }

    public static Ability findAbility(String path){
        try{
            Ability toReturn;
            toReturn = (Ability) Class.forName(path).newInstance();
            return toReturn;
        }catch (InstantiationException e) {
            Log.e(TAG, "InstantiationException: "+ e.getMessage());
            return null;
            } catch (IllegalAccessException e) {
            Log.e(TAG, "IllegalAccessException: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Can not find Class: " + path);
            return null;
        }

    }


    public static Card[] getDeckAsCardArray(AssetStore pAssetStore, String pJsonFileName) {
        JSONArray jsonArray = pAssetStore.getJson(pJsonFileName);
        Card [] deck = new Card[jsonArray.length()];
        try {
            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject object = jsonArray.getJSONObject(index);
                Card currentCard = getCard(object);
                deck[index] = currentCard;
            }
        }catch(JSONException e){
            Log.e(TAG, "findAbility: ");
        }
        return deck;
    }


    public static Card getCard(JSONObject object) throws JSONException {
        int id          = object.getInt     ("_id");
        int ev          = object.getInt     ("ev");
        int attack      = object.getInt     ("attack");
        int defense     = object.getInt     ("defense");
        String ability  = object.getString  (ABILITIES_FILENAME + "ability");
        String imgFile  = object.getString  ("picture");
        String type     = object.getString  ("type");
        boolean inDeck  = object.getBoolean ("inDeck");

        return new Card(id,type,defense,attack,ev,0,inDeck,imgFile,ability);
    }


}
