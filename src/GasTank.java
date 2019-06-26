public class GasTank {

    //member variables that store that maximum gas capacity and percentage of gas in the gas tank
    protected float maxCapacity;
    protected float percentageGasRemaining;

    //constructor for the gas tank which initialises the member variables
    public GasTank(float maxCapacity){
        this.maxCapacity = maxCapacity;
        this.percentageGasRemaining = maxCapacity;
    }

    // sets the gas to the float input by the user and depletes the gas used from the total
    public void depleteGas(float number){
        this.percentageGasRemaining = this.percentageGasRemaining - number;

    }

    //getter method for the current gas remaining in the tank
    public float getPercentageNow() {
        return this.percentageGasRemaining;
    }

}
