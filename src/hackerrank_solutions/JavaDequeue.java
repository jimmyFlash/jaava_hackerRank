package hackerrank_solutions;

import java.util.*;

public class JavaDequeue {


    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        Deque deque = new ArrayDeque<>();
        int n = in.nextInt();
        int m = in.nextInt();
        int theMaxCount = Integer.MIN_VALUE;
        Set<Integer> hSet = new HashSet<>();
        for (int i = 0; i < n ; i++) {
            int num = in.nextInt();

            deque.add(num);
            hSet.add(num);

            if(deque.size() == m){

                //System.out.println("the deque: " + deque + ", the hastset: " + hSet);
                if(hSet.size() > theMaxCount) theMaxCount = hSet.size();
                int removeFirstElement  = (int) deque.pop();
                //System.out.println("removed item from deque " + removeFirstElement);
                   /*
                    explanation of the below statement :
                    Since it has already been iterated over it won't be re-added to the set when we add the
                    next number into the set and deque so if we were to remove it from the set it would give
                     us an incorrect set of numbers.
                    {1,2,3,3} que {1,2,3} set
                    remove 3 from both and add say 5 to the que/set we get
                    {5,1,2,3} que {5,1,2} set
                    meaning any set that needs that extra 3 in there will not be correct. so we check to
                    see if there is a duplicate of that number and leave it if there is. I think.
                   */
                if(!deque.contains(removeFirstElement)){
                    // System.out.println("the item NOT present in hashset: " + removeFirstElement);
                    hSet.remove(removeFirstElement);
                }
            }
        }
        System.out.println(theMaxCount);
    }
}

/*
Methods of Deque:

add(element): Adds an element to the tail.
addFirst(element): Adds an element to the head.
addLast(element): Adds an element to the tail.
offer(element): Adds an element to the tail and returns a boolean to explain if the insertion was successful.
offerFirst(element): Adds an element to the head and returns a boolean to explain if the insertion was successful.
offerLast(element): Adds an element to the tail and returns a boolean to explain if the insertion was successful.

iterator(): Returna an iterator for this deque.
descendingIterator(): Returns an iterator that has the reverse order for this deque.

push(element): Adds an element to the head.
pop(element): Removes an element from the head and returns it.
removeFirst(): Removes the element at the head.
removeLast(): Removes the element at the tail.

poll(): Retrieves and removes the head of the queue represented by this deque (in other words, the first element of this deque), or returns null if this deque is empty.
pollFirst(): Retrieves and removes the first element of this deque, or returns null if this deque is empty.
pollLast(): Retrieves and removes the last element of this deque, or returns null if this deque is empty.
peek(): Retrieves, but does not remove, the head of the queue represented by this deque (in other words, the first element of this deque), or returns null if this deque is empty.
peekFirst(): Retrieves, but does not remove, the first element of this deque, or returns null if this deque is empty.
peekLast(): Retrieves, but does not remove, the last element of this deque, or returns null if this deque is empty.

 */
