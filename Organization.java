import java.util.ArrayList;

public class Organization {
    private String name;
    private Admin admin;
    private ArrayList<Entity> entityList = new ArrayList<>();                     // μια λίστα από "entities" που μπορούν να διανεμηθούν σε "beneficiaries"
    private ArrayList<Donator> donatorList = new ArrayList<>();
    private ArrayList<Beneficiary> beneficiaryList = new ArrayList<>();
    private RequestDonationList currentDonations = new RequestDonationList();
    private RequestDonationList materialList = new RequestDonationList();
    private RequestDonationList serviceList = new RequestDonationList();

    public Organization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAdmin(Admin admin){
        this.admin = admin;
    }

    public Admin getAdmin(){
        return admin;
    }

    public int materialListSize(){
        return materialList.getRdEntities().size();
    }

    public int serviceListSize(){
        return serviceList.getRdEntities().size();
    }

    public RequestDonationList getMaterialList(){
        return materialList;
    }

    public RequestDonationList getServiceList(){
        return serviceList;
    }

    public void addEntity(Entity entity) throws ElementAlreadyExistsInEntityList{
        for(int i = 0; i<entityList.size(); i++)
            if(entity.getID() == entityList.get(i).getID())
                throw new ElementAlreadyExistsInEntityList();
        entityList.add(entity);
        System.out.println("Το στοιχείο προστέθηκε επιτυχώς στην λίστα του οργανισμού");

    }

    public boolean checkEntityList(Entity entity){
        for(int i=0; i<entityList.size(); i++)
            if(entity.getID() == entityList.get(i).getID())
                return true;
        return false;
    }

    public void removeEntity(Entity entity, User user) throws TheUserHasNoAccess{
        if(user instanceof Admin)
            for(int i=0; i<entityList.size(); i++)  
                if(entityList.get(i).getID() == entity.getID()) {
                    entityList.remove(i);
                    System.out.println("Το στοιχείο αφαιρέθηκε με επιτυχία");
                    break;
                }
        else throw new TheUserHasNoAccess();
    }

    public User checkAPhoneNumber(String phone) throws TheUserDoesNotExist{
        for (int i = 0; i<beneficiaryListSize(); i++)
            if(phone.equals(beneficiaryList.get(i).getPhone())) 
                return beneficiaryList.get(i);

        for (int i = 0; i<donatorListSize(); i++)
            if(phone.equals(donatorList.get(i).getPhone()))
                return donatorList.get(i);

            if(phone.equals(admin.getPhone()))
                return admin;
            else
                throw new TheUserDoesNotExist();
    }

    public ArrayList<Donator> getDonatorList() {
        return donatorList;
    }

    public int donatorListSize(){
        return donatorList.size();
    }

    public void insertDonator(Donator donator)throws TheUserAlreadyExistsInDonatorList{
        for(int i=0; i<donatorList.size(); i++)
            if(donatorList.get(i) == donator)
                throw new TheUserAlreadyExistsInDonatorList();

        donatorList.add(donator);
        System.out.println("Ο χρήστης προστέθηκε με επιτυχία στην λίστα του οργανισμού");
    }

    public void removeDonator(Donator donator){
        for(int i = 0; i<donatorList.size(); i++)
            if(donator == donatorList.get(i)){
                donatorList.remove(i);
                System.out.println("Ο δωρητής αφαιρέθηκε με επιτυχία");
                break;
            }
    }

    public String listDonator(){
        String s = "Υπάρχουν " + donatorList.size() + " δωρητές στον οργανισμό.\n";
        s += "=".repeat(s.length());
        s += "\n";
        for (int i=0; i<donatorList.size(); i++)
            s += (i+1) + ". "  + donatorList.get(i) + "\n";
        return s;
    }

    public ArrayList<Beneficiary> getBeneficiaryList() {
        return beneficiaryList;
    }

    public int beneficiaryListSize(){
        return beneficiaryList.size();
    }

    public void insertBeneficiary(Beneficiary beneficiary)throws TheUserAlreadyExistsInBeneficiaryList{
        for(int i=0; i<beneficiaryList.size(); i++)
            if(beneficiaryList.get(i) == beneficiary)
                throw new TheUserAlreadyExistsInBeneficiaryList();

        beneficiaryList.add(beneficiary);
        System.out.println("Ο χρήστης προστέθηκε με επιτυχία στην λίστα του οργανισμού");
    }

