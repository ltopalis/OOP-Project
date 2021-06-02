public class Admin extends User{
    private boolean isAdmin = true;

    public Admin(String name, String phone)
    {
        super(name, phone);
    }

    public boolean getisAdmin() {return isAdmin;}
}
