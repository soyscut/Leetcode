import java.util.*;

/**
 * 347. Top K Frequent Elements
 * https://leetcode.com/problems/top-k-frequent-elements/
 */
public class TopKFrequent {
    Map<Integer, Integer> numToCounts;

    /**
     * Solution 1. Quick Select Solution
     * Time: O(N), Space: O(N)
     */
    public int[] topKFrequent(int[] nums, int k) {
        numToCounts = new HashMap<>();

        for (int num : nums) {
            numToCounts.put(num, numToCounts.getOrDefault(num, 0) + 1);
        }

        int[] array = new int[numToCounts.size()];
        int idx = 0;
        for (int num : numToCounts.keySet()) {
            array[idx] = num;
            idx++;
        }

        int index = quickSelect(array, 0, array.length - 1, array.length - k + 1);

        return Arrays.copyOfRange(array, index, array.length);
    }

    private int quickSelect(int[] array, int left, int right, int k) {
        int pivotValue = numToCounts.get(array[left]);

        int i = left + 1, j = right;
        while (i <= j) {
            if (numToCounts.get(array[i]) > pivotValue && numToCounts.get(array[j]) < pivotValue) {
                swap(array, i, j);
            }
            if (numToCounts.get(array[i]) <= pivotValue) {
                i++;
            }
            if (numToCounts.get(array[j]) >= pivotValue) {
                j--;
            }
        }
        // 需要有这个swap去保证数组是被分隔开的，i - 1左边是比pivot小的， i-1右边是大的
        // 这是因为这题是拿后k个数，而不是第k个，所以需要这个数组是somehow有序的
        swap(array, i - 1, left);

        // Left -> i - 2: leftHand, i, right: rightHand
        int leftCnt = i - left - 1;
        if (leftCnt >= k) {
            return quickSelect(array, left, i - 2, k);
        } else if (leftCnt + 1 == k) {
            return i - 1;
        } else {
            return quickSelect(array, i, right, k - leftCnt - 1);
        }
    }

    private void swap(int[] array, int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    /**
     * Solution 2: Heap/Priority Queue
     * Time: O(NlogK), Space: O(N)
     */
    public int[] topKFrequent2(int[] nums, int k) {
        numToCounts = new HashMap<>();

        for (int num : nums) {
            int counts = numToCounts.getOrDefault(num, 0);
            numToCounts.put(num, counts + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a , b) -> numToCounts.get(a) - numToCounts.get(b));
        // O(K + (N-K)LogK)
        for (int num : numToCounts.keySet()) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] result = new int[k];
        int index = k - 1;
        while (!pq.isEmpty()) {
            result[index] = pq.poll();
            index--;
        }
        return result;
    }

    /**
     * Solution 3. Bucket sort Solution
     * Time: O(N), Space: O(N)
     */
    public int[] topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> numToCounts = new HashMap<>();

        int maxCounts = 0;
        for (int num : nums) {
            int count = numToCounts.getOrDefault(num, 0) + 1;
            numToCounts.put(num, count);
            maxCounts = Math.max(maxCounts, count);
        }

        Map<Integer, List<Integer>> freqToNums = new HashMap<>();
        for (int num : numToCounts.keySet()) {
            int freq = numToCounts.get(num);
            if (!freqToNums.containsKey(freq)) {
                freqToNums.put(freq, new ArrayList<>());
            }
            freqToNums.get(freq).add(num);
        }

        int[] result = new int[k];
        int index = k - 1;
        for (int i = maxCounts; i >= 0; i--) {
            List<Integer> numsInFreq = freqToNums.getOrDefault(i, null);
            if (numsInFreq != null) {
                for (int integer : numsInFreq) {
                    result[index] = integer;
                    index--;
                    if (index < 0) {
                        return result;
                    }
                }
            }
        }

        return result;
//        List<Integer>[] bucket = new ArrayList[maxCounts + 1];
//
//        for (int num : numToCounts.keySet()) {
//            int count = numToCounts.get(num);
//            if (bucket[count] == null) {
//                bucket[count] = new ArrayList<>();
//            }
//            bucket[count].add(num);
//        }
//
//        int[] result = new int[k];
//        int idx = k - 1;
//        for (int i = maxCounts; i >= 1; i--) {
//            List<Integer> curr = bucket[i];
//            if (curr != null) {
//                for (int num : curr) {
//                    result[idx] = num;
//                    idx--;
//                }
//                if (idx < 0) {
//                    break;
//                }
//            }
//        }

        return result;
    }
}
