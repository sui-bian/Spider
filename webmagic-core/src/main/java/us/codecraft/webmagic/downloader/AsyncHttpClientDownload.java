package us.codecraft.webmagic.downloader;

import com.google.common.collect.Sets;
import com.google.common.util.concurrent.*;
import com.ning.http.client.*;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.cookie.Cookie;
import com.ning.http.client.providers.netty.NettyResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.utils.UrlUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by simonliu on 2014/6/14.
 */
public class AsyncHttpClientDownload extends AbstractDownloader{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Random random = new Random();
    AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();
    final AsyncHttpClient asyncHttpClient;

    public AsyncHttpClientDownload() {
        builder.setMaximumConnectionsTotal(100);
        this.asyncHttpClient  = new AsyncHttpClient(builder.build());
    }

    @Override
    public ListenableFuture<Response> AsynGetDownload(Request request,Task task) {
        ListenableFuture<Response> future = null;

        Site site = null;
        if (task != null) {
            site = task.getSite();
        }
        Set<Integer> acceptStatCode;
        String charset = null;
        Map<String, String> headers = null;
        Map<String,String> cookies = null;
        if (site != null) {
            acceptStatCode = site.getAcceptStatCode();
            charset = site.getCharset();
            headers = site.getHeaders();
            cookies = site.getCookies();
        } else {
            acceptStatCode = Sets.newHashSet(200);
        }
        logger.info("downloading page {}", request.getUrl());
        CloseableHttpResponse httpResponse = null;
        try {
            AsyncHttpClient.BoundRequestBuilder builder1 = asyncHttpClient.prepareGet(request.getUrl());
            if(proxys!=null&&proxys.size()>0){
                ProxyServer ps = proxys.get(random.nextInt(proxys.size()));
                logger.info("ProxyServer host is {},port is {}",ps.getHost(),ps.getPort());
                builder1.setProxyServer(ps);
            }
            for(Map.Entry<String, String> entry:cookies.entrySet()){
                //builder1.addCookie(new Cookie());
            }

            for(Map.Entry<String, String> entry:headers.entrySet()){
                builder1.addHeader(entry.getKey(),entry.getValue());
            }
            future  = builder1.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return future;
        }
    }




    @Override
    public Page download(Request request, Task task) {
        return null;
    }

    @Override
    public void setThread(int threadNum) {

    }


}


