public class WrongQuantity extends OurExceptions {
    public WrongQuantity(){
        super("Η ποσότητα που ζητάς δεν είναι λάθος");
    }

    public WrongQuantity(String msg){
        super(msg);
    }

    public WrongQuantity(double level){
        super("Μπορείτε να λάβετε μέχρι " + level);
    }
}
