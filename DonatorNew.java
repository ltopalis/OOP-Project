public class Donator extends User{ 
    // LISTS:
    private Offers offersList = new Offers();
    
    // CONSTRUCTORS:
    public Donator(String name, String phone){
    super(name, phone);     
    }
    
    // WRAPPER METHODS FOR LIST MANAGEMENT:
    
    // #1 offersList (from class Offers)
    public void addOffer(RequestDonation rd, Organization org){ 
        this.offersList.add(rd, org, null); // adds a RequestDonation in list
    }
    public void removeOffer(RequestDonation rd){
        this.offersList.remove(rd.getEntity()); // removes RequestDonation from list
    }
    public Offers getOffersList(){
        return offersList;
    }
    public boolean commmit(Organization org){
        this.offersList.commit(org);
        return true;
    }
}

