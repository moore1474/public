class Solution(object):
    def findTheDifference(self, s, t):
        for i in range(len(s)):
            if s[i] != t[i]: return t[i]
        return t[len(t)-1]
            
print(Solution().findTheDifference('abcd', 'dbcae'));