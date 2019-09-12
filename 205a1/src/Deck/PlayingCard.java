package Deck;
//Xinan Wang 5535802
/*PlayingCard is a abstract class, all cards extends this class.
In this class, all the element can't return, so getmethod will return the actually value.
It implements comparable sothat I let the collection can be compare by it's rank.
For later work, if it go a card, it can compare by it's rank.
 */
import java.lang.Math.*;
public abstract class PlayingCard implements Comparable<Object>{
    public static int value;
    public int rank;
    public String cardnumber;
    public char cardcolor;
    public String realcard;
    public void getCard(){
    };
    public int getvalue(){
        return this.value;
    }
    public int getrank(){
        return rank;
    }
    public char getcolor(){
        return this.cardcolor;
    }
    public String toString(){
        return realcard;
    }
    public int compareTo(Object o) {
        PlayingCard pc = (PlayingCard) o; 
        return this.getrank() - pc.getrank() ;
    }
}
