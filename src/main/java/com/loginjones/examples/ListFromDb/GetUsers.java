package com.loginjones.examples.ListFromDb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class GetUsers 
{

	public class User{
	    Integer id;
	    ArrayList<String> cities=new ArrayList<String>();
		public Integer getId()
		{
			return id;
		}
		public void setId(Integer id)
		{
			this.id = id;
		}
		public ArrayList<String> getCities()
		{
			return cities;
		}
		public void setCities(ArrayList<String> cities)
		{
			this.cities = cities;
		}
		public void add(String city)
		{
			cities.add(city);
		}
	    
	}
	
	    DataSource ds;
	    String sql="SELECT u.ID,c.CITY FROM users U, cities C WHERE U.id = C.user_id order by 1";
	    
	    public DataSource getDs()
		{
			return ds;
		}

		public void setDs(DataSource ds)
		{
			this.ds = ds;
		}

		public List<User> getUsers() throws SQLException 
		{
			List<User> users=new ArrayList<GetUsers.User>();
			
			Connection connection = ds.getConnection();
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			Integer currentId=-1;
			User currentUser=new User();
			
			while(rs.next())
			{
				Integer id=rs.getInt("ID");
				if (id != currentId)
				{
					//new user
					currentUser=new User();	
					users.add(currentUser);
					currentUser.setId(id);
				}
				currentId=id;
				currentUser.add(rs.getString("CITY"));
			}
			
			return users;

	    }
		
		public String getUsersJson() throws JsonGenerationException, JsonMappingException, IOException, SQLException
		{
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(getUsers());
		}


	    
	    

}
