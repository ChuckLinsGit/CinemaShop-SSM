package customModule.service;

import java.util.List;

import customModule.entity.Customer;

public interface CustomerService {


	boolean existsByPhone(String customPhone);

	boolean existsByName(String customName);

	boolean existsByMail(String customMail);

	boolean ifExists(String loginWay, String loginWayValue);

	boolean checkPassword(String loginWay, String loginWayValue, String password);

	Customer getCusotmer(String loginWay, String loginWayValue);
	
	void changeCustomer(Customer cust);

	void addCustomer(Customer cust);

	void addWannasee(int movieId, int customNumb);

	List<String> getWannasee(int customNumb);
	
	void cancelWannasee(int movieId, int customNumb);

	void addMovieComm(Integer movieId, String commentContent, int customNumb);

	void changeMovieComm(int commentNumb, String commentContent);

	void delMovieComm(int commentNumb);

	List<String> getMyMovieComm(int customNumb);

	List<String> getThisMovieComm(Integer movieId);


}
