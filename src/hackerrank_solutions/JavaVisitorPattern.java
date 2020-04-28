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
//            System.out.println(child.getClass() + "," + child.getValue());
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
    int leafValue = 0;
    public int getResult() {
        return leafValue;
    }

    public void visitNode(TreeNode node) {
    }

    public void visitLeaf(TreeLeaf leaf) {
//        System.out.println(leaf.getValue());
        leafValue += leaf.getValue();
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

    }

    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor() == Color.RED) allRed.add(leaf);

    }
}

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

    static String[] nodeSet;
    static  String[] nodeColors ;

    static HashMap<Integer, HashSet<Integer>> branchMap = new HashMap<>();
    static boolean [] checkedNode;
    static int numOfNodes;

    public static Tree solve() {
        String test = "5\n" +
                "4 7 2 5 12\n" +
                "0 1 0 0 1\n" +
                "1 2\n" +
                "1 3\n" +
                "3 4\n" +
                "3 5";


        Scanner sc = new Scanner(test);
        numOfNodes = Integer.parseInt(sc.nextLine());

         checkedNode = new boolean[numOfNodes];
         nodeSet = sc.nextLine().split("\\s");
         nodeColors = sc.nextLine().split("\\s");


         for (int i = 0; i < numOfNodes ; i++){
             branchMap.put(i, new HashSet<>());
         }
         while (sc.hasNextLine()){
            String str = sc.nextLine();
            String[] treeComponent = str.split("\\s");
            int fromNode = Integer.parseInt(treeComponent[0]) - 1;
            int toNode = Integer.parseInt(treeComponent[1]) - 1;
            // System.out.println("fromNode/toNode " + fromNode + ", " + toNode);
             branchMap.get(fromNode).add(toNode);
             branchMap.get(toNode).add(fromNode);
         }
         sc.close();
        return buildTree(0);
    }

    public static Tree buildTree(int vertex){
        if(branchMap.get(vertex).isEmpty()) {// case where tree has no branches
            return new TreeLeaf(Integer.parseInt(nodeSet[vertex]),
                    nodeColors[vertex].equals("1") ? Color.GREEN : Color.RED,
                    0);
        }else{
            return transverseTree(vertex, 0);
        }
    }


    public static Tree transverseTree(int node, int depth){
        checkedNode[node] = true;
        //ArrayList<Tree> childs = new ArrayList<>();
        ArrayList<Integer> childBranches = new ArrayList<>();

        HashSet<Integer> connectedNodes = branchMap.get(node);
//        System.out.println("-> " + connectedNodes);
        for(Integer e : connectedNodes) {
            //System.out.println("iteration " + e);
            if (!checkedNode[e]) {
                //childs.add(transverseTree(e, depth + 1));
                childBranches.add(e);
            }
        }

        if(childBranches.isEmpty()){
           // System.out.println("create leaf");
            return new TreeLeaf(Integer.parseInt(nodeSet[node]),
                    nodeColors[node].equals("1") ? Color.GREEN : Color.RED, depth);
        }else{
            //System.out.println("create tree");
            TreeNode treeNode = new TreeNode(Integer.parseInt(nodeSet[node]),
                    nodeColors[node].equals("1") ? Color.GREEN : Color.RED, depth);
            for(Integer s : childBranches)
                if(s != node) treeNode.addChild(transverseTree(s, depth + 1));
            return treeNode;
        }

       /*
       if(childs.isEmpty()) {
            return new TreeLeaf(Integer.parseInt(nodeSet[node]),
                    nodeColors[node].equals("1") ? Color.GREEN : Color.RED, depth);
        } else{
            TreeNode treeNode = new TreeNode(Integer.parseInt(nodeSet[node]),
                    nodeColors[node].equals("1") ? Color.GREEN : Color.RED, depth);
            for(Tree child : childs)
                treeNode.addChild(child);
            return treeNode;
        }
        */
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
/*

    static int N;
    static HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
    static int[] values;
    static int[] colors;
    static boolean [] mark;


    public static Tree solve() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.valueOf(br.readLine());
            values = new int[N];
            colors = new int[N];
            int parent, child;
            String[] chunks = br.readLine().split(" ");
            for(int i = 0; i < N; i++ ){
map.put(i, new HashSet<Integer>());
                values[i] = Integer.valueOf(chunks[i]);
            }
            chunks = br.readLine().split(" ");
            for(int i = 0; i < N; i++ )
                colors[i] = Integer.valueOf(chunks[i]);

            for(int i = 0, length = N - 1; i < length; i++){
                chunks = br.readLine().split(" ");
                parent = Integer.valueOf(chunks[0]) - 1;
                child = Integer.valueOf(chunks[1]) - 1;

                map.get(parent).add(child);
                map.get(child).add(parent);

            }
            return dfs(0);
        } catch (IOException ex) {

        }
        return null;
    }

     public static Tree dfs(int vertex){
        if(map.get(vertex).isEmpty())
            return new TreeLeaf(values[vertex], Color.values()[colors[vertex]], 0);
        else{
            mark = new boolean[N];
            return runDfs(vertex, 0);
        }
    }

    public static Tree runDfs(int vertex, int depth){
        mark[vertex] = true;
        ArrayList<Tree> childs = new ArrayList<>();
        for(Integer e : map.get(vertex))
            if(!mark[e])
                childs.add(runDfs(e, depth + 1));
        if(childs.isEmpty())
            return new TreeLeaf(values[vertex], Color.values()[colors[vertex]], depth);
        else{
            TreeNode node = new TreeNode(values[vertex], Color.values()[colors[vertex]], depth);
            for(Tree child : childs)
                node.addChild(child);
            return node;
        }
    }

 */