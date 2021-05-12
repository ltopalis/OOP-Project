import java.util.ArrayList;

public class Beneficiary extends User{
  private int noPersons = 1;
  private ArrayList<RequestDonationList>receivedList = new ArrayList<RequestDonationList>();
  private ArrayList<Requests>requestsList = new ArrayList<Requests>();
    
  public Beneficiary(String name, String phone, int noPersons)
  {
    super(name, phone);
    this.noPersons = noPersons;
  }
    
  public void setNoPersons(int noPersons) {this.noPersons = noPersons;}
  public int getNoPersons(){return noPersons;}
  public int size(){return receivedList.size();}
  
  public void setAddReceivedList(RequestDonationList ReceivedList) {receivedList.add(ReceivedList);} // Η add() επιστρέφει true αν όλα έχουν πάει καλά
  public RequestDonationList getAddReceivedList(int elem) {return receivedList.get(elem);} // IndexOutOfBoundsException
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
  public void setAddRequestsList(Requests RequestsList) {requestsList.add(RequestsList);} // Η add() επιστρέφει true αν όλα έχουν πάει καλά
  public Requests getAddRequestsList(int elem) {return requestsList.get(elem);} // IndexOutOfBoundsException
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
}