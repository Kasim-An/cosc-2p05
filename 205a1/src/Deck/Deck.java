package Deck;
//Xinan Wang 5535802
/*Build a deck, from H,D,C,S and order from A,2,3......J,Q,K
shuffle into arraylist for random deal
deal is get a single card
draw is get specifically one card
checkreminder is to get size for right now deck
*/
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Deck {
    public ArrayList deck;
    public Ace ace;
    public NumberCard nc;
    public FaceCard fc;
    public String[] colorset={"H","D","C","S"};
    public String[] faceset={"J","Q","K"};
    public Deck(){
        deck=new ArrayList<>();
        String tempstring;
        for(int colorloop=0;colorloop<4;colorloop++){
            for(int i=0;i<14;i++){
                if(i==0){
                    ace=new Ace(colorset[colorloop]);
                    //System.out.print(ace.cardnumber+" "+ace.cardcolor+", ");
                    deck.add(ace);
                }
                else if(i>=2&&i<=10){
                    tempstring="";
                    tempstring=tempstring+i;
                    nc=new NumberCard(tempstring,colorset[colorloop]);
                    //System.out.print(nc.cardnumber+" "+nc.cardcolor+", ");
                    deck.add(nc);
                }
                else{
                    if(i-11>=0){
                        fc=new FaceCard(faceset[i-11],colorset[colorloop]);
                        //System.out.print(fc.cardnumber+" "+fc.cardcolor+", ");
                        deck.add(fc);
                    }
                }
            }//System.out.println();
        }
    }
    public ArrayList shuffle(){
        Collections.shuffle(deck);
        return deck;
    }
    public void draw(PlayingCard card){//draw some card
        for(int i=0;i<deck.size();i++){
            if(card.toString().equals(deck.get(i).toString()))
            {                
                deck.remove(i);
            }
        }
    }
    public PlayingCard deal(){//deal a random card in this deck
        int i=(int)(Math.random()*deck.size());
        PlayingCard card=(PlayingCard)deck.get(i);
        deck.remove(i);
        return card;
    }
    public int checkreminder(){
        return deck.size();
    }
}
