def merge_sort(sortable):
    if len(sortable) == 1:
        return sortable
    else:
        mid_way = len(sortable)//2
        left = merge_sort(sortable[0:mid_way])
        right = merge_sort(sortable[mid_way:])
        return merge(left, right)
        
def merge(left, right):
    ret = []
    while len(left) != 0 and len(right) != 0:
        front_min = left if left[0] < right[0] else right
        ret.append(front_min.pop(0))
    while len(left) > 0: ret.append(left.pop(0))
    while len(right) > 0 : ret.append(right.pop(0))
    return ret

if __name__=='__main__':
    import ztesting.sorting_test.sorting_test as test
    test.test_merge_sort()