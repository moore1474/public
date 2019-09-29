
class Solution(object):
    def reverse(self, x):
        if x == 0: return 0
        negative = x<0
        x = x * -1 if negative else x    
        digit_check_divider = 1000000000
        num = 0
        digits = 0
        last_digit_added = 0
        while digit_check_divider!= 0:
            tail = x % digit_check_divider
            if  tail != x:                
                to_add = x - tail
                x -= to_add
                to_add = to_add/digit_check_divider * 10**digits
                num += to_add
            digits += 1            
            digit_check_divider /= 10
        #Trim trailing zeroes
        while num % 10 == 0:
            num = num /10
        if num > 2147483648: return 0
        return -num if negative else num
        
print(Solution().reverse(-90100))
#-2147483412
#-2143847402
#-2143847412