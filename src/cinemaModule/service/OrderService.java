package cinemaModule.service;

import java.util.List;

import cinemaModule.entity.Adorder;
import cinemaModule.entity.Comment;
import cinemaModule.entity.Order;
import cinemaModule.entity.TimeInterval;

public interface OrderService {

	//��Ҫ����totalValue�������ó���������λռ��
	void submitAdorder(TimeInterval interval,Integer ticketAmount, Integer[] seatArray,Integer customNumb);

	void cancelAdorder(Integer adOrderNumb);
	//��Ҫ��Adorder���ȡ������Ϣ��ת��д��order����Adorder�������¼��Ϊ�ѳɽ�
	void submitOrder(Integer adOrderNumb);

	void submitCiComm(Integer orderNumb, String content);

	//����δ�ɽ���Ԥ����
	List<Adorder> getAdorders(Integer customNumb);

	List<Order> getOrders(Integer customNumb);

	void finishOrder(Integer orderNumb);
	

	List<Comment> getMyComment(Integer customNumb);

	void changMyCiComm(Integer commentNumb, String content);

	List<Comment> getComment();





}
