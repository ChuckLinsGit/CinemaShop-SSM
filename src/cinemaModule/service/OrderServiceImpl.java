package cinemaModule.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cinemaModule.dao.OrderDaO;
import cinemaModule.entity.Adorder;
import cinemaModule.entity.Comment;
import cinemaModule.entity.Order;
import cinemaModule.entity.TimeInterval;

public class OrderServiceImpl implements OrderService {

	private OrderDaO orderDao;
	//需要计算totalValue，并将该场次所订座位占用
	@Override
	public void submitAdorder(TimeInterval interval,Integer ticketAmount, Integer[] seatArray,Integer customNumb) {
		Map<String, Integer> roomNumbMap=new HashMap<String,Integer>();
		roomNumbMap.put("roomNumb", interval.getRoomNumb());
		Float ticketPrice=orderDao.getTicketPrice(interval.getScheduleNumb(),roomNumbMap);
		Float totalValue=ticketPrice*ticketAmount;
		
		List<Integer> seatset1 = new ArrayList<Integer>();
		List<Integer> seatset2 = new ArrayList<Integer>();
		String seatStr = null;
		for (int i=0;i<seatArray.length;i++) {
			if((seatArray[i]/100)>=4||(seatArray[i]/100)<=11) {
				seatset1.add(seatArray[i]);
			}else {
				seatset2.add(seatArray[i]);
			}
			if(i==(seatArray.length-1)) {
				seatStr=String.valueOf(seatArray[i]);
				break;
			}
			seatStr=String.valueOf(seatArray[i])+",";
		}
		orderDao.addAdorder(customNumb,interval.getScheduleNumb(),seatStr,ticketAmount,totalValue);
		//占用位置后还需增加相应的人数
		orderDao.modifySeatset1(roomNumbMap,interval.getScheduleNumb(),seatset1,11);
		orderDao.modifySeatset2(roomNumbMap,interval.getScheduleNumb(),seatset2,11);
		checkIfFull(roomNumbMap,interval.getScheduleNumb(),ticketAmount);
	}


	//删除预订单后还需将座位表接触占用
	@Override
	public void cancelAdorder(Integer adOrderNumb) {
		List<Adorder> adorders = orderDao.getAdorders(adOrderNumb);
		//解析座位并根据大小放在不同的list中以对应不同的座位表
		List<Integer> seatset1 = new ArrayList<Integer>();
		List<Integer> seatset2 = new ArrayList<Integer>();
		for (Adorder adorder : adorders) {
			if(adorder!=null) {
				String[] seatStr = adorder.getSeat().split(",");
				for(int i=0;i<seatStr.length;i++) {
					int seat=Integer.valueOf(seatStr[i]);
					if((seat/100)>=4||(seat/100)<=11) {
						seatset1.add(seat);
					}else {
						seatset2.add(seat);
					}
				}
				Map<String, Integer> roomNumbMap=new HashMap<String,Integer>();
				roomNumbMap.put("roomNumb", adorder.getRoomNumb());
				orderDao.deleteAdorder(adOrderNumb);
				orderDao.modifySeatset1(roomNumbMap,adorder.getScheduleNumb(),seatset1,10);
				orderDao.modifySeatset2(roomNumbMap,adorder.getScheduleNumb(),seatset2,10);
				checkIfFull(roomNumbMap, adorder.getScheduleNumb(), -adorder.getTicketAmount());
			}
		}
	}
	
	//添加预订单或者删除预订单时检查满座情况，若添加预订单后满足置isFill=1，若删除预订单后不满座置isFill=0
	private void checkIfFull(Map<String, Integer> roomNumbMap, Integer scheduleNumb, Integer ticketAmount) {
		Integer beOrder = orderDao.getBeOrder(roomNumbMap,scheduleNumb);
		Integer isFill = orderDao.getIsFill(roomNumbMap,scheduleNumb);
		Integer typeNumb = orderDao.getTypeNumb(roomNumbMap);
		Integer seatAmount = orderDao.getSeatAmount(typeNumb);
		if((beOrder+ticketAmount)==seatAmount) {
			if(isFill==0) {
				orderDao.setIsFill(roomNumbMap,scheduleNumb,1);
			}
		}else {
			if(isFill==1) {
				orderDao.setIsFill(roomNumbMap,scheduleNumb,0);
			}
		}
	}

	@Override
	public void submitOrder(Integer adOrderNumb) {
		List<Adorder> adorders = orderDao.getAdorders(adOrderNumb);
		for (Adorder adorder : adorders) {
			if(adorder!=null) {
				orderDao.addOrder(adorder.getCustomNumb(),adorder.getRoomNumb(),adorder.getScheduleNumb(),adorder.getSeat(),
			  			adorder.getTicketAmount(),adorder.getTotalValue());
				orderDao.finishAdorder(adOrderNumb);
			}
		}
		
	}
	
	@Override
	public void finishOrder(Integer orderNumb) {
		orderDao.setDeal(orderNumb);
	}
	
	@Override
	public List<Adorder> getAdorders(Integer customNumb) {
		return orderDao.getAdorders(customNumb);
	}

	@Override
	public List<Order> getOrders(Integer customNumb) {
		return orderDao.getOrders(customNumb);
	}

	@Override
	public void submitCiComm(Integer orderNumb, String content) {
		orderDao.addCiComm(orderNumb,content);
	}


	@Override
	public List<Comment> getMyComment(Integer customNumb) {
		List<Order> orders = orderDao.getOrders(customNumb);
		List<Integer> orderNumbList=new ArrayList<Integer>();
		for (Order order : orders) {
			orderNumbList.add(order.getOrderNumb());
		}
		return orderDao.getMyComment(orderNumbList);
	}

	@Override
	public void changMyCiComm(Integer commentNumb, String content) {
		orderDao.modifyMyCiComm(commentNumb,content);
	}

	@Override
	public List<Comment> getComment() {
		return orderDao.getComment();
	}

}
