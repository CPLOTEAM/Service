package com.sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dbconnection.AccessDBConnection;
import com.model.Consumer;
import com.userregistration.ConsumerController;
import com.userregistration.component.ConsumerServiceImpl;
import com.userregistration.component.IConsumerServiceDao;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AccessDBConnection.class })
public class TestRunner extends TestCase {

	private MockMvc mockMvc;

	IConsumerServiceDao iConsumerServiceDao = new ConsumerServiceImpl();

	/*
	 * @Mock ConsumerServiceImpl consumerImpl;
	 */
	@Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockStatement;
    
    @Mock
    private PreparedStatement mockClaimStatement;

    @Mock
    private ResultSet mockResultSet;

	@Before
	public void init() throws IOException {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(new ConsumerController())
				.build();
		
		PowerMock.mockStatic(AccessDBConnection.class);
		EasyMock.expect(AccessDBConnection.getDbCon()).andReturn(mockConnection);
		PowerMock.replay(AccessDBConnection.class);
	}

	/*@Test
	public void getAccount() throws Exception {

		PowerMock.mockStatic(AccessDBConnection.class);
		Connection mockconnection = EasyMock.createMock(Connection.class);
		
		PreparedStatement statement = EasyMock.createMock(PreparedStatement.class);
		ResultSet rs = EasyMock.createMock(ResultSet.class);
		
		EasyMock.expect(AccessDBConnection.getDbCon()).andReturn(connection);
		PowerMock.replay(AccessDBConnection.class);

		EasyMock.expect(rs.getInt("CONSUMERID")).andReturn(1);
		EasyMock.expect(rs.getString("FIRSTNAME")).andReturn("12:24:56");
		EasyMock.expect(rs.getString("LASTNAME")).andReturn("03/19/2011");
		EasyMock.expect(rs.getString("EMAILID")).andReturn("VIX1");
		EasyMock.expect(rs.getDouble("PHONENO")).andReturn(Double.valueOf("20.96"));
		EasyMock.expect(rs.getString("ADDRESS")).andReturn("1");
		
		EasyMock.expect(rs.next()).andReturn(true).andReturn(false);
		
		EasyMock.expect(connection.prepareStatement("SELECT * FROM CONSUMERS")).andReturn(statement).anyTimes();
		EasyMock.expect(statement.executeQuery()).andReturn(rs).anyTimes();
		
		EasyMock.expect(AccessDBConnection.getDbCon()).andReturn(mockConnection);
		PowerMock.replay(AccessDBConnection.class);
		//Mockito.when(AccessDBConnection.getDbCon()).thenReturn(mockConnection);
		Mockito.when(mockConnection.prepareStatement("SELECT * FROM CONSUMERS")).thenReturn(mockStatement);
		Mockito.when(mockStatement.executeQuery()).thenReturn(mockResultSet);
		Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
		
		Mockito.when(mockResultSet.getInt("CONSUMERID")).thenReturn(1);
		Mockito.when(mockResultSet.getString("FIRSTNAME")).thenReturn("12:24:56");
		Mockito.when(mockResultSet.getString("LASTNAME")).thenReturn("03/19/2011");
		Mockito.when(mockResultSet.getString("EMAILID")).thenReturn("VIX1");
		Mockito.when(mockResultSet.getString("PHONENO")).thenReturn("20.96");
		Mockito.when(mockResultSet.getString("ADDRESS")).thenReturn("1");

		//PowerMock.replay(AccessDBConnection.class);

		Consumer consumer = iConsumerServiceDao.getPersonDetail(1);
		
		
		// Assert.assertEquals(consumer,consumerImpl.getPersonDetail(id));

		
		 * Consumer consum = iConsumerServiceDao.getPersonDetail(id);
		 
		 this.mockMvc.perform(get("/data/person")
		 .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		  .andExpect(status().isOk());
		 
	}*/
	
