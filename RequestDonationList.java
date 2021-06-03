import java.util.ArrayList;

public class RequestDonationList {
    private ArrayList<RequestDonation> rdEntities = new ArrayList<>();

    public ArrayList<RequestDonation> getRdEntities(){
        return rdEntities;
    }

    public RequestDonation get(int elem) throws TheEntityDoesNotExistInrdEntities{
        for(int i=0; i< rdEntities.size(); i++)
            if(rdEntities.get(i).getEntity().getID() == elem)
                return rdEntities.get(i);

            throw new TheEntityDoesNotExistInrdEntities();
    }

    public void add(RequestDonation requestDonation, Organization organization, Beneficiary beneficiary)
            throws TheOrganizationDoesNotSupportTheEntity, WrongQuantity, TheOrganizationDoesNotSupportTheQuantity, TheEntityDoesNotExistInrdEntities {
        if(organization.checkEntityList(requestDonation.getEntity())){ // το Entity υπάρχει στον οργανισμό
            for(int i=0; i< rdEntities.size(); i++)
                if(rdEntities.get(i).getEntity().getID() == requestDonation.getEntity().getID()) {
                    rdEntities.get(i).addQuantity(requestDonation.getQuantity());
                    return;
                }
            rdEntities.add(requestDonation);
        }
        else
            throw new TheOrganizationDoesNotSupportTheEntity();
    }

    public boolean remove(RequestDonation requestDonation) throws TheEntityDoesNotExistInrdEntities{
        for(int i=0; i< rdEntities.size(); i++)
            if(rdEntities.get(i).getEntity().getID() == requestDonation.getEntity().getID()) {
                rdEntities.remove(i);
                System.out.println("Το αγαθό αφαιρέθηκε επιτυχώς");
                return true;
            }
        throw new TheEntityDoesNotExistInrdEntities();
    }

    public void modify(int elem, double quantity){
        rdEntities.get(elem).setQuantity(quantity);
        System.out.println("Η ποσότητα τροποποήθηκε επιτυχώς");
    }

    public void modify(Entity entity, double quantity, Beneficiary beneficiary, Organization organization)
            throws TheEntityDoesNotExistInrdEntities, WrongQuantity, TheOrganizationDoesNotSupportTheQuantity{
        int i;
        for (i=0; i<rdEntities.size(); i++)
            if(entity.getID() == rdEntities.get(i).getEntity().getID()) {
                rdEntities.get(i).setQuantity(quantity);
                return;
            }

        throw new TheEntityDoesNotExistInrdEntities();
    }

    public String monitor(){
        int i;
        if(rdEntities.size() == 0) return "Στην λίστα δεν υπάρχουν στοιχεία";
        String s = "Στην λίστα υπάρχουν " + rdEntities.size() + " στοιχεία\n";
        for(i = 0; i < rdEntities.size()-1; i++)
            s += (i+1) + ". " + rdEntities.get(i).getEntity().getName() + " (" + rdEntities.get(i).getQuantity() + ")\n";
        s += (i+1) + ". " + rdEntities.get(i).getEntity().getName() + " (" + rdEntities.get(i).getQuantity() + ")";
        return s;
    }

    public boolean reset(){
        return rdEntities.removeAll(rdEntities);

    }
}
