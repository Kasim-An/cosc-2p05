package Deck;
//Xinan Wang 5535802
/*Ace extend everythhing from playingcard, it has it's own value and method.
number for Ace will be 1 for its rank and value.
*/
import java.lang.Math.*;
public class Ace extends PlayingCard implements Comparable<Object>{
    public static int value=1;
    public int rank=1;
    public static String cardnumber="A";
    public char[] totalcolor={'H','D','C','S'};
    public char cardcolor;
    public String realcard=null;
    public Ace(String tempcolor){//initial ace card
        this.cardcolor=tempcolor.charAt(0);
        this.value=1;
        this.rank=1;
    }
    public void getCard(){
        int temprandom2=(int)(Math.random()*4);
        cardcolor=totalcolor[temprandom2];
        realcard=cardnumber+cardcolor;
    }
    public int getvalue(){
        return this.value;
    }
    public int getrank(){
        return this.rank;
    }
    public char getcolor(){
        return this.cardcolor;
    }
    public String toString(){
        realcard="A"+cardcolor;
        return realcard;
    }
    @Override
    public int compareTo(Object o) {
        PlayingCard pc = (PlayingCard) o; 
        return this.getrank() - pc.getrank() ;
    }
}
