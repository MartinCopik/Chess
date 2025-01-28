package chessPieces.MobilityOfPieces;

import chessGame.ChessPieceMovement;
import chessGame.Chessboard;
import chessPieces.Knight;

public interface KnightMobility {

    default  void knightMoveUpShortLeft(Chessboard chessboard, Knight knight){
        ChessPieceMovement.movePossibility(knight, knight.getRowPosition()-1, knight.getColumnPosition()-2, chessboard);
    }
    default  void knightMoveUpShortRight(Chessboard chessboard, Knight knight){
        ChessPieceMovement.movePossibility(knight, knight.getRowPosition()-1, knight.getColumnPosition()+2, chessboard);
    }
    default  void knightMoveUpLongLeft(Chessboard chessboard, Knight knight){
        ChessPieceMovement.movePossibility(knight, knight.getRowPosition()-2, knight.getColumnPosition()-1, chessboard);
    }
    default  void knightMoveUpLongRight(Chessboard chessboard, Knight knight){
        ChessPieceMovement.movePossibility(knight, knight.getRowPosition()-2, knight.getColumnPosition()+1, chessboard);
    }
    default  void knightMoveDownShortLeft(Chessboard chessboard, Knight knight){
        ChessPieceMovement.movePossibility(knight, knight.getRowPosition()+1, knight.getColumnPosition()-2, chessboard);
    }
    default  void knightMoveDownShortRight(Chessboard chessboard, Knight knight){
        ChessPieceMovement.movePossibility(knight, knight.getRowPosition()+1, knight.getColumnPosition()+2, chessboard);
    }
    default  void knightMoveDownLongLeft(Chessboard chessboard, Knight knight){
        ChessPieceMovement.movePossibility(knight, knight.getRowPosition()+2, knight.getColumnPosition()-1, chessboard);
    }
    default  void knightMoveDownLongRight(Chessboard chessboard, Knight knight){
        ChessPieceMovement.movePossibility(knight, knight.getRowPosition()+2, knight.getColumnPosition()+1, chessboard);
    }
}
