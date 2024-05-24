public class QuickSelect {
    /**
     * Find out the kth element from min to max in the array.
     */
    public int quickSelect(int[] nums, int left, int right, int k) {
        if (nums.length < k) {
            return -1;
        }

        int pivotValue = nums[left];

        int i = left + 1, j = right;
        while (i <= j) {
            if (nums[i] > pivotValue && nums[j] < pivotValue) {
                swap(nums, i ,j);
            }
            if (nums[i] <= pivotValue) {
                i++;
            }
            if (nums[j] >= pivotValue) {
                j--;
            }
        }
        // i - 1 是左边,一定比pivot小, j + 1 是右边，一定比pivot大
        // 如果都比pivot小，那i目前就指向边界外右边；如果都比pivot小，那目前j就指向pivot index
        int leftCnt = i - 1 - left;
        if (leftCnt >= k) {
            return quickSelect(nums, left + 1, i - 1, k);
        } else if (leftCnt + 1 == k) {
            return pivotValue;
        } else {
            return quickSelect(nums, j + 1, right, k - leftCnt - 1);
        }
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
