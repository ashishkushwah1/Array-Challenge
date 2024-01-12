import java.util.*;
import java.util.LinkedList;

public class Solution {
  static class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
  }

  static Queue<Node> q = new LinkedList<>();
  static Node root = null;

  public static void BuildTree(String val) {
    Node newNode;
    if (val.equals("#")) {
      newNode = new Node(-1);
    } else
      newNode = new Node(Integer.parseInt(String.valueOf(val)));
    if (root == null)
      root = newNode;
    else if (q.peek().left == null)
      q.peek().left = newNode;
    else {
      q.peek().right = newNode;
      if (q.peek().left.data == -1)
        q.peek().left = null;
      if (q.peek().right.data == -1)
        q.peek().right = null;
      q.remove();
    }
    if (newNode.data != -1)
      q.add(newNode);
  }

public static Node lca(Node root,String s1, String s2){
  int n1 = Integer.parseInt(String.valueOf(s1));
  int n2 = Integer.parseInt(String.valueOf(s2));
  if(root==null || root.data==n1 || root.data==n2) return root;
  Node leftLca = lca(root.left,s1,s2);
  Node rightLca = lca(root.right,s1,s2);
  if(leftLca==null) return rightLca;
  if(rightLca==null) return leftLca;
  return root;

}
  public static void main(String[] args) {
    String strArr[] = new String[3];
    String s1 = "[12,5,9,6,2,0,8,#,#,7,4,#,#,#,#]";
    strArr[0] = s1;
    strArr[1] = "6";
    strArr[2] = "4";
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < strArr[0].length(); i++) {
      char ch = strArr[0].charAt(i);
      if (Character.isDigit(ch) || ch == '#')
        sb.append(ch);
      else if (ch == ',' || ch == ']') {
        BuildTree(sb.toString());
        sb.setLength(0);
      }
    }
    int ans = lca(root,strArr[1],strArr[2]).data;
    System.out.println(Integer.toString(ans));
  }
}
