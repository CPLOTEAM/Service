package com.userregistration;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Claim;
import com.model.Consumer;
import com.model.Status;
import com.userregistration.component.IClaimSearchServiceDao;
import com.userregistration.component.IConsumerServiceDao;

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
	public @ResponseBody Status addNewUser(@RequestBody Consumer consumerDetails)  {

		int uniqueIdentifierNum=-1;
		try {
			uniqueIdentifierNum = personService.addNewUser(consumerDetails);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("The unique integer value"+ uniqueIdentifierNum);
		return new Status(uniqueIdentifierNum, "Added Successfully");
	}
	
	@RequestMapping(value="/modifyaddeduser",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status modifyAddedUser(@RequestBody Consumer consumerDetails) {

		boolean flag = false;
		try {
			flag = personService.modifyAddedUser(consumerDetails);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Status(flag, "Updated Successfully");
	}
	
	@RequestMapping(value="/deleteuser",method=RequestMethod.DELETE,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status deleteuser(@RequestParam Integer id) {

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
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Status(flag, message);
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
	
	
}