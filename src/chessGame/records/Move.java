package chessGame.records;

import chessGame.PieceType;

public record Move(
        //ChessPiece chessPiece,
        int fromRow,
        int fromColumn,
        int toRow,
        int toColumn,
        PieceState movingBefore,
        PieceState capturedBefore,
        CastlingMove castlingMove,
        PieceType promotedTo
) {
    public boolean capture(){
        return capturedBefore != null;
    }

    public boolean castling(){
        return castlingMove != null;
    }

    public boolean promotion(){
        return promotedTo != null;
    }
}
