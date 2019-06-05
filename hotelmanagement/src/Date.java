import java.lang.*;
/**
 * Date class
 */
public class Date {

    /**
     * The day
     */
    private int day;
    /**
     * The month
     */
    private int month;
    /**
     * The year
     */
    private int year;

    /**
     * Constructor
     */
    public Date() {
    }

    /**
     * Constructor
     * @param day to be assigned
     * @param month to be assigned
     * @param year to be assigned
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Set the date
     * @param day to be assigned
     * @param month to be assigned
     * @param year to be assigned
     */
    public void setDate(int day, int month,int year)
    {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    /**
     * Get the day of date
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * Set the day of date
     * @param day to be assigned
     */
    private void setDay(int day) {
        this.day = day;
    }

    /**
     * Get the month of date
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Set the month of date
     * @param month to be assigned
     */
    private void setMonth(int month) {
        this.month = month;
    }

    /**
     * Get the year of date
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year of date
     * @param year to be assigned
     */
    private void setYear(int year) {
        this.year = year;
    }

    /**
     * Checks if two Dates are equal
     * @param o to be checked
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;

        Date date = (Date) o;

        if (day != date.day) return false;
        if (month != date.month) return false;
        return year == date.year;
    }

    /**
     * Overriding toString method
     * @return
     */
    @Override
    public String toString() {
        return
                "" + day +
                "/" + month +
                "/" + year +
                '\n';
    }
}
