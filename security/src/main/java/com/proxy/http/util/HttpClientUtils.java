//package com.proxy.http.util;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.security.KeyManagementException;
//import java.security.KeyStore;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.UnrecoverableKeyException;
//import java.security.cert.CertificateException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.net.ssl.SSLContext;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.ParseException;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.ssl.SSLContexts;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.proxy.http.enumconst.ContentTypeEnum;
//
//public class HttpClientUtils {
//    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
//    /**
//     * 
//     * 方法说明 http get 请求
//     * 
//     * @param uri
//     *            请求地址
//     * @return 请求响应内容
//     */
//    public static String doGet(String uri) {
//        String content = null;
//    	CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(uri);
//        // 执行请求.
//        content = doRequest(httpClient, httpGet,true);
//        return content;
//    }
// 
//    /**
//     * 
//     * 方法说明 http get 请求
//     * 
//     * @param uri
//     *            请求地址
//     * @return 请求响应内容
//     */
//    public static String doGet(String uri,CloseableHttpClient httpClient) {
//        String content = null;
//        if (httpClient==null) {
//        	 httpClient = HttpClients.createDefault();
//		}
//        HttpGet httpGet = new HttpGet(uri);
//        // 执行请求.
//        content = doRequest(httpClient, httpGet,false);
//        return content;
//    }
//
//    /**
//     * 
//     * 方法说明 http post 请求
//     * 
//     * @param uri
//     *            请求地址
//     * @param params
//     *            请求附带的键值对参数
//     * @return 请求响应内容
//     */
//    public static String doPost(String uri, Map<String, Object> params) {
//        String content = null;
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(uri);
//        // 组装post请求参数
//        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
//        Set<String> keySet = params.keySet();
//        for (String key : keySet) {
//            nameValuePairList.add(new BasicNameValuePair(key, (String) params.get(key)));
//        }
// 
//        HttpEntity entity = null;
//        try {
//            entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        httpPost.setEntity(entity);
//        // 执行请求
//        content = doRequest(httpClient, httpPost,true);
//        return content;
//    }
// 
//
//    /**
//     * 
//     * 方法说明 http post 请求
//     * 
//     * @param uri
//     *            请求地址
//     * @param params
//     *            请求附带的键值对参数
//     * @return 请求响应内容
//     */
//    public static String doPost(String uri, Map<String, Object> params,CloseableHttpClient httpClient) {
//        String content = null;
//        if (httpClient==null) {
//        	 httpClient = HttpClients.createDefault();
//		}
//        HttpPost httpPost = new HttpPost(uri);
//        // 组装post请求参数
//        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
//        Set<String> keySet = params.keySet();
//        for (String key : keySet) {
//            nameValuePairList.add(new BasicNameValuePair(key, (String) params.get(key)));
//        }
//        HttpEntity entity = null;
//        try {
//            entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        httpPost.setEntity(entity);
//        // 执行请求
//        content = doRequest(httpClient, httpPost,false);
//        return content;
//    }
//    
//    /**
//     * 
//     * 方法说明 http post 请求
//     * 
//     * @param uri
//     *            请求地址
//     * @param postData
//     *            请求附带的文本数据
//         * @param contentType
//     *            请求附带的文本数据格式
//     * @return 请求响应内容
//     */
//    public static String doPost(String uri, String postData,ContentTypeEnum contentType) {
//        String content = null;
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(uri);
//        StringEntity postEntity = new StringEntity(postData, "UTF-8");
//        postEntity.setContentType(contentType.getContentTypeValue());
//        httpPost.setEntity(postEntity);
//        // 执行请求
//        content = doRequest(httpClient, httpPost,true);
//        return content;
//    }
// 
//    /**
//     * 
//     * 方法说明 https get 请求(附带验证个人证书PKCS12)
//     * 
//     * @param uri
//     *            请求地址
//     * @param certFileUrl
//     *            个人PKCS12证书本地路径
//     * @param password
//     *            证书密钥
//     * @return 请求响应内容
//     */
//    public static String doGetWithCertPKCS12(String uri, String certFileUrl, String password) {
//        String content = null;
//        CloseableHttpClient httpClient = getSslHttpClient(certFileUrl, password);
//        HttpGet httpGet = new HttpGet(uri);
//        // 执行请求.
//        content = doRequest(httpClient, httpGet,true);
//        return content;
//    }
// 
//    /**
//     * 
//     * 方法说明 https post 请求(附带验证个人证书PKCS12)
//     * 
//     * @param uri
//     *            请求地址
//     * @param postData
//     *            请求附带的文本数据
//     * @param certFileUrl
//     *            个人PKCS12证书本地路径
//     * @param password
//     *            证书密钥
//     * @return 请求响应内容
//     */
//    public static String doPostWithCertPKCS12(String uri, String postData, String certFileUrl, String password) {
//        String content = null;
//        CloseableHttpClient httpClient = getSslHttpClient(certFileUrl, password);
//        HttpPost httpPost = new HttpPost(uri);
//        StringEntity postEntity = new StringEntity(postData, "UTF-8");
//        httpPost.setEntity(postEntity);
//        // 执行请求.
//        content = doRequest(httpClient, httpPost,true);
//        return content;
//    }
// 
//    /**
//     * 
//     * 方法说明 初始化带个人PKCS12证书的 HttpClient
//     * 
//     * @param certLocalPath
//     *            个人PKCS12证书本地路径
//     * @param password
//     *            证书密钥
//     * @return HttpClient
//     */
//    private static CloseableHttpClient getSslHttpClient(String certLocalPath, String password) {
//        CloseableHttpClient httpClient = null;
//        KeyStore keyStore = null;
//        FileInputStream instream = null;
//        try {
//            keyStore = KeyStore.getInstance("PKCS12");
//            instream = new FileInputStream(new File(certLocalPath));// 加载本地的证书进行https加密传输
//            keyStore.load(instream, password.toCharArray());// 设置证书密码
//        } catch (NoSuchAlgorithmException | CertificateException | IOException | KeyStoreException e1) {
//            e1.printStackTrace();
//        } finally {
//            try {
//                instream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        SSLContext sslcontext = null;
//        try {
//            sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray()).build();
//        } catch (KeyManagementException | UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
//            e.printStackTrace();
//        }
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
//                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//        httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
//        return httpClient;
//    }
// 
//    /**
//     * 
//     * 方法说明 发起 http 请求
//     * 
//     * @param request
//     *            请求对象
//     * @return 请求响应内容
//     * @param httpClient
//     */
//    private static String doRequest(CloseableHttpClient httpClient, HttpUriRequest request,boolean closed) {
//        CloseableHttpResponse response = null;
//        try {
//            logger.debug("发起http请求：{} ", request.getURI());
//            response = httpClient.execute(request);
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//            	if (closed) {
//            		httpClient.close();
//				}
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return praseResponse(response);
//    }
// 
//    
//    /**
//     * 
//     * 方法说明 解析请求响应
//     * 
//     * @param response
//     *            响应对象
//     * @return 请求响应内容
//     */
//    private static String praseResponse(CloseableHttpResponse response) {
//        String content = "";
//        if (response != null) {
//            // 获取响应实体
//            HttpEntity entity = response.getEntity();
//            // 打印响应状态
//            logger.debug("请求返回状态：{} ", response.getStatusLine());
//            if (entity != null) {
//                try {
//                    content = EntityUtils.toString(entity, "UTF-8");
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    try {
//                        response.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        // 打印响应内容
//        logger.debug("请求返回结果:{} ", content);
//        return content;
//    }
//}