class Solution(object):
    def countAndSay(self, n):
        s = "1"
        for x in range(1, n):
            new_s=''
            i = 0
            j = 0      
            cnt = 0
            while  j < len(s):                 
                if s[i] != s[j]: 
                    new_s += str(cnt) + s[i]
                    i = j
                    cnt = 0
                else: 
                    cnt += 1
                    j += 1
            new_s += str(cnt) + s[i]
            s = new_s
        return s    
        
     
print(Solution().countAndSay(1))#1
print(Solution().countAndSay(2))#2       
print(Solution().countAndSay(3))#21
print(Solution().countAndSay(4))#1211
print(Solution().countAndSay(5))#111221