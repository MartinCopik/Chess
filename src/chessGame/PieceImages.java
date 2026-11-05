package chessGame;

import javax.swing.ImageIcon;
import java.awt.MediaTracker;
import java.net.URL;

public final class PieceImages {

    private PieceImages() {
    }

    public static ImageIcon load(String fileName) {
        String resourcePath = "/pieces/" + fileName;
        URL resource = PieceImages.class.getResource(resourcePath);
        if (resource == null) {
            throw new IllegalStateException("Missing piece image: " + resourcePath);
        }

        ImageIcon icon = new ImageIcon(resource);
        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            throw new IllegalStateException("Could not load piece image: " + resourcePath);
        }
        return icon;
    }
}
