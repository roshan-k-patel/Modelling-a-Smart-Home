import java.util.Random;

public class RandomVaries extends Appliance {

    //member variables
    private int probabilityOn;
    private int maxUnitsConsumed;
    private int minUnitsConsumed;

    //constructor which initalises member variables and inherits from the abstract class appliance, and throws the
    //exception incase the probability entered is greater than 100%
    public RandomVaries(String name, int minUnitsConsumed, int maxUnitsConsumed, int probabilityOn) throws Exception {

        super(name);
        this.maxUnitsConsumed = maxUnitsConsumed;
        this.minUnitsConsumed = minUnitsConsumed;
        this.probabilityOn = probabilityOn;

        if (probabilityOn < 1) {

            throw new Exception(probabilityOn + " The probability cannot be greater than 100%");
        }

    }

    @Override
    //overrided timePasses method for the randomVaries class which says that if the number chosen is the same as the
    //probability parameter it will tell meter to consume units. e.g. for 6 the probability is 1/6, so if the number
    //rolled is 6 then the units will be consumed from the meter
    public void timePasses() {

        Random x = new Random();

        if (probabilityOn != x.nextInt((probabilityOn - 1) + 1) + 1) {
            return;
        }
        if (maxUnitsConsumed >= 0 && minUnitsConsumed >= 0) {
            unitsConsumedPerHour = x.nextInt((maxUnitsConsumed - minUnitsConsumed) + 1) + minUnitsConsumed;
            tellMeterToConsumeUnits();
        }

        if (maxUnitsConsumed < 0 && minUnitsConsumed < 0) {
            unitsConsumedPerHour = -1 * (x.nextInt(-1 * maxUnitsConsumed - 1) - minUnitsConsumed);
            tellMeterToConsumeUnits();
        }
    }

}