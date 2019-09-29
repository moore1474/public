class Solution(object):
    def singleNumber(self, nums):
        answer = 0
        for num in nums:
            answer = answer ^ num
        return int(answer)
        
        
        
a = [1,1,2,2,3,4,4,5,5]
print(Solution().singleNumber(a))