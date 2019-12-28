package backend.entity;

public class CartItems {
	public int id;
	public int user_id;
	public int book_id;
	public int quantity;
	private Users _User;
	private Book _Book;

	public int InStock() {
		return _Book.in_stock;
	}

	public CartItems(Book book, int quantity) {
		book_id = book.id;
		this.quantity = quantity;
		user_id = Auth.isAuth ? Auth.users.id : -1;
		_User = Auth.isAuth ? Auth.users : null;
		_Book = book;
	}

	public CartItems(Book book, CartItems item) { // only from database to app
		book_id = book.id;
		this.quantity = item.quantity;
		this.id = item.id;
		user_id = Auth.isAuth ? Auth.users.id : -1;
		_User = Auth.isAuth ? Auth.users : null;
		_Book = book;
	}

	public Users get_User() {
		return _User;
	}

	public void set_User(Users _User) {
		this._User = _User;
	}

	public Book get_Book() {
		return _Book;
	}

	public void set_Book(Book _Book) {
		this._Book = _Book;
	}

	public void RemoveUser() {
		this._User = null;
	}
}
