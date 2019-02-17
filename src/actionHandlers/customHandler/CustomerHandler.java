package actionHandlers.customHandler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.javafx.stage.EmbeddedWindow;

import customModule.entity.Customer;
import customModule.service.CustomerService;
import customModule.service.customServ;
import utils.loginVadation.validateCode;
import utils.miaodiyun.source.httpApiDemo.*;
import utils.miaodiyun.source.httpApiDemo.common.HttpUtil;

@Controller
@RequestMapping("customer/")
public class CustomerHandler{
	//邮件验证执行对象
	private AccountInfo accountInfo;
	private IndustrySMS industrySMS;
	//验证时间=当前生成验证码的时间new Date()+设置的时长vadateTime
	private Timestamp validateTime;
	private CustomerService customServ;
	
	
	
	/**
	 * 新消息通知登录用户
	 */
	@RequestMapping("Inform")
	public void inform(){
			
	}
	
	/**
	 * 发送短信验证码
	 * @param customPhone
	 */
	@RequestMapping("SendMiaodiMs")
	public void sendMiaodiMs(String customPhone,HttpSession session) {
		industrySMS = new IndustrySMS();
		//生产验证码并设置验证码时限
		session.setAttribute("validateDeadTime",(new Date().getTime())+validateTime.getTime());
		String Randomcode = HttpUtil.createRandomVcode();
		session.setAttribute("registerValidation", Randomcode);
		industrySMS.to=customPhone;
		industrySMS.smsContent="【映像影院】验证码："+Randomcode+"，打死都不要告诉别人哦！";
		
		accountInfo = new AccountInfo();
		/*
		accountInfo.execute();
		IndustrySMS.execute();*/
	    
		ModelAndView mAndView=new ModelAndView();
		mAndView.addObject("MiaodiMsg", "验证码已发送！");
		mAndView.setViewName("redirect:/register.jsp");
	}
	
