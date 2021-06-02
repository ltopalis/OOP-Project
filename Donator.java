public class Donator extends User{
    private Offers offerList = new Offers();

    public Donator(String name, String phone) {
        super(name, phone);
    }

    public boolean isEmpty(){
        return offerList.getRdEntities().isEmpty();
    }

    public Offers getOfferList(){
        return offerList;
    }

    public void addAnOffer(RequestDonation requestDonation, Organization organization)
            throws TheOrganizationDoesNotSupportTheEntity, WrongQuantity, TheOrganizationDoesNotSupportTheQuantity, TheEntityDoesNotExistInrdEntities {
        offerList.add(requestDonation, organization, null);
    }

    public void removeAnOffer(RequestDonation requestDonation) throws TheEntityDoesNotExistInrdEntities{
        offerList.remove(requestDonation);
    }

    public void modifyAnOffer(int elem, double quantity){
        offerList.modify(elem, quantity);
    }

    public void modifyAnOffer(Entity entity, double quantity)
            throws TheEntityDoesNotExistInrdEntities, WrongQuantity, TheOrganizationDoesNotSupportTheQuantity{
        offerList.modify(entity, quantity, null, null);
    }

    public String monitorAllOffers(){
        return offerList.monitor();
    }

    public boolean resetTheList(){
        return offerList.reset();
    }

    public void commitTheOffer(Organization organization)
            throws TheOrganizationDoesNotSupportTheEntity, WrongQuantity, TheOrganizationDoesNotSupportTheQuantity, TheEntityDoesNotExistInrdEntities {
        offerList.commit(organization, null);
    }
}