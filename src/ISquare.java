import java.awt.*;

public interface ISquare {

    int getRowPosition();
    int getColumnPosition();

    void setPieceOnSquare(ChessPiece piece);

    ChessPiece getPieceOnSquare();

    Component getEmptyPiecePanel();

    Color getEmptyPieceColor();

    void markTheSquareForAttack();

    void markTheSquareForMove();

    void discardPieceFromSquare();

}
