package src;
import java.util.LinkedList;
import java.util.Queue;


public class Arrangement{
    private Queue<Staff> staffQueue;
    private Day dayArr[];
    private int dayCount;
    private int minDaysOff;
    private Boolean perWeek; //cheking if the user would organise his shifts perWeek
    private String title;
    private Boolean done;
    public Arrangement(int StaffForDays[],int off)
    {
        this.staffQueue= new LinkedList<>();
        this.minDaysOff = off;
        dayCount = StaffForDays.length;
        dayArr = new Day[dayCount];
        for(int i=0;i < dayCount;i++)
        {
            dayArr[i]=new Day(i+1,StaffForDays[i]);
        }
        perWeek = false;
        done= false;
    }
    public Arrangement(int StaffForDays[],int off,Queue<Staff> aStaffQueue,String title)
    {
        this.staffQueue = new LinkedList<>();
        this.title = title;
        this.minDaysOff = off;
        dayCount = StaffForDays.length;
        dayArr = new Day[dayCount];
        for(int i=0;i < dayCount;i++)
        {
            dayArr[i]=new Day(i+1,StaffForDays[i]);
        }
        perWeek = false;
        for(int i =0;i<aStaffQueue.size();i++)
        {
            this.staffQueue.add(aStaffQueue.poll());
        }
        done =false;
    }
    public int getDayCount() {
        return dayCount;
    }
    public String getTitle() {
        return title;
    }
    public void addStaff(Staff ren)
    {
        staffQueue.add(ren);
        done =false;
    }
    public Boolean switchPerWeek()
    {
        if(perWeek)
            perWeek=false;
        else
            perWeek=true;
        done =false;
        return perWeek;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDay(int num, int minStaff)
    {
        assert dayArr.length>=num : "Day out of bounds";
        if(perWeek)
         {
            for(int i=num%8;i<dayArr.length;i+=7)
            {
                dayArr[i].setMinStaff(minStaff);
            }    
         }
         else{
            dayArr[num].setMinStaff(minStaff);
         } 
         done =false;
    }
    public void reset()
    {
        for(Day day:dayArr)
        {
            day.reset();
        }
        for(Staff staff:staffQueue)
            staff.reset();
        done =false;
    }
    public Boolean is_satisfied()
    {
        int availableShifts = staffQueue.size()*(dayCount-minDaysOff);
        int requiredShifts=0;
        Boolean satisfied = true;
        for(int i=0;i<dayCount;i++)
        {
            requiredShifts+=dayArr[i].getMinStaff();
            if(dayArr[i].getMinStaff()>staffQueue.size())
            {
                satisfied = false;
            }
        }
        if(requiredShifts>availableShifts)
            satisfied = false;
        return satisfied;
    }
    public Day[] arrange()
    {
        if(done)
            return dayArr;
        reset();
        Queue<Staff> tempQ =this.staffQueue;
        int requiredDays = this.dayCount-this.minDaysOff;
        if(this.is_satisfied())
        {
            for(int i=0;i<this.dayCount;i++)    //first loop to fill out the minimum staff required for each day
            {
                for(int j=0;j<this.dayArr[i].getMinStaff();j++)
                {
                    dayArr[i].addStaff(tempQ.peek());
                    if(tempQ.peek().getDaysWorked()<requiredDays)
                    tempQ.add(tempQ.poll());
                    else{tempQ.poll();}
                }
            }
            if(tempQ.size()>0)
            {
                int i=0,j=0;
                while(tempQ.size()!=0) //second loop to spread the remaining staff
                {
                    try {
                        while(dayArr[i%(dayCount)].includes(tempQ.peek())&&j<tempQ.size())//checking if staff already in that day
                    {
                        tempQ.add(tempQ.poll());
                        j++;
                    }
                    if (!(j<tempQ.size()))
                        {j=0;i++;continue;}
                    j=0;
                    } catch (Exception e) {
                        
                    }
                    dayArr[i%(dayCount)].addStaff(tempQ.peek());
                    if(tempQ.peek().getDaysWorked()<requiredDays)
                    tempQ.add(tempQ.poll());
                    else{tempQ.poll();}
                    i++;
                }
            }
        }
        else if(!this.is_satisfied())
        {
            int i=0,j=0;
                while(tempQ.size()!=0) 
                {
                    j=0;
                    if(dayArr[i%dayCount].getMinStaff() == dayArr[i%dayCount].getStaffCount())
                        {
                            i++;
                            continue;
                        }
                    try {
                        while(dayArr[i%(dayCount)].includes(tempQ.peek())&&j<tempQ.size())
                    {
                        tempQ.add(tempQ.poll()); 
                        j++;

                    }
                    } catch (Exception ArrayIndexOutOfBoundsException) {
                        i++;
                        continue;
                    }
                    dayArr[i%(dayCount)].addStaff(tempQ.peek());
                    if(tempQ.peek().getDaysWorked()<requiredDays)
                    tempQ.add(tempQ.poll());
                    else{tempQ.poll();}
                    i++;
                }
        }

        done = true;
        return dayArr;
        
    }


}