import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) throws IOException{

        Scanner input = new Scanner(System.in);

        Cabin[] myShip = new Cabin[12]; //make cabin class with 12 cabins
        Passenger[][] myCabin = new Passenger[12][3]; // make passenger class and 2D array for store passenger details

        //myShip array element assign Cabin constructor
        for (int i = 0; i < myShip.length; i++) {
            myShip[i] = new Cabin();
        }

        //myCabin array element assign Passenger constructor
        for (int x = 0; x < myCabin.length; x++){
            for (int i = 0; i < myCabin[x].length; i++){
                myCabin[x][i] = new Passenger();
            }
        }

        initialise(myShip, myCabin); //myship array and mycabin array initialise

        boolean endLoop = true; //make boolean to check want to run main list
        while (endLoop) {   //make loop to run main list continuously
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
                    T: Expense
                    Q: Quit from program

                    Please Enter choose character:\s""");

            switch (Character.toUpperCase(input.next().charAt(0))) {
                case 'A' -> addsCustomerCabin(myShip, myCabin);
                case 'V' -> viewAllCabin(myShip, myCabin);
                case 'E' -> emptyCabins(myShip);
                case 'D' -> deleteCustomerFromCabin(myShip, myCabin);
                case 'F' -> findCabinFromCustomerName(myShip, myCabin);
                case 'S' -> storeProgramDataToFile(myShip, myCabin);
                case 'L' -> loadProgramDataFromFile();
                case 'O' -> viewCustomersNameOrderAlphabetically(myCabin);
                case 'T' -> expesesOfPassengers(myShip, myCabin);
                case 'Q' -> endLoop = false;
                default -> System.out.println("Wrong Character selected");
            }
        }
    }

    public static void initialise(Cabin[] shipRef, Passenger[][] cabinRef){
        for (int i = 0; i < shipRef.length; i++){
            shipRef[i].setcabinNum(20); //initialise cabin number as 20
        }

        for (int i = 0; i < cabinRef.length; i++){
            for(int k = 0; k < cabinRef[i].length; k++){
                cabinRef[i][k].setFirstName("empty"); //initialise passenger first name as empty
                cabinRef[i][k].setSurName("empty"); //initialise passenger surname as empty
                cabinRef[i][k].setExpenses(0);  //initialise passenger expense as 0
            }
        }
    }

    public static void addsCustomerCabin(Cabin[] myShip, Passenger[][] myCabin){

        int cabinNum; //assign cabin number as int
        Scanner input = new Scanner(System.in);

        System.out.println("Enter cabin number (0-11)");
        cabinNum = input.nextInt(); //input cabin number
        if (cabinNum >= 0 && cabinNum < 12){ //check the cabin number in 0 to 12

            myShip[cabinNum].setcabinNum(cabinNum); //set myship array location
            System.out.println("which location do you want to allocate passenger in cabin number " + cabinNum);
            int passengerLocation = input.nextInt(); //input passenger location in mycabin array
            if (passengerLocation >= 0 && passengerLocation < 3){ // check passenger location in 0 - 3


                if (myCabin[cabinNum][passengerLocation].getFirstName().equals("empty")){ //check that passenger location empty or not
                    System.out.println("Enter Passenger " + passengerLocation + " first name:");
                    myCabin[cabinNum][passengerLocation].setFirstName(input.next());    // input passenger first name
                    System.out.println("Enter Passenger " + passengerLocation + " surname:");
                    myCabin[cabinNum][passengerLocation].setSurName(input.next()); // input passenger surname

                    int expenses = 0;
                    do {
                        try {
                            System.out.println("Enter Passenger " + passengerLocation + " expense:");
                            expenses = input.nextInt(); //input passenger expense
                            myCabin[cabinNum][passengerLocation].setExpenses(expenses);

                        } catch (InputMismatchException e) {
                            System.out.print("Invalid expense. "+ "\n");
                        }
                        input.nextLine();
                    } while (expenses <= 0);

                } else {
                    System.out.println(passengerLocation + " Cabin already have customer");
                }
            } else {
                System.out.println("Please enter cabin location 0 - 11");
                addsCustomerCabin(myShip, myCabin); //use recursion method
            }

        }else {
            System.out.println("Maximum Cabins are 0 to 11, Please select 0 to 11 cabin \n");
            addsCustomerCabin(myShip, myCabin); //use recursion method
        }

    }

    public static void viewAllCabin(Cabin[] myShip, Passenger[][] myCabin){
        for (int i = 0; i < myShip.length; i++){
            if (myShip[i].getcabinNum() != 20){ //check if the cabin number equal initialise number or not,
                for (int k = 0; k < myCabin[i].length; k++){
                    if (!"empty".equalsIgnoreCase(myCabin[i][k].getFirstName())){
                        System.out.println("\n" + "Cabin number: " + myShip[i].getcabinNum() + " Passenger " + k + "\n");
                        System.out.println("Passenger firstname: " + myCabin[i][k].getFirstName() + "\n" + "Passenger surname: " + myCabin[i][k].getSurName() + "\n" + "Passenger Expenses: " + myCabin[i][k].getExpenses());

                    }else {
                        System.out.println("\n"+"  Cabin number: " + i + " Passenger: " + k + " empty");
                    }
                }
            } else {
                System.out.println("\n" + "Cabin " + i + " is Empty");
            }
        }
    }

    public static void emptyCabins(Cabin[] myShip){

        for (int i = 0; i < myShip.length; i++){
            if (myShip[i].getcabinNum() == 20){ //check the cabin empty or not
                System.out.println("cabin " + i + " is empty");
            }
        }
    }

    public static void deleteCustomerFromCabin(Cabin[] myShip, Passenger[][] myCabin){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter cabin number to delete(0-11)");
        int cabinNum = input.nextInt(); //input cabin number
        myShip[cabinNum].setcabinNum(20); //make cabin number as 20

        for (int i = 0; i < 3; i++){
            myCabin[cabinNum][i].setFirstName("empty"); //set mycabin passenger first name as empty
            myCabin[cabinNum][i].setSurName("empty"); //set mycabin passenger surname as empty
            myCabin[cabinNum][i].setExpenses(0); //set mycabin passenger expense as 0
        }

        System.out.println("Cabin Deleted");
    }

    public static void findCabinFromCustomerName(Cabin[] myShip, Passenger[][] myCabin){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Customer Name: ");
        String passengerName = input.next(); //input passenger name

        boolean checkCustomerName = false; //make boolean for check customer
        for(int i= 0; i < myShip.length; i++){
            for (int k = 0; k < myCabin[i].length; k++){

                if (passengerName.equalsIgnoreCase(myCabin[i][k].getFirstName()) && !checkCustomerName){ //check cabin name include ship cabin or not
                    System.out.println(passengerName + " customer in cabin number " + i + ", " + k + " person" + "\n");
                    checkCustomerName = true;
                }
            }
        }
        if (!checkCustomerName){
            System.out.println(passengerName + " Customer name not match with the records\n");
        }
    }

    public static void storeProgramDataToFile(Cabin[] myShip, Passenger[][] myCabin) throws IOException{
        try (PrintWriter out = new PrintWriter(new FileWriter("C:/Users/HP/Desktop/Folder/IIT/1st Year 2nd Sem/SD 2/CW/CW_Task2_Task3/Data/Task2.txt"))) {
            for (int i = 0; i < myShip.length; i++){
                for (int k = 0; k < myCabin[i].length; k++){

                    if (myShip[i].getcabinNum() != 20){ //check if cabin empty or not
                        if (!"empty".equalsIgnoreCase(myCabin[i][k].getFirstName())){ //check cabin name include customer or not
                            out.println("\n" + "Cabin number: " + myShip[i].getcabinNum() + " Passenger " + k + "\n");
                            out.println("Passenger firstname: " + myCabin[i][k].getFirstName() + "\n" + "Passenger surname: "
                                    + myCabin[i][k].getSurName() + "\n" + "Passenger Expenses: "
                                    + myCabin[i][k].getExpenses());
                        } else {
                            out.println("\n" + "Cabin " + i + " Passenger " + k + " is Empty");
                        }

                    }else {
                        out.println("\n"+"Cabin " + i + " is Empty");
                        break;
                    }
                }
            }
        }
        System.out.println("All cabin names have been saved");
    }

    public static void loadProgramDataFromFile(){

        //call the load file
        try {
            File filelocation = new File("C:/Users/HP/Desktop/Folder/IIT/1st Year 2nd Sem/SD 2/CW/CW_Task2_Task3/Data/Task2.txt");
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

    public static void viewCustomersNameOrderAlphabetically(Passenger[][] myCabin){

        String[] firstNameList = new String[36]; //create new array to assign passengers first name

        int index = 0;

        for (int i = 0; i < myCabin.length; i++){
            for (int k = 0; k < myCabin[i].length; k++){

                firstNameList[index++] = myCabin[i][k].getFirstName(); //assign passenger first name to new array
            }
        }

        //sorting first name
        for (int i = 0; i < firstNameList.length - 1; i++){
            for (int j = 0; j < firstNameList.length - i -1; j++){
                if (firstNameList[j].compareTo(firstNameList[j + 1]) > 0){

                    String temp = firstNameList[j];
                    firstNameList[j] = firstNameList[j + 1];
                    firstNameList[j+ 1] = temp;
                }
            }
        }

        for (int i = 0; i < firstNameList.length; i++){
            if (!"empty".equalsIgnoreCase(firstNameList[i])){
                System.out.println(firstNameList[i]);
            }
        }

    }

    public static void expesesOfPassengers(Cabin[] myShip, Passenger[][] myCabin){
        Scanner input = new Scanner(System.in);

        int Total = 0;
        boolean endLoop = true;
        while (endLoop) {

            System.out.println("""
                    A: Do you want check one person Expense
                    B: Do you want to check all person Expense
                    T: Do you want check Total Expenses
                    M: Back to main \s""");

            switch (Character.toUpperCase(input.next().charAt(0))) {

                case 'A':
                    System.out.println("Enter Passenger name: ");
                    String name = input.next(); //input passenger name to check expense
                    boolean checkCustomerName = false;
                    for (int i = 0; i < myCabin.length; i++) {
                        for (int k = 0; k < myCabin[i].length; k++) {
                            if (name.equalsIgnoreCase(myCabin[i][k].getFirstName())) { //check passenger inside the ship
                                System.out.println("\n"+name + " passenger expense is " + myCabin[i][k].getExpenses());
                                checkCustomerName = true;
                            }
                        }
                    }
                    if (!checkCustomerName){
                        System.out.println(name + " Customer name not match with the records\n");

                    }
                    break;

                case 'B':
                    for (int i = 0; i < myShip.length; i++) {
                        for (int k = 0; k < myCabin[i].length; k++) {

                            if (myCabin[i][k].getExpenses() > 0) {
                                System.out.println("\n"+myCabin[i][k].getFirstName() + " Passenger expense is: " + myCabin[i][k].getExpenses());
                            }
                        }
                    }
                    break;

                case 'T':
                    for (int i = 0; i < myShip.length; i++) {
                        for (int k = 0; k < myCabin[i].length; k++) {

                            if (myCabin[i][k].getExpenses() > 0) {
                                Total += myCabin[i][k].getExpenses(); //calculate total
                            }
                        }
                    }
                    System.out.println("\n"+"Total Expense: " + Total);
                    break;

                case 'M':
                    endLoop = false;

                default:
                    System.out.println("\n"+"Please choose correct option");

            }
        }
    }
}
