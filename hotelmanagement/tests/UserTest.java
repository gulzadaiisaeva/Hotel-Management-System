import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Gulzada on 7.03.2018.
 */
class UserTest {
    @Test
    public void TestToString() {
        User u=new User("Gulzada","Iisaeva","gul","123","0552");
        String str="";
        str+="\nPersonal info : " + "Name = Gulzada, Surname = Iisaeva, phone = 0552\n"
                +"Reservation info : "+"Number = 0, type = null, cost = 0$\n"+"Check-in date = 0/0/0\n"+
                "Checkout date = 0/0/0\n\n";

        assertEquals(str,u.toString());
    }

    @Test
    void equals() {
        PeopleAbstract p=new User("Gulzada","Iisaeva","gul","123","0552");
        PeopleAbstract p2=new User("Gulzada","Almasova","gulzada","1245","0552");
        assertEquals(false,p.equals(p2));
    }

}