package com.loginjones.examples.ListFromDb;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.loginjones.examples.ListFromDb.GetUsers;
import com.loginjones.examples.ListFromDb.GetUsers.User;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersTest
{
	String testsql="SELECT u.ID,c.CITY FROM users U, cities C WHERE U.id = C.user_id order by 1";

	@Before
	public void setUp() throws Exception
	{
		
		when(ds.getConnection()).thenReturn(con);
		when(con.prepareStatement(testsql)).thenReturn(ps);
		when(ps.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
		when(rs.getInt("ID")).thenReturn(1).thenReturn(1).thenReturn(1).thenReturn(2).thenReturn(2);
		when(rs.getString("CITY")).thenReturn("Toronto").thenReturn("London").thenReturn("NY").thenReturn("LA").thenReturn("Tokyo");
	}

	@After
	public void tearDown() throws Exception
	{
	}
	
	@Mock
	DataSource ds;
	
	@Mock
	Connection con;
	
	@Mock
	PreparedStatement ps;
	
	@Mock
	ResultSet rs;
	
	@Test
	public void testJson()throws Exception
	{
		GetUsers getUsers=new GetUsers();
		getUsers.setDs(ds);
		String jsonString=getUsers.getUsersJson();
		System.out.println(jsonString);
	}

	@Test
	public void testGetUsers() throws SQLException
	{
		GetUsers getUsers=new GetUsers();
		getUsers.setDs(ds);
		List<User> users=getUsers.getUsers();
		System.out.println(users.size());
		assertTrue(users.size()==2);
		
	}
	
	
	
	

}
