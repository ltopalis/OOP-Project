public class Requests  extends RequestDonationList{
    @Override
    public void add(RequestDonation requestDonation, Organization organization, Beneficiary beneficiary)
            throws TheOrganizationDoesNotSupportTheEntity, WrongQuantity, TheOrganizationDoesNotSupportTheQuantity, TheEntityDoesNotExistInrdEntities {
        validRequestDonation(requestDonation, beneficiary);
        organization.quantityCheck(requestDonation);
        super.add(requestDonation, organization, null);
    }

    @Override
    public void modify(Entity entity, double quantity, Beneficiary beneficiary, Organization organization)
            throws TheEntityDoesNotExistInrdEntities, WrongQuantity, TheOrganizationDoesNotSupportTheQuantity {
        validRequestDonation(new RequestDonation(entity, quantity), beneficiary);
        organization.quantityCheck(new RequestDonation(entity, quantity));
        super.modify(entity, quantity, null, null);
    }

    private boolean validRequestDonation(RequestDonation requestDonation, Beneficiary ben) throws WrongQuantity, TheEntityDoesNotExistInrdEntities {
        if(requestDonation.getEntity().getClass() == Service.class) return true;

        switch (ben.getNoPersons()){
            case 1: // level1
                if(requestDonation.getQuantity() <= ((Material)super.get(requestDonation.getEntity().getID()).getEntity()).getLevel1()) return true ;
                else throw new WrongQuantity(((Material)requestDonation.getEntity()).getLevel1());
            case 2:
            case 3:
            case 4: // level2
                if(requestDonation.getQuantity() <= ((Material)requestDonation.getEntity()).getLevel2()) return true ;
                else throw new WrongQuantity(((Material)requestDonation.getEntity()).getLevel2());
            default: // level3
                if(requestDonation.getQuantity() <= ((Material)requestDonation.getEntity()).getLevel3()) return true ;
                else throw new WrongQuantity(((Material)requestDonation.getEntity()).getLevel3());
        }
    }

    public void commit(RequestDonation requestDonation, Beneficiary beneficiary, Organization organization)
            throws WrongQuantity, TheOrganizationDoesNotSupportTheQuantity,
            TheOrganizationDoesNotSupportTheEntity, TheEntityDoesNotExistInrdEntities{
        validRequestDonation(requestDonation, beneficiary);
        organization.quantityCheck(requestDonation);
        organization.removeCurrentDonationQuantity(requestDonation);
        super.remove(requestDonation);
        beneficiary.addReceivedList(requestDonation, organization);
    }
}
