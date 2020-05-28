import java.util.LinkedList;
import java.util.Queue;

public class TreeTest {

    static class Node {
        public char val;
        public Node left;
        public Node right;

        public Node(char val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    static Node build() {
        //通过build方法构建一棵树，返回树的根节点
        Node A = new Node('A');
        Node B = new Node('B');
        Node C = new Node('C');
        Node D = new Node('D');
        Node E = new Node('E');
        Node F = new Node('F');
        Node G = new Node('G');
        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        E.left = G;
        C.right = F;
        return A;
    }

    /*public static int treeSize = 0;
    public static void Size(Node root) {
        //计算结点个数
        if (root == null) {
            return;
        }
        treeSize++;
        leafSize(root.left);
        leafSize(root.right);
    }*/
    public static int size(Node root) {
        //计算结点个数
        if (root == null) {
            //空树里有0个结点
            return 0;
        }
        //整个树的结点 = 根结点个数 + 左子树个数 + 右子树个数
        return 1 + size(root.left) + size(root.right);
    }

    /*public static int leafSize = 0;
    public static void leafSize(Node root) {
        //计算叶子结点
        if (root == null) {
            return;
        }
        if(root.left == null || root.right == null) {
            //满足条件即为叶子结点
            leafSize++;
            return;
        }
        leafSize(root.left);
        leafSize(root.right);
    }*/
    public static int leafSize(Node root) {
        //计算叶子结点
        if (root == null) {
            return 0;
        }
        if(root.left == null && root.right == null) {
            //满足条件即为叶子结点
            return 1;
        }
        //当前叶子结点个数 = 左子树叶子结点个数 + 右子树叶子结点个数
        return leafSize(root.left) + leafSize(root.right);
    }

    public static int kLevelSize(Node root, int k) {
        //求第k层结点个数
        if (root == null || k < 1) {
            return 0;
        }
        if(k == 1) {
            return 1;
            //根节点一定为1（空树除外）
        }
        //第k层结点个数 = 其左子树的 k-1 层结点个数 + 其右子树的 k-1 层结点个数
        return kLevelSize(root.left, k-1) + kLevelSize(root.right, k-1);
    }

    /*public static Node result = null;
    public static void find(Node root, int toFind) {
        // 查找 val 所在结点，没有找到返回 null
        if (root == null ) {
            return;
        }
        if(root.val == toFind) {
            result = root;
            return;
        }
        find(root.left, toFind);
        find(root.right, toFind);

    }*/
    public static Node find(Node root, char toFind) {
        // 查找 val 所在结点，没有找到返回 null
        if (root == null) {
            return null;
        }
        if(root.val == toFind) {
            //判断根结点是否为toFind
            return root;
        }
        Node result = find(root.left, toFind);
        if(result != null) {
            return result;
        }
        return find(root.right, toFind);
    }

    public static void main(String[] args) {
        Node root = build();
        System.out.println(size(root));//7
        System.out.println(leafSize(root));//3
        System.out.println(kLevelSize(root, 3));//3

        Node result = find(root, 'G');
        System.out.println(result);
        //Node{val=G, left=null, right=null}
        Node result1 = find(root, 'C');
        System.out.println(result1);
        //Node{val=C, left=null, right=Node{val=F, left=null, right=null}}
    }
}
