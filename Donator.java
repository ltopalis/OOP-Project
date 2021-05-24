import java.util.ArrayList;

public class Donator extends User{   
  private ArrayList<Offers> offersList = new ArrayList<Offers>();
  aimport java.util.ArrayList;
public class Donator extends User{   
  private ArrayList<Offers> offersList = new ArrayList<Offers>();
  
  public Donator(String name, String phone){
    super(name, phone);     
  }
  
  public void AddOffersList(Offers OffersList) {offersList.add(OffersList);}
  
  public Offers getOFFERList(int elem) { // Η get() ρίχνει IndexOutOfBoundsException
  try{
     return offersList.get(elem);//γτ το κοκκινιζει
  } // είχες ξεχάσει να σβήσεις την προηγούμενη υλοποίηση 
  catch (IndexOutOfBoundsException e){
    System.err.println(e);
    return null; // Στην Java πρέπει σε κάθε διαδρομή να επιστρέφεται κάτι
    // Αν πίασει Exception δεν θα επιστρέψει κάτι γιαυτό σου έβγαζε λάθος.
    // Απλά έβαλα να επιστρέψει το null που σημαίνει τίποτε. Τα στοιχεία που έχει ο πίνακας σου είναι αναφορές
    // συνεπώς έπρεπε να έβαζα null ως επιστρεφόμενος τύπος.
    // Κατάλαβες? Ωραία!
  }// μαλιστα καπετανιε 
  // στη μπενεφισιαρι ελα 
}
  /* ΓΕΙΑΑΑΑΑΑΑΑ */
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
