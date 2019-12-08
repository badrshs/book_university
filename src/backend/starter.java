package backend;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import backend.entity.Category;
import backend.entity.Users;
import backend.model._Category;
import backend.model.Model;
import backend.model._Order;
import backend.model._OrderItems;
import backend.model._Users;
 
 
public class starter {
 
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException, SQLException {
		_Category _category = new _Category();
		Category[] category =   (Category[]) _category.get();
		System.out.println(category[0].GetBooks()[0]);
		//`name`, `surname`, `email`, `password`, `created_at`, `card_n.umber`, `cvv_code`
		/*
		Map<String, String> names = new HashMap<>();		
		 names.put("name", "badr");
		 names.put("surname", "salem");
		 names.put("email", "qor@qer.com");
		 names.put("password", "cherw");
		 names.put("card_number", "123");
		 names.put("cvv_code", "321");
		 usres.create(names);

		 */
		/*
		Map<String, String> names = new HashMap<>();		
		 names.put("name", "badr123");
		 usres.update(names,4);*/

		// usres.delete(2);
 		

	 
	}

}
