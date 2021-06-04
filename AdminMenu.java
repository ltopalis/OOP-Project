import java.util.Scanner;
import java.util.InputMismatchException;

public class AdminMenu {

    public boolean adminMenu(Scanner sc, Admin admin, Organization org){
        try{
            System.out.println(" ================= KENTRIKO MENOY ==================");
            System.out.println("1. View");
            System.out.println("2. Monitor Organization");
            // =================================
            System.out.println("3. Log Out");
            System.out.println("4. Exit");
            // =================================
            System.out.print("Επιλογή; ");
            int choice = AdminChoice(sc);
            System.out.println("=======================================");


            switch(choice){
                case 1:
                    this.view(sc, admin, org);
                    break;

                case 2:
                    this.monitorOrganization(sc, admin, org);
                    break;

                case 3:
                    return false;

                case 4:
                    System.out.print("Bye bye!");
                    System.exit(0);
                default:
                   System.out.println("Λάθος είσοδος. Ξαναπροσπαθήστε!");

            }



        }catch(InputMismatchException e){
            System.out.println("Λάθος επιλογή. Ξαναπροσπαθήστε!");
        }catch(WrongInput e){
            System.out.println(e);
        }

        return true;
    }
    
    private int AdminChoice(Scanner sc) throws InputMismatchException, WrongInput{
        int Choice;

        Choice = sc.nextInt();

        // INPUT OUT OF BOUNDS CHECK
        if(Choice<1 || Choice>5)
            throw new WrongInput();
        else
            return Choice;
    }

    // #1 VIEW:
    private void view(Scanner sc, Admin admin, Organization org) throws WrongInput{
        // show the categories first:
        System.out.println("Παρακαλώ επιλέξτε είδος:");
        System.out.println("1. MATERIALS");
        System.out.println("2. SERVICES");
        System.out.println("3. ΑΚΥΡΩΣΗ");
        // get input:
        int choice = sc.nextInt();
        String selectedItem;
        switch(choice){
            // MATERIALS:
            case 1:
                System.out.println(org.listMaterials());
                /* ^ για όλα τα στοιχεία τύπου Material εμφάνισε <ονομα του Entity> (<ποσότητα>)*/
                System.out.println("Διαλέξτε ένα αγαθό ή πληκτρολογήστε e για μετάβαση στο αρχικό μενού");
                selectedItem = sc.next();
                if(selectedItem.equalsIgnoreCase("e")) return;

                System.out.println(org.getMaterialList().getRdEntities().get(Integer.parseInt(selectedItem)-1));
                break;
            // SERVICES:
            case 2:
                System.out.println(org.listServices());
                /* ^ για όλα τα στοιχεία τύπου Service εμφάνισε <ονομα του Entity> (<ποσότητα>)*/
                System.out.println("Διαλέξτε μια παροχή ή πληκτρολογήστε e για μετάβαση στο αρχικό μενού");
                selectedItem = sc.next();
                if(selectedItem.equalsIgnoreCase("e")) return;

                System.out.println(org.getServiceList().getRdEntities().get(Integer.parseInt(selectedItem)-1));
                break;
            case 3:
                return; // ends view method
            default:
                throw new WrongInput();
        }
    }

