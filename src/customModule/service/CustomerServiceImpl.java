package customModule.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customModule.dao.CustomerDao;
import customModule.entity.Customer;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;

	@Override
	public boolean existsByPhone(String customPhone) {
		Customer customer=customerDao.getCustomerByPhone(customPhone);
		return (customer!=null);
	}

	@Override
	public boolean existsByName(String customName) {
		Customer customer=customerDao.getCustomerByName(customName);
		return (customer!=null);
	}

	@Override
	public boolean existsByMail(String customMail) {
		Customer customer=customerDao.getCustomerByMail(customMail);
		return (customer!=null);
	}

	@Override
	public boolean ifExists(String way, String value) {
		Map<String, String> wayMap=new HashMap<String, String>();
		wayMap.put(way, value);
		Customer customer=customerDao.getCustomer(wayMap);
		return (customer==null);
	}

	@Override
	public boolean checkPassword(String way, String value, String password) {
		Map<String, String> wayMap=new HashMap<String, String>();
		wayMap.put(way, value);
		Customer customer=customerDao.getCustomer(wayMap);
		return (customer.getPassword().equals(password));
	}

	@Override
	public Customer getCusotmer(String way, String value) {
		return customerDao.getCustomer(way,value);
	}

	@Override
	public void changeCustomer(Customer cust) {
		customerDao.modifyCustomer(cust);
	}

	@Override
	public void addCustomer(Customer cust) {
		customerDao.addCustomer(cust);
	}

	@Override
	public void addWannasee(int movieId, int customNumb) {
		customerDao.addWannasee(movieId,customNumb);
	}

	//通过movieId获取电影名list
	@Override
	public List<String> getWannasee(int customNumb) {
		List<Integer> wannaseeMovieId = customerDao.getWannaseeMovieId(customNumb);
		return customerDao.getMovieByname(wannaseeMovieId);
	}
	
	@Override
	public void cancelWannasee(int movieId, int customNumb) {
		customerDao.deleteWannasee(movieId,customNumb);
	}

	@Override
	public void addMovieComm(Integer movieId, String commentContent, int customNumb) {
		customerDao.addMovieComm(movieId,customNumb,commentContent);
	}

	@Override
	public List<String> getMyMovieComm(int customNumb) {
		return customerDao.getMovieCommBycustomNumb(customNumb);
	}
	
	@Override
	public List<String> getThisMovieComm(Integer movieId) {
		return customerDao.getMovieComm(movieId);
	}
	
	@Override
	public void changeMovieComm(int commentNumb, String commentContent) {
		customerDao.modifyMovieComm(commentNumb,commentContent);
	}

	@Override
	public void delMovieComm(int commentNumb) {
		customerDao.deleteMovieComm(commentNumb);
	}



}
