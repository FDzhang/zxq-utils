package com.fd.demo.utils.algo;

import org.junit.Test;

/**
 * @author ：zxq
 * @date ：Created in 2021/1/15 16:17
 */

public class ArrayTest {

    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;

        int index = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[index - 2])
                nums[index++] = nums[i];
        }

        return index;
    }

    @Test
    public void test() {
        int[] A = {1, 1, 1, 2, 2, 3};
        int len = removeDuplicates(A);

        for (int i = 0; i < len; i++) {
            System.out.print(A[i] + ",");
        }
    }
}
