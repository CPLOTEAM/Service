package com.userregistration.component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.dbconnection.AccessDBConnection;
import com.model.Claim;


@Component
public class ClaimSearchServiceImpl implements IClaimSearchServiceDao{
	public Claim getClaimDetail(Integer id) throws ClassNotFoundException, SQLException, IOException{
		Claim p = new Claim();
		Connection con = AccessDBConnection.getDbCon();
		System.out.println("ClaimID value");
		String selectSQL = "SELECT CLAIMID,TYPE,STATUS FROM CLAIMS WHERE CLAIMID='"+id+"'";
		
		PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			String claimid = rs.getString("CLAIMID");
			String claimType = rs.getString("TYPE");
			String claimStatus = rs.getString("STATUS");
			p.setClaimid(new Integer(claimid));
			p.setClaimtype(claimType);
			p.setClaimStatus(claimStatus);
			
			System.out.println("CLAIMID"+claimid);
			System.out.println("TYPE"+claimType);
			System.out.println("STATUS"+claimStatus);
		}
		return p;
	}

}
