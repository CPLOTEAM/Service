package com.userregistration.component;

import java.io.IOException;
import java.sql.SQLException;

import com.model.Claim;


public interface IClaimSearchServiceDao {
	public Claim getClaimDetail(Integer id) throws ClassNotFoundException, SQLException, IOException;

}
