package src;

public class App {
    public static void main(String[] args) {

        // AFrame frame1 = new AFrame();
        


        
        int staffArr[] = {5,5,5,5,5,5,5};
        int daysOff = 2;
        
        Arrangement arrangement1 = new Arrangement(staffArr, daysOff);
        arrangement1.setTitle("Market");
        String people[] = {"Hamid","Rachid","Zahra","Boujemaa","Said"};
        for(String s:people)
            arrangement1.addStaff(new Staff(s));
        ArrangementTable panel = new ArrangementTable(arrangement1);

        for(String s:people)
            arrangement1.addStaff(new Staff(s));
        Day arrangedDays[] =  arrangement1.arrange();
        for(int i=0;i<arrangement1.getDayCount();i++)
        {
           System.out.println(arrangedDays[i].print());
        }
    }
    
}
