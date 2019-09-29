def selection_sort(sortable):
    n = len(sortable)
    for i in range(n):
        min = i
        for j in range(i+1, n):
            if sortable[j] < sortable[min]:
                min = j
        temp = sortable[i]
        sortable[i] = sortable[min]
        sortable[min] = temp
    return sortable
            

def selection_sort_recursive(sortable, n=-1):
    if n == -1: n = len(sortable)
    if n == 1: return sortable
    start = len(sortable) - n
    min_i = start
    for i in range(start, len(sortable)):
        if sortable[i] < sortable[min_i]:
                min_i = i
    temp = sortable[start]
    sortable[start] = sortable[min_i]
    sortable[min_i] = temp
    selection_sort_recursive(sortable, n-1)
    return sortable

if __name__=='__main__':
    import ztesting.sorting_test.sorting_test as test
    test.test_selection_sort()