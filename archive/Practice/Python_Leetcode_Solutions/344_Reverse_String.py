class Solution(object):
    def reverseString(self, s):
        up = 0
        down = len(s) - 1
        sList = list(s)
        while up <= down:
            tmp = sList[up]
            sList[up] = sList[down]
            sList[down] = tmp
            up += 1
            down -= 1
        return ''.join(sList)
    
    def reverseString2(self, s):
        return s[::-1]
            
print(Solution().reverseString("A"))
print(Solution().reverseString("AB"))
print(Solution().reverseString("ABC"))
print(Solution().reverseString("Hello World"))
print(Solution().reverseString(""))
