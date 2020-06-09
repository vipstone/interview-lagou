import java.util.Arrays;

/**
 * 算法示例
 */
public class AlgorithmExample {
    public static void main(String[] args) {

        // 待排序数组
        int[] nums = {33, 45, 11, 22, 12, 39, 27};
        bubbleSort(nums);
        // 打印排序完数组
        System.out.println(Arrays.toString(nums));

    }

    /**
     * 冒泡排序
     */
    private static void bubbleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            // 判断标识
            boolean flag = true;
            for (int j = 0; j < nums.length - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    // 前面数字大于后面的数字，执行位置交换
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    // 执行了位置交换，更改标识
                    flag = false;
                }
            }
            if (flag) {
                // 没有可以移动的元素了，跳出循环
                break;
            }
        }
    }


    /**
     * 二分法测试代码
     */
    private static void binaryTest() {
        // 要查询的数组
        int[] binaryNums = new int[100];
        for (int i = 0; i < 100; i++) {
            // 初始化数组（存入 100 个数据）
            binaryNums[i] = (i + 1);
        }
        // 要查询的数值
        int findValue = 75;
        // 调用二分查找算法
        int binaryResult = binarySearch(binaryNums, 0, binaryNums.length - 1, findValue);
        // 打印结果
        System.out.println("元素的位置是：" + (binaryResult + 1));
    }


    /**
     * 二分查找算法（返回该值第一次出现的位置）
     * @param nums      查询数组
     * @param start     开始下标
     * @param end       结束下标
     * @param findValue 要查找的值
     * @return int
     */
    private static int binarySearch(int[] nums, int start, int end, int findValue) {
        if (start <= end) {
            // 中间位置
            int middle = (start + end) / 2;
            // 中间的值
            int middleValue = nums[middle];
            if (findValue == middleValue) {
                // 等于中值直接返回
                return middle;
            } else if (findValue < middleValue) {
                // 小于中值，在中值之前的数据中查找
                return binarySearch(nums, start, middle - 1, findValue);
            } else {
                // 大于中值，在中值之后的数据中查找
                return binarySearch(nums, middle + 1, end, findValue);
            }
        }
        return -1;
    }
}


