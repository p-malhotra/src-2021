package practice;

import java.util.*;

public class NTreeNode {
    /**
     * he root of the Binary Tree is the Root of the Generic Tree.
     * The left child of a NTreeNode in the Generic Tree is the Left child of that NTreeNode in the Binary Tree.
     * The right sibling of any NTreeNode in the Generic Tree is the Right child of that NTreeNode in the Binary Tree.
     */
    int val;
    List<NTreeNode> children;

    NTreeNode() {
    }

    NTreeNode(int n, int data) {
        children = new ArrayList<>(n);
        this.val = data;
    }
    NTreeNode(int data) {
        this.val = data;
    }
    static void inorder(NTreeNode NTreeNode)
    {
        if (NTreeNode == null)
            return;
        // Total children count
        int total = NTreeNode.children.size();
        // All the children except the last
        for (int i = 0; i < total - 1; i++)
            inorder(NTreeNode.children.get(i));

        // Print the current NTreeNode's data
        System.out.print("" + NTreeNode.val + " ");

        // Last child
        inorder(NTreeNode.children.get(total - 1));
    }
    static void preOrder(NTreeNode root) {
        // Stack to store the NTreeNodes
        Stack<NTreeNode> NTreeNodes = new Stack<>();
        // push the current NTreeNode onto the stack
        NTreeNodes.push(root);
        // Loop while the stack is not empty
        while (!NTreeNodes.isEmpty()) {
            // Store the current NTreeNode and pop
            // it from the stack
            NTreeNode curr = NTreeNodes.pop();

            // Current NTreeNode has been travarsed
            if (curr != null) {
                System.out.print(curr.val + " ");

                // Store all the childrent of
                // current NTreeNode from right to left.
                for (int i = curr.children.size() - 1; i >= 0; i--) {
                    NTreeNodes.add(curr.children.get(i));
                }
            }
        }
    }
    public NTreeNode cloneTree(NTreeNode r) {
        if (r == null)
            return null;
        NTreeNode new_r = new NTreeNode(r.val);
        for (NTreeNode child: r.children)
            new_r.children.add(cloneTree(child));
        return new_r;
    }
    public NTreeNode cloneTreeIT(NTreeNode root) {
        Map<NTreeNode,NTreeNode> map = new HashMap<NTreeNode,NTreeNode>();
        Queue<NTreeNode> q = new LinkedList<NTreeNode>();
        if(root!=null)q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            while(size-->0){
                NTreeNode node = q.poll();
                NTreeNode clonedNode = map.getOrDefault(node, new NTreeNode(node.val));
                for(NTreeNode child : node.children){
                    q.offer(child);
                    NTreeNode clonedChild = map.getOrDefault(child, new NTreeNode(child.val));
                    clonedNode.children.add(clonedChild);
                    map.put(child,clonedChild);
                }
                map.put(node,clonedNode);
            }
        }
        return map.get(root);
    }
}
