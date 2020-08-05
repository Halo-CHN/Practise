package io.github.halochn.leetcode;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

    }

   static   class Solution {
        public boolean isBalanced(TreeNode root) {
            if(root == null) return true;
            int minHeight=0;
            LinkedList<TreeNode> queue=new LinkedList();
            queue.offer(root);
            int levelSize=1;
            int high=1;
            while(!queue.isEmpty()){
                TreeNode node = queue.poll();
                levelSize--;
                if(null!=node.left){
                    queue.offer(node.left);
                }
                if(null!=node.right){
                    queue.offer(node.right);
                }
                if(levelSize==0){
                    levelSize=queue.size();
                    if(minHeight==0 && levelSize<Math.pow(2,high)){
                        minHeight=high;
                    }
                    high++;
                }
            }
            return (high-minHeight)<=1;
        }
    }
}

