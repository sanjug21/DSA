package Tree;

import java.util.Scanner;

public class BinaryTree {
    
    class Node{
        int val;
        Node left;
        Node right;
        Node(int val){
            this.val=val;
        }
    }
    private Node root;
    public  BinaryTree(){
        root=CreateTree();
    }
    Scanner sc=new Scanner(System.in);
    private Node CreateTree(){
        int item=sc.nextInt();
        Node nn=new Node(item);
        boolean hlc=sc.nextBoolean();
        if(hlc){
            nn.left=CreateTree();
        }
        boolean hrc=sc.nextBoolean();
        if(hrc){
            nn.right=CreateTree();
        }
        return nn;
    }
    public void Display(){
        displayTree(root);
    }
    public void displayTree(Node root){
        if(root==null)return;
        String s="";
        s="<---";
        s=s+root.val;
        s=s+"-->";
        if(root.left!=null){
            s=root.left.val+s;
        }else s="."+s;
        if(root.right!=null){
            s=s+root.right.val;
        }else s=s+".";
        System.out.println(s);
        displayTree(root.left);
        displayTree(root.right);
    }
    public static void main(String[] args) {
        
    }
}
