#Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def getIntersectionNode(self, headA, headB):
        #Determine lengths
        len1, len2 = 0, 0
        currA, currB = headA, headB
        while currA != None:
            len1 += 1
            currA = currA.next
        while currB != None: 
            len2 += 1
            currB = currB.next
            
        #Get to same Starting Point
        currA, currB = headA, headB
        while len1 > len2: 
            currA = currA.next
            len1 -= 1
        while len2 > len1: 
            currB = currB.next
            len2 -= 1
            
        #Loop until intersect is found
        while currA != None and currB !=None:
            if currA == currB: return currA
            currA = currA.next
            currB = currB.next
        
        return None
        
head1 = ListNode(1)
head1.next = ListNode(2)
intersect = ListNode(3)
head1.next.next = intersect
intersect.next = ListNode(4)
head2 = ListNode(1)
head2.next = intersect
print(Solution().getIntersectionNode(head1, head2).val) #3
print(Solution().getIntersectionNode(head1, head1).val) #1
head2.next = ListNode(5)
print(Solution().getIntersectionNode(head1, head2))