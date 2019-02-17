package actionHandlers.systemHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import customModule.entity.Customer;
import systemModule.entity.Authority;
import systemModule.entity.Role;
import systemModule.service.SystemService;
import utils.miaodiyun.source.httpApiDemo.AccountInfo;
import utils.miaodiyun.source.httpApiDemo.IndustrySMS;
import utils.miaodiyun.source.httpApiDemo.common.HttpUtil;

/**
 * 系统管理员可以增加、修改和删除角色，但系统管理员只能在数据库增加修改和删除
 * @author www25
 *
 */
@Controller
@RequestMapping("Sys/")
public class RoleHandler {
	//邮件验证执行对象
	private AccountInfo accountInfo;
	private IndustrySMS industrySMS;
	//验证时间=当前生成验证码的时间new Date()+设置的时长vadateTime
	private Timestamp validateTime;
	private SystemService sysServ;
	
	public ModelAndView sysManagerlogin(Integer managerNumb,String password,String validatCode,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		//检测验证码是否正确
		if(validatCode.equals(session.getAttribute("loginValidateCode"))){
			System.out.println("验证码正确！！");
			//验证用户是否存在
			boolean ifExists=sysServ.ifExists(managerNumb);
			if(ifExists==false){
				mAndView.addObject("sysManagerloginMsg", "该管理员不存在！");
				mAndView.setViewName("redirect:sysManagerlogin.jsp");
				return mAndView;
			}else{
				//验证密码是否正确：基于安全原则，应该再次用登录方式的值和密码进行查询，不应查出用户资料再比对密码是否正确
				boolean isRight=sysServ.checkPassword(managerNumb,password);
				if(isRight==false) {
					mAndView.addObject("sysManagerloginMsg", "密码错误！");
					mAndView.setViewName("redirect:sysManagerlogin.jsp");
					return mAndView;
				}else{
					//将manager设为会话值，可以被其他页面引用
					session.setAttribute("manager", managerNumb);
					String postURI = request.getParameter("postURI");
					mAndView.setViewName("redirect:"+postURI);
					return mAndView;
				}
			}
		}else{
			mAndView.addObject("sysManagerloginMsg", "验证码错误！");
			mAndView.setViewName("redirect:sysManagerlogin.jsp");
			return mAndView;		
		}
	}
	
	/**
	 * 增加角色
	 */
	@RequestMapping("AddRole")
	public ModelAndView addRole(Role newRole,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {
			sysServ.addRole(newRole);
			mAndView.addObject("AddRoleMsg", "添加成功！");
		}else {
			mAndView.addObject("AddRoleMsg", "非系统管理员无法添加！");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * 修改角色资料
	 */
	@RequestMapping("ChangeRoleData")
	public ModelAndView changeRoleData(Role role,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {			
				sysServ.changeRoleData(role);
				mAndView.addObject("ChangeRoleDataMsg", "修改成功！");
		}else {
			mAndView.addObject("ChangeRoleDataMsg", "非系统管理员无法修改！");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * 删除角色
	 */
	@RequestMapping("DeleteRole")
	public ModelAndView deleteRole(Integer roleNumb,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {			
				sysServ.deleteRole(roleNumb);
				mAndView.addObject("DeleteRoleMsg", "删除成功！");
		}else {
			mAndView.addObject("DeleteRoleMsg", "非系统管理员无法删除！");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * 查询所有角色:还需从角色-权限表中读取角色的权限
	 */
	@RequestMapping("GetRoles")
	public ModelAndView getRoles(HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {			
			List<Role> roleList=sysServ.getRoles();
			mAndView.addObject("roleList", roleList);
		}else {
			mAndView.addObject("DeleteRoleMsg", "非系统管理员无法查询！");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * 增加权限
	 */
	@RequestMapping("AddAuthority")
	public ModelAndView addAuthority(String newAuthority,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {
			sysServ.addAuthority(newAuthority);
			mAndView.addObject("AddAuthorityMsg", "添加成功！");
		}else {
			mAndView.addObject("AddAuthority"+"Msg", "非系统管理员无法添加！");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * 修改权限内容
	 */
	@RequestMapping("ChangeAuthority")
	public ModelAndView changeAuthority(Authority authority,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {			
			sysServ.changeAuthority(authority);
			mAndView.addObject("ChangeAuthority"+"Msg", "修改成功！");
		}else {
			mAndView.addObject("ChangeAuthority"+"Msg", "非系统管理员无法修改！");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * 删除权限
	 */
	@RequestMapping("DeleteAuthority")
	public ModelAndView deleteAuthority(Integer authorityNumb,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {			
			sysServ.deleteAuthority(authorityNumb);
			mAndView.addObject("DeleteAuthority"+"Msg", "删除成功！");
		}else {
			mAndView.addObject("DeleteAuthority"+"Msg", "非系统管理员无法删除！");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * 查询所有权限
	 */
	@RequestMapping("GetAuthorities")
	public ModelAndView getAuthorities(HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {			
			List<Authority> authorityList=sysServ.getAuthorities();
			mAndView.addObject("authorityList", authorityList);
		}else {
			mAndView.addObject("DeleteRoleMsg", "非系统管理员无法查询！");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	@RequestMapping("Grant")
	public ModelAndView grant(Integer roleNumb,Integer authorityNumb,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {
			sysServ.grant(roleNumb,authorityNumb);
			mAndView.addObject("Grant"+"Msg", "授权成功！");
		}else {
			mAndView.addObject("Grant"+"Msg", "非系统管理员无法授权！");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	@RequestMapping("Revoke")
	public ModelAndView revoke(Integer roleNumb,Integer authorityNumb,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {
			sysServ.revoke(roleNumb,authorityNumb);
			mAndView.addObject("Grant"+"Msg", "收权成功！");
		}else {
			mAndView.addObject("Grant"+"Msg", "非系统管理员无法收权！");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
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
}
