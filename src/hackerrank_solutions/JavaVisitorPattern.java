package hackerrank_solutions;

/*
5
4 7 2 5 12
0 1 0 0 1
1 2
1 3
3 4
3 5
 */

import java.util.*;

enum Color {
    RED, GREEN
}

/**
 * base tree abstract class
 */
abstract class Tree {

    private final int value; // node value
    private final Color color; // node color either red/green
    private final int depth; // node depth in tree

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    // visitor pattern implementation
    public abstract void accept(TreeVis visitor);

}

/**
 * tree node
 * each tree node is a branch that has children
 */
class TreeNode extends Tree {

    // child nodes belonging to this tree spawning form this tree branch
    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
//            System.out.println(child.getClass() + "," + child.getValue());
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

/**
 * leaf nodes
 * leaf nodes have no children
 */
class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

/**
 * tree visitor abstract class
 */
abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}

/**
 * tree visitor that sums the number of leaves in a tree branch
 * implements the TreeVis interface
 */
class SumInLeavesVisitor extends TreeVis {
    int leafValue = 0;
    public int getResult() {
        return leafValue;
    }

    // redundant since this applies to leaves
    public void visitNode(TreeNode node) {
    }

    public void visitLeaf(TreeLeaf leaf) {
//        System.out.println(leaf.getValue());
        leafValue += leaf.getValue();
    }
}

/**
 * tree visitor that calculates the product of
 * all red node values in either branch or leaf
 * implements the TreeVis interface
 */
class ProductOfRedNodesVisitor extends TreeVis {

    long result=1;

    // limit on integer
    final int M=1_000_000_007; // modul

    public int getResult(){
        return (int)result;
    }

    public void visitNode(TreeNode node){
        if(node.getColor()==Color.RED){
            result=(result*node.getValue()) % M;
        }
    }
    public void visitLeaf(TreeLeaf leaf){
        if(leaf.getColor()==Color.RED){
            result=(result * leaf.getValue()) % M;
        }
    }
}

/**
 * tree visitor that:
 * calculates the sum of values for all nodes in a tree branch that are at even depth
 * calculates the sum of values for all green nodes in a leaf node
 * then subtracts even tree nodes from the green leaf nodes value
 */
class FancyVisitor extends TreeVis {

    private int sumNodesEvnDepth = 0;
    private int sumGrnLeaves = 0;

    public int getResult() {
        int diffCal = sumNodesEvnDepth - sumGrnLeaves;
        return Math.abs(diffCal);
    }

    public void visitNode(TreeNode node) {
        if(node.getDepth() % 2 == 0) sumNodesEvnDepth += node.getValue();
    }

    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor() == Color.GREEN) sumGrnLeaves += leaf.getValue();

    }
}

public class JavaVisitorPattern {

    static int[] nodeSet;
    static Color[] nodeColors ;

    static HashMap<Integer, HashSet<Integer>> branchMap;
    static boolean [] checkedNode;
    static int numOfNodes;

    public static Tree solve() {
        String test = "5\n" +      // number of nodes
                "4 7 2 5 12\n" +  // node values
                "0 1 0 0 1\n" +   // node colors
                "1 2\n" +         // nod mapping node 1 <-- 2
                "1 3\n" +        // nod mapping node 1 <-- 3
                "3 4\n" +       // nod mapping node 3 <-- 4
                "3 5";          // nod mapping node 3 <-- 5


        Scanner sc = new Scanner(test);
        numOfNodes = Integer.parseInt(sc.nextLine());

         checkedNode = new boolean[numOfNodes]; // nodes that have been checked
         nodeSet = new int[numOfNodes]; // all nodes array
         nodeColors = new Color[numOfNodes]; // all node colors array
         branchMap = new HashMap<>(numOfNodes); // dictionary map of parent node value and its child nodes

        for (int i = 0; i < numOfNodes; i++) {
            nodeSet[i] = sc.nextInt(); // store node values in array
        }
        for (int i = 0; i < numOfNodes; i++) {
            nodeColors[i] = sc.nextInt() == 0 ? Color.RED : Color.GREEN; // store node colors 0 or 1 only
        }

        for (int i = 0; i < numOfNodes - 1; i++) {
            int u = sc.nextInt(); // left node
            int v = sc.nextInt(); // right node

//         Edges are undirected: Add 1st direction
            HashSet<Integer> uNeighbors = branchMap.get(u); // get the left node neighbors list if exists
            // create a new hashset for each node if not found neighbors if not found or store if one is already created
            if (uNeighbors == null) {
                uNeighbors = new HashSet<>();
                branchMap.put(u, uNeighbors);
            }
            uNeighbors.add(v); // add the right node to set of nodes adjacent to left node

//         Edges are undirected: Add 2nd direction
            HashSet<Integer> vNeighbors = branchMap.get(v); // get the right node neighbors list if exists
            if (vNeighbors == null) {
                vNeighbors = new HashSet<>();
                branchMap.put(v, vNeighbors);
            }
            vNeighbors.add(u); // add the left node to set of nodes adjacent to right node
        }
        sc.close();

        //         Handle 1-node tree
        if (numOfNodes == 1) {
            return new TreeLeaf(nodeSet[0], nodeColors[0], 0); // return a TreeLeaf instance
        }
        //         Create Tree
        TreeNode root = new TreeNode(nodeSet[0], nodeColors[0], 0);
        addChildren(root, 1);
        return root;
    }

    // Recursively adds children of a TreeNode
    private static void addChildren(TreeNode parent, Integer parentNum) {
//         Get HashSet of children and loop through them
        for (Integer treeNum : branchMap.get(parentNum)) {
            HashSet<Integer> childNodesOfCurrent = branchMap.get(treeNum);
            childNodesOfCurrent.remove(parentNum); // removes the opposite arrow direction reference to parent node

            Tree tree;
//         Add child
            boolean childHasChild = !childNodesOfCurrent.isEmpty();
            if (childHasChild) { // it's a branch
                tree = new TreeNode(nodeSet[treeNum - 1],
                                    nodeColors[treeNum - 1],
                              parent.getDepth() + 1);
            } else { // it's a leaf
                tree = new TreeLeaf(nodeSet[treeNum - 1],
                                    nodeColors[treeNum - 1],
                              parent.getDepth() + 1);
            }
            parent.addChild(tree); // update the parent node with new child 

//         Recurse if necessary
            if (tree instanceof TreeNode) {
                addChildren((TreeNode) tree, treeNum);
            }
        }
    }






    public static void main(String[] args){
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }

}
