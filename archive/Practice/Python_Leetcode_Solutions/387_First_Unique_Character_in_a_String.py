class Solution(object):
    def firstUniqChar(self, s):
        if s is None: return -1
        occurences = {}
        for i in range(len(s)):
            if s[i] not in occurences:
                occurences[s[i]] = {'count':0, 'index':i}
            occurences[s[i]]['count'] += 1
        occurences = filter(lambda x: x['count']==1, occurences.values())
        if len(occurences)==0: return -1
        return min(occurences, key=lambda x: x['index'])['index']
    
print(Solution().firstUniqChar("leetcode"))#0
print(Solution().firstUniqChar("loveleetcode"))#2
print(Solution().firstUniqChar("lllllll"))#-1
print(Solution().firstUniqChar(""))#-1
print(Solution().firstUniqChar(None))#-1