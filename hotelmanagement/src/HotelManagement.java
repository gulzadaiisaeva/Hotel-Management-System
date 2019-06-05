import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.security.InvalidParameterException;
import java.lang.*;

/**
 * Main class of hotel management where guest or receptionist can make reservation , cancel
 * check-in or checkout
 */
public class HotelManagement {

    /**
     * Keeps all the users
     */
    protected   ArrayList<User> users = new ArrayList<User>();
    /**
     * Keeps all the rooms of hotel
     */
    private ArrayList<Room> rooms =  new ArrayList<Room>();
    /**
     * The Receptionist
     */
    private Receptionist receptionist;
    private int indexOfUser;


    /**
     * Constructor
     */
    public HotelManagement() {
        receptionist=new Receptionist();
        CSVReaderUser();
        CSVReaderRooms();

    }

    /**
     *
     * @return
     */
    public int getIndexOfUser() {
        return indexOfUser;
    }

    /**
     * Set the index of current user
     * @param indexOfUser to be assigned
     */
    public void setIndexOfUser(int indexOfUser) {
        this.indexOfUser = indexOfUser;
    }


    /**
     * Log in by asking logName and password from user
     * checks if logName and password exist in users , also finds the index current user
     * @param in
     * @throws InvalidParameterException
     */
    public boolean userLogin(Scanner in) throws InvalidParameterException
    {
        int flag=-1;
        String logName,password;
        System.out.println("\nEnter a LogName: ");
        logName=in.nextLine();

        System.out.println("Enter a Password: ");
        password=in.nextLine();
        for(int i =0; i< users.size(); i++)
        {

            if(users.get(i).getLogName().equals(logName) && users.get(i).getPassword().equals(password)) {
                setIndexOfUser(i);
                System.out.println("\n********* WELCOME TO USER PAGE*************");
                flag=0;
                i=users.size();
                return  true;
            }

        }

        if (flag==-1)
            throw new InvalidParameterException("Wrong info!failed login");

        return false;
    }

    /**
     * Log in by asking logName and password from receptionist
     * LogName and Password is "admin"
     * checks if logName and password is "admin"
     * @param in
     * @throws InvalidParameterException
     */
    public boolean receptionistLogin(Scanner in)throws InvalidParameterException
    {
        int flag=-1;
        String logName,password;

        System.out.println("Enter a LogName: ");
        logName=in.nextLine();

        System.out.println("Enter a Password: ");
        password=in.nextLine();

        if(logName.equals("admin") && password.equals("admin")) {
            System.out.println("\n************Welcome to Receptionist Page*************");
            flag=0;
            return true;
        }

        if (flag==-1)
            throw new InvalidParameterException("Wrong info!failed login");

        return false;
    }

