from _Node_Tests_Util import *

class Solution(object):
    def removeNthFromEnd(self, head, n):
        current = head
              
        #Find first n ahead
        i = 0
        while i < n:
            current = current.next
            if current is None: return head.next if n == i+1 else head
            i += 1
            
        #Go to the end
        n_minus_one_back = head
        while current.next is not None:
            n_minus_one_back = n_minus_one_back.next
            current = current.next
        
        n_minus_one_back.next = n_minus_one_back.next.next
        
        return head
        
        
ll = create_linked_list((1,2,3,4,5))
ll = Solution().removeNthFromEnd(ll, 2)
printll(ll)#1 -> 2 -> 3 -> 5 -> None

print('')

ll2 = create_linked_list((1,2))
ll2 = Solution().removeNthFromEnd(ll2, 2)
printll(ll2)#2 -> None