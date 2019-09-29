class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def maxDepth(self, root):
        if root == None: return 0
        if root.left == None and root.right==None: return 1
        elif root.left != None and root.right==None: return 1 + self.maxDepth(root.left)
        elif root.left == None and root.right!=None: return 1 + self.maxDepth(root.right)
        else: return max(1 + self.maxDepth(root.left), 1 + self.maxDepth(root.right))
        
        
root = TreeNode(1)
root.left = TreeNode(2)
root.right = TreeNode(3)
root.left.left= TreeNode(4)
root.left.right = TreeNode(5)
root.left.left = TreeNode(6)

print(Solution().maxDepth(root))

print(Solution().maxDepth(None))