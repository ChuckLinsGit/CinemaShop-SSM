package cinemaModule.service;

import java.util.List;

import cinemaModule.entity.Adorder;
import cinemaModule.entity.Comment;
import cinemaModule.entity.Order;
import cinemaModule.entity.TimeInterval;

public interface OrderService {

	//需要计算totalValue，并将该场次所订座位占用
	void submitAdorder(TimeInterval interval,Integer ticketAmount, Integer[] seatArray,Integer customNumb);

	void cancelAdorder(Integer adOrderNumb);
	//需要从Adorder表读取订单信息，转而写入order表并将Adorder表该条记录记为已成交
	void submitOrder(Integer adOrderNumb);

	void submitCiComm(Integer orderNumb, String content);

	//返回未成交的预订单
	List<Adorder> getAdorders(Integer customNumb);

	List<Order> getOrders(Integer customNumb);

	void finishOrder(Integer orderNumb);
	

	List<Comment> getMyComment(Integer customNumb);

	void changMyCiComm(Integer commentNumb, String content);

	List<Comment> getComment();





}
