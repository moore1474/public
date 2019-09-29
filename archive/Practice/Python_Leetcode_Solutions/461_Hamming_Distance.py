class Solution(object):
    def hammingDistance(self, x, y):
        count = 0
        differences = x ^ y
        while differences:
            differences = differences & (differences-1)
            count += 1
        return count
    
print(Solution().hammingDistance(1, 4))#2
    
print(Solution().hammingDistance(15, 0))#3