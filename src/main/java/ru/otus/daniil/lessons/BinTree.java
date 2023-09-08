package ru.otus.daniil.lessons;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinTree implements SearchTree {
    private final TreeNode root;

    public BinTree(List<TreeNode> list) {
        List<TreeNode> sortedList = list;  // чтоб лист источник не менять
        sortedList.sort(Comparator.comparing(TreeNode::getWeight));
        root = buildReqursive(sortedList);
    }

    public TreeNode buildReqursive(List<TreeNode> list) {
        TreeNode localRoot;
        int centerIdx;
        if (list.size() == 1) {
            localRoot = list.get(0);
            return localRoot;
        }
        if (list.size() == 2) {
            localRoot = list.get(1);
            localRoot.setLeft(list.get(0));
            return localRoot;
        }
        if (list.size() == 5) {
            centerIdx = 3;  // для крОсоты
        } else {
            centerIdx = list.size() / 2;
        }
        localRoot = list.get(centerIdx);

        List<TreeNode> leftList = list.subList(0, centerIdx); //если бы я умел читать доку, то не потратил бы время на to_index - EXCLUSIVE
        List<TreeNode> rightList = list.subList(centerIdx + 1, list.size());

        localRoot.setLeft(buildReqursive(leftList));
        localRoot.setRight(buildReqursive(rightList));

        return localRoot;
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
