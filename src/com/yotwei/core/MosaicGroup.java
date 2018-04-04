package com.yotwei.core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by YotWei on 2018/4/3.
 */
public class MosaicGroup {

    public MosaicGroup() {
        list = new ArrayList<>();
    }

    private List<MosaicPiece> list;
    private int counter = 0;

    public void addPiece(String path, BufferedImage image) {
        list.add(new MosaicPiece(path, image));
    }

    public BufferedImage nextPiece() throws IOException {
        if (counter >= list.size()) {
            counter = 0;
            Collections.shuffle(list);
        }
        return list.get(counter++).getImage();
    }

    private class MosaicPiece {

        private BufferedImage image;
        private String path;

        MosaicPiece(String path, BufferedImage image) {
            this.path = path;
            this.image = image;
        }

        BufferedImage getImage() throws IOException {
            if (image == null)
                image = ImageIO.read(new File(path));
            return image;
        }
    }
}
