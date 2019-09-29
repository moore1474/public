def bubble_sort(sortable):
    while True:
        iterations = 0
        no_swaps = True
        for i in range(len(sortable) - iterations - 1):            
            if sortable[i] > sortable[i+1]:
                temp = sortable[i+1]
                sortable[i+1] = sortable[i]
                sortable[i] = temp
                no_swaps = False
        iterations += 1
        if no_swaps: return sortable

if __name__=='__main__':
    import ztesting.sorting_test.sorting_test as test
    test.test_bubble_sort()