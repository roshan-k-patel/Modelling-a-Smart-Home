import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedReader;

public class House {

    //Member variables for the House class
    protected ArrayList<Appliance> appliances = new ArrayList<>();
    Meter water;
    BatteryMeter electricity;
    BufferedReader readerOne;
    ArrayList<String> fileArray;
    GasMeter gas;

    //Constructor for the house which takes the member variables and initialises them
    public House(BatteryMeter electricity, Meter water, String configFileName) {
    	this.water = water;
    	this.electricity = electricity;
        FileReader configToRead;

        // throws the exception if the file cannot be found, with the error "cannot locate file"
        try {
            configToRead = new FileReader(configFileName);

            readerOne = new BufferedReader(configToRead);
        }

        catch (FileNotFoundException e) {
            System.out.println("Cannot locate file");
        }
    }

    //Overloaded constructor for the extension which includes the gas meter
    public House(BatteryMeter electricity, Meter water, GasMeter gas, String configFileName) {
        this.water = water;
        this.electricity = electricity;
        this.gas = gas;
        FileReader configToRead;

        // throws the exception if the file cannot be found, with the error "cannot locate file"
        try {
            configToRead = new FileReader(configFileName);

            readerOne = new BufferedReader(configToRead);
        }

        catch (FileNotFoundException e) {
            System.out.println("Cannot locate file");
        }
    }
    // continues to read the file while there is a next line in the file
    public String getLine(){

        try {

            while(fileIsReady() == true) {

                return readerOne.readLine();

            }

        }

        catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    //method to check if the file is the correct format and has data
    public Boolean fileIsReady(){

        try {
            return readerOne.ready();
        }

        catch (NullPointerException e) {
            return false;
        }

        catch (IOException e){
            e.printStackTrace();
        }

        return false;
    }

    //getter to retrieve the config info from the config file, and splits it into the correct format
    //adding it to the ArrayList, then returns the ArrayList
    public ArrayList<String> getConfigInfo() {
        fileArray = new ArrayList<String>();

        for (int n = 0; n < 9; n++) {
            try {
                String config = getLine().split(": ")[1].split("/")[0];

                fileArray.add(config);
            }

            catch(IndexOutOfBoundsException e) {

            }
        }

        return fileArray;
    }

    //creates a new cyclic fixed object and throws exception
    public CyclicFixed cyclicFixedOne() throws Exception {

        // assigns it to null so it's empty
        CyclicFixed cfOne = null;

        //gets the necessary information from the config file
        try {

            float f = Float.parseFloat(fileArray.get(3));

            int i = Integer.parseInt(fileArray.get(4));

            try {

                //assigns the information to the correct parameters of the appliance
                cfOne = new CyclicFixed(fileArray.get(0), f, i);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        //catches the out of bounds exception
        catch (IndexOutOfBoundsException e) {

        }

        //catches the exception for when the format of the file is bad, i.e. there is information missing
        catch(NumberFormatException e) {

            throw new Exception ("The file is formatted badly");

        }

        return cfOne;

    }

    //This method assigns an appliance to one of the subclass types
    public CyclicVaries newCyclicVaries () {

        CyclicVaries cvOne = null;

        try {

            int iOne = Integer.parseInt(fileArray.get(3));

            int iTwo = Integer.parseInt(fileArray.get(4));

            int iThree = Integer.parseInt(fileArray.get(5));

            try {

                cvOne = new CyclicVaries(fileArray.get(0), iOne, iTwo , iThree);

            }
            catch (Exception e) {

                e.printStackTrace();

            }

        }

        catch (IndexOutOfBoundsException e) {

        }

        return cvOne;

    }

    public RandomFixed newRandomFixed () {

        RandomFixed rfOne = null;

        try {

            float f = Float.parseFloat(fileArray.get(3));

            int i = Integer.parseInt(fileArray.get(4).split("1 in ")[1]);

            try {

                rfOne = new RandomFixed(fileArray.get(0), f , i);
            }

            catch (Exception e) {

                e.printStackTrace();

            }

        }

        catch (IndexOutOfBoundsException e) {

        }

        return rfOne;

    }

    public RandomVaries newRandomVaries () {

        RandomVaries rvOne = null;

        try {

            int iOne = Integer.parseInt(fileArray.get(3));

            int iTwo = Integer.parseInt(fileArray.get(4));

            int iThree = Integer.parseInt(fileArray.get(5).split("1 in ")[1]);

            try {

                rvOne = new RandomVaries(fileArray.get(0), iOne , iTwo, iThree);
            }
            catch (Exception e) {

                e.printStackTrace();

            }

        }

        catch (IndexOutOfBoundsException e) {

        }

        return rvOne;

    }

    public void getSubclass() throws Exception {

        while (fileIsReady() == true) {

            try {
                System.out.println(getConfigInfo());

                String subclass = fileArray.get(1);

                String meter = fileArray.get(2);

                //reads the config file to find what type of appliance it is and what meter it should be allocated to
                if ( subclass.equals("CyclicFixed") ) {


                    if (meter.equals("electric")) {

                        addElectricalAppliance(cyclicFixedOne());

                    }

                    if (meter.equals("water")) {

                        addWaterAppliance(cyclicFixedOne());

                    }

                }

                //reads the config file to find what type of appliance it is and what meter it should be allocated to
                if ( subclass.equals("CyclicVaries") ) {


                    if (meter.equals("electric")) {

                        addElectricalAppliance(newCyclicVaries());

                    }

                    if (meter.equals("water")) {
                        addWaterAppliance(newCyclicVaries());

                    }

                }

                //reads the config file to find what type of appliance it is and what meter it should be allocated to
                if ( subclass.equals("RandomFixed") ) {


                    if (meter.equals("electric")) {

                        addElectricalAppliance(newRandomFixed());

                    }

                    if (meter.equals("water")) {

                        addWaterAppliance(newRandomFixed());

                    }

                }

                //reads the config file to find what type of appliance it is and what meter it should be allocated to
                if ( subclass.equals("RandomVaries") ) {


                    if (meter.equals("electric")) {

                        addElectricalAppliance(newRandomVaries());

                    }

                    if (meter.equals("water")) {

                        addWaterAppliance(newRandomVaries());

                    }

                    if (meter.equals("gas")) {

                        addGasAppliance(newRandomVaries());
                    }

                }


            }

            //catches the exception of an empty line in the config file
            catch (NullPointerException e) {

            }

        }

    }



    // method that adds an electrical appliance to the appliances ArrayList
    public void addElectricalAppliance(Appliance a){
       
    	appliances.add(a);
    	a.setMeter(electricity);

    }

    // method that adds an water appliance to the appliances ArrayList
    public void addWaterAppliance(Appliance a){

        appliances.add(a);
        a.setMeter(water);

    }

    //method that adds a gas appliance to the appliances ArrayList
    public void addGasAppliance(Appliance a) {

        appliances.add(a);
        a.setMeter(gas);
    }

    //method that removes an appliance from the ArrayList given the name of the appliance
    public void removeAppliance(Appliance a){

        Iterator<Appliance> itr = appliances.iterator();

        while(itr.hasNext()) {
            Appliance x = itr.next();

            if (a.equals(x)) {

                itr.remove();
            }
        }

    }

    //method which returns the number of appliances in the appliances ArrayList
    public int numAppliances(){
        return appliances.size();
    }

    //activate method which by default is set to run the simulation for 7 days, and return the total cost of all meters
    public double activate() {

        double electricCost = 0;
        double waterCost = 0;
        double gasCost = 0;

        for (int j = 0; j < 168; j++) {

            for (int i = 0; i < appliances.size(); i++) {

                appliances.get(i).timePasses();

            }


            electricCost = electricCost + electricity.report();
            waterCost = waterCost + water.report();
            gasCost = gasCost + gas.report();


        }

        return electricCost + waterCost + gasCost;
    }

        //overloaded activate method which takes a parameter n for the number of hours that the simulation should be run
        public double activate(int n) {

            double electricCost = 0;
            double waterCost = 0;
            double gasCost = 0;

            for (int j = 0; j < n; j++) {

                for (int i = 0; i < appliances.size(); i++) {

                    appliances.get(i).timePasses();

                }



                electricCost = electricCost + electricity.report();
                waterCost = waterCost + water.report();
                gasCost = gasCost + gas.report();


            }
            return electricCost + waterCost + gasCost;


    }



}
