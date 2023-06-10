package product;

import java.util.List;

public interface IAccountGroup extends Groupable {
	
	void add(Groupable groupable);
	void remove(Groupable groupable);
	List<Groupable> getAll();
	String getName();
	void setName(String name);
	void display();
}
