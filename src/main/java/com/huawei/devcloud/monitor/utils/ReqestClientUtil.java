package com.huawei.devcloud.monitor.utils;

import com.apimgt.sdk.http.HttpMethodName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huawei.devcloud.monitor.model.JenkinsBean;
import com.huawei.devcloud.monitor.model.JenkinsResource;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.AuthCache;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReqestClientUtil
{
    private static final Logger logger = Logger.getLogger(ReqestClientUtil.class.getName());
   
    private static int CONNECT_TIMEOUT = 5000;

    private static int SOCKET_TIMEOUT = 120000;
    
    private int socketTimeOut = SOCKET_TIMEOUT;
    
    private static final String HEADER_DOMAIN = "domain_tag";
    
  	public static final String COMMON_DOMAIN_NAME = "op_service";

    public int getSocketTimeOut() {
        return socketTimeOut;
    }

    public void setSocketTimeOut(int socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }
    
    public JSONObject postByAksk(String requesturl, JenkinsBean jenkinsBean, String mimeType, String charset,
                                 String logPrefix, String ak, String sk) throws IOException, GeneralSecurityException, URISyntaxException
    {
    	Gson gson = new GsonBuilder().create();
    	mimeType = mimeType == null ? "application/json" : mimeType;
    	charset = charset == null ? "UTF-8" : charset;
    	HttpPost post = new HttpPost(requesturl);
        JSONObject result = null;
        try {
        	
        	Map<String, String> headers = new HashMap<String, String>();
        	headers.put("Content-Type", mimeType);
            String jenkinsInfo = gson.toJson(jenkinsBean);
            /**
             * 签名认证
             */
            Map<String, String> requestHeaders = AkSkHandler.getAkskHeaders(ak, sk, requesturl, HttpMethodName.POST, jenkinsInfo, headers);
        	
            HttpEntity entity = new StringEntity(jenkinsInfo,ContentType.create(mimeType,charset));
            post.setEntity(entity);
	      	// Put the header of the signed request to the new request.
            if(requestHeaders != null && !requestHeaders.isEmpty())
            {
            	for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
     	      	   if (entry.getKey().equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH.toString())) {
     	      	    continue;
     	      	   }
     	      	   post.addHeader(entry.getKey(), entry.getValue());
           	    }
            }
      	    
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            customReqConf.setConnectTimeout(CONNECT_TIMEOUT);
            customReqConf.setSocketTimeout(socketTimeOut);
            post.setConfig(customReqConf.build());
            String formatUrl = CommonUtils.replaceIp(requesturl);
            logger.log(Level.INFO,"[build-notification] request.url:"+formatUrl+" . jenkins:" + jenkinsBean.getUrl()+" . jenkins.version:"+jenkinsBean.getVersion()+". labels:"+jenkinsBean.getLabels()+".\n loadinfo:"+gson.toJson(jenkinsBean.getLoad_info()));
            result = this.commonRequest3(post, charset);
            
        }finally {
        	post.releaseConnection();
        }
    	return result;
    }
    
    public JSONObject post(String url, Map<String, String> headers, String body, String mimeType, String charset,
                           String logPrefix)
            throws IOException, GeneralSecurityException
    {
        mimeType = mimeType == null ? "application/json" : mimeType;
        charset = charset == null ? "UTF-8" : charset;
        HttpPost post = new HttpPost(url);
        JSONObject obj;
        try
        {
            if(StringUtils.isNotBlank(body))
            {
                HttpEntity entity = new StringEntity(body, ContentType.create(mimeType, charset));
                post.setEntity(entity);
            }
            if(headers != null && !headers.isEmpty())
            {
                Iterator it = headers.entrySet().iterator();
                while(it.hasNext())
                {
                    Map.Entry<String, String> entry = (Map.Entry)it.next();
                    post.addHeader((String)entry.getKey(), (String)entry.getValue());
                }
            }
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            customReqConf.setConnectTimeout(CONNECT_TIMEOUT);
            customReqConf.setSocketTimeout(socketTimeOut);
            post.setConfig(customReqConf.build());
            JSONObject result = this.commonRequest3(post, charset);
            obj = result;
        }
        finally
        {
            post.releaseConnection();
        }
        return obj;
    }
   
    public JSONObject post(String url, JenkinsBean jenkinsBean, String mimeType, String charset,
                           String logPrefix,String octopusApiGatewayToken)
            throws IOException, GeneralSecurityException
    {
        mimeType = mimeType == null ? "application/json" : mimeType;
        charset = charset == null ? "UTF-8" : charset;
        HttpPost post = new HttpPost(url);
        JSONObject obj = null;
        try
        {
            post.addHeader("Content-Type","application/json");
            String token = StringUtils.stripToNull(octopusApiGatewayToken);
            if(token!=null) {
                post.addHeader("x-auth-token", octopusApiGatewayToken);
            }
            Gson gson = new GsonBuilder().create();
            String jenkinsInfo = gson.toJson(jenkinsBean);
            
            HttpEntity entity = new StringEntity(jenkinsInfo,ContentType.create(mimeType,charset));
            post.setEntity(entity);
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            customReqConf.setConnectTimeout(CONNECT_TIMEOUT);
            customReqConf.setSocketTimeout(socketTimeOut);
            post.setConfig(customReqConf.build());
            String formatUrl = CommonUtils.replaceIp(url);
            logger.log(Level.INFO,"[build-notification] request.url:"+formatUrl+" jenkins:" + jenkinsBean.getUrl()+" . jenkins.version:"+jenkinsBean.getVersion()+" . labels: "+jenkinsBean.getLabels()+" . loadinfo:"+gson.toJson(jenkinsBean.getLoadInfo()));
            JSONObject result = this.commonRequest3(post, charset);
            obj = result;
        }
        finally
        {
            post.releaseConnection();
        }
        return obj;
    }

    public JSONObject get(String url, Map<String, String> params, Map<String, String> headers, String charset, String logPrefix)
            throws IOException, GeneralSecurityException
    {
        charset = (charset == null) ? "UTF-8" : charset;
        if ((params != null) && (!params.isEmpty()))
        {
            StringBuffer buf = new StringBuffer();
            for (Map.Entry entry : params.entrySet()) {
                buf.append((String)entry.getKey() + "=" + (String)entry.getValue() + "&");
            }
            url = url + "?" + buf.toString();
        }
        HttpGet get = new HttpGet(url);
        try {
            if ((headers != null) && (!headers.isEmpty())) {
                for (Map.Entry entry : headers.entrySet()) {
                    get.addHeader((String)entry.getKey(), (String)entry.getValue());
                }
            }
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            customReqConf.setConnectTimeout(CONNECT_TIMEOUT);
            customReqConf.setSocketTimeout(socketTimeOut);
            get.setConfig(customReqConf.build());
            JSONObject result = commonRequest3(get, charset);
            JSONObject localJSONObject1 = result;
            return localJSONObject1; 
        } finally { 
        	get.releaseConnection(); 
        }

    }
    
    public JSONObject post(String url, JenkinsResource jenkinsResource, String mimeType, String charset,
                           String logPrefix, String octopusApiGatewayToken)
            throws IOException, GeneralSecurityException
    {
        mimeType = mimeType == null ? "application/json" : mimeType;
        charset = charset == null ? "UTF-8" : charset;
        HttpPost post = new HttpPost(url);
        JSONObject obj = null;
        try
        {
            post.addHeader("Content-Type","application/json");
            String token = StringUtils.stripToNull(octopusApiGatewayToken);
            if(token!=null) {
                post.addHeader("x-auth-token", octopusApiGatewayToken);
            }
            Gson gson = new GsonBuilder().create();
            String jenkinsResources = gson.toJson(jenkinsResource);
            HttpEntity entity = new StringEntity(jenkinsResources, ContentType.create(mimeType,charset));
            post.setEntity(entity);
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            customReqConf.setConnectTimeout(CONNECT_TIMEOUT);
            customReqConf.setSocketTimeout(socketTimeOut);
            post.setConfig(customReqConf.build());
//            logger.info("[BuildNotification][" + logPrefix + "] request.url=" + url + " information=" + jenkinsResource);
            JSONObject result = this.commonRequest3(post, charset);
            obj = result;
        }
        finally
        {
            post.releaseConnection();
        }
        return obj;
    }

    public JSONObject commonRequest3(HttpRequestBase request, String charset)
            throws IOException, GeneralSecurityException
    {
        boolean isHttps = request.getURI().toString().startsWith("https");
        return commonRequest2(request, charset, Integer.valueOf(5000), Integer.valueOf(120000), isHttps);
    }

    public JSONObject commonRequest2(HttpRequestBase request, String charset, Integer connTimeout, Integer readTimeout, boolean isHttps)
            throws GeneralSecurityException, IOException
    {
        HttpClient client = null;
        try
        {
            if (!isHttps) {
                PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
                cm.setMaxTotal(128);
                cm.setDefaultMaxPerRoute(25);
                client = HttpClients.custom().setConnectionManager(cm).build();
            } else {
                client = createSSLInsecureClient();
            }

            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout.intValue());
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout.intValue());
            }
            request.setConfig(customReqConf.build());
            request.addHeader("Connection", "close");
            
            HttpResponse response = client.execute(request);
            JSONObject localJSONObject = parseResponse(response, request.getURI().toString(), charset);

            return localJSONObject;
        }
        finally
        {
            if ((client != null) && (client instanceof CloseableHttpClient))
                ((CloseableHttpClient)client).close();
        }
    }
    
    public HttpResponse opsTokenRequest3(HttpRequestBase request, String charset)
            throws IOException, GeneralSecurityException
    {
        boolean isHttps = request.getURI().toString().startsWith("https");
        return opsTokenRequest2(request, charset, Integer.valueOf(5000), Integer.valueOf(120000), isHttps);
    }
    
    public HttpResponse opsTokenRequest2(HttpRequestBase request, String charset, Integer connTimeout, Integer readTimeout, boolean isHttps)
            throws GeneralSecurityException, IOException
    {
        HttpClient client = null;
        try
        {
            if (!isHttps) {
                PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
                cm.setMaxTotal(128);
                cm.setDefaultMaxPerRoute(25);
                client = HttpClients.custom().setConnectionManager(cm).build();
            } else {
                client = createSSLInsecureClient();
            }

            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout.intValue());
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout.intValue());
            }
            request.setConfig(customReqConf.build());
            request.addHeader("Connection", "close");
            
            HttpResponse response = client.execute(request);

            return response;
        }
        finally
        {
            if ((client != null) && (client instanceof CloseableHttpClient))
                ((CloseableHttpClient)client).close();
        }
    }
   
    private JSONObject parseResponse(HttpResponse res, String url, String charset)
            throws IllegalStateException, IOException
    {
        JSONObject result = new JSONObject();

        charset = (charset == null) ? "UTF-8" : charset;
        url = (url == null) ? "unknow" : url;

        int statusCode = res.getStatusLine().getStatusCode();
        result.put("HttpCode", Integer.valueOf(statusCode));
        JSONObject jsonContent = null;
        HttpEntity entity = res.getEntity();
        if ((null != entity) && (null != entity.getContent())) {
            String content = StreamToString(entity);
            if ((content != null) && (content.startsWith("{"))) {
            	jsonContent = JSONObject.fromObject(content);
                result.put("Record", jsonContent);
            }else {
                result.put("Record", content);
            }
        }

        if ((statusCode < 200) || (statusCode >= 300)) {
            result.put("Severity", "Error");
            result.put("Message", res.getStatusLine().getReasonPhrase());
            logger.log(Level.WARNING,"[build-notification] "+statusCode+" request.url:"+url+"; content:"
                    + result.optString("Record") + ";charset:" + charset);
        } else {
            result.put("Severity", "Succeed");
            JSONObject obj = JSONObject.fromObject(jsonContent.get("result"));
            String resultcontent = obj.containsKey("loadInfo")?obj.getString("loadInfo"):"";
            logger.log(Level.INFO,"[build-notification] "+statusCode+"; error:"+jsonContent.getString("error")+"; content:"+resultcontent);
        }
        return result;
    }

    private String StreamToString(HttpEntity entity) throws IOException
    {
        InputStream instream = entity.getContent();
        try
        {
            ContentType contentType = ContentType.getOrDefault(entity);

            String charset = "UTF-8";
            Charset ct_charset = contentType.getCharset();
            if (ct_charset != null)
            {
                charset = ct_charset.name();
            }
            StringBuilder b = new StringBuilder();
            char[] tmp = new char[1024];
            Reader reader = new InputStreamReader(instream, charset);
            try
            {
                int l;
                while ((l = reader.read(tmp)) != -1)
                {
                    b.append(tmp, 0, l);
                }
            }
            catch (ConnectionClosedException e)
            {
                reader.close();
                instream.close();
                logger.log(Level.SEVERE,"[build-notification] ConnectionClosedException");
            } finally
            {
                reader.close();
            }

            return b.toString();
        }
        finally
        {
            instream.close();
        }

    }

    private static CloseableHttpClient createSSLInsecureClient()
            throws GeneralSecurityException
    {
        try
        {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
            {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException
                {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier()
            {
                public boolean verify(String arg0, SSLSession arg1)
                {
                    return true;
                }

                public void verify(String host, SSLSocket ssl)
                        throws IOException
                {
                }

                public void verify(String host, X509Certificate cert)
                        throws SSLException
                {
                }

                public void verify(String host, String[] cns, String[] subjectAlts)
                        throws SSLException
                {
                }
            });
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (GeneralSecurityException e) {
            throw e;
        }
    }
    
    public String loadIAMToken(String url, String requestStr, String pkiToken) {
  		URI uri = URI.create(url);
  		HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
  		AuthCache authCache = new BasicAuthCache();
  		BasicScheme basicAuth = new BasicScheme();
  		authCache.put(host, basicAuth);
  		HttpPost httpPost = new HttpPost(uri);
  		httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
  		httpPost.setHeader("Accept", "application/json;charset=UTF-8");
  		httpPost.setHeader(HEADER_DOMAIN, COMMON_DOMAIN_NAME);
  		if (StringUtils.isNotBlank(pkiToken)) {
  			httpPost.setHeader("X-Auth-Token", pkiToken);
  		}
  		ReqestClientUtil requestClient = new ReqestClientUtil();
  		HttpResponse response = null;
  		try {
  			StringEntity stringEntity = null;
  			stringEntity = new StringEntity(requestStr, HTTP.UTF_8);
  			httpPost.setEntity(stringEntity);
  			HttpClientContext localContext = HttpClientContext.create();
  			localContext.setAuthCache(authCache);
  			response = requestClient.opsTokenRequest3(httpPost, "UTF-8");
  			handleResponse(response);
  			Header tokenHeader = response.getFirstHeader("X-Subject-Token");
  			if (tokenHeader == null) {
  				throw new RuntimeException("load IAM token error,X-Subject-Token header is null.");
  			}
  			return tokenHeader.getValue();
  		} catch (Exception e) {
  			throw new RuntimeException("load IAM token error,url:" + url, e);
  		} finally {
  			httpPost.releaseConnection();
  		}
  	}
	
	private void handleResponse(HttpResponse response) throws IOException {
		int code = response.getStatusLine().getStatusCode();
//		String result = StreamToString(response.getEntity());
		if (code != 200 && code != 201) {
			throw new RuntimeException("HTTP ERROR, Code: " + code + "; Result: " +response.getEntity());
		}
	}
}
