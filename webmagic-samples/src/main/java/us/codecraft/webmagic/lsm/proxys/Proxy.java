package us.codecraft.webmagic.lsm.proxys;

import com.ning.http.client.ProxyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simonliu on 2014/10/21.
 */
public class Proxy {

    protected static  Logger logger = LoggerFactory.getLogger("root");

    static List<ProxyServer> proxys = new ArrayList<ProxyServer>();
    static InputStream input = null;
    public static void init(){
        input = Proxy.class.getClassLoader().getResourceAsStream("proxy.properties");
        BufferedReader bufRead = new BufferedReader(new InputStreamReader(input));

        String line = "";

        logger.info("action");
        try{
            while ((line = bufRead.readLine()) != null) {
                line = line.trim();
                String[] s = line.split("\\s+");
                proxys.add(new ProxyServer(s[0],Integer.parseInt(s[1])));
            }
        }catch(Exception e){
            logger.error(e.getMessage(),e);
        }

        logger.info("size is {}",proxys.size());

    }

    public static void main(String[] agrs){
        Proxy.init();
    }

    public static List<ProxyServer> addProxyServers(){
        return proxys;
    }
}
