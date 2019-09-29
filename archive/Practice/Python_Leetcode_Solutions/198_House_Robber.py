class Solution(object):
    def rob(self, nums):
        prev = curr = 0
        for x in nums:
            prev, curr = curr, max(prev + x, curr)
        return curr
        
        
        
print(Solution().rob([5,5,5,0,0,0,5,5,0,5,5,5,0]))#25