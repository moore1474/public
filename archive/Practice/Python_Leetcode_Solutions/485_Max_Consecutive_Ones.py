class Solution(object):
    def findMaxConsecutiveOnes(self, nums):
        maxCnt = 0
        ctr = 0
        for num in nums:
            if num == 0: ctr = 0
            else: ctr += 1
            maxCnt = max(ctr, maxCnt)
        return maxCnt
            
print(Solution().findMaxConsecutiveOnes([1,1,0,1,1, 0, 0, 1, 1, 1]))#3
print(Solution().findMaxConsecutiveOnes([]))#0
print(Solution().findMaxConsecutiveOnes([0]))#0
print(Solution().findMaxConsecutiveOnes([1]))#1
print(Solution().findMaxConsecutiveOnes([0, 1]))#1
print(Solution().findMaxConsecutiveOnes([1, 0]))#1