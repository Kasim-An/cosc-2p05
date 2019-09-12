package pkg205as3;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
//https://www.geeksforgeeks.org/thread-pools-java/
//https://www.geeksforgeeks.org/future-and-futuretask-in-java/
public class tpool
{
    private ExecutorService executor;
    public ArrayList dispatch(int x,int y)
    {
    	SAW a = new SAW(x,y);
    	SAW b = new SAW(x,y);
    	SAW c = new SAW(x,y);
    	SAW d = new SAW(x,y);
    
        FutureTask<ArrayList> result1 = new FutureTask<>(a);
    	FutureTask<ArrayList> result2 = new FutureTask<>(b);
    	FutureTask<ArrayList> result3 = new FutureTask<>(c);
    	FutureTask<ArrayList> result4 = new FutureTask<>(d);
    
        executor = Executors.newFixedThreadPool(4);
    	executor.execute(result1);
    	executor.execute(result2);
    	executor.execute(result3);
    	executor.execute(result4);
        
	try{
            ArrayList answer = result1.get();
            ArrayList temp = result2.get();//try conpare two and get best
            if (temp.size()>=answer.size()){
            	answer=temp;
            }
            temp = result3.get();
            if (temp.size()>=answer.size()){
                answer=temp;
            }
            temp = result4.get();
            if (temp.size()>=answer.size()){
                answer=temp;
            }
            executor.shutdown();//finish thread pool and return best
            return answer;
	}
	catch (InterruptedException | ExecutionException e){
	}
        return null;
    }
}
