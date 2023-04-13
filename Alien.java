import java.util.*; 

public class Alien implements Contract{

    /* attributes for the Alien class */
    private String name;
    private int height;
    private int energy;
    private int capacity;
    private ArrayList <String> collected_items;
    private int x_coord;
    private int y_coord;
    private String lastAction;
    
    /* Constructor for the Alien class */
    public Alien(String name, int height, int capacity, int x_coord, int y_coord){
        this.name = name;
        this.energy = 100;
        this.height = height;
        this.capacity = capacity;
        this.collected_items = new ArrayList<>();
        this.x_coord= x_coord;
        this.y_coord = y_coord;
        this.lastAction = "N/A";
    }
    /* Overloaded constructor for the Alien class wehn only given a name, height, and capacity */
    public Alien(String name, int height, int capacity){
        this.name = name;
        this.energy = 100;
        this.height = height;
        this.capacity = capacity;
        this.collected_items = new ArrayList<>();
        this.x_coord = 0;
        this.y_coord = 0;
        this.lastAction = "N/A";
    }

    /* Overloaded constructor for the Alien class wehn only given a name */
    public Alien(String name){
        this.name = name;
        this.energy = 100;
        this.height = 80;
        this.capacity = 8;
        this.collected_items = new ArrayList<>();
        this.x_coord = 0;
        this.y_coord = 0;
        this.lastAction = "N/A";
    }

    /* Accessor for name */
    public String getName(){
        return this.name;
    }

    /* Accessor for energy */
    public int getEnergy(){
        return this.energy;
    }

    /* Accessor for height */
    public int getHeight(){
        return this.height;
    }

    /* Accessor for last action completed */
    public String getLastAction(){
        return this.lastAction;
    }

    /* Accessor for capacity which represents the max num of items the alien can grab */
    public int getCapacity(){
        return this.capacity;
    }

    /* Accessor for getCollectedItems which is a list of all the things the alien has grabbed */
    public ArrayList<String> getCollectedItems(){
        return this.collected_items;
    }

    /* Accessor for the x coordinate that the alien is loacted in */
    public int getXcoord(){
        return this.x_coord;
    }

    /* Accessor for the y coordinate that the alien is loacted in */
    public int getYcoord(){
        return this.y_coord;
    }

