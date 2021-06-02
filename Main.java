import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)){
            Menu menu = new Menu();
            Organization organization = new Organization("Let's get physical");
            Material milk = new Material(45, "Γάλα", "Πλήρες γάλα Μπαρμπαμπρίλιος", 1.0,2.0, 4.0);
            Material sugar = new Material(52, "Ζάχαρη", "Κρυσταλική ζάχαρη", 1.0, 1.0, 2.0);
            Material rice = new Material(71, "ρύζι", "Καρολίνα του Μονακό", 1.0, 2.0, 6.0);
            Service MedicalSupport = new Service(87, "Ιατρική βοήθεια    ",
                    "Βοήθεια στο σπίτι από φοιτητές ιατρικής, φυσικοθεράπείας και νοσηλευτικής");
            Service NurserySupport = new Service(21, "Νοσηλευτική βοήθεια", "Η γιατρέσσα Εύη στο σπίτι σας");
            Service BabySitting = new Service(35, "Βρεφική φροντίδα    ",
                    "Βαριέσαι να φροντίσεις το παιδί και θέλεις να βγείς για καφέ? Και εγώ στην θέση σου το ίδιο θα έκανα!");
            RequestDonation rd1 = new RequestDonation(milk, 5.0);
            RequestDonation rd2 = new RequestDonation(sugar, 10.0);
            RequestDonation rd3 = new RequestDonation(rice, 4.0);
            RequestDonation rd4 = new RequestDonation(MedicalSupport, 10.30);
            RequestDonation rd5 = new RequestDonation(NurserySupport, 52.2);
            RequestDonation rd6 = new RequestDonation(BabySitting, 3.50);
            Admin admin = new Admin("Κώστας", "445236547");
            Beneficiary beneficiary1 = new Beneficiary("Γιώργος", "45213665", 4);
            Beneficiary beneficiary2 = new Beneficiary("Γιάννης", "69852112", 2);
            Donator donator = new Donator("Lucas", "44536852");
            organization.addEntity(milk);
            organization.addEntity(sugar);
            organization.addEntity(rice);
            organization.addEntity(MedicalSupport);
            organization.addEntity(NurserySupport);
            organization.addEntity(BabySitting);
            organization.addCurrentDonation(rd1, null);
            organization.addCurrentDonation(rd2, null);
            organization.addCurrentDonation(rd3, null);
            organization.addCurrentDonation(rd4, null);
            organization.addCurrentDonation(rd5, null);
            organization.addCurrentDonation(rd6, null);
            organization.setAdmin(admin);
            organization.insertBeneficiary(beneficiary1);
            organization.insertBeneficiary(beneficiary2);
            organization.insertDonator(donator);
            String phoneNumber = "";
            User user;
            
            while(true) {
                try {
                    System.out.println("Γεία σας.");
                    System.out.print("Δώστε το τηλέφωνο σας (Για τερματισμό από το πρόγραμμα πληκρολογήστε e): ");
                    phoneNumber = sc.next();
                    if(phoneNumber.equals("e")) System.exit(0);
                    if (!phoneNumber.matches("[0-9]+")) throw new WrongInput(); // Ελέγχει αν ο αριθμός τηλεφώνου περιέχει μόνο αριθμούς
                    user = organization.checkAPhoneNumber(phoneNumber);

                    menu.generalMenu(sc, user, organization);
                }catch(TheUserDoesNotExist e){
                    try {
                        System.out.println(e);
                        System.out.print("Θα θέλατε να εγγραφείτε;(n/y) ");
                        String answer = sc.next();
                        if (!(answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("y")))
                            throw new WrongInput();
                        if (answer.equalsIgnoreCase("y")) {
                            System.out.print("Θα σας ενδιέφερε να εγγραφείτε ως εποφελούμενος (1) ή ως δωρητής(2); ");
                            int choice = sc.nextInt();
                            System.out.print("Δώστε το όνομά σας: ");
                            String name = sc.next();
                            switch (choice) {
                                case 1:
                                    System.out.print("Από πόσα μέλη αποτελέιται η οικογένεια σας; ");
                                    int noPersons = sc.nextInt();
                                    Beneficiary newBeneficiary = new Beneficiary(name, phoneNumber, noPersons);
                                    organization.insertBeneficiary(newBeneficiary);
                                    System.out.println("Εγγραφήκατε επιτυχώς!");
                                    menu.generalMenu(sc, newBeneficiary, organization);
                                    break;
                                case 2:
                                    Donator newDonator = new Donator(name, phoneNumber);
                                    organization.insertDonator(newDonator);
                                    System.out.println("Εγγραφήκατε επιτυχώς!");
                                    menu.generalMenu(sc, newDonator, organization);
                                    break;
                                default:
                                    throw new WrongInput();

                            }
                        }
                    } catch (WrongInput k){
                        System.out.println(k);
                    }catch (InputMismatchException k){
                        System.out.println(k);
                    }
                } catch (WrongInput e) {
                    // TODO Auto-generated catch block
                    System.out.println(e);
                }
            }




        } catch (ElementAlreadyExistsInEntityList elementAlreadyExistsInEntityList) {
            System.out.println(elementAlreadyExistsInEntityList);
        } catch (TheOrganizationDoesNotSupportTheQuantity theOrganizationDoesNotSupportTheQuantity) {
            System.out.println(theOrganizationDoesNotSupportTheQuantity);
        } catch (WrongQuantity wrongQuantity) {
            System.out.println(wrongQuantity);
        } catch (TheOrganizationDoesNotSupportTheEntity theOrganizationDoesNotSupportTheEntity) {
            System.out.println(theOrganizationDoesNotSupportTheEntity);
        } catch (TheUserAlreadyExistsInBeneficiaryList theUserAlreadyExistsInBeneficiaryList) {
            System.out.println(theUserAlreadyExistsInBeneficiaryList);
        } catch (TheUserAlreadyExistsInDonatorList theUserAlreadyExistsInDonatorList) {
            System.out.println(theUserAlreadyExistsInDonatorList);
        } catch (TheEntityDoesNotExistInrdEntities theEntityDoesNotExistInrdEntities) {
            theEntityDoesNotExistInrdEntities.printStackTrace();
        }

    }

}
