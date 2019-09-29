# Definition for singly-linked list.
class ListNode(object):
     def __init__(self, x):
         self.val = x
         self.next = None

class Solution(object):
    def deleteNode(self, node):
       if node.next == None: return
       node.val = node.next.val
       node.next = node.next.next
       
       

head = ListNode(1)
head.next = ListNode(2)
head.next.next = ListNode(3)
head.next.next.next = ListNode(4)
Solution().deleteNode(head.next.next)
curr = head
while curr != None:    
    print(curr.val)
    curr = curr.next
#1 2 4