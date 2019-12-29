package backend.entity;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import backend.controller.Helper;
import backend.controller.Router;
import backend.model._Users;

public class Auth {
	public static boolean isAuth = false;
	public static Users users;
	public static String ErrorMessage = "";
	private static String email;
	private static String password;

	public Auth(Users users) {
		this.users = users;
		isAuth = true;
	}

	public static int id() {
		if (!isAuth)
			return -1;
		return users.id;
	}

	public static boolean isAdmin() {
		if (!isAuth)
			return false;
		return users.is_admin == 1;
	}

	public Users getAuth() {
		return users;
	}

	public static void UpdateInformation(String name, String surname, String email, String password, String card_num,
			String cvv) {
		_Users _user = new _Users();
		Map<String, String> user = new HashMap<>();
		user.put("name", name);
		user.put("surname", surname);
		user.put("email", email);
		user.put("password", Helper.encryption(password));
		if (card_num.length() == 16)
			user.put("card_number", Helper.encryption(card_num));
		if (cvv.length() == 3)
			user.put("cvv_code", Helper.encryption(cvv));
		_user.update(user, users.id);
		refresh();
	}

	public static void refresh() {
		try {
			_Users _user = new _Users();
			Users[] response = (Users[]) _user.find(Auth.users.id);
			users = response[0];
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Auth(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public boolean check() {
		try {
			_Users _user = new _Users();
			Users[] users = (Users[]) _user.where("email", "=", email).and()
					.where("password", "=", Helper.encryption(password)).first();
			boolean status = users.length > 0;
			if (status) {
				new Auth(users[0]);
				MainCart.RefreshUserIdAfterSuccessfullyLogin();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void logout() {
		users = null;
		isAuth = false;
		email = null;
		password = null;
		MainCart.RefreshUserIdAfterSuccessfullyLogout();

		Router.ShowCategoriesList();
	}

	public static void CreateUser(String name, String surname, String email, String password, String card_num,
			String cvv) {
		_Users _user = new _Users();
		Map<String, String> user = new HashMap<>();
		user.put("name", name);
		user.put("surname", surname);
		user.put("email", email);
		user.put("password", Helper.encryption(password));
		if (card_num.length() == 16)
			user.put("card_number", Helper.encryption(card_num));
		if (cvv.length() == 3)
			user.put("cvv_code", Helper.encryption(cvv));
		_user.create(user);
		new Auth(email, password).check();
	}
}
