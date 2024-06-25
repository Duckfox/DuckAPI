package com.duckfox.duckapi.utils;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomUtil {
    private RandomUtil(){}
    public static Random random = new Random();
    /**
     * 带权重的随机选择算法，从Map的键中返回一个随机的键。
     *
     * @param weightedMap 键值对形式的Map，其中值是权重
     * @return 随机选择的键
     */
    public static <K> K weightedRandomKey(Map<K, Integer> weightedMap) {
        if (weightedMap == null || weightedMap.isEmpty()) {
            throw new IllegalArgumentException("Weighted map cannot be null or empty");
        }

        int totalWeight = 0;
        for (int weight : weightedMap.values()) {
            totalWeight += weight;
        }

        if (totalWeight == 0) {
            // 如果所有权重都是0，则随机返回一个键
            return weightedMap.keySet().iterator().next();
        }

        Random random = new Random();
        int randomValue = random.nextInt(totalWeight) + 1;

        int currentWeight = 0;
        for (Map.Entry<K, Integer> entry : weightedMap.entrySet()) {
            currentWeight += entry.getValue();
            if (randomValue <= currentWeight) {
                return entry.getKey();
            }
        }

        // 如果所有权重都小于随机值，返回最后一个键（理论上不应该发生）
        return weightedMap.keySet().iterator().next();
    }

    public static <T> T getRandomElement(List<T> list){
        return list.get(random.nextInt(list.size()));
    }
    /**
     * 包头不包尾的随机数
     * @param min 最小值
     * @param max 最大值
     * */
    public static int getRandomInt(int min, int max){
        return min + random.nextInt(max - min);
    }
    /**
    * 包头包尾的随机数
     * @param min 最小值
     * @param max 最大值
    * */
    public static int getRandomIntInclusive(int min, int max){
        return min + random.nextInt((max - min) + 1);
    }
}
