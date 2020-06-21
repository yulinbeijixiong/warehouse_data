package com.hive.function;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author annyu
 * @description hive udf 函数
 * @date 2020/5/29
 **/
public class BaseFieldUDF extends UDF {
    public static String evaluate(String line,String key)throws JSONException {
        String[] log = line.split("\\|");
        if(log.length!=2||StringUtils.isBlank(log[1])){
            return "";
        }
        JSONObject baseJson = new JSONObject(log[1].trim());

        String result="";
        //获取服务器时间
        if("st".equals(key)){
            return log[0].trim();
        }else if("et".equals(key)){
            //获取事件数
            if(baseJson.has("et")){
               result = baseJson.getString("et");
            }
        }else {
            JSONObject cm = baseJson.getJSONObject("cm");
            if(cm.has(key)){
                result=cm.getString(key);
            }
        }

        return result;

    }

    public static void main(String[] args) {
        String line ="1590494560914|{\"cm\":{\"ln\":\"-86.0\",\"sv\":\"V2.9.0\",\"os\":\"8.0.4\",\"g\":\"" +
                "8T4V24N5@gmail.com\",\"mid\":\"496\",\"nw\":\"WIFI\",\"l\":\"pt\",\"vc\":\"11\",\"hw\":\"1080*1920" +
                "\",\"ar\":\"MX\",\"uid\":\"999\",\"t\":\"1590419452882\",\"la\":\"-46.4\",\"md\":\"apple-0\",\"vn\":\"" +
                "13.6\",\"ba\":\"Apple\",\"sr\":\"Q\"},\"ap\":\"app\",\"et\":[{\"ett\":\"1590409324688\",\"en\":\"" +
                "newsdetail\",\"kv\":{\"entry\":\"1\",\"loadingTime\":\"4\",\"newsStayTime\":\"45\",\"goodsId\":\"250\"" +
                ",\"action\":\"4\",\"showType\":\"5\",\"category\":\"55\",\"type1\":\"201\",\"goodsSource\":\"1\"}},{\"ett\"" +
                ":\"1590462491286\",\"en\":\"active_background\",\"kv\":{\"activeSource\":\"3\"}},{\"ett\":\"1590444854147\"" +
                ",\"en\":\"favorites\",\"kv\":{\"addTime\":\"1590483522180\",\"id\":0,\"courseId\":5,\"userId\":7}}]}";
        String mid = evaluate(line, "mid");
        System.out.println(mid);
    }
}
