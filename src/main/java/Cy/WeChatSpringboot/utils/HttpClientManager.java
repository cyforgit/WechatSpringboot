package Cy.WeChatSpringboot.utils;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class HttpClientManager {

	private static final String userAgent = "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83)";
	
	private static HttpParams params;
	private static ClientConnectionManager conMgr;
	private static HttpClient client;
	
	static {
		
		params = new BasicHttpParams();

        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);//http协议版本
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);//编码格式
        HttpProtocolParams.setUseExpectContinue(params, true);//
        HttpProtocolParams.setUserAgent(params,userAgent);//
        
        
        /*最大连接数*/
        ConnManagerParams.setMaxTotalConnections(params, CommonConstants.HTTP_MAX_CON);
        
        /*每个路由的最大连接数*/
        ConnPerRouteBean connPerRoute = new ConnPerRouteBean(CommonConstants.HTTP_MAX_CON_PERROUTE);
        ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);
          
        /* 从连接池中取连接的超时时间 */
        ConnManagerParams.setTimeout(params, CommonConstants.GET_CON_TIMEOUT);
        /* 连接超时 */
        HttpConnectionParams.setConnectionTimeout(params, CommonConstants.CON_TIMEOUT);
        /* 接收超时 */
        HttpConnectionParams.setSoTimeout(params, CommonConstants.RECV_TIMEOUT);
        
        
        /* 设置我们的HttpClient支持HTTP和HTTPS两种模式 */
        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        
        /* 使用线程安全的连接管理来创建HttpClient */
        conMgr = new ThreadSafeClientConnManager(params, schReg);

	}
	
	public static synchronized HttpClient getHttpClient() {
		
		if(conMgr !=null) {
			
			conMgr.closeExpiredConnections();
		}

		if(client == null) {
			
			client =  new DefaultHttpClient(conMgr, params);
		}
		
		return client;
	}
	
	@Override
	protected void finalize() {
		
		if(conMgr !=null) {
			
			conMgr.shutdown();
			conMgr = null;
			client = null;
		}
	}
}
