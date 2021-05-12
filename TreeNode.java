package practice;

import java.util.*;

public class TreeNode {
    int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1==null )
            return root2;
        if(root2 == null)
            return root1;
        root1.val +=root2.val;
        root1.left=mergeTrees( root1.left,  root2.left);
        root1.right=mergeTrees( root1.right,  root2.right);
        return root1;

    }
    public TreeNode mergeTreesIt(TreeNode root1, TreeNode root2) { // time o(n) space o(1)
        if (root1 == null)
            return root2;
        if (root2 == null)
            return root1;
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{root1, root2});
        while (!stack.isEmpty()) {
            TreeNode[] a = stack.pop();
            if (a[0] == null || a[1] == null)
                continue;
            a[0].val += a[1].val;
            if (a[0].left == null)
                a[0].left = a[1].left;
            else
                stack.push(new TreeNode[]{a[0].left, a[1].left});
            if (a[0].right == null)
                a[0].right = a[1].right;
            else
                stack.push(new TreeNode[]{a[0].right, a[1].right});

        }
        return root1;
    }
        /**  Time: O(logN)  space O(H)
         */
    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null)
            return null;
        if(val<root.val)
            return searchBST( root.left,  val) ;
        else if(val>root.val)
            return  searchBST( root.right,  val) ;
        return root;
    }
    public TreeNode searchBSTIT(TreeNode root, int val) { //time O(h) space )(1)
        while (root != null && val != root.val)
            root = val < root.val ? root.left : root.right;
        return root;
    }
    public boolean isUnivalTree(TreeNode root) {
        if(root==null)
            return true;
        return (((root.left == null ) || (root.left !=null && (root.val==root.left.val && isUnivalTree(root.left)))) && (((root.right == null ) || (root.right != null && ( root.val== root.right.val && isUnivalTree(root.right))))));

    }
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;

        TreeNode left= root.left;
        TreeNode right =root.right;

        root.left=invertTree( right);
        root.right=invertTree( left);
        return root;

    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null)
            return true;
        if(p==null || q== null)
            return false;
        return (p.val == q.val) && isSameTree( p.left,  q.left) && isSameTree( p.right,  q.right);

    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res=  new ArrayList<>();
        helper(root, res,"");
        return res;

    }
    void helper(TreeNode node, List<String> res, String tmp){
        if(node !=null)
        {
            tmp+= Integer.toString(node.val);
            if(node.left== null && node.right == null)
                res.add(tmp);
            else{
                tmp+="->";
                helper(node.left,res,tmp);
                helper(node.right,res,tmp);

            }
        }
    }
    public int findTilt(TreeNode root) {
        return helper(root)[0];
    }

    // [0] is the tilt of the entire tree rooted at currNode
