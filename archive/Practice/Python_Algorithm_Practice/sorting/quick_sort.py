from common import *

def partition(sortable, low, pivot):
    high = pivot-1
    def keep_going(): return low != high
    while keep_going():
        while keep_going() and sortable[low] <= sortable[pivot]: 
            low += 1
        while keep_going() and sortable[high] >= sortable[pivot]: 
            high -= 1
        swapsVarsInArray(sortable, low, high)      
    if sortable[pivot] < sortable[high]: 
        swapsVarsInArray(sortable, high, pivot)
    return high
        
def quick_sort(sortable, left=0, right=None):    
    if right == None: right = len(sortable)-1
    if right <= left: return sortable
    else:
        pivot = partition(sortable, left, right)
        quick_sort(sortable, left, pivot)
        quick_sort(sortable, pivot+1, right)
        return sortable

if __name__=='__main__':
    import ztesting.sorting_test.sorting_test as test
    test.test_quick_sort()
    
