import java.util.Random;

public class CyclicVaries extends Appliance {

    //member variables for the CyclicVaries class
    protected int timeOn;
    protected int time = 0;
    protected int unitsConsumedPerHourMax;
    protected int unitsConsumedPerHourMin;


    //constructor which inherits from the appliance class, and initialises variables, also includes
    //the check which checks that the time on is
    public CyclicVaries(String name, int minUnitsConsumed, int maxUnitsConsumed, int cycleLength) throws Exception {

        super(name);
        this.unitsConsumedPerHourMin = minUnitsConsumed;
        this.unitsConsumedPerHourMax = maxUnitsConsumed;
        this.timeOn = cycleLength;

        if (cycleLength < 1 || cycleLength > 24) {

            throw new Exception( cycleLength + "The cycle length must be and integer between 1 and 24");

        }


    }

    //The overrided time passes method, which uses the random class to create a random probability that the meter will
    //Consume units
    @Override
    public void timePasses() {

        if (time < timeOn) {

            if (unitsConsumedPerHourMax > 1 && unitsConsumedPerHourMin > 1) {
                Random x = new Random();
                this.unitsConsumedPerHour = x.nextInt((unitsConsumedPerHourMax - unitsConsumedPerHourMin) + 1) + unitsConsumedPerHourMin;
                tellMeterToConsumeUnits();

            }


            if (unitsConsumedPerHourMax < 1 && unitsConsumedPerHourMin < 1) {
                Random x = new Random();
                unitsConsumedPerHour = -1 * (x.nextInt(-1 * unitsConsumedPerHourMax -1) - unitsConsumedPerHourMin);
                tellMeterToConsumeUnits();

            }
        }

        time ++;

        if(time == 24 ) {

            time = 0;

        }

    }

}