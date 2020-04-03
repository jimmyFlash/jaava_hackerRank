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

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

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

    public abstract void accept(TreeVis visitor);

}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private TreeLeaf leaf;
    private ArrayList<TreeLeaf> children = new ArrayList<>();

    public int getResult() {
        int leafValue = 0;
        for (TreeLeaf child : children) {
            leafValue += child.getValue();
        }
        return leafValue;
    }

    public void visitNode(TreeNode node) {
        node.accept(this);
    }

    public void visitLeaf(TreeLeaf leaf) {
        this.leaf = leaf;
        this.leaf.accept(this);
        children.add(leaf);
    }
}

class ProductOfRedNodesVisitor extends TreeVis {

    private ArrayList<Tree> allRed = new ArrayList<>();

    public int getResult() {
        int prodcut = 1;
        for (Tree red : allRed){
            prodcut *= red.getValue();
            if(prodcut == 0){
                prodcut = 1;
            }
        }
        return prodcut;
    }

    public void visitNode(TreeNode node) {
        if(node.getColor() == Color.RED) allRed.add(node);
        node.accept(this);
    }

    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor() == Color.RED) allRed.add(leaf);
        leaf.accept(this);
    }
}

class FancyVisitor extends TreeVis {

    private ArrayList<TreeLeaf> greenLeaves = new ArrayList<>();
    private ArrayList<TreeNode> nodesAtEvenDepth = new ArrayList<>();
    private int diffCal = 0;
    public int getResult() {
        //implement this
        return Math.abs(diffCal);
    }

    public void visitNode(TreeNode node) {
        if(node.getDepth() % 2 == 0) nodesAtEvenDepth.add(node);
        node.accept(this);
    }

    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor() == Color.GREEN) greenLeaves.add(leaf);
        leaf.accept(this);
    }
}

public class JavaVisitorPattern {

    public static Tree solve() {

/*
5
4 7 2 5 12
0 1 0 0 1
1 2
1 3
3 4
3 5

 */
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, List<Integer>> branchMap = new HashMap<>();
        int depth = 0;
        int numOfNodes = sc.nextInt();
        int[] nodeSet = new int[numOfNodes];
        int[] nodeColors = new int[numOfNodes];

        // populate the array of node value (nodeSet) :  0 - 4
        // populate the array of node colors (nodeColors) : 5 - 9
        for (int i = 0 ; i < numOfNodes * 2 ; i++){
            if(i < numOfNodes){
                nodeSet[i] = sc.nextInt();
                //System.out.println("i: " + i + "," + nodeSet[i]);
            }else{
                nodeColors[ i - numOfNodes ] = sc.nextInt();
               // System.out.println("i - numOfNodes: " + (i - numOfNodes) + "," + nodeColors[ i - numOfNodes ]);
            }
        }


        sc.nextLine();
       /* while (sc.hasNextInt()){
            System.out.println("jjj: " + sc.nextInt());
        }*/

        for (int i = 0 ; i < 4 ; i++){
            String str = sc.nextLine();
            String[] treeComponent = str.split("\\s");
            int fromNode = Integer.parseInt(treeComponent[0]);
            int toNode = Integer.parseInt(treeComponent[1]);
            if(!branchMap.containsKey(fromNode)){
                List<Integer> branchConnection = new ArrayList<>();
                branchConnection.add(toNode);
                branchMap.put(fromNode, branchConnection);
            }else{

                if( !branchMap.get(fromNode).contains(toNode) ){
                    branchMap.get(fromNode)
                            .add(toNode);
                }
            }
        }
        TreeNode tn = null;
        TreeNode prevTn = null;
        for(Map.Entry<Integer, List<Integer>> mapping : branchMap.entrySet()){
            Integer edge = mapping.getKey();
            depth++;

             tn = new TreeNode(
                    nodeSet[edge -1],
                    nodeColors[edge - 1] == 1? Color.GREEN : Color.RED,
                    depth
            );
            System.out.println( "NODE --->, value: " + tn.getValue()
                    + ", color: " + tn.getColor()
                    + " @depth: " + tn.getDepth());
            if(prevTn != null){
                prevTn.addChild(tn);
                System.out.println( "SUB-NODE <-->," + " ADD-TO: " + prevTn.getValue());
            }

            List<Integer> connectedTo = mapping.getValue();
            prevTn = tn;
           for (Integer b : connectedTo){
               if(!branchMap.containsKey(b)){// make sure it's not a tree branch

                   TreeLeaf tl = new TreeLeaf(
                           nodeSet[b - 1],
                           nodeColors[b - 1] == 1 ? Color.GREEN : Color.RED,
                           tn.getDepth() + 1);
                   tn.addChild(tl);
                   System.out.println( "LEAF **, value: " + tl.getValue()
                           + ", color: " + tl.getColor()
                           + " @depth: " + tl.getDepth()
                           + " ADD-TO: " + tn.getValue());
               }
           }
        }

        sc.close();
        return tn;
    }


    public static void main(String[] args){
        Tree root = solve();
        /*
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
    */
    }

}
