package GoogleChallenge1till2;

import java.util.*;

/**
 * Created by ShIvS on 6/4/2017.
 */
public class MainFunction {

    public static void main(String[] args)
    {
        int distance = answer(9,18);
        System.out.println("distance=" + distance);

       /* for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                System.out.println(i + " " + j + " " + answer(i,j,solutionArray));
            }
        }*/


    }

    public static int answer(int src, int dest)
    {
        int[][] solutionArray = generateSolution();
        MatrixIndex srcMatrixIndex = getMatrixIndexOfPosition(src);
        MatrixIndex destMatrixIndex = getMatrixIndexOfPosition(dest);

        int rowDisplacement = srcMatrixIndex.row - destMatrixIndex.row;
        int colDisplacement = srcMatrixIndex.col - destMatrixIndex.col;

        if(rowDisplacement<0)
            rowDisplacement = -1 * rowDisplacement;
        if(colDisplacement<0)
            colDisplacement = -1 * colDisplacement;

        if(rowDisplacement == 1 && colDisplacement ==1 && (src==0 || dest==0 ||src==7 || dest==7 ||src==63 || dest==63 ||src==56 || dest==56))
            return 4;

        if(rowDisplacement == 1 && colDisplacement ==1 && (srcMatrixIndex.row!=0 || destMatrixIndex.row!=0))
            return 2;



        return solutionArray[rowDisplacement][colDisplacement];
        /*
        //Printing all possible convertions from number(0-63) to matrix index

        int x = 0;
        for(int i=0;i<8;i++)
        {
            for (int j=0; j<8; j++) {
                GoogleChallenge1till2.MatrixIndex matrixIndex = getMatrixIndexOfPosition(x);
                System.out.print(x + "-" + matrixIndex.toString());
                x++;
            }
            System.out.println("");
        }
        */


    }

    private static MatrixIndex getMatrixIndexOfPosition(int positionNumber)
    {
        MatrixIndex matrixIndex;
        int row = (positionNumber)/8;
        int col = (positionNumber)%8;
        matrixIndex = new MatrixIndex(row, col);
        return matrixIndex;
    }

    public static int[][] generateSolution()
    {
        int[][] solutionArray = new int[8][8];

        for (int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                solutionArray[i][j] = 1000;//some large value
            }
        }
        solutionArray[0][0] = 0;
        int numberOfEntiresInSolutionArray = 1;//have to fill all entries in solutionArray ie till numberOfEntiresInSolutionArray = 64

        Queue queue = new LinkedList();
        queue.add(new MatrixIndex(0, 0));

        //while(queue.size()!=0 && numberOfEntiresInSolutionArray<=64) {
        while(queue.size()!=0) {
            MatrixIndex currentIndex = (MatrixIndex) queue.remove();
            int currentIndexContent = solutionArray[currentIndex.row][currentIndex.col];
//          System.out.println("*"+currentIndexContent+"*");
            MatrixIndex neighbours[] = findKnightNeighbours(currentIndex);
            for(int i=0;i<8 && neighbours[i]!=null ;i++)
            {
                MatrixIndex m = neighbours[i];
                if(solutionArray[m.row][m.col]>(currentIndexContent+1) )
                {
                    solutionArray[m.row][m.col] = currentIndexContent + 1;
                    numberOfEntiresInSolutionArray++;
                    queue.add(m);
                }
            }
        }
        /*
        //Printing solution
        for (int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(solutionArray[i][j]+"-");
            }
            System.out.println();
        }
        */
        return solutionArray;
    }
    //function to find matrix indexes all positions reachable from Knights moves ie 2+1/1+2
    public static MatrixIndex[] findKnightNeighbours(MatrixIndex currentIndex)
    {
        //  assuming currentIndex is at (4,4) then neighbours reachable via knights move are at
        // (from left upper corner to clockwise)
        //  (3,2) (2,3) (2,5) (3,6)
        //  (5,6) (6,5) (6,3) (5,2)
        MatrixIndex neighbours[] = new MatrixIndex[8];

        int row, col, newRow, newCol;
        row = currentIndex.getRow();
        col = currentIndex.getCol();

        int i = 0;
        //1st neighbour (-1,-2)
        newRow = row-1;
        newCol = col-2;
        if(newCol>=0 && newRow>=0 && newCol<=7 && newRow<=7) {
            neighbours[i] = new MatrixIndex(newRow, newCol);
            i++;
        }

        //2nd neighbour (-2,-1)
        newRow = row-2;
        newCol = col-1;
        if(newCol>=0 && newRow>=0  && newCol<=7 && newRow<=7) {
            neighbours[i] = new MatrixIndex(newRow, newCol);
            i++;
        }

        //3rd neighbour (-2,+1)
        newRow = row-2;
        newCol = col+1;
        if(newCol>=0 && newRow>=0  && newCol<=7 && newRow<=7) {
            neighbours[i] = new MatrixIndex(newRow, newCol);
            i++;
        }

        //4th neighbour (-1,+2)
        newRow = row-1;
        newCol = col+2;
        if(newCol>=0 && newRow>=0  && newCol<=7 && newRow<=7) {
            neighbours[i] = new MatrixIndex(newRow, newCol);
            i++;
        }

        //5th neighbour (+1,+2)
        newRow = row+1;
        newCol = col+2;
        if(newCol>=0 && newRow>=0  && newCol<=7 && newRow<=7) {
            neighbours[i] = new MatrixIndex(newRow, newCol);
            i++;
        }

        //6th neighbour (+2,+1)
        newRow = row+2;
        newCol = col+1;
        if(newCol>=0 && newRow>=0 && newCol<=7 && newRow<=7) {
            neighbours[i] = new MatrixIndex(newRow, newCol);
            i++;
        }

        //7th neighbour (+2,-1)
        newRow = row+2;
        newCol = col-1;
        if(newCol>=0 && newRow>=0 && newCol<=7 && newRow<=7) {
            neighbours[i] = new MatrixIndex(newRow, newCol);
            i++;
        }

        //8th neighbour (+1,-2)
        newRow = row+1;
        newCol = col-2;
        if(newCol>=0 && newRow>=0 && newCol<=7 && newRow<=7) {
            neighbours[i] = new MatrixIndex(newRow, newCol);
            i++;
        }
        return neighbours;
    }
}
class MatrixIndex
{
    int row;
    int col;

    public MatrixIndex(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return ("("+row+","+col+")");
    }
}
