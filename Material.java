public class Material extends Entity{
    private double level1;
    private double level2;
    private double level3;

    public Material(int ID, String name, String description,
                    double level1, double level2, double level3){

        super(ID, name, description);
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
    }

    public double getLevel1() {return level1;}
    public double getLevel2() {return level2;}
    public double getLevel3() {return level3;}

    public String getDetails(){return " MATERIAL level1: " + this.level1 + " level2: " + this.level2 + " level3: " + this.level3;}
}