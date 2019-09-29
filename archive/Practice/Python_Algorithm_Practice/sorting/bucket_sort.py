from sorting.insertion_sort import insertion_sort

def bucket_sort(sortable, num_of_buckets):
    #Put the numbers into buckets
    the_min = min(sortable)
    the_range = (max(sortable)-the_min)/num_of_buckets
    buckets = [[] for i in range(num_of_buckets)]    
    for x in sortable: 
        bucket_num = (x-the_min)//the_range
        if bucket_num == num_of_buckets: bucket_num -= 1
        buckets[bucket_num].append(x)
    
    #Sort item in buckets and put back into list
    i=0
    for bucket in buckets:
        insertion_sort(bucket)
        for num in bucket:
            sortable[i] = num
            i += 1
    
    return sortable

if __name__=='__main__':
    import ztesting.sorting_test.sorting_test as test
    test.test_bucket_sort()
