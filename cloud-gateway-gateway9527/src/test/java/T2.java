import java.time.ZonedDateTime;

/**
 * @Author: xsxin
 * @Date: 2020/6/22 13:16
 */
public class T2 {

    /**
     * 获取当前默认时区
     * @param args
     */
    public static void main(String[] args) {
        ZonedDateTime time = ZonedDateTime.now(); //默认时区
        System.out.println(time);
    }
}
