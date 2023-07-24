package lk.ijse.dep.service;

public class BoardImpl implements Board{
    private final Piece[][] pieces = new Piece[6][5];

    private final BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        for (int i=0; i<6; i++){
            for (int j=0; j<5; j++){
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i=0; i<NUM_OF_ROWS; i++){
            if (pieces[col][i].equals(Piece.EMPTY)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
//        if (findNextAvailableSpot(col)==-1){
//            return false;
//        }else {
//            return true;
//        }
        return findNextAvailableSpot(col) != -1;
    }

    @Override
    public boolean existLegalMoves() {
        for (int i=0; i<6; i++){
            for (int j=0; j<5; j++){
                if (pieces[i][j].equals(Piece.EMPTY)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        pieces[col][findNextAvailableSpot(col)]=move;
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {
        for (int i=0; i<NUM_OF_ROWS; i++){
            for (int j=0; j<NUM_OF_COLS/2; j++){
                if((!pieces[j][i].equals(Piece.EMPTY)) && pieces[j][i].equals(pieces[j+1][i]) && pieces[j][i].equals(pieces[j+2][i]) && pieces[j][i].equals(pieces[j+3][i])){
                    return new Winner(pieces[j][i],j,i,j+3,i);
                }
            }
        }
        for (int j=0; j<NUM_OF_COLS; j++){
            for (int i=0; i<NUM_OF_ROWS/2; i++){
                if (!pieces[j][i].equals(Piece.EMPTY) && pieces[j][i].equals(pieces[j][i+1]) && pieces[j][i].equals(pieces[j][i+2]) && pieces[j][i].equals(pieces[j][i+3])){
                    return new Winner(pieces[j][i],j,i,j,i+3);
                }
            }
        }
        return null;
    }
}