    /**
     * Register new user to hotel
     * @param in
     */
    public void signIn(Scanner in)
    {
        User u1= new User();
        u1.signIn(in);
        int flag = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogName().equals(u1.getLogName())) {
                System.out.println("Username you choose is not available");
                flag = 1;
            }
        }
        if (flag == 0) {
            users.add(u1);
            CSVWriterUser();
        }
    }

    /**
     * Checking is there available room for reservation
     * @param room type of room to be checked
     * @return
     */
    public boolean isAvailableRoom(String room)
    {
        if(room.equals("standard 200$")) {
            for (int i = 0; i < 10; i++) {
                if (rooms.get(i).getStatus().equals("available"))
                    return true;
            }
        }
        if(room.equals("deluxe 600$")) {
            for (int i = 10; i < 20; i++) {
                if (rooms.get(i).getStatus().equals("available"))
                    return true;
            }
        }
        if(room.equals("family 400$")) {
            for (int i = 20; i < 30; i++) {
                if (rooms.get(i).getStatus().equals("available"))
                    return true;
            }
        }
        return false;
    }

    /**
     * Calculate the cost of reservation
     * Number of people*number of day*cost of room
     * @return
     */
    public int calculateNumOfDay()
    {
        int temp;
        if(users.get(getIndexOfUser()).checkin.getMonth()!=users.get(getIndexOfUser()).checkout.getMonth())
        {
            temp=(31-users.get(getIndexOfUser()).checkin.getDay())+users.get(getIndexOfUser()).checkout.getDay();
        }
        else{
            temp=users.get(getIndexOfUser()).checkout.getDay()-users.get(getIndexOfUser()).checkin.getDay();
        }
        return temp;
    }

    /**
     * Makes the reservation by user
     * 1)Asks check-in and checkout day
     * 2)Asks number of guests
     * 3)Asks type of room
     * Calculates the cost of reservation
     * @param in
     */
    public void userReservation(Scanner in)
    {
        boolean flag=true;

        System.out.println("Enter a Check-in date as 01 02 2018: ");
        users.get(getIndexOfUser()).checkin.setDate(in.nextInt(),in.nextInt(),in.nextInt());
        System.out.println("Enter a Checkout date as 01 02 2018: ");
        users.get(getIndexOfUser()).checkout.setDate(in.nextInt(),in.nextInt(),in.nextInt());

        System.out.println("How many people? ");
        int cost=in.nextInt();

        while(flag) {
            System.out.println("Choose a type of Room:\n1)Standard 200$\n2)Deluxe 600$\n3)Family 400$ ");
            int roomType=in.nextInt();
            if (roomType == 1) {
                if(isAvailableRoom("standard 200$"))
                    users.get(getIndexOfUser()).roomInfo.setType("standard 200$");
                    users.get(getIndexOfUser()).roomInfo.setCost(cost*200*calculateNumOfDay());
                    users.get(getIndexOfUser()).roomInfo.setStatus("reserved");
                    flag=false;
            }
            else if (roomType== 2) {
                if(isAvailableRoom("deluxe 600$"))
                    users.get(getIndexOfUser()).roomInfo.setType("deluxe 600$");
                    users.get(getIndexOfUser()).roomInfo.setCost(cost*600*calculateNumOfDay());
                    users.get(getIndexOfUser()).roomInfo.setStatus("reserved");
                    flag=false;
            }
            else {
                if(isAvailableRoom("family 400$"))
                    users.get(getIndexOfUser()).roomInfo.setType("family 400$");
                    users.get(getIndexOfUser()).roomInfo.setCost(cost*400*calculateNumOfDay());
                    users.get(getIndexOfUser()).roomInfo.setStatus("reserved");
                    flag=false;
            }

        }

        System.out.println(users.get(getIndexOfUser()).toString());
        CSVWriterUser();

    }
    /**
     * Makes the reservation by receptionist
     * 1)Asks name and surname of guests
     * 2)Asks check-in and checkout day
     * 3)Asks number of guests
     * 4)Asks type of room
     * Calculates the cost of reservation
     * @param in
     */
    public void receptReservation(Scanner in)
    {
        boolean flag=true;
        User u=new User();
        System.out.println("Enter a Name ");
        u.setName(in.nextLine());
        System.out.println("Enter a Surname ");
        u.setSurname(in.nextLine());
        System.out.println("Enter a PhoneNumber ");
        u.setPhoneNum(in.nextLine());
        System.out.println("Enter a Check-in date as 01 02 2018: ");
        u.checkin.setDate(in.nextInt(),in.nextInt(),in.nextInt());
        System.out.println("Enter a Checkout date as 01 02 2018: ");
        u.checkout.setDate(in.nextInt(),in.nextInt(),in.nextInt());

        System.out.println("How many people? ");
        int cost=in.nextInt();

        while(flag) {
            System.out.println("Choose a type of Room:\n1)Standard 200$\n2)Deluxe 600$\n3)Family 400$ ");
            int roomType=in.nextInt();
            if (roomType == 1) {
                if(isAvailableRoom("standard 200$"))
                    u.roomInfo.setType("standard 200$");
                    u.roomInfo.setCost(cost*200*calculateNumOfDay());
                    u.roomInfo.setStatus("reserved");
                    flag=false;
            }
            else if (roomType== 2) {
                if(isAvailableRoom("deluxe 600$"))
                    u.roomInfo.setType("deluxe 600$");
                    u.roomInfo.setCost(cost*600*calculateNumOfDay());
                    u.roomInfo.setStatus("reserved");
                    flag=false;
            }
            else {
                if(isAvailableRoom("family 400$"))
                    u.roomInfo.setType("family 400$");
                    u.roomInfo.setCost(cost*400*calculateNumOfDay());
                    u.roomInfo.setStatus("reserved");
                    flag=false;
            }

        }
        users.add(u);

        System.out.println(users.get(users.size()-1).toString());
        CSVWriterUser();

    }


    /**
     * Cancel the reservation by user
     * If there is no reservation returns false
     * Shows the reservation and asks to cancel it
     * If the reservation was checked then it impossible to cancel,it shows that reservation was
     * already checked
     * @param reader
     * @return
     */
    public boolean userCancel(Scanner reader)
    {
        if(users.get(getIndexOfUser()).roomInfo.getStatus().equals("available"))
        {
            System.out.println("There is no reservation");
            return false;
        }
        System.out.println("  Your reservation: ");
        System.out.println(users.get(getIndexOfUser()).toString());
        System.out.println("Do you want to cancel it? y/n");

        char c = reader.next().charAt(0);
        if(c=='y') {
            if (users.get(getIndexOfUser()).roomInfo.getStatus().equals("reserved")) {
                users.get(getIndexOfUser()).roomInfo.setAllData(null,"available",0,0);
                users.get(getIndexOfUser()).checkin.setDate(0,0,0);
                users.get(getIndexOfUser()).checkout.setDate(0,0,0);
                System.out.println("Your reservation was canceled");
                CSVWriterUser();
                return true;
            } else
                System.out.println("Your booking was checked");
        }

        return false;
    }
    /**
     * Cancel the reservation by receptionist
     * Asks the name and surname of guests
     * Cancel if there any reservation of the guest shows and asks to cancel?
     * If the reservation was checked then it impossible to cancel,it shows that reservation was
     * already checked
     * @param in
     * @return
     */
    public boolean receptCancel(Scanner in)
    {
        String name, surname;
        boolean flag=true;
        while(flag) {
            System.out.println("\nEnter a Name ");
            name = in.nextLine();
            System.out.println("Enter a Surname ");
            surname = in.nextLine();

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getName().equals(name) && users.get(i).getSurname().equals(surname)) {
                    setIndexOfUser(i);
                    flag=false;
                }
            }
            if(flag!=false)
                System.out.println("There is no such person");
        }
        if(users.get(getIndexOfUser()).roomInfo.getStatus().equals("available"))
        {

            System.out.println("There is no reservation");
            return false;
        }
        System.out.println("\nYour reservation: ");
        System.out.println(users.get(getIndexOfUser()).toString());
        System.out.println("Do you want to cancel it? y/n");
        char c = in.next().charAt(0);
        if(c=='y') {
            if (users.get(getIndexOfUser()).roomInfo.getStatus().equals("reserved")) {
                users.get(getIndexOfUser()).roomInfo.setAllData(null,"available",0,0);
                users.get(getIndexOfUser()).checkin.setDate(0,0,0);
                users.get(getIndexOfUser()).checkout.setDate(0,0,0);
                System.out.println("\nYour reservation was canceled\n");
                return true;
            } else
                System.out.println("\nYour booking was checked\n");
        }
        CSVWriterUser();
        return false;
    }

    /**
     * Check-in for user by admin
     * Asks the date and lists all reservation which have to be check-in that day
     * Asks the name and surname of guests
     * Lists all available room and choose the room number,after gives the room to guest
     * @param in
     * @return
     */
    public boolean checkin(Scanner in)
    {
        String name="",surname="";
        System.out.println("Enter a Date as 01 02 2018 :");


        Date today=new Date(in.nextInt(),in.nextInt(),in.nextInt());
        System.out.println("\nThe all reservation of today which have to be checked: ");
        for (int i = 0; i < users.size() ; i++) {
            if(today.equals(users.get(i).checkin) && users.get(i).roomInfo.getStatus().equals("reserved"))
                System.out.println(users.get(i).toString());
        }
        in.nextLine();
        System.out.println("Enter a Name: ");
        name=in.nextLine();

        System.out.println();
        System.out.println("Enter a Surname: " );
        surname=in.nextLine();

        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getName().equals(name) && users.get(i).getSurname().equals(surname))
                setIndexOfUser(i);
        }

        System.out.println("All available rooms: ");
        for (int i = 0; i < rooms.size() ; i++) {
            if(rooms.get(i).getStatus().equals("available") && users.get(getIndexOfUser()).roomInfo.getType().equals(rooms.get(i).getType()))
                System.out.println(rooms.get(i).getNumber());
        }
        System.out.println("Choose a number");
        int num=in.nextInt();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getNumber()==num) {
                rooms.get(i).setStatus("checked");
            }
        }
        users.get(getIndexOfUser()).roomInfo.setStatus("checked");
        users.get(getIndexOfUser()).roomInfo.setNumber(num);
        CSVWriterUser();
        CSVWriterRooms();
        return  true;
    }

    /**
     * Checkout for user by admin
     * Asks the date and lists all reservation which have to be checkout that day
     * Asks the name and surname of guests
     * Set the room available
     * @param in
     * @return
     */
    public boolean checkout(Scanner in)
    {
        String name="",surname="";
        System.out.println("Enter a Date as 01 02 2018 :");

        Date today=new Date(in.nextInt(),in.nextInt(),in.nextInt());
        System.out.println("The all reservation of today which have to be checkout: ");
        for (int i = 0; i < users.size() ; i++) {
            if(today.equals(users.get(i).checkout))
                System.out.println(users.get(i).toString());
        }
        in.nextLine();
        System.out.println("Enter a Name: ");
        name=in.nextLine();

        System.out.println();
        System.out.println("Enter a Surname: " );
        surname=in.nextLine();

        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getName().equals(name) && users.get(i).getSurname().equals(surname))
                setIndexOfUser(i);
        }

        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getNumber()==users.get(getIndexOfUser()).roomInfo.getNumber()) {
                rooms.get(i).setStatus("available");
                users.get(getIndexOfUser()).checkin.setDate(0,0,0);
                users.get(getIndexOfUser()).checkout.setDate(0,0,0);
                users.get(getIndexOfUser()).roomInfo.setAllData(null,"available",0,0);
            }
        }
        users.get(getIndexOfUser()).roomInfo.setStatus("available");
        CSVWriterUser();
        CSVWriterRooms();
        return  true;
    }

    /**
     * Read the users from csv file to users variable
     * Read their personal info, booking info
     * @throws IOException
     */
    public void CSVReaderUser()
    {
        String csvFile = "user.csv";
        String line = "";

        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] temp = line.split(cvsSplitBy);

                User u=new User(temp[0],temp[1],temp[2],temp[3],temp[4]);
                u.roomInfo.setNumber(Integer.parseInt(temp[5]));
                u.roomInfo.setStatus(temp[6]);
                u.roomInfo.setType(temp[7]);
                u.roomInfo.setCost(Integer.parseInt(temp[8]));
                u.checkin.setDate(Integer.parseInt(temp[9]),Integer.parseInt(temp[10]),Integer.parseInt(temp[11]));
                u.checkout.setDate(Integer.parseInt(temp[12]),Integer.parseInt(temp[13]),Integer.parseInt(temp[14]));

                users.add(u);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write the users from users variable file to csv file
     * Write their personal info, booking info
     * @throws IOException
     */
    public void CSVWriterUser()
    {

        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPARATOR = "\n";
        String header="name,surname,logName,password,phoneNum,roomNo,status,roomtype,startDay,month,year,endDay,month,year";

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("user.csv");
            fileWriter.append(header.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);
            for (int i = 0; i < users.size(); i++) {
                fileWriter.append(users.get(i).getName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(users.get(i).getSurname());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(users.get(i).getLogName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(users.get(i).getPassword());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(users.get(i).getPhoneNum());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(users.get(i).roomInfo.getNumber()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(users.get(i).roomInfo.getStatus());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(users.get(i).roomInfo.getType());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(users.get(i).roomInfo.getCost()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(users.get(i).checkin.getDay()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(users.get(i).checkin.getMonth()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(users.get(i).checkin.getYear()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(users.get(i).checkout.getDay()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(users.get(i).checkout.getMonth()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(users.get(i).checkout.getYear()));
                fileWriter.append(NEW_LINE_SEPARATOR);

            }


        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }


    }

    /**
     * Read the all rooms information from csv file to room variable
     *
     * @throws IOException
     */
    public void CSVReaderRooms()
    {
        String csvFile = "rooms.csv";
        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] temp = line.split(cvsSplitBy);

                Room r=new Room(temp[0],temp[1],Integer.parseInt(temp[2]),Integer.parseInt(temp[3]));
                rooms.add(r);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Write the all rooms information from room variable to csv filee
     *
     * @throws IOException
     */
    public void CSVWriterRooms()
    {
        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPARATOR = "\n";
        String header="type,status,cost,number";

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("rooms.csv");

            fileWriter.append(header.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);
            for (int i = 0; i < rooms.size(); i++) {
                fileWriter.append(rooms.get(i).getType());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(rooms.get(i).getStatus());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(rooms.get(i).getCost()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(rooms.get(i).getNumber()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(NEW_LINE_SEPARATOR);

            }


        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }

    }
}
