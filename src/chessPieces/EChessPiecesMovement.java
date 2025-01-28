package chessPieces;

import chessGame.ChessPieceMovement;
import chessGame.Chessboard;

import java.awt.*;

public enum EChessPiecesMovement{
    PAWN(){
        @Override
        public void checkColorOfPawn(Chessboard chessboard, ChessPiece piece){
            if (piece.getChessPieceColor().equals(Color.BLACK)){
                blackPawnMoves(chessboard, piece);
            }else whitePawnMoves(chessboard, piece);
        }

        private void blackPawnMoves(Chessboard chessboard, ChessPiece piece){
            pawnMoveDown(chessboard, piece);
            blackPawnAttacks(chessboard, piece);
        }

        private void blackPawnAttacks(Chessboard chessboard, ChessPiece piece){
            pawnMoveDiagonallyDownLeft(chessboard, piece);
            pawnMoveDiagonallyDownRight(chessboard, piece);
        }

        private void pawnMoveDiagonallyDownLeft(Chessboard chessboard, ChessPiece piece){
            possibleAttackMove(piece, chessboard,piece.getRowPosition()+1, piece.getColumnPosition()-1);
        }

        private void pawnMoveDiagonallyDownRight(Chessboard chessboard, ChessPiece piece){
            possibleAttackMove(piece, chessboard,piece.getRowPosition()+1, piece.getColumnPosition()+1);
        }

        private void pawnMoveDown(Chessboard chessboard, ChessPiece piece){
            for (int row = piece.getRowPosition()+1; row <= piece.getRowPosition()+2; row++){
                if (possibleBasicMove(piece, chessboard, row, piece.getColumnPosition())){
                    break;
                }
            }
        }
        private boolean possibleBasicMove(ChessPiece piece, Chessboard chessboard, int row, int column){
            return isMoveValid(piece, chessboard, row, column) || !piece.getPieceFirstMove();
        }

        private void whitePawnMoves(Chessboard chessboard, ChessPiece piece){
            pawnMoveUp(chessboard, piece);
            whitePawnAttacks(chessboard, piece);
        }

        private void whitePawnAttacks(Chessboard chessboard, ChessPiece piece){
            pawnMoveDiagonallyUpLeft(chessboard, piece);
            pawnMoveDiagonallyUpRight(chessboard, piece);
        }

        private void pawnMoveUp(Chessboard chessboard, ChessPiece piece){
            for (int row = piece.getRowPosition()-1; row >= piece.getRowPosition()-2; row--){
                if (possibleBasicMove(piece, chessboard, row, piece.getColumnPosition())){
                    break;
                }
            }
        }

        private void pawnMoveDiagonallyUpLeft(Chessboard chessboard, ChessPiece piece){
            possibleAttackMove(piece, chessboard,piece.getRowPosition()-1,piece.getColumnPosition()-1);
        }

        private void pawnMoveDiagonallyUpRight(Chessboard chessboard, ChessPiece piece){
            possibleAttackMove(piece, chessboard,piece.getRowPosition()-1,piece.getColumnPosition()+1);
        }

        private void possibleAttackMove(ChessPiece piece, Chessboard chessboard, int rowToCheck, int columnToCheck) {
            if (ChessPieceMovement.isOutOfBorder(rowToCheck, columnToCheck, chessboard)){
            }else if (ChessPieceMovement.positionIsTaken(rowToCheck, columnToCheck, chessboard)
                    && ChessPieceMovement.pieceIsAttacking(piece, rowToCheck, columnToCheck, chessboard)) {
                if (!chessboard.getGameManager().isValidationInProcess()){
                    if (chessboard.getGameManager().invalidMove(piece, chessboard.getArrayBoard()[rowToCheck][columnToCheck])){
                        return;
                    }
                }
                piece.getChessPieceMovementMap().put(chessboard.getArrayBoard()[rowToCheck][columnToCheck], piece);
            }
        }

        private boolean isMoveValid(ChessPiece piece, Chessboard chessboard, int rowToCheck, int columnToCheck) {
            if (ChessPieceMovement.isOutOfBorder(rowToCheck, columnToCheck, chessboard)){
                return true;
            }else if (!ChessPieceMovement.positionIsTaken(rowToCheck, columnToCheck, chessboard)){
                if (!chessboard.getGameManager().isValidationInProcess()){
                    if (chessboard.getGameManager().invalidMove(piece, chessboard.getArrayBoard()[rowToCheck][columnToCheck])){
                        return false;
                    }
                }
                piece.getChessPieceMovementMap().put(chessboard.getArrayBoard()[rowToCheck][columnToCheck], piece);
                return false;
            }
            return true;
        }

    },
    ROOK(){},
    KNIGHT(){
        public  void knightMoveUpShortLeft(Chessboard chessboard, Knight piece){
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition()-1, piece.getColumnPosition()-2, chessboard);
        }
        public  void knightMoveUpShortRight(Chessboard chessboard, Knight piece){
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition()-1, piece.getColumnPosition()+2, chessboard);
        }
        public  void knightMoveUpLongLeft(Chessboard chessboard, Knight piece){
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition()-2, piece.getColumnPosition()-1, chessboard);
        }
        public  void knightMoveUpLongRight(Chessboard chessboard, Knight piece){
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition()-2, piece.getColumnPosition()+1, chessboard);
        }
        public  void knightMoveDownShortLeft(Chessboard chessboard, Knight piece){
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition()+1, piece.getColumnPosition()-2, chessboard);
        }
        public  void knightMoveDownShortRight(Chessboard chessboard, Knight piece){
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition()+1, piece.getColumnPosition()+2, chessboard);
        }
        public  void knightMoveDownLongLeft(Chessboard chessboard, Knight piece){
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition()+2, piece.getColumnPosition()-1, chessboard);
        }
        public  void knightMoveDownLongRight(Chessboard chessboard, Knight piece){
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition()+2, piece.getColumnPosition()+1, chessboard);
        }
    },
    BISHOP(){},
    QUEEN(){},
    KING(){
        @Override
        public void moveUp(Chessboard chessboard, ChessPiece piece) {
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition()-1, piece.getColumnPosition(), chessboard);
        }

        @Override
        public void moveDown(Chessboard chessboard, ChessPiece piece) {
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition()+1, piece.getColumnPosition(), chessboard);
        }

        @Override
        public void moveLeft(Chessboard chessboard, ChessPiece piece) {
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition(), piece.getColumnPosition()-1, chessboard);
        }

        @Override
        public void moveRight(Chessboard chessboard, ChessPiece piece) {
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition(), piece.getColumnPosition()+1, chessboard);
        }

        @Override
        public void moveDiagonallyUpLeft(Chessboard chessboard, ChessPiece piece) {
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition()-1, piece.getColumnPosition()-1, chessboard);
        }

        @Override
        public void moveDiagonallyDownLeft(Chessboard chessboard, ChessPiece piece) {
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition()+1, piece.getColumnPosition()-1, chessboard);
        }

        @Override
        public void moveDiagonallyUpRight(Chessboard chessboard, ChessPiece piece) {
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition()-1, piece.getColumnPosition()+1, chessboard);
        }

        @Override
        public void moveDiagonallyDownRight(Chessboard chessboard, ChessPiece piece) {
            ChessPieceMovement.movePossibility(piece, piece.getRowPosition()+1, piece.getColumnPosition()+1, chessboard);
        }
    };
    public void checkColorOfPawn(Chessboard chessboard, ChessPiece piece){

    }
    public void moveDiagonallyDownRight(Chessboard chessboard, ChessPiece piece){
        int row = piece.getRowPosition();
        int colum = piece.getColumnPosition();
        while (row <= 7 || colum <= 7){
            row++;
            colum++;
            if (ChessPieceMovement.movePossibility(piece, row, colum, chessboard)){
                break;
            }
        }
    }
    public void moveDiagonallyUpRight(Chessboard chessboard, ChessPiece piece){
        int row = piece.getRowPosition();
        int colum = piece.getColumnPosition();
        while (row >= 0 || colum <= 7){
            row--;
            colum++;
            if (ChessPieceMovement.movePossibility(piece, row, colum, chessboard)){
                break;
            }
        }
    }
    public void moveDiagonallyDownLeft(Chessboard chessboard, ChessPiece piece){
        int row = piece.getRowPosition();
        int colum = piece.getColumnPosition();
        while (row >= 7 || colum >= 0){
            row++;
            colum--;
            if (ChessPieceMovement.movePossibility(piece, row, colum, chessboard)){
                break;
            }
        }
    }
    public void moveDiagonallyUpLeft(Chessboard chessboard, ChessPiece piece){
        int row = piece.getRowPosition();
        int colum = piece.getColumnPosition();
        while (row >= 0 || colum >= 0){
            row--;
            colum--;
            if (ChessPieceMovement.movePossibility(piece, row, colum, chessboard)){
                break;
            }
        }
    }
    public void moveRight(Chessboard chessboard, ChessPiece piece){
        for (int colum = piece.getColumnPosition()+1; colum<= 7; colum++){
            if (ChessPieceMovement.movePossibility(piece, piece.getRowPosition(), colum, chessboard)){
                break;
            }
        }
    }
    public void moveLeft(Chessboard chessboard, ChessPiece piece){
        for (int colum = piece.getColumnPosition()-1; colum>= 0; colum--){
            if (ChessPieceMovement.movePossibility(piece, piece.getRowPosition(), colum, chessboard)){
                break;
            }
        }
    }
    public void moveDown(Chessboard chessboard, ChessPiece piece){
        for (int row = piece.getRowPosition()+1; row<= 7; row++){
            if (ChessPieceMovement.movePossibility(piece, row, piece.getColumnPosition(), chessboard)){
                break;
            }
        }
    }

    public void moveUp(Chessboard chessboard, ChessPiece piece){
        for (int row = piece.getRowPosition()-1; row >= 0 ; row--){
            if (ChessPieceMovement.movePossibility(piece, row, piece.getColumnPosition(), chessboard)){
                break;
            }
        }
    }
    public  void knightMoveUpShortLeft(Chessboard chessboard, Knight piece){
    }
    public  void knightMoveUpShortRight(Chessboard chessboard, Knight piece){
    }
    public  void knightMoveUpLongLeft(Chessboard chessboard, Knight piece){
    }
    public  void knightMoveUpLongRight(Chessboard chessboard, Knight piece){
    }
    public  void knightMoveDownShortLeft(Chessboard chessboard, Knight piece){
    }
    public  void knightMoveDownShortRight(Chessboard chessboard, Knight piece){
    }
    public  void knightMoveDownLongLeft(Chessboard chessboard, Knight piece){
    }
    public  void knightMoveDownLongRight(Chessboard chessboard, Knight piece){
    }
}
