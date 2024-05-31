package BinarySearch;

public class BinarySearch {
    /**
     * Search in array [1, 3, 5, 7, 9]
     * Target:0. Left Index: 0, Right index:-1
     * Target:2. Left Index: 1, Right index:0
     * Target:4. Left Index: 2, Right index:1
     * Target:8. Left Index: 4, Right index:3
     * Target:10. Left Index: 5, Right index:4
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println("Target:" + target + ". Left Index: " + left + ", Right index:" + right);

        return left;
    }

    /**
     * Search in array [1, 3, 5, 7, 9]
     * Target:0. Left Index: 0, Right index:0
     * Target:2. Left Index: 1, Right index:1
     * Target:4. Left Index: 2, Right index:2
     * Target:8. Left Index: 4, Right index:4
     * Target:10. Left Index: 5, Right index:5
     */
    public int searchRightOpen(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println("Target:" + target + ". Left Index: " + left + ", Right index:" + right);

        return left;
    }

    /**
     * Search in array [1, 3, 5, 5, 5, 7, 9]
     * Target:5. Left Index: 2, Right index:1
     * Target:1. Left Index: 0, Right index:-1
     * Target:2. Left Index: 1, Right index:0
     * Target:3. Left Index: 1, Right index:0
     * Target:10. Left Index: 7, Right index:6
     */
    public int searchLeftMost(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println("Target:" + target + ". Left Index: " + left + ", Right index:" + right);

        return left;
    }

    /**
     * Search in array [1, 3, 5, 5, 5, 7, 9]
     * Target:1. Left Index: 1, Right index:0, Index: 0
     * Target:2. Left Index: 1, Right index:0, Index: -1
     * Target:3. Left Index: 2, Right index:1, Index: 1
     * Target:5. Left Index: 5, Right index:4, Index: 4
     * Target:10. Left Index: 7, Right index:6, Index: -1
     */
    public int searchRightMost(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = -1, index = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                if (nums[mid] == target) {
                    index = mid;
                }
                left = mid + 1;
            }
        }

        System.out.println("Target:" + target + ". Left Index: " + left + ", Right index:" + right + ", Index: " + index);

        return left;
    }
}
