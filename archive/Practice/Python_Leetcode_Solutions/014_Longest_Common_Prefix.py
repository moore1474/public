class Solution(object):
    def longestCommonPrefix(self, strs):
        prefix = ''
        if strs is None or len(strs)==0: return ''
        min_char = min([len(str) for str in strs if True])
        for i in range(min_char):
            char = strs[0][i]
            non_match_found = False
            for str in strs:
                if str[i] != char:
                    non_match_found = True
                    break
            if non_match_found: break
            prefix += char
        return prefix
    
print(Solution().longestCommonPrefix(["AAB", "AAC", "AAA", "AASLKGMSLK"]))#AA
print(Solution().longestCommonPrefix(["AAB", "BAC", "AAA", "AASLKGMSLK"]))#
print(Solution().longestCommonPrefix(None))#
print(Solution().longestCommonPrefix([]))#