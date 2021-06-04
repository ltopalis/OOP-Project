public class RequestDonation{
    private Entity entity;
    private double quantity;

    RequestDonation(Entity entity, double quantity){
        this.entity = entity;
        this.quantity = quantity;
    }

    public double getQuantity(){return quantity;}

    public void setQuantity(double quantity){this.quantity = quantity;}

    public void addQuantity(double quantity) {this.quantity += quantity;} 
    
    public Entity getEntity(){return entity;}

    @Override
    public String toString() {
        return "(" + getQuantity() + ")\t" + getEntity();
    }

}
