package chessGame;

import chessGame.records.PieceState;
import chessPieces.*;

public final class ChessPieceFactory {
    private ChessPieceFactory() {
    }

    public static ChessPiece create(PieceState state, int row, int column) {
        String prefix = state.color() == PieceColor.WHITE ? "white" : "black";
        String name = switch (state.type()) {
            case KING -> "King";
            case QUEEN -> "Queen";
            case ROOK -> "Rook";
            case BISHOP -> "Bishop";
            case KNIGHT -> "Knight";
            case PAWN -> "Pawn";
        };
        String image = prefix + name + ".png";
        ChessPiece piece = switch (state.type()) {
            case KING -> new King(state.color(), image, row, column);
            case QUEEN -> new Queen(state.color(), image, row, column);
            case ROOK -> new Rook(state.color(), image, row, column);
            case BISHOP -> new Bishop(state.color(), image, row, column);
            case KNIGHT -> new Knight(state.color(), image, row, column);
            case PAWN -> new Pawn(state.color(), image, row, column);
        };
        piece.setPieceFirstMove(state.firstMove());
        return piece;
    }
}
