public class TheEntityDoesntExistInCompanyListException extends OurExceptions{
    public TheEntityDoesntExistInCompanyListException(){
        super("The Entity you try to add is not supported by the company!");
    }
}