	/**
	 * 邮箱验证
	 */
	@RequestMapping("sendMailMs")
	public void mailVadation(String to,String from,String host,String password,HttpSession httpSessionsession) {
			
		Properties properties=System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		
	   /*某些邮件服务器可能需要以下设置		
 		* MailSSLSocketFactory factory=new MailSSLSocketFactory();
		factory.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory",factory);*/
		
		//使用商业邮箱服务器，提供方会要求使用第三方平台登录时开启POP3/SMTP服务并使用授权码登录
		Session session =Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		session.setDebug(true);
		try {
			MimeMessage message=new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress= {new InternetAddress(to)};
			message.setRecipients(Message.RecipientType.TO, toAddress);
			message.setSubject("映像影院邮件注册验证","utf-8");
			message.setSentDate(Calendar.getInstance().getTime());
			
			//生产验证码并设置验证码时限
			httpSessionsession.setAttribute("validateDeadTime",(new Date().getTime())+validateTime.getTime());
			String Randomcode = HttpUtil.createRandomVcode();
			httpSessionsession.setAttribute("registerValidation", Randomcode);

			MimeBodyPart mimeBodyPart=new MimeBodyPart();
			mimeBodyPart.setText("这是来自映像影院的注册验证邮件，验证码是："+Randomcode+"\n若不是您的操作带来的结果，请勿理会。", "utf-8");
			Multipart multipart=new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新用户注册:手机号、邮箱号、用户名不能重复注册且不能为空，验证码必须正确
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("Register")
	public ModelAndView register(Customer cust,String vadateCode,HttpSession session) {
		ModelAndView mView=new ModelAndView();
		
		if(cust.getCustomPhone()==""){
			mView.addObject("registerMsg", "手机号不能为空！");
			mView.setViewName("redirect:/register.jsp");
			return mView;
		}
		boolean hasExists=customServ.existsByPhone(cust.getCustomPhone());
		if(hasExists==true){
			mView.addObject("registerMsg", "该手机号已注册！");
			mView.setViewName("redirect:/register.jsp");
			return mView;
		}
		
		if(cust.getCustomName()=="") {
			mView.addObject("registerMsg", "用户名不能为空！");
			mView.setViewName("redirect:/register.jsp");
			return mView;
		}
		hasExists=customServ.existsByName(cust.getCustomName());
		if(hasExists==true){
			mView.addObject("registerMsg", "该用户名已存在！");
			mView.setViewName("redirect:/register.jsp");
			return mView;
		}
		
		if(cust.getCustomMail()=="") {
			mView.addObject("registerMsg", "邮箱不能为空！");
			mView.setViewName("redirect:/register.jsp");
			return mView;
		}
		hasExists=customServ.existsByMail(cust.getCustomMail());
		if(hasExists==true){
			mView.addObject("registerMsg", "该邮箱已注册！");
			mView.setViewName("redirect:/register.jsp");
			return mView;
		}
		//验证码必须未超时
		if((new Date().getTime())<=(long)session.getAttribute("validateDeadTime")) {
			if(vadateCode.equals(session.getAttribute("registerValidation"))){
				System.out.println("验证码正确！！");
				customServ.addCustomer(cust);
				session.setAttribute("customer", cust);
				mView.addObject("registerMsg", "注册成功！");
				mView.setViewName("redirect:/login.jsp");
				return mView;
			}else{
				mView.addObject("registerMsg", "验证码错误！");
				mView.setViewName("redirect:/register.jsp");
				return mView;		
			}
		}else {
			mView.addObject("registerMsg", "验证码过时，请重新发送验证码！");
			mView.setViewName("redirect:/register.jsp");
			return mView;
		}
	}

	/** 
	 * 响应验证码页面 
	 * @return 
	 * @throws IOException 
	 */   
	@RequestMapping("loginValidation")
	public ModelAndView loginValidate(HttpServletResponse response,HttpSession session){
			ModelAndView modelAndView=new ModelAndView();
		    validateCode vCode = new validateCode(120,40,5,100); 
		    
		    session.setAttribute("loginValidateCode", vCode.getCode());  
		    
		    BufferedImage buffImg = vCode.getBuffImg();
			modelAndView.addObject("loginValidateImag", buffImg);
		    
			modelAndView.setViewName("redirect:/login.jsp");
			return modelAndView;
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("Login")
	public ModelAndView login(String loginWay,String loginWayValue,String password,String validatCode,HttpServletRequest request){
		HttpSession session=request.getSession();
		//发起请求的uri：http:\\**\**.jsp,并非请求的url而是发起请求的URI，需要在发起请求时放进request参数中
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
			//判断登陆方式
			if(loginWay.equals("customhone")) {
				if(loginWayValue==""){
					mAndView.addObject("loginMsg", "手机号不能为空！");
					mAndView.setViewName("redirect:login.jsp");
					return mAndView;
				}
			}
			if(loginWay.equals("customName")) {
				if(loginWayValue==""){
					mAndView.addObject("loginMsg", "用户名不能为空！");
					mAndView.setViewName("redirect:login.jsp");
					return mAndView;
				}
			}
			if(loginWay.equals("customMail")) {
				if(loginWayValue==""){
					mAndView.addObject("loginMsg", "邮箱不能为空！");
					mAndView.setViewName("redirect:login.jsp");
					return mAndView;
				}
			}
			
			//检测验证码是否正确
			if(validatCode.equals(session.getAttribute("loginValidateCode"))){
				System.out.println("验证码正确！！");
				//验证用户是否存在
				boolean ifExists=customServ.ifExists(loginWay,loginWayValue);
				if(ifExists==false){
					mAndView.addObject("loginMsg", "该用户不存在！");
					mAndView.setViewName("redirect:login.jsp");
					return mAndView;
				}else{
					//验证密码是否正确：基于安全原则，应该再次用登录方式的值和密码进行查询，不应查出用户资料再比对密码是否正确
					boolean isRight=customServ.checkPassword(loginWay,loginWayValue,password);
					if(isRight==false) {
						mAndView.addObject("loginMsg", "密码错误！");
						mAndView.setViewName("redirect:login.jsp");
						return mAndView;
					}else{
						Customer cust=customServ.getCusotmer(loginWay,loginWayValue);
						//将customer设为会话值，可以被其他页面引用
						session.setAttribute("customer", cust);
						mAndView.setViewName("redirect:"+postURI);
						return mAndView;
					}
				}
			}else{
				mAndView.addObject("loginMsg", "验证码错误！");
				mAndView.setViewName("redirect:login.jsp");
				return mAndView;		
			}
	}
	
	
	/**
	 * 更换密码
	 */
	@RequestMapping("ChangePassword")
	public ModelAndView changeMissingPassword(Customer cust,String newPassword,String vadateCode,HttpSession session) {
		ModelAndView mAndView=new ModelAndView();
		//验证码必须未超时
		if((new Date().getTime())<=(long)session.getAttribute("validateDeadTime")) {
			if(vadateCode.equals(session.getAttribute("registerValidation"))){
				System.out.println("验证码正确！！");
				boolean ifRight=customServ.checkPassword("customNumb", String.valueOf(cust.getCustomNumb()), cust.getPassword());
				if(ifRight==false) {
					mAndView.addObject("changePasswordMsg", "旧密码错误！");
					mAndView.setViewName("redirect:personCenter.jsp");
					return mAndView;
				}else{
					cust.setPassword(newPassword);
					customServ.changeCustomer(cust);
					
					session.setAttribute("customer", cust);
					mAndView.addObject("changePasswordMsg", "修改密码成功！");
					mAndView.setViewName("redirect:personCenter.jsp");
					return mAndView;
				}
			}else{
				mAndView.addObject("changePasswordMsg", "验证码错误！");
				mAndView.setViewName("redirect:/register.jsp");
				return mAndView;		
			}
		}else {
			mAndView.addObject("changePasswordMsg", "验证码过时，请重新发送验证码！");
			mAndView.setViewName("redirect:/register.jsp");
			return mAndView;
		}
	}
	
	/**
	 * 修改个人资料
	 */
	@RequestMapping("ChangeData")
	public ModelAndView changeData(Customer cust,HttpSession session) {
		ModelAndView mAndView=new ModelAndView();
		customServ.changeCustomer(cust);
		session.setAttribute("customer", cust);
		mAndView.addObject("changeDataMsg", "修改成功！");
		mAndView.setViewName("redirect:personCenter.jsp");
		return mAndView;
	}
	
	
	/**
	 * 添加想看的电影
	 */
	@RequestMapping("AddWannasee")
	public ModelAndView addWannasee(String movieId,HttpServletRequest request) {
		HttpSession session=request.getSession();
		//发起请求的uri：http:\\**\**.jsp,并非请求的url而是发起请求的URI，需要在发起请求时放进request参数中；
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		Customer cust = (Customer) session.getAttribute("customer");
		customServ.addWannasee(Integer.valueOf(movieId),cust.getCustomNumb());
		mAndView.addObject("addWannaseeMsg", "添加成功！");
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * 查看想看的电影
	 */
	@RequestMapping("getWannasee")
	public ModelAndView getWannasee(HttpServletRequest request) {
		HttpSession session=request.getSession();
		//发起请求的uri：http:\\**\**.jsp,并非请求的url而是发起请求的URI，需要在发起请求时放进request参数中；
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		Customer cust = (Customer) session.getAttribute("customer");
		List<String> wannasee = customServ.getWannasee(cust.getCustomNumb());
		mAndView.addObject("wannasee", wannasee);
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	
	/**
	 * 取消想看的电影
	 */
	@RequestMapping("CancelWannasee")
	public ModelAndView cancelWannasee(String movieId,HttpServletRequest request) {
		HttpSession session=request.getSession();
		Customer cust = (Customer) session.getAttribute("customer");
		//发起请求的uri：http:\\**\**.jsp,并非请求的url而是发起请求的URI，需要在发起请求时放进request参数中；
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		customServ.cancelWannasee(Integer.valueOf(movieId),cust.getCustomNumb());
		mAndView.addObject("cancelWannaseeMsg", "取消成功！");
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * 提交电影评价
	 */
	@RequestMapping("SubMovieComm")
	public ModelAndView submitCommentsAboutMovie(String movieId,String commentContent,HttpServletRequest request) {
		HttpSession session=request.getSession();
		//发起请求的uri：http:\\**\**.jsp,并非请求的url而是发起请求的URI，需要在发起请求时放进request参数中；
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		Customer cust = (Customer) session.getAttribute("customer");
		customServ.addMovieComm(Integer.valueOf(movieId),commentContent,cust.getCustomNumb());
		mAndView.addObject("subMovieCommMsg", "评论成功！");
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	
	/**
	 * 修改电影评价
	 */
	@RequestMapping("ChangMovieComm")
	public ModelAndView changeCommentAboutMovie(String commentNumb,String commentContent,HttpServletRequest request) {
		//发起请求的uri：http:\\**\**.jsp,并非请求的url而是发起请求的URI，需要在发起请求时放进request参数中；
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		customServ.changeMovieComm(Integer.valueOf(commentNumb),commentContent);
		mAndView.addObject("changMovieCommMsg", "修改评论成功！");
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * 查看“我”的电影评价
	 */
	@RequestMapping("getMyMovieComm")
	public ModelAndView getMyMovieComm(HttpServletRequest request) {
		HttpSession session=request.getSession();
		//发起请求的uri：http:\\**\**.jsp,并非请求的url而是发起请求的URI，需要在发起请求时放进request参数中；
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		Customer cust = (Customer) session.getAttribute("customer");
		List<String> myMovieComm = customServ.getMyMovieComm(cust.getCustomNumb());
		mAndView.addObject("myMovieComm", myMovieComm);
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * 查看电影的评价
	 */
	@RequestMapping("getThisMovieComm")
	public ModelAndView getThisMovieComm(Integer movieId,HttpServletRequest request) {
		HttpSession session=request.getSession();
		//发起请求的uri：http:\\**\**.jsp,并非请求的url而是发起请求的URI，需要在发起请求时放进request参数中；
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		List<String> thisMovieComm = customServ.getThisMovieComm(movieId);
		mAndView.addObject("thisMovieComm", thisMovieComm);
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * 删除电影评价
	 */
	@RequestMapping("delMovieComm")
	public ModelAndView delSubmitCommentAboutMovie(String commentNumb,HttpServletRequest request) {
		//发起请求的uri：http:\\**\**.jsp,并非请求的url而是发起请求的URI，需要在发起请求时放进request参数中；
		String postURI = request.getParameter("postURI");
		HttpSession session=request.getSession();
		Customer cust=(Customer) session.getAttribute("customer");
		ModelAndView mAndView=new ModelAndView();
		customServ.delMovieComm(Integer.valueOf(commentNumb));
		mAndView.addObject("delMovieCommMsg", "删除评论成功！");
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	@RequestMapping("ChangeVIPLevel")
	public ModelAndView changeVIPLevel(Customer cust,String newLevel,HttpSession session) {
		ModelAndView mAndView=new ModelAndView();
		cust.setVIPLevel(newLevel);
		customServ.changeCustomer(cust);
		session.setAttribute("customer", cust);
		mAndView.addObject("changeDataMsg", "修改成功！");
		mAndView.setViewName("redirect:personCenter.jsp");
		return mAndView;
	}

	/**
	 * 用户退出
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("Logout")
	public ModelAndView logout(HttpSession session){
		ModelAndView mAndView=new ModelAndView();
		session.removeAttribute("customer");
		mAndView.addObject("LogoutMsg", "成功退出！");
		mAndView.setViewName("redirect:login.jsp");
		return mAndView;	
	}

}