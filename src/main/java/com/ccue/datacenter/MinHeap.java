package com.ccue.datacenter;

/**
 * @author joking@aliyun.com
 * @description
 * @date 2019-01-07
 */
public class MinHeap {

    /**
     * @description:
     * @since: 2019-01-07
     *
     * index 为最后一个非叶子节点的节点
     */
    public static void up(int a[], int index, int len) {

        int left = 2*index +1;
        int right = 2*index+2;
        int minIndex = index;

        // 右节点不一定存在
        // 先左右比较，得到最小，和根比较
        if (right < len && a[left] < a[right]) {
            minIndex = left;
        } else {
            minIndex = right;
        }
        // 比根节点还小，就交换
        if (a[minIndex] < a[index]) {
            int temp = a[index];
            a[index] = a[minIndex];
            a[minIndex] = temp;

            // 需要递归维护整个字数为最小堆
            up(a, minIndex, len);
        }
    }

}
