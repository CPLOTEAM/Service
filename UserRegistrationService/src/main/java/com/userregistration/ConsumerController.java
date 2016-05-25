package com.userregistration;

import com.model.Claim;
import com.model.Consumer;
import com.model.Status;
import com.userregistration.component.IClaimSearchServiceDao;
import com.userregistration.component.IConsumerServiceDao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class ConsumerController {
	@Autowired
	private IConsumerServiceDao personService;
	@Autowired
	private IClaimSearchServiceDao claimService;
	
	@RequestMapping(value="/person",produces=MediaType.APPLICATION_XML_VALUE)
	public Consumer getPersonDetail(@RequestParam(value = "id",required = false,
	                                                    defaultValue = "0") Integer id) throws ClassNotFoundException, IOException, SQLException {
		Consumer p = personService.getPersonDetail(id);
		return p;
	}
	
	@RequestMapping(value="/person",produces=MediaType.APPLICATION_JSON_VALUE)
	public Consumer getPersonDetailJson(@RequestParam(value = "id",required = false,
	                                                    defaultValue = "" +
	                                                    		"0") Integer id) throws ClassNotFoundException, IOException, SQLException {
		Consumer p = personService.getPersonDetail(id);
		return p;
	}
	
	@RequestMapping(value="/consumesdata",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public void setPersonDetailJson(@RequestBody Consumer json) {
		
	        System.out.println("Consumes");
	}
	@RequestMapping(value="/addnewuser",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addNewUser(@RequestBody Consumer consumerDetails) throws IOException, SQLException  {

		int uniqueIdentifierNum=-1;
		try {
			uniqueIdentifierNum = personService.addNewUser(consumerDetails);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IOException();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException("Message" + e.getMessage());
		}
		return new Status(uniqueIdentifierNum,0, true, "Added Successfully");
	}
	
	@RequestMapping(value="/modifyaddeduser",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status modifyAddedUser(@RequestBody Consumer consumerDetails) throws IOException, SQLException {

		boolean flag = false;
		int id = consumerDetails.getConsumerId();
		String message = null;
		try {
			flag = personService.modifyAddedUser(consumerDetails);
			if (flag){
				message = "Updated Successfully";
			}else{
				message = "No Record Found";
				id = -1;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IOException();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException("Message" + e.getMessage());
		}
		return new Status(id, 0, flag, message);
	}
	
	@RequestMapping(value="/deleteuser",method=RequestMethod.DELETE,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status deleteuser(@RequestParam Integer id) throws IOException, SQLException {

		boolean flag = false;
		String message = null;
		try {
			flag = personService.deletedAddedUser(id);
			if (flag){
				message = "Deleted Successfully";
			}else{
				message = "No Record Found";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IOException();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException("Message" + e.getMessage());
		}
		return new Status(id, 0, flag, message);
	}
	
	/**
	 * Claim Search service
	 */
	@RequestMapping(value="/claimSearch",produces=MediaType.APPLICATION_JSON_VALUE)
	public Claim getClaimDetailJson(@RequestParam(value = "id",required = false,
	                                                    defaultValue = "" +
	                                                    		"0") Integer id) throws ClassNotFoundException, IOException, SQLException {
		Claim p = claimService.getClaimDetail(id);
		System.out.println("  **** id *** "+id);
		return p;
	}
	
	@ExceptionHandler(Exception.class)
	public Status myError(HttpServletRequest request, Exception exception) {
		Status error=new Status(0, null);
		error.setUniqueIdentifierId(-1);
		error.setFlag(false);
	    error.setCode(HttpStatus.BAD_REQUEST.value());
	    error.setMessage(exception.getLocalizedMessage());
	    if(exception.getLocalizedMessage().contains("index violation; CONSUMERS_EMAILID table: CONSUMERS")){
	    	error.setMessage("Email Adress Already Exist");
	    }
	    return error;
	}
	
	@RequestMapping(value="/validation",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody Status validation(@RequestParam(value = "EmailId") String EmailId,
			@RequestParam(value="Password") String Password )
			 throws ClassNotFoundException, IOException, SQLException 
	{
		boolean flag = false;
		String message = null;
		System.out.println("Email Id "+ EmailId + "Password" + Password);
		try {
			flag = personService.checkPersonDetail(EmailId,Password);
			if (flag){
				message = "Valid";
			}else{
				message ="Invalid    User Name / Password";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
}
		return new Status(flag, message);
}
}
