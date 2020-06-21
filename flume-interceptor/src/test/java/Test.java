import com.flume.interceptor.LogETLInterceptor;
import com.flume.interceptor.LogTypeInterceptor;
import org.apache.flume.Event;
import org.apache.flume.event.JSONEvent;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @author annyu
 * @description
 * @date 2020/5/21
 **/
public class Test {
    public static void main(String[] args) {
        LogETLInterceptor logETLInterceptor = new LogETLInterceptor();
        JSONEvent event = new JSONEvent();

        String str="1590566108060|{\"cm\":{\"ln\":\"-43.0\",\"sv\":\"V2.6.8\",\"os\":\"8.2.0\",\"g\":\"8K80R0H4@gmail.com\",\"mid\":\"500\",\"nw\":\"WIFI\",\"l\":\"en\",\"vc\":\"4\",\"hw\":\"1080*1920\",\"ar\":\"BR\",\"uid\":\"998\",\"t\":\"1590488679635\",\"la\":\"-30.5\",\"md\":\"Huawei-0\",\"vn\":\"10.0\",\"ba\":\"Huawei\",\"sr\":\"J\"},\"ap\":\"app\",\"et\":[{\"ett\":\"1590519627728\",\"en\":\"ad\",\"kv\":{\"activityId\":\"1\",\"displayMills\":\"76331\",\"entry\":\"3\",\"action\":\"5\",\"contentType\":\"0\"}},{\"ett\":\"1590485371104\",\"en\":\"notification\",\"kv\":{\"apTime\":\"1590497593816\",\"action\":\"2\",\"type\":\"2\",\"content\":\"\"}},{\"ett\":\"1590489449794\",\"en\":\"favorites\",\"kv\":{\"addTime\":\"1590550428975\",\"id\":0,\"courseId\":9,\"userId\":0}},{\"ett\":\"1590495673919\",\"en\":\"praise\",\"kv\":{\"addTime\":\"1590560819604\",\"targetId\":2,\"id\":1,\"type\":4,\"userId\":3}}]}";
        event.setBody(str.getBytes());
        event.setHeaders(new HashMap<>());
        logETLInterceptor.intercept(event);
        LogTypeInterceptor logTypeInterceptor = new LogTypeInterceptor();
        logTypeInterceptor.intercept(event);
    }
}
