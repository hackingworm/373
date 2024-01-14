package problems;

import datastructures.IntTree;
// Checkstyle will complain that this is an unused import until you use it in your code.
import datastructures.IntTree.IntTreeNode;

/**
 * See the spec on the website for tips and example behavior.
 *
 * Also note: you may want to use private helper methods to help you solve these problems.
 * YOU MUST MAKE THE PRIVATE HELPER METHODS STATIC, or else your code will not compile.
 * This happens for reasons that aren't the focus of this assignment and are mostly skimmed over in
 * 142 and 143. If you want to know more, you can ask on the discussion board or at office hours.
 *
 * REMEMBER THE FOLLOWING RESTRICTIONS:
 * - do not call any methods on the `IntTree` objects
 * - do not construct new `IntTreeNode` objects (though you may have as many `IntTreeNode` variables
 *      as you like).
 * - do not construct any external data structures such as arrays, queues, lists, etc.
 * - do not mutate the `data` field of any node; instead, change the tree only by modifying
 *      links between nodes.
 */

public class IntTreeProblems {

    private static int depthSum(IntTreeNode node, int level) {
        if (null == node) {
            return 0;
        }

        return node.data * level + depthSum(node.left, level + 1) + depthSum(node.right, level + 1);
    }
    /**
     * Computes and returns the sum of the values multiplied by their depths in the given tree.
     * (The root node is treated as having depth 1.)
     */
    public static int depthSum(IntTree tree) {
        // TODO replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        return depthSum(tree.overallRoot, 1);
    }

    private static void removeLeaves(IntTreeNode parent) {
        if (null != parent.left) {
            if (null == parent.left.left && null == parent.left.right) {
                parent.left = null;
            } else {
                removeLeaves(parent.left);
            }
        }

        if (null != parent.right) {
            if (null == parent.right.left && null == parent.right.right) {
                parent.right = null;
            } else {
                removeLeaves(parent.right);
            }
        }
    }
    /**
     * Removes all leaf nodes from the given tree.
     */
    public static void removeLeaves(IntTree tree) {
        // TODO replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        if (null == tree.overallRoot) {
            return;
        }

        if (null == tree.overallRoot.left && null == tree.overallRoot.right) {
            tree.overallRoot = null;
        } else {
            removeLeaves(tree.overallRoot);
        }
    }

    private static IntTreeNode trim(IntTreeNode parent, int min, int max) {
        if (null != parent.left) {
            parent.left = trim(parent.left, min, max);
        }

        if (null != parent.right) {
            parent.right = trim(parent.right, min, max);
        }

        if (min > parent.data || max < parent.data) {
            if (null == parent.left) {
                return parent.right;
            }

            if (null == parent.right) {
                return parent.left;
            }

            if (null == parent.left.right) {
                parent.left.right = parent.right;
                return parent.left;
            }

            if (null == parent.right.left) {
                parent.right.left = parent.left;
                return parent.right;
            }

            parent.right.right.left = parent.right.left;
            parent.right.left = parent.left;
            return parent.right;
        }

        return parent;
    }
    /**
     * Removes from the given BST all values less than `min` or greater than `max`.
     * (The resulting tree is still a BST.)
     */
    public static void trim(IntTree tree, int min, int max) {
        // TODO replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        if (null == tree.overallRoot) {
            return;
        }

        tree.overallRoot = trim(tree.overallRoot, min, max);
    }
}
