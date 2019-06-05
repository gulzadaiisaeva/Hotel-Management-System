import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.security.InvalidParameterException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Gulzada on 7.03.2018.
 */
class HotelManagementTest {

    private HotelManagement hotel=new HotelManagement();
    @Test
    void userLogin() {
        try{
        FileReader file = new FileReader("userLoginTest.txt");
        Scanner in = new Scanner(file);
        assertEquals(true,hotel.userLogin(in));
        } catch (Throwable throwable) {
            throw new InvalidParameterException(throwable.getMessage());
        }
    }

    @Test
    void receptionistLogin() {
        try{
            FileReader file = new FileReader("receptLoginTest.txt");
            Scanner in = new Scanner(file);
            assertEquals(true,hotel.receptionistLogin(in));
        } catch (Throwable throwable) {
            throw new InvalidParameterException(throwable.getMessage());
        }

    }

    @Test
    void isAvailableRoom() {
        assertEquals(true,hotel.isAvailableRoom("standard 200$"));
    }

    @Test
    void calculateNumOfDay() {
        hotel.setIndexOfUser(0);
        hotel.users.get(hotel.getIndexOfUser()).checkin.setDate(3,3,2018);
        hotel.users.get(hotel.getIndexOfUser()).checkout.setDate(5,3,2018);
        assertEquals(2,hotel.calculateNumOfDay());
    }

}