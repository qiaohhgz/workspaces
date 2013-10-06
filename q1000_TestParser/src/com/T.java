 //HtmlParser代码接口变化比较多，因此写一个最新的。废话不多说，贴代码共大家享用！

/*
 * Main.java
 *
 * Created on 2007年1月19日, 上午9:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;

 

/**
 *
 * @author xcz
 */
public class T {
    
    
    /** Creates a new instance of Main */
    public T() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception  {
    	
    	System.getProperties().put("http.proxySet", "true");
		System.getProperties().put("http.proxyHost", "172.20.230.5");
		System.getProperties().put("http.proxyPort", "3128");
		System.getProperties().put("https.proxySet", "true");
		System.getProperties().put("https.proxyHost", "172.20.230.5");
		System.getProperties().put("https.proxyPort", "3128");
        // Create an instance of HttpClient.
        HttpClient client = new HttpClient();
        
        // Create a method instance.
        PostMethod post_method = new PostMethod("https://passport.115.com/");
        
        NameValuePair[] data = {
            new NameValuePair("ac", "login"),
            new NameValuePair("login[account]", "qsj695546958@163.com"),
            new NameValuePair("login[passwd]", "q7837793"),
        };
        
        post_method.setRequestBody(data);
        
        try {
            // Execute the method.
            int statusCode = client.executeMethod(post_method);
            
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + post_method.getStatusLine());
            }
            
            // Read the response body.
            //byte[] responseBody = post_method.getResponseBody();
            
            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            //System.out.println(new String(responseBody));
            
        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            post_method.releaseConnection();
        }
        
        byte[] responseBody = null;
        
        GetMethod get_method = new GetMethod("http://www.115.com");
        
        // Provide custom retry handler is necessary
        get_method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        
        try {
            // Execute the method.
            int statusCode = client.executeMethod(get_method);
            
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + get_method.getStatusLine());
            }
            
            // Read the response body.
            //responseBody = get_method.getResponseBody();

            //这里用流来读页面
            
            InputStream in = get_method.getResponseBodyAsStream();
            if (in != null) {
                byte[] tmp = new byte[4096];
                int bytesRead = 0;
                ByteArrayOutputStream buffer = new ByteArrayOutputStream(1024);
                while ((bytesRead = in.read(tmp)) != -1) {
                    buffer.write(tmp, 0, bytesRead);
                }
                responseBody = buffer.toByteArray();
            }
            
            
            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            //System.out.println(new String(responseBody));
            
        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            get_method.releaseConnection();
        }
        
        Parser parser;
        
        parser = Parser.createParser(new String(responseBody, "UTF-8"), "UTF-8");
        
        
        String filterStr = "table";
        NodeFilter filter = new TagNameFilter(filterStr);
        
        NodeList tables = parser.extractAllNodesThatMatch(filter);
        
        //System.out.println(tables.elementAt(17).toString());

       //找到单位列表所在的表格
        
        TableTag tabletag = (TableTag) tables.elementAt(17);
        
        TableRow row = tabletag.getRow(3);
        
        TableColumn[] cols = row.getColumns();
        //System.out.println("单位名称：" + cols[2].toHtml());
        System.out.println("单位名称：" + cols[2].childAt(0).getText());
        
    }
    
}

