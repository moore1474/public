class Solution(object):
    def containsDuplicate(self, nums):
        return len(nums) > len(set(nums))
    
    
print(Solution().containsDuplicate([2, 3, 4, 5]))
print(Solution().containsDuplicate([2, 3, 4, 4, 5]))