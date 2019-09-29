def count_sort(sortable, k):
    
    # Get counts
    count = {}
    for x in sortable:
        count[x] = 1 if x not in count else count[x] +1
        
    #Change count dictionary to starting index dictionary
    new_total=0
    total = 0    
    for x in range(k):
        if x in count:
            new_total += count[x]
            count[x] = total
            total = new_total
    
    #Start filling in the new array
    out = list(range(len(sortable)))
    for x in sortable:
        out[count[x]] = x
        count[x] += 1
            
    return out
            
if __name__ == '__main__':
    import ztesting.sorting_test.sorting_test as test
    test.test_counting_sort()

