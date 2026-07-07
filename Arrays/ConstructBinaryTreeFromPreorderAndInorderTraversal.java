class Solution {
    Map<Integer, Integer> inorderMap = new HashMap<>();
    int preIdx = 0;
    int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return solve(0, inorder.length - 1);
    }

    private TreeNode solve(int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }

        int rootVal = preorder[preIdx++];
        TreeNode root = new TreeNode(rootVal);

        int rootIdx = inorderMap.get(rootVal);

        root.left = solve(inStart, rootIdx - 1);
        root.right = solve(rootIdx + 1, inEnd);

        return root;
    }
}