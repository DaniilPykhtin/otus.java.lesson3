package ru.otus.daniil.lessons;

import java.util.ArrayList;
import java.util.List;

public class MainApp {


    public static void main(String[] args) {
        List<TreeNode> arrList = new ArrayList<>();
        arrList.add(new Node(1));
        arrList.add(new Node(47));
        arrList.add(new Node(5));
        arrList.add(new Node(23));
        arrList.add(new Node(15));
        arrList.add(new Node(5));  // дубль
        arrList.add(new Node(12));
        arrList.add(new Node(35));
        arrList.add(new Node(60));
        arrList.add(new Node(-12));
        arrList.add(new Node(53));
        arrList.add(new Node(90));
        arrList.add(new Node(123));
        arrList.add(new Node(33));
        arrList.add(new Node(11));
        arrList.add(new Node(0));
        arrList.add(new Node(9));
        arrList.add(new Node(-24));
        arrList.add(new Node(-1));
        arrList.add(new Node(87));


        BinTree tree = new BinTree();
        tree.fromList(arrList);
        tree.find(34);
        tree.find(2);
        tree.find(0);
        tree.find(55);

        System.out.println(tree.getSortedList());


        //System.out.println(getIdx(21, 0));
    }
}
