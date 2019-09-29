class ListNode(object):
     def __init__(self, x):
         self.val = x
         self.next = None

class Solution(object):
    def hasCycle(self, head):
        if head == None: return False
        walk_one = head
        walk_two = head
        while walk_two.next != None and walk_two.next.next != None:
            walk_one = walk_one.next
            walk_two = walk_two.next.next
            if walk_one == walk_two: return True
        return False
    
    
ll = ListNode('A')
ll.next = ListNode('B')
ll.next.next = ListNode('C')
ll.next.next.next = ListNode('D')
print(Solution().hasCycle(ll))#False
ll.next.next.next.next = ll
print(Solution().hasCycle(ll))#True