
public class CyclicFixed extends Appliance {
	
	//Variables used 
	protected int timeOn;
	protected int time = 0;
	
	//Constructor that defines the parameters and assigns them to variables
	public CyclicFixed(String name, float unitsConsumedPerHour, int timeOn) throws Exception {
		super(name);
		this.unitsConsumedPerHour = unitsConsumedPerHour;
		this.timeOn = timeOn;

		//Throws an exception if the period is greater than 24 or has a negative value
		if (timeOn < 1 || timeOn > 24) {
			
			throw new Exception( timeOn + "The appliance can only be active between 1 and 24 hours");
		
		}
		
	} 
	
	//method that passes 1 hour of time only if the time is still less than the specified time on
	public void timePasses() {
		if (time < timeOn) {
			tellMeterToConsumeUnits();
		}

		time ++;	
		
		//When the day has finished this if statement resets the time back to 0
		if(time == 24 ) {
			
			time = 0;
		}
	}
}