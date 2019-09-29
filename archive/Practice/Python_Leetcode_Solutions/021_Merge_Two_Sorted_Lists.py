class ListNode(object):
     def __init__(self, x):
         self.val = x
         self.next = None

class Solution(object):
    def mergeTwoLists(self, l1, l2):
        dummy = ListNode(0)
        current = dummy
        while l1 is not None or l2 is not None:
            if l1 is not None and (l2 is None or l1.val <= l2.val):
                current.next = l1
                l1 = l1.next
            elif l2 is not None and (l1 is None or l2.val <= l1.val):
                current.next = l2
                l2 = l2.next
            current = current.next
        return dummy.next

#Quick Test Code
a = ListNode(1)
a.next = ListNode(5)
b = ListNode(3)
b.next = ListNode(4)
c = Solution().mergeTwoLists(a, b)
