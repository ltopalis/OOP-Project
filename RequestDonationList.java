import java.util.ArrayList;

public class RequestDonationList extends TheEntityDoesntExistInCompanyListException{
    private ArrayList<RequestDonation> rdEntities = new ArrayList<>();

    /** This method returns the number of RequestDonation in the rdEntities */
    public int listSize(){
        return rdEntities.size();
    }

    public int get(int elem){
        return rdEntities.get(elem).getEntity().getID();
    }


    /**
     *  Adds a requestDonation and check if it is supported by a company. 
     * If so the requestSonation is added in the rdEtities list.
     * return false if a problem occurs
     */
    public boolean add(RequestDonation requestDonation, RequestDonationList companyList) 
    throws TheEntityDoesntExistInCompanyListException{
        boolean flag = true;
        
        // check if the requestDonation exists in company's List
        int i = 0;
        boolean exist = false;
        while(true){
            if (requestDonation.getEntity().getID() == companyList.get(i)) {
                exist = true;
                break;
            }
            if (i < companyList.listSize()) throw new TheEntityDoesntExistInCompanyListException();
            i++;
        }
        if(!exist) return false;

        // check if the requestDonation already exists in List
        sameID: if(!rdEntities.isEmpty()){
            i = 0;
            while(i<rdEntities.size()){
                if(requestDonation.getEntity().getID() ==
                    rdEntities.get(i).getEntity().getID()){ // same IDs
                    rdEntities.get(i).addQuantity(requestDonation.getQuantity());
                    flag = !flag; // if there is an Entity with the same ID, it shouldn't be added in List
                    break sameID; // breaks the outter if when we find an entity with the same ID
                }
                i++;
            }
        }
        
        // Add an Entity only if it doesn't exist already
        if(flag) rdEntities.add(requestDonation);
        return true;
    }

    /** Removes an RequestDonation from the list according to its position in list
     * returns false if a problem occurs
     */
    public boolean remove(int elem){
        try{
            String name = rdEntities.get(elem).getEntity().getName();
                int id = rdEntities.get(elem).getEntity().getID();
                rdEntities.remove(elem);
                System.out.println("Το στοιχείο " + name + " με κωδικό " + id + " αφαιρέθηκε επιτυχώς!");
            return true;
        }catch(IndexOutOfBoundsException e){
            System.err.println(e);
            return false;
        }
    }

    /** Removes an RequestDonation from the list according to the ID of the Entity
     * returns false if a problem occurs
     */
    public boolean remove(Entity elem) throws ThereIsNotSuchElementException{
        for(int i=0; i<rdEntities.size(); i++)
            if(elem.getID() == rdEntities.get(i).getEntity().getID()){
                String name = rdEntities.get(i).getEntity().getName();
                int id = rdEntities.get(i).getEntity().getID();
                rdEntities.remove(i);
                System.out.println("Το στοιχείο " + name + " με κωδικό " + id + " αφαιρέθηκε επιτυχώς!");
                return true;
            }

        throw new ThereIsNotSuchElementException(elem.getID());
    }


    /** Modify the quantity of an Entity from the list according to its position in list
     * returns false if a problem occurs
     */
    public boolean modify(int position, double quantity){
        try{
            rdEntities.get(position).setQuantity(quantity);
            return true;
        }catch(IndexOutOfBoundsException e){
            System.err.println("Wrong index. Try again!");
            return false;
        }
    }

    /** Modify the quantity of an Entity from the list according to the ID of the Entity
     * returns false if a problem occurs
     */
    public boolean modify(Entity entity, double quantity) throws ThereIsNotSuchElementException{
        try{
            for(int i=0; i<rdEntities.size(); i++)
                if(entity.getID() == rdEntities.get(i).getEntity().getID()){
                    rdEntities.get(i).setQuantity(quantity);
                    return true;
                }
            throw new ThereIsNotSuchElementException();
        }catch(ThereIsNotSuchElementException e){
            System.err.println(entity.getID());
            return false;
        }
    }


    /** returns the number of Entities in the List, their names and their quantity */
    public String monitor(){
        String s = "Υπάρχουν " + rdEntities.size() + " στοιχεία στην λίστα\n";
        s += "=".repeat(s.length());
        s += "\n";
        for(int i=0; i<rdEntities.size(); i++)
            s += i+1 + ". " + rdEntities.get(i).getEntity().getName() + 
            "\t(Ποσότητα: " + rdEntities.get(i).getQuantity() + ")\n";
        s += "=".repeat(s.length());
        return s;
    }

    /** Deletes everythig from the list */
    public boolean reset(){
        try{
            for(int i=rdEntities.size(); i>=0; i--){
                String name = rdEntities.get(i).getEntity().getName();
                int id = rdEntities.get(i).getEntity().getID();
                rdEntities.remove(i);
                System.out.println("Το στοιχείο " + name + " με κωδικό " + id + " αφαιρέθηκε επιτυχώς!");
            }
        } catch(IndexOutOfBoundsException e){
            System.err.println(e);
            return false;
        }
        return true;
    }

}