class Node(object):
    
    def __init__(self, val, next=None):
        self.val = val
        self.next = next
    
    def __str__(self):
        return str(self.val)

def as_node(node):
    if node is None or type(node) is Node:
        return node
    else: return Node(node)

class LinkedList:    
    
    def __init__(self, head=None):
        self.head = as_node(head)
    
    def size(self):
        size = 0
        curr = self.head
        while curr != None:
            size += 1
            curr = curr.next
        return size
    
    def printAll(self):
        to_return = ''
        curr = self.head
        while curr is not None:
            to_return = to_return + str(curr.val) + ' '
            curr = curr.next
        return to_return.strip()
            
    def empty(self):
        return self.head == None
    
    def valAt(self, indx):
        curr = self.head
        while indx != 0 and curr is not None:
            curr = curr.next
            indx -= 1
        return curr.val
    
    def push_front(self, node):
        node = as_node(node)
        tmp = self.head
        self.head = node
        self.head.next = tmp
        
    def pop_front(self):
        if self.head == None: return None
        tmp = self.head
        self.head = self.head.next
        return tmp
    
    def push(self, node, insert_on=None):
        node = as_node(node)
        if insert_on is None: insert_on = self.head
        if self.head is None: self.head = node
        else: 
            if insert_on.next is None: insert_on.next = node
            else: self.push(node, insert_on.next)
            
    def peek(self):
        if self.head == None: return None
        curr = self.head
        while curr.next != None: curr = curr.next
        return curr
    
    def peek_front(self):
        return self.head
    
    def insert(self, indx, node):
        node = as_node(node)
        cnt = 0
        curr = self.head
        while cnt < indx and curr.next != None:
            cnt += 1
            curr = curr.next
        if curr != None:
            tmp = curr.next
            curr.next = node
            node.next = tmp
    
    def erase(self, indx):
        cnt = 0
        curr = self.head
        while curr != None and cnt+1 < indx:
            curr = curr.next
            cnt += 1
        if curr != None and curr.next != None:            
            curr.next = curr.next.next
            
    
    def value_n_back(self, n):        
        num_of_nodes = self.size()
        cnt = 0
        curr = self.head
        while num_of_nodes - cnt - 1 > n and curr != None:
            cnt += 1
            curr = curr.next
        if curr is None: return curr
        return curr.val
            
    def pop(self, remove = True):
        curr, prev = self.head, None
        if curr.next == None:
            self.head = None
            return curr
        while curr != None and curr.next != None:
            prev = curr
            curr = curr.next
        prev.next = None
        return curr
    
    def indx_of_val(self, val):
        indx = 0
        curr = self.head
        while curr != None and curr.val != val:
            curr = curr.next
            indx += 1
        if curr == None: return -1
        return indx
        
    def remove_val(self, val):
        curr, prev = None, None
        if self.head is not None and self.head.val == val:
            self.head = self.head.next
        else: curr = self.head
        while curr != None:
            if curr.val == val:
                prev.next = curr.next
                break
            prev = curr
            curr = curr.next
        return self.head
    
    def reverse(self):
        curr = self.head
        prev = None
        while curr.next!= None:
            tmp = curr
            curr = curr.next
            tmp.next = prev
            prev = tmp
        curr.next = prev
        self.head = curr

if __name__=='__main__':              
    import ztesting.lists_test.linked_list_test as ll_test
    ll_test.test_linked_list()
