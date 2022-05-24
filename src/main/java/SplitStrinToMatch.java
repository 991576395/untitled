import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @author xuzhenyao
 * @Date 2022/4/25 9:04
 */
public class SplitStrinToMatch {

    /**
     * 匹配两个字符串
     * @param source  原内容
     * @param targe  样本
     */
    public Map<String, List<Integer>> checkStringCode(String source, String targe){
        Map<String,List<Integer>> stringListMap = new HashMap<>();

        List<Integer> numbers = new LinkedList<>();

        for (int i = 0; i < source.length(); i++) {
            int index = -1;
            String key = String.valueOf(source.charAt(i));
            while((index = targe.indexOf(key,index)) != -1){
                if(numbers.contains(index)){
                    numbers.add(index);
                    if(stringListMap.get(key) == null){
                        List<Integer> integers = new ArrayList<>();
                        integers.add(index);
                        stringListMap.put(key,integers);
                    }else{
                        stringListMap.get(key).add(index);
                    }
                }
                index++;
            }
        }

        numbers.sort((o1, o2) -> {
                    if (o1.equals(o2)) {
                        return 0;
                    }
                    return o1 > o2 ? 1 : -1;
                }
        );

        int start = 0;
        int end =0;
        //循环targ  将连续数字进行合并
        for (int i = 0; i < numbers.size() -1; i++) {
            if(numbers.get(i) + 1 == numbers.get(i +1)){
                end++;
                if(i == numbers.size() -2 && start != end){
                    mergMap(stringListMap, numbers, start, end);
                }
                continue;
            }else{
                if(start == end){
                    start = i + 1;
                    end = i + 1;
                    continue;
                }
                mergMap(stringListMap, numbers, start, end);
                start = i;
                end = i;
            }
        }
        return stringListMap;
    }


    private void mergMap(Map<String, List<Integer>> stringListMap, List<Integer> numbers, int start, int end) {
        int size = end - start +1;
        String[] codes = new String[size];
        int checkSize = 0;
        //key合并
        for (Map.Entry<String, List<Integer>> stringListEntry : stringListMap.entrySet()) {
            for (int j = 0; j < codes.length; j++) {
                if (stringListEntry.getValue() != null && stringListEntry.getValue().contains(numbers.get(j + start))) {
                    codes[j] = stringListEntry.getKey();
                    stringListEntry.getValue().remove(numbers.get(j + start));

                    if(stringListEntry.getValue().size() == 0){
                        stringListMap.put(codes[j],null);
                    }
                    checkSize++;
                }
            }

            if (checkSize ==  size){
                break;
            }
        }

        if(stringListMap.get(StringUtils.join(codes)) == null){
            List<Integer> integers = new LinkedList<>();
            integers.add(numbers.get(start));
            stringListMap.put(StringUtils.join(codes),integers);
        }else{
            stringListMap.get(StringUtils.join(codes)).add(numbers.get(start));
        }
    }

    public static void main(String[] args) {
        SplitStrinToMatch splitStrinToMatch = new SplitStrinToMatch();
        System.out.println(new Gson().toJson(splitStrinToMatch.checkStringCode("你好哟","阿斯蒂芬你阿斯蒂芬好阿斯蒂芬你好")));
    }
}
