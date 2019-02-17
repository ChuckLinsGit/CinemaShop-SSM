package cinemaModule.dao;

import java.util.List;
import java.util.Map;

import cinemaModule.entity.Adorder;
import cinemaModule.entity.Comment;
import cinemaModule.entity.Order;
import cinemaModule.entity.TimeInterval;

public interface OrderDaO {

	//获取票价
	Float getTicketPrice(Integer scheduleNumb,Map roomNumb);

	void addAdorder(Integer customNumb, Integer scheduleNumb, String seatStr, Integer ticketAmount, Float totalValue);

	//需要增加触发器判断是否满人
	void modifySeatset1(Map roomNumb,Integer scheduleNumb, List<Integer> seatset1,Integer flag);

	void modifySeatset2(Map roomNumb,Integer scheduleNumb, List<Integer> seatset2,Integer flag);

	void finishAdorder(Integer adOrderNumb);
	
	void deleteAdorder(Integer adOrderNumb);

	List<Adorder> getAdorders(Integer customNumb);

	void addOrder(Integer customNumb, Integer scheduleNumb, Integer roomNumb, String seat, Integer ticketAmount, Float totalValue);

	List<Order> getOrders(Integer customNumb);

	void setDeal(Integer orderNumb);
	
	void addCiComm(Integer orderNumb, String content);

	List<Comment> getMyComment(List<Integer> orderNumbList);

	void modifyMyCiComm(Integer commentNumb, String content);

	List<Comment> getComment();

	//检查满座情况
	Integer getBeOrder(Map<String, Integer> roomNumbMap, Integer scheduleNumb);

	Integer getTypeNumb(Map<String, Integer> roomNumbMap);

	Integer getSeatAmount(Integer typeNumb);

	Integer getIsFill(Map<String, Integer> roomNumbMap, Integer scheduleNumb);

	void setIsFill(Map<String, Integer> roomNumbMap, int scheduleNumb, int value);


}
