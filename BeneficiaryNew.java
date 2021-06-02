public class Beneficiary extends User{
    // INSTANCE VARIABLES:
    private int noPersons = 1;
    
    // LISTS:
    private RequestDonationList receivedList = new RequestDonationList();
    private Requests requestsList = new Requests();
    
    // CONSTRUCTORS:
    public Beneficiary(String name, String phone, int noPersons)
    {
        super(name, phone);
        this.noPersons = noPersons;
    }
    
    // SETTERS-GETTERS:
    public void setNoPersons(int noPersons) {this.noPersons = noPersons;}
    public int getNoPersons(){return noPersons;}
    
    // WRAPPER METHODS FOR LIST MANAGEMENT:
    
    // #1 receivedList: (from class RequestDonationList)
    public void addReceived(RequestDonation rd, Organization org){
            this.receivedList.add(rd, org, null); // adds a RequestDonation in list
    }
    public void removeReceived(RequestDonation rd){
            this.receivedList.remove(rd.getEntity());  // removes RequestDonation from list
    }
    public RequestDonationList getReceivedList(){
      return receivedList;
    }
    // ^αν χρειαστεί υλοποιούμε και άλλες μεθόδους ανάλογα με το τι θέλουμε κάθε φορά.
    
    // #2 requestsList:
    public void addRequest(RequestDonation rd, Organization org){
            this.requestsList.add(rd, org, null);
        } // adds a RequestDonation in list
    public void removeRequest(RequestDonation rd){
            this.requestsList.remove(rd.getEntity());
        } // removes RequestDonation from list
    public Requests getRequestsList(){
    return requestsList;
    }
    // ^αν χρειαστεί υλοποιούμε και άλλες μεθόδους ανάλογα με το τι θέλουμε κάθε φορά.
    
    /* αυτές ειναι οι βασικές μέθοδοι για την διαχείριση των λιστών. 
       Σε περίπτωση που χρειαστούν άλλες μπορούμε να τις προσθέσουμε */
}
