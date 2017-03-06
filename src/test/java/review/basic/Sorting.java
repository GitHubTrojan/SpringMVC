package review.basic;

/**
 * Created by Vincent on 2017/3/5.
 * 排序算法 Review
 */
public class Sorting {
    private static int[] beforeSorting = {3,1,5,7,2,4,9,6,10,8};

    public static void main(String[] args){
        Sorting sort = new Sorting();
//        sort.insertSort(beforeSorting);
//        print(beforeSorting);
//        sort.shellSort();
//        sort.simpleSelectSort();
//        sort.SelectSort(beforeSorting, beforeSorting.length);
//        print(beforeSorting);
    }
    /**
     * 按序输出
     */
    public static void print(int[] a){
        for (int i=0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    /**
     * Straight Insertion Sort
     * Description
     * in every single loop do this
     * step 1: compare two elements of the array
     *  exchange if the one before is bigger
     * step 2: exchange the two elements mentioned below.
     *  using a variable 'x' to save the smaller one
     *  using a variable 'j' to save the index of the element whose value is smaller than x.
     * step 3: using a loop to move the elements and get the value of j
     * step 4: insert the 'x' at 'j+1'
     */
    public void insertSort(int[] a){
        //  step 1
        for (int i=1; i < a.length; i++){
            if (a[i] < a[i-1]){
                int j;
                int x = a[i];
                a[i] = a[i-1];
                for (j = i-1; j >= 0 && x < a[j]; j--){
                    a[j+1] = a[j];
                }
                a[j+1] = x;
            }
        }
    }

    /**
     *  基本思想：
     *  算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组
     *  每组中记录的下标相差d.对每组中全部元素进行直接插入排序，然后再用一个较小的增量（d/2）对它进行分组，
     *  在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后，排序完成。
     */
    public void shellSort(){
        int[] a = {1,54,6,3,78,34,12,45,56,100};
        double d1 = a.length;
        int temp = 0;
        while (true){
            d1 = Math.ceil(d1/2);
            int d = (int) d1;
            for (int x=0; x < d; x++){
                for (int i=x=d; i<a.length; i+=d){
                    int j;
                    temp = a[i];
                    for (j = i-d; j>=0 && temp < a[j]; j-=d){
                        a[j+d] = a[j];
                    }
                    a[j+d] = temp;
                }
            }
            if (d == 1){
                break;
            }
        }
        for (int i=0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    /**
     *  基本思想：
     *  在要排序的一组数中，选出最小（或者最大）的一个数与第1个位置的数交换；
     *  然后在剩下的数当中再找最小（或者最大）的与第2个位置的数交换，
     *  依次类推，直到第n-1个元素（倒数第二个数）和第n个元素（最后一个数）比较为止。
     */
    public void simpleSelectSort(){
        int a[]={1,54,6,3,78,34,12,45};
        int position=0;
        for(int i=0;i<a.length;i++){
            int j=i+1;
            position=i;
            int temp=a[i];
            for(;j<a.length;j++){
                if(a[j]<temp){
                    temp=a[j];
                    position=j;
                }
            }
            a[position]=a[i];
            a[i]=temp;
        }
        print(a);
    }
    public void SelectSort(int r[],int n) {
        int i ,j , min ,max, tmp;
        for (i=1 ;i <= n/2;i++) {
            // 做不超过n/2趟选择排序
            min = i; max = i ; //分别记录最大和最小关键字记录位置
            for (j= i+1; j<= n-i; j++) {
                if (r[j] > r[max]) {
                    max = j ; continue ;
                }
                if (r[j]< r[min]) {
                    min = j ;
                }
            }
            //该交换操作还可分情况讨论以提高效率
            tmp = r[i-1]; r[i-1] = r[min]; r[min] = tmp;
            tmp = r[n-i]; r[n-i] = r[max]; r[max] = tmp;

        }
    }

    /**
     * bubbleSort
     */
    public void bubbleSort(int a[], int n){
        for(int i =0 ; i< n-1; ++i) {
            for(int j = 0; j < n-i-1; ++j) {
                if(a[j] > a[j+1])
                {
                    int tmp = a[j] ; a[j] = a[j+1] ;  a[j+1] = tmp;
                }
            }
        }
    }

    public void Bubble_1 (int r[], int n) {
        int i= n -1;  //初始时,最后位置保持不变
        while ( i> 0) {
            int pos= 0; //每趟开始时,无记录交换
            for (int j= 0; j< i; j++)
                if (r[j]> r[j+1]) {
                    pos= j; //记录交换的位置
                    int tmp = r[j]; r[j]=r[j+1];r[j+1]=tmp;
                }
            i= pos; //为下一趟排序作准备
        }
    }

    public void Bubble_2 (int r[], int n){
        int low = 0;
        int high= n -1; //设置变量的初始值
        int tmp,j;
        while (low < high) {
            for (j= low; j< high; ++j) //正向冒泡,找到最大者
                if (r[j]> r[j+1]) {
                    tmp = r[j]; r[j]=r[j+1];r[j+1]=tmp;
                }
            --high;                 //修改high值, 前移一位
            for ( j=high; j>low; --j) //反向冒泡,找到最小者
                if (r[j]<r[j-1]) {
                    tmp = r[j]; r[j]=r[j-1];r[j-1]=tmp;
                }
            ++low;                  //修改low值,后移一位
        }
    }
}
