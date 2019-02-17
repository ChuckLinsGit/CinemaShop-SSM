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
	//�ʼ���ִ֤�ж���
	private AccountInfo accountInfo;
	private IndustrySMS industrySMS;
	//��֤ʱ��=��ǰ������֤���ʱ��new Date()+���õ�ʱ��vadateTime
	private Timestamp validateTime;
	private CustomerService customServ;
	
	
	
	/**
	 * ����Ϣ֪ͨ��¼�û�
	 */
	@RequestMapping("Inform")
	public void inform(){
			
	}
	
	/**
	 * ���Ͷ�����֤��
	 * @param customPhone
	 */
	@RequestMapping("SendMiaodiMs")
	public void sendMiaodiMs(String customPhone,HttpSession session) {
		industrySMS = new IndustrySMS();
		//������֤�벢������֤��ʱ��
		session.setAttribute("validateDeadTime",(new Date().getTime())+validateTime.getTime());
		String Randomcode = HttpUtil.createRandomVcode();
		session.setAttribute("registerValidation", Randomcode);
		industrySMS.to=customPhone;
		industrySMS.smsContent="��ӳ��ӰԺ����֤�룺"+Randomcode+"����������Ҫ���߱���Ŷ��";
		
		accountInfo = new AccountInfo();
		/*
		accountInfo.execute();
		IndustrySMS.execute();*/
	    
		ModelAndView mAndView=new ModelAndView();
		mAndView.addObject("MiaodiMsg", "��֤���ѷ��ͣ�");
		mAndView.setViewName("redirect:/register.jsp");
	}
	
	/**
	 * ������֤
	 */
	@RequestMapping("sendMailMs")
	public void mailVadation(String to,String from,String host,String password,HttpSession httpSessionsession) {
			
		Properties properties=System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		
	   /*ĳЩ�ʼ�������������Ҫ��������		
 		* MailSSLSocketFactory factory=new MailSSLSocketFactory();
		factory.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory",factory);*/
		
		//ʹ����ҵ������������ṩ����Ҫ��ʹ�õ�����ƽ̨��¼ʱ����POP3/SMTP����ʹ����Ȩ���¼
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
			message.setSubject("ӳ��ӰԺ�ʼ�ע����֤","utf-8");
			message.setSentDate(Calendar.getInstance().getTime());
			
			//������֤�벢������֤��ʱ��
			httpSessionsession.setAttribute("validateDeadTime",(new Date().getTime())+validateTime.getTime());
			String Randomcode = HttpUtil.createRandomVcode();
			httpSessionsession.setAttribute("registerValidation", Randomcode);

			MimeBodyPart mimeBodyPart=new MimeBodyPart();
			mimeBodyPart.setText("��������ӳ��ӰԺ��ע����֤�ʼ�����֤���ǣ�"+Randomcode+"\n���������Ĳ��������Ľ����������ᡣ", "utf-8");
			Multipart multipart=new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���û�ע��:�ֻ��š�����š��û��������ظ�ע���Ҳ���Ϊ�գ���֤�������ȷ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("Register")
	public ModelAndView register(Customer cust,String vadateCode,HttpSession session) {
		ModelAndView mView=new ModelAndView();
		
		if(cust.getCustomPhone()==""){
			mView.addObject("registerMsg", "�ֻ��Ų���Ϊ�գ�");
			mView.setViewName("redirect:/register.jsp");
			return mView;
		}
		boolean hasExists=customServ.existsByPhone(cust.getCustomPhone());
		if(hasExists==true){
			mView.addObject("registerMsg", "���ֻ�����ע�ᣡ");
			mView.setViewName("redirect:/register.jsp");
			return mView;
		}
		
		if(cust.getCustomName()=="") {
			mView.addObject("registerMsg", "�û�������Ϊ�գ�");
			mView.setViewName("redirect:/register.jsp");
			return mView;
		}
		hasExists=customServ.existsByName(cust.getCustomName());
		if(hasExists==true){
			mView.addObject("registerMsg", "���û����Ѵ��ڣ�");
			mView.setViewName("redirect:/register.jsp");
			return mView;
		}
		
		if(cust.getCustomMail()=="") {
			mView.addObject("registerMsg", "���䲻��Ϊ�գ�");
			mView.setViewName("redirect:/register.jsp");
			return mView;
		}
		hasExists=customServ.existsByMail(cust.getCustomMail());
		if(hasExists==true){
			mView.addObject("registerMsg", "��������ע�ᣡ");
			mView.setViewName("redirect:/register.jsp");
			return mView;
		}
		//��֤�����δ��ʱ
		if((new Date().getTime())<=(long)session.getAttribute("validateDeadTime")) {
			if(vadateCode.equals(session.getAttribute("registerValidation"))){
				System.out.println("��֤����ȷ����");
				customServ.addCustomer(cust);
				session.setAttribute("customer", cust);
				mView.addObject("registerMsg", "ע��ɹ���");
				mView.setViewName("redirect:/login.jsp");
				return mView;
			}else{
				mView.addObject("registerMsg", "��֤�����");
				mView.setViewName("redirect:/register.jsp");
				return mView;		
			}
		}else {
			mView.addObject("registerMsg", "��֤���ʱ�������·�����֤�룡");
			mView.setViewName("redirect:/register.jsp");
			return mView;
		}
	}

	/** 
	 * ��Ӧ��֤��ҳ�� 
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
	 * �û���¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("Login")
	public ModelAndView login(String loginWay,String loginWayValue,String password,String validatCode,HttpServletRequest request){
		HttpSession session=request.getSession();
		//���������uri��http:\\**\**.jsp,���������url���Ƿ��������URI����Ҫ�ڷ�������ʱ�Ž�request������
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
			//�жϵ�½��ʽ
			if(loginWay.equals("customhone")) {
				if(loginWayValue==""){
					mAndView.addObject("loginMsg", "�ֻ��Ų���Ϊ�գ�");
					mAndView.setViewName("redirect:login.jsp");
					return mAndView;
				}
			}
			if(loginWay.equals("customName")) {
				if(loginWayValue==""){
					mAndView.addObject("loginMsg", "�û�������Ϊ�գ�");
					mAndView.setViewName("redirect:login.jsp");
					return mAndView;
				}
			}
			if(loginWay.equals("customMail")) {
				if(loginWayValue==""){
					mAndView.addObject("loginMsg", "���䲻��Ϊ�գ�");
					mAndView.setViewName("redirect:login.jsp");
					return mAndView;
				}
			}
			
			//�����֤���Ƿ���ȷ
			if(validatCode.equals(session.getAttribute("loginValidateCode"))){
				System.out.println("��֤����ȷ����");
				//��֤�û��Ƿ����
				boolean ifExists=customServ.ifExists(loginWay,loginWayValue);
				if(ifExists==false){
					mAndView.addObject("loginMsg", "���û������ڣ�");
					mAndView.setViewName("redirect:login.jsp");
					return mAndView;
				}else{
					//��֤�����Ƿ���ȷ�����ڰ�ȫԭ��Ӧ���ٴ��õ�¼��ʽ��ֵ��������в�ѯ����Ӧ����û������ٱȶ������Ƿ���ȷ
					boolean isRight=customServ.checkPassword(loginWay,loginWayValue,password);
					if(isRight==false) {
						mAndView.addObject("loginMsg", "�������");
						mAndView.setViewName("redirect:login.jsp");
						return mAndView;
					}else{
						Customer cust=customServ.getCusotmer(loginWay,loginWayValue);
						//��customer��Ϊ�Ựֵ�����Ա�����ҳ������
						session.setAttribute("customer", cust);
						mAndView.setViewName("redirect:"+postURI);
						return mAndView;
					}
				}
			}else{
				mAndView.addObject("loginMsg", "��֤�����");
				mAndView.setViewName("redirect:login.jsp");
				return mAndView;		
			}
	}
	
	
	/**
	 * ��������
	 */
	@RequestMapping("ChangePassword")
	public ModelAndView changeMissingPassword(Customer cust,String newPassword,String vadateCode,HttpSession session) {
		ModelAndView mAndView=new ModelAndView();
		//��֤�����δ��ʱ
		if((new Date().getTime())<=(long)session.getAttribute("validateDeadTime")) {
			if(vadateCode.equals(session.getAttribute("registerValidation"))){
				System.out.println("��֤����ȷ����");
				boolean ifRight=customServ.checkPassword("customNumb", String.valueOf(cust.getCustomNumb()), cust.getPassword());
				if(ifRight==false) {
					mAndView.addObject("changePasswordMsg", "���������");
					mAndView.setViewName("redirect:personCenter.jsp");
					return mAndView;
				}else{
					cust.setPassword(newPassword);
					customServ.changeCustomer(cust);
					
					session.setAttribute("customer", cust);
					mAndView.addObject("changePasswordMsg", "�޸�����ɹ���");
					mAndView.setViewName("redirect:personCenter.jsp");
					return mAndView;
				}
			}else{
				mAndView.addObject("changePasswordMsg", "��֤�����");
				mAndView.setViewName("redirect:/register.jsp");
				return mAndView;		
			}
		}else {
			mAndView.addObject("changePasswordMsg", "��֤���ʱ�������·�����֤�룡");
			mAndView.setViewName("redirect:/register.jsp");
			return mAndView;
		}
	}
	
	/**
	 * �޸ĸ�������
	 */
	@RequestMapping("ChangeData")
	public ModelAndView changeData(Customer cust,HttpSession session) {
		ModelAndView mAndView=new ModelAndView();
		customServ.changeCustomer(cust);
		session.setAttribute("customer", cust);
		mAndView.addObject("changeDataMsg", "�޸ĳɹ���");
		mAndView.setViewName("redirect:personCenter.jsp");
		return mAndView;
	}
	
	
	/**
	 * ����뿴�ĵ�Ӱ
	 */
	@RequestMapping("AddWannasee")
	public ModelAndView addWannasee(String movieId,HttpServletRequest request) {
		HttpSession session=request.getSession();
		//���������uri��http:\\**\**.jsp,���������url���Ƿ��������URI����Ҫ�ڷ�������ʱ�Ž�request�����У�
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		Customer cust = (Customer) session.getAttribute("customer");
		customServ.addWannasee(Integer.valueOf(movieId),cust.getCustomNumb());
		mAndView.addObject("addWannaseeMsg", "��ӳɹ���");
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * �鿴�뿴�ĵ�Ӱ
	 */
	@RequestMapping("getWannasee")
	public ModelAndView getWannasee(HttpServletRequest request) {
		HttpSession session=request.getSession();
		//���������uri��http:\\**\**.jsp,���������url���Ƿ��������URI����Ҫ�ڷ�������ʱ�Ž�request�����У�
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		Customer cust = (Customer) session.getAttribute("customer");
		List<String> wannasee = customServ.getWannasee(cust.getCustomNumb());
		mAndView.addObject("wannasee", wannasee);
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	
	/**
	 * ȡ���뿴�ĵ�Ӱ
	 */
	@RequestMapping("CancelWannasee")
	public ModelAndView cancelWannasee(String movieId,HttpServletRequest request) {
		HttpSession session=request.getSession();
		Customer cust = (Customer) session.getAttribute("customer");
		//���������uri��http:\\**\**.jsp,���������url���Ƿ��������URI����Ҫ�ڷ�������ʱ�Ž�request�����У�
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		customServ.cancelWannasee(Integer.valueOf(movieId),cust.getCustomNumb());
		mAndView.addObject("cancelWannaseeMsg", "ȡ���ɹ���");
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * �ύ��Ӱ����
	 */
	@RequestMapping("SubMovieComm")
	public ModelAndView submitCommentsAboutMovie(String movieId,String commentContent,HttpServletRequest request) {
		HttpSession session=request.getSession();
		//���������uri��http:\\**\**.jsp,���������url���Ƿ��������URI����Ҫ�ڷ�������ʱ�Ž�request�����У�
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		Customer cust = (Customer) session.getAttribute("customer");
		customServ.addMovieComm(Integer.valueOf(movieId),commentContent,cust.getCustomNumb());
		mAndView.addObject("subMovieCommMsg", "���۳ɹ���");
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	
	/**
	 * �޸ĵ�Ӱ����
	 */
	@RequestMapping("ChangMovieComm")
	public ModelAndView changeCommentAboutMovie(String commentNumb,String commentContent,HttpServletRequest request) {
		//���������uri��http:\\**\**.jsp,���������url���Ƿ��������URI����Ҫ�ڷ�������ʱ�Ž�request�����У�
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		customServ.changeMovieComm(Integer.valueOf(commentNumb),commentContent);
		mAndView.addObject("changMovieCommMsg", "�޸����۳ɹ���");
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * �鿴���ҡ��ĵ�Ӱ����
	 */
	@RequestMapping("getMyMovieComm")
	public ModelAndView getMyMovieComm(HttpServletRequest request) {
		HttpSession session=request.getSession();
		//���������uri��http:\\**\**.jsp,���������url���Ƿ��������URI����Ҫ�ڷ�������ʱ�Ž�request�����У�
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		Customer cust = (Customer) session.getAttribute("customer");
		List<String> myMovieComm = customServ.getMyMovieComm(cust.getCustomNumb());
		mAndView.addObject("myMovieComm", myMovieComm);
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * �鿴��Ӱ������
	 */
	@RequestMapping("getThisMovieComm")
	public ModelAndView getThisMovieComm(Integer movieId,HttpServletRequest request) {
		HttpSession session=request.getSession();
		//���������uri��http:\\**\**.jsp,���������url���Ƿ��������URI����Ҫ�ڷ�������ʱ�Ž�request�����У�
		String postURI = request.getParameter("postURI");
		ModelAndView mAndView=new ModelAndView();
		List<String> thisMovieComm = customServ.getThisMovieComm(movieId);
		mAndView.addObject("thisMovieComm", thisMovieComm);
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * ɾ����Ӱ����
	 */
	@RequestMapping("delMovieComm")
	public ModelAndView delSubmitCommentAboutMovie(String commentNumb,HttpServletRequest request) {
		//���������uri��http:\\**\**.jsp,���������url���Ƿ��������URI����Ҫ�ڷ�������ʱ�Ž�request�����У�
		String postURI = request.getParameter("postURI");
		HttpSession session=request.getSession();
		Customer cust=(Customer) session.getAttribute("customer");
		ModelAndView mAndView=new ModelAndView();
		customServ.delMovieComm(Integer.valueOf(commentNumb));
		mAndView.addObject("delMovieCommMsg", "ɾ�����۳ɹ���");
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	@RequestMapping("ChangeVIPLevel")
	public ModelAndView changeVIPLevel(Customer cust,String newLevel,HttpSession session) {
		ModelAndView mAndView=new ModelAndView();
		cust.setVIPLevel(newLevel);
		customServ.changeCustomer(cust);
		session.setAttribute("customer", cust);
		mAndView.addObject("changeDataMsg", "�޸ĳɹ���");
		mAndView.setViewName("redirect:personCenter.jsp");
		return mAndView;
	}

	/**
	 * �û��˳�
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("Logout")
	public ModelAndView logout(HttpSession session){
		ModelAndView mAndView=new ModelAndView();
		session.removeAttribute("customer");
		mAndView.addObject("LogoutMsg", "�ɹ��˳���");
		mAndView.setViewName("redirect:login.jsp");
		return mAndView;	
	}

}