class Solution(object):
    def setZeroes(self, matrix):
        m = len(matrix)
        n = len(matrix[0])
        
        # Mark them
        for i in range(m):
            for j in range(n):
                if matrix[i][j] == 0: matrix[i][j] = 'x'                    
        
        # Delete them
        for i in range(m):
            for j in range(n):
                if matrix[i][j] == 'x':
                    matrix[i][j] = 0
                    for k in range(m):
                        if matrix[k][j] != 'x': matrix[k][j] = 0
                    for k in range(n):
                        if matrix[i][k] != 'x': matrix[i][k] = 0
        

matrix = [
            [0, 1, 1, 1],
            [1, 1, 1, 1],
            [1, 0, 1, 1],
            [1, 1, 1, 1]
        ]

Solution().setZeroes(matrix)
print(matrix)#[[0, 0, 0, 0], [0, 0, 1, 1], [0, 0, 0, 0], [0, 0, 1, 1]]

