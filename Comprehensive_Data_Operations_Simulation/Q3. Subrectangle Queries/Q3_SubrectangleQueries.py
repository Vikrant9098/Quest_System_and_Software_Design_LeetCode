class SubrectangleQueries(object):

    def __init__(self, rectangle):
        """
        :type rectangle: List[List[int]]
        """
        self.rect = rectangle                          # store the matrix

    def updateSubrectangle(self, row1, col1, row2, col2, newValue):
        """
        :type row1: int
        :type col1: int
        :type row2: int
        :type col2: int
        :type newValue: int
        :rtype: None
        """
        for r in range(row1, row2 + 1):                # loop through rows in range
            for c in range(col1, col2 + 1):            # loop through columns in range
                self.rect[r][c] = newValue             # update each cell

    def getValue(self, row, col):
        """
        :type row: int
        :type col: int
        :rtype: int
        """
        return self.rect[row][col]                    # return value at (row, col)