	@Test
	public void addNewUserTest() throws Exception {
		Consumer consumer = new Consumer();
		consumer.setFirstName("Ashita");
		consumer.setLastName("Teotia");
		consumer.setAddress("Address");
		consumer.setEmailId("ashita@gmail.com");
		consumer.setPassword("asas");
		consumer.setPhoneno("asasasasa");
		 Mockito.when(mockConnection.prepareStatement("insert into CONSUMERS (" 
				+"FIRSTNAME, LASTNAME, ADDRESS, PHONENO, EMAILID, PASSWORD )"
                    + "values (?, ?, ?, ?, ?, ?)")).thenReturn(mockStatement);

		 Mockito.when(mockStatement.execute()).thenReturn(true);
		 Mockito.when(mockStatement.getGeneratedKeys()).thenReturn(mockResultSet);
		 Mockito.when(mockResultSet.next()).thenReturn(true);
		 Mockito.when(mockResultSet.getInt(1)).thenReturn(1);
		 
		 int generatedId = iConsumerServiceDao.addNewUser(consumer);
			
			
		 
		// Mockito.verify(statement).executeUpdate();
	}
	
	@Test(expected=SQLException.class)
	public void addNewUserTestFail() throws Exception {
		Consumer consumer = new Consumer();
		consumer.setFirstName("Ashita");
		consumer.setLastName("Teotia");
		consumer.setAddress("Address");
		consumer.setEmailId("ashita@gmail.com");
		consumer.setPassword("asas");
		consumer.setPhoneno("asasasasa");
		 Mockito.when(mockConnection.prepareStatement("insert into CONSUMERS (" 
				+"FIRSTNAME, LASTNAME, ADDRESS, PHONENO, EMAILID, PASSWORD )"
                    + "values (?, ?, ?, ?, ?, ?)")).thenReturn(mockStatement);

		 iConsumerServiceDao.addNewUser(consumer);
			
			
		 
		// Mockito.verify(statement).executeUpdate();
	}
	
	@Test
	public void modifyAddedUserTest() throws Exception {
		
		Consumer consumer = new Consumer();
		
		consumer.setConsumerId(10);
		consumer.setFirstName("Ashita");
		consumer.setLastName("Teotia");
		consumer.setEmailId("ashita@gmail.com");
		consumer.setPassword("asas");
		consumer.setPhoneno("asasasasa");
		
		 Mockito.when(mockConnection.prepareStatement("UPDATE CONSUMERS SET FIRSTNAME = ?, LASTNAME = ?," +
					"ADDRESS=?, PHONENO=?, EMAILID=?, PASSWORD=?   WHERE CONSUMERID = ?")).thenReturn(mockStatement);

		 Mockito.when(mockStatement.executeUpdate()).thenReturn(1);
		 Mockito.when(mockResultSet.getInt(1)).thenReturn(1);
		 
		 boolean generatedId = iConsumerServiceDao.modifyAddedUser(consumer);
		 
		// Mockito.verify(statement).executeUpdate();
	}
	
	@Test(expected=SQLException.class)
	public void modifyAddedUserTestFail() throws Exception {
		Consumer consumer = new Consumer();
		consumer.setFirstName("Ashita");
		consumer.setLastName("Teotia");
		consumer.setAddress("Address");
		consumer.setEmailId("ashita@gmail.com");
		consumer.setPassword("asas");
		consumer.setPhoneno("asasasasa");
		consumer.setConsumerId(1);
		 Mockito.when(mockConnection.prepareStatement("UPDATE CONSUMERS SET FIRSTNAME = ?, LASTNAME = ?," +
					"ADDRESS=?, PHONENO=?, EMAILID=?, PASSWORD=?   WHERE CONSUMERID = ?")).thenReturn(mockStatement);
		 
		/* Mockito.when(mockStatement.getGeneratedKeys()).thenReturn(mockResultSet);
		 Mockito.when(mockResultSet.next()).thenReturn(false);
		 Mockito.when(mockResultSet.getInt(1)).thenThrow(SQLException.class);
		 */
		 boolean modifiedVal = iConsumerServiceDao.modifyAddedUser(consumer);
			
			System.out.println("Not Added"+modifiedVal );
		 
		// Mockito.verify(statement).executeUpdate();
	}
	
