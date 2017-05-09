package com.crawl.jiuzhou;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HTMLUnitTest {

	public static void main(String[] args) throws Exception {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(true);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setTimeout(10000);

		DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient
				.getCredentialsProvider();
		credentialsProvider.addCredentials("username", "password");

		HtmlPage page = webClient.getPage("http://ju11.net/");
		HtmlForm loginForm = (HtmlForm) page.getElementById("form1");

		// HtmlInput username = loginForm.getInputByName("txt_userid");
		// HtmlInput password = loginForm.getInputByName("txt_userpw");

		HtmlInput username = (HtmlInput) page.getElementById("txtUser");
		HtmlInput password = (HtmlInput) page.getElementById("txtPassword");
		username.setValueAttribute("ds6265");
		password.setValueAttribute("czw663662");

		DomElement submit = null;

		DomNodeList<DomElement> domElements = page.getElementsByTagName("a");
		for (DomElement temp : domElements) {
			if (temp.getAttribute("class").equals("login")) {
				submit = temp;
			}
		}
		HtmlPage resultP = submit.click();
		webClient.waitForBackgroundJavaScript(30000);
		String resultStr = resultP.asXml();
//		System.out.println(resultStr);
		
		DomElement domElement = resultP.getElementById("aTvpd");
		HtmlPage domElementTv = domElement.click();
//		System.out.println(domElementTv.asXml());
		DomElement domElement1 = domElementTv.getElementById("topmenu1");
		HtmlPage domElementAv = domElement1.click();
//		System.out.println(domElementAv.asXml());
		domElements = domElementAv.getElementsByTagName("a");
		for (DomElement temp : domElements) {
			if (temp.getAttribute("class").equals("ny-ypjslj-a")) {
				HtmlPage domElementAvTemp = temp.click();
//				System.out.println(domElementAvTemp.asXml());
				DomNodeList<DomElement> elemets = domElementAvTemp.getElementsByTagName("a");
				for (DomElement temp1 : elemets){
					if (temp1.getAttribute("class").equals("ypjs-xx-jsani")) {
						HtmlPage domElementAvTempD = temp1.click();
						System.out.println(domElementAvTempD.asXml());
						System.out.println("===========================================");
						System.out.println("===========================================");
						System.out.println("===========================================");
						System.out.println(domElementAvTempD.getElementById("hidevalue_url").asXml());
						System.out.println(domElementAvTempD.getElementById("hidevalue_url").getAttribute("value"));
						break;
					}
				}
				break;
			}
		}
		//????????????????????????????????????如何带上cookie登陆
		System.exit(0);
		
//		FileWriter writer = new FileWriter("mofang.html");
//		writer.write(resultP.getWebResponse().getContentAsString());
//		writer.close();
		
		

		@SuppressWarnings("resource")
		WebClient webClient2 = new WebClient();// 创建WebClient
		page = webClient2.getPage("http://ju11.net/"); // 打开百度

		DomNodeList<DomElement> list = page.getElementsByTagName("login");

		// 获得name为"登陆"的html元素
		HtmlElement htmlElement = page.getElementByName("登陆");
		page = htmlElement.click();// 调用click()方法
		// 获得name为"username"的html元素
		HtmlElement usernameEle = page.getElementByName("username");
		// 获得id为"password"的html元素
		HtmlElement passwordEle = (HtmlElement) page.getElementById("password");
		usernameEle.focus(); // 设置输入焦点
		usernameEle.type("username123"); // 填写值

		passwordEle.focus(); // 设置输入焦点
		passwordEle.type("mypassword"); // 填写值
		// 获得name为"登陆"的元素
		HtmlElement submitEle = page.getElementByName("登陆");
		// 点击“登陆”
		page = submitEle.click();
		String result = page.asXml();// 获得click()后的html页面（包括标签）
		if (result.contains("登陆成功！")) {
			System.out.println("登陆成功");
		} else {
			System.out.println("登陆失败");
		}
	}
}
