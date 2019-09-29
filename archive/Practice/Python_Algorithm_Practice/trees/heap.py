
class Heap:
        
    def __init__(self, min=False):
        self.min = min
        self.balanced_tree = []
    
    def push(self, x):
        pass
    
    def pop(self):
        pass
    
    def parent(self, i):
        pass
    
    def heapify(self, i):
        pass

    def __len__(self):
        return len(self.balanced_tree)

if __name__=='__main__':
    import ztesting.trees_test.heap_test as test
    test.test_heap()