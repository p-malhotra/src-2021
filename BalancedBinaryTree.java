public class BalancedBinaryTree {

    static boolean isBalanced(BinaryNode root){
        if(root == null)
            return true;
        int leftH= root.left.getSize();
        int rightH= root.right.getSize();
        return leftH-rightH==1?true: false;
    }

    public static void main(String[] args) {
        int[] arr= new int[]{2,3,4,5,6,7,8,9};
        BinaryNode root= createMinBST(arr,0,arr.length);
        System.out.println("Is balanced " +isBalanced(root));

    }
    static BinaryNode createMinBST(int[] arr, int start, int end){
        if(end<start)
            return null;
        int mid=(end+start)/2;
        BinaryNode t=  new BinaryNode(arr[mid]);
        t.setLeftChild(createMinBST(arr, start,mid-1));
        t.setRightChild(createMinBST(arr,mid+1,end));
        return t;

    }
}
