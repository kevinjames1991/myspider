package com.crawl.jiuzhou;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault(); //构建一个Client
		HttpGet get = new HttpGet("https://imp.modernavenue.com/login");
		HttpPost post = new HttpPost("https://imp.modernavenue.com/login");//构建一个POST请求
		//构建表单参数
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("Username", "admin"));
		formParams.add(new BasicNameValuePair("Password", "admin"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");//将表单参数转化为“实体”
		post.setEntity(entity);//将“实体“设置到POST请求里
		HttpResponse response = client.execute(post);//提交POST请求
		HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
		String content = EntityUtils.toString(result);;//用httpcore.jar提供的工具类将"实体"转化为字符串打印到控制台
		System.out.println(content);
		if(content.contains("登陆成功")){
			System.out.println("登陆成功！！！");
		}

	}

}
