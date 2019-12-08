package backend.entity;

public class Cart {
	public int user_id;
	public int book_id;
	public int quantity;
	private Users _User;
	private Book _Book;
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
}
