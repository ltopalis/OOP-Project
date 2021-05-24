public class Requests extends RequestDonationList{
    @Override
    public boolean add(RequestDonation requestDonation, Organization org, Beneficiary ben)
            throws TheEntityDoesntExistInCompanyListException {
                try{
                    org.checkEntityExists(requestDonation.getEntity()); // check if the Entity exists

                    if(validRequestDonation(requestDonation, ben) && 
                    requestDonation.getQuantity() <= org.getCuRequestDonation().getQuantity(requestDonation.getEntity().getID()))
                        return super.add(requestDonation, org, null);

                }catch(TheEntityDoesntExistInCompanyListException e){
                    System.err.println(e);
                    return false;
                } catch(WrongQuantityException e){
                    System.err.println(e);
                    return false;
                }
                
                return true;
    }

    @Override
    public boolean modify(int position, double quantity, Organization org, Beneficiary ben) {
        try{
            if(validRequestDonation(getArrayList().get(position), ben) && 
            getArrayList().get(position).getQuantity() <= 
            org.getCuRequestDonation().getQuantity(getArrayList().get(position).getEntity().getID()))
                return super.modify(position, quantity, null, null);
        }catch(WrongQuantityException e){
            System.err.println(e);
            return false;
        } catch(IndexOutOfBoundsException e){
            System.err.print(e);
            return false;
        }catch(Exception e){
            System.err.println("Παρουσιάστηκε πρόβλημα\n" + e);
            return false;
        }
        
        return false;        
    }

    @Override
    public boolean modify(Entity entity, double quantity, Organization org, Beneficiary ben) throws ThereIsNotSuchElementException {
        try{
            for(int i=0; i<org.getCuRequestDonation().listSize(); i++){
                if(entity.getID() == org.getCuRequestDonation().get(i)){
                    if(quantity <= org.getCuRequestDonation().getQuantity(entity.getID()) &&
                    validRequestDonation(new RequestDonation(entity, quantity), ben))
                        return super.modify(entity, quantity, null, null);
                }
                throw new ThereIsNotSuchElementException();
            }
        }catch(ThereIsNotSuchElementException e){
            System.err.println(entity.getID());
            return false;
        }catch(WrongQuantityException e){
            System.err.println(e);
            return false;
        }catch(IndexOutOfBoundsException e){
            System.err.println(e);
            return false;
        }catch(Exception e){
            System.err.println("Παρουσιάστηκε πρόβλημα\n" + e);
            return false;
        }
        return false;

    }

    public boolean validRequestDonation(RequestDonation requestDonation, Beneficiary ben) throws WrongQuantityException{
        if(requestDonation.getEntity().getClass() == Service.class) return true;

        switch (ben.getNoPersons()){
            case 1: // level1
                if(requestDonation.getQuantity() <= ((Material)requestDonation.getEntity()).getLevel1()) return true ;
                else throw new WrongQuantityException();
            case 2:
            case 3:
            case 4: // level2
                if(requestDonation.getQuantity() <= ((Material)requestDonation.getEntity()).getLevel2()) return true ;
                else throw new WrongQuantityException();
            default: // level3
                if(requestDonation.getQuantity() <= ((Material)requestDonation.getEntity()).getLevel3()) return true ;
                else throw new WrongQuantityException();
        }
    }

    public boolean commit(RequestDonation requestDonation, Organization org, Beneficiary ben){
        try{
            org.checkEntityExists(requestDonation.getEntity()); // check if the Entity exists

            if(validRequestDonation(requestDonation, ben) && 
            requestDonation.getQuantity() <= org.getCuRequestDonation().getQuantity(requestDonation.getEntity().getID())){
                org.removeDonation(requestDonation);
                super.remove(requestDonation.getEntity());
                ben.setAddReceivedList(requestDonation);
                return true;
            }

        }catch(TheEntityDoesntExistInCompanyListException e){
            System.err.println(e);
            return false;
        } catch(WrongQuantityException e){
            System.err.println(e);
            return false;
        }catch(ThereIsNotSuchElementException e){
            System.err.println(e);
            return false;
        }catch(Exception e){
            System.err.println(e);
            return false;
        }
        return true;

    }

}
