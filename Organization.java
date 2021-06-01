import java.util.*;

public class Organization {

    //INSTANCE VARIABLES:
    private String name; 
    private Admin admin;

    private ArrayList<Entity> entityList = new ArrayList<>();                     // μια λίστα από "entities" που μπορούν να διανεμηθούν σε "beneficiaries"
    private ArrayList<Beneficiary> beneficiaryList = new ArrayList<>();           // μια λίστα από "beneficiaries" 
    private ArrayList<Donator> donatorList = new ArrayList<>();                   // μια λίστα από "donators"
    private RequestDonationList currentDonations = new RequestDonationList();     // ΟΛΕΣ ΟΙ ΠΡΟΣΦΟΡΕΣ ΤΟΥ ΟΡΓΑΝΙΣΜΟΥ ΕΔΩ ! ! ! 
    
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
    public void addEntity(Entity ent){
         // checks if the entity already exists in entityList:
        try
        {
            this.checkEntityExists(ent); // HANDLED
        }
        catch (ElementAlreadyExistsException eae)
        {
            System.err.println(eae);
        }
        entityList.add(ent);
    }
    public void removeEntity(Entity ent){
        // check if admin?
        if ((this.admin).getisAdmin() == false) {return;} // abbort
        entityList.remove(ent);
    }
    void listEntities(){
       System.out.println("Service List:");
       for (int i = 0; i < entityList.size(); i++){
           if (entityList.get(i) instanceof Service){
               System.out.println(entityList.get(i).getName());
           }
       }
        System.out.println("Material List:");
       for (int i = 0; i < entityList.size(); i++){ // scan the contents of the List
           if (entityList.get(i) instanceof Material){ // check if it is a Material
               System.out.println(entityList.get(i).getName());
           }
       }
    }
    public void checkEntityExists(Entity ent) throws ElementAlreadyExistsException { // NEW  
        for (int i = 0; i < entityList.size(); i++){
            if (ent.getID() == entityList.get(i).getID()) {throw new ElementAlreadyExistsException();} // check each one of the entityList elements
        }
    }
    public ArrayList<Entity> getEntityList(){
        return this.entityList;
    }
    // #2 donatorList:
    void insertDonator(User don){
        try
        {
            this.checkDonatorExists(don); // HANDLED
        }
        catch (ElementAlreadyExistsException eae)
        {
            System.err.println(eae);
        }
        donatorList.add((Donator)don);
    }
    void removeDonator(User don){
        donatorList.remove((Donator)don);
    }
    void listDonators(){
        System.out.println("Donator List:");
        for(int i = 0; i < donatorList.size(); i++){
            System.out.println(i + ". " + donatorList.get(i).getName());
        }
    }
    public void checkDonatorExists(User donator) throws ElementAlreadyExistsException {
        for (int i = 0; i < donatorList.size(); i++){
            if ((donator.getPhone() == donatorList.get(i).getPhone()) && (donator.getName() == donatorList.get(i).getName())) {throw new ElementAlreadyExistsException();} // check each one of the entityList elements
        }
    }
    public ArrayList<Donator> getDonatorList(){
        return this.donatorList;
    }

    // #3 benefiaciaryList:
    void insertBeneficiary(User ben){
        try
        {
            this.checkBeneficiaryExists(ben); // HANDLED
        }
        catch (ElementAlreadyExistsException eae)
        {
            System.err.println(eae);
        }
        beneficiaryList.add((Beneficiary)ben);
    }
    void removeBeneficiary(User ben){
        beneficiaryList.remove((Beneficiary)ben);
    }
    void listBeneficiaries(){
        System.out.println(i+ ". " + this.beneficaryList.get(i).getName());
    }
    public void checkBeneficiaryExists(User beneficiary) throws ElementAlreadyExistsException {
        for (int i = 0; i < beneficiaryList.size(); i++){
            if ((beneficiary.getPhone() == beneficiaryList.get(i).getPhone()) && (beneficiary.getName() == beneficiaryList.get(i).getName())) {throw new ElementAlreadyExistsException();}
        }
    }
    public ArrayList<Beneficiary> getBeneficiaryList(){
        return this.beneficiaryList;
    }
    // #4 currentDonations: (wrapper methods)
    public boolean addDonation(RequestDonation rd){
        return this.currentDonations.add(rd, this, null);
        // NOTICE: quantity automatically rises, if given Entity, already exists (see RequestDonationList.add >> Requests.addQuantity)
    }
    public void removeDonation(RequestDonation rd){
        this.currentDonations.remove(rd.getEntity());
    }
    public int sizeDonation(){
        return this.currentDonations.listSize();
    }
    public RequestDonationList getCurrentDonations(){
        return this.currentDonations
    }
} // end Organization class
