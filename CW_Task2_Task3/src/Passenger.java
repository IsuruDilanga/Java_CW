public class Passenger {

    private String firstName;
    private String surName;
    private int expenses;

    public Passenger(){

        firstName = "f";
        surName = "S";
        expenses = 0;
    }

    public void setFirstName(String fName){
        firstName = fName;
    }

    public void setSurName(String sName){
        surName = sName;
    }

    public void setExpenses(int eExpenses){
        expenses = eExpenses;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getSurName(){
        return surName;
    }

    public int getExpenses(){
        return expenses;
    }
}
