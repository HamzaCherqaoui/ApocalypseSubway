public class Item{
   private String name;
   private String type;
   private String description;

   //Constructor 

   public Item( String pName,String pType,String pDescription){
    name = pName;
    type = pType;
    description = pDescription;
   }
   //Accessor methods

   public String getName(){
       return name;
   }
   public String getType(){
    return type;
   }
    public String getDescription(){
    return description;
    }
    
    //Mutator methods

    public void setName(String name){
        this.name = name;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String toString(){
        return getName()+" ["+getType()+"] : "+getDescription();
    }

}