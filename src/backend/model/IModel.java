package backend.model;

public interface IModel<Base> {
	
	public   String Queury = "";
 	
	IModel where(String columns,String condition,String value);
	IModel and();
	IModel or();
	Object first();
	Object find(int id);
	Object get();
	boolean delete(int id);
	Object delete(int id,String column);
 	
	
	
	
}
