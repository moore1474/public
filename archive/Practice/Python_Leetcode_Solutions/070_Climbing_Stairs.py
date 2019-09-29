#Non-Memoized
class Solution2(object):
    def climbStairs(self, n):
        if n <= 2: return n
        return self.climbStairs(n-1) + self.climbStairs(n-2)
    
#Memoized
class Solution(object):
    
    def __init__(self):
        self.memo = {1:1, 2:2}
    
    def climbStairs(self, n):
        if n not in self.memo:
            self.memo[n] = self.climbStairs(n-1) + self.climbStairs(n-2)
        return self.memo[n]
            
#print(Solution2().climbStairs(35))#14930352
print(Solution().climbStairs(35))#14930352