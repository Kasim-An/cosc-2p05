package Deck;
//Xinan Wang 5535802
/*NumberCard extend everythhing from playingcard, it has it's own value and method.
number for NumberCard will be it's own rank and value.
*/
import java.lang.Math.*;
public class NumberCard extends PlayingCard implements Comparable<Object>{
    public int value;
    public int rank;
    public String cardnumber;
    public char[] totalcolor={'H','D','C','S'};
    public char cardcolor;
    public String realcard=null;
    public NumberCard(String tempnumber,String tempcolor){//initial card
        this.cardnumber=tempnumber;
        this.cardcolor=tempcolor.charAt(0);
        value=Integer.parseInt(cardnumber);
        rank=value;
    }
    public void getCard(){//get 2 to 10
        int temprandom=(int)(Math.random()*9+2);
        rank=temprandom;
        int temprandom2=(int)(Math.random()*4);
        cardcolor=totalcolor[temprandom2];
        value=rank;
        cardnumber=Integer.toString(rank);
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
        realcard=""+cardnumber+cardcolor;
        return realcard;
    }
    @Override
    public int compareTo(Object o) {
        PlayingCard pc = (PlayingCard) o; 
        return this.getrank() - pc.getrank() ;
    }
}
