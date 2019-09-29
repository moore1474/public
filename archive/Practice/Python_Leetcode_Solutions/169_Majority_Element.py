class Solution(object):
    def majorityElement(self, nums):
        count = 0
        curr = None
        for num in nums:
            if count == 0: curr = num
            count += (1 if curr != num else -1)            
        return curr

print(Solution().majorityElement([2,2,2,3,4]))
print(Solution().majorityElement([3,4,2,2,2]))
print(Solution().majorityElement([3,2,2,2,3]))