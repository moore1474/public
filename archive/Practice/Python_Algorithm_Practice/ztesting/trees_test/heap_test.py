from ztesting.common_testing_functions import *
from random import randint
from trees import heap

def test_heap():
    start_high_level_test("Testing Heap")
    test_max_heap()
    test_min_heap()
    
def test_max_heap():
    max_heap = create_heap()
    assertEquals('Max Heap', 'Heap Property Holds - ' + str(validate_heap(max_heap)),
                 'Heap Property Holds - True')

def test_min_heap():
    min_heap = create_heap(True)
    assertEquals('Min Heap', 'Heap Property Holds - ' + str(validate_heap(min_heap, True)),
                 'Heap Property Holds - True')

def create_heap(min=False):
    the_heap = heap.Heap(min)
    for i in range(10):
        the_heap.push(i)
    return the_heap

def validate_heap(the_heap, min=False):    
    for i, v in enumerate(the_heap.balanced_tree):
        l = 2 * i + 1
        r = 2 * i + 2
        if l<len(the_heap) and not min and the_heap.balanced_tree[l]>the_heap.balanced_tree[i]: return False
        if r<len(the_heap) and not min and the_heap.balanced_tree[r]>the_heap.balanced_tree[i]: return False
        if l<len(the_heap) and min and the_heap.balanced_tree[l]<the_heap.balanced_tree[i]: return False
        if r<len(the_heap) and min and the_heap.balanced_tree[r]<the_heap.balanced_tree[i]: return False    
    return True
        
    
if __name__=='__main__':
    test_heap()