# Definition for singly-linked list.
class ListNode(object):
     def __init__(self, x):
         self.val = x
         self.next = None

class Solution(object):
    def reverseList(self, head):
        if head == None: return head
        curr = head
        prev = None
        while curr.next != None:
            temp = curr.next
            curr.next = prev
            
            prev = curr
            curr = temp
        curr.next = prev
        return curr
            
       
       
head = ListNode(1)
head.next = ListNode(2)
head.next.next = ListNode(3)
head.next.next.next = ListNode(4)
rev = Solution().reverseList(head)
print(rev.val)
print(rev.next.val)
print(rev.next.next.val)
print(rev.next.next.next.val)

rev = Solution().reverseList(None)