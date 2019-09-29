class Solution(object):
    def strStr(self, haystack, needle):
        if len(needle) == 0: return 0
        if len(haystack) == 0: return -1
        i = 0
        while i < len(haystack) and len(haystack) - i >= len(needle):
            if haystack[i] == needle[0]:                
                found = True
                new_start = 0
                for j in range(1, len(needle)):
                    if not new_start and i + j < len(haystack) and needle[0] == haystack[i + j]:            
                        new_start = i + j - 1 
                    if i + j >= len(haystack) or haystack[i + j] != needle[j]: 
                        found = False                        
                        if new_start: i = new_start
                        break
                if found: return i
            i += 1
        return -1
    

print(Solution().strStr('hayhayhayneedlehay', 'needle'))  # 9
print(Solution().strStr('abcabcd', 'abcd'))  # 3
print(Solution().strStr('aabca', 'abc'))  # 1
print(Solution().strStr('abcabcd', 'NOT_IN_THERE'))  # -1
print(Solution().strStr('almos', 'almost'))  # -1
print(Solution().strStr('a', ''))  # 0
print(Solution().strStr('', '22'))  # -1
print(Solution().strStr('mississippi', 'issippi'))  # 4
print(Solution().strStr('bbbbaaa', 'aaaaa')) # -1