// [1] is the sum of all values in the tree rooted at currNode
    private int[] helper(TreeNode currNode) {

        if (currNode == null) return new int[]{0, 0};

        int[] left = helper(currNode.left);
        int[] right = helper(currNode.right);

        // tiltOfCurrNode = Math.abs(sumOfAllValuesInLeftSubtree - sumOfAllValuesInRightSubtree);
        // totalTilt = tiltOfCurrNode + totalTiltOfLeftSubtree + totalTiltOfRightSubtree;             // total tilt of tree rooted at currNode
        int totalTilt = Math.abs(left[1] - right[1]) + left[0] + right[0];

        // sumOfAllValues = currNode.val + sumOfAllValuesInLeftSubtree + sumOfAllValuesInRightSubtree;     // sum of all values in tree rooted at currNode
        int sumOfAllValues = currNode.val + left[1] + right[1];

        return new int[]{totalTilt, sumOfAllValues};
    }
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root == null)
            return false;
        Queue<TreeNode> q= new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode parentX=null;
            TreeNode parentY= null;
            int size = q.size();
            for(int i=0;i< size;i++){
                TreeNode node= q.poll();
                if(node.left != null){
                    if(root.left.val==x) parentX=root.left;
                    else if(root.left.val==y) parentY= root.right;
                    q.offer(root.left);
                }
                if(node.right != null){
                    if(root.right.val==x) parentX=root.left;
                    else if(root.right.val==y) parentY= root.right;
                    q.offer(root.right);
                }
            }
            if(parentX !=null && parentY != null) return parentX !=parentY;
            if(parentX !=null || parentY != null) return false;

        }

        return false;
    }
    //TODO find depth of node;
    public int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[1];
        height(root, diameter);
        return diameter[0];
    }

    private int height(TreeNode node, int[] diameter) {
        if (node == null) {
            return 0;
        }
        int lh = height(node.left, diameter);
        int rh = height(node.right, diameter);
        diameter[0] = Math.max(diameter[0], lh + rh);
        return 1 + Math.max(lh, rh);
    }
    public int diameterOfBinaryTree1(TreeNode root) {
        int res = 0;
        if(root == null) return res;

        // int cur = Math.max(maxDepth(root.left), maxDepth(root.right)) + Math.min(maxDepth(root.left), maxDepth(root.right));
        int cur = maxDepth(root.left) + maxDepth(root.right);
        int left = diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);

        return Math.max(cur, Math.max(left, right));
    }
    public int maxDepthIt(TreeNode root){
        if(root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;
        Deque<TreeNode> q= new ArrayDeque<>();
        q.offer(root);
        int depth =0;
        while(!q.isEmpty()){
            int size=q.size();
            depth++;
            for(int i=0;i<size;i++){
                TreeNode node=q.poll();
                if(node.left !=null)
                    q.offer(node.left);
                if(node.right !=null)
                    q.offer(node.right);
            }
        }
        return depth;

    }
    public void inorder(TreeNode r, List<Integer> nums, Queue<Integer> heap, int k) {
        if (r == null)
            return;

        inorder(r.left, nums, heap, k);
        heap.add(r.val);
        if (heap.size() > k)
            heap.remove();
        inorder(r.right, nums, heap, k);
    }

    /**
     * Time O(nlog(k)) space o(k+h)
     */
    //
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> nums = new ArrayList();

        // init heap 'less close element first'
        Queue<Integer> heap = new PriorityQueue<>((o1, o2) -> Math.abs(o1 - target) > Math.abs(o2 - target) ? -1 : 1);
        inorder(root, nums, heap, k);
        return new ArrayList<>(heap);
    }
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root != null) {
            res.add(root.val);
            getBounds(root.left, res, true, false);
            getBounds(root.right, res, false, true);
        }
        return res;
    }
    private void getBounds(TreeNode node, List<Integer> res, boolean lb, boolean rb) {
        if (node == null) return;
        if (lb) res.add(node.val);
        if (!lb && !rb && node.left == null && node.right == null) res.add(node.val);
        getBounds(node.left, res, lb, rb && node.right == null);
        getBounds(node.right, res, lb && node.left == null, rb);
        if (rb) res.add(node.val);
    }
    public List<Integer> boundaryOfBinaryTree1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;
        //add root.
        res.add(root.val);
        //add left boundary
        formLeftBoundary(root.left, res);
        //add left leaves
        addLeaves(root.left, res);
        //add right leaves
        addLeaves(root.right, res);
        //add right boundary
        formRightBoundary(root.right, res);
        return res;
    }
    private void formLeftBoundary(TreeNode root, List<Integer> res){
        if(root == null)
            return;
        if(root.left != null){ //only if value is not null, it should be put to the arraylist.
            res.add(root.val);
            formLeftBoundary(root.left, res);
        } else if(root.right != null){
            res.add(root.val);
            formLeftBoundary(root.right, res);
        }
    }
    private void formRightBoundary(TreeNode root, List<Integer> res){
        if(root == null)
            return;
        if(root.right != null){ //only if value is not null, it should be put to the arraylist.
            formRightBoundary(root.right, res);
            res.add(root.val);
        } else if(root.left != null){
            formRightBoundary(root.left, res);
            res.add(root.val);
        }
    }
    private void addLeaves(TreeNode root, List<Integer> res){
        if(root == null)
            return;
        addLeaves(root.left, res);
        if(root.left == null && root.right == null)
            res.add(root.val);
        addLeaves(root.right, res);
    }
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return transverse(s,t);
    }
    public boolean transverse(TreeNode s, TreeNode t){
        return s !=null && (equals(s,t) || transverse(s.left,t) || transverse(s.right,t));
    }
    public boolean equals(TreeNode s, TreeNode t){
        if(s==null && t== null)
            return true;
        if(s== null || t==null)
            return false;
        return s.val ==t.val && equals(s.left,t.left) && equals(s.right,t.right);


    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //return left == null ? right : right == null ? left : root;
        //result
        if(left == null) {
            return right;
        }
        else if(right == null) {
            return left;
        }
        else { //both left and right are not null, we found our result
            return root;
        }
    }
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        if (Math.abs(height(root.left)-height(root.right)) <= 1)
            return (isBalanced(root.left) && isBalanced(root.right));
        return false;
    }
    public int height(TreeNode root) {
        if (root == null)
            return 0;
        int left = height(root.left);
        int right= height(root.right);
        return (Math.max(left,right)+1);

    }
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left == null) {
            return root;
        }

        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;   // node 2 left children
        root.left.right = root;         // node 2 right children
        root.left = null;
        root.right = null;
        return newRoot;
    }
    public int closestValue(TreeNode root, double target) {
        int val, closest = root.val;
        while (root != null) {
            val = root.val;
            closest = Math.abs(val - target) < Math.abs(closest - target) ? val : closest;
            root =  target < root.val ? root.left : root.right;
        }
        return closest;
    }
      public int kthSmallest(TreeNode root, int k) { // BST itself could be altered
        Stack<TreeNode> st = new Stack<>();

        while (root != null) {
            st.push(root);
            root = root.left;
        }

        while (k != 0) {
            TreeNode n = st.pop();
            k--;
            if (k == 0) return n.val;
            TreeNode right = n.right;
            while (right != null) {
                st.push(right);
                right = right.left;
            }
        }

        return -1; // never hit if k is valid
    }
      public int findKthLargestValueInBst(TreeNode tree, int k) {
        Stack<TreeNode> st = new Stack<>();

        while (tree != null) {
            st.push(tree);
            tree = tree.right;
        }

        while (k != 0) {
            TreeNode n = st.pop();
            k--;
            if (k == 0) return n.val;
            TreeNode le = n.left;
            while (le != null) {
                st.push(le);
                le = le.right;
            }
        }
        return -1;
    }
}
