import java.util.ArrayList;

public class ContainerItem extends Item {
    private ArrayList<Item> items;

    public ContainerItem(String pName,String pType,String pDescription){
        super(pName, pType, pDescription);
        items = new ArrayList<Item>();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public boolean hasItem(String name){
        for(int i=0; i< items.size();i++){
            if (items.get(i).getName().equalsIgnoreCase(name) == true){
                return true;
                }
            }
        
        return false;
    }

    public Item removeItem(String name){
        for(int i=0; i< items.size();i++){
            if (items.get(i).getName().equalsIgnoreCase(name) == true){
                Item e = items.get(i);
                items.remove(i);
                return e;
                }
            }
        return null;
    }
    
    @Override
    public String toString(){
         String s = super.toString().concat("\n");
         for(int i=0; i< items.size();i++){
             s+= items.get(i).getName().concat("\n");
         }
         return s;
    }
    
}
