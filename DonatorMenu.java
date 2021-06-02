import java.util.InputMismatchException;
import java.util.Scanner;

public class DonatorMenu {
    private void AddOffer(Scanner sc, Donator donator, Organization organization){
        try{
            System.out.println("1. Material");
            System.out.println("2. Services");
            System.out.println("3. Πίσω");
            System.out.print("Ποια κατηγορία σας ενδιαφέρει; ");
            int choice = sc.nextInt();

            switch(choice) {
                case 1: // Material
                    System.out.println(organization.listMaterials());
                    System.out.print("Επιλογή; ");
                    int materialChoice = sc.nextInt();
                    if(materialChoice > organization.materialListSize() || materialChoice < 0)
                        throw new WrongInput();
                    System.out.println(organization.getMaterialList().getRdEntities().get(materialChoice-1));
                    System.out.print("Θα σας ενδιέφερε να το προσφέρετε;(y/n) ");
                    String s = sc.next();
                    if(!(s.equals("n") || s.equals("y")))
                        throw new WrongInput();
                    if(s.equals("y")) {
                        System.out.println("Πόση ποσότητα επιθυμείτε να προσφέρετε; ");
                        double quantity = sc.nextDouble();
                        if (quantity < 0)
                            throw new WrongInput();
                        donator.addAnOffer(new RequestDonation(organization.getMaterialList().getRdEntities().get(materialChoice - 1).getEntity(), quantity), organization);
                        System.out.println("To αγαθό προστέθηκε επιτυχώς στην λίστα σας");
                    }
                    break;
                case 2:
                    System.out.println(organization.listServices());
                    System.out.print("Επιλογή; ");
                    int serviceChoice = sc.nextInt();
                    if(serviceChoice > organization.serviceListSize() || serviceChoice < 0)
                        throw new WrongInput();
                    System.out.println(organization.getServiceList().getRdEntities().get(serviceChoice-1));
                    System.out.print("Θα σας ενδιέφερε να το προσφέρετε;(y/n) ");
                    String k = sc.next();
                    if(!(k.equals("n") || k.equals("y")))
                        throw new WrongInput();
                    if(k.equals("y")) {
                        System.out.print("Πόσες ώρες επιθυμείτε να προσφέρετε; ");
                        double quantity = sc.nextDouble();
                        if (quantity < 0)
                            throw new WrongInput();
                        donator.addAnOffer(new RequestDonation(organization.getServiceList().getRdEntities().get(serviceChoice - 1).getEntity(), quantity), organization);
                        System.out.println("To αγαθό προστέθηκε επιτυχώς στην λίστα σας");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Λάθος επιλογή");
            }
        }catch (InputMismatchException e){
            System.out.println("Λάθος επιλογή");
        }catch (WrongInput e){
            System.out.println(e);
        } catch (TheOrganizationDoesNotSupportTheQuantity theOrganizationDoesNotSupportTheQuantity) {
            theOrganizationDoesNotSupportTheQuantity.printStackTrace();
        } catch (WrongQuantity wrongQuantity) {
            wrongQuantity.printStackTrace();
        } catch (TheOrganizationDoesNotSupportTheEntity theOrganizationDoesNotSupportTheEntity) {
            theOrganizationDoesNotSupportTheEntity.printStackTrace();
        } catch (TheEntityDoesNotExistInrdEntities theEntityDoesNotExistInrdEntities) {
            theEntityDoesNotExistInrdEntities.printStackTrace();
        }
    }

    private void showOffers(Scanner sc, Donator donator, Organization organization){
        try {
            System.out.println(donator.monitorAllOffers());
            if(donator.isEmpty()) return;
            System.out.println("\n1. Τροποποίηση λίστας");
            System.out.println("2. Καθαρισμός όλων των παροχών");
            System.out.println("3. Καταχώρηση");
            System.out.println("4. Πίσω");
            System.out.print("Επιλογή; ");
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    System.out.print("Επιλέξτε ένα είδος ");
                    choice = sc.nextInt();
                    if(choice<0 || choice > (donator.getOfferList().getRdEntities().size()+1)) throw new WrongInput();
                    int elem = choice-1;
                    System.out.println(donator.getOfferList().getRdEntities().get(elem));
                    System.out.println("1. Διαγραφή παροχής");
                    System.out.println("2. Τροποποίηση ποσότητας");
                    System.out.println("3. Πίσω");
                    System.out.print("Επιλογή; ");
                    choice = sc.nextInt();
                    if(choice<0 || choice>3) throw new WrongInput();
                    if(choice == 1) {
                        donator.removeAnOffer(donator.getOfferList().getRdEntities().get(elem));
                        System.out.println("Επιτυχής διαγραφή");
                    }
                    else if(choice==2){
                        System.out.print("Δώστε την νέα ποσότητα ");
                        double quantity = sc.nextDouble();
                        if(quantity<0) throw new WrongInput();
                        donator.modifyAnOffer(elem, quantity);
                        System.out.println("Επιτυχής τροποποίηση");
                    }else
                        break;
                    break;
                case 2:
                    System.out.print("Είστε σίγουροι ότι θέλετε να διαγράψετε όλες τις παροχέ σας;(y/n) ");
                    String s = sc.next();
                    if(!(s.equals("n") || s.equals("y"))) throw new WrongInput();
                    if(s.equals("y")) {
                        donator.resetTheList();
                        System.out.println("Η λίστα διαγράφτηκε με επιτυχία");
                    }
                    break;
                case 3:
                    System.out.print("Είστε σίγουροι ότι θέλετε να υποβάλετε τις παροχέ σας;(y/n) ");
                    s = sc.next();
                    if(!(s.equals("n") || s.equals("y"))) throw new WrongInput();
                    if(s.equals("y")) {
                        donator.commitTheOffer(organization);
                        System.out.println("Οι παροχές υποβλήθηκαν με επιτυχία");
                    }
                    break;
                case 4:
                    return;
                default:
                    throw new WrongInput();
            }
        }catch (InputMismatchException e){
            System.out.println(e);
        }catch (WrongInput e){
            System.out.println(e);
        } catch (TheEntityDoesNotExistInrdEntities theEntityDoesNotExistInrdEntities) {
            theEntityDoesNotExistInrdEntities.printStackTrace();
        } catch (TheOrganizationDoesNotSupportTheQuantity theOrganizationDoesNotSupportTheQuantity) {
            theOrganizationDoesNotSupportTheQuantity.printStackTrace();
        } catch (WrongQuantity wrongQuantity) {
            wrongQuantity.printStackTrace();
        } catch (TheOrganizationDoesNotSupportTheEntity theOrganizationDoesNotSupportTheEntity) {
            theOrganizationDoesNotSupportTheEntity.printStackTrace();
        }
    }