    /** This method first checks if the alien has enough energy to do this then it checks if it has enough holding capacity to grab an item. When it successfuly grabs an item it looses energy.
     * @param String item which is the item the alien is checking to pick up
     */
    public void grab(String item){
        try{
            if ((this.energy < 10)){
                throw new RuntimeException("I don't have enough energy to try this... :|");
            }
            else if (this.collected_items.size() == this.capacity){
                throw new RuntimeException("Cannot grab item because I don't have space to hold it");
            }
            else if (this.collected_items.contains(item)){
                throw new RuntimeException("I already have this item!");
            }
            this.collected_items.add(item);
            System.out.println(item + " has been grabbed");
            this.lastAction = "Grab";
            this.energy -= 10;
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /** This method first checks if the alien has enough energy then checks if an alien has an item or not and then drops it or throws an error if it does not
     * @param String item this is the item that the alien wants to check if it has in order to drop
     * @return String item which is the item the alien is checking to drop
     */
    public String drop(String item){
        try{
            if ((this.energy < 10)){
                throw new RuntimeException("I don't have enough energy to try this... :|");
            }
            else if (!this.collected_items.contains(item)){
                throw new RuntimeException("Cannot drop this item because I do not have it.");
            }
            this.collected_items.remove(item);
            System.out.println(item + " has been dropped!");
            this.lastAction = "Drop";
            this.energy -= 10;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return item;
    }

    /** This method first checks if the alien has enough energy to do this then it will "examine" it by looking at it and deciding on a 50% chance that it will want it then attempt to grab the item.
     * @param String item which is the item the alien is checking to examine
     */
    public void examine(String item){
        Random rand = new Random();
        int n = rand.nextInt(2);
        try{
            if ((this.energy < 10)){
                throw new RuntimeException("I don't have enough energy to try this... :|");
            }
            else if (n == 0){
                System.out.println("I think " + item + " is ugly! I don't want it get it away from me....");
            }
            System.out.println("I do like this I want to grab it!");
            grab(item);
            this.energy -= 10;
            this.lastAction = "Examine";

        } catch(Exception e){
            System.out.println(e);
        }
    
    }

    /** This method first checks if the alien has enough energy to do this then it checks if it has an item. If it does it will be used.
     * @param String item which is the item the alien is checking to use
     */
    public void use(String item){
        try{
            if ((this.energy < 10)){
                throw new RuntimeException("I don't have enough energy to try this... :|");
            }
            else if (!this.collected_items.contains(item)){
                throw new RuntimeException("Cannot use this because I do not have it.");
            }
            System.out.println(item + " has been used!");
            this.energy -= 10;
            this.lastAction = "Use";
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    /** This method first checks if the alien has enough energy to do this then it checks if it is capable of walking in a certain direction.
     * @param String direction which is the direction that the alien wishes to walk in
     * @return boolean that represents whether the alien is capable of walking at all or in a specific direction
     */
    public boolean walk(String direction){
        try{
            if ((this.energy < 10)){
                throw new RuntimeException("I don't have enough energy to try this... :|");
            }
            else if (direction == "North" && this.y_coord >= 100){
                throw new RuntimeException("I cannot go there because it is out of bounds.");
            }
            else if(direction == "South" && this.y_coord <= 0){
                throw new RuntimeException("I cannot go there because it is out of bounds.");
            }
            else if(direction == "East" && this.x_coord >= 100){
                throw new RuntimeException("I cannot go there because it is out of bounds.");
            }
            else if(direction == "West" && this.x_coord <= 0){
                throw new RuntimeException("I cannot go there because it is out of bounds.");
            }
            return true;
        } catch(Exception e){
            System.out.println(e);
            return false;
        }

    }
    /** This method calls on walk to verify that the alien can walk then it will move the alien according to desired direction.
     * @param String direction which is the direction the alien wishes to walk in
     */
    public void move(String direction){
            if (walk(direction) == false){
                walk(direction);
            }
            else if (direction == "North"){
                this.y_coord += 1;
                System.out.println(this.name + " moved one step North.");
                this.lastAction = "Walk-North";
                this.energy -=10;
            }
            else if (direction == "East"){
                this.x_coord += 1;
                System.out.println(this.name + " moved one step East.");
                this.lastAction = "Walk-East";
                this.energy -=10;
            }
            else if (direction == "South"){
                this.y_coord -= 1;
                System.out.println(this.name + " moved one step South.");
                this.lastAction = "Walk-South";
                this.energy -=10;
            }
            else if (direction == "West"){
                this.x_coord -= 1;
                System.out.println(this.name + " moved one step West.");
                this.lastAction = "Walk-West";
                this.energy -=10;
            }
    }

        /** This methods verifies if the alien is capable of flying by checking that it has enough energy, wants to fly to a spot within the bounds, and isn't already there.
        * @param int x which is the x coordinate the alien wishes to be at
        * @param int y which is the y coordinate the alien wishes to be at
        * @return boolean that represents whether the alien can fly or not
        */
        public boolean fly(int x, int y){
            try {
                if ((this.energy < 10)){
                    throw new RuntimeException("I don't have enough energy to try this... :|");
                }
                else if(this.x_coord == x && this.y_coord == y){
                    throw new RuntimeException("Cannot fly there because I am already here.");
                }
                else if (x < 0 | x > 100 | y < 0 | y > 100){
                    throw new RuntimeException("Cannot fly there because it's out of boundaries.");
                }
                return true;

            } catch(Exception e){
                System.out.println(e);
                return false;
            } 
        }

    /** This method calls on fly() to verify that it is able to then it will succesfully update the coordinnates the alien is at.
     * @param int x the x coordinate the alien wishes to go to
     * @param int y the y coordinate the alien wishes to go to
     */
    public void flyTo(int x, int y){
            if(fly(x,y) == false){
                fly(x, y);
            }
            this.x_coord = x;
            this.y_coord = y;
            System.out.println(this.name + " is now in the coordinate:(" + x + "," + y + ")");
            this.energy -= 10;
            this.lastAction = ("Fly");
    }

    /** This method first checks if the alien has enough energy to do this then it checks if it has enough height to shrink if so it reduces the height by 20.
     * @return this.height which is the updated or not updated height for the alien
     */
    public Number shrink(){
        try {
            if ((this.energy < 10)){
            throw new RuntimeException("I don't have enough energy to try this... :|");
            }
            else if(this.height <= 20){
                throw new RuntimeException("I am too tiny cannot shrink anymore.. :(");
            }
            this.height -= 20;
            this.lastAction = "Shrink";
            return this.height;

        } catch(Exception e){
            System.out.println(e);
            return this.height;
        } 

    }

    /** This method first checks if the alien has enough energy to do this then it increases its height by 20.
     * @param this.height which is the updated or not updated height of the alien
     */
    public Number grow(){
        try {
            if ((this.energy < 10)){
                throw new RuntimeException("I don't have enough energy to try this... :|");
            }
            this.height += 20;
            this.lastAction = "Grow";
            return this.height;
        } catch(Exception e){
            System.out.println(e);
            return this.height;
        }
    }

    /** This method increases the alien's energy by 100 and prints out the new energy level
     */
    public void rest(){
        this.energy += 100;
        System.out.println("Now my energy level is " + this.energy);
    }

    /** This method checks which was the last method called and then it will increase the energy by 10 and perform the opposite method if there is one. If it doesn't call a method then it cannot be undone or further instructions are provided.
     */
    public void undo(){
        try{
            if (this.lastAction.equals("N/A")){
                throw new RuntimeException("Cannot undo anything because no action has been completed yet.");
            }
            else if (this.lastAction.equals("Grow")){
                this.energy += 10;
                shrink();
            }
            else if (this.lastAction.equals("Shrink")){
                this.energy += 10;
                grow();
            }
            else if (this.lastAction.equals("Grab")){
                this.energy += 10;
                System.out.println("Call the drop method with the name of the item you just grabbed.");
            }
            else if (this.lastAction.equals("Drop")){
                this.energy += 10;
                System.out.println("Call the grab method with the name of the item you just dropped.");
            }
            else if (this.lastAction.equals("Examine")){
                System.out.println("Cannot undo examine method!");
            }
            else if (this.lastAction.equals("Use")){
                System.out.println("Cannot undo use method!");
            }
            else if (this.lastAction.equals("Walk-North")){
                this.energy += 10;
                walk("South");
            }
            else if (this.lastAction.equals("Walk-South")){
                this.energy += 10;
                walk("North");
            }
            else if (this.lastAction.equals("Walk-West")){
                this.energy += 10;
                walk("East");
            }
            else if (this.lastAction.equals("Walk-East")){
                this.energy += 10;
                walk("West");
            }
            else if (this.lastAction.equals("Examine")){
                System.out.println("Cannot undo examine method!");
            }
            else if(this.lastAction.equals("Fly")){
                System.out.println("Call the flyTo method with your previous coordinate.");
            }
            
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Alien bob = new Alien("Bob", 80, 2);
        bob.grab("Cat");
        bob.grab("Human");
        bob.grab("Human2");
        bob.drop("Human2");
        bob.drop("Human");
        bob.grab("Human");
        bob.undo();
        bob.shrink();
        bob.move("North");
        bob.move("South");
        bob.use("Cat");   
        bob.use("Human2");
        bob.examine("Human2");
        bob.shrink();
        bob.grow();
        bob.flyTo(10, 20);
        bob.undo();
    }
}
