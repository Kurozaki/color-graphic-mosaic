## color-graphic-mosaic
用许多小图片拼成一张大图片，支持简单的彩色图片，需要一定量的原素材支持

## 使用

```
public class Client {

    public static void main(String[] args) throws Exception {
        CMHandler handler = new CMHandler();
        System.out.println("初始化完毕...");
        
        //原图片输入路径和生成图片输出路径
        handler.handle("pics/death_note_l.jpg", "pics/death_note_l_out.jpg");
    }
}
```

## 思路
    将所有方形（建议是方形）马赛克图片根据平均RGB值分组，我将颜色简化成27种，每个R，G，B分量有0x00,0x7f,0xfe三种值，总共简化成27种颜色，每张图片有一个平均RGB值，选择RGB分量最“靠近”的一个分组，例如0xfd0102，最“靠近”分组0xfe0000，分完组后保存在一个文件夹里。

    读取一张图片，尺寸建议不要太大，否则处理起来比较慢，输出的图片也会很大，读取每一个像素点的RGB值，找到最“靠近”的RGB分组值，从分组里随机选择一张图片摆在对应的位置上，最后完成输出图片。

    需要大量的方形马赛克图片作为原素材，而且最好平均RGB值分散到各个分组，就是尽量不要都同一个色调

## 效果图

<img src="https://github.com/Kurozaki/color-graphic-mosaic/blob/master/pics/death_note_l_out.jpg?raw=true"/>
<img src="https://github.com/Kurozaki/color-graphic-mosaic/blob/master/pics/gudako_out.jpg?raw=true"/>
<img src="https://github.com/Kurozaki/color-graphic-mosaic/blob/master/pics/tamamo_no_mae_out.jpg?raw=true"/>
