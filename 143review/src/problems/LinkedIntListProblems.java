package problems;

import datastructures.LinkedIntList;
// Checkstyle will complain that this is an unused import until you use it in your code.
import datastructures.LinkedIntList.ListNode;

/**
 * See the spec on the website for example behavior.
 *
 * REMEMBER THE FOLLOWING RESTRICTIONS:
 * - do not call any methods on the `LinkedIntList` objects.
 * - do not construct new `ListNode` objects for `reverse3` or `firstToLast`
 *      (though you may have as many `ListNode` variables as you like).
 * - do not construct any external data structures such as arrays, queues, lists, etc.
 * - do not mutate the `data` field of any node; instead, change the list only by modifying
 *      links between nodes.
 */

public class LinkedIntListProblems {

    /**
     * Reverses the 3 elements in the `LinkedIntList` (assume there are exactly 3 elements).
     */
    public static void reverse3(LinkedIntList list) {
        // TODO replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        ListNode first = list.front;
        ListNode second = first.next;
        ListNode third = second.next;
        third.next = second;
        second.next = first;
        first.next = null;
        list.front = third;
    }

    /**
     * Moves the first element of the input list to the back of the list.
     */
    public static void firstToLast(LinkedIntList list) {
        // TODO replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        ListNode first = list.front;

        if (null == first || null == first.next) {
            return;
        }

        ListNode last = first;
        while (null != last.next) {
            last = last.next;
        }
        list.front = first.next;
        first.next = null;
        last.next = first;
    }

    /**
     * Returns a list consisting of the integers of a followed by the integers
     * of n. Does not modify items of A or B.
     */
    public static LinkedIntList concatenate(LinkedIntList a, LinkedIntList b) {
        // Hint: you'll need to use the 'new' keyword to construct new objects.
        // TODO replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        ListNode front = null;
        ListNode newCurrent = null;

        if (null != a.front) {
            ListNode current = a.front;
            front = new ListNode(current.data);
            newCurrent = front;
            while (null != current.next) {
                current = current.next;
                newCurrent.next = new ListNode(current.data);
                newCurrent = newCurrent.next;
            }
        }

        if  (null != b.front) {
            ListNode current = b.front;
            if (null == front) {
                front = new ListNode(current.data);
                newCurrent = front;
            } else {
                newCurrent.next = new ListNode(current.data);
                newCurrent = newCurrent.next;
            }

            while (null != current.next) {
                current = current.next;
                newCurrent.next = new ListNode(current.data);
                newCurrent = newCurrent.next;
            }
        }

        return new LinkedIntList(front);
    }
}
