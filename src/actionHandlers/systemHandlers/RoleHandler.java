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
 * ϵͳ����Ա�������ӡ��޸ĺ�ɾ����ɫ����ϵͳ����Աֻ�������ݿ������޸ĺ�ɾ��
 * @author www25
 *
 */
@Controller
@RequestMapping("Sys/")
public class RoleHandler {
	//�ʼ���ִ֤�ж���
	private AccountInfo accountInfo;
	private IndustrySMS industrySMS;
	//��֤ʱ��=��ǰ������֤���ʱ��new Date()+���õ�ʱ��vadateTime
	private Timestamp validateTime;
	private SystemService sysServ;
	
	public ModelAndView sysManagerlogin(Integer managerNumb,String password,String validatCode,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		//�����֤���Ƿ���ȷ
		if(validatCode.equals(session.getAttribute("loginValidateCode"))){
			System.out.println("��֤����ȷ����");
			//��֤�û��Ƿ����
			boolean ifExists=sysServ.ifExists(managerNumb);
			if(ifExists==false){
				mAndView.addObject("sysManagerloginMsg", "�ù���Ա�����ڣ�");
				mAndView.setViewName("redirect:sysManagerlogin.jsp");
				return mAndView;
			}else{
				//��֤�����Ƿ���ȷ�����ڰ�ȫԭ��Ӧ���ٴ��õ�¼��ʽ��ֵ��������в�ѯ����Ӧ����û������ٱȶ������Ƿ���ȷ
				boolean isRight=sysServ.checkPassword(managerNumb,password);
				if(isRight==false) {
					mAndView.addObject("sysManagerloginMsg", "�������");
					mAndView.setViewName("redirect:sysManagerlogin.jsp");
					return mAndView;
				}else{
					//��manager��Ϊ�Ựֵ�����Ա�����ҳ������
					session.setAttribute("manager", managerNumb);
					String postURI = request.getParameter("postURI");
					mAndView.setViewName("redirect:"+postURI);
					return mAndView;
				}
			}
		}else{
			mAndView.addObject("sysManagerloginMsg", "��֤�����");
			mAndView.setViewName("redirect:sysManagerlogin.jsp");
			return mAndView;		
		}
	}
	
	/**
	 * ���ӽ�ɫ
	 */
	@RequestMapping("AddRole")
	public ModelAndView addRole(Role newRole,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {
			sysServ.addRole(newRole);
			mAndView.addObject("AddRoleMsg", "��ӳɹ���");
		}else {
			mAndView.addObject("AddRoleMsg", "��ϵͳ����Ա�޷���ӣ�");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * �޸Ľ�ɫ����
	 */
	@RequestMapping("ChangeRoleData")
	public ModelAndView changeRoleData(Role role,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {			
				sysServ.changeRoleData(role);
				mAndView.addObject("ChangeRoleDataMsg", "�޸ĳɹ���");
		}else {
			mAndView.addObject("ChangeRoleDataMsg", "��ϵͳ����Ա�޷��޸ģ�");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * ɾ����ɫ
	 */
	@RequestMapping("DeleteRole")
	public ModelAndView deleteRole(Integer roleNumb,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {			
				sysServ.deleteRole(roleNumb);
				mAndView.addObject("DeleteRoleMsg", "ɾ���ɹ���");
		}else {
			mAndView.addObject("DeleteRoleMsg", "��ϵͳ����Ա�޷�ɾ����");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * ��ѯ���н�ɫ:����ӽ�ɫ-Ȩ�ޱ��ж�ȡ��ɫ��Ȩ��
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
			mAndView.addObject("DeleteRoleMsg", "��ϵͳ����Ա�޷���ѯ��");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * ����Ȩ��
	 */
	@RequestMapping("AddAuthority")
	public ModelAndView addAuthority(String newAuthority,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {
			sysServ.addAuthority(newAuthority);
			mAndView.addObject("AddAuthorityMsg", "��ӳɹ���");
		}else {
			mAndView.addObject("AddAuthority"+"Msg", "��ϵͳ����Ա�޷���ӣ�");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * �޸�Ȩ������
	 */
	@RequestMapping("ChangeAuthority")
	public ModelAndView changeAuthority(Authority authority,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {			
			sysServ.changeAuthority(authority);
			mAndView.addObject("ChangeAuthority"+"Msg", "�޸ĳɹ���");
		}else {
			mAndView.addObject("ChangeAuthority"+"Msg", "��ϵͳ����Ա�޷��޸ģ�");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * ɾ��Ȩ��
	 */
	@RequestMapping("DeleteAuthority")
	public ModelAndView deleteAuthority(Integer authorityNumb,HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mAndView=new ModelAndView();
		String postURI = request.getParameter("postURI");
		Integer manager=(Integer) session.getAttribute("manager");
		if (manager!=null) {			
			sysServ.deleteAuthority(authorityNumb);
			mAndView.addObject("DeleteAuthority"+"Msg", "ɾ���ɹ���");
		}else {
			mAndView.addObject("DeleteAuthority"+"Msg", "��ϵͳ����Ա�޷�ɾ����");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
	}
	
	/**
	 * ��ѯ����Ȩ��
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
			mAndView.addObject("DeleteRoleMsg", "��ϵͳ����Ա�޷���ѯ��");
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
			mAndView.addObject("Grant"+"Msg", "��Ȩ�ɹ���");
		}else {
			mAndView.addObject("Grant"+"Msg", "��ϵͳ����Ա�޷���Ȩ��");
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
			mAndView.addObject("Grant"+"Msg", "��Ȩ�ɹ���");
		}else {
			mAndView.addObject("Grant"+"Msg", "��ϵͳ����Ա�޷���Ȩ��");
		}
		mAndView.setViewName("redirect:"+postURI);
		return mAndView;
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
}
