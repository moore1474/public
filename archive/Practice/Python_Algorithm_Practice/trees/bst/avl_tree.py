from trees.bst.binary_search_tree import *

#http://blog.coder.si/2014/02/how-to-implement-avl-tree-in-python.html
class Avl_Tree(BinarySearchTree):
    
    def push(self, new_node, current=None):
        super(Avl_Tree, self).push(new_node, current);        
        if not current:
            #Otherwise BST recalls this push recursively
            parent = self.find_parent(new_node.val)
            if parent: self.do_rotations(parent)     
    
    def do_rotations(self, root):
        balance = self.balance_factor(root)  
                
        while balance > 1 or balance < -1:
            if balance > 1: #Left heavy
                if root.left and self.balance_factor(root.left) < 0:
                    self.left_rotation(root.left)#LR
                else:
                    self.right_rotation(root)
            elif balance < -1: #Right Heavy
                 if root.right and self.balance_factor(root.right) > 0:
                    self.right_rotation(root.right)#RL
                 else:
                    self.left_rotation(root)
            balance = self.balance_factor(root)
            
        parent = self.find_parent(root.val)
        if parent: self.do_rotations(parent)    
    
    def balance_factor(self, node):
        l_height = 1 + self.height(node.left) if node.left else 0
        r_height = 1 + self.height(node.right)  if node.right else 0
        return l_height - r_height
        
    def left_rotation(self, root):
        root.right.left = root #Make Pivot Node point to node being shifted
        parent = self.find_parent(root.val)
        if parent:
            parent_attr ='left' if parent.left == root else 'right'        
            setattr(parent, parent_attr, root.right) #Point parent of shifted node to pivot node
        else: self.root = root.right #Shifting a root node, so pivot becomes root
        root.right = None #Shifted node should now point to nothing
    
    def right_rotation(self, root):
        root.left.right = root #Make Pivot Node point to node being shifted
        parent = self.find_parent(root.val)
        if parent:
            parent_attr ='left' if parent.left == root else 'right'     
            setattr(parent, parent_attr, root.left) #Point parent of shifted node to pivot node
        else: self.root = root.left #Shifting a root node, so pivot becomes root
        root.left = None #Shifted node should now point to nothing        
    
if __name__=='__main__':
    import ztesting.trees_test.binary_search_tree_test as test
    test.avl_tree_test()