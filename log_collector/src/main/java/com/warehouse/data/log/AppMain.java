package com.warehouse.data.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author annyu
 * @description 生成日志的主类
 * @date 2020/5/13
 **/
public class AppMain {
    private static final Logger logger = LoggerFactory.getLogger(AppMain.class);
    private static Random random = new Random();
    /**
     * 设备id
     */
    private static int sMid = 0;
    /**
     * 用户id
     */
    private static int sUid = 0;
    /**
     * 商品id
     */
    private static int sGoodSid = 0;

    public static void main(String[] args) {
        //控制延迟时间
        long delay = args.length > 0 ? Long.parseLong(args[0]) : 0L;
        long loopLen = args.length > 1 ? Integer.parseInt(args[1]) : 1000L;
        generateLog(delay, loopLen);
    }

    /**
     * 生成日志
     *
     * @param delay
     * @param loopLen
     */
    public static void generateLog(Long delay, long loopLen) {
        for (int i = 0; i < loopLen; i++) {
            int flag = random.nextInt(2);
            long now;
            switch (flag) {

                case 0:
                    AppStart appStart = generateStart();
                    String jsonString = JSON.toJSONString(appStart);
                    logger.info(jsonString);
                    break;
                case 1:
                    JSONObject json = new JSONObject();
                    json.put("ap", "app");
                    json.put("cm", generateComFields());
                    //事件日志
                    JSONArray eventArray = new JSONArray();
                    //商品点击
                    if (random.nextBoolean()) {
                        eventArray.add(generateGoodsClick());
                        json.put("et", eventArray);
                    }
                    //商品详情页
                    if (random.nextBoolean()) {
                        eventArray.add(generateGoodsDetail());
                        json.put("et", eventArray);
                    }
                    //商品列表
                    if (random.nextBoolean()) {
                        eventArray.add(generateGoodsList());
                        json.put("et", eventArray);
                    }
                    // 广告
                    if (random.nextBoolean()) {
                        eventArray.add(generateAd());
                        json.put("et", eventArray);
                    }
                    // 消息通知
                    if (random.nextBoolean()) {
                        eventArray.add(generateNotification());
                        json.put("et", eventArray);
                    }
                    // 用户活跃
                    if (random.nextBoolean()) {
                        eventArray.add(generateBackGround());
                        json.put("et", eventArray);
                    }
                    // 故障日志
                    if (random.nextBoolean()) {
                        eventArray.add(generateError());
                        json.put("et", eventArray);
                    }
                    // 用户评论
                    if (random.nextBoolean()) {
                        eventArray.add(generateComment());
                        json.put("et", eventArray);
                    }
                    //用户收藏
                    if (random.nextBoolean()) {
                        eventArray.add(generateFavorites());
                        json.put("et", eventArray);
                    }
                    //用户点赞
                    if (random.nextBoolean()) {
                        eventArray.add(generatePraise());
                        json.put("et", eventArray);
                    }
                    now = System.currentTimeMillis();
                    logger.info(now + "|" + json.toJSONString());
                    break;
                default:
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    /**
     * 启动日志
     *
     * @return
     */
    private static AppStart generateStart() {
        AppStart appStart = new AppStart();
        //设置设备id
        appStart.setMid(sMid++ +"");
        appStart.setUid(sUid++ + "");
        appStart.setVc("" + random.nextInt(20));
        appStart.setVn("1" + random.nextInt(4) + "." + random.nextInt(10));
        appStart.setOs("8." + random.nextInt(3) + "." + random.nextInt(10));
        appStart.setEn("start");
        int flag = random.nextInt(3);
        switch (flag) {
            case (0):
                appStart.setL("es");
                break;
            case (1):
                appStart.setL("en");
                break;
            case (2):
                appStart.setL("pt");
                break;
            default:
        }
        //渠道号
        appStart.setSr(getRandomChar(1));
        //区域
        flag = random.nextInt(2);
        switch (flag) {
            case 0:
                appStart.setAr("BR");
                break;
            case 1:
                appStart.setAr("MX");
                break;
            default:
        }
        //获取手机品牌
        switch (flag) {
            case 0:
                appStart.setBa("Apple");
                appStart.setMd("apple-" + random.nextInt(20));
                break;
            case 1:
                appStart.setBa("Huawei");
                appStart.setMd("Huawei-" + random.nextInt(20));
                break;
            case 2:
                appStart.setBa("Xiaomi");
                appStart.setMd("Xiaomi-" + random.nextInt(20));
                break;
            default:
        }
        //嵌入 sdk 的版本
        appStart.setSv("V2." + random.nextInt(10) + "." + random.nextInt(10));
        // gmail
        appStart.setG(getRandomCharAndNum(8) + "@gmail.com");
        //屏幕宽高 hw
        flag = random.nextInt(4);
        //屏幕宽度
        switch (flag) {
            case 0:
                appStart.setHw("2568*1600");
                break;
            case 1:
                appStart.setHw("640*1136");
                break;
            case 2:
                appStart.setHw("750*1134");
                break;
            case 3:
                appStart.setHw("1080*1920");
                break;
            default:
        }
        long millis = System.currentTimeMillis();
        appStart.setT("" + (millis - random.nextInt(99999999)));
        //设置网络模式
        flag = random.nextInt(4);
        switch (flag) {
            case 0:
                appStart.setNw("3G");
                break;
            case 1:
                appStart.setNw("4G");
                break;
            case 2:
                appStart.setNw("5G");
                break;
            case 3:
                appStart.setNw("WIFI");
                break;
            default:
        }
        //经度
        appStart.setLn((-34 - random.nextInt(83) - random.nextInt(60) / 10.0) + "");
        //纬度
        appStart.setLa((32 - random.nextInt(85) - random.nextInt(60) / 10.0) + "");
        //入口
        flag = random.nextInt(5) + 1;
        appStart.setEntry("" + flag);
        //开屏广告类型
        appStart.setOpenAdType("" + (random.nextInt(2) + 1));
        //状态
        appStart.setAr("" + (random.nextInt(8) + 1 > 8 ? 2 : 1));
        //加载时长
        appStart.setLoadingTime("" + random.nextInt(20));
        flag = random.nextInt(10);
        switch (flag) {
            case 1:
                appStart.setDetail("102");
                break;
            case 2:
                appStart.setDetail("201");
                break;
            case 3:
                appStart.setDetail("325");
                break;
            case 4:
                appStart.setDetail("433");
                break;
            case 5:
                appStart.setDetail("542");
                break;
            default:
                appStart.setDetail("");
        }
        appStart.setExtend1("");
        return appStart;
    }

    /**
     * 基本信息
     *
     * @return
     */
    private static JSONObject generateComFields() {
        AppBase appBase = new AppBase();
        //设置设备id
        appBase.setMid(sMid++ + "");
        appBase.setUid(sUid++ + "");
        appBase.setVc("" + random.nextInt(20));
        appBase.setVn("1" + random.nextInt(4) + "." + random.nextInt(10));
        appBase.setOs("8." + random.nextInt(3) + "." + random.nextInt(10));
        int flag = random.nextInt(3);
        switch (flag) {
            case (0):
                appBase.setL("es");
                break;
            case (1):
                appBase.setL("en");
                break;
            case (2):
                appBase.setL("pt");
                break;
            default:
        }
        flag = random.nextInt(2);
        switch (flag) {
            case 0:
                appBase.setAr("BR");
                break;
            case 1:
                appBase.setAr("MX");
                break;
            default:
        }
        appBase.setSr(getRandomChar(1));
        flag = random.nextInt(3);
        switch (flag) {
            case 0:
                appBase.setBa("Apple");
                appBase.setMd("apple-" + random.nextInt(20));
                break;
            case 1:
                appBase.setBa("Huawei");
                appBase.setMd("Huawei-" + random.nextInt(20));
                break;
            case 2:
                appBase.setBa("Xiaomi");
                appBase.setMd("Xiaomi-" + random.nextInt(20));
                break;
            default:
        }
        //嵌入 sdk 的版本
        appBase.setSv("V2." + random.nextInt(10) + "." + random.nextInt(10));
        // gmail
        appBase.setG(getRandomCharAndNum(8) + "@gmail.com");
        flag = random.nextInt(4);
        //屏幕宽度
        switch (flag) {
            case 0:
                appBase.setHw("2568*1600");
                break;
            case 1:
                appBase.setHw("640*1136");
                break;
            case 2:
                appBase.setHw("750*1134");
                break;
            case 3:
                appBase.setHw("1080*1920");
                break;
            default:
        }
        long millis = System.currentTimeMillis();
        appBase.setT("" + (millis - random.nextInt(99999999)));
        //设置网络模式
        flag = random.nextInt(4);
        switch (flag) {
            case 0:
                appBase.setNw("3G");
                break;
            case 1:
                appBase.setNw("4G");
                break;
            case 2:
                appBase.setNw("5G");
                break;
            case 3:
                appBase.setNw("WIFI");
                break;
            default:
        }
        //经度
        appBase.setLn((-34 - random.nextInt(83) - random.nextInt(60) / 10.0) + "");
        //纬度
        appBase.setLa((32 - random.nextInt(85) - random.nextInt(60) / 10.0) + "");
        return (JSONObject) JSON.toJSON(appBase);
    }

    /**
     * 商品点击
     *
     * @return
     */
    private static JSONObject generateGoodsClick() {
        AppGoodsClick appGoodsClick = new AppGoodsClick();
        boolean boolFlag = random.nextInt(10) < 7;
        // 动作：曝光商品=1，点击商品=2，
        if (boolFlag) {
            appGoodsClick.setAction("1");
        } else {
            appGoodsClick.setAction("2");
        }
        // 商品 id
        String goodsId = sGoodSid + "";
        sGoodSid++;
        appGoodsClick.setGoodsId(goodsId);

        // 顺序 设置成 6 条吧
        int flag = random.nextInt(6);
        appGoodsClick.setPlace("" + flag);
        // 曝光类型
        flag = 1 + random.nextInt(2);
        appGoodsClick.setExtend1("" + flag);
        // 分类
        flag = 1 + random.nextInt(100);
        appGoodsClick.setCategory("" + flag);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(appGoodsClick);
        return packEventJson("display", jsonObject);
    }

    /**
     * 商品详情
     *
     * @return
     */
    private static JSONObject generateGoodsDetail() {
        AppGoodsItem appGoodsItem = new AppGoodsItem();
        // 页面入口来源
        int flag = 1 + random.nextInt(3);
        appGoodsItem.setEntry(flag + "");
        // 动作
        appGoodsItem.setAction("" + (random.nextInt(4) + 1));
        // 商品 id
        appGoodsItem.setGoodsId(sGoodSid + "");
        // 商品来源类型
        flag = 1 + random.nextInt(3);
        appGoodsItem.setGoodsSource(flag + "");
        // 商品样式
        flag = random.nextInt(6);
        appGoodsItem.setShowType("" + flag);
        // 页面停留时长
        flag = random.nextInt(10) * random.nextInt(7);
        appGoodsItem.setNewsStayTime(flag + "");
        // 加载时长
        flag = random.nextInt(10) * random.nextInt(7);
        appGoodsItem.setLoadingTime(flag + "");
        // 加载失败码
        flag = random.nextInt(10);
        switch (flag) {
            case 1:
                appGoodsItem.setType1("102");
                break;
            case 2:
                appGoodsItem.setType1("201");
                break;
            case 3:
                appGoodsItem.setType1("325");
                break;
            case 4:
                appGoodsItem.setType1("433");
                break;
            case 5:
                appGoodsItem.setType1("542");
                break;
            default:
                appGoodsItem.setType1("");
                break;
        }
        // 分类
        flag = 1 + random.nextInt(100);
        appGoodsItem.setCategory("" + flag);
        JSONObject eventJson = (JSONObject) JSON.toJSON(appGoodsItem);
        return packEventJson("newsdetail", eventJson);

    }

    /**
     * 商品列表
     *
     * @return
     */
    private static JSONObject generateGoodsList() {
        AppGoodsList appLoading = new AppGoodsList();
        // 动作
        int flag = random.nextInt(3) + 1;
        appLoading.setAction(flag + "");
        // 加载时长
        flag = random.nextInt(10) * random.nextInt(7);
        appLoading.setLoadingTime(flag + "");
        // 失败码
        flag = random.nextInt(10);
        switch (flag) {
            case 1:
                appLoading.setType1("102");
                break;
            case 2:
                appLoading.setType1("201");
                break;
            case 3:
                appLoading.setType1("325");
                break;
            case 4:
                appLoading.setType1("433");
                break;
            case 5:
                appLoading.setType1("542");
                break;
            default:
                appLoading.setType1("");
                break;
        }
        // 页面 加载类型
        flag = 1 + random.nextInt(2);
        appLoading.setLoadingWay("" + flag);
        // 扩展字段 1
        appLoading.setExtend1("");
        // 扩展字段 2
        appLoading.setExtend2("");
        // 用户加载类型
        flag = 1 + random.nextInt(3);
        appLoading.setType("" + flag);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(appLoading);
        return packEventJson("loading", jsonObject);
    }

    /**
     * 广告点击日志
     *
     * @return
     */
    private static JSONObject generateAd() {
        AppAd appAd = new AppAd();
        // 入口
        int flag = random.nextInt(3) + 1;
        appAd.setEntry(flag + "");
        // 动作
        flag = random.nextInt(5) + 1;
        appAd.setAction(flag + "");
        // 内容类型类型
        flag = random.nextInt(6) + 1;
        appAd.setContentType(flag + "");
        // 展示样式
        flag = random.nextInt(120000) + 1000;
        appAd.setDisplayMills(flag + "");
        flag = random.nextInt(1);
        if (flag == 1) {
            appAd.setContentType(flag + "");
            flag = random.nextInt(6);
            appAd.setItemId(flag + "");
        } else {
            appAd.setContentType(flag + "");
            flag = random.nextInt(1) + 1;
            appAd.setActivityId(flag + "");
        }
        JSONObject jsonObject = (JSONObject) JSON.toJSON(appAd);

        return packEventJson("ad", jsonObject);
    }

    /**
     * 通知类日志
     *
     * @return
     */
    private static JSONObject generateNotification() {
        AppNotification appNotification = new AppNotification();
        appNotification.setType("" + (random.nextInt(4) + 1));
        appNotification.setAction("" + (random.nextInt(4) + 1));
        appNotification.setApTime(System.currentTimeMillis() - random.nextInt(99999999) + "");
        appNotification.setContent("");
        JSONObject jsonObject = (JSONObject) JSON.toJSON(appNotification);
        return packEventJson("notification", jsonObject);
    }

    /**
     * 后台活跃
     *
     * @return
     */
    private static JSONObject generateBackGround() {
        AppActiveBackGround appActiveBackGround = new AppActiveBackGround();
        appActiveBackGround.setActiveSource("" + (random.nextInt(3) + 1));
        JSONObject jsonObject = (JSONObject) JSON.toJSON(appActiveBackGround);

        return packEventJson("active_background", jsonObject);
    }

    /**
     * 错误日志
     *
     * @return
     */
    private static JSONObject generateError() {
        AppErrorLog appErrorLog = new AppErrorLog();
        //错误摘要
        String[] errorBriefs = {"atcn.lift.dfdf.web.AbstractBaseController.validInbound(AbstractBaseController.java:72) ",
                "at cn.lift.appIn.control.CommandUtil.getInfo(CommandUtil.java:67)"};
        //错误详情
        String[] errorDetails = {"java.lang.NullPointerException\\n " + "atcn.lift.appIn.web.AbstractBaseController." +
                "validInbound(AbstractBaseController.java:7 2)\\n " + "at cn.lift.dfdf.web.AbstractBaseController.validInbound",
                "atcn.lift.dfdfdf.control.CommandUtil.getInfo(CommandUtil.java:67)\\n " +
                        "atsun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\\n" +
                        " at java.lang.reflect.Method.invoke(Method.java:606)\\n"};

        //错误摘要
        appErrorLog.setErrorBrief(errorBriefs[random.nextInt(errorBriefs.length)]);
        //错误详情
        appErrorLog.setErrorDetail(errorDetails[random.nextInt(errorDetails.length)]);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(appErrorLog);
        return packEventJson("error", jsonObject);
    }

    private static JSONObject generateComment() {
        AppComment comment = new AppComment();
        comment.setCommentId(random.nextInt(10));
        comment.setUserId(random.nextInt(10));
        comment.setPCommentId(random.nextInt(5));
        comment.setContent(getCONTENT());
        comment.setAddTime((System.currentTimeMillis() - random.nextInt(99999999)) + "");
        comment.setOtherId(random.nextInt(10));
        comment.setPraiseCount(random.nextInt(1000));
        comment.setReplyCount(random.nextInt(200));
        JSONObject jsonObject = (JSONObject) JSON.toJSON(comment);
        return packEventJson("comment", jsonObject);
    }

    private static String getCONTENT() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < random.nextInt(100); i++) {
            str.append(getRandomChar());
        }
        return str.toString();
    }

    private static char getRandomChar() {
        String str = "";
        int hightPos; //
        int lowPos;
        Random random = new Random();
        //随机生成汉子的两个字节
        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();
        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }
        return str.charAt(0);
    }

