package us.codecraft.webmagic.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.ning.http.client.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
//import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.ning.http.client.providers.netty.NettyResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.providers.netty.NettyResponse;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("unused")
public class AsyncHttpClientTest {

    public static int runCount = 10000;
    public static ExecutorService service;
    public static AsyncHttpClient asyncHttpClient = null;
    public static AsyncHttpClientConfig.Builder builder;
    public static HttpClient httpClient;
    public static void init(){
        service = Executors.newFixedThreadPool(3);
        builder = new AsyncHttpClientConfig.Builder();
        builder.setMaximumConnectionsTotal(10000);
        asyncHttpClient = new AsyncHttpClient(builder.build());
        httpClient = new DefaultHttpClient();

    }

    public static void main(String[] args) {
        init();
        long start1 = System.currentTimeMillis();
        System.out.println("当前开始时间"+start1);
        AsyncHttpClientTest1();
        long end1 = System.currentTimeMillis();
        System.out.println("当前结束时间"+end1);
        System.out.println("使用AsyncHttpClient 共用时间　" + (end1 - start1) + "ms");
        // --------------httpClient-------------------------
        for(int i=0;i<9999999;i++){}
        long start2 = System.currentTimeMillis();
        System.out.println("当前httpkais时间"+start2);
        HttpCientTest();
        long end2 = System.currentTimeMillis();
        System.out.println("当前httpjieshu时间"+end2);
        System.out.println("使用HttpClient 共用时间　" + (end2 - start2) + "ms");
    }

    public static void AsyncHttpClientTest1() {

        try {
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread()+"AsyncHttpClient开始调用"+System.currentTimeMillis());
                String url = "http://www.baidu.com";

                AsyncHttpClient.BoundRequestBuilder builder1 = asyncHttpClient.prepareGet(url);
                builder1.addHeader("Host","www.baidu.com");
                //builder1.addHeader("Cookie","CNZZDATA950900=cnzz_eid%3D121532405-1412608782-%26ntime%3D1412671135; 227c9_lastfid=2; 227c9_ck_info=%2F%09; 227c9_winduser=BQhXAwE6BVUGBlxRV1EOBVBRUAYFClNRUQFcA1BRVgILU1BUUAg%3D; 227c9_groupid=8; 227c9_lastvisit=0%091412671611%09%2Fthread0806.php%3Ffid%3D2%26search%3D%26page%3D101");
                builder1.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.2; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0");

                final ListenableFuture<Response> future = builder1.execute();
                //service.execute(command);
                future.addListener(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            System.out.println(Thread.currentThread() + "回调函数开始"  + System.currentTimeMillis());
                            //System.out.println("响应是 "+future.get().getResponseBody());
                        }  catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }, service);
                System.out.println("AsyncHttpClient正在访问"+System.currentTimeMillis());
                //Response response = future.get();
                //System.out.println("AsyncHttpClient访问结束"+System.currentTimeMillis());
                //System.out.println("AsyncHttpClient访问结束，内容"+response.getResponseBody());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("AsyncHttpClient访问成功"+System.currentTimeMillis());
    }

    public static void HttpCientTest() {
        try {


                HttpGet httpGet = new HttpGet("http://www.baidu.com");
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity entity = httpResponse.getEntity();
                //EntityUtils.consume(httpResponse.getEntity());
                System.out.println("HttpClient访问成功");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}