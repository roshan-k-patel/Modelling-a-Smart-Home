import java.util.Random;

// The random fixed appliance type which extends the appliance abstract class
public class RandomFixed extends Appliance {

    //member variable
    private int probabilityOn;

    //constructor which intialises variables and inherits some from the parent class, also includes the exception
    //if the probability entered is greater than 100%
    public RandomFixed(String name, float unitsConsumed, int probabilityOn) throws Exception {

        super(name);
        this.unitsConsumedPerHour = unitsConsumed;
        this.probabilityOn = probabilityOn;
        if (probabilityOn < 1 ) {
            throw new Exception( probabilityOn + "The probability cannot be greater than 100%");

        }

    }

    @Override
    //overrided timePasses method for the randomFixed class which says that if the number chosen is the same as the
    //probability parameter it will tell meter to consume units. e.g. for 6 the probability is 1/6, so if the number
    //rolled is 6 then the units will be consumed from the meter
    public void timePasses() {

        Random x = new Random();

        if ( x.nextInt(( probabilityOn - 1) + 1) + 1 == probabilityOn) {

            tellMeterToConsumeUnits();

        }

    }

}