    /**
     * 收藏
     *
     * @return
     */
    private static JSONObject generateFavorites() {
        AppFavorites favorites = new AppFavorites();
        favorites.setCourseId(random.nextInt(10));
        favorites.setUserId(random.nextInt(10));
        favorites.setAddTime((System.currentTimeMillis() - random.nextInt(99999999)) + "");
        JSONObject jsonObject = (JSONObject) JSON.toJSON(favorites);
        return packEventJson("favorites", jsonObject);
    }

    /**
     * 点赞
     *
     * @return
     */
    private static JSONObject generatePraise() {
        AppPraise praise = new AppPraise();
        praise.setId(random.nextInt(10));
        praise.setUserId(random.nextInt(10));
        praise.setTargetId(random.nextInt(10));
        praise.setType(random.nextInt(4) + 1);
        praise.setAddTime((System.currentTimeMillis() - random.nextInt(99999999)) + "");
        JSONObject jsonObject = (JSONObject) JSON.toJSON(praise);
        return packEventJson("praise", jsonObject);
    }

    /**
     * 随机返回一个字符
     *
     * @param length
     * @return
     */
    private static String getRandomChar(Integer length) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append((char) (65 + random.nextInt(26)));
        }
        return str.toString();
    }

    /**
     * @param length
     * @return
     */
    private static String getRandomCharAndNum(Integer length) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            boolean b = random.nextBoolean();
            // 字符串
            if (b) {
                // int choice = random.nextBoolean() ? 65 : 97; 取得 65 大写字母还是 97 小写字母
                // 取得大写字
                str.append((char) (65 + random.nextInt(26)));
            } else {
                // 数字
                str.append(random.nextInt(10));
            }
        }
        return str.toString();
    }

    /**
     * @param eventName
     * @param jsonObject
     * @return
     */
    private static JSONObject packEventJson(String eventName, JSONObject jsonObject) {
        JSONObject eventJson = new JSONObject();
        eventJson.put("ett", (System.currentTimeMillis() - random.nextInt(99999999)) + "");
        eventJson.put("en", eventName);
        eventJson.put("kv", jsonObject);
        return eventJson;
    }
}
