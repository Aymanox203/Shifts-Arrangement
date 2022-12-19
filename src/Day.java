package src;

import java.util.ArrayList;
import java.util.List;

public class Day {
    private int number;
    private int minStaff;
    private List<Staff> staffList;
    private int staffCount=0;
    
    Day(int num, int Staff)
    {
        this.number = num;
        this.minStaff = Staff;
        staffList = new ArrayList<>();
        staffCount=0;
        
    }
    public void reset()
    {
        staffList = new ArrayList<>();
        staffCount = 0;
    }
    public int getNumber()
    {return number;}
    public int getMinStaff()
    {return minStaff;}
    public Staff[] getStaff()
    {
        Staff tempArr[] = new Staff[staffCount];
        for(int i=0;i<staffCount;i++)
        {
            tempArr[i] = staffList.get(i);
        }
        return tempArr;
    }
    public int getStaffCount() {
        return staffCount;
    }
    public void setMinStaff(int a)
    {
        this.minStaff = a;
    }
    public void addStaff(Staff s)
    {
        s.CheckOut();
        staffList.add(s);
        this.staffCount++;
    }
    public Boolean includes(Staff s)
    {
        for(int i=0;i<staffCount;i++)
        {
            if(s==staffList.get(i))
            {
                return true;
            }
        }
        return false;
    }
    public String getNames()
    {
        String names="";
        for(int i=0;i<this.staffList.size();i++)
        {
            names+=this.staffList.get(i).getName();
            names+="/";
        } 
        return names;
    }
    public String print()
    {
         
        return ("Day "+ number+": "+ getNames());
    }
    

}
