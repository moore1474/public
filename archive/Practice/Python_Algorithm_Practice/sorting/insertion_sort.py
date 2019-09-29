def insertion_sort(sortable):
    for i in range(len(sortable)):
        while i>0 and sortable[i] < sortable[i-1]:
            temp = sortable[i-1]
            sortable[i-1] = sortable[i]
            sortable[i] = temp
            i -= 1
    return sortable

def insertion_sort_recursive(sortable, n=-1):
    if n == -1: n = len(sortable)
    if n  == 1: return sortable
    insertion_sort_recursive(sortable, n-1)
    i = n - 1
    while i>0 and sortable[i] < sortable[i-1]:
        temp = sortable[i-1]
        sortable[i-1] = sortable[i]
        sortable[i] = temp
        i -= 1
    return sortable
    
    
if __name__=='__main__':
    import ztesting.sorting_test.sorting_test as test
    test.test_insertion_sort()
    