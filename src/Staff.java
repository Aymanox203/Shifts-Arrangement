package src;

public class Staff{
    
    private String name;
    private int ID;
    private int daysWorked=0;
    private static int count =0;
    public Staff(String name)
    {
        this.name = name;
        this.ID = 1100+count;
        count++;
        this.daysWorked = 0;
    }
    public int CheckOut()
    {
        return ++daysWorked;
    }
    public void reset()
    {
        daysWorked = 0;
    }
    public String print()
    {
        String text = name + "\t" + ID + "\t" + daysWorked;
        return text;
    }
    public String getName() {
        return name;
    }
    public int getDaysWorked() {
        return daysWorked;
    }
}