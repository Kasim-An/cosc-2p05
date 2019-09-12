package Deck;
//Xinan Wang 5535802
/*Main function will follow by this order
Scanner for input,do while to make at least game run one times
starting for draw how first, for later go card and crib counts
random adding cards to crib, player1 and player2
show top deck card value
show all cards
calculate score
check if player want to continue by enter "true"
*/
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner sc;
        do{
            System.out.println();
            System.out.println("Game Start");
            
        Deck d=new Deck();
        CribbageHand playerhand1=new CribbageHand();
        CribbageHand playerhand2=new CribbageHand();
        CribbageHand realcrib=new CribbageHand();
        int whofirst=-1;//use later for go card
        System.out.println("Draw starting card");
        PlayingCard starting1=d.deal();
        System.out.println("Player 1 draw "+starting1.toString());
        PlayingCard starting2=d.deal();
        System.out.println("Player 2 draw "+starting2.toString());
        if(starting1.getrank()>=starting2.getrank()){
            System.out.println("Player 1 first");
            whofirst=1;
        }
        else{
            System.out.println("Player 2 first");
            whofirst=2;
        }
        for(int i=0;i<4;i++){
            PlayingCard c=d.deal();
            realcrib.addcard(c);
            PlayingCard c1=d.deal();
            playerhand1.addcard(c1);
            PlayingCard c2=d.deal();
            playerhand2.addcard(c2);
        }
        PlayingCard topdeck=d.deal();
        System.out.println("Top deck card is "+topdeck.toString());
        playerhand1.addcard(topdeck);
        playerhand2.addcard(topdeck);
        System.out.println();
        System.out.println("crib hand is ");
        for(int j=0;j<realcrib.countcard();j++){
            System.out.print(realcrib.sortHand().get(j).toString()+" ");
        }
        System.out.println("total value for crib is "+realcrib.tally());
        System.out.println("1st hand is ");
        for(int j=0;j<playerhand1.countcard();j++){
            System.out.print(playerhand1.sortHand().get(j).toString()+" ");
        }
        System.out.println("total value for player1 is "+playerhand1.tally());
        System.out.println("2nd hand is ");
        for(int j=0;j<playerhand2.countcard();j++){
            System.out.print(playerhand2.sortHand().get(j).toString()+" ");
        }
        System.out.println("total value for player2 is "+playerhand2.tally());
        System.out.println();
        System.out.println("Reply true to continue, replay any other will end this game");
        sc=new Scanner(System.in);
        }while(sc.next().equals("true"));
        /*CribbageHand temphand=new CribbageHand();
        System.out.println("Random 4 cards");
        for(int i=0;i<4;i++){
            PlayingCard c=d.deal();
            System.out.print(c.toString()+" ");
            temphand.addcard(c);
            //d.shuffle();
        }
        System.out.println();
        NumberCard tempnum=new NumberCard("2","H");
        temphand.addcard(tempnum);
        System.out.println("checking add card "+tempnum.toString());
        for(int x=0;x<temphand.countcard();x++){
            System.out.print(temphand.playerhand.get(x)+" ");
        }
        System.out.println();
        System.out.println("Current value");
        for(int x=0;x<temphand.countcard();x++){
            System.out.print(temphand.playerhand.get(x).getvalue()+" ");
        }
        System.out.println();
        System.out.println("Search card "+tempnum.toString());
        boolean temppp=temphand.searchcard(new NumberCard("2","H"));
        System.out.println(temppp);
        System.out.println("Remove card "+tempnum.toString());
        temphand.removecard(tempnum);
        for(int x=0;x<temphand.countcard();x++){
            System.out.print(temphand.playerhand.get(x)+" ");
        }
        System.out.println();
        System.out.println("Current value");
        for(int x=0;x<temphand.countcard();x++){
            System.out.print(temphand.playerhand.get(x).getvalue()+" ");
        }
        System.out.println();
        System.out.println("none sort hand "+temphand.toString());
        ArrayList newa=temphand.sortHand();
        System.out.print("Sorted hand will be ");
        for(int i=0;i<newa.size();i++){
            System.out.print(" "+newa.get(i)+" ");
        }
        System.out.println();
        System.out.println("Hand value "+temphand.tally());*/
    }
}
