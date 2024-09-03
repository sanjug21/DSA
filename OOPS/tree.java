package OOPS;

import java.util.*;
public class tree {
   static class Node{
        Node left;
        Node right;
        int data;
        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    static class BinaryTree{
        static int idx=-1;
        public Node buildTree(int nodes []){
            idx++;
            if(nodes[idx]==-1){
                return null;
            }
            Node nn=new Node(nodes[idx]);
            nn.left=buildTree(nodes);
            nn.right=buildTree(nodes);
            return nn;
        }}
        public static void preorder(Node root){
            if(root==null){
                
                return;
            }
            System.out.print(root.data+" ");
            preorder(root.left);
            preorder(root.right);
    }
    public static void inorder(Node root) {
       if(root==null){
                
                return;
        } 
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);        
    }
    public static void postorder(Node root) {
      if(root==null){
                return;
        } 
        inorder(root.left);
        inorder(root.right);
         System.out.print(root.data+" ");   
    }
    public static void orderlevel(Node root){
        if(root==null){
            return;
        }
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node curr=q.remove();
            if(curr==null){
                System.out.print("   ");
                if(q.isEmpty()){
                    break;
                }
                else q.add(null);
            }
            else{
                System.out.print(curr.data+" ");
                if(curr.left!=null){
                    q.add(curr.left);
                }
                if(curr.right!=null){
                    q.add(curr.right);
                }
            }
        }
    }
    public static int height(Node root) {
      if(root==null) {
        return 0;
      } 
      int lh=height(root.left);
      int rh=height(root.right);
      return Math.max(lh,rh)+1;

    }

    public static void main(String [] args){
        int nodes[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree tree=new BinaryTree();
         Node root=tree.buildTree(nodes);
        // System.out.println(root.data);
        // preorder(root);
        // System.out.println();
        // inorder(root);
        // System.out.println();
        // postorder(root);
        // System.out.println();
        orderlevel(root);
       // System.out.println("\n"+"height is "+height(root));

    }
}
