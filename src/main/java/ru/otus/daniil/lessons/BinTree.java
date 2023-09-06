package ru.otus.daniil.lessons;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Из Предварительно отсортированного списка (List) сформировать двоичное дерево поиска
//        Написать рекурсивную функцию поиска в сформированном дереве
//        Класс должен имплементировать следующий интерфейс
//        ```
//public interface SearchTree {
///**
// @param element to find
// @return element if exists, otherwise - null
// /
// T find(T element);
// List getSortedList();
// }


public class BinTree implements SearchTree {
    private TreeNode root;
    private int size;

    public TreeNode getRoot() {
        return root;
    }

    public BinTree() {
    }

    public void fromList(List<TreeNode> initArray) {

        if (root != null) {
            System.out.println("Дерево должно быть пустым");
            return;
        }

        initArray.sort(Comparator.comparing(TreeNode::getWeight));

//        for (int i = 0; i < initArray.size(); i++) {
//            System.out.print(initArray.get(i).getWeight() + " ");
//        }

        this.root = initArray.get(initArray.size() / 2);

        List<Integer> rootIndexes = getRootIndexes(initArray.size(), 0);

//        System.out.println(rootIndexes);
        for (int i = 0; i < rootIndexes.size(); i++) {
            addNode(initArray.get(rootIndexes.get(i)), root);
        }

        for (int i = 0; i < initArray.size(); i++) {
//            System.out.println(initArray.get(i).getWeight());
            addNode(initArray.get(i), root);
        }
    }

    public TreeNode addNode(TreeNode node, TreeNode root) {

        if (node == null) {
            return null;
        }
        if (root == null) {
            return node;
        }
//        System.out.println("Добавим к root = " + root.getWeight() + " ноду с весом = " + node.getWeight());
        if (node.getWeight() > root.getWeight()) {
//            System.out.println("Идем вправо");
            root.setRight(addNode(node, root.getRight()));
        } else if (node.getWeight() < root.getWeight()) {
//            System.out.println("Идем влево");
            root.setLeft(addNode(node, root.getLeft()));
        } else {
            return root;
        }
        return root;
    }

    //найдём индексы будущих корневых элементов дерева по отсортированному списку
    public List<Integer> getRootIndexes(int size, int offset) {
        List<Integer> res = new ArrayList<>();

        int center = size / 2;
//        System.out.println(center + " " + offset);

        if (size == 5) {
            res.add(3 + offset);
            res.add(1 + offset);
            return res;
        } else {
            res.add(center + offset);
        }

        if (center / 2f <= 1f) {
            return res;
        }

        res.addAll(getRootIndexes(center, offset));
        res.addAll(getRootIndexes(size - (center + 1), offset + (center + 1)));

        return res;
    }

    @Override
    public TreeNode find(int weight) {
        return findReqursive(weight, root);
    }

    public TreeNode findReqursive(int weight, TreeNode root) {
        if (root == null) {
            System.out.println("Ноды с весом: " + weight + " нет в дереве");
            return null;
        }
        System.out.println("Ищем " + weight + " Текущий узел: " + root.getWeight());

        if (weight < root.getWeight()) {
            return findReqursive(weight, root.getLeft());
        } else if (weight > root.getWeight()) {
            return findReqursive(weight, root.getRight());
        } else {
            System.out.println("Адрес ноды с весом: " + weight + " - " + root);
            return root;
        }

    }

    public List getTreeAsList(TreeNode root) {
        List<Integer> list = new ArrayList();

        if (root == null) {
            return list;
        }
        list.add(root.getWeight());
        list.addAll(getTreeAsList(root.getLeft()));
        list.addAll(getTreeAsList(root.getRight()));
        return list;
    }


    @Override
    public List getSortedList() {
        List<Integer> list = new ArrayList();
        list = getTreeAsList(root);
        list.sort(Comparator.naturalOrder());
        return list;
    }
}
