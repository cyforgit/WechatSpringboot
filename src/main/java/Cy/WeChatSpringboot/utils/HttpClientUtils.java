package Cy.WeChatSpringboot.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientUtils {

	public static String getResponseFromGetMethod(HttpClient client,
			String url, String encodeType) throws IOException,
			ClientProtocolException {
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				entity.getContent(), encodeType));
		String buffer = null;
		StringBuffer responseBuf = new StringBuffer();
		while ((buffer = reader.readLine()) != null) {
			responseBuf.append(buffer);
		}
		get.abort();
		return responseBuf.toString();
	}
	
	public static String getResponseFromDeleteMethod(HttpClient client,
			String url, String encodeType) throws IOException,
			ClientProtocolException {
		HttpDelete delete = new HttpDelete(url);
		HttpResponse response = client.execute(delete);
		HttpEntity entity = response.getEntity();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				entity.getContent(), encodeType));
		String buffer = null;
		StringBuffer responseBuf = new StringBuffer();
		while ((buffer = reader.readLine()) != null) {
			responseBuf.append(buffer);
		}
		delete.abort();
		return responseBuf.toString();
	}

	public static String getResponseFromPostMethod(HttpClient client,
			String testUrl, HashMap<String, String> nameValueMap,
			String encodeType) throws UnsupportedEncodingException,
			IOException, ClientProtocolException {
		HttpPost post = new HttpPost(testUrl);
		if (nameValueMap != null && nameValueMap.size() > 0) {
			// 创建表单参数列表
			List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			Iterator<String> iterator = nameValueMap.keySet().iterator();
			String paramName;
			while (iterator.hasNext()) {
				paramName = iterator.next();
				// System.out.println("paramName:" + paramName + ",Value:"
				// + nameValueMap.get(paramName));
				qparams.add(new BasicNameValuePair(paramName, nameValueMap
						.get(paramName)));
			}
			// 填充表单
			post.setEntity(new UrlEncodedFormEntity(qparams, encodeType));
		}

		HttpResponse postResponse = client.execute(post);
		int statusCode = postResponse.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			HttpEntity entity = postResponse.getEntity();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), encodeType));
			String buffer;
			StringBuffer responseBuf = new StringBuffer();
			while ((buffer = reader.readLine()) != null) {
				responseBuf.append(buffer);
			}
			return responseBuf.toString();
		} else if (statusCode == 302) {// 302表示重定向
			Header[] hs = postResponse.getHeaders("Location");
			if (hs.length > 0) {
				String url = hs[0].getValue();
				post.abort();
				return getResponseFromGetMethod(client, url, encodeType);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static String getResponseFromPostMethod(HttpClient client,
			String testUrl,  Map<String, String> httpHeaderMap,String postContent, String encodeType)
			throws UnsupportedEncodingException, IOException,
			ClientProtocolException {
		HttpPost post = new HttpPost(testUrl);
		if (httpHeaderMap != null && httpHeaderMap.size() > 0) {
            Iterator<String> iterator = httpHeaderMap.keySet().iterator();
            String headerName;
            while (iterator.hasNext()) {
                headerName = iterator.next();
                post.setHeader(headerName, httpHeaderMap.get(headerName));
            }
        }
		post.setEntity(new StringEntity(postContent, encodeType));
		HttpResponse postResponse = client.execute(post);
		int statusCode = postResponse.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			HttpEntity entity = postResponse.getEntity();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), encodeType));
			String buffer;
			StringBuffer responseBuf = new StringBuffer();
			while ((buffer = reader.readLine()) != null) {
				responseBuf.append(buffer);
			}
			return responseBuf.toString();
		} else if (statusCode == 302) {// 302表示重定向
			Header[] hs = postResponse.getHeaders("Location");
			if (hs.length > 0) {
				String url = hs[0].getValue();
				post.abort();
				return getResponseFromGetMethod(client, url, encodeType);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
