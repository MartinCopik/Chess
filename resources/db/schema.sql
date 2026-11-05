PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS players (
    id         INTEGER PRIMARY KEY,
    name       TEXT NOT NULL CHECK (length(trim(name)) > 0),
    created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS games (
    id INTEGER PRIMARY KEY,

    white_player_id INTEGER NOT NULL,
    black_player_id INTEGER NOT NULL,

    history_position INTEGER NOT NULL DEFAULT 0
        CHECK (history_position >= 0),

    status TEXT NOT NULL DEFAULT 'IN_PROGRESS'
        CHECK (status IN (
            'IN_PROGRESS',
            'WHITE_WON',
            'BLACK_WON',
            'DRAW'
        )),

    created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (white_player_id)
        REFERENCES players(id) ON DELETE RESTRICT,

    FOREIGN KEY (black_player_id)
        REFERENCES players(id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS moves (
    game_id INTEGER NOT NULL,
    move_number INTEGER NOT NULL CHECK (move_number >= 1),

    from_row INTEGER NOT NULL CHECK (from_row BETWEEN 0 AND 7),
    from_column INTEGER NOT NULL CHECK (from_column BETWEEN 0 AND 7),
    to_row INTEGER NOT NULL CHECK (to_row BETWEEN 0 AND 7),
    to_column INTEGER NOT NULL CHECK (to_column BETWEEN 0 AND 7),

    moving_type TEXT NOT NULL
        CHECK (moving_type IN ('KING', 'QUEEN', 'ROOK', 'BISHOP', 'KNIGHT', 'PAWN')),
    moving_color TEXT NOT NULL CHECK (moving_color IN ('WHITE', 'BLACK')),
    moving_first_move INTEGER NOT NULL CHECK (moving_first_move IN (0, 1)),

    captured_type TEXT
        CHECK (captured_type IN ('KING', 'QUEEN', 'ROOK', 'BISHOP', 'KNIGHT', 'PAWN')),
    captured_color TEXT CHECK (captured_color IN ('WHITE', 'BLACK')),
    captured_first_move INTEGER CHECK (captured_first_move IN (0, 1)),

    castling_row INTEGER CHECK (castling_row BETWEEN 0 AND 7),
    rook_from_column INTEGER CHECK (rook_from_column BETWEEN 0 AND 7),
    rook_to_column INTEGER CHECK (rook_to_column BETWEEN 0 AND 7),
    rook_first_move INTEGER CHECK (rook_first_move IN (0, 1)),

    promoted_to TEXT CHECK (promoted_to IN ('QUEEN', 'ROOK', 'BISHOP', 'KNIGHT')),

    PRIMARY KEY (game_id, move_number),

    FOREIGN KEY (game_id)
        REFERENCES games(id) ON DELETE CASCADE,

    CHECK (
        (captured_type IS NULL
            AND captured_color IS NULL
            AND captured_first_move IS NULL)
        OR
        (captured_type IS NOT NULL
            AND captured_color IS NOT NULL
            AND captured_first_move IS NOT NULL)
    ),

    CHECK (
        (castling_row IS NULL
            AND rook_from_column IS NULL
            AND rook_to_column IS NULL
            AND rook_first_move IS NULL)
        OR
        (castling_row IS NOT NULL
            AND rook_from_column IS NOT NULL
            AND rook_to_column IS NOT NULL
            AND rook_first_move IS NOT NULL)
    ),

    CHECK (promoted_to IS NULL OR moving_type = 'PAWN'),

    CHECK (
        castling_row IS NULL
        OR (moving_type = 'KING'
            AND captured_type IS NULL
            AND promoted_to IS NULL)
    )
);