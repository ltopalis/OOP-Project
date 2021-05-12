import java.util.ArrayList;

public class Donator extends User{   
  private ArrayList<Offers> offersList = new ArrayList<Offers>();
  
  public Donator(String name, String phone){
    super(name, phone);     
  }
  
  public void AddOffersList(Offers OffersList) {offersList.add(OffersList);}
  
  public Offers getOFFERList(int elem) {return offersList.get(elem);} // Η get() ρίχνει IndexOutOfBoundsException
  

  public boolean removeElemFromOffersList(int elem) {
    try{
      offersList.remove(elem);
    }
    catch (IndexOutOfBoundsException iBoundsException){
      System.out.println(iBoundsException); // Τις εξαιρέσεις να τις βγάζουμε καλύτερα στην error stream (System.err.println(iBoundsException))
      return false;
    }
    return true;
  } 

}
