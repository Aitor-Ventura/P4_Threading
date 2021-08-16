package textprocessing;
import java.util.Map;
import java.util.HashMap;


public class FileProcessor extends Thread{
    private FileContents fc;
    private WordFrequencies wf;
    private String fil = "[^aeiouAEIOUáéíóúÁÉÍÓÚñÑÜü\\w]+";
    private Map <String,Integer> map = new HashMap <String,Integer>();

    public FileProcessor(FileContents fc, WordFrequencies wf){
        this.fc=fc;
        this.wf=wf;
    }
    
    @Override
    public void run (){
        String [] aux;
        String txt = fc.getContents();
       
        while (txt!=null){
            aux = txt.split(fil);
            
            for(int j=0; j<aux.length; j++){
                if(map.containsKey(aux[j])){
                    map.put(aux[j],map.get(aux[j])+1);
                    
                }else{
                    map.put(aux[j],1);
                }
            }
            txt=fc.getContents();
        } 
        wf.addFrequencies(map);
    }
    
}
