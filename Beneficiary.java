import java.util.ArrayList;

public class Beneficiary extends User{
  private int noPersons = 1;
  private ArrayList<RequestDonation>receivedList = new ArrayList<RequestDonation>();
  private ArrayList<Requests>requestsList = new ArrayList<Requests>();
    
  public Beneficiary(String name, String phone, int noPersons)
  {
    super(name, phone);
    this.noPersons = noPersons;
  }
    
  public void setNoPersons(int noPersons) {this.noPersons = noPersons;}
  public int getNoPersons(){return noPersons;}
  public int size(){return receivedList.size();}
  
  public void setAddReceivedList(RequestDonation ReceivedList) {receivedList.add(ReceivedList);} // Η add() επιστρέφει true αν όλα έχουν πάει καλά
  public RequestDonation getAddReceivedList(int elem) {return receivedList.get(elem);} // IndexOutOfBoundsException
  public boolean removeElementFromReceivedlist(int elem) {
    try{
      receivedList.remove(elem);
    }
    catch (IndexOutOfBoundsException e){
      System.out.println(e);
      return false;
    }
    return true;
  }
  public boolean setAddRequestsList(Requests RequestsList) {return requestsList.add(RequestsList);} // Η add() επιστρέφει true αν όλα έχουν πάει καλά
  public Requests getAddRequestsList(int elem) {return requestsList.get(elem);} 
   
public boolean removeElemFromRequestsList(int elem) {
    try{
      requestsList.remove(elem);
    }
    catch (IndexOutOfBoundsException iBoundsException){
      System.out.println(iBoundsException);
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
      String s = getName() + " " + getPhone() + "\n" ;

      for(int i = 0; i<receivedList.size(); i++)
        s += receivedList.get(i).getEntity().getID() + " " + receivedList.get(i).getEntity().getName() +
         " (" + receivedList.get(i).getQuantity() + ")\n";

      return s;
  } 
  
  // Nikos 694444444
  // 55 kfdshksla (55)
  // 

  // ID name (Quality)
}
