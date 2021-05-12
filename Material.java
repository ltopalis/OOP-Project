public class Material extends Entity{
  private double level1; // private
  private double level2;
  private double level3;
 
  public Material(int ID, String name, String description, 
                double level1, double level2, double level3){
  
    super(ID, name, description);
    this.level1 = level1;
    this.level2 = level2;
    this.level3 = level3;
 }
 
  public String getDetails(){return "Είναι τύπου Material με levels: " + this.level1 + " " + this.level2 + " " + this.level3;} // Να επιστρέφει και ότι είναι material
 






}