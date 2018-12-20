package com.congzibank.dayalgrithm;

import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] a = new int[] {1, 4,6,7,7,9,0,5,8};

        a = bubbleSort(a);

        for (int i : a) {
            Log.e("sort", "======" + i + "=======");
        }
    }

    /**
     * 冒泡排序
     * @param nums
     * @return
     */
    public int[] bubbleSort(int[] nums) {
        if (nums == null || nums.length < 2)  {
                return nums;
        }
        for (int i = nums.length - 1; i > 0 ; i -- ) {
            for (int j = 0 ; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }
}