    public void removeBeneficiary(Beneficiary beneficiary){
        for(int i = 0; i<beneficiaryList.size(); i++)
            if(beneficiary == beneficiaryList.get(i)){
                beneficiaryList.remove(i);
                System.out.println("Ο εποφελούμενος αφαιρέθηκε με επιτυχία");
                break;
            }
    }

    public String listBeneficiary(){
        String s = "Υπάρχουν " + beneficiaryList.size() + " εποφελούμενοι στον οργανισμό.\n";
        s += "=".repeat(s.length());
        s += "\n";
        for (int i=0; i<beneficiaryList.size(); i++)
            s += (i+1) + ". "  + beneficiaryList.get(i) + "\n";
        return s;
    }

    public String listEntities(){
        String s = "Στον οργανισμό υπάρχουν " + entityList.size() + " στοιχεία\n";
        s += "\tMaterials\n";
        s += "=".repeat("\tMaterials\t".length() + 6);
        s += "\n";
        for(int i=0; i<materialListSize(); i++)
            s += materialList.getRdEntities().get(i) + "\n";
        s += "\tServices\n";
        s += "=".repeat("\tServices".length() + 6);
        s += "\n";
        for(int i=0; i<serviceListSize(); i++)
            s += serviceList.getRdEntities().get(i) + "\n";

        return s;
    }

    public String listMaterials(){
        String s = "";
        int i;
        for(i=0; i<materialListSize()-1; i++)
            s += (i+1) + ". " + materialList.getRdEntities().get(i).getEntity().getName() + "\t(" + materialList.getRdEntities().get(i).getQuantity() + ")\n";
        s+= (i+1) + ". " + materialList.getRdEntities().get(i).getEntity().getName() + "\t(" + materialList.getRdEntities().get(i).getQuantity() + ")";
        return s;
    }

    public String listServices(){
        String s = "";
        int i;
        for(i=0; i<serviceListSize()-1; i++)
            s += (i+1) + ". " + serviceList.getRdEntities().get(i).getEntity().getName() + "\t(" + serviceList.getRdEntities().get(i).getQuantity() + ")\n";
        s += (i+1) + ". " + serviceList.getRdEntities().get(i).getEntity().getName() + "\t(" + serviceList.getRdEntities().get(i).getQuantity() + ")";
        return s;
    }

    public void addCurrentDonation(RequestDonationList listOfRequestDonation)
            throws TheOrganizationDoesNotSupportTheEntity, WrongQuantity, TheOrganizationDoesNotSupportTheQuantity, TheEntityDoesNotExistInrdEntities {
        for(int i=0; i<listOfRequestDonation.getRdEntities().size(); i++) {
            currentDonations.add(listOfRequestDonation.getRdEntities().get(i), this, null);
            if(listOfRequestDonation.getRdEntities().get(i).getEntity() instanceof Material)
                materialList.add(listOfRequestDonation.getRdEntities().get(i), this, null);
            else
                serviceList.add(listOfRequestDonation.getRdEntities().get(i), this, null);
        }
    }

    public void addCurrentDonation(RequestDonation requestDonation, Beneficiary beneficiary)
            throws TheOrganizationDoesNotSupportTheEntity, WrongQuantity, TheOrganizationDoesNotSupportTheQuantity, TheEntityDoesNotExistInrdEntities {
        currentDonations.add(requestDonation, this, beneficiary);
        if(requestDonation.getEntity() instanceof Material)
            materialList.add(requestDonation, this, beneficiary);
        else
            serviceList.add(requestDonation, this, beneficiary);
    }

    public void quantityCheck(RequestDonation requestDonation) throws WrongQuantity, TheOrganizationDoesNotSupportTheQuantity{
        if(requestDonation.getQuantity() < 0) throw new WrongQuantity();

        for(int i=0; i<currentDonations.getRdEntities().size(); i++)
            if(requestDonation.getEntity().getID() == currentDonations.getRdEntities().get(i).getEntity().getID())
                if(requestDonation.getQuantity() > currentDonations.getRdEntities().get(i).getQuantity())
                    throw new TheOrganizationDoesNotSupportTheQuantity();
    }

    public void removeCurrentDonationQuantity(RequestDonation requestDonation){
        for(int i=0; i<currentDonations.getRdEntities().size(); i++)
            if(currentDonations.getRdEntities().get(i).getEntity().getID() == requestDonation.getEntity().getID())
                currentDonations.getRdEntities().get(i).addQuantity((requestDonation.getQuantity() * -1));
    }
}
