import java.util.ArrayList;

/**
 * Array Challenge:
 * Have the function ArrayChallenge(arr) take arr which represents a heap data structure and determine
 * whether or not it is a max heap.
 * A max heap has the property that all nodes in the heap are either greater than or equal to each of its children.
 * For example: if arr is [100,19,36,17] then this is a max heap and your program should return the string max.
 * If the input is not a max heap then your program should return a list of nodes in string format,
 * in the order that they appear in the tree, that currently do not satisfy the max heap property
 * because the child nodes are larger than their parent.
 * For example: if arr is [10,19,52,13,16] then your program should return 19,52.
 *
 * Another example: if arr is [10,19,52,104,14] then your program should return 19,52,104
 * Examples
 * Input: new int[] {73,74,75,7,2,107}
 * Output: 74,75,107
 * Input: new int[] {1,5,10,2,3,10,1}
 * Output: 5,10
 */
public class BinaryTreeMaxHeapCheck {

    public static int[] testArr = new int[] {10,19,52,13,16};

    public static String arrayChallenge(int[] arr){

        int length = arr.length ;
        ArrayList<Integer> notHeap = new ArrayList<>();

        System.out.println("Is it Max heap: " + isHeap( arr, 0, arr.length-1));

        /*
         when navigating a binary tree we know:
            - each branch node has minimum of 1 and MAX of 2 (sub branch/leaf nodes)
            - to navigate the node sub branch/leaf nodes we use the formula :
               left/right branch = 2 * (parent node pos) + ( 1 | 2 )
            - reversing  this formula for the tree length we get:
              ( array.length - 2 <max num branches per node> )/ 2 <max sub nodes per branch-node>
         */
        int binaryTreeHalf = (length - 2) / 2;
        System.out.println("half tree size: " + binaryTreeHalf + "\n");

        // Start from root and go till the last internal
        // node
        for (int i = 0 ; i <= binaryTreeHalf ; i++){

            System.out.println("        parent ( " + arr[i]  + " )");

            if(leftChildNode(i) < length) {
                System.out.println("       /             \\");
                System.out.print(" left node: " + arr[leftChildNode(i)]);
                if (arr[i] < arr[leftChildNode(i)]) {
                    notHeap.add(arr[leftChildNode(i)]);
                }
            }

            if(rightChildNode(i) < length) {

                System.out.print(" || right node:  " + arr[rightChildNode(i)]);
                if(arr[i]  < arr[rightChildNode(i)]){
                    notHeap.add(arr[rightChildNode(i)]);
                }
            }
            System.out.print("\n");
        }

        if(notHeap.size() > 0){
            return notHeap.toString();
        }
        return "max";
    }

    // Returns true if arr[i..n-1]
    // represents a max-heap
    static boolean isHeap(int[] arr, int i, int n) {
        // If a leaf node
        if (i >= (n - 2) / 2) {
            return true;
        }

        // If an internal node and
        // is greater than its
        // children, and same is
        // recursively true for the
        // children
        if (arr[i] >= arr[leftChildNode(i)]
                && arr[i] >= arr[rightChildNode(i)]
                && isHeap(arr, leftChildNode(i), n)
                && isHeap(arr, rightChildNode(i), n)) {

        }

        return arr[i] >= arr[leftChildNode(i)]
                && arr[i] >= arr[rightChildNode(i)]
                && isHeap(arr, leftChildNode(i), n)
                && isHeap(arr, rightChildNode(i), n);
    }

    private static int leftChildNode(int pos){
        return 2 * pos + 1;
    }

    private static int rightChildNode(int pos){
        return 2 * pos + 2;
    }

    public static void main(String[] args) {

//        Scanner sc = new Scanner(System.in);
//        String arrString = sc.nextLine();
        String checkMaxHeap = arrayChallenge(testArr);
        System.out.println("\nIs it max heap: " + checkMaxHeap.equalsIgnoreCase("max") +
                ", elements violating max heap: " +
                (checkMaxHeap.equalsIgnoreCase("max") ? "": checkMaxHeap));
    }
}
