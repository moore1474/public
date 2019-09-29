class Solution(object):
    def removeDuplicates(self, nums):
        if not nums: return 0
        end = 0
        for i in range(len(nums)):
           if nums[i] != nums[end]:
            end += 1
            nums[end] = nums[i]
        return end + 1
        
a = [1, 1, 1, 2, 2, 3, 4, 4, 5]      
print(Solution().removeDuplicates(a))
print(a)
