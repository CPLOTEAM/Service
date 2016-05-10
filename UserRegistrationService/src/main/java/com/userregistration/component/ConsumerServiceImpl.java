package com.userregistration.component;

import com.dbconnection.AccessDBConnection;
import com.model.Consumer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class ConsumerServiceImpl implements IConsumerServiceDao {
	
	
	public Consumer getPersonDetail(Integer id) throws ClassNotFoundException, SQLException, IOException{
		Consumer p = new Consumer();
		Connection con = AccessDBConnection.getDbCon();
		System.out.println("USERID value");
		String selectSQL = "SELECT CONSUMERID FROM CONSUMERS";
		System.out.println("selectSQL");
		PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			String userid = rs.getString("CONSUMERID");
			System.out.println("USERID"+userid);
		}
		return p;
	}

	public int addNewUser(Consumer consumerDetails) throws IOException, SQLException {
		Connection con = AccessDBConnection.getDbCon();

		String query = "insert into CONSUMERS (" 
				+"FIRSTNAME, LASTNAME, ADDRESS, PHONENO, EMAILID, PASSWORD )"
                    + "values (?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
	      preparedStmt.setString (1, consumerDetails.getFirstName());
	      preparedStmt.setString (2, consumerDetails.getFirstName());
	      preparedStmt.setString   (3, consumerDetails.getAddress());
	      preparedStmt.setString(4, consumerDetails.getPhoneno());
	      preparedStmt.setString    (5, consumerDetails.getEmailId());
	      preparedStmt.setString    (6, consumerDetails.getPassword());
	 	 
	      preparedStmt.execute();
	      ResultSet rs = preparedStmt.getGeneratedKeys();
	      int newCustomerId = 0;
	      if(rs != null && rs.next()){
              System.out.println("Generated Emp Id: "+rs.getInt(1));
              newCustomerId = rs.getInt(1);
          }
        return newCustomerId;
	}

	public boolean modifyAddedUser(Consumer consumerDetails) throws IOException,
			SQLException {
		
		boolean updatedFlag = false;
		Connection con = AccessDBConnection.getDbCon();

		String query = "UPDATE CONSUMERS SET FIRSTNAME = ?, LASTNAME = ?," +
				"ADDRESS=?, PHONENO=?, EMAILID=?, PASSWORD=?   WHERE CONSUMERID = ?";
		
		PreparedStatement ps = con.prepareStatement(query);
			 
			    // set the preparedstatement parameters
			    ps.setString(1,consumerDetails.getFirstName());
			    ps.setString(2,consumerDetails.getLastName());
			    ps.setString(3,consumerDetails.getAddress());
			    ps.setString(4,consumerDetails.getPhoneno());
			    ps.setString(5,consumerDetails.getEmailId());
			    ps.setString(6,consumerDetails.getPassword());
			    ps.setInt(7,consumerDetails.getConsumerId());
			 
			    // call executeUpdate to execute our sql update statement
			    int rows = ps.executeUpdate();
			    System.out.println("Value1");
			    System.out.println("Value");
			    if(rows!=0){
			    	updatedFlag= true;
			    }
			    System.out.println("Value"+updatedFlag);
			    ps.close();
		return updatedFlag;
	}

	public boolean deletedAddedUser(Integer consumerId) throws IOException,
			SQLException {
		
		boolean deletedFlag = false;
		Connection con = AccessDBConnection.getDbCon();
		PreparedStatement createPlayer = con.prepareStatement("DELETE FROM CONSUMERS WHERE CONSUMERID=?");
        createPlayer.setInt(1, consumerId);
        int row = createPlayer.executeUpdate();
		System.out.println("Row number" + row);
		if(row!=0){
			deletedFlag= true;
	    }
		return deletedFlag;
	}
}

