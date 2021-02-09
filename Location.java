import java.util.ArrayList;
import java.util.HashMap;

public class Location {
    private String name;
    private String description;
    private ArrayList<Item> list;
    private HashMap< String, Location> myMap;

    // Constructor 
    
    public Location( String pName, String pDescription){
        name = pName;
        description = pDescription;
        list = new ArrayList<Item>();
        myMap = new HashMap<String,Location>();
    }

    // Accessor methods

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }

    // Mutator methods 
    
    public void setName(String name){
        this.name = name;
    }
    public void setdescription(String description){
        this.description = description;
    }

    public void addItem(Item item){
        list.add(item);
    }

    public boolean hasItem(String name){
        for(int i=0; i< list.size();i++){
            if (list.get(i).getName().equalsIgnoreCase(name) == true){
                return true;
                }
            }
        
        return false;
    }

    public Item getItem(String name){
        for(int i=0; i< list.size();i++){
            if (list.get(i).getName().equalsIgnoreCase(name) == true){
                return list.get(i);
                }
            }
        
        return null;
    }

    public Item getItem(int index){
        if(index >= 0 && index< list.size()){
           return list.get(index);
        }
        else{
            return null;
        }
    }
    
    public int numItems(){
        return list.size();
    }

    public Item removeItem(String name){
        for(int i=0; i< list.size();i++){
            if (list.get(i).getName().equalsIgnoreCase(name) == true){
                Item e = list.get(i);
                list.remove(i);
                return e;
                }
            }
        return null;
    }

    public void connect(String s, Location l){
        myMap.put(s,l);
    }
    
    public boolean canMove(String name){
        if(myMap.containsKey(name.toLowerCase())== true){
            return true;
        }
        else{
            return false;
        }
    }
    
    public Location getLocation(String name){
        return myMap.get(name.toLowerCase());
    }
    

}
