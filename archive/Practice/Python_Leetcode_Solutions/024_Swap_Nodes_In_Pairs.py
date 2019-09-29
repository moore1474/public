class ListNode(object):
     def __init__(self, x):
         self.val = x
         self.next = None

class Solution(object):
    def swapPairs(self, head):
        prev = curr = ListNode('dummy')
        curr.next = head
        while curr.next and curr.next.next:
            first = curr.next
            second = first.next
            third = second.next
            second.next = first
            first.next = third
            curr.next = second
            curr = first
        return prev.next
                

def printLL(head):
    while head is not None:
        print(head.val)
        head = head.next

#Test Code   
head = ListNode(1)
temp = head
for i in range(2, 10):
    temp.next = ListNode(i)
    temp = temp.next

temp = Solution().swapPairs(head)
printLL(temp)

head = ListNode(1)
head.next = ListNode(2)
temp = Solution().swapPairs(head)
printLL(head)