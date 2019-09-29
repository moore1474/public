from graphs.adj_list_graph import *
from ztesting.common_testing_functions import *

def test_adj_list_graph():
    start_high_level_test("Testing Adjacency List Graph Implementation")
    test_add_vertex_and_add_edge()
    breadth_first_traversal_test()
    depth_first_traversal_test()
    contract_vertext_test()
    dijsktras_test()
    test_prims_algorithm()

def test_add_vertex_and_add_edge():
    graph = Adjacency_List_Graph()
    one = Vertex(1)
    two = Vertex(2, {1:one})
    three = Vertex(3, {4: Vertex(4), 2:two})
    graph.add_vertices(one, two, three)
    four = one.edges[2].edges[3].edges[4]
    assertEquals('Add Vertex', four.val, 4)
    graph.add_edge(four, one)
    assertEquals('Add Edge', four.edges[1].val, 1)

def test_graph():
    graph = Adjacency_List_Graph()
    vertexMap = {}
    for i in range(1, 9):
        vertexMap[i] = Vertex(i)
    graph.add_edge(vertexMap[1], vertexMap[2])
    graph.add_edge(vertexMap[1], vertexMap[3])
    graph.add_edge(vertexMap[2], vertexMap[5])
    graph.add_edge(vertexMap[2], vertexMap[6])
    graph.add_edge(vertexMap[3], vertexMap[4])
    graph.add_edge(vertexMap[4], vertexMap[5])
    graph.add_edge(vertexMap[4], vertexMap[7])
    graph.add_edge(vertexMap[5], vertexMap[7])
    graph.add_edge(vertexMap[6], vertexMap[7])
    graph.add_edge(vertexMap[6], vertexMap[8])  
    graph.add_vertex(Vertex(9, {7:vertexMap[7], 8:vertexMap[8]}))
    return graph

def breadth_first_traversal_test():
    graph = test_graph()
    assertEquals('Breadth First Traversal', graph.breadth_first_traversal(), [1, 2, 3, 5, 6, 4, 7, 8, 9])
    graph.delete_vertex(5)
    assertEquals('Delete Vertex', graph.breadth_first_traversal(), [1, 2, 3, 6, 4, 8, 7, 9])
    
def depth_first_traversal_test():
    graph = test_graph()
    assertEquals('Depth First Traversal', graph.depth_first_traversal(),[1, 3, 4, 7, 6, 8, 9, 5, 2])
    graph.delete_edge(6, 8)
    graph.delete_edge(7, 9)
    assertEquals('Delete Edge', graph.depth_first_traversal(), [1, 3, 4, 7, 6, 5, 2])
    
def dijsktras_test():
    graph = Adjacency_List_Graph()
    vertexMap = {}
    for i in range(1, 7):
        vertexMap[i] = Vertex(i)
    graph.add_edge(vertexMap[1], vertexMap[2], 3)
    graph.add_edge(vertexMap[1], vertexMap[3], 4)
    graph.add_edge(vertexMap[1], vertexMap[4], 2)
    graph.add_edge(vertexMap[2], vertexMap[5], 2)
    graph.add_edge(vertexMap[2], vertexMap[3], 4)
    graph.add_edge(vertexMap[3], vertexMap[5], 6)
    graph.add_edge(vertexMap[4], vertexMap[5], 1)
    graph.add_edge(vertexMap[4], vertexMap[6], 4)
    graph.add_edge(vertexMap[5], vertexMap[6], 2)
    costs = graph.get_shortest_path_costs(vertexMap[1])
    assertEquals("Dijsktra's Algorithm", str(costs),'{1: 0, 2: 3, 3: 4, 4: 2, 5: 3, 6: 5}')

def contract_vertext_test():
    graph = test_graph()
    graph.contract_vertex(5, 7)
    assertEquals('Contract Vertex', graph.breadth_first_traversal(),'12356498')

def test_prims_algorithm():
    graph = Adjacency_List_Graph()
    vertexMap = {}
    vertexMap['A'] = Vertex('A')
    vertexMap['B'] = Vertex('B')
    vertexMap['C'] = Vertex('C')
    vertexMap['D'] = Vertex('D')
    vertexMap['E'] = Vertex('E')
    vertexMap['F'] = Vertex('F')
    vertexMap['G'] = Vertex('G')
    graph.add_edge(vertexMap['A'], vertexMap['B'], 2)
    graph.add_edge(vertexMap['A'], vertexMap['D'], 6)
    graph.add_edge(vertexMap['A'], vertexMap['F'], 5)
    graph.add_edge(vertexMap['B'], vertexMap['C'], 7)
    graph.add_edge(vertexMap['C'], vertexMap['D'], 9)
    graph.add_edge(vertexMap['C'], vertexMap['F'], 1)
    graph.add_edge(vertexMap['D'], vertexMap['G'], 8)
    graph.add_edge(vertexMap['E'], vertexMap['D'], 4)
    graph.add_edge(vertexMap['E'], vertexMap['F'], 3)
    parents = graph.get_mst(vertexMap['A'])
    assertEquals("Prim's Algorithm", str(parents),"{'A': None, 'C': 'F', 'B': 'A', 'E': 'F', 'D': 'E', 'G': 'D', 'F': 'A'}")


if __name__=='__main__':
    test_adj_list_graph()