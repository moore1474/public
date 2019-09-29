from lists.linked.linked_list import *
import math

def get_digit(num, digit):
    #Starting with least sig digit at 0 place   
    str_rep = str(num)
    digit = len(str_rep) - digit -1
    if digit < 0:
        return 0
    return int(str_rep[digit])

def radix_sort(sortable):
    max_num = max(sortable)
    iter = 0
    while 10 ** iter <= max_num:
        #Put nums in buckets
        buckets = [[] for x in range(10)]
        for num in sortable:
            digit = get_digit(num, iter)
            buckets[digit].append(num)
        
        #Take out of buckets and put back into array
        i = 0
        for bucket in buckets:
            for num in bucket:
                sortable[i] = num
                i+=1
        
        iter += 1
        
    return sortable

if __name__=='__main__':
   import ztesting.sorting_test.sorting_test as test
   test.test_radix_sort()
