package com.crawl.jiuzhou;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpClientTest2 {
//
	private static final String loginUrl = "http://ha66.net/LoadData/Pd.ashx";
	private static final String inUrl = "http://tv222.net/index.aspx";
//	private static final String loginUrl = "http://127.0.0.1/manager/loginservice/login.kn";
//	private static final String inUrl = "http://127.0.0.1/manager/buyerinfoservice/findBuyerInfoPage.kn";
	public static void main(String[] args) {
		// 创建一个HttpClient
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		BasicCookieStore cookieStore = new BasicCookieStore();
		BasicClientCookie cookie = new BasicClientCookie("test", "aaa; bbb");
		BasicClientCookie cookie2 = new BasicClientCookie("test2", "aaa: bbb");
		cookie.setDomain("127.0.0.1");
		cookie2.setDomain("127.0.0.1");
		cookie.setPath("/");
		cookie.setVersion(1);
		cookieStore.addCookie(cookie);
		cookieStore.addCookie(cookie2);
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).disableRedirectHandling()
				.setDefaultCookieStore(cookieStore).build();
		HttpContext context = new BasicHttpContext();
		try {
//			 创建一个get请求用来接收_xsrf信息
//			 HttpGet get = new HttpGet(loginUrl);
//			 // 获取_xsrf
//			 CloseableHttpResponse response = httpClient.execute(get);
//			 printResponse(response);
//			 System.out.println(getCookie(response));
			// String responseHtml = EntityUtils.toString(response.getEntity());
			// String xsrfValue = responseHtml.split("<input type=\"hidden\"
			// name=\"_xsrf\" value=\"")[1].split("\"/>")[0];
			// System.out.println("xsrfValue:" + xsrfValue);
			// response.close();

			// 构造post数据
			List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();
			// valuePairs.add(new BasicNameValuePair("_xsrf", xsrfValue));
//			valuePairs.add(new BasicNameValuePair("loginname", "275648393@qq.com"));
//			valuePairs.add(new BasicNameValuePair("nloginpwd", "czw663662123"));
//			valuePairs.add(new BasicNameValuePair("email", "275648393@qq.com"));
			valuePairs.add(new BasicNameValuePair("username", "admin"));
			valuePairs.add(new BasicNameValuePair("password", "admin"));
//			valuePairs.add(new BasicNameValuePair("account", "chenzhangwei"));
//			valuePairs.add(new BasicNameValuePair("ps", "chenzhangwei2017011"));
			valuePairs.add(new BasicNameValuePair("account", "admin"));
			valuePairs.add(new BasicNameValuePair("ps", "123456"));
			valuePairs.add(new BasicNameValuePair("txtUser", "ds6265"));
			valuePairs.add(new BasicNameValuePair("txtPassword", "czw663662"));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
			// 创建一个post请求
			HttpPost post = new HttpPost(loginUrl);
			// 注入post数据
			post.setEntity(entity);
			
			// 创建一个GET请求
			String str = EntityUtils.toString(new UrlEncodedFormEntity(valuePairs, Consts.UTF_8));
			HttpGet httpget = new HttpGet(loginUrl+"?" + str);
			
			CloseableHttpResponse httpResponse = httpClient.execute(post,context);
			// 打印登录是否成功信息
//			printResponse(httpResponse);
			System.out.println(EntityUtils.toString(httpResponse.getEntity()));
			// 构造一个get请求，用来测试登录cookie是否拿到
			HttpGet g = new HttpGet(inUrl);
			
			// 得到post请求返回的cookie信息
			String c = getCookie(httpResponse);
			System.out.println(c);
			// 将cookie注入到get请求头当中
//			g.setHeader("Cookie", c);
			CloseableHttpResponse r = httpClient.execute(g,context);
			String content = EntityUtils.toString(r.getEntity());
			System.out.println("==============================");
			System.out.println(content);
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void printResponse(HttpResponse httpResponse) throws ParseException, IOException {
		// 获取响应消息实体
		HttpEntity entity = httpResponse.getEntity();
		// 响应状态
		System.out.println("status:" + httpResponse.getStatusLine());
		System.out.println("headers:");
		HeaderIterator iterator = httpResponse.headerIterator();
		while (iterator.hasNext()) {
			System.out.println("\t" + iterator.next());
		}
		// 判断响应实体是否为空
		if (entity != null) {
			String responseString = EntityUtils.toString(entity);
			System.out.println("response length:" + responseString.length());
			System.out.println("response content:" + responseString.replace("\r\n", ""));
		}
	}

	public static Map<String, String> cookieMap = new HashMap<String, String>(64);

	// 从响应信息中获取cookie
	public static String getCookie(HttpResponse httpResponse) {
		Header headers[] = httpResponse.getHeaders("Set-Cookie");
		if (headers == null || headers.length == 0) {
			System.out.println("=============there are no cookies");
			return null;
		}
		String cookie = "";
		for (int i = 0; i < headers.length; i++) {
			cookie += headers[i].getValue();
			if (i != headers.length - 1) {
				cookie += ";";
			}
		}

		String cookies[] = cookie.split(";");
		for (String c : cookies) {
			c = c.trim();
			if (cookieMap.containsKey(c.split("=")[0])) {
				cookieMap.remove(c.split("=")[0]);
			}
			cookieMap.put(c.split("=")[0],
					c.split("=").length == 1 ? "" : (c.split("=").length == 2 ? c.split("=")[1] : c.split("=", 2)[1]));
		}
		String cookiesTmp = "";
		for (String key : cookieMap.keySet()) {
			cookiesTmp += key + "=" + cookieMap.get(key) + ";";
		}

		return cookiesTmp.substring(0, cookiesTmp.length() - 2);
	}
}