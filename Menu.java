import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu{
 public Menu(){}
    
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
