from ztesting.common_testing_functions import *
from trees.bst.binary_search_tree import *
from trees.bst.avl_tree import *
from trees.bst.red_black_tree import *
import random
from logging import root

#Setup Binary Search Tree
bst = BinarySearchTree()
bst.push(27)
bst.push(14)
bst.push(35)
bst.push(10)
bst.push(19)
bst.push(31)
bst.push(42)

def test_binary_search_tree():
    start_high_level_test('Binary Search Tree')
    
    #Test Structure
    correct = bst.root.val == 27
    correct &= bst.root.left.val == 14
    correct &= bst.root.right.val == 35
    correct &= bst.root.left.left.val == 10
    correct &= bst.root.left.right.val == 19
    correct &= bst.root.right.left.val == 31
    correct &= bst.root.right.right.val == 42
    assertEquals("Correct Structure", lambda _: correct, True)    
    assertEquals("Find Max", lambda _: bst.max().val, 42)
    assertEquals("Find Minimum", lambda _: bst.min().val, 10)
    assertEquals("Find", lambda _: bst.find(19).val, 19)
    assertEquals("Find Non-Existent", lambda _:bst.find(199), None)
    assertEquals("Height", lambda _:[bst.height(), bst.height(bst.find(14)), bst.height(bst.find(10))], [2, 1, 0])
    assertEquals("Find Parent", lambda _:bst.find_parent(31).val, 35)
    assertEquals("Find Parent of Root", lambda _:bst.find_parent(27), None)
    gs = bst.get_successor
    assertEquals("Get Successor", lambda _: [gs(19).val, gs(10).val, gs(35).val, gs(42)], [27, 14, 42, None])
    def testTraversal(method, name, expected):
        path = method()      
        assertEquals(name + ' Traversal',  lambda _: path, expected)
    testTraversal(bst.preorder, 'Preorder',[27, 14, 10, 19, 35, 31, 42])
    testTraversal(bst.inorder, 'In Order', [10, 14, 19, 27, 31, 35, 42])
    testTraversal(bst.postorder, 'Post Order', [10, 19, 14, 31, 42, 35, 27])
    testTraversal(bst.level_order, 'Level Order', [27, 14, 35, 10, 19, 31, 42])
    bst.delete_value(27)
    bst.delete_value(35)
    bst.delete_value(19)
    testTraversal(bst.level_order, 'Post Delete', [31, 14, 42, 10])

def avl_tree_test():
    
    start_high_level_test('AVL Balanced Binary Search Tree')
    
    #Test all right (Left Rotation)
    all_right = Avl_Tree();#You are the worst doctor! (Arrested Development)
    root = Node(1)
    all_right.root = root
    root.right = Node(2)
    root.right.right = Node(3)
    all_right.left_rotation(root)
    assertEquals('Left Rotation (Rotate root)', 'root.left=' + str(all_right.root.left.val), 'root.left=1')
    
    #Test all left (Right Rotation)
    all_left = Avl_Tree();
    root = Node(4)
    all_left.root = root
    root.left = Node(3)
    root.left.left = Node(2)
    root.left.left.left = Node(1)
    all_left.right_rotation(root.left)
    assertEquals('Right Rotation (Non-Root)', 'root.left.right=' + str(root.left.right.val), 'root.left.right=3')
    
    avl_tree = Avl_Tree();
    for i in range(10, 0, -1):
        avl_tree.push(Node(i))
    assertEquals('Insert always decreasing', 'Height=' + str(avl_tree.height()), 'Height=3')
    avl_tree.preorder()  
    avl_tree = Avl_Tree();
    for i in range(10, 0, -1):
        avl_tree.push(Node(i))
    assertEquals('Insert always increasing', 'Height=' + str(avl_tree.height()), 'Height=3');
     
    for i in range(3):
        avl_tree = Avl_Tree();
        added = ''
        for i in range(10):
            x = Node(random.randint(1, 10000))
            added = added + ' '  + str(x.val)
            avl_tree.push(x)
        avl_tree.preorder();
        assertEquals('Random', 'Height=' + str(avl_tree.height()), 'Height=3');

def test_red_black_tree():
    #Setup Tree
    rb_tree = Red_Black_Tree()
    rb_tree.push(Node(11))
    rb_tree.push(Node(1))
    rb_tree.push(Node(14))
    rb_tree.push(Node(2))       
    rb_tree.push(Node(7))
    rb_tree.push(Node(15))
    rb_tree.push(Node(5))
    rb_tree.push(Node(8))
    rb_tree.push(Node(4))

    rb_tree.preorder()
    assertEquals('Red Black Tree', rb_tree.get_path_taken(), '7B 2R 1B 5B 4R 11R 8B 14B 15R');

if __name__=='__main__':
    test_binary_search_tree()
    avl_tree_test()
    #test_red_black_tree()