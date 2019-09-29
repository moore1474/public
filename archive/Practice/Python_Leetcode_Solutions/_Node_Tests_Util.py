import sys

class ListNode(object):
     def __init__(self, x):
         self.val = x
         self.next = None

def create_linked_list(nodes):
    current = head = ListNode(nodes[0])    
    for i in range(1, len(nodes)):
        current.next = ListNode(nodes[i])
        current = current.next
    return head

def printll(head):
    while head != None:
        sys.stdout.write(str(head.val) + ' -> ')
        head = head.next
    sys.stdout.write('None')
    
if __name__=='__main__':
    ll = create_linked_list((1,2,3,4,5))
    printll(ll)
    