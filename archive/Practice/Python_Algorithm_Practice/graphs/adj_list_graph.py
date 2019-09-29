from lists.linked.linked_list import Node
from lists.linked.linked_list import LinkedList
import math
from trees.heap import *

#Sample Graph: http://techieme.in/breadth-first-traversal/

class Vertex:
    
    sort_prop = 'val'
    
    def __init__(self, val, edges = None, weights = None):
        self.val = val
        self.edges = {} if edges is None else edges
        self.weights = {} if weights is None else weights
        
    def degree(self):
        return len(self.edges)
    
    def __eq__(self, other):
        return self.val == self.val
    
    def __lt__(self, other):
        return getattr(self, self.sort_prop) < getattr(other, self.sort_prop)
    
    def __le__(self, other):
        return getattr(self, self.sort_prop) <= getattr(other, self.sort_prop)
    
    def __gt__(self, other):
        return getattr(self, self.sort_prop) > getattr(other, self.sort_prop)
    
    def __ge__(self, other):
        return getattr(self, self.sort_prop) >= getattr(other, self.sort_prop)
    
    
    
class Adjacency_List_Graph():
    
    def __init__(self, directed=False):
        self.directed = directed
        self.vertices = {}
    
    def add_vertex(self, v):
        self.vertices[v.val] = v
        if not self.directed:
            for vertex in v.edges.values():
                if vertex.val not in self.vertices:
                    self.add_vertex(Vertex(vertex.val))
                vertex.edges[v.val]=v 
                
    def add_vertices(self, *vertices):
        for vertex in vertices:
            self.add_vertex(vertex)
        
    def add_edge(self, a, b, weight = None):
        if a.val not in self.vertices: self.vertices[a.val] = a
        if b.val not in self.vertices: self.vertices[b.val] = b
        a.edges[b.val] = b
        if weight: a.weights[b.val] = weight
        if not self.directed: b.edges[a.val] = a 
            
    def delete_vertex(self, vertex):
        for edge in list(self.vertices[vertex].edges):
            self.delete_edge(vertex, edge)
        self.vertices[vertex] = None
            
    def delete_edge(self, a, b):
        self.vertices[a].edges.pop(b, None)
        if not self.directed: 
            self.vertices[b].edges.pop(a, None)
    
    def contract_vertex(self, v1, v2):
        pass
    
    def depth_first_traversal(self, source=None, find=None):
        path = []
        stack = []
        visited_or_in_stack = {}
        
        stack.append(self.vertices.values()[0])
        visited_or_in_stack[stack[0].val] = True
        while stack:
            
            visiting = stack.pop()
            path.append(visiting.val)
            
            
            for vertex in visiting.edges.values():
                if vertex.val not in visited_or_in_stack: 
                    stack.append(vertex)
                    visited_or_in_stack[vertex.val] = True
            
        return path
            
    
    def breadth_first_traversal(self, source=None, find=None):
        path = []
        queue = []
        visited_or_in_stack = {}
        
        queue.append(self.vertices.values()[0])
        visited_or_in_stack[queue[0].val] = True
        while queue:
            
            visiting = queue.pop(0)
            path.append(visiting.val)
            
            
            for vertex in visiting.edges.values():
                if vertex.val not in visited_or_in_stack: 
                    queue.append(vertex)
                    visited_or_in_stack[vertex.val] = True
            
        return path
    
    def get_shortest_path_costs(self, source):
        pass
    
    def get_mst(self, root):
        pass
            

if __name__ == '__main__':
    import ztesting.graphs_test.adj_list_graph_test as test
    test.test_adj_list_graph()