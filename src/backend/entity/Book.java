package backend.entity;

public class Book extends Base{

	/*`id`, `name`, `description`, `category_id`, `photo`, `writer`, `quantity`, `in_stock`, `price
 * 
 */
	public int id ;
	public int quantity ;
	public int in_stock ;
	public int category_id ;
	public double price ;
	public String description ;
	public String name ;
	public String photo ;
	public String writer ;
	private String categoryName;
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", quantity=" + quantity + ", in_stock=" + in_stock + ", category_id=" + category_id
				+ ", price=" + price + ", description=" + description + ", name=" + name + ", photo=" + photo
				+ ", writer=" + writer + "]";
	}
	
	public String GetCategoryName() {
		return  categoryName;
	}	
	public void SetCategoryName(String name ) {
		  categoryName = name;
	}
}
