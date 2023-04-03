// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;




import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;
    
    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor
    
    // add a record to the list
   public String addEntry(Entry e){
       ListIterator<Entry> iter = tr.listIterator();
       while (iter.hasNext()) {
           Entry current = iter.next();
           if (current.getDay()==e.getDay() && current.getMonth()==e.getMonth() && current.getYear()==e.getYear() && current.getName().equals(e.getName())){
               return "Entry already present";
       }
       }
       tr.add(e);
       return "Record added";
   } // addClass
   
   // look up the entry of a given day and month
   public String lookupEntry (int d, int m, int y) {
       ListIterator<Entry> iter = tr.listIterator();
       String result = "No entries found";
       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) 
             result = current.getEntry();
            }
       return result;
   } // lookupEntry

    public String delete(String name,int d, int m, int y){
        for(int i=0;i<tr.size();i++){
            if(tr.get(i).getDay()==d && tr.get(i).getMonth()==m && tr.get(i).getYear()==y &&tr.get(i).getName().equals(name)){
                tr.remove(i);
                return "Entry Successfully deleted";
            }
        }
        return "Entry not found";

    }

    public String findAllByDate(int d,int m, int y){
        ListIterator<Entry> iter = tr.listIterator();
        String result = "";
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getDay()==d && current.getMonth()==m && current.getYear()==y)
                result +=("\n"+current.getEntry());
        }
        if (result.equals("")){
            result="No entries found";
        }
        return result;
    }

   
   // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }
   
} // TrainingRecord