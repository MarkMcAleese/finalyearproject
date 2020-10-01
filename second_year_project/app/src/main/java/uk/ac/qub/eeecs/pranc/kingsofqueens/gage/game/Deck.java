package uk.ac.qub.eeecs.pranc.kingsofqueens.gage.game;
import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.ArrayList;


import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.engine.io.AssetStore;
import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.genAlgorithm;
import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.engine.graphics.IGraphics2D;

public class Deck{


    private boolean deckIsEmpty = true;

    public static final int SIZEOFCLASSDECK = 3;
    public static final int SIZEOFNEUTRALDECK = 2;

    public static final String NEUTRAL = "Neutral";
    public static final String DECKPATH = "Decks/";
    public static final String JSON = ".json";

    private Bitmap deckImg;
    private Rect deckRect;
    private ArrayList<Card> playerDeck = new ArrayList<> ();

    public Deck(){
        deckRect = null;
    }

    public Deck(AssetStore assetStore,String pDeck1, String pDeck2){

        loadDecksIntoAssestManger(assetStore,pDeck1, pDeck2);
        setDeckUp(assetStore, pDeck1, pDeck2);
    }
    // Refactor json string joining into a method so it gets called instead of d// doing it over and over again
    public boolean loadDecksIntoAssestManger(AssetStore assetStore, String pDeck1, String pDeck2){
        addJsonToAssetManager(assetStore,NEUTRAL);
        return addJsonToAssetManager(assetStore,pDeck1) && addJsonToAssetManager(assetStore,pDeck2);

    }

    public boolean addJsonToAssetManager(AssetStore assetStore,String deckName) {
        return assetStore.loadAndAddJson(deckName, DECKPATH + deckName + JSON);
    }

    public void shuffle(){
        genAlgorithm.knuthShuffle(playerDeck);
    }

    public void setDeck( Card [] pDeck1,  Card [] pDeck2,  Card [] pNeutralDeck){


        addCardsToDeck(pDeck1      , SIZEOFCLASSDECK);
        addCardsToDeck(pDeck2      , SIZEOFCLASSDECK);
        addCardsToDeck(pNeutralDeck, SIZEOFNEUTRALDECK);

        deckIsEmpty = playerDeck.isEmpty();

        if(!deckIsEmpty)
            shuffle();
    }

    private void addCardsToDeck(Card[] pDeck1, int pTimesAdded) {
        for (Card s : pDeck1) {
            if (s.inDeck) {
                for (int i = 0; i < pTimesAdded; i++) {
                    playerDeck.add(s);
                }
            }
        }
    }


    public Card [] drawFromDeck(int draws){
        Card [] hand = new Card[draws];
        for(int i = 0; i < draws; i++){
            if(playerDeck.isEmpty()){
                deckIsEmpty = true;
                return hand;
            }
            hand[i] = playerDeck.get(0);
            playerDeck.remove(0);
        }
        return hand;
    }
    public boolean setDeckUp(AssetStore assetStore, String pDeckName1, String pDeckName2){
        Card [] deck1 = jsonToCardCollection(assetStore, pDeckName1);
        Card [] deck2 = jsonToCardCollection(assetStore, pDeckName2);
        Card [] neutralDeck = jsonToCardCollection(assetStore, NEUTRAL);

        setDeck(deck1,deck2,neutralDeck);
        return deckIsEmpty;
    }

    public Card[] jsonToCardCollection(AssetStore pAssetStore, String pJsonFileName){
        Card[] deck = genAlgorithm.getDeckAsCardArray(pAssetStore, pJsonFileName);
        return deck;
    }



    public int getSize() {
        return playerDeck.size();
    }

    public void drawDeck(genAlgorithm.field side, IGraphics2D iGraphics2D) {
    float top;
        float bot;
        float leftSide;

        int left;
        int right;
        int topI;
        int botI;

        if (deckRect == null) {
            if (side == genAlgorithm.field.TOP) {
                top = 0;
                bot = iGraphics2D.getSurfaceHeight();

                leftSide = 0;
                left = (int) leftSide;
                right = (int) leftSide + 100;
                topI = (int) top;
                botI = (int) ((bot) - (bot / 1.5)) - 75;
                deckRect = new Rect(left, topI, right, botI);

            } else {
                top = iGraphics2D.getSurfaceHeight() / 2;
                bot = iGraphics2D.getSurfaceHeight();

                leftSide = 0;
                left = (int) leftSide;
                right = (int) leftSide + 100;
                topI = (int) ((top) + (top / 4) + 105);
                botI = (int) bot;
                deckRect = new Rect(left, topI, right, botI);
            }
        }

        iGraphics2D.drawBitmap(deckImg,null,deckRect,null);
    }

    public boolean isDeckIsEmpty() {
        return deckIsEmpty;
    }

    public Bitmap getDeckImg() {
        return deckImg;
    }

    public void setDeckImg(Bitmap deckImg) {
        this.deckImg = deckImg;
    }
}


