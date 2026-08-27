import java.util.*;
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] bucket = new List[nums.length + 1];

        for (int num : map.keySet()) {
            int frequency = map.get(num);

            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }

            bucket[frequency].add(num);
        }
        int[] result = new int[k];
        int index = 0;

        for (int frequency = bucket.length - 1;
             frequency >= 0 && index < k;
             frequency--) {

            if (bucket[frequency] != null) {
                for (int num : bucket[frequency]) {
                    result[index++] = num;

                    if (index == k) {
                        break;
                    }
                }
            }
        }

        return result;
    }
}