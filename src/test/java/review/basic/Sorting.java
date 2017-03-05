package review.basic;

/**
 * Created by Vincent on 2017/3/5.
 * 排序算法 Review
 */
public class Sorting {
    private static int[] beforeSorting = {3,1,5,7,2,4,9,6,10,8};

    public static void main(String[] args){
        Sorting sort = new Sorting();
        sort.insertSort(beforeSorting);
        sort.print(beforeSorting);
    }
    /**
     * 按序输出
     */
    public void print(int[] a){
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
}
