class Solution(object):
    def getSum(self, a, b):
        if a == 0: return b
        if b == 0: return a
        neg_bit, mask = (1 << 32) >> 1, ~(~0 << 32)
        a = (a | ~mask) if a<0 else (a & mask)
        b = (b | ~mask) if b<0  < 0 else (b & mask)
        while b:
            carry = a & b
            a = a ^ b 
            a = (a | ~mask) if (a & neg_bit)  else (a & mask)
            b = carry << 1
            b = (b | ~mask) if (b & neg_bit)  else (b & mask)
        return a
            
            
print(Solution().getSum(1, 3))#4
print(Solution().getSum(0, 5))#5
print(Solution().getSum(5, 0))#5
print(Solution().getSum(23, 77))#100
print(Solution().getSum(-1, -1))#-2
print(Solution().getSum(500000, 500000))#1,000,000
print(Solution().getSum(5, -3))#2
print(Solution().getSum(-1, 1))#0