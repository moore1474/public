from random import randint
import sorting.bubble_sort as bubble_sort
import sorting.insertion_sort as insertion_sort
import sorting.merge_sort as merge_sort
import sorting.quick_sort as quick_sort
import sorting.selection_sort as selection_sort
import sorting.counting_sort as counting_sort
import sorting.radix_sort as radix_sort
import sorting.bucket_sort as bckt_sort
from ztesting.common_testing_functions import *

def get_random(start=0, end=10):
    random_nums =[]
    for i in range(10):
        random_nums.append(randint(start, end))
    return random_nums

def test_sorts():
    start_high_level_test("Sorting Algorithms Test")
    test_bubble_sort()
    test_insertion_sort()
    test_merge_sort()
    test_quick_sort()
    test_selection_sort()
    test_counting_sort()
    test_radix_sort()
    test_bucket_sort()
    
def test_sort(name, function, arg=None):
    random_nums = get_random()
    if arg:assertEquals(name, lambda _: function(random_nums, arg), sorted(random_nums))
    else : assertEquals(name, lambda _: function(random_nums), sorted(random_nums))

 
def test_bubble_sort():
    test_sort('BUBBLE SORT', bubble_sort.bubble_sort)
        
def test_insertion_sort():
    test_sort('INSERTION SORT', insertion_sort.insertion_sort)
    test_sort('INSERTION SORT (recursive)', insertion_sort.insertion_sort_recursive)
    
def test_merge_sort():
    test_sort('MERGE SORT', merge_sort.merge_sort)

def test_quick_sort():
    test_sort('QUICK SORT', quick_sort.quick_sort)

def test_selection_sort():
    test_sort('SELECTION SORT', selection_sort.selection_sort)
    test_sort('SELECTION SORT', selection_sort.selection_sort_recursive)

def test_counting_sort():
    test_sort('COUNTING SORT', counting_sort.count_sort, 11)

def test_radix_sort():
    random_nums = get_random(0, 1000)
    assertEquals('RADIX SORT', radix_sort.radix_sort(random_nums), sorted(random_nums))

def test_bucket_sort():
    random_nums = get_random(0, 1000)
    assertEquals('BUCKET SORT', bckt_sort.bucket_sort(random_nums, 15), sorted(random_nums))

if __name__=='__main__':
    test_sorts()
    