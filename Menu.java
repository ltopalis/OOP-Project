import java.util.Scanner;

public class Menu {
    public void generalMenu(Scanner sc, User user, Organization organization){
        BeneficiaryMenu beneficiaryMenu = new BeneficiaryMenu();
        DonatorMenu donatorMenu = new DonatorMenu();
        AdminMenu adminMenu = new AdminMenu();

        //Εμφανίζει χαιρετισμό, τα στοιχεία του χρήστη και ένδειξη ότι είναι Admin.
        System.out.println("----- Καλώς Ορίσατε " + user.getName() + "! -----");
        System.out.println("Τηλ: " + user.getPhone());
        System.out.print((user instanceof Admin) ? "----- Έχετε συνδεθεί ως διαχειριστής. -----\n" : "");
        System.out.println("Οργανισμός: " + organization.getName());
        // -----------------------------------------------------------------------------------
        boolean check = true;

        while (check) {
            if (user instanceof Beneficiary)
                check = beneficiaryMenu.beneficiaryMenu(sc, (Beneficiary) user, organization);
            else if (user instanceof Donator)
                check = donatorMenu.donatorMenu(sc, (Donator) user, organization);
            else
                check = adminMenu.AdminMenu(sc, (Admin) user, organization);
        }
    }
}
