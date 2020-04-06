package projecteuler.problems;

public class P19 implements Problem{
    public P19() {
        super();
    }
    
    public void execute() {
        System.out.println("You are given the following information, but you may prefer to do some research for yourself." + 
        "1 Jan 1900 was a Monday." +
        "Thirty days has September," +
        "April, June and November." +
        "All the rest have thirty-one," +
        "Saving February alone," +
        "Which has twenty-eight, rain or shine." +
        "And on leap years, twenty-nine." +
        "A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400." +
        "How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?");
        
        // Determining how many Sundays per case.
        int[] regular_year_sundays = new int[7]; // 0 correspond with Jan 1 being a Sunday, 1 corresponds with a Monday, etc.
        int[] leap_year_sundays    = new int[7];
        
        // Filling up the arrays
        int reg_year_day_of_the_week = 0;
        int leap_year_day_of_the_week = 0;
        // add_month changes the value of the total_sundays array inputted.
        for (int jan_1 = 0; jan_1 < 7; jan_1++) {
            // Check January
            if (jan_1 == 0) {
                regular_year_sundays[0]++;
                leap_year_sundays[0]++;
            }
            // Check February by adding January's days
            reg_year_day_of_the_week = this.add_month(regular_year_sundays, 31, reg_year_day_of_the_week, jan_1);
            leap_year_day_of_the_week = this.add_month(leap_year_sundays, 31, leap_year_day_of_the_week, jan_1);
            // Check March by adding February's days
            reg_year_day_of_the_week = this.add_month(regular_year_sundays, 28, reg_year_day_of_the_week, jan_1);
            leap_year_day_of_the_week = this.add_month(leap_year_sundays, 29, leap_year_day_of_the_week, jan_1);
            // Check April by adding March's days
            reg_year_day_of_the_week = this.add_month(regular_year_sundays, 31, reg_year_day_of_the_week, jan_1);
            leap_year_day_of_the_week = this.add_month(leap_year_sundays, 31, leap_year_day_of_the_week, jan_1);
            // Check May by adding April's days
            reg_year_day_of_the_week = this.add_month(regular_year_sundays, 30, reg_year_day_of_the_week, jan_1);
            leap_year_day_of_the_week = this.add_month(leap_year_sundays, 30, leap_year_day_of_the_week, jan_1);
            // Check June by adding May's days
            reg_year_day_of_the_week = this.add_month(regular_year_sundays, 31, reg_year_day_of_the_week, jan_1);
            leap_year_day_of_the_week = this.add_month(leap_year_sundays, 31, leap_year_day_of_the_week, jan_1);
            // Check July by adding June's days
            reg_year_day_of_the_week = this.add_month(regular_year_sundays, 30, reg_year_day_of_the_week, jan_1);
            leap_year_day_of_the_week = this.add_month(leap_year_sundays, 30, leap_year_day_of_the_week, jan_1);
            // Check August by adding July's days
            reg_year_day_of_the_week = this.add_month(regular_year_sundays, 31, reg_year_day_of_the_week, jan_1);
            leap_year_day_of_the_week = this.add_month(leap_year_sundays, 31, leap_year_day_of_the_week, jan_1);
            // Check September by adding August's days
            reg_year_day_of_the_week = this.add_month(regular_year_sundays, 31, reg_year_day_of_the_week, jan_1);
            leap_year_day_of_the_week = this.add_month(leap_year_sundays, 31, leap_year_day_of_the_week, jan_1);
            // Check October by adding September's days
            reg_year_day_of_the_week = this.add_month(regular_year_sundays, 30, reg_year_day_of_the_week, jan_1);
            leap_year_day_of_the_week = this.add_month(leap_year_sundays, 30, leap_year_day_of_the_week, jan_1);
            // Check November by adding October's days
            reg_year_day_of_the_week = this.add_month(regular_year_sundays, 31, reg_year_day_of_the_week, jan_1);
            leap_year_day_of_the_week = this.add_month(leap_year_sundays, 31, leap_year_day_of_the_week, jan_1);
            // Check December by adding November's days
            reg_year_day_of_the_week = this.add_month(regular_year_sundays, 30, reg_year_day_of_the_week, jan_1);
            leap_year_day_of_the_week = this.add_month(leap_year_sundays, 30, leap_year_day_of_the_week, jan_1);
        }
        
        // Now, we have the amount of Sundays in any given year, if we know what day of the week the first day of the year is.
        // So we will iterate from 1901 to 2000, because we can easily determine which year is a leap year.
        // We will also keep track of the day of the week.
        
        int day_of_the_week = (1 + 365) % 7; // Jan 1 1900 is a Monday. Add 365 days and mod by 7 to find the starting day of 1901.
                                             // 1900 is not a leap year.
        int sunday_counter = 0;
        for (int i = 1901; i < 2001; i++) {
            if (i % 4 != 0) { // Not a leap year
                sunday_counter += regular_year_sundays[day_of_the_week];
                day_of_the_week = (day_of_the_week + 365) % 7;
            }
            else {
                sunday_counter += leap_year_sundays[day_of_the_week];
                day_of_the_week = (day_of_the_week + 366) % 7;
            }
        }
        
        System.out.println("\nAnswer: " + sunday_counter);
    }
    
    // Returns the day_of_the_week at the start of the next month.
    private int add_month(int[] arr, int num_days, int day_of_the_week, int entry) {
        int temp = (day_of_the_week + num_days) % 7;
        if (temp == 0) {
            arr[entry]++;
        }
        return temp;
    }
}
