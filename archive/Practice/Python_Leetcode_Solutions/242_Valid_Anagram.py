class Solution(object):
    def isAnagram(self, s, t):
        def charCount(str):            
            chars = {}    
            for c in str:
                if c not in chars:
                    chars[c] = 0
                chars[c] += 1
            return chars
        
        def countInFirstMatchesSecond(dict1, dict2):
            for char in dict1.keys():
                if char not in dict2: return False
                if dict2[char] != dict1[char]: return False
            return True
        
        chars1 = charCount(s)
        chars2 = charCount(t)
        
        return countInFirstMatchesSecond(chars1, chars2) and countInFirstMatchesSecond(chars2, chars1)
    
print(Solution().isAnagram('anagram', 'nagaram'))#True
print(Solution().isAnagram('rat', 'car'))#False

        