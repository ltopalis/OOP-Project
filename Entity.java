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

  /* Ας κάνουμε μία λίγο καλύτερη μορφοποίηση σε αυτή την μέθοδο.
  Ενδεικτικά (ότι είναι σε " " είναι οι μεταβλητές), 
  "όνομα" (ID: "κωδικός")
  "περιγραφή"
  */

  // return this.name + " (ID: " + this.ID + " )" + "\n" + this.description
  public String getEntityInfo(){return "(ID: " + this.ID + ")" +"\n" + "(name: " + this.name + ")" + 
  "(description: " + this.description +")";} // 66 lazania hdjkfdksfdsl
  abstract public String getDetails(); // Εφόσον λέει απλή δήλωση πρέπει να είναι abstract (Μην ξεχάσεις στο τέλος το ;)
  
  @Override
  public String toString()
  {return getEntityInfo() + "\t" + getDetails();}

    
    
 }
