import java.io.*;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        String[] ship = new String[12]; //Create 12 ship cabin
        initialise(ship);

        boolean endLoop = true;
        while (endLoop){

            System.out.println("""

                    Please choose from the menu

                    A: Add a customer to cabin
                    V: View all cabins
                    E: Display Empty cabins
                    D: Delete customer from cabin
                    F: Find cabin from customer name
                    S: Store program data into file
                    L: Load program data from file
                    O: View Passengers ordered alphabetically by name
                    Q: Quit from program

                    Please Enter choose character:\s""");

            switch (Character.toUpperCase(input.next().charAt(0))){
                case 'A' -> addsCustomerCabin(ship);
                case 'V' -> viewAllCabin(ship);
                case 'E' -> emptyCabins(ship);
                case 'D' -> deleteCustomerFromCabin(ship);
                case 'F' -> findCabinFromCustomerName(ship);
                case 'S' -> storeProgramDataToFile(ship);
                case 'L' -> loadProgramDataFromFile(ship);
                case 'O' -> viewCustomersNameOrderAlphabetically(ship);
                case 'Q' -> endLoop = false;
                default -> System.out.println("Wrong Character selected");
            }
        }
    }

    public static void initialise(String[] shipRef) {
        for (int i = 0; i < 12; i++) {
            shipRef[i] = "empty"; //initialise cabin as empty
        }
    }

    public static void emptyCabins(String[] ship){
        for (int i = 0; i < 12; i++) {

            if (ship[i].equals("empty")) {  //use condition to check cabin is empty or not
                System.out.println("cabin " + i + " is empty");
            }
        }
    }

    public static void addsCustomerCabin(String[] ship){
        String cabinName;
        int cabinNum;

        Scanner input = new Scanner(System.in);
        System.out.println("Enter cabin number (0 - 11)");

        cabinNum = input.nextInt(); //scan input cabin number
        if (cabinNum >= 0 && cabinNum < 12){ //check cabin number correct range

            if (ship[cabinNum].equals("empty")){
                System.out.println("Enter name for cabin " + cabinNum + " :");
                cabinName = input.next();
                ship[cabinNum] = cabinName;
            } else {
                System.out.println(cabinNum + " cabin already have customer");
            }
        } else {
            System.out.println("Maximum Cabins are 0 to 11, Please select between 0 to 11 cabin \n");
            addsCustomerCabin(ship);
        }
    }

    public static void viewAllCabin(String[] ship){
        for (int i = 0; i < 12; i++){
            if (!"empty".equalsIgnoreCase(ship[i])){ //check cabin empty or not
                System.out.println("Customer name " + ship[i] + ", cabin number: " + i);
            } else {
                System.out.println("Cabin " + i + " is empty");
            }
        }
    }

    public static void deleteCustomerFromCabin(String[] ship){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter cabin number to delete(0-11)");

        int cabinNum = input.nextInt(); //scan cabin number
        ship[cabinNum] = "empty"; // make cabin number empty
        System.out.println("Cabin Deleted");
    }

    public static void findCabinFromCustomerName(String[] ship){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter customer name: ");
        String cabinName = input.next();

        boolean checkCustomerName = false; //make boolean for check customer

        for (int i = 0; i < ship.length; i++) {

            if (cabinName.equalsIgnoreCase(ship[i])) { //check cabin name include ship cabin or not
                System.out.println(cabinName + " customer in cabin number " + i + "\n");
                checkCustomerName = true;
            }
        }

        if (!checkCustomerName){
            System.out.println(cabinName + " Customer name not match with the records\n");
        }

    }

    public static void storeProgramDataToFile(String[] ship) throws IOException{

        try(PrintWriter out = new PrintWriter(new FileWriter("C:/Users/HP/Desktop/Folder/IIT/1st Year 2nd Sem/SD 2/CW/CW_Task1/Data/Task1.txt"))) {
            for (int i = 0; i < ship.length; i++) {
                if (!"empty".equalsIgnoreCase(ship[i])){ //check cabin name include customer or not
                    out.println("Customer name " + ship[i] + ",cabin number: " + i);
                } else {
                    out.println("Cabin " + i + " is Empty");
                }
            }
        }
        System.out.println("All cabin names have been saved");
    }

    public static void loadProgramDataFromFile(String[] ship) throws IOException{

        try {
            File filelocation = new File("C:/Users/HP/Desktop/Folder/IIT/1st Year 2nd Sem/SD 2/CW/CW_Task1/Data/Task1.txt");
            Scanner fileReader = new Scanner(filelocation);
            while (fileReader.hasNextLine()){
                String data = fileReader.nextLine();
                System.out.println(data);
            }
            fileReader.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred. ");
            e.printStackTrace();
        }
    }

    public static void viewCustomersNameOrderAlphabetically(String[] ship){

        for (int i = 0; i < ship.length - 1; i++){  // use bubble sort method to make alphabetically order
            for (int k = 0; k < ship.length - i - 1; k++){
                if (ship[k].compareTo(ship[k + 1]) > 0){

                    String temp = ship[k];
                    ship[k]  = ship[k+1];
                    ship[k + 1] = temp;
                }
            }
        }

        System.out.println("Customers name ordered alphabetically: \n");

        for (int i = 0; i < ship.length; i++){

            if (!"empty".equalsIgnoreCase(ship[i])){

                System.out.println(ship[i]);
            }
        }
    }
}
