class Solution(object):
    def arrayPairSum(self, nums):
        return sum(sorted(nums)[::2])
    
print(Solution().arrayPairSum([1,4,3,2]))