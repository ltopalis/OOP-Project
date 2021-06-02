public class Offers extends RequestDonationList{
    public void commit(Organization organization, Beneficiary beneficiary)
            throws TheOrganizationDoesNotSupportTheEntity, WrongQuantity, TheOrganizationDoesNotSupportTheQuantity, TheEntityDoesNotExistInrdEntities {
        for(int i=getRdEntities().size()-1; i>=0; i--)
            organization.addCurrentDonation(this.getRdEntities().get(i), beneficiary);
        if(super.reset())
            System.out.println("Τα στοιχεία προστέθηκαν επιτυχώς στην λίστα του οργανισμού");
    }
}
