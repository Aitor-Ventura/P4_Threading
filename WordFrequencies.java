package textprocessing;
import java.util.Map;
import java.util.HashMap;

public class WordFrequencies {
    private Map<String,Integer> wordFrequenciesMap = new HashMap<String, Integer>(); 
    
    public synchronized void addFrequencies(Map<String,Integer> f){
        for(Map.Entry<String,Integer> e: f.entrySet()){
            Integer frecuencie = e.getValue();
            String pal = e.getKey();
            Integer aux = wordFrequenciesMap.get(pal);
            
            if(wordFrequenciesMap.containsKey(pal)){
                wordFrequenciesMap.put(pal, aux+frecuencie);
            } else{
                wordFrequenciesMap.put(pal, frecuencie);
            }
        }
    }
    public Map<String,Integer> getFrequencies(){
        return this.wordFrequenciesMap;
    }
}
