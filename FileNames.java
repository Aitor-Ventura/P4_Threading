package textprocessing;
import java.util.Queue;
import java.util.LinkedList;
public class FileNames {
    private Queue<String> queue= new LinkedList<String>();
    private String name;
    private boolean variable=false;
    
    
    public synchronized void addName(String fileName) {
        if(!variable){
            queue.add(fileName);
            notifyAll();
        }
    }
  
    public synchronized String getName() {
        name=queue.poll();
        while(name==null){
            if(variable){
                return null;
            }
            try{
                wait();
            }catch(InterruptedException e){}
            name=queue.poll();
        }
        notifyAll();
        return name;
    }
    
    public synchronized void noMoreNames() {
        variable=true;
        notifyAll();
    }
}