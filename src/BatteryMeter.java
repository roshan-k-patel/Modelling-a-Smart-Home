public class BatteryMeter extends Meter {

    //Variable for the battery
    protected Battery cell;

        //Constructor for the battery which inherits from Meter and creates a battery of a max capacity 80
        public BatteryMeter(String utilityName, double unitCost, float meterReading ){

          super(utilityName, unitCost, meterReading);

          cell = new Battery(80);

        }

        //Report method to return the meter reading and cost
        public double report(){

            //if the meterReading is less than zero, then the charge is added to the charge of the battery
            if (meterReading < 0 ){
                cell.addCharge(meterReading * -1);
                System.out.println("The battery's current charge is " + cell.getChargeNow());
                meterReading = 0;
                return meterReading;
            }

            //if the meter reading is positive and the battery has charge, charge is taken from the battery
            if (meterReading > 0 && cell.getChargeNow() > 0) {
                System.out.println("The battery has lost " + cell.getChargeNow() +  " units");
                this.meterReading = this.meterReading - cell.getChargeNow();
                meterReading = 0;
                return meterReading;
            }



            //Calculates the total cost of electricity used in the battery meter
            double cost =  meterReading * unitCost;

            //System output to inform the user how what has consumed x amount of units and the cost
            System.out.println(utilityName + ", has consumed " + meterReading+ " units" + ", for a total of $" + cost);

            meterReading = 0;

            return cost;
        }

        //The consume units method that updates the meter reading
        public void consumeUnits(float unitsConsumedPerHour){

            meterReading = meterReading + unitsConsumedPerHour;

        }
    }


