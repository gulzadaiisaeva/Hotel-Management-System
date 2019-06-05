import java.lang.*;

/**
 * abstract PeopleAbstract class which implements People interface
 */
public abstract class PeopleAbstract implements People {

    /**
     * Name of the Person
     */
    private String name;
    /**
     * Surname of the Person
     */
    private String surname;
    /**
     * logName of the Person
     */
    private String logName;
    /**
     * password of the Person
     */
    private String password;
    /**
     * phone number of the Person
     */
    private String phoneNum;

    /**
     * Constructor
     * @param name to be assigned
     * @param surname to be assigned
     * @param logName to be assigned
     * @param password to be assigned
     * @param phoneNum to be assigned
     */
    public PeopleAbstract(String name, String surname, String logName, String password, String phoneNum) {
        this.name = name;
        this.surname = surname;
        this.logName = logName;
        this.password = password;
        this.phoneNum = phoneNum;
    }

    /**
     * Default constructor
     */
    public  PeopleAbstract() {}

    /**
     * Get Login name of Person
     * @return login name
     */
    public String getLogName()
    {
        return logName;
    }

    /**
     * Get the password of the person
     * @return password
     */
    public String getPassword() {return password;}

    /**
     * Set the login name
     * @param newLogName to be assigned
     */
    public void setLogName(String newLogName)
    {
        this.logName=newLogName;
    }

    /**
     * Set the password
     * @param newPassword to be assigned
     */
    public void setPassword(String newPassword)
    {
        this.password=newPassword;
    }

    /**
     * Get the name of the person
     * @return name
     */
    public String getName()
    {
        return  name;
    }

    /**
     * Get the surname of person
     * @return surname
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * Set the name of the person
     * @param newName to be assigned
     */
    public void setName(String newName)
    {
        name=newName;
    }

    /**
     * Set the surname of the person
     * @param newSirname to be assigned
     */
    public void setSurname(String newSirname)
    {
        this.surname =newSirname;
    }

    /**
     * Get the phone number
     * @return phone number
     */
    public String getPhoneNum()
    {
        return phoneNum;
    }

    /**
     * Set the phone number of the person
     * @param newPhoneNum to be assigned
     */
    public void setPhoneNum(String newPhoneNum)
    {
        this.phoneNum=newPhoneNum;
    }

    /**
     * To String method
     * @return
     */
    public String toString()
    {
        String str="";
        str+="Name = " + name + ", Surname = " + surname + ", phone = " + phoneNum + "\n";
        return  str;
    }

    /**
     * compares the parameter user with this class
     * abstract class, written with the idea of polymorphism
     * @param aPeople
     * @return if equals returns true,else returns false
     */
    abstract public boolean equals(PeopleAbstract aPeople);

}
