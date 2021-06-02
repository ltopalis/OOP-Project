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
    public String getName(){
        return this.name;
    }
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
        catch (EntityAlreadyExistsException eae)
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
    public void listEntities(){
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
    public void checkEntityExists(Entity ent) throws EntityAlreadyExistsException { // NEW  
        for (int i = 0; i < entityList.size(); i++){
            if (ent.getID() == entityList.get(i).getID()) {throw new EntityAlreadyExistsException();} // check each one of the entityList elements
        }
    }
    public ArrayList<Entity> getEntityList(){
        return this.entityList;
    }
    // #2 donatorList:
    public void insertDonator(User don){
        try
        {
            this.checkDonatorExists(don); // HANDLED
        }
        catch (EntityAlreadyExistsException eae)
        {
            System.err.println(eae);
        }
        donatorList.add((Donator)don);
    }
    public void removeDonator(User don){
        donatorList.remove((Donator)don);
    }
    public void listDonators(){
        System.out.println("Donator List:");
        for(int i = 0; i < donatorList.size(); i++){
            System.out.println(i + ". " + donatorList.get(i).getName());
        }
    }
    public void checkDonatorExists(User donator) throws EntityAlreadyExistsException {
        for (int i = 0; i < donatorList.size(); i++){
            if ((donator.getPhone() == donatorList.get(i).getPhone()) && (donator.getName() == donatorList.get(i).getName())) {throw new EntityAlreadyExistsException();} // check each one of the entityList elements
        }
    }
    public ArrayList<Donator> getDonatorList(){
        return this.donatorList;
    }

    // #3 benefiaciaryList:
    public void insertBeneficiary(User ben){
        try
        {
            this.checkBeneficiaryExists(ben); // HANDLED
        }
        catch (EntityAlreadyExistsException eae)
        {
            System.err.println(eae);
        }
        beneficiaryList.add((Beneficiary)ben);
    }
    public void removeBeneficiary(User ben){
        beneficiaryList.remove((Beneficiary)ben);
    }
    public void listBeneficiaries(){
        System.out.println("Beneficiary List:");
        for(int i = 0; i < beneficiaryList.size(); i++){
            System.out.println(i+ ". " + this.beneficiaryList.get(i).getName());
        }
    }
    public void checkBeneficiaryExists(User beneficiary) throws EntityAlreadyExistsException {
        for (int i = 0; i < beneficiaryList.size(); i++){
            if ((beneficiary.getPhone() == beneficiaryList.get(i).getPhone()) && (beneficiary.getName() == beneficiaryList.get(i).getName())) {throw new EntityAlreadyExistsException();}
        }
    }
    public ArrayList<Beneficiary> getBeneficiaryList(){
        return this.beneficiaryList;
    }
    // #4 currentDonations: (wrapper methods)
    public boolean addDonation(RequestDonation rd){
        try
        {
            return this.currentDonations.add(rd, this, null);
        }
        catch (TheEntityDoesntExistInCompanyListException ex)
        {
            System.err.println(ex);
        }
        // NOTICE: quantity automatically rises, if given Entity, already exists (see RequestDonationList.add >> Requests.addQuantity)
        return true;
    }
    public void removeDonation(RequestDonation rd){
        try
        {
            this.currentDonations.remove(rd.getEntity());
        }
        catch (ThereIsNotSuchElementException hi)
        {
            System.err.println(hi);
        }
    }
    public int sizeDonation(){
        return this.currentDonations.listSize();
    }
    public RequestDonationList getCurrentDonations(){
        return this.currentDonations;
    }
} // end Organization class
