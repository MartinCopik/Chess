package chessGame.screens;

import chessGame.records.GameSummary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.OptionalLong;

public final class SavedGamesDialog extends JDialog {
    private OptionalLong selectedGame = OptionalLong.empty();

    private SavedGamesDialog(JFrame owner, List<GameSummary> games, long currentGameId) {
        super(owner, "Saved games", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Game", "White", "Black", "Status", "Position", "Last saved (UTC)"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
            @Override public Class<?> getColumnClass(int column) { return column == 0 ? Long.class : String.class; }
        };
        for (GameSummary game : games) {
            model.addRow(new Object[]{
                    game.id(), game.whitePlayerName(), game.blackPlayerName(), statusText(game.status()),
                    game.historyPosition() + " / " + game.moveCount(), game.updatedAt()
            });
        }

        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        table.setRowHeight(26);
        table.getColumnModel().getColumn(0).setPreferredWidth(55);
        table.getColumnModel().getColumn(5).setPreferredWidth(190);

        JButton open = new JButton("Open game");
        JButton cancel = new JButton("Cancel");
        open.setEnabled(false);
        table.getSelectionModel().addListSelectionListener(event -> open.setEnabled(table.getSelectedRow() >= 0));
        open.addActionListener(event -> {
            int viewRow = table.getSelectedRow();
            if (viewRow >= 0) {
                selectedGame = OptionalLong.of(games.get(table.convertRowIndexToModel(viewRow)).id());
                dispose();
            }
        });
        cancel.addActionListener(event -> dispose());
        table.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2 && table.rowAtPoint(event.getPoint()) >= 0) {
                    open.doClick();
                }
            }
        });
        for (int row = 0; row < games.size(); row++) {
            if (games.get(row).id() == currentGameId) {
                table.setRowSelectionInterval(row, row);
                break;
            }
        }
        if (table.getSelectedRow() < 0 && !games.isEmpty()) {
            table.setRowSelectionInterval(0, 0);
        }

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        content.add(new JLabel(games.isEmpty() ? "No saved games yet." : "Select a game to continue or review."), BorderLayout.NORTH);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(850, 300));
        content.add(scroll, BorderLayout.CENTER);
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(open);
        actions.add(cancel);
        content.add(actions, BorderLayout.SOUTH);
        setContentPane(content);
        getRootPane().setDefaultButton(open);
        getRootPane().registerKeyboardAction(event -> dispose(), KeyStroke.getKeyStroke("ESCAPE"),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        setMinimumSize(new Dimension(620, 260));
        pack();
        setLocationRelativeTo(owner);
    }

    public static OptionalLong choose(JFrame owner, List<GameSummary> games, long currentGameId) {
        SavedGamesDialog dialog = new SavedGamesDialog(owner, List.copyOf(games), currentGameId);
        dialog.setVisible(true);
        return dialog.selectedGame;
    }

    private static String statusText(String status) {
        return switch (status) {
            case "IN_PROGRESS" -> "In progress";
            case "WHITE_WON" -> "White won";
            case "BLACK_WON" -> "Black won";
            case "DRAW" -> "Draw";
            default -> "Unknown";
        };
    }
}