    // #2 MONITOR ORGANIZATION:
    private void monitorOrganization(Scanner sc, Admin admin, Organization org){
        try {
            System.out.println("Παρακαλώ επιλέξτε την ενέργεια που επιθυμείτε να εκτελέσετε:");
            System.out.println("1. LIST BENEFICIARIES");
            System.out.println("2. LIST DONATORS");
            System.out.println("3. RESET BENEFICIARIES' LISTS");
            System.out.println("4. ΑΚΥΡΩΣΗ");
            // get input:
            int choice = sc.nextInt();
            switch (choice) {
                case 1: // LIST BENEFICIARIES:
                    System.out.println("1. Τρέχουσα κατάσταση των προιόντων που έχει πάρει ένας εποφελούμενος");
                    System.out.println("2. Εκκαθάριση της λίστας αγαθών που έχει λάβει ένας εποφελούμενος");
                    System.out.println("3. Διαγραφή εποφελούμενου");
                    System.out.println("4. Πίσω");
                    System.out.print("Επιλογη; ");
                    int menuChoice = sc.nextInt();
                    if (menuChoice==4) break; // BACK
                    System.out.println(org.listBeneficiary());
                    System.out.print("Επιλογή εποφελούμενου ");
                    int beneficiaryChoice = sc.nextInt();
                    if(beneficiaryChoice<0 || beneficiaryChoice > org.beneficiaryListSize())
                        throw new WrongInput();
                    beneficiaryChoice--;
                    switch(menuChoice){
                        case 1: // Τρέχουσα κατάσταση προιόντων που έχει λάβει
                            System.out.println(org.getBeneficiaryList().get(beneficiaryChoice).getRequestsList().monitor());
                            break;
                        case 2: // reset ReceivedList
                            System.out.print("Είστε σίγουροι ότι θέλετε να διαγράψετε όλα τα αγαθά που έχει λάβει ο εποφελούμενος;(y/n) ");
                            String s = sc.next();
                            if(!(s.equalsIgnoreCase("n") || s.equalsIgnoreCase("y")))
                                throw new WrongInput();
                            if(s.equalsIgnoreCase("y")){
                                org.getBeneficiaryList().get(beneficiaryChoice).resetReceivedList();
                                System.out.println("Η λίστα διαγράφτηκε επιτυχώς");
                            }
                            break;
                        case 3: // Delete a Beneficiary
                            System.out.print("ΠΡΟΣΟΧΗ: Θα θέλατε να διαγράψετε τον συγκεκρτιμένο Εποφελούμενο απο τον οργανισμό;(y/n) ");
                            s = sc.next();
                            if(!(s.equalsIgnoreCase("n") || s.equalsIgnoreCase("y")))
                                throw new WrongInput();
                            if(s.equalsIgnoreCase("y"))
                                org.removeBeneficiary(org.getBeneficiaryList().get(beneficiaryChoice));
                            break;
                        default:
                            throw new WrongInput();
                    }
                    break;
                case 2: // LIST DONATORS:
                    System.out.println("1. Τρέχουσα κατάσταση των προιόντων που προτίθεται να δώσει ένας δωτητής");
                    System.out.println("2. Διαγραφή δωρητή");
                    System.out.println("3. Πίσω");
                    System.out.print("Επιλογη; ");
                    menuChoice = sc.nextInt();
                    if (menuChoice==3) break; // BACK
                    System.out.println(org.listDonator());
                    System.out.print("Επιλογή δωρητή ");
                    int donatorChoice = sc.nextInt();
                    if(donatorChoice<0 || donatorChoice > org.donatorListSize())
                        throw new WrongInput();

                    switch(menuChoice){
                        case 1: // Λίστα δωρεών
                            System.out.println(org.getDonatorList().get(donatorChoice).getOfferList().monitor());
                            break;
                        case 2:
                            System.out.println("ΠΡΟΣΟΧΗ: Θα θέλατε να διαγράψετε τον συγκεκρτιμένο Δωρητή απο τον οργανισμό;(n/y) ");
                            String answer = sc.next();
                            if (!(answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("y")))
                                throw new WrongInput();
                            if(answer.equalsIgnoreCase("y")) {
                                org.removeDonator(org.getDonatorList().get(donatorChoice));
                                System.out.println("Αφαιρέθηκε επιτυχώς!");
                            }
                    }
                    break;
                case 3: //RESET BENEFICIARIES' LISTS:
                    System.out.println("ΠΡΟΣΟΧΗ: Θα θέλατε να διαγράψετε την λίστα των προιόντων όλων των εποφελούμενων;(n/y) ");
                    String answer = sc.next();
                    if (!(answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("y")))
                        throw new WrongInput();
                    if(answer.equalsIgnoreCase("y")){
                        for(int i=0; i< org.beneficiaryListSize(); i++)
                            org.getBeneficiaryList().get(i).resetReceivedList();
                        System.out.println("Η διαγραφή εκτελέσθηκε επιτυχώς!");
                    }
                    break;
                case 4:
                    break; // terminates MONITOR ORGANIZATION method
                default:
                    System.out.println("Λάθος επιλογή. Ξαναπροσπαθήστε!");
            }// end switch-choice
        }catch (InputMismatchException e){
            System.out.println(e);
        }catch (WrongInput e){
            System.out.println(e);
        }catch (Exception e){
            System.out.println(e);
        }
    }// end method

// ======================================================================

}
