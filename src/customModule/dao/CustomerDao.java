package customModule.dao;

import java.util.List;
import java.util.Map;

import customModule.entity.Customer;

public interface CustomerDao {

	Customer getCustomerByPhone(String customPhone);

	Customer getCustomerByName(String customName);

	Customer getCustomerByMail(String customMail);

	Customer getCustomer(Map map);

	void addCustomer(Customer cust);

	void modifyCustomer(Customer cust);

	void addWannasee(int movieId, int customNumb);

	List<Integer> getWannaseeMovieId(int customNumb);
	
	List<String> getMovieByname(List<Integer> wannaseeMovieId);
	
	void deleteWannasee(int movieId, int customNumb);

	void addMovieComm(Integer movieId, int customNumb, String commentContent);

	List<String> getMovieCommBycustomNumb(int customNumb);

	List<String> getMovieComm(Integer movieId);
	
	void modifyMovieComm(int commentNumb, String commentContent);

	void deleteMovieComm(int commentNumb);

}
