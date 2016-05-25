package com.test;

import java.io.IOException;
import java.sql.SQLException;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.model.Claim;
import com.userregistration.component.ClaimSearchServiceImpl;

public class ClaimSearchServiceImplTest {
	
	
	/**
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void checkStatus() throws ClassNotFoundException, SQLException, IOException{
		Claim claim = EasyMock.createMock(Claim.class);
		EasyMock.expect(claim.getClaimStatus()).andReturn("Completed");
		EasyMock.expect(claim.getClaimtype()).andReturn("Profesional");
		EasyMock.expect(claim.getClaimid()).andReturn(1);
		EasyMock.replay(claim);
		ClaimSearchServiceImpl mockClaim = new ClaimSearchServiceImpl();
		Claim checkClaim = mockClaim.getClaimDetail(1);
		Assert.assertEquals(checkClaim.getClaimStatus(),claim.getClaimStatus());
		Assert.assertEquals(checkClaim.getClaimtype(),claim.getClaimtype());
	
	}
	
	/**
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void checkNegativeStatus() throws ClassNotFoundException, SQLException, IOException{
		Claim claim = EasyMock.createMock(Claim.class);
		EasyMock.expect(claim.getClaimStatus()).andReturn("Completed");
		EasyMock.expect(claim.getClaimtype()).andReturn("Institutional");
		EasyMock.expect(claim.getClaimid()).andReturn(1);
		EasyMock.replay(claim);
		ClaimSearchServiceImpl mockClaim = new ClaimSearchServiceImpl();
		Claim checkClaim = mockClaim.getClaimDetail(1);
		Assert.assertEquals(checkClaim.getClaimStatus(),claim.getClaimStatus());
		Assert.assertNotEquals(checkClaim.getClaimtype(),claim.getClaimtype());
	
	}

}
