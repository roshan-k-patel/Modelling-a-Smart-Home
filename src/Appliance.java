
public abstract class Appliance {
	
	//Variables Used 
	protected String name;
	
	Meter meterOne;
	
	float unitsConsumedPerHour;
	

	//Constructor that defines the parameters and assigns them to variables
	public Appliance(String name) {
		
		this.name = name;
	
	}

	//Setter method
	public void setMeter(Meter meter) {
		
		meterOne = meter;
	
	}
	
	//Abstract method used in subclasses
	public abstract void timePasses();
	
	//Method that initiates the meter / tell it to consume units
	protected void tellMeterToConsumeUnits() {
		
		meterOne.consumeUnits(unitsConsumedPerHour);
	
	}

	//Getter method
	public String getName() {
		
		return name;
	
	}

}