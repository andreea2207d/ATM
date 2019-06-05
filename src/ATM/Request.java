package ATM;

public class Request {
	private int id;
	private int fromDepositId;
	private int toDepositId;
	private int amount;
	
	
	public Request(int id, int fromDepositId, int toDepositId, int amount) {
		this.id = id;
		this.fromDepositId = fromDepositId;
		this.toDepositId = toDepositId;
		this.amount = amount;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getFromDepositId() {
		return fromDepositId;
	}
	public void setFromDepositId(int fromDepositId) {
		this.fromDepositId = fromDepositId;
	}
	public int getToDepositId() {
		return toDepositId;
	}
	public void setToDepositId(int toDepositId) {
		this.toDepositId = toDepositId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
