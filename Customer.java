public class Customer{

	private String savingsID;
	private float savingsBalance =  0;
	private String currentID;
	private float currentBalance = 0;

	public Customer(){
		this.savingsID = savingsID;
		this.savingsBalance = savingsBalance;
		this.currentID = currentID;
		this.currentBalance = currentBalance;
	}

	public String getSavingsID(){
		return savingsID;
	}

	public float getSavingsBalance(){
		return savingsBalance;
	}

	public String getCurrentID(){
		return currentID;
	}

	public float getCurrentBalance(){
		return currentBalance;
	}

	public void setSavingsID(String ID){
		this.savingsID = ID;
	}

	public void setCurrentID(String ID){
		this.currentID = ID;
	}


	public void setSavingsBalance(float value){
		this.savingsBalance = value;
	}

	public void setCurrentBalance(float value){
		this.currentBalance = value;
	}
}	
