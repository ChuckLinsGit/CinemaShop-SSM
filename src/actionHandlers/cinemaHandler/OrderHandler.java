package actionHandlers.cinemaHandler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cinemaModule.entity.Adorder;
import cinemaModule.entity.Comment;
import cinemaModule.entity.Order;
import cinemaModule.entity.TimeInterval;
import cinemaModule.service.OrderService;
import customModule.entity.Customer;

@Controller
@RequestMapping("customer/")
public class OrderHandler {

	private OrderService orderServ;

	/**
	 * �����û��ύ�ĵ�Ӱ��ʱ�κ���λ��Ϣ�ύԤ����
	 */
	@RequestMapping("SubmitAdorder")
	public void submitAdorder(TimeInterval interval,Integer ticketAmount,Integer[] setArray,HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		HttpSession session=request.getSession();
		Integer customNumb=((Customer)session.getAttribute("customer")).getCustomNumb();
		orderServ.submitAdorder(interval,ticketAmount,setArray,customNumb);
		ModelAndView andView=new ModelAndView();
		andView.addObject("submitAdOrderMsg", "Ԥ�����ύ��ɣ�");
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ������ʱ���߿�������ȡ������
	 * ��ʱʵ�֣���ÿ�β�ѯ�ö���ǰ��֤�ö����Ƿ�ʱ��������������δʵ��
	 */
	@RequestMapping("CancelAdorder")
	public void cancalAdorder(Integer adOrderNumb,HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		orderServ.cancelAdorder(adOrderNumb);
		ModelAndView andView=new ModelAndView();
		andView.addObject("cancelAdorderMsg", "Ԥ����ȡ���ɹ���");
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ��ѯԤ����
	 */
	@RequestMapping("getAdorder")
	public void getAdorder(HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		HttpSession session=request.getSession();
		Integer customNumb=((Customer)session.getAttribute("customer")).getCustomNumb();
		List<Adorder> adorderList = orderServ.getAdorders(customNumb);
		ModelAndView andView=new ModelAndView();
		andView.addObject("adorderList",adorderList);
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * �û������ύԤ����Ϊ������������ʾ��Ϣ������ȡ��
	 */
	@RequestMapping("SubmitOrder")
	public void submitOrder(Integer adOrderNumb,HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		orderServ.submitOrder(adOrderNumb);
		ModelAndView andView=new ModelAndView();
		andView.addObject("submitOrderMsg", "�����ύ�ɹ���");
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * �û�����ʹ�ö���
	 */
	@RequestMapping("FinishOrder")
	public void finishOrder(Integer orderNumb,HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		orderServ.finishOrder(orderNumb);
		ModelAndView andView=new ModelAndView();
		andView.addObject("submitOrderMsg", "����ʹ�óɹ���");
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ��ѯ����
	 */
	@RequestMapping("getOrder")
	public void getOrder(HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		HttpSession session=request.getSession();
		Integer customNumb=((Customer)session.getAttribute("customer")).getCustomNumb();
		List<Order> orderList = orderServ.getOrders(customNumb);
		ModelAndView andView=new ModelAndView();
		andView.addObject("orderList",orderList);
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * �û��ύ��ӰԺ����
	 */
	@RequestMapping("SubmitCiComm")
	public void submitCommentsAboutCinema(Integer orderNumb,String content,HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		orderServ.submitCiComm(orderNumb,content);
		ModelAndView andView=new ModelAndView();
		andView.addObject("submitOrderMsg", "�����ύ�ɹ���");
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ��ѯ�Լ�������
	 */
	@RequestMapping("getMyComment")
	public void getMyComment(HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		HttpSession session=request.getSession();
		Integer customNumb=((Customer)session.getAttribute("customer")).getCustomNumb();
		List<Comment> MyCommentList = orderServ.getMyComment(customNumb);
		ModelAndView andView=new ModelAndView();
		andView.addObject("MyCommentList",MyCommentList);
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * �޸��Լ�������
	 */
	@RequestMapping("ChangMyCiComm")
	public void changMyCiComm(Integer commentNumb,String content,HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		orderServ.changMyCiComm(commentNumb,content);
		ModelAndView andView=new ModelAndView();
		andView.addObject("submitOrderMsg", "�����޸ĳɹ���");
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * ��ѯ��������
	 */
	@RequestMapping("getComment")
	public void getComment(HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		List<Comment> CommentList = orderServ.getComment();
		ModelAndView andView=new ModelAndView();
		andView.addObject("CommentList",CommentList);
		andView.setViewName("redirect:"+postURI);
	}
}
