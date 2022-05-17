package helperMethods;

import objects.PathPoint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {
    public static BufferedImage getSpriteAtlas() {
        try {
            BufferedImage img = null;
            InputStream is = new BufferedInputStream(new FileInputStream("resources/spriteatlas.png"));
            img = ImageIO.read(is);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Can't load img");
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
            writeData(newLvl, idArr, new PathPoint(0, 0), new PathPoint(0, 0));
        }
    }

    private static void writeData(File f, int[] idArr, PathPoint start, PathPoint finish) {
        try {
            PrintWriter w = new PrintWriter(f);
            for (int i : idArr)
                w.println(i);

            w.println(start.getX());
            w.println(start.getY());
            w.println(finish.getX());
            w.println(finish.getY());

            w.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveLvl(String name, int[][] lvl, PathPoint start, PathPoint finish) {
        File newLvl = new File("Levels/" + name + ".txt");

        if (newLvl.exists()) {
            writeData(newLvl, Utility.matrixToArr(lvl), start, finish);
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

    public static ArrayList<PathPoint> getLvlPathPoint(String name) {
        File newLvl = new File("Levels/" + name + ".txt");

        if (newLvl.exists()) {
            ArrayList<Integer> list = readData(newLvl);

            ArrayList<PathPoint> points = new ArrayList<>();
            points.add(new PathPoint(list.get(400), list.get(401)));
            points.add(new PathPoint(list.get(402), list.get(403)));

            return points;
        } else {
            System.out.println("Nivelul: " + name + " nu exista!");
            return null;
        }
    }

    public static int[][] readLvlData(String name) {
        File newLvl = new File("Levels/" + name + ".txt");

        if (newLvl.exists()) {
            ArrayList<Integer> list = readData(newLvl);
            return Utility.arrayListToMatrix(list, 20, 20);
        } else {
            System.out.println("Nivelul: " + name + " nu exista!");
            return null;
        }
    }

}
