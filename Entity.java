abstract class Entity {
    private int ID;
    private String name;
    private String description;

    public Entity(int ID, String name, String description){
        this.ID = ID;
        this.name = name;
        this.description = description;
    }

    public Entity(int ID) {this.ID = ID;}

    public void setID(int ID){this.ID = ID;}
    public int getID(){return ID;}
    public void setName(String name) {this.name = name;}
    public String getName(){return name;}
    public void setDescription(String description) {this.description = description;}
    public String getDescription(){return description;}

    public String getEntityInfo(){return "(" + this.ID + ") " + this.name +
            "\t" + getDetails() + "\t" + this.description;}
    abstract public String getDetails();

    @Override
    public String toString()
    {return getEntityInfo();}



}
