public class Offers extends RequestDonationList {
    public boolean commit(Organization organization){
        try{
            for(int i = 0; i<super.getArrayList().size(); i++){
                organization.addDonation(getArrayList().get(i));
                if(!(super.remove(i))) return false;
            }
            return true;
        }catch(Exception e){
            System.err.println(e);
            return false;
        }
    }
}
