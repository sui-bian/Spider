package us.codecraft.webmagic.main;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Created by simonliu on 2014/6/12.
 */
public class TestGuavaFuture {
    Spider spider = Spider.create(new PageProcessor() {
        @Override
        public void process(Page page) {

        }

        @Override
        public Site getSite() {
            return null;
        }
    });

}
