package com.no1.cz.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000117683330";   //你的沙箱APPID

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCUOUGJI9zjBsxw1rfDhXdL7PiJ+6ldgN0ZS7Fuv8rG13tP8dhkbQfcofWIftShOnFqHuz4ZnimPjmVeqUCQla3XYH0X0GqbmwBEBK2W6Lbji//aocovcLzpdfOOCpgmqe+hgdfSjUE3H6s/qb/gCFJLFNfa2m6aEZXLHo36IXmS2BC4SByjaap2Q6oby1KEwCbm84Fl9clfZw2JyV3Tcl5BdVmy/cYzcJaL2iJgcGjT107qcXL4MLW5jt+8hrqGt5wudDBZfzN+VrfLeV4KNjdqrDVZCdGukOIhQyAHE7xOyS6Zc0b41mUWiasrtUKMxw6OCxo4Twcx9wYeSaImd8XAgMBAAECggEAJlzTS21OxCuESkgoc6BdifchNIVABoVNtDhVCfb1wpUOw1gyy3s0p7P7WoKJAdmgdPjgGEJdQ5mjSb7eakFFNFwtQFRPKI8/wN6qGSyKcAzS+2ZU6QSEKo9n1WXQAgTFGZiS2+TW6BUxbBDUpBhDhS7FvVKNoMNQQUuvrjyuOzYtVvrq6DXcelOniR3rLMnX8PlIm/nZtBcAWVQ4C+GEVUTAjDXsy71q8jvkXjSgCPNU+VPsZXxyQF97gsNvzLaq6vFKfxi7QZPDO5gYjCJxPlMLBD81WAOu+5dnBwiNoAbQDrFA5Ya3LZrUnsd3AwQZwSbrbTJdoZ1L/Ie0LdqeoQKBgQDeCboInZt83jjzz1SNsafxwKkR2NmByQBu+CwxR9v5esYvcEt7d+6zmflMPaiqNhpKhpHFt9YnmbCFCa12Llksz6yEp6R0W/i8mDQqIcN5DbskW8EY20lNnd9KWnE6kJsf7uTU7tgGNPGDpxDlD8px54m1mufq2YD6gvRFJxuWLwKBgQCq5TQ5rrhRdLC1cdGZ6O/NGYicR8CSbTsnvlGf7+lXoht1iAwiYEWdxe8Ta54yW63nvnfAbUGEYGjg1ZMWC8ztpJxSpRoms4TY2JlCDNmgZZbYOGdLxICFZbyRqct9DPm6Rn0Fec+UJSa9Qjo1fLs9fJk0fHJwFQTuxw65ojpzmQKBgQDAti555dkbMoC507wuUwBRygazz8NQatVuewVYLGdXC+FRMSTCX+CUj1IZgvaN7PE6t7tzoNBFhJX5keG0zhtl0kWIyTvqJh0oCu5TlwRiX7GKp/xbh5ACxsATd5YI35NTzvNbgIWMNY1c17bJKMBrxir2rSEW63qPcvNxxbSizwKBgQCSiZ9SpuMd1bWAoGxeUULu16owd8ZEyK0k0ykiv5GSt0pcaaAeCfcn5oWuXWV1QO/J+6SruAOqAxFrWTeae9vpGnGA3FmkPeL4HiKsZGBoDTRfi13ntkOOH0KmlLYMMIgilja171FzcrfZKWTIYIAdpS2pt9xZB20HYmDaBKWiYQKBgQC+EZN/8IgQ1b5RHg6nncs1eJFgL04LQMJqyyBH+xOkyoFH0HWedOXM/GjI0tB7zHEskOOz6fKAo4zjbAXakH5fKYEd6VUGdy3g9MJM98jKHPcx8PF9mByZ/igZbxZxVHfkHRP5vyoew4tCbryEk7cXcTp8PoUvSfvG6r3r8NC/9w==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlDlBiSPc4wbMcNa3w4V3S+z4ifupXYDdGUuxbr/Kxtd7T/HYZG0H3KH1iH7UoTpxah7s+GZ4pj45lXqlAkJWt12B9F9Bqm5sARAStlui244v/2qHKL3C86XXzjgqYJqnvoYHX0o1BNx+rP6m/4AhSSxTX2tpumhGVyx6N+iF5ktgQuEgco2mqdkOqG8tShMAm5vOBZfXJX2cNicld03JeQXVZsv3GM3CWi9oiYHBo09dO6nFy+DC1uY7fvIa6hrecLnQwWX8zfla3y3leCjY3aqw1WQnRrpDiIUMgBxO8TskumXNG+NZlFomrK7VCjMcOjgsaOE8HMfcGHkmiJnfFwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
    // 改成你自己的端口和站点根目录
    public static String notify_url = "http://localhost:10001/web-dev/myPay/toNotify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
//    public static String return_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
    // 改成你自己的端口和站点根目录
    public static String return_url = "http://localhost:10001/web-dev/myPay/succ";


    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志存放的父级路径
    public static String log_path = "D:\\tmp\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}