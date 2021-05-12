import javax.swing.tree.TreeNode;

class BinaryNode {

    BinaryNode left;
    BinaryNode right;
    BinaryNode parent;
    int data;
    int size;

    BinaryNode(int d) {
        this.data = d;
    }

    void setLeftChild(BinaryNode left) {
        this.left = left;
        if (left != null)
            left.parent = this;

    }

    void setRightChild(BinaryNode right) {
        this.right = right;
        if (right != null)
            right.parent = this;

    }


    public void insertInOrder(int d){
        if(d<=data){
            if(left==null)
                setLeftChild(new BinaryNode(d));
            else
                insertInOrder(d);
        }
        else{
            if(right == null)
                setRightChild(new BinaryNode(d));
            else
                insertInOrder(d);
        }
        size++;
    }

    public boolean isBST(){
        if(left!= null && (left.data>this.data) &&!isBST())
            return false;
        if(right!= null && (right.data>this.data) &&!isBST())
            return false;

        return true;
    }
    public static boolean validateBst(BinaryNode tree) {
        // Write your code here.
        return validateBst(tree,null,null);
    }

    public static boolean validateBst(BinaryNode root,Integer low ,Integer high) {
        if(root==null)
            return true;

        if ((low != null && root.data < low) || (high != null && root.data >= high)) {
            return false;
        }
        return validateBst(root.left,low,root.data) && validateBst(root.right,root.data,high);
    }
    public int getSize(){
        int leftH= left != null? left.size:0;
        int rightH=right != null?right.size:0;
        return Math.max(leftH,rightH)+1;
    }

    public BinaryNode find(int d){
        if(d== data)
            return this;
        if(d<data)
            return left !=null? left.find(d): null;
        else
            return right != null? right.find(d): null;
    }

     static BinaryNode createMinimalBST(int arr[], int start, int end){
    if(end< start)
        return null;
    int mid = (end+start)/2;
        BinaryNode t= new BinaryNode(arr[mid]);
        t.setLeftChild(createMinimalBST(arr,start,mid-1));
        t.setRightChild(createMinimalBST(arr,mid+1,end));
        return t;
    }

    void printNode(){
        //TODO
    }
}
