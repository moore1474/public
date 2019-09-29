class ListNode(object):
     def __init__(self, x):
         self.val = x
         self.next = None

# 162 ms
class Solution(object):
    def addTwoNumbers(self, l1, l2):
        head =  ListNode(0);
        current = head
        carry = 0
        while True:
            a = l1.val if l1 is not None else 0
            b = l2.val if l2 is not None else 0
            current.val = a + b + carry
            if current.val > 9: 
                current.val -= 10
                carry = 1
            else: 
                carry = 0
            l1 = l1.next if l1 is not None else None
            l2 = l2.next if l2 is not None else None
            if l1 is None and l2 is None: 
                if carry >0: 
                    current.next = ListNode(0)
                    current = current.next
                    current.val = carry
                return head
            current.next = ListNode(0)
            current = current.next
    
            
