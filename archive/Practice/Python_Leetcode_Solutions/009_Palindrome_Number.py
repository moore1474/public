class Solution(object):
    def isPalindrome(self, x):
        if x<0: return False
        left_div = 1000000000
        right_div = 10
        while left_div >= right_div:
            if x % left_div != x:
                l = int(x/left_div)
                l = l - (l-l%10)
                r = x - (x-x%right_div)
                r = r - (r%(right_div/10))
                r = r/(right_div/10)
                if l != r : return False
                right_div *=10
            left_div /= 10
        return True
    
print(Solution().isPalindrome(54145))