package helperMethods;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {
    public static BufferedImage getSpriteAtlas() throws FileNotFoundException {

        BufferedImage img = null;
        InputStream is = new BufferedInputStream(new FileInputStream("resources/spriteatlas.png"));

        try {
            img = ImageIO.read(is);
        }catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    public static void createFile() {
        File newLvl = new File("Levels/new_lvl.txt");

        try {
            newLvl.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDefaultLvl(String name, int[] idArr) {
        File newLvl = new File("Levels/" + name + ".txt");
        if (newLvl.exists()) {
            System.out.println("(Eroare createDefaultLvl)Nivelul: " + name + " exista");
        } else {
            try {
                newLvl.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writeData(newLvl,idArr);
        }
    }

    private static void writeData(File f,int[] idArr) {
        try {
            PrintWriter w = new PrintWriter(f);
            for (int i : idArr)
                w.println(i);
            w.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveLvl(String name, int[][] lvl) {
        File newLvl = new File("Levels/" + name + ".txt");

        if (newLvl.exists()) {
            writeData(newLvl,Utility.matrixToArr(lvl));
        } else {
            System.out.println("(Eroare saveLvl)Nivelul: " + name + " nu exista");
        }
    }

    private static ArrayList<Integer> readData(File f) {
        ArrayList<Integer> list = new ArrayList<>();

        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                list.add(Integer.parseInt(scan.nextLine()));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static int[][] readLevelData(String name) {
        File newLvl = new File("Levels/" + name + ".txt");

        if (newLvl.exists()) {
            ArrayList<Integer> list = readData(newLvl);
            return Utility.arrayListToMatrix(list,20,20);
        }else {
            System.out.println("Nivelul: " + name +" nu exista!");
            return null;
        }
    }

}
