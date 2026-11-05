package chessGame.records;

import chessGame.PieceType;

public record PendingMove(
        int fromRow,
        int fromColumn,
        int toRow,
        int toColumn,
        PieceState movingBefore,
        PieceState capturedBefore,
        CastlingMove castlingMove
) {

    public Move complete(PieceType promotedTo){
        return new Move(
                fromRow,
                fromColumn,
                toRow,
                toColumn,
                movingBefore,
                capturedBefore,
                castlingMove,
                promotedTo
        );
    }
}
