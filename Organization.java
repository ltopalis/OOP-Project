import java.util.*;

public class Organization {

    //INSTANCE VARIABLES:
    private String name; 
    private Admin admin;

    private ArrayList<Entity> entityList = new ArrayList<>();             // μια λίστα από "entities" που μπορούν να διανεμηθούν σε "beneficiaries"
    private ArrayList<Beneficiary> beneficiaryList = new ArrayList<>();   // μια λίστα από "beneficiaries" 
    private ArrayList<Donator> donatorList = new ArrayList<>();           // μια λίστα από "donators"
    private RequestDonationList currentDonations;     // an rdEntities list (check RequestDonationList for more info)
    
    // CONSTRUCTOR:
    public Organization(String name, Admin admin){
        this.name = name;
        this.admin = admin;
    }
     public Organization(String name, User user){
        this.name = name;
        this.admin = (Admin)user;
    }
    // METHODS:

    // Admin:
    public void setAdmin(Admin adm){
        this.admin = adm; // change organization's Admin 
    }
    public Admin getAdmin(){
        return admin; // returns organization's Admin (object)
    }

    // LIST MANAGEMENT:

    // #1 entityList:
    public void addEntity(Entity ent) throws EntityAlreadyExists{
        // checks if the entity already exists in entityList:
        if (checkEntityExists(ent)) {throw new EntityAlreadyExists();}
        entityList.add(ent);
    }
    public void removeEntity(Entity ent){
        // check if admin?
        if ((this.admin).getisAdmin() == false) {return;}
        entityList.remove(ent);
    }
    void listEntities(){
       // to be implemented...
    }
    public boolean checkEntityExists(Entity ent){ // NEW  
        for (int i = 0; i < entityList.size(); i++){
            if (ent.getID() == entityList.get(i).getID()) {return true;} // check each one of the entityList elements
        }
        return false; // element not found
    }
    // #2 donatorList:
    void insertDonator(Donator don){

    }
    void removeDonator(Donator don){
        
    }
    void listDonators(){
        
    }

    // #3 benefiaciaryList:
    void insertBeneficiary(Beneficiary ben){

    }
    void removeBeneficiary(Beneficiary ben){
        
    }
    void listBeneficiary(){

    }

    // (#4) requestDonationList:
    // wrapper μέθοδοι προαιρετικά (με την υλοποίηση της κλάσης RequestDonationList)

} // end Organization class
