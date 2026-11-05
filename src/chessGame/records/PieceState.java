package chessGame.records;

import chessGame.PieceType;
import chessGame.PieceColor;

public record PieceState(
        PieceType type,
        PieceColor color,
        boolean firstMove
) {
}
