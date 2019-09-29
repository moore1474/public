from ztesting.common_testing_functions import *
from lists.linked.linked_list import *

def test_linked_list():
    ll = getTestLL()
    start_high_level_test("Linked List Test")
    assertEquals('Print', lambda _: ll.printAll(), '1 2 3 4 5 6 7 8 9 10')
    assertEquals('size', lambda _: ll.size(), 10)
    assertEquals('Val At', lambda _: ll.valAt(5), 6)
    assertEquals('Val N Back', lambda _: ll.value_n_back(5), 5)
    assertEquals('Peek', lambda _: ll.peek().val, 10)
    assertEquals('Peek Front', lambda _: ll.peek_front().val, 1)
    assertEquals('Index Of', lambda _: ll.indx_of_val(5), 4)
    assertEquals('Index Of - Not Found', lambda _: ll.indx_of_val(32453252345), -1)
    ll.insert(5, 100)
    assertEquals('Insert (100)', lambda _: ll.printAll(), '1 2 3 4 5 6 100 7 8 9 10')
    ll.erase(6)
    assertEquals('Erase (100)', lambda _:ll.printAll(), '1 2 3 4 5 6 7 8 9 10')
    ll = getTestLL()
    ll.remove_val(5)
    assertEquals('Remove (5)', lambda _:ll.printAll(), '1 2 3 4 6 7 8 9 10')
    ll.push(Node(11))
    assertEquals('Push (11)', lambda _:ll.printAll(), '1 2 3 4 6 7 8 9 10 11')
    ll.pop()
    assertEquals('Pop', lambda _:ll.printAll(), '1 2 3 4 6 7 8 9 10')
    ll.push_front(0)
    assertEquals('Push Front', lambda _:ll.printAll(), '0 1 2 3 4 6 7 8 9 10')
    ll.pop_front()
    assertEquals('Pop Front', lambda _:ll.printAll(), '1 2 3 4 6 7 8 9 10')
    ll.reverse()
    assertEquals('Reverse', lambda _:ll.printAll(), '10 9 8 7 6 4 3 2 1')
    while not ll.empty():
        ll.pop();
    assertEquals('Pop all', lambda _: ll.printAll(), '')

def getTestLL():
    ll = LinkedList()
    for i in range(1, 11):
        ll.push(i)
    return ll

if __name__=='__main__':
    test_linked_list()