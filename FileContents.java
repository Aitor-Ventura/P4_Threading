package textprocessing;
import java.util.Queue;
import java.util.LinkedList;

public class FileContents {
    private Queue<String> queue = new LinkedList<String>();
    private int maxChars, maxFiles;
    private int chars = 0;
    private int registerCount = 0;
    private boolean closed = false;
    
    public FileContents(int maxFiles, int maxChars) {
        this.maxFiles = maxFiles;
        this.maxChars = maxChars;
    }
    public synchronized void registerWriter() {
        this.registerCount++;
    }
    public synchronized void unregisterWriter() {
        this.registerCount--;
        if(this.registerCount==0){
            closed=true;
            notifyAll();
        }
    }
    public synchronized void addContents(String contents) {
        int n = chars+contents.length();
        while((n > maxChars || queue.size() > maxFiles) && (queue.size() != 0)){
            try{
                wait();
            }catch(InterruptedException e){}
        }
        chars+= contents.length();
        queue.add(contents);
        notifyAll();
    }
    public synchronized String getContents() {
        String res = queue.poll();
        while(res==null){
            if(closed){
                return null;
            }
            try{
                wait();
            }catch(InterruptedException e){}
            res = queue.poll();
        }
        chars -= res.length();
        notifyAll();
        return res;
    }
    
}
