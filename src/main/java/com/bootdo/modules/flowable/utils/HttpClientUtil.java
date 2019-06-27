package com.bootdo.modules.flowable.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用httpclient调用封装
 */
public class HttpClientUtil {

    public static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    private static final int CONN_TIMEOUT_MINIS = 5000;// 默认值,毫秒数
    private static final int READ_TIMEOUT_MINIS = 5000;// 默认值,毫秒数

    public static void main(String[] args) {
//		String skuIds = "6e1e9eb003b242939a3ab97419e64a13";
//		String doGetRequest = HttpClientUtil.doSimplePost("https://peanut.huimin888.cn/api/product/getDetailBySkuId/", new HashMap<String, String>(){{put("skuIds",skuIds);}});
//		System.out.println("-----------------------------------"+doGetRequest);
//
//		if(StringUtils.isNotEmpty(doGetRequest)) {
//			JSONObject jsonObject = JSONObject.fromString(doGetRequest);
//			if(null != jsonObject && !"0".equals(jsonObject.get("result"))) {
//				Object obj  = (JSONArray) jsonObject.get("data");
//				if( obj instanceof JSONArray) {
//					List<JSONObject>  jsonArray =  (JSONArray) obj;
//
//					for (JSONObject pro : jsonArray) {
//						System.err.println("--------------------------------");
//						System.err.println(((JSONObject)pro.get("product")).get("name"));
//						System.err.println(((JSONObject)pro.get("proDetail")).get("propertyIds"));
////						System.err.println(pro.get("proDetail"));
//					}
//				}
//			}
//		}

        Map params = new HashMap();
        params.put("id", "1");
        String str = doPost("https://dakou.huimin888.cn/api/shop/getShopById", params);
        System.out.println(str);
    }

    /**
     * 发送http GET请求，并返回http响应字符串
     *
     * @param urlstr 完整的请求url字符串
     * @return
     */
    public static String doGetRequest(String urlstr, String monitorName) {
        return doGetRequestWithCookie(urlstr, "ddddd", CONN_TIMEOUT_MINIS, READ_TIMEOUT_MINIS, "UTF-8");
    }

    public static String doGetRequestWithCookie(String urlstr, String cookieStr, String monitorName) {
        return doGetRequestWithCookie(urlstr, cookieStr, CONN_TIMEOUT_MINIS, READ_TIMEOUT_MINIS, "UTF-8");
    }


    public static String doGetRequestWithCookie(String urlstr, String cookieStr, int connTimeoutMinis,
                                                int readTimeoutMinis, String encoding) {
        long startTime = System.currentTimeMillis();
        String response = "";
        HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
        client.getHttpConnectionManager().getParams().setConnectionTimeout(connTimeoutMinis);
        client.getHttpConnectionManager().getParams().setSoTimeout(readTimeoutMinis);
        client.getParams().setIntParameter("http.connection.timeout", connTimeoutMinis);
        client.getParams().setIntParameter("http.socket.timeout", readTimeoutMinis);
        HttpMethod httpmethod = new GetMethod(urlstr);
        httpmethod.setFollowRedirects(true);
        httpmethod.setRequestHeader("Accept-Language", "zh-cn");
        httpmethod.setRequestHeader("User-Agent",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN)");
        httpmethod.setRequestHeader("Connection", "Keep-Alive");
        if (!StringUtils.isBlank(cookieStr)) {
            httpmethod.setRequestHeader("cookie", cookieStr);
        }
        try {
            int statusCode = client.executeMethod(httpmethod);
            if (statusCode == HttpStatus.SC_OK) {
                // read
                // res = httpmethod.getResponseBodyAsString();
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(bao);
                BufferedInputStream bis = null;
                bis = new BufferedInputStream(httpmethod.getResponseBodyAsStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }

                if (bis != null)
                    bis.close();
                if (bos != null) {
                    bos.flush();
                    response = bao.toString(encoding);
                }
            } else {
            }
        } catch (HttpException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            response = "";
        } catch (IOException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            response = "";
        } finally {
            httpmethod.releaseConnection();
        }
        return response;
    }


