package textprocessing;
public class FileReader extends Thread{
    private FileNames fn;
    private FileContents fc;
    private String actual ="";
    public FileReader(FileNames fn, FileContents fc){
        this.fn = fn;
        this.fc = fc;
    }
    
    @Override
    public void run(){
        String aux = "";
        actual = fn.getName();
        fc.registerWriter();
        
        while(actual != null){
            
            aux = Tools.getContents(actual);
            fc.addContents(aux);
            actual = fn.getName();
            
        }
        fc.unregisterWriter();
    }
}
