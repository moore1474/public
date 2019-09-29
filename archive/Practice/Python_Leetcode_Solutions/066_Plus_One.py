class Solution(object):
    def plusOne(self, digits):
        carry = True       
        for i in range(len(digits)-1, -1, -1):
            if carry and digits[i] == 9: 
                digits[i] = 0
            elif carry:
                digits[i] += 1
                carry = False            
        if carry: digits = [1] + digits
        return digits
        
print(Solution().plusOne([1, 2, 3]))#124
print(Solution().plusOne([1, 2, 9]))#130
print(Solution().plusOne([9, 9, 9]))#1000