    public static String doGetRequestWithCookie(String urlstr, String cookieStr, int connTimeoutMinis,
                                                int readTimeoutMinis, String encoding, String monitorName, Map<String, String> headers) {
        long startTime = System.currentTimeMillis();
        String response = "";
        HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
        client.getHttpConnectionManager().getParams().setConnectionTimeout(connTimeoutMinis);
        client.getHttpConnectionManager().getParams().setSoTimeout(readTimeoutMinis);
        client.getParams().setIntParameter("http.connection.timeout", connTimeoutMinis);
        client.getParams().setIntParameter("http.socket.timeout", readTimeoutMinis);

        HttpMethod httpmethod = new GetMethod(urlstr);
        httpmethod.setFollowRedirects(true);
        httpmethod.setRequestHeader("Accept-Language", "zh-cn");
        httpmethod.setRequestHeader("User-Agent",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN)");
        httpmethod.setRequestHeader("Connection", "Keep-Alive");
        if (!StringUtils.isBlank(cookieStr)) {
            httpmethod.setRequestHeader("cookie", cookieStr);
        }
        try {
            if (null != headers && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    String value = entry.getValue();
                    if (StringUtils.isBlank(value)) {
                        continue;
                    }
                    httpmethod.addRequestHeader(entry.getKey(), value);
                }
            }

            int statusCode = client.executeMethod(httpmethod);
            if (statusCode == HttpStatus.SC_OK) {
                // read
                // res = httpmethod.getResponseBodyAsString();
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(bao);
                BufferedInputStream bis = null;
                bis = new BufferedInputStream(httpmethod.getResponseBodyAsStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }

                if (bis != null)
                    bis.close();
                if (bos != null) {
                    bos.flush();
                    response = bao.toString(encoding);
                }
            } else {
            }
        } catch (HttpException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            response = "";
        } catch (IOException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            response = "";
        } finally {
            httpmethod.releaseConnection();
        }
        return response;
    }

    /**
     * 发送完整的HTTP POST请求,用于支持请求里面含有多个个参数key相同的情况
     *
     * @param urlstr
     * @param params
     * @param cookieStr
     * @param connTimeoutMinis
     * @param readTimeoutMinis
     * @param encoding
     * @param monitorName
     * @return
     */
    public static String doCompletePostRequest(String urlstr, Map<String, String[]> params, String cookieStr,
                                               int connTimeoutMinis, int readTimeoutMinis, String encoding, String monitorName) {
        long startTime = System.currentTimeMillis();
        String response = null;
        HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
        client.getHttpConnectionManager().getParams().setConnectionTimeout(connTimeoutMinis);
        client.getHttpConnectionManager().getParams().setSoTimeout(readTimeoutMinis);
        client.getParams().setIntParameter("http.connection.timeout", connTimeoutMinis);
        client.getParams().setIntParameter("http.socket.timeout", readTimeoutMinis);

        PostMethod httpmethod = new PostMethod(urlstr);
        httpmethod.setFollowRedirects(false);
        httpmethod.setRequestHeader("Accept-Language", "zh-cn");
        httpmethod.setRequestHeader("User-Agent",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN)");
        httpmethod.setRequestHeader("Connection", "Keep-Alive");
        if (!StringUtils.isBlank(cookieStr)) {
            httpmethod.setRequestHeader("cookie", cookieStr);
        }
        try {
            if (null != params && !params.isEmpty()) {
                for (Map.Entry<String, String[]> entry : params.entrySet()) {
                    String[] values = entry.getValue();
                    if (values == null || values.length == 0) {
                        continue;
                    }
                    for (String value : values) {
                        httpmethod.addParameter(entry.getKey(), value);
                    }
                }
            }
            int statusCode = client.executeMethod(httpmethod);
            if (statusCode == HttpStatus.SC_OK) {
                // read
                // res = httpmethod.getResponseBodyAsString();
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(bao);
                BufferedInputStream bis = null;
                bis = new BufferedInputStream(httpmethod.getResponseBodyAsStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                if (bis != null)
                    bis.close();
                if (bos != null) {
                    bos.flush();
                    response = bao.toString(encoding);
                }
            } else {
            }
        } catch (HttpException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            response = "";
        } catch (IOException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            response = "";
        } finally {
            httpmethod.releaseConnection();
        }
        return response;
    }


    /**
     * 发送http POST请求，并返回http响应字符串
     * 适用于明确没有多个参数key相同的情况,提供这样的方法,是便于组合请求参数
     *
     * @param urlstr 完整的请求url字符串
     * @return
     */
    public static String doSimplePostRequest(String urlstr, Map<String, String> params, String cookieStr,
                                             int connTimeoutMinis, int readTimeoutMinis, String encoding, String monitorName) {
        long startTime = System.currentTimeMillis();
        log.warn("doSimplePostRequest.param url:{};params: {}", urlstr, params.toString());
        String response = null;
        HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
        client.getHttpConnectionManager().getParams().setConnectionTimeout(connTimeoutMinis);
        client.getHttpConnectionManager().getParams().setSoTimeout(readTimeoutMinis);
        client.getParams().setIntParameter("http.connection.timeout", connTimeoutMinis);
        client.getParams().setIntParameter("http.socket.timeout", readTimeoutMinis);

        PostMethod httpmethod = new PostMethod(urlstr);
        httpmethod.setFollowRedirects(false);
        httpmethod.setRequestHeader("Accept-Language", "zh-cn");
        httpmethod.setRequestHeader("User-Agent",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN)");
        httpmethod.setRequestHeader("Connection", "Keep-Alive");
        httpmethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        if (!StringUtils.isBlank(cookieStr)) {
            httpmethod.setRequestHeader("cookie", cookieStr);
        }
        try {
            if (null != params && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    httpmethod.addParameter(entry.getKey(), entry.getValue());
                }
            }
            int statusCode = client.executeMethod(httpmethod);
            if (statusCode == HttpStatus.SC_OK) {
                // read
                // res = httpmethod.getResponseBodyAsString();
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(bao);
                BufferedInputStream bis = null;
                bis = new BufferedInputStream(httpmethod.getResponseBodyAsStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                if (bis != null)
                    bis.close();
                if (bos != null) {
                    bos.flush();
                    response = bao.toString(encoding);
                }
            } else {
            }
        } catch (HttpException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            response = "";
        } catch (IOException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            response = "";
        } finally {
            httpmethod.releaseConnection();
        }
        log.warn("doSimplePostRequest.param url:{};params: {},response: {}", urlstr, params.toString(), response);
        return response;
    }

    public static String doSimplePostJsonRequest(String urlstr, Map<String, String> params, String cookieStr,
                                             int connTimeoutMinis, int readTimeoutMinis, String encoding, String monitorName) {
        long startTime = System.currentTimeMillis();
        log.warn("doSimplePostRequest.param url:{};params: {}", urlstr, params.toString());
        String response = null;
        HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
        client.getHttpConnectionManager().getParams().setConnectionTimeout(connTimeoutMinis);
        client.getHttpConnectionManager().getParams().setSoTimeout(readTimeoutMinis);
        client.getParams().setIntParameter("http.connection.timeout", connTimeoutMinis);
        client.getParams().setIntParameter("http.socket.timeout", readTimeoutMinis);

        PostMethod httpmethod = new PostMethod(urlstr);
        httpmethod.setFollowRedirects(false);
        httpmethod.setRequestHeader("Accept-Language", "zh-cn");
        httpmethod.setRequestHeader("User-Agent",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN)");
        httpmethod.setRequestHeader("Connection", "Keep-Alive");
        httpmethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        if (!StringUtils.isBlank(cookieStr)) {
            httpmethod.setRequestHeader("cookie", cookieStr);
        }
        try {
            if (null != params && !params.isEmpty()) {
                RequestEntity entity = new StringRequestEntity(JSONObject.toJSONString(params),"application/json","UTF-8");
                httpmethod.setRequestEntity(entity);
                httpmethod.setRequestHeader("Content-Type","application/json;charset=UTF-8");

            }
            int statusCode = client.executeMethod(httpmethod);
            if (statusCode == HttpStatus.SC_OK) {
                // read
                // res = httpmethod.getResponseBodyAsString();
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(bao);
                BufferedInputStream bis = null;
                bis = new BufferedInputStream(httpmethod.getResponseBodyAsStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                if (bis != null)
                    bis.close();
                if (bos != null) {
                    bos.flush();
                    response = bao.toString(encoding);
                }
            } else {
            }
        } catch (HttpException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            response = "";
        } catch (IOException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            response = "";
        } finally {
            httpmethod.releaseConnection();
        }
        log.warn("doSimplePostRequest.param url:{};params: {},response: {}", urlstr, params.toString(), response);
        return response;
    }


    public static String doSimplePost(String urlstr, Map<String, String> params) {
        return doSimplePostRequest(urlstr, params, null, 500000, 500000, "utf-8", null);
    }

    public static String doSimplePostJson(String urlstr, Map<String, String> params) {
        return doSimplePostJsonRequest(urlstr, params, null, 500000, 500000, "utf-8", null);
    }



    public static String doPostRequestBaoShuiQu(String urlstr, Map<String, String> params, String cookieStr,
                                                int connTimeoutMinis, int readTimeoutMinis, String encoding, String monitorName) {
        long startTime = System.currentTimeMillis();
        String response = null;
        HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
        client.getHttpConnectionManager().getParams().setConnectionTimeout(connTimeoutMinis);
        client.getHttpConnectionManager().getParams().setSoTimeout(readTimeoutMinis);
        client.getParams().setIntParameter("http.connection.timeout", connTimeoutMinis);
        client.getParams().setIntParameter("http.socket.timeout", readTimeoutMinis);

        PostMethod httpmethod = new PostMethod(urlstr);
        httpmethod.setFollowRedirects(false);
        httpmethod.setRequestHeader("Accept-Encoding", "application/x-www-form-urlencoded");
        httpmethod.setRequestHeader("Accept-Language", "zh-cn");
        httpmethod.setRequestHeader("User-Agent",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN)");
        httpmethod.setRequestHeader("Connection", "Keep-Alive");
        httpmethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        if (!StringUtils.isBlank(cookieStr)) {
            httpmethod.setRequestHeader("cookie", cookieStr);
        }
        try {
            if (null != params && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    httpmethod.addParameter(entry.getKey(), entry.getValue());
                }
            }
            int statusCode = client.executeMethod(httpmethod);
            if (statusCode == HttpStatus.SC_OK) {
                // read
                // res = httpmethod.getResponseBodyAsString();
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(bao);
                BufferedInputStream bis = null;
                bis = new BufferedInputStream(httpmethod.getResponseBodyAsStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                if (bis != null)
                    bis.close();
                if (bos != null) {
                    bos.flush();
                    response = bao.toString(encoding);
                }
            } else {
            }
        } catch (HttpException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            response = "";
        } catch (IOException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            response = "";
        } finally {
            httpmethod.releaseConnection();
        }
        return response;
    }


    public static String doSimplePostRequestHeaders(String urlstr, Map<String, String> params, String cookieStr,
                                                    int connTimeoutMinis, int readTimeoutMinis, String encoding, String monitorName, Map<String, String> headers) {
        long startTime = System.currentTimeMillis();
        String response = null;
        HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
        client.getHttpConnectionManager().getParams().setConnectionTimeout(connTimeoutMinis);
        client.getHttpConnectionManager().getParams().setSoTimeout(readTimeoutMinis);
        client.getParams().setIntParameter("http.connection.timeout", connTimeoutMinis);
        client.getParams().setIntParameter("http.socket.timeout", readTimeoutMinis);

        PostMethod httpmethod = new PostMethod(urlstr);
        httpmethod.setFollowRedirects(false);
        httpmethod.setRequestHeader("Accept-Language", "zh-cn");
        httpmethod.setRequestHeader("User-Agent",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN)");
        httpmethod.setRequestHeader("Connection", "Keep-Alive");
        httpmethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        if (!StringUtils.isBlank(cookieStr)) {
            httpmethod.setRequestHeader("cookie", cookieStr);
        }
        try {
            if (null != params && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    httpmethod.addParameter(entry.getKey(), entry.getValue());
                }
            }
            if (null != headers && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    String value = entry.getValue();
                    if (StringUtils.isBlank(value)) {
                        continue;
                    }
                    httpmethod.addRequestHeader(entry.getKey(), value);
                }
            }

            int statusCode = client.executeMethod(httpmethod);
            if (statusCode == HttpStatus.SC_OK) {
            } else {
            }
            // read
            // res = httpmethod.getResponseBodyAsString();
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(bao);
            BufferedInputStream bis = null;
            bis = new BufferedInputStream(httpmethod.getResponseBodyAsStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            if (bis != null)
                bis.close();
            if (bos != null) {
                bos.flush();
                response = bao.toString(encoding);
            }
        } catch (HttpException e) {
            log.error("get httpPost fail: url=" + urlstr, e);
            response = "";
        } catch (IOException e) {
            log.error("get httpPost fail: url=" + urlstr, e);
            response = "";
        } finally {
            httpmethod.releaseConnection();
        }
        return response;
    }

    public static String doSimplePutRequestHeaders(String urlstr, Map<String, String> params, String cookieStr,
                                                   int connTimeoutMinis, int readTimeoutMinis, String encoding, String monitorName, Map<String, String> headers) {
        long startTime = System.currentTimeMillis();
        String response = null;
        HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
        client.getHttpConnectionManager().getParams().setConnectionTimeout(connTimeoutMinis);
        client.getHttpConnectionManager().getParams().setSoTimeout(readTimeoutMinis);
        client.getParams().setIntParameter("http.connection.timeout", connTimeoutMinis);
        client.getParams().setIntParameter("http.socket.timeout", readTimeoutMinis);

        PutMethod httpmethod = new PutMethod(urlstr);
        httpmethod.setFollowRedirects(false);
        httpmethod.setRequestHeader("Accept-Language", "zh-cn");
        httpmethod.setRequestHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN)");
        httpmethod.setRequestHeader("Connection", "Keep-Alive");
        httpmethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        if (!StringUtils.isBlank(cookieStr)) {
            httpmethod.setRequestHeader("cookie", cookieStr);
        }
        try {
            NameValuePair[] pairs = new NameValuePair[params.size()];
            if (null != params && !params.isEmpty()) {
                int i = 0;
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    pairs[i++] = new NameValuePair(entry.getKey(), entry.getValue());
                }
            }
            httpmethod.setQueryString(pairs);
            if (null != headers && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    String value = entry.getValue();
                    if (StringUtils.isBlank(value)) {
                        continue;
                    }
                    httpmethod.addRequestHeader(entry.getKey(), value);
                }
            }

            int statusCode = client.executeMethod(httpmethod);
            if (statusCode == HttpStatus.SC_OK) {
            } else {
            }
            // res = httpmethod.getResponseBodyAsString();
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(bao);
            BufferedInputStream bis = null;
            bis = new BufferedInputStream(httpmethod.getResponseBodyAsStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            if (bis != null)
                bis.close();
            if (bos != null) {
                bos.flush();
                response = bao.toString(encoding);
            }
        } catch (HttpException e) {
            log.error("get httpPut fail: url=" + urlstr, e);
            response = "";
        } catch (IOException e) {
            log.error("get httpPut fail: url=" + urlstr, e);
            response = "";
        } finally {
            httpmethod.releaseConnection();
        }
        return response;
    }


    public static String doPostBodyData(String urlstr, String bodyData) {
        String encoding = "utf-8";
        String cookieStr = "";
        CloseableHttpResponse response = null;
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONN_TIMEOUT_MINIS)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000).build();

        HttpPost httpPost = new HttpPost(urlstr);

        httpPost.setConfig(requestConfig);

        try {
            StringEntity stringEntity = new StringEntity(bodyData, Consts.UTF_8);
            httpPost.setEntity(stringEntity);
            response = client.execute(httpPost);

            HttpEntity entity = response.getEntity();
            responseText = EntityUtils.toString(entity, encoding);
        } catch (HttpException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            responseText = "";
        } catch (IOException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            responseText = "";
        } finally {
            httpPost.completed();
            try {
                client.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return responseText;
    }

    public static String doPost(String urlstr, Map<String, String> params) {
        String encoding = "utf-8";
        String cookieStr = "";
        CloseableHttpResponse response = null;
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";

        HttpPost httpPost = new HttpPost(urlstr);

        try {

            List list = new ArrayList();
            if (null != params && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            response = client.execute(httpPost);

            HttpEntity entity = response.getEntity();
            responseText = EntityUtils.toString(entity, encoding);
        } catch (HttpException e) {
            log.error("get httpPost fail: url=" + urlstr, e);
            responseText = "";
        } catch (IOException e) {
            log.error("get httpPost fail: url=" + urlstr, e);
            responseText = "";
        } finally {
            httpPost.completed();
            try {
                client.close();
            } catch (IOException e) {
                log.error("client close error:" + e.getMessage(), e);
            }
        }
        return responseText;
    }

    public static String doGet(String urlstr) {
        String encoding = "utf-8";
        String cookieStr = "";
        CloseableHttpResponse response = null;
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";

        HttpGet httpGet = new HttpGet(urlstr);

        try {
            response = client.execute(httpGet);

            HttpEntity entity = response.getEntity();
            responseText = EntityUtils.toString(entity, encoding);
        } catch (HttpException e) {
//            log.error("get httpGet fail: url=" + urlstr, e);
            responseText = "";
        } catch (IOException e) {
            log.error("get httpGet fail: url=" + urlstr, e);
            responseText = "";
        } finally {
            httpGet.completed();
            try {
                client.close();
            } catch (IOException e) {
                log.error("client close error:" + e.getMessage(), e);
            }
        }
        return responseText;
    }

    public static InputStream downloadImage(String url) {

        CloseableHttpResponse response = null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();

            InputStream inputStream = entity.getContent();

            return inputStream;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
