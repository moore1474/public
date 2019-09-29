class Solution(object):
    def hammingWeight(self, n):
        count = 0
        while n != 0:
            count += n & 1
            n = n >> 1
        return count
         
print(Solution().hammingWeight(11))#3