	@Test
	public void deletedAddedUserTest() throws Exception {
		
		Consumer consumer = new Consumer();
		
		consumer.setConsumerId(10);
		
		 Mockito.when(mockConnection.prepareStatement("DELETE FROM CONSUMERS WHERE CONSUMERID=?")).thenReturn(mockStatement);
		 Mockito.when(mockConnection.prepareStatement("DELETE FROM CLAIMS WHERE CONSUMERID = ?")).thenReturn(mockClaimStatement);
		 
		 Mockito.when(mockStatement.executeUpdate()).thenReturn(1);
		 Mockito.when(mockClaimStatement.executeUpdate()).thenReturn(1);
		 
		 boolean generatedId = iConsumerServiceDao.deletedAddedUser(consumer.getConsumerId());
		 
		// Mockito.verify(statement).executeUpdate();
	}
	
	@Test
	public void deletedAddedUserNullException() throws Exception {
		
		Consumer consumer = new Consumer();
		
		consumer.setConsumerId(10);
		
		 Mockito.when(mockConnection.prepareStatement("DELETE FROM CONSUMERS WHERE CONSUMERID=?")).thenReturn(mockStatement);
		 Mockito.when(mockConnection.prepareStatement("DELETE FROM CLAIMS WHERE CONSUMERID = ?")).thenReturn(mockClaimStatement);
		 
		 Mockito.when(mockStatement.executeUpdate()).thenReturn(1);
		 Mockito.when(mockClaimStatement.executeUpdate()).thenReturn(1);
		 
		 boolean generatedId = iConsumerServiceDao.deletedAddedUser(consumer.getConsumerId());
		 
		// Mockito.verify(statement).executeUpdate();
	}
	
		@Test
			public void checkPersonDetailTest() throws Exception {
				Consumer consumer = new Consumer();
				consumer.setEmailId("ssarav@gmail.com");
				consumer.setPassword("aaaa");
				Mockito.when(mockConnection.prepareStatement("SELECT * FROM CONSUMERS WHERE EMAILID=? AND PASSWORD=?"))
						.thenReturn(mockStatement);

				Mockito.when(mockStatement.executeQuery()).thenReturn(mockResultSet);
				Mockito.when(mockResultSet.next()).thenReturn(true);
				boolean generatedId = iConsumerServiceDao.checkPersonDetail(consumer.getEmailId(), consumer.getPassword());
			}
		
		@Test
		public void checkPersonDetailInvalidCase() throws Exception {
			Consumer consumer = new Consumer();
			consumer.setEmailId("ssarav@gmail.com");
			consumer.setPassword("aaaa");
			Mockito.when(mockConnection.prepareStatement("SELECT * FROM CONSUMERS WHERE EMAILID=? AND PASSWORD=?"))
					.thenReturn(mockStatement);

			Mockito.when(mockStatement.executeQuery()).thenReturn(mockResultSet);
			Mockito.when(mockResultSet.next()).thenReturn(false);
			boolean generatedId = iConsumerServiceDao.checkPersonDetail(consumer.getEmailId(), consumer.getPassword());
		}


			@Test(expected = SQLException.class)
			public void checkPersonDetailTestFail() throws Exception {
				Consumer consumer = new Consumer();
				consumer.setEmailId(null);
				consumer.setPassword(null);
				Mockito.when(mockConnection.prepareStatement("SELECT * FROM CONSUMERS WHERE EMAILID=? AND PASSWORD=?"))
						.thenReturn(mockStatement);
				iConsumerServiceDao.checkPersonDetail(consumer.getEmailId(), consumer.getPassword());

			}

}
