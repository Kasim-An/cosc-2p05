package pkg205as3;

import java.util.ArrayList;
import java.util.concurrent.Callable;
//https://www.geeksforgeeks.org/thread-pools-java/
public class SAW implements Callable<ArrayList>//set callable or runnable to be threaded
{
    private final int x;
    private final int y;
    public SAW(int x,int y){
        this.x=x;
        this.y=y;
    }
    private int[] getPath(int x, int y){
        int length = x*y-1;//maximum for 2*2 is 2*2-1, for 4*4 is 15 travel all points
        int[] path = new int[length];
        for(int i=0;i<path.length;i++){
            path[i] = (int) (Math.random() * 4)+1;//replace NSEW for 1-4 random number
        }
        return path;
    }
    private ArrayList SAW(int x, int y)
    {
    	boolean[][] map = new boolean[x][y];
    	for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
    		map[i][j]=false;
            }
    	}
    	ArrayList optimal = new ArrayList();
    	int[] path = getPath(x,y);
    	int tempx=0;
    	int tempy=y-1;
    	for (int aPath : path) {
            map[tempx][tempy] = true;
            switch (aPath) {
            	case 1://1 is up
                    if (tempy == y - 1) {
                        return optimal;
                    }//outside
                    if (map[tempx][tempy + 1]) {
                        return optimal;
                    }//visited
                    optimal.add(1);
                    tempy=tempy+1;
                    break;
                case 2://2 is right
                    if (tempx == x - 1) {
                        return optimal;
                    }//outside
                    if (map[tempx + 1][tempy]) {
			return optimal;
                    }//visited
                    tempx=tempx+1;
                    optimal.add(2);
                    break;
        	case 3://3 is down
                    if (tempy == 0) {
                        return optimal;
                    }//outside
                    if (map[tempx][tempy - 1]) {
                        return optimal;
                    }//visited
                    optimal.add(3);
                    tempy=tempy-1;
                    break;
    		case 4://4 is left
                    if (tempx == 0) {
    			return optimal;
                    }//outside
                    if (map[tempx - 1][tempy]) {
			return optimal;
                    }//visited
                    tempx=tempx-1;
                    optimal.add(4);
                    break;
		default:
                    break;
		}
            }
            return optimal;
	}
    @Override
    public ArrayList call() {
        int optimal = 0;
        ArrayList result = new ArrayList();
        for(int i=0;i<100;i++){//calculate 100 times and get optimal one, outside doing thread
            ArrayList path = SAW(x,y);
            if(optimal<=path.size()){
                result = path;
                optimal=result.size();
            }
        }
    return result;
    }
}
