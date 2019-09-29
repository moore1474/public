#Trivial implementation
class Solution(object):
    def lengthOfLongestSubstring(self, s):
        maxStr= s[0] if len(s)>0 else ''
        for i in range(len(s)):
            testStr = s[i]
            for j in range(i+1, len(s)):
                if s[j] in testStr: break
                else: testStr += s[j]
                if len(testStr) > len(maxStr): maxStr = testStr
        return len(maxStr)

#Solution with 2 pointers
class Solution2(object):
    def lengthOfLongestSubstring(self, s):
        leftIndx = 0
        rightIndx = 0
        charIndxMap = {}
        maxFound = 0
        while rightIndx<len(s):
            newLetter = s[rightIndx]           
            if newLetter in charIndxMap and charIndxMap[newLetter] >= leftIndx:
                leftIndx = charIndxMap[newLetter] + 1                
            charIndxMap[newLetter] = rightIndx
            rightIndx += 1
            maxFound = max(rightIndx - leftIndx, maxFound)
        return maxFound
    
print(Solution2().lengthOfLongestSubstring("abcabcbb"))