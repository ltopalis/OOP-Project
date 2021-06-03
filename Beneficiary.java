public class Beneficiary extends User {
    private int noPersons = 1;
    private RequestDonationList receivedList = new RequestDonationList(); // Είδη που έχει ήδη λάβει
    private Requests requestsList = new Requests(); // Είδη που ζητάει

    public Beneficiary(String name, String phone, int noPersons) {
        super(name, phone);
        this.noPersons = noPersons;
    }

    public int getNoPersons() {
        return noPersons;
    }

    public void setNoPersons(int noPersons) throws WrongNoPersons{
        if(noPersons<0) throw new WrongNoPersons();
        this.noPersons = noPersons;
    }

    /* receivedList */

    public void addReceivedList(RequestDonation requestDonation, Organization organization)
            throws TheOrganizationDoesNotSupportTheEntity, WrongQuantity, 
            TheOrganizationDoesNotSupportTheQuantity, TheEntityDoesNotExistInrdEntities {
        {
            receivedList.add(requestDonation, organization, this);
        }
    }

    public boolean removeReceivedList(RequestDonation requestDonation) throws TheEntityDoesNotExistInrdEntities{
        return receivedList.remove(requestDonation);
    }

    public void modifyReceivedList(int elem, double quantity){
        receivedList.modify(elem, quantity);
    }

    public void modifyReceivedList(Entity entity, double quantity, Organization organization)
            throws TheEntityDoesNotExistInrdEntities, WrongQuantity, TheOrganizationDoesNotSupportTheQuantity{
        receivedList.modify(entity, quantity, this, organization);
    }

    public String monitorReceivedList(){
        return receivedList.monitor();
    }

    public boolean resetReceivedList(){
        return receivedList.reset();
    }

    /* requestsList */

    public Requests getRequestsList(){
        return requestsList;
    }

    public void removeAnOffer(RequestDonation requestDonation) throws TheEntityDoesNotExistInrdEntities{
        requestsList.remove(requestDonation);
    }

    public boolean requestListIsEmpty(){
        return requestsList.getRdEntities().isEmpty();
    }

    public void addRequestList(RequestDonation requestDonation, Organization organization)
            throws TheOrganizationDoesNotSupportTheEntity, WrongQuantity, TheOrganizationDoesNotSupportTheQuantity, TheEntityDoesNotExistInrdEntities {
        requestsList.add(requestDonation, organization, this);
    }

    public void modifyRequestList(Entity entity, double quantity, Organization organization)
            throws TheEntityDoesNotExistInrdEntities, WrongQuantity, TheOrganizationDoesNotSupportTheQuantity{
        requestsList.modify(entity, quantity, this, organization);
    }

    public void commitRequestList(RequestDonation requestDonation, Organization organization)
            throws WrongQuantity, TheOrganizationDoesNotSupportTheQuantity,
            TheEntityDoesNotExistInrdEntities, TheOrganizationDoesNotSupportTheEntity{
        requestsList.commit(requestDonation, this, organization);
    }

    public String monitorRequestList(){
        return requestsList.monitor();
    }

    @Override
    public String toString() {
        return super.toString() + " Αριθμός μελών: " + getNoPersons();
    }
}
