//import java.util.ArrayList;
import java.util.Scanner;
public class Driver {
    private static Location currLocation;
    public static ContainerItem myInventory;
    public static ContainerItem c1;
    public static ContainerItem c2;

    public static void createWorld(){
        Location pennStation = new Location("Penn Station","The station is dark, there are back up generators somwhere");
        Location jamaicaStation = new Location("Jamaica Station","The tunnels in this station have collapsed, it is pitch black");
        Location queensStation = new Location("Queens Plaza","On the under side of queens, it is not that friendly down here. Watch out!");
        Location brklynStation = new Location("Bedford Av"," There are no rats here, maybe they have evolved");

        pennStation.connect("south", brklynStation);
        brklynStation.connect("north", pennStation);
        pennStation.connect("east", queensStation);
        queensStation.connect("west", pennStation);
        queensStation.connect("south", jamaicaStation);
        jamaicaStation.connect("north", queensStation);
        jamaicaStation.connect("west", brklynStation);
        brklynStation.connect("east", jamaicaStation);

        Item m1 = new Item("Matches", "Tool", "You can use these to light a fire.");
        Item m2 = new Item("Water", "Food", "A half-full bottle of water.");
        Item m3 = new Item("Axe", "Weapon", "Old metal baseball bat, might come in handy!");
        Item m4 = new Item("Rope", "Tool", "30 Feet of green sturdy rope.");
        Item m5 = new Item("Candy", "Food", "Snickers candy bar, expires really soon.");
        Item m6 = new Item("Bandages", "Tool" ,"A pack of 5 bandages to cover your wounds.");

        pennStation.addItem(m1);
        pennStation.addItem(m4);
        brklynStation.addItem(m6);
        queensStation.addItem(m2);
        queensStation.addItem(m3);
        jamaicaStation.addItem(m5);
        
        pennStation.addItem(c1);
        brklynStation.addItem(c2);
        currLocation = pennStation;

    }

