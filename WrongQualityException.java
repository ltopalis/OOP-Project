public class WrongQuantityException extends OurExceptions {
    public WrongQuantityException(){
        super("The quantity you desire is invalid");
    }

    public WrongQuantityException(String msg){
        super(msg);
    }    
}
