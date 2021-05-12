public class ThereIsNotSuchElementException extends OurExceptions{
    public ThereIsNotSuchElementException(){
        super("There isn't such an Entity in the list!");
    }

    public ThereIsNotSuchElementException(int id){
        super("There isn't an Entity with ID: " + id + " in the list!");
    }

    public ThereIsNotSuchElementException(String msg){super(msg);}

}
