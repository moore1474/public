class Solution(object):
    def moveZeroes(self, nums):
        num_of_zeroes = 0
        for x in nums:
            if x == 0: num_of_zeroes += 1
        curr = 0
        for i in range(len(nums)):
            if nums[i] != 0: 
                nums[curr] = nums[i]
                curr += 1
        start = len(nums) - num_of_zeroes
        for i in range(start, len(nums)):
            nums[i] = 0
            
a =  [0, 1, 0, 3, 12, 0, 22, 0]
Solution().moveZeroes(a)
print(a)

a =  [0]
Solution().moveZeroes(a)
print(a)

a =  []
Solution().moveZeroes(a)
print(a)

a =  [1]
Solution().moveZeroes(a)
print(a)