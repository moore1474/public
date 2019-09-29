class Solution(object):
    def titleToNumber(self, s):
        total = 0
        for i in range(len(s)):
            multiple = len(s) - 1 - i
            val = ord(s[i]) - ord('A') + 1
            total +=  val * 26**(multiple)
        return total

print(Solution().titleToNumber('A'))#1
print(Solution().titleToNumber('AA'))#27
print(Solution().titleToNumber('ZA'))#677