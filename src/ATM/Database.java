package ATM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Database {
    private static Connection connect = null;
    private static Boolean init = false;
    
    private static void init() throws Exception {
    	if(!init) {
	    	init = true;
	    	
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	connect = DriverManager
	                 .getConnection("jdbc:mysql://localhost:3306/atm?"
	                         + "user=root&password=");
    	}
    }
    
    private static ResultSet getUserResultSet(String username) throws Exception {
    	Database.init();
    	System.out.println(connect);
    	PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM users WHERE username=?");
        preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();
        
        if(!rs.next() ) {
        	return null;
        } else {
            return rs;	
        }
    }
    
    public static String getPassword(String username) {
    	ResultSet rs;
    	
		try {
			rs = getUserResultSet(username);
			
	    	if(rs == null) {
	    		return null;
	    	} else {
	    		return rs.getString("password");
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    
    }
    
    public static int getID(String username) {
    	ResultSet rs;
		try {
			rs = getUserResultSet(username);
	    	if(rs == null) {
	    		return -1;
	    	} else {
	    		return Integer.parseInt(rs.getString("id"));
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
    }
    
    public static void addUser(String username, String password) {    	
    	try {
    		Database.init();
        	
	        PreparedStatement preparedStatement = connect.prepareStatement("INSERT INTO users(username, password) VALUES (?, md5(?));");
	        preparedStatement.setString(1, username);
	        preparedStatement.setString(2, password);
	        preparedStatement.executeUpdate();
	        
	        int id = Database.getID(username);
	        preparedStatement = connect.prepareStatement("INSERT INTO deposits(user_id, amount) VALUES (?, 2000);");
	        preparedStatement.setInt(1, id);
	        preparedStatement.executeUpdate();
	        
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void addRequest(int fromDepositId, int toDepositId, int amount) {    	
    	try {
    		Database.init();
        	
	        PreparedStatement preparedStatement = connect.prepareStatement("INSERT INTO requests(from_deposit, to_deposit, amount) VALUES (?, ?, ?);");
	        preparedStatement.setInt(1, fromDepositId);
	        preparedStatement.setInt(2, toDepositId);
	        preparedStatement.setInt(3, amount);
	        preparedStatement.executeUpdate();   
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void addFunds(int depositId, int amount) {
		try {
			Database.init();
			
	    	PreparedStatement preparedStatement;
			preparedStatement = connect.prepareStatement("UPDATE deposits SET amount=amount+? WHERE id=?");
		    preparedStatement.setInt(1, amount);
		    preparedStatement.setInt(2, depositId);
		    preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void withdrawFunds(int depositId, int amount) {
		try {
			Database.init();
			
	    	PreparedStatement preparedStatement;
			preparedStatement = connect.prepareStatement("UPDATE deposits SET amount=amount-? WHERE id=?");
		    preparedStatement.setInt(1, amount);
		    preparedStatement.setInt(2, depositId);
		    preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static ArrayList<Deposit> getDeposits(int userId) {    	
    	try {
    		Database.init();
        	
	        PreparedStatement preparedStatement = connect.prepareStatement("SELECT id, amount FROM deposits WHERE user_id=? ORDER BY id ASC");
	        preparedStatement.setInt(1, userId);
	        ResultSet rs = preparedStatement.executeQuery();
	        
	        ArrayList<Deposit> deposits = new ArrayList<Deposit>();
	        while(rs.next()) {
	        	int id = rs.getInt("id");
	        	int amount = rs.getInt("amount");
	        	
	        	deposits.add(new Deposit(id, amount));
	        }
	        
	        return deposits;	        
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public static ArrayList<Request> getRequests(int userId) {    	
    	try {
    		Database.init();
        	
	        PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM deposits INNER JOIN requests ON deposits.id = requests.from_deposit WHERE user_id=?");
	        preparedStatement.setInt(1, userId);
	        ResultSet rs = preparedStatement.executeQuery();
	        
	        ArrayList<Request> requests = new ArrayList<Request>();
	        while(rs.next()) {
	        	int id = rs.getInt("requests.id");
	        	int fromDepositId = rs.getInt("from_deposit");
	        	int toDepositId = rs.getInt("to_deposit");
	        	int amount = rs.getInt("requests.amount");
	        	
	        	requests.add(new Request(id, fromDepositId, toDepositId, amount));
	        }
	        
	        return requests;	        
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public static void deleteRequest(int requestId) {    	
    	try {
    		Database.init();
        	
	        PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM requests WHERE id=?");
	        preparedStatement.setInt(1, requestId);
	        preparedStatement.executeUpdate();	        
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void deleteDeposit(int depositId) {    	
    	try {
    		Database.init();
        	
	        PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM deposits WHERE id=?");
	        preparedStatement.setInt(1, depositId);
	        preparedStatement.executeUpdate();	        
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void newDeposit(int userId) {    	
    	try {
    		Database.init();
        	
	        PreparedStatement preparedStatement = connect.prepareStatement("INSERT INTO deposits(user_id) VALUES (?);");
	        preparedStatement.setInt(1, userId);
	        preparedStatement.executeUpdate(); 
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static Request getRequest(int requestId) {    	
    	try {
    		Database.init();
        	
	        PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM requests WHERE id=?");
	        preparedStatement.setInt(1, requestId);
	        ResultSet rs = preparedStatement.executeQuery();
	        
	        if(rs.next() ) {
	        	int id = rs.getInt("id");
	        	int fromDepositId = rs.getInt("from_deposit");
	        	int toDepositId = rs.getInt("to_deposit");
	        	int amount = rs.getInt("amount");
	        	
	        	return new Request(id, fromDepositId, toDepositId, amount);
	        } else {
	            return null;
	        }
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public static int getDepositAmount(int depositId) {    	;
    	try {
        	Database.init();
	    	PreparedStatement preparedStatement = connect.prepareStatement("SELECT amount FROM deposits WHERE id=?");
	        preparedStatement.setInt(1, depositId);
	        ResultSet rs = preparedStatement.executeQuery();
	        
	        if(!rs.next() ) {
	        	return -1;
	        } else {
	            return rs.getInt("amount");	
	        }
    	} catch(Exception e) {
    		e.printStackTrace();
    		return -1;
    	}
    }
}