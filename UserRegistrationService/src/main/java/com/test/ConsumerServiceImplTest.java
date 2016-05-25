package com.test;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.userregistration.component.ConsumerServiceImpl;

public class ConsumerServiceImplTest {

	/**
	 * @throws IOException
	 * @throws SQLException
	 */
	@Test
	public void deleteUser() throws IOException, SQLException{
		
		ConsumerServiceImpl mockClaim = new ConsumerServiceImpl();
		boolean checkClaim = mockClaim.deletedAddedUser(1);
		Assert.assertEquals(checkClaim,true);
		
		
	}

	/**
	 * @throws IOException
	 * @throws SQLException
	 */
	@Test
	public void deleteUserNegative() throws IOException, SQLException{
		
		ConsumerServiceImpl mockClaim = new ConsumerServiceImpl();
		boolean checkClaim = mockClaim.deletedAddedUser(100);
		Assert.assertEquals(checkClaim,false);
		
		
	}
}
