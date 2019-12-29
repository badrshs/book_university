package backend;

import java.awt.EventQueue;
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

import backend.controller.Router;
import backend.entity.Category;
import backend.entity.Users;
import backend.model._Category;
import backend.model.Model;
import backend.model._Order;
import backend.model._OrderItems;
import backend.model._Users;
 
 
public class starter {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Router.ShowCategoriesList();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
