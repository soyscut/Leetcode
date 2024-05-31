package BinarySearch;

public class EatingBanana {
    public int minEatingSpeed(int[] piles, int h) {
        int maxNumber = 0;
        for (int pile : piles) {
            maxNumber = Math.max(pile, maxNumber);
        }

        int l = 1, r = maxNumber, result = 0;
        while (l <= r) {
            int m = l + (r - l) / 2;

            long hours = 0;
            for (int pile : piles) {
                hours += (pile / m + (pile % m == 0 ? 0 : 1));
            }
            if (hours > h) {
                l = m + 1;
            } else {
                result = m;
                r = m - 1;
            }
        }
        return result;
    }
}
