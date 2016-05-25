package com.userregistration.component;

import com.model.Consumer;

import java.io.IOException;
import java.sql.SQLException;

public interface IConsumerServiceDao {
  public Consumer getPersonDetail(Integer id) throws ClassNotFoundException, SQLException, IOException;
  public int addNewUser(Consumer consumerDetails) throws IOException, SQLException;
  public boolean modifyAddedUser(Consumer consumerDetails) throws IOException, SQLException;
  public boolean deletedAddedUser(Integer cunsumerId) throws IOException, SQLException;
  
  public boolean checkPersonDetail(String emailId, String password) throws ClassNotFoundException, SQLException;
}
