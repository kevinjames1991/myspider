package com.crawl.jiuzhou;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HTMLUnitTest {

	public static void main(String[] args) throws Exception {
		HTMLUnitTest htmlUnitTest = new HTMLUnitTest();
		WebClient webClient = htmlUnitTest.getWebClient();
		HtmlPage loginPage = htmlUnitTest.login(webClient, "http://th55.net/SiteSort/TS111/index.aspx");
		DomElement domElement = loginPage.getElementById("aTvpd");
		HtmlPage domElementTv = domElement.click();
		System.out.println("===============domElementTv=========");
		DomElement domElement1 = domElementTv.getElementById("topmenu1");
		HtmlPage domElementAv = domElement1.click();
		System.out.println(domElementAv.asXml());
//		DomNodeList<DomNode> domNodeList = domElementAv.querySelectorAll(".NextClass");
//		for (DomNode domNode : domNodeList) {
//		}
		//跳转到尾页
		DomNodeList<DomElement> domElements = domElementAv.getElementsByTagName("input");
		for (DomElement tempDom : domElements) {
			System.out.println(tempDom.getAttribute("class")+"====="+tempDom.getAttribute("value"));
			//获取尾页页面内容
			if (tempDom.getAttribute("class").equals("NexClass") && tempDom.getAttribute("value").trim().equals("尾頁")) {
				HtmlPage domElementAvEnd = tempDom.click();
				Thread.sleep(10000);
				System.out.println(domElementAvEnd.asXml());
				DomNodeList<DomElement> domElementsEnd = domElementAvEnd.getElementsByTagName("a");
				//遍历一页中的所有
				for (DomElement temp : domElementsEnd) {
					if (temp.getAttribute("class").equals("ny-ypjslj-a")) {
						HtmlPage domElementAvDetail = temp.click();
						System.out.println(domElementAvDetail.getElementById("ContentPlaceHolder1_title").asText());
						DomNodeList<DomElement> elemetsDetail = domElementAvDetail.getElementsByTagName("a");
						for (DomElement temp1 : elemetsDetail){
							//进入播放页获取视频链接
							if (temp1.getAttribute("class").equals("ypjs-xx-jsani")) {
								HtmlPage domElementAvPlay = temp1.click();
								System.out.println(domElementAvPlay.getElementById("hidevalue_url").getAttribute("value"));
								break;
							}
						}
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
		
		

	/*	@SuppressWarnings("resource")
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
		}*/
	}
	
	public WebClient getWebClient() {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(true);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setTimeout(10000);
		return webClient;
	}
	
	public HtmlPage login(WebClient webClient,String url) throws Exception{
		DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient
				.getCredentialsProvider();
		credentialsProvider.addCredentials("username", "password");

		HtmlPage page = webClient.getPage(url);
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
		HtmlPage loginPage = submit.click();
		webClient.waitForBackgroundJavaScript(30000);
//		System.out.println(resultP.asXml());
		return loginPage;
	}
}
