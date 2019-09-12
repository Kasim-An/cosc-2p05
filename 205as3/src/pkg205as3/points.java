package pkg205as3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class points extends JPanel//draw points is a panel and initial in GUI
{
    int x;
    int y;
    ArrayList path;
    public void display(int x, int y, ArrayList path)
    {
    	this.x=x;
    	this.y=y;
    	this.path=path;
        this.repaint();//initial and redraw the points
    }
    @Override
    public void paint(Graphics g)
    {
        int tempx = 10;//starting potition is (10,10)
        int tempy ;
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        for(int lpy=0;lpy<y;lpy++){
            tempy=10;
            for(int lpx=0;lpx<x;lpx++)
            {
            	g2d.fill3DRect(tempy, tempx, 5, 5, true);
             	tempy=tempy+30;
            }
            tempx+=30;
        }
        if(path.size()>0){
            tempx=12;//drawline from (12,12) at points' middle
            tempy=12;
            for (Object a : path) {
                switch ((int) a) {
                    case 1://up
                        g2d.drawLine(tempx,tempy,tempx,tempy-30);
            		tempy=tempy-30;
            		break;
                    case 2://right
            		g2d.drawLine(tempx,tempy,tempx+30,tempy);
            		tempx=tempx+30;
            		break;
                    case 3://down
            		g2d.drawLine(tempx,tempy,tempx,tempy+30);
            		tempy=tempy+30;
            		break;
                    case 4://left
            		g2d.drawLine(tempx,tempy,tempx-30,tempy);
            		tempx=tempx-30;
            		break;
                    default:
            		break;
                }
            }
        }
    }
}