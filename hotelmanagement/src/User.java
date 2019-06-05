
import java.util.Scanner;
import java.lang.*;

/**
 * User class which extends PeopleAbstract
 */
public class User extends PeopleAbstract {

    /**
     * Information about room
     */
    protected Room roomInfo=new Room();
    /**
     * Information about check in date
     */
    protected Date checkin = new Date();
    /**
     * Information about checkout date
     */
    protected Date checkout = new Date();

    /**
     * Constructor
     */
    public User(){
        super();

    }

    /**
     * Constructor
     * @param name to be assigned
     * @param sirname to be assigned
     * @param logName to be assigned
     * @param password to be assigned
     * @param phoneNum to be assigned
     */
    public User(String name, String sirname, String logName, String password, String phoneNum)
    {
        super( name,  sirname,  logName,  password,  phoneNum);
    }

    /**
     * Register the user to hotel by asking
     * personal information
     * @param in
     */
    protected void signIn(Scanner in)
    {

        System.out.println("Enter a Name: ");
        setName(in.nextLine());

        System.out.println("Enter a Sirname: ");
        setSurname(in.nextLine());

        System.out.println("Enter a Logname: ");
        setLogName(in.nextLine());

        System.out.println("Enter a password: ");
        setPassword(in.nextLine());

        System.out.println("Enter a phone number: ");
        setPhoneNum(in.nextLine());

        roomInfo.setStatus("available");
    }


    /**
     * Overriding toString method
     * @return
     */
    @Override
    public String toString()
    {
        String str="";
        str+="\nPersonal info : " + super.toString()
                +"Reservation info : "+roomInfo.toString()+"Check-in date = "+checkin.toString()+"Checkout date = "+checkout.toString()+"\n";
        return  str;

    }

    /**
     * compares the parameter  with this class
     *
     * @param aPeople
     * @return if equals returns true,else returns false
     */
    @Override
    public boolean equals(PeopleAbstract aPeople){
        if(aPeople == this ) return true;
        if(aPeople ==null ) return false;

        /*exeption handling using try-catch method*/
        try{
            User temp=(User)aPeople;
            return (getName().equals(temp.getName()) && getSurname().equals(temp.getSurname()));
        }
        catch(ClassCastException E){
            System.out.println("Wrong input for this(equals) method!, input is"
                    + "not a user");
            return false;
        }
    }

}
