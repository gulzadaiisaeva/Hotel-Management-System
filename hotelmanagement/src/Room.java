import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;
/**
 * Room class
 */
public class Room {
    /**
     * Number of Room
     */
    private int number;
    /**
     * Status of Room
     */
    private String status;
    /**
     * Type of Room
     */
    private String type;
    /**
     * Cost of Reservation
     */
    private int cost;

    /**
     * Constructor
     */
    public Room(){}

    /**
     * Sets all data
     * @param type to be assigned
     * @param status  to be assigned
     * @param cost to be assigned
     * @param number to be assigned
     */
    public void setAllData(String type , String status, int cost,int number)
    {
        setNumber(number);
        setStatus(status);
        setCost(cost);
        setType(type);
    }
    /**
     *
     * @param type to be assigned
     * @param status to be assigned
     * @param cost to be assigned
     * @param number to be assigned
     */
    public Room(String type , String status, int cost,int number) {
        this.number = number;
        this.status = status;
        this.type = type;
        this.cost = cost;
    }

    /**
     * Get the room number
     * @return room number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Get the room status
     * @return room status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the room type
     * @return room type
     */
    public String getType() {
        return type;
    }

    /**
     * Get the Cost of Reservation
     * @return Cost of Reservation
     */
    public int getCost() {
        return cost;
    }

    /**
     * Set the number of room
     * @param number to be assigned
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Set the status of room
     * @param status to be assigned
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Set the type of room
     * @param type to be assigned
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Set the Cost of Reservation
     * @param cost to be assigned
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Overriding toString method
     * @return
     */
    @Override
    public String toString()
    {
        String str="";
        str+="Number = " + number +  ", type = " + type + ", cost = " + cost + "$\n";
        return  str;


    }





}
