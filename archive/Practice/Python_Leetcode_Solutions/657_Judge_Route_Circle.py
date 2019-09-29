class Solution(object):
    def judgeCircle(self, moves):
        pos = [0,0]
        for s in moves:
            if s=='U':pos[0]+=1
            elif s=='D':pos[0]-=1
            elif s=='L':pos[1]-=1
            elif s=='R':pos[1]+=1
        return pos[0]==0 and pos[1]==0
        
print(Solution().judgeCircle("UD"))#True
print(Solution().judgeCircle("LL"))#False