    public static void main(String[] args){

        Item s1 = new Item("Knife", "Weapon", "A 2016 SwissArmy Knife, use it to cut things and defend yourself, be careful you may use it on yourself someday...");
        Item s2 = new Item("Flashlight", "Tool", "A flashlight with two extra batteries. Make them count!");
        Item s3 = new Item("Gasmask", "Tool", "A gas mask to help filter out the radioactive air.");
        Item s4 = new Item("Bar", "Food", "A proterin bar to keep you energized and ready.");
        Item s5 = new Item("Water", "Drink", "Some fresh water.");
        Item s6 = new Item("Wrench", "Tool", "An allen wrench, might be useful!");
        Item s7 = new Item("Screwdriver", "Tool", "This is a screwdriver.");
        
        myInventory = new ContainerItem("Backpack", "Inventory", "Herschel Spply backpack to store your items, you already have a couple to start.");
        c1 = new ContainerItem("Toolbox", "Tool", "This contains a bunch of tools to help you fix mechanical issues.");
        c2 = new ContainerItem("Bottle", "Container", "A plastic bottle that has water in it");

        

        myInventory.addItem(s1);
        myInventory.addItem(s2);
        myInventory.addItem(s3);
        myInventory.addItem(s4);

        c1.addItem(s6);
        c1.addItem(s7);
        c2.addItem(s5);
        
        Scanner scnr = new Scanner(System.in);

        
        System.out.println("The year 2077. Nuclear war has ravaged the world including the city of NewYork and left it in shambles. Welcome to the begining of the end!");
        boolean program = true;
        createWorld();
        while(program == true){
            
            System.out.println("Enter Command");
            String command = scnr.nextLine();  

            String[] sCommand = command.split(" ");

            
            switch(sCommand[0]){
                case "quit":{
                program = false;
                System.out.println("You have quit the game.");
                break;
                }

                case "look":{
                System.out.println(currLocation.getName()+ " - "+ currLocation.getDescription());
                for(int i=0;i< currLocation.numItems();i++){
                    System.out.println("+ "+currLocation.getItem(i).getName());
                }
                break;
                }

                case "examine":{
                    if(sCommand.length == 1){
                    System.out.println("You did not tell me which item to examine."); 
                    }
                
                    else if(currLocation.hasItem(sCommand[1]) == true){
                        for(int i=0; i<currLocation.numItems();i++){
                            if(sCommand[1].equalsIgnoreCase(currLocation.getItem(i).getName())){
                                System.out.println(currLocation.getItem(i).toString());
                            }
                        }           
                    } 
                    else{
                        System.out.println("This item doesn't exist!");
                    }        
                    break;
                }

                case "go":{
                    if(sCommand.length == 1){
                    System.out.println("You did not tell me where to go."); 
                    }

                    else if(currLocation.canMove(sCommand[1]) == true){
                    currLocation = currLocation.getLocation(sCommand[1]);
                    }
                            
                    else{
                        System.out.println("You can't go that way.");
                    }        
                    break;

                }
                
                case "inventory":{
                   System.out.println(myInventory.toString());
                   break;

                }

                case "take":{
                    if(sCommand.length == 1){
                        System.out.println("You didn't tell me which item to take"); 
                        }
                    else if (sCommand.length == 2){
                        if(currLocation.hasItem(sCommand[1].toLowerCase())){
                            Item e = currLocation.removeItem(sCommand[1]);
                            myInventory.addItem(e);
                            currLocation.removeItem(sCommand[1]);
                            System.out.println("You just took this item!");
                        }
                        else{
                            System.out.println("Cannot find that item here!");
                        }
                    }

                    else if(sCommand[2].equals("from") == true){
                        if(sCommand.length == 3){
                           System.out.println("You didn't tell me what item to take from!");
                        }   

                        else if(currLocation.getItem(sCommand[3].toLowerCase()) instanceof ContainerItem){
                           ContainerItem container = (ContainerItem) currLocation.getItem(sCommand[3].toLowerCase());
                            if(container.hasItem(sCommand[1].toLowerCase()) == true){
                                Item name = container.removeItem(sCommand[1].toLowerCase());
                                myInventory.addItem(name);
                                container.removeItem(sCommand[1].toLowerCase());
                                System.out.println("You just took " + name.getName()+" from "+container.getName());
                               }
                            
                            else{
                                System.out.println("Can't find that item in this container!");
                            }
                        }   
                        
                        else{
                           System.out.println("That's not a container item or this container item doesn't exist.");
                        }
                    }

                    break;

                 }
                 case "put":{
                    if(sCommand.length == 2){
                        System.out.println("Specify your item and container."); 
                        }

                    else if(sCommand[2].equals("in") == true){
                        if(sCommand.length == 3){
                           System.out.println("You didn't specify the container!");
                        }   

                        else if(currLocation.getItem(sCommand[3].toLowerCase()) instanceof ContainerItem){
                           ContainerItem container = (ContainerItem) currLocation.getItem(sCommand[3].toLowerCase());
                            if(myInventory.hasItem(sCommand[1].toLowerCase()) == true){
                                Item name = myInventory.removeItem(sCommand[1].toLowerCase());
                                container.addItem(name);
                                myInventory.removeItem(name.getName().toLowerCase());
                                System.out.println("You just put the " + name.getName()+" in the "+container.getName());
                               }
                            
                            else{
                                System.out.println("Can't find that item in your inventory!");
                            }
                        }   
                        
                        else{
                           System.out.println("That's not a container item or this container item doesn't exist.");
                        }
                    }

                    else{
                        System.out.println("This item is not in your inventory.");
                    }
                    break;

                 }

                 case "drop":{
                    if(sCommand.length == 1){
                        System.out.println("You didn't tell me which item to drop"); 
                        }
                    else if(myInventory.hasItem(sCommand[1].toLowerCase())){
                        Item e = myInventory.removeItem(sCommand[1]);
                        currLocation.addItem(e);
                        myInventory.removeItem(sCommand[1]);
                        System.out.println("You just dropped this item!");
                    }
                    else{
                      System.out.println("Cannot find that item in your inventory!");
                    }
                    break;
                 }

                 case "help":{
                     System.out.println("+ look - This command shows your location and the items in that location.");
                     System.out.println("+ examine - This command examines objects (add the object's name after this command).");
                     System.out.println("+ go - This command let's you navigate through locations( add the direction where you want to go after this command). ");
                     System.out.println("+ inventory - This command shows a list of the items in your inventory.");
                     System.out.println("+ take - This command let's you take an object and store it in your inventory (add the object's name after this command).");
                     System.out.println("+ take _ from _ - This command let's you take an item from a container and put in your inventroy.");
                     System.out.println("+ put _ in _ - This command let's you put an item from your inventory in another container");
                     System.out.println("+ drop - This command let's you drop an item from your inventory in your current location (add the object's name after this command).");
                     System.out.println("+ quit - This command let's you quit the game.");
                     
                     break;

                 }
                
                default: 
                System.out.println("I don't know how to do that");
            }
        }
    }
}
