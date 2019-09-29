class Solution(object):
    def myAtoi(self, str):
        str = str.strip()
        num = 0
        on_digit = 1
        for i in range(len(str)-1, -1, -1):
            digit = ord(str[i]) - 48
            if str[i]=='-' or str[i]=='+':
                if i != 0: 
                    num = 0
                    on_digit = 1
                elif str[i]=='-': num = -1 * num
            elif digit<0 or digit>9: 
                num = 0
                on_digit = 1
            else:
                num += (on_digit * digit)
                on_digit *= 10
        num = num if num<2147483647 else 2147483647
        num = num if num>-2147483648 else -2147483648
        return num
print(Solution().myAtoi("-2433"))
