public class Battery {

    //member variables that store that maximum charge capacity and current charge of the battery
   protected float maxCapacity;
   protected float chargeNow;

    //constructor for the battery which defines the max capacity of the battery
    public Battery(float maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    // sets the charge to the float input by the user
    public void addCharge(float number){
        this.chargeNow = this.chargeNow + number;

        //if statement that makes sure the battery does not go over it's capacity
        if (this.chargeNow > this.maxCapacity) {
            this.chargeNow = this.maxCapacity;
        }
    }

    //getter method for the current charge of the battery
    public float getChargeNow() {
        return this.chargeNow;
    }

}
