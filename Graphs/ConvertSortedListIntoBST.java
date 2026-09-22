class Solution {
    private ListNode head;

    public TreeNode sortedListToBST(ListNode head) {
        this.head = head;
        int n = getLength(head);
        return convert(0, n - 1);
    }

    private int getLength(ListNode node) {
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }

    private TreeNode convert(int left, int right) {
        if (left > right) return null;

        int mid = (left + right) / 2;

        TreeNode node = new TreeNode(0);
        node.left = convert(left, mid - 1);

        node.val = head.val;
        head = head.next;

        node.right = convert(mid + 1, right);

        return node;
    }
}