package com.yotwei.core;

import com.sun.istack.internal.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by YotWei on 2018/4/1.
 */
public class CMHandler {

    public CMHandler() throws IOException {
        init();
    }

    private Map<String, MosaicGroup> mosaicGroupMap = new HashMap<>();

    @SuppressWarnings("ConstantConditions")
    private void init() throws IOException {
        File procdir = new File(Config.MOSAIC_PROCESS_PATH);
        for (File d : procdir.listFiles()) {
            String k = d.getName();
            MosaicGroup pieceGroup = new MosaicGroup();
            for (File pf : d.listFiles()) {
                pieceGroup.addPiece(pf.getName(), ImageIO.read(pf));
            }
            mosaicGroupMap.put(k, pieceGroup);
        }
    }

    public void handle(String src, String dst) throws Exception {
        BufferedImage srcimg = ImageIO.read(new File(src));
        BufferedImage dstimg = new BufferedImage(
                srcimg.getWidth() * Config.MOSAIC_WIDTH,
                srcimg.getHeight() * Config.MOSAIC_HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < srcimg.getHeight(); y++) {
            for (int x = 0; x < srcimg.getWidth(); x++) {
                String k = String.format("%06x", Util.RGB.aligningValue(srcimg.getRGB(x, y)));
                dstimg.getGraphics().drawImage(
                        mosaicGroupMap.get(k).nextPiece(),
                        x * Config.MOSAIC_WIDTH,
                        y * Config.MOSAIC_HEIGHT,
                        Config.MOSAIC_WIDTH,
                        Config.MOSAIC_HEIGHT,
                        null);
            }
        }
        ImageIO.write(dstimg, "jpg", new File(dst));
    }

    /**
     * 加工原图片
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void processOriginMosaicImages(String pathOrigin, String pathOut) throws IOException {
        File dirOrigin = new File(pathOrigin);
        if (!dirOrigin.isDirectory())
            throw new IOException("No such directory");
        File[] files = dirOrigin.listFiles();
        if (files == null) {
            throw new IOException("Null file list");
        }
        for (File file : files) {
            BufferedImage compress =
                    Util.compressImage(ImageIO.read(file), Config.MOSAIC_WIDTH, Config.MOSAIC_HEIGHT);

            String writePath = String.format(
                    "%s%06x",
                    pathOut,
                    Util.RGB.aligningValue(Util.RGB.getAverageRGB(compress)));
            File dir = new File(writePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            ImageIO.write(compress, "jpg", new File(writePath, file.getName()));
        }
    }

}
