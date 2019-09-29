class Solution(object):
    def isValid(self, s):
        open_stack = []
        for char in s:
            if char in ['(', '{', '[']:
                open_stack.append(char);
            elif char in [')', '}', ']']:
                if len(open_stack) == 0: return False
                should_match = open_stack.pop()
                if char == ')' and should_match != '(': return False
                elif char == '}' and should_match != '{': return False
                elif char == ']' and should_match != '[': return False
            else: return False
        return len(open_stack)==0
    
print(Solution().isValid('['))