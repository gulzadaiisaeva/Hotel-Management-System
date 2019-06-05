import java.lang.*;
/**
 * Receptionist class which extends PeopleAbstract
 */
public class Receptionist extends PeopleAbstract {


    public Receptionist(){
        super();
    }

    /**
     * Overriding toString method
     * @return
     */
    @Override
    public String toString()
    {
        String str="";
        str+="Admin info is hidden\n";
        return  str;

    }


    /**
     * compares the parameter  with this class
     * @param aPeople
     * @return if equals returns true,else returns false
     */
    @Override
    public boolean equals(PeopleAbstract aPeople){
        /*exeption handling using try-catch method*/
        try{
            Receptionist temp=(Receptionist)aPeople;
            return (getName().equals(temp.getName()) && getSurname().equals(temp.getSurname()));
        }
        catch(ClassCastException E){
            System.out.println("Wrong input for this(equals) method!, input is"
                    + "not a user");
            return false;
        }
    }

}
