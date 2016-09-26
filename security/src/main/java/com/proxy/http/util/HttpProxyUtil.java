///**
// * 
// */
//package com.proxy.http.util;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.security.KeyManagementException;
//import java.security.KeyStore;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.CertificateException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.net.ssl.SSLContext;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.ParseException;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLContexts;
//import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
///**
// * @author Administrator
// *
// */
//public class HttpProxyUtil {
//	public static String getIpAddr(HttpServletRequest request) {   
//	     String ipAddress = null;   
//	     //ipAddress = request.getRemoteAddr();   
//	     ipAddress = request.getHeader("x-forwarded-for");   
//	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
//	      ipAddress = request.getHeader("Proxy-Client-IP");   
//	     }   
//	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
//	         ipAddress = request.getHeader("WL-Proxy-Client-IP");   
//	     }   
//	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
//	      ipAddress = request.getRemoteAddr();   
//	      if(ipAddress.equals("127.0.0.1")){   
//	       //根据网卡取本机配置的IP   
//	       InetAddress inet=null;   
//	    try {   
//	     inet = InetAddress.getLocalHost();   
//	    } catch (UnknownHostException e) {   
//	     e.printStackTrace();   
//	    }   
//	    ipAddress= inet.getHostAddress();   
//	      }   
//	            
//	     }   
//	     //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割   
//	     if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15   
//	         if(ipAddress.indexOf(",")>0){   
//	             ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));   
//	         }   
//	     }   
//	     return ipAddress;    
//	  }  
//	
//	/**
//	 * HttpClient连接SSL
//	 */
//	public static void  ssl() {
//		CloseableHttpClient httpclient = null;
//		try {
//			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//			FileInputStream instream = new FileInputStream(new File("d:\\tomcat.keystore"));
//			try {
//				// 加载keyStore d:\\tomcat.keystore  
//				trustStore.load(instream, "123456".toCharArray());
//			} catch (CertificateException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					instream.close();
//				} catch (Exception ignore) {
//				}
//			}
//			// 相信自己的CA和所有自签名的证书
//			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
//			// 只允许使用TLSv1协议
//			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
//					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
//			// 创建http请求(get方式)
//			HttpGet httpget = new HttpGet("https://localhost:8443/myDemo/Ajax/serivceJ.action");
//			System.out.println("executing request" + httpget.getRequestLine());
//			CloseableHttpResponse response = httpclient.execute(httpget);
//			try {
//				HttpEntity entity = response.getEntity();
//				System.out.println("----------------------------------------");
//				System.out.println(response.getStatusLine());
//				if (entity != null) {
//					System.out.println("Response content length: " + entity.getContentLength());
//					System.out.println(EntityUtils.toString(entity));
//					EntityUtils.consume(entity);
//				}
//			} finally {
//				response.close();
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (KeyManagementException e) {
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (KeyStoreException e) {
//			e.printStackTrace();
//		} finally {
//			if (httpclient != null) {
//				try {
//					httpclient.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//	/**
//	 * post方式提交表单（模拟用户登录请求）
//	 */
//	public  static void  postForm() {
//		// 创建默认的httpClient实例.  
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		// 创建httppost  
//		HttpPost httppost = new HttpPost("http://www.ip138.com");
//		// 创建参数队列  
//		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//		formparams.add(new BasicNameValuePair("username", "admin"));
//		formparams.add(new BasicNameValuePair("password", "123456"));
//		UrlEncodedFormEntity uefEntity;
//		try {
//			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
//			httppost.setEntity(uefEntity);
//			System.out.println("executing request " + httppost.getURI());
//			CloseableHttpResponse response = httpclient.execute(httppost);
//			try {
//				HttpEntity entity = response.getEntity();
//				if (entity != null) {
//					System.out.println("--------------------------------------");
//					System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
//					System.out.println("--------------------------------------");
//				}
//			} finally {
//				response.close();
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			// 关闭连接,释放资源  
//			try {
//				httpclient.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
//	 */
//	public static void  post() {
//		// 创建默认的httpClient实例.  
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		// 创建httppost  
//		HttpPost httppost = new HttpPost("http://www.ip138.com");
//		// 创建参数队列  
//		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//		formparams.add(new BasicNameValuePair("type", "house"));
//		UrlEncodedFormEntity uefEntity;
//		try {
//			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
//			httppost.setEntity(uefEntity);
//			System.out.println("executing request " + httppost.getURI());
//			CloseableHttpResponse response = httpclient.execute(httppost);
//			try {
//				HttpEntity entity = response.getEntity();
//				if (entity != null) {
//					System.out.println("--------------------------------------");
//					System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
//					System.out.println("--------------------------------------");
//				}
//			} finally {
//				response.close();
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			// 关闭连接,释放资源  
//			try {
//				httpclient.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * 发送 get请求
//	 */
//	public static void  get() {
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		try {
//			// 创建httpget.  
//			HttpGet httpget = new HttpGet("http://www.ip138.com");
//			System.out.println("executing request " + httpget.getURI());
//			// 执行get请求.  
//			CloseableHttpResponse response = httpclient.execute(httpget);
//			try {
//				// 获取响应实体  
//				HttpEntity entity = response.getEntity();
//				System.out.println("--------------------------------------");
//				// 打印响应状态  
//				System.out.println(response.getStatusLine());
//				if (entity != null) {
//					// 打印响应内容长度  
//					System.out.println("Response content length: " + entity.getContentLength());
//					// 打印响应内容  
//					System.out.println("Response content: " + EntityUtils.toString(entity));
//				}
//				System.out.println("------------------------------------");
//			} finally {
//				response.close();
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			// 关闭连接,释放资源  
//			try {
//				httpclient.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//}
