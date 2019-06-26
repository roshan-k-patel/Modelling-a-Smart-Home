public class Main {

    public static void main(String[] args) throws Exception {

        BatteryMeter electricityMeter = new BatteryMeter("Electricity", 0.013, 0);
        Meter waterMeter = new Meter("Water",0.002,0);
        GasMeter gasMeter = new GasMeter("Gas", 0.0225,100);
        House maison = new House(electricityMeter, waterMeter,gasMeter, "config.txt");




        maison.getSubclass();

        //activates the simulation for the default 7 days, the simulation has been intialised with the extension
        //Gas meter included
        System.out.println("The total cost of the simulation is " + "$" + maison.activate(5));




    }
}
