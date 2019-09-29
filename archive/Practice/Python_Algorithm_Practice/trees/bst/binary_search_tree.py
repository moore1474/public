import lists.linked.linked_list as linked_list
class Node(object):
    
    def __init__(self, val=None, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        
        def __repr__():
            return self.val
        
class BinarySearchTree(object):
    root = None
        
    def push(self, new_node, current = None):
        current = self.root if current is None else current
        new_node = new_node if type(new_node) is Node else Node(new_node)
        if self.root == None: self.root = new_node
        else:
            if new_node.val < current.val:
                if current.left is None: current.left = new_node
                else: self.push(new_node, current.left)
            elif new_node.val > current.val:
                if current.right is None: current.right = new_node
                else: self.push(new_node, current.right)
    
    def min(self):
        current = self.root
        while current.left is not None:
            current = current.left
        return current
    
    def max(self):
        current = self.root
        while current.right != None:
            current = current.right
        return current
    
    def find(self, x, current = None, return_parent = False):
        if current == None: current = self.root
        if current.val == x: return current
        if x < current.val: 
            if current.left is None: return None
            elif return_parent and current.left.val == x: return current
            else: return self.find(x, current.left, return_parent)
        else: 
            if current.right is None: return None
            elif return_parent and current.right.val == x: return current
            else: return self.find(x, current.right, return_parent)
        
    def find_parent(self, x, current = None):
        if self.root.val == x: return None
        return self.find(x, current, True)
    
    def preorder(self, root=None, path=[]):
        if self.root is None: return path
        if root is None: root = self.root
        path.append(root.val)
        if root.left is not None: self.preorder(root.left, path)
        if root.right is not None: self.preorder(root.right, path)
        return path
        
    def inorder(self, current = None, path=[]):
        if current is None: current = self.root
        if current.left is not None: self.inorder(current.left, path)
        path.append(current.val)
        if current.right is not None: self.inorder(current.right, path)
        return path
    
    def postorder(self, current=None, path=[]):
        if current is None: current = self.root
        if current.left is not None: self.postorder(current.left, path)        
        if current.right is not None: self.postorder(current.right, path)
        path.append(current.val)
        return path
        
    def level_order(self):
        queue = []
        path = []
        queue.append(self.root)
        while queue:
            current = queue.pop(0)
            path.append(current.val)
            if current.left: queue.append(current.left)
            if current.right: queue.append(current.right)
        return path
        
    def height(self, node = None):
        node = self.root if node is None else node
        if node is None: return 0
        l_height = 1 + self.height(node.left) if node.left is not None else 0
        r_height = 1 + self.height(node.right) if node.right is not None else 0
        return max(l_height, r_height)
    
    def delete_value(self, x):
        to_delete = self.find(x)        
        parent = None if to_delete == self.root else self.find_parent(x)
        
        #!!!!In Order (Not just sucessor)!!!! Successor
        successor = to_delete.right
        while successor and successor.left:
            successor = successor.left

        successor_parent = None if successor is None else self.find_parent(successor.val)
        if parent is None: 
            temp = self.root
            self.root = successor
        else:
            to_replace = 'left' if to_delete == parent.left else 'right'
            temp = getattr(parent, to_replace)
            setattr(parent, to_replace, successor)
        
        if successor_parent:
            to_replace = 'left' if successor == successor_parent.left else 'right'
            setattr(successor_parent, to_replace, None)
            
        if successor: successor.left = temp.left
        if successor: successor.right = temp.right
        
            
    def get_successor(self, x, current = None):   
        successor = None
        current = self.root
        while current != None :
            if (successor == None or current.val<successor.val) and current.val>x:
                successor = current
            if current.val > x: current = current.left
            else: current = current.right
        return successor
        
        


if __name__=='__main__':
    import ztesting.trees_test.binary_search_tree_test as test
    test.test_binary_search_tree()