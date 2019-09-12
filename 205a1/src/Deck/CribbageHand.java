package Deck;
//Xinan Wang 5535802
/*CribbageHand implements hand, it acts as crib and player hand
the ability for this class will be add card, remove card, count score, get hand size,
search card and sort card, function will be override from hand interface
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CribbageHand implements Hand{
    public boolean iscrib;
    public ArrayList<PlayingCard> playerhand=new ArrayList<PlayingCard>(1);
    public String toString(){
        String tos="";
        for(int i=0;i<playerhand.size();i++){
            tos=tos+playerhand.get(i).toString()+" ";
        }
        return tos;
    };
    public void addcard(PlayingCard card){
        playerhand.add(card);
        if(playerhand.size()==4){
            iscrib=true;
        }
    };
    public PlayingCard removecard(PlayingCard card){
        playerhand.remove(card);
        if(playerhand.size()==4){
            iscrib=true;
        }
        return card;
    };
    public int countcard(){
        return playerhand.size();
    }
    public int tally(){//count marks
        int totalmark=0;
        
        //for(PlayingCard tempc:playerhand){
        //    totalmark=totalmark+tempc.getvalue();
        //}
        totalmark=totalmark+flushes(playerhand) + nobs(playerhand);
        totalmark=totalmark+pairs(playerhand) + runs(playerhand) + count(playerhand);
        System.out.println("flushes: "+flushes(playerhand)+" nobs: "+nobs(playerhand));
        System.out.println("pairs: "+pairs(playerhand)+" runs: "+runs(playerhand)+" count: "+count(playerhand));
        
        
        return totalmark;
    };
    public int count(ArrayList<PlayingCard> cards) {//end count
        return count(cards,0,0);
    }
    private static int count(ArrayList<PlayingCard>cards,int i,int val) {//find all possible to get 15
        if(i>=cards.size()) 
            return 0;
        int score=0;
        score=score+count(cards,i+1,val);
        val=val+cards.get(i).getvalue();
        if(val==15)
            score=score+2;//each earns 2 mark
        score=score+count(cards,i+1,val);
        return score;
    }
    public int pairs(ArrayList<PlayingCard> tempal){//using hashmap to get pairs, trible or quadruple
        int score=0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(PlayingCard card:tempal)
            map.put(card.getvalue(),map.getOrDefault(card.getvalue(),0)+1);
        for(int key:map.keySet())
            switch(map.get(key)){
                case 2:
                    score=score+2; 
                    break;//earns 2 marks
                case 3:
                    score=score+6; 
                    break;//earns 6 marks or 2x3
                case 4:
                    score=score+12; 
                    break;//earns 12 marks or 2x1x2x3
            }
        return score;
    }
    private static int runs(ArrayList<PlayingCard> cards) {//find runs(sequence)
        int runn=1;
        int best=1;
        for(int i=1;i<cards.size();i++)
            if(cards.get(i).getrank()==cards.get(i-1).getrank()+1){//get more runs
                runn++;
                best=runn>best?runn:best;
            }
            else runn=1;
        return best>2?best:0;//if exist, then run must at least 3
    }
    private static int nobs(ArrayList<PlayingCard> cards) {//find nobs,same color and inhand has "J"
        int i=0;
        for(int j=0; j<cards.size()-1;j++)
            if(cards.get(j).getcolor()==cards.get(j).getcolor()&&cards.get(i).getrank()==11)
                i++;
        return i;
    }
    private static int flushes(ArrayList<PlayingCard> cards) {//find four card in same color, if topcard is same color, value=5
        char type=cards.get(0).getcolor();
        for(int i=1;i<4;i++)
            if(cards.get(i).getcolor()!=type) 
                return 0;
        return cards.get(cards.size()-1).getcolor()==type?5:4;//adding topdeck card in last one,so compare color to last one
    }
    public boolean searchcard(PlayingCard card){//search one card in hand
        for(PlayingCard tempc:playerhand){
            if(tempc.toString().equals(card.toString())){
                return true;
            }
        }
        return false;
    };
    public ArrayList getHand(){//return as arraylist
        return playerhand;
    };
    public ArrayList sortHand(){//use compareable playingcard to compare by value and sort into collection
        Collections.sort(playerhand);
        return playerhand;
    }
    public int getSize(){//same for countcard method based
        return playerhand.size();
    };
}
