from trees.bst.avl_tree import *

class Red_Black_Tree(Avl_Tree):
    
    def do_push(self, nodeToInsert, insertOntoNode = None):
        if self.root is None:
            self.root = nodeToInsert
        else:
            if insertOntoNode is None: insertOntoNode = self.root
            if nodeToInsert.val < insertOntoNode.val and insertOntoNode.left is None:
                insertOntoNode.left = nodeToInsert
            elif nodeToInsert.val < insertOntoNode.val:
                self.do_push(nodeToInsert, insertOntoNode.left);
            elif nodeToInsert.val > insertOntoNode.val and insertOntoNode.right is None:
                insertOntoNode.right = nodeToInsert
            elif nodeToInsert.val > insertOntoNode.val:
                self.do_push(nodeToInsert, insertOntoNode.right);
    
    def push(self, insert_node):        
        self.do_push(insert_node)
        insert_node.red = True
        self.fix_insert(insert_node)   

    def fix_insert(self, node):
        if self.root == node:
            node.red = False
        else:
            parent = self.find_parent(node.val)
            if parent is not None and parent.red:
                grand_parent = self.find_parent(parent.val)
                uncle = grand_parent.left if grand_parent.left != parent else grand_parent.right
                uncle_is_red = False if uncle is None else uncle.red
                if uncle_is_red:
                    grand_parent.red = True
                    parent.red = False
                    uncle.red = False
                    self.fix_insert(grand_parent)
                else:
                    self.do_rotations(grand_parent)
                    self.fix_insert(parent)

    def left_rotation(self, root):
        super(Red_Black_Tree, self).left_rotation(root)
        root.red = True
        self.find_parent(root.val).red = False
        
    def right_rotation(self, root):
        super(Red_Black_Tree, self).right_rotation(root)
        root.red = True
        self.find_parent(root.val).red = False
        
if __name__=='__main__':
    import ztesting.trees_test.binary_search_tree_test as test
    test.test_red_black_tree()