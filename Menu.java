import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu{
 public Menu(){}
 //============================ ΔΕΝ ΤΟ ΕΧΩ ΤΡΕΞΕΙ ΑΚΟΜΗ ΚΑΙ ΜΠΟΡΕΙ ΝΑ ΑΛΛΑΞΕΙ =====================
 public void AdminMenu(Scanner sc, Admin admin, Organization org){
	//Εμφανίζει χαιρετισμό, τα στοιχεία του χρήστη και ένδειξη ότι είναι Admin.
	System.out.println("----- Καλώς Ορίσατε " + admin.getName() + "! -----");
	System.out.println("Τηλ: " + admin.getPhone());
	System.out.println("----- Έχετε συνδεθεί ως διαχειριστής. -----");
	System.out.println("Οργανισμός: " + org.getName());
	// -----------------------------------------------------------------------------------
	try{
		int choice = AdminChoice(sc);
		System.out.println(choice);

		switch(choice){
                case 1:
                System.out.println("Choice 1");
                this.view(sc, admin, org);
                break;

                case 2:
                System.out.println("Choice 2");
                this.monitorOrganization(sc, admin, org);
                break;

                case 3:
                System.out.println("Choice 3");
                break;

                case 4:
                    System.out.print("Bye bye!");
                    System.exit(0);
                default:
                System.out.println(" ================= KENTRIKO MENOY ==================");
                System.out.println("1. View");
        		System.out.println("2. Monitor Organization");
        		// =================================
        		System.out.println("3. Log Out");
        		System.out.println("4. Exit");
        		// =================================
        		System.out.print("Επιλογή? ");
        		System("=======================================");

            }



        }catch(InputMismatchException e){
            System.err.println("Λάθος επιλογή. Ξαναπροσπαθήστε!");
    }
}
private int AdminChoice(Scanner sc) throws InputMismatchException{
	int Choice;
        System.out.println("1. View");
        System.out.println("2. Monitor Organization");
        // =================================
        /*System.out.println("3. Back");*/
        System.out.println("3. Log Out");
        System.out.println("4. Exit");
        // =================================
        System.out.print("Επιλογή? ");

        // INCORRECT INPUT TYPE CHECK 
        if (sc.hasNextInt())
        	Choice = sc.nextInt();
        else 
        	throw new InputMismatchException();
        // INPUT OUT OF BOUNDS CHECK 
        if(Choice<1 || Choice>5) 
        	throw new InputMismatchException();
        else 
        	return Choice;
}

// #1 VIEW:
private void view(Scanner sc, Admin admin, Organization org){
	// show the categories first:
	System.out.println("Παρακαλώ επιλέξτε είδος:");
	System.out.println("1. MATERIALS");
	System.out.println("2. SERVICES");
	System.out.println("3. ΑΚΥΡΩΣΗ");
	// get input:
	int choice;
	String selectedItem;
	switch(choice){
		// MATERIALS:
		case 1: 
			for(int i = 0; i < org.getCurrentDonations().listSize(); i++){
				if(org.getCurrentDonations().getArrayList().get(i) instanceof Material)
					System.out.println(org.getCurrentDonations().getArrayList().get(i),getEntity().getName() + " (" 
						+ org.getCurrentDonations().getArrayList().get(i).getQuantity() + ")");
						/* ^ για όλα τα στοιχεία τύπου Material εμφάνισε <ονομα του Entity> (<ποσότητα>)*/
			}// end for loop #1
			System.out.println("Εισάγετε το όνομα του <MATERIAL> όπως αυτό εμφανίζεται στην οθόνη για να εμφανίσετε περισσότερες πληροφορίες...");
			System.out.println("ειδάλως");
			System.out.println("Πατήστε <ENTER> για να μεταβείτε στο αρχικό μενού."); 
			selectedItem = (sc.nextLine()).trim();
			sc.close();

			for (int i = 0; i <org.getCurrentDonations().listSize(); i++) {
				if(selectedItem == org.getCurrentDonations().getArrayList().get(i).getEntity().getName())
					System.out.println(org.getCurrentDonations().getArrayList().get(i).getEntityInfo());
				else 
					System.out.println("Δεν βρέθηκε...");
			}// end for loop #2
			break;
		// SERVICES:
		case 2:
			for(int i = 0; i < org.getCurrentDonations().listSize(); i++){
				if(org.getCurrentDonations().getArrayList().get(i) instanceof Service)
					System.out.println(org.getCurrentDonations().getArrayList().get(i),getEntity().getName() + " (" 
						+ org.getCurrentDonations().getArrayList().get(i).getQuantity() + ")");
						/* ^ για όλα τα στοιχεία τύπου Service εμφάνισε <ονομα του Entity> (<ποσότητα>)*/
			}// end for loop #1
			System.out.println("Εισάγετε το όνομα του <SERVICE> όπως αυτό εμφανίζεται στην οθόνη για να εμφανίσετε περισσότερες πληροφορίες...");
			selectedItem = (sc.nextLine()).trim();
			sc.close();

			for (int i = 0; i <org.getCurrentDonations().listSize(); i++) {
				if(selectedItem == org.getCurrentDonations().getArrayList().get(i).getEntity().getName())
					System.out.println(org.getCurrentDonations().getArrayList().get(i).getEntityInfo());
				else 
					System.out.println("Δεν βρέθηκε...");
			}// end for loop #2
			break;

		case 3:
			return; // ends view method

		default:
			System.err.println("Λάθος επιλογή. Ξαναπροσπαθήστε!");
			System.out.println("----------------------------------");
			System.out.println("Παρακαλώ επιλέξτε είδος:");
			System.out.println("1. MATERIALS");
			System.out.println("2. SERVICES");
			System.out.println("3. ΑΚΥΡΩΣΗ");
			System.out.println("----------------------------------");
			continue;
		}
}

// #2 MONITOR ORGANIZATION:
private void monitorOrganization(Scanner sc, Admin admin, Organization org){
	System.out.println("Παρακαλώ επιλέξτε την ενέργεια που επιθυμείτε να εκτελέσετε:");
	System.out.println("1. LIST BENEFICIARIES");
	System.out.println("2. LIST DONATORS");
	System.out.println("3. RESET BENEFICIARIES' LISTS");
	System.out.println("4. ΑΚΥΡΩΣΗ");
	// get input:
	int choice;
	int indexBen; // shows the index (the place) of the beneficiary inside organizations beneficiaryList (ex. 3. Josh, indexBen = 3)
	switch(choice){
		// LIST BENEFICIARIES:
		
	case 1:
		org.listBeneficaries();
		System.out.println("Επιλογή <BENEFICIARY>. Εισάγετε την ΑΡΙΘΜΗΣΗ του εποφελούμενου που θέλετε να επιλέξετε...");
		try{
			indexBen = sc.nextInt();
		}
		catch (InputMismatchException e){
			System.err.println("Λάθος επιλογή.");
			return; // close monitor organization method
		}
			if ((indexBen < org.getBeneficiaryList().size()) && (indexBen >= 0)) {
				Beneficiary selectedBen = org.getBeneficiaryList().get(i); // temporary reference to the selected beneficiary 
				System.out.println("Συνολικές Παροχές:");
 				for(int j = 0; j < org.getBeneficiaryList().get(i).getReceivedList().listSize(); j++){
					System.out.println(selectedBen.getReceivedList().getArrayList().get(j).getEntity().getEntityInfo() + " (" +selectedBen.getReceivedList().getArrayList().get(j).getQuantity() + ")"); // prints out each received list (entity quantity)
				}// end receivedList loop	
			}// end if statement
			else 
				System.out.println("Δεν βρέθηκε...");

		System.out.println("Παρακαλώ επιλέξτε την ενέργεια που επιθυμείτε να εκτελέσετε:");
		System.out.println("1. ΚΑΘΑΡΙΣΜΟΣ ΛΙΣΤΑΣ");
		System.out.println("2. ΔΙΑΓΡΑΦΗ ΑΠΟ ΤΟΝ ΟΡΓΑΝΙΣΜΟ");
		System.out.println("3. ΑΚΥΡΩΣΗ");

		int benChoice;
		switch (benChoice){
			// ΚΑΘΑΡΙΣΜΟΣ ΛΙΣΤΑΣ:
			case 1: 
				selectedBen.getReceivedList().reset();
				break;
			// ΔΙΑΓΡΑΦΗ ΑΠΟ ΤΟΝ ΟΡΓΑΝΙΣΜΟ:
			case 2:
				System.out.println("ΠΡΟΣΟΧΗ: Θα θέλατε να διαγράψετε τον συγκεκρτιμένο Εποφελούμενο απο τον οργανισμό;");
				System.out.println("( y / n )");
				if ((answer = "y") || (answer == "Y")) {
					org.removeBeneficiary(selectedBen);
				}
				else 
				System.out.println("Η αφαίρεση ακυρώθηκε...");
				break;
			case 3:
				break; 
			default:
				System.err.println("Λάθος επιλογή. Ξαναπροσπαθήστε!");
		        System.out.println("----------------------------------");
				System.out.println("1. ΚΑΘΑΡΙΣΜΟΣ ΛΙΣΤΑΣ");
				System.out.println("2. ΔΙΑΓΡΑΦΗ ΑΠΟ ΤΟΝ ΟΡΓΑΝΙΣΜΟ");
				System.out.println("3. ΑΚΥΡΩΣΗ");
				System.out.println("----------------------------------");
			continue;
		}// end switch-benChoice
		break; // case 1 break. 

			// LIST DONATORS:
		int indexDon;// shows the index (the place) of the donator inside organizations donatorList (ex. 3. Josh, indexBen = 3)
	case 2:
			org.listDonators();
			System.out.println("Επιλογή <DONATOR>. Εισάγετε την ΑΡΙΘΜΗΣΗ του δωρητή που θέλετε να επιλέξετε...");
		try{
			indexDon = sc.nextInt();
		}
		catch (InputMismatchException e){
			System.err.println("Λάθος επιλογή.");
			return; // close monitor organization method
		}
		if ((indexDon < org.getDonatorList().size()) && (indexDon >= 0)) {
			Donator selectedDon = org.getDonatorList().get(i);// temporary reference to the selected Donator
			System.out.println("Συνολικές Προσφερόμενες Παροχές:");
			for (int i = 0; i < selectedDon.getOffersList().listSize(); i++) {
				System.out.println(selectedDon.getOffersList().getArrayList().get(i).getEntity().getName() + selectedDon.getOffersList().getArrayList().get(i).getQuantity());
			}// end for

		}//end if
	
		System.out.println("ΠΡΟΣΟΧΗ: Θα θέλατε να διαγράψετε τον συγκεκρτιμένο Δωρητή απο τον οργανισμό;");
		System.out.println("( y / n )");

		String answer = (sc.nextLine()).trim();
		if ((answer = "y") || (answer == "Y")){
			org.removeDonator(selectedDon);
			System.out.println("Αφαιρέθηκε επιτυχώς!");
		}
		else 
			System.out.println("Η αφαίρεση ακυρώθηκε...");
		break;
		//RESET BENEFICIARIES' LISTS:
	case 3:
		for (int i = 0; i < org.getBeneficiaryList().size(); i++) {
				org.getBeneficiaryList().get(i).getReceivedList().reset(); // resets each idividual (i) beneficiary's receivedList
		}
		System.out.println("Η διαγραφή εκτελέσθηκε επιτυχώς!");
		break;
	case 4:
		break; // terminates MONITOR ORGANIZATION method
	default:
		System.err.println("Λάθος επιλογή. Ξαναπροσπαθήστε!");
		System.out.println("----------------------------------");
		System.out.println("1. LIST BENEFICIARIES");
		System.out.println("2. LIST DONATORS");
		System.out.println("3. RESET BENEFICIARIES' LISTS");
		System.out.println("4. ΑΚΥΡΩΣΗ");
		System.out.println("----------------------------------");

		continue;
	}// end switch-choice
}// end method

// ======================================================================

    
     public void DonatorMenu(Scanner sc, Donator donator, Organization organization){
        AddOffer(sc, organization, donator);
    }


    private void AddOffer(Scanner sc, Organization organization, Donator donator){
        System.out.println("1. Material");
        System.out.println("2. Service");
        System.out.println("Επιλογή? ");
        int choice = sc.nextInt();

        switch(choice){
            case 1: // Materials
            



            break;
            case 2: // Services
            



            break;
            default: // For instance choice == 5 



            
        }
    }



    public void BeneficiaryMenu(Scanner sc, Beneficiary beneficiary, Organization organization){
        try{
            int choice = BeneficiaryChoice(sc);
            System.out.println(choice);

            switch(choice){
                case 1:
                    System.out.println("Choice 1");
                    break;
                case 2:
                System.out.println("Choice 2");
                break;

                case 3:
                System.out.println("Choice 3");
                break;

                case 4:
                System.out.println("Choice 4");
                break;

                case 5:
                System.out.println("Choice 5");
                break;


                case 6:
                    System.out.print("Bye bye!");
                    System.exit(0);
            }



        }catch(InputMismatchException e){
            System.err.println("Λάθος επιλογή. Ξαναπροσπαθήστε!");
        }

    }

    private int BeneficiaryChoice(Scanner sc) throws InputMismatchException{
        int Choice;
        System.out.println("1. Αdd Offer");
        System.out.println("2. Show Offers");
        System.out.println("3. Commit");
        System.out.println("4. Back");
        System.out.println("5. Log Out");
        System.out.println("6. Exit");
        System.out.print("Επιλογή? ");
        
        if(sc.hasNextInt()) Choice = sc.nextInt();
        else throw new InputMismatchException();

        if(Choice<1 || Choice>6) throw new InputMismatchException();
        else return Choice;
    }





}
