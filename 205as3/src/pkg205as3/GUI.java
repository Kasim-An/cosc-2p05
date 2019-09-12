package pkg205as3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GUI
{
    JPanel board = new JPanel();
    JPanel points = new JPanel();
    private final JButton SAW = new JButton("SAW");
    
    public void setWindow(){
        points.setSize(600,400);
        points.setBounds(0,0,500,300);//upper is show points
        board.setSize(600,400);
        board.setBounds(0,300,500,200);//lower is control board
        board.setLayout(null);
        final ArrayList[] path = new ArrayList[1];
        final int[] x={2};//initial x as array and final, so that later can be edit
        final int[] y={2};
        boolean[] answer={false};
        JFrame frame=new JFrame("Self Avoiding Walks");
        points po=new points();
        frame.setMinimumSize(new Dimension(600,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton increasex = new JButton("X+");
        increasex.setBounds(220, 40, 60, 40);
        increasex.addActionListener((ActionEvent e) -> {
            if((x[0])<16){//limit x and y, x is 2-16,y is 2-10
                x[0]=x[0]+1;//in here initial array and final so it can be display
                po.display(x[0],y[0],new ArrayList());//problem
                answer[0]=false;
                SAW.setVisible(true);
            }
        });
        board.add(increasex);
        //finish x+ button
        JButton minusx = new JButton("X-");
        minusx.setBounds(100, 40, 60, 40);
        minusx.addActionListener((ActionEvent e) ->{
            if(x[0]!=2){
                x[0]=x[0]-1;
                po.display(x[0],y[0],new ArrayList());
                answer[0] =false;
                SAW.setVisible(true);
            }
        });
        board.add(minusx);
        //finish x- button
        JButton increasey = new JButton("Y+");
        increasey.setBounds(160, 70, 60, 40);
        increasey.addActionListener((ActionEvent e) ->{
            if (y[0]<10){
                y[0]=y[0]+1;
                po.display(x[0],y[0],new ArrayList());
                answer[0] =false;
                SAW.setVisible(true);
            }
        });
        board.add(increasey);
        //finish y+ button
        JButton minusy = new JButton("Y-");
        minusy.setBounds(160, 0, 60, 40);
        minusy.addActionListener((ActionEvent e) ->{
            if(y[0]!=2){
                y[0]=y[0]-1;
                po.display(x[0],y[0],new ArrayList());
                answer[0] =false;
            }
        });
        board.add(minusy);
        //finish y- button
        SAW.setBounds(0,0,100,30);
        SAW.addActionListener((ActionEvent e) ->//do calculation
        {
            tpool pool = new tpool();//thread pool
            ArrayList temp= pool.dispatch(x[0],y[0]);
            if(answer[0]){//if has solution
                int counter = 0;
                while(path[0].size()>=temp.size() && counter<10){
                    temp= pool.dispatch(x[0],y[0]);
                    counter=counter+1;
                }//get global best
                if(path[0].size()<temp.size()){
                    path[0] = temp;
                }
            }
            else{
                path[0]=temp;
            }
            answer[0]=true;
            po.display(x[0],y[0],path[0]);
            display(path[0]);
        });
        board.add(SAW);
        //finish SAW
        JButton reset = new JButton("Reset");
        reset.setBounds(0,70,100,30);
        reset.addActionListener((ActionEvent e) -> {
            path[0] = new ArrayList();
            po.display(x[0],y[0],new ArrayList());
        });
        board.add(reset);
        //finished reset button
        JButton check = new JButton("Check current length");
        check.setBounds(0,120,200,30);
        check.addActionListener((ActionEvent e) -> {
            try{
                JOptionPane.showMessageDialog(null, "Length is "+path[0].size(),"Current", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception ab){//do nothing for nothing start yet
            }
        });
        board.add(check);
        po.setBounds(0,0,500,300);
        points.setLayout(null);
        points.add(po);
        frame.add(points);
        frame.add(board);
        frame.setLayout(null);
        frame.setVisible(true);
        po.display(x[0],y[0],new ArrayList());
    }
    private void display(ArrayList path){
        System.out.println("This turn best run is:"+path.size());
    }
}
