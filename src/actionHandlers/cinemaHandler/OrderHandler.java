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
	 * 根据用户提交的电影的时段和座位信息提交预订单
	 */
	@RequestMapping("SubmitAdorder")
	public void submitAdorder(TimeInterval interval,Integer ticketAmount,Integer[] setArray,HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		HttpSession session=request.getSession();
		Integer customNumb=((Customer)session.getAttribute("customer")).getCustomNumb();
		orderServ.submitAdorder(interval,ticketAmount,setArray,customNumb);
		ModelAndView andView=new ModelAndView();
		andView.addObject("submitAdOrderMsg", "预订单提交完成！");
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 订单超时或者可以主动取消订单
	 * 超时实现：在每次查询该订单前验证该订单是否超时——————尚未实现
	 */
	@RequestMapping("CancelAdorder")
	public void cancalAdorder(Integer adOrderNumb,HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		orderServ.cancelAdorder(adOrderNumb);
		ModelAndView andView=new ModelAndView();
		andView.addObject("cancelAdorderMsg", "预订单取消成功！");
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 查询预订单
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
	 * 用户主动提交预订单为订单，返回提示信息，不可取消
	 */
	@RequestMapping("SubmitOrder")
	public void submitOrder(Integer adOrderNumb,HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		orderServ.submitOrder(adOrderNumb);
		ModelAndView andView=new ModelAndView();
		andView.addObject("submitOrderMsg", "订单提交成功！");
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 用户主动使用订单
	 */
	@RequestMapping("FinishOrder")
	public void finishOrder(Integer orderNumb,HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		orderServ.finishOrder(orderNumb);
		ModelAndView andView=new ModelAndView();
		andView.addObject("submitOrderMsg", "订单使用成功！");
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 查询订单
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
	 * 用户提交电影院评价
	 */
	@RequestMapping("SubmitCiComm")
	public void submitCommentsAboutCinema(Integer orderNumb,String content,HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		orderServ.submitCiComm(orderNumb,content);
		ModelAndView andView=new ModelAndView();
		andView.addObject("submitOrderMsg", "评论提交成功！");
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 查询自己的评论
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
	 * 修改自己的评论
	 */
	@RequestMapping("ChangMyCiComm")
	public void changMyCiComm(Integer commentNumb,String content,HttpServletRequest request) {
		String postURI=request.getParameter("postURI");
		orderServ.changMyCiComm(commentNumb,content);
		ModelAndView andView=new ModelAndView();
		andView.addObject("submitOrderMsg", "评论修改成功！");
		andView.setViewName("redirect:"+postURI);
	}
	
	/**
	 * 查询所有评论
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
