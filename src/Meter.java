public class Meter {
	
	//member variables for the meter class
	protected String utilityName;
	protected double unitCost;
	protected float meterReading;
	
	//Constructor that intialises the member variables
	public Meter(String utilityName, double unitCost, float meterReading ){
		
		this.utilityName = utilityName;
		this.unitCost = unitCost;
		this.meterReading = meterReading;
		
	}

	//report method for the meter, returns the cost of the meter and then resets it to 0
	public double report(){

		if (meterReading <= 0 ){
			
			meterReading = 0;
			double x =0;
			return x ;
		}
		
		//Calculates the total cost 
		double cost =  meterReading * unitCost;
		

		//print line for the user which shows the consumption of units and total cost
		System.out.println(utilityName + ", has consumed " + meterReading+ " units" + ", for a total of $" + cost);
		meterReading = 0;
		
		return cost;	
	}

	//Consume units method which updates the meter reading
	public void consumeUnits(float unitsConsumedPerHour){
		
		meterReading = meterReading + unitsConsumedPerHour;
		
	}
}
	