import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author jerry chan
 * @date 2021/9/19
 */
public class EsJsonGenerator {

    @Test
    public void execute() {
        IntStream.range(1, 100).forEach(n -> {
            Long timestamp = DateUtil.current();
            Long userId = Long.parseLong(RandomUtil.randomNumbers(5));
            List<Long> cityList = Arrays.asList(5810L, 6020L, 5840L, 5860L, 5868L);
            List<Long> tagList = Arrays.asList(111L, 222L, 333L, 444L, 555L);
            Map<String, Object> map = new HashMap<>();
            map.put("id", n);
            map.put("name", RandomName.getRandomJianHan(3));
            map.put("phone", getPhoneNum());
            map.put("country", 100000);
            map.put("povince", 400000);
            map.put("city", cityList.get(RandomUtil.randomInt(0, 4)));
            map.put("status", RandomUtil.randomInt(1, 4));
            map.put("type", RandomUtil.randomInt(1, 4));
            map.put("corp_id", "21299");
            map.put("create_user_id", userId);
            map.put("modify_user_id", userId);
            map.put("create_time", timestamp);
            map.put("modify_time", timestamp);
            map.put("tag_relation", IntStream.range(0, RandomUtil.randomInt(1, 4)).mapToObj(i -> {
                Map<String, Object> tagRelation = new HashMap<>();
                tagRelation.put("tag_id", tagList.get(i));
                tagRelation.put("create_user_id", userId);
                tagRelation.put("modify_user_id", userId);
                tagRelation.put("create_time", timestamp);
                tagRelation.put("modify_time", timestamp);
                return tagRelation;
            }).collect(Collectors.toList()));
            System.out.println("POST /clue_3/_doc/" + map.get("id") + "\n" + JSONUtil.toJsonStr(map));
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static String getPhoneNum() {
        //给予真实的初始号段，号段是在百度上面查找的真实号段
        String[] start = {"133", "149", "153", "173", "177",
                "180", "181", "189", "199", "130", "131", "132",
                "145", "155", "156", "166", "171", "175", "176", "185", "186", "166", "134", "135",
                "136", "137", "138", "139", "147", "150", "151", "152", "157", "158", "159", "172",
                "178", "182", "183", "184", "187", "188", "198", "170", "171"};

        //随机出真实号段  使用数组的length属性，获得数组长度，
        //通过Math.random（）*数组长度获得数组下标，从而随机出前三位的号段
        String phoneFirstNum = start[(int) (Math.random() * start.length)];
        //随机出剩下的8位数
        String phoneLastNum = "";
        //定义尾号，尾号是8位
        final int LENPHONE = 8;
        //循环剩下的位数
        for (int i = 0; i < LENPHONE; i++) {
            //每次循环都从0~9挑选一个随机数
            phoneLastNum += (int) (Math.random() * 10);
        }
        //最终将号段和尾数连接起来
        return phoneFirstNum + phoneLastNum;
    }
}