    public boolean donatorMenu(Scanner sc, Donator donator, Organization organization){
        try {
            System.out.println("1. Προσθέστε προσφορά");
            System.out.println("2. Δείτε τις προσφορές σας");
            System.out.println("3. Υποβάλετε τις προσφορές σας");
            System.out.println("4. Αποσύνδεση Χρήστη");
            System.out.println("5. Έξοδος");
            System.out.print("Επιλογή; ");
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    AddOffer(sc, donator, organization);
                    break;
                case 2:
                    showOffers(sc, donator, organization);
                    break;
                case  3:
                    System.out.print("Είστε σίγουροι ότι θέλετε να υποβάλετε τις παροχέ σας;(y/n) ");
                    String s = sc.next();
                    if(!(s.equals("n") || s.equals("y"))) throw new WrongInput();
                    if(s.equals("y")) {
                        donator.commitTheOffer(organization);
                        System.out.println("Οι παροχές υποβλήθηκαν με επιτυχία");
                    }
                    break;
                case 4:
                    System.out.println("Αποσυνδεθήκατε επιτυχώς");
                    return false;
                case 5:
                    System.out.println("Bye bye");
                    System.exit(0);
                default:
                    throw new WrongInput();
            }
        }catch(InputMismatchException e){
            System.out.println(e);
        }catch (WrongInput e){
            System.out.println(e);
        } catch (TheOrganizationDoesNotSupportTheQuantity theOrganizationDoesNotSupportTheQuantity) {
            System.out.println(theOrganizationDoesNotSupportTheQuantity);
        } catch (WrongQuantity wrongQuantity) {
            System.out.println(wrongQuantity);
        } catch (TheOrganizationDoesNotSupportTheEntity theOrganizationDoesNotSupportTheEntity) {
            System.out.println(theOrganizationDoesNotSupportTheEntity);
        } catch (TheEntityDoesNotExistInrdEntities theEntityDoesNotExistInrdEntities) {
            theEntityDoesNotExistInrdEntities.printStackTrace();
        }
        return true;
    }
}
