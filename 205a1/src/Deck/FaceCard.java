package Deck;
//Xinan Wang 5535802
/*PlayingCard extend everythhing from playingcard, it has it's own value and method.
number for PlayingCard will be it's own rank, all the value for facecard will be 10
*/
import java.lang.Math.*;
public class FaceCard extends PlayingCard implements Comparable<Object>{
    public static int value=10;//all value to 10
    public int rank;
    public String cardnumber;
    public char[] totalcolor={'H','D','C','S'};
    public char cardcolor;
    public String realcard=null;
    public FaceCard(String tempnumber,String tempcolor){//initial face card and it's rank
        this.cardnumber=tempnumber;
        this.cardcolor=tempcolor.charAt(0);
        this.value=10;//all value to 10
        if(tempnumber=="J"){
            this.rank=11;
        }
        if(tempnumber=="Q"){
            this.rank=12;
        }if(tempnumber=="K"){
            this.rank=13;
        }
    }
    public void getCard(){//get 11 to 13
        int temprandom=(int)(Math.random()*3+11);
        rank=temprandom;
        int temprandom2=(int)(Math.random()*4);
        cardcolor=totalcolor[temprandom2];
        if(rank==11){
            cardnumber="J";
        }
        if(rank==12){
            cardnumber="Q";
        }
        else
            cardnumber="K";
        realcard=cardnumber+cardcolor;
        System.out.println(realcard);
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
