# Definition for singly-linked list.
class ListNode(object):
     def __init__(self, x):
         self.val = x
         self.next = None

class Solution(object):
    def oddEvenList(self, head):
        if head == None: return head
        last_odd = odd_curr = curr = odd_head = head
        even_head = even_curr = None
        if curr != None: even_head = even_curr = curr.next
        odd = True
        while curr != None:
            temp = curr.next
            if odd:
                if curr.next != None:
                    odd_curr.next = curr.next.next
                    odd_curr = odd_curr.next
                    if odd_curr != None: last_odd = odd_curr
            if not odd:
                if curr.next != None:
                    even_curr.next = curr.next.next
                    even_curr = even_curr.next
            odd = not odd
            curr = temp
        last_odd.next = even_head
        return head
        
head = ListNode(1)
head.next = ListNode(2)
head.next.next = ListNode(3)
head.next.next.next = ListNode(4)
head.next.next.next.next = ListNode(5)
end = head.next.next.next.next
head = Solution().oddEvenList(head)
curr = head
while curr != None:
    print(curr.val) #1, 3, 5, 2, 4
    curr = curr.next

print('--------------')

head = ListNode(1)
head.next = ListNode(2)
head.next.next = ListNode(3)
head.next.next.next = ListNode(4)
head.next.next.next.next = ListNode(5)
head.next.next.next.next.next = ListNode(6)
head.next.next.next.next.next.next = ListNode(7)
head.next.next.next.next.next.next.next = ListNode(8)
head = Solution().oddEvenList(head)
curr = head
while curr != None:
    print(curr.val) #1, 3, 5, 7, 2, 4, 6, 8
    curr = curr.next
    
print('--------------')
print(Solution().oddEvenList(None))
