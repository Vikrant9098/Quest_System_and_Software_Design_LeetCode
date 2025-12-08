class SubrectangleQueries {

    private int[][] rect;                          // store the rectangle

    public SubrectangleQueries(int[][] rectangle) {
        rect = rectangle;                          // copy the reference to the matrix
    }
    
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {

        for (int r = row1; r <= row2; r++) {       // loop through all rows in the given range
            for (int c = col1; c <= col2; c++) {   // loop through all columns in the range
                rect[r][c] = newValue;             // update each cell with newValue
            }
        }
    }
    
    public int getValue(int row, int col) {
        return rect[row][col];                     // simply return the value at (row, col)
    }
}
