package Deck;
//Xinan Wang 5535802
/*
Only interface for hand, use for later
*/
import java.util.ArrayList;

public interface Hand {
    public String toString();
    public void addcard(PlayingCard card);//take card from draw
    public PlayingCard removecard(PlayingCard card);
    public int countcard();//count how many cards in hand
    public int tally();//count points
    public boolean searchcard(PlayingCard card);//search single card
    public ArrayList getHand();
    public int getSize();
}
