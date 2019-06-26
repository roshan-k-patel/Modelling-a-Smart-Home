public class GasMeter extends Meter {

    //member variable

    protected GasTank tank;

    //Constructor that defines the parameters and assigns them to variables
    public GasMeter(String utilityName, double unitCost, float meterReading) {

        super(utilityName, unitCost, meterReading);

        tank = new GasTank(100);
    }

    //The report method which checks whether the gas tank needs to be auto refilled, also reports the percentage
    //Of the total tank of gas used
    public double report() {

        //if the meterReading is less than zero, then the charge is added to the charge of the battery
        if (tank.getPercentageNow() == 0) {
            System.out.println("The gas tank will be refilled");
            return meterReading;
        }

        //if there is gas left in the tank it deducts it from the total
        if (meterReading > 0 && tank.getPercentageNow() > 0) {
            System.out.println("The tank has lost " + (tank.maxCapacity - tank.getPercentageNow()) + " units");
            System.out.println(tank.getPercentageNow());
            tank.percentageGasRemaining = (tank.percentageGasRemaining - meterReading);
            meterReading = 0;
            return meterReading;
        }


        //Calculates the total cost of the gas used
        double cost = meterReading * unitCost;

        //Reports the percentage of gas consumed from the full tank and the cost of the gas
        System.out.println(utilityName + " has consumed " + meterReading + " percent of the tank for a total of $" + cost);
        double a = tank.maxCapacity - meterReading;
        System.out.println("The remaining gas in the tank is " + a + " refilling tank now");
        meterReading = 0;

        return cost;
    }

    //adds the units consumed to the meter reading
    public void consumeUnits(float unitsConsumedPerHour) {

        meterReading = meterReading + unitsConsumedPerHour;

    }
}
