import ztesting.lists_test.linked_list_test as ll_test
import ztesting.trees_test.binary_search_tree_test as bst_test
import ztesting.sorting_test.sorting_test as sort_test
import ztesting.graphs_test.adj_list_graph_test as adj_graph_test
import ztesting.trees_test.heap_test as heap_test

ll_test.test_linked_list()
bst_test.test_binary_search_tree()
bst_test.avl_tree_test()
heap_test.test_heap()
sort_test.test_sorts()
adj_graph_test.test_adj_list_graph()