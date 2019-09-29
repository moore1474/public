# I = 1
# V = 5
# X = 10
# L = 50
# C = 100
# D = 500
# M = 1000
class Solution(object):
    def romanToInt(self, s):
        sum = 0
        numerals = {'I':1, 'V':5, 'X': 10, 'L':50, 'C':100, 'D':500, 'M':1000}
        to_subtract = 0
        for i in range(len(s)):
            
            one_back = s[i-1] if i>0 else ''
            current = s[i]
            one_forward = s[i+1] if i<len(s)-1 else ''
            
            do_subtraction = i<len(s)-1 and one_back !=  current and one_forward != current and numerals[one_forward]>numerals[current]
            
            if not do_subtraction: sum += numerals[current] - to_subtract
            
            to_subtract = numerals[current] if do_subtraction else 0
        
        return sum
print(Solution().romanToInt("V"))#5
print(Solution().romanToInt("IV"))#4
print(Solution().romanToInt("IVXX"))#107