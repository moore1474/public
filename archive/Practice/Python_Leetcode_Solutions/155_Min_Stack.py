class MinStack(object):
    
    def __init__(self):
        self.stack = []
    
    def push(self, x):
        min = x if len(self.stack) == 0 else self.stack[len(self.stack)-1][1]
        min = min if min <= x else x
        self.stack.append((x, min))

    def pop(self):
        self.stack.pop()

    def top(self):
        return self.stack[len(self.stack)-1][0]
        

    def getMin(self):
        return self.stack[len(self.stack)-1][1]
        
minStack =  MinStack()
minStack.push(-2)
minStack.push(0)
minStack.push(-3)
print(minStack.getMin()) #-3
minStack.pop()
print(minStack.top()) #0
print(minStack.getMin()) #-2
print('------------')
minStack =  MinStack()
minStack.push(-2)
minStack.push(0)
minStack.push(-1)
print(minStack.getMin())#-2
print(minStack.top())#-1
minStack.pop()
print(minStack.getMin())#-2
