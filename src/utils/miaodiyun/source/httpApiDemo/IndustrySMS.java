package utils.miaodiyun.source.httpApiDemo;

import utils.miaodiyun.source.httpApiDemo.common.Config;
import utils.miaodiyun.source.httpApiDemo.common.HttpUtil;

public class IndustrySMS
{
	private static String operation = "/industrySMS/sendSMS";

	private static String accountSid = Config.ACCOUNT_SID;
	public static String to;
	public static String smsContent;

	/**
	 * */
	public static void execute()
	{
		String url = Config.BASE_URL + operation;
		String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + smsContent
				+ HttpUtil.createCommonParam();

		String result = HttpUtil.post(url, body);
		System.out.println("result:" + System.lineSeparator() + result);
	}
}
