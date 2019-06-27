package com.bootdo.modules.flowable.utils;

import com.bootdo.modules.flowable.domain.ActDeCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * author :sq
 */
public class TreeCategory {

    private List<Node> data = new ArrayList<Node>();
    private List<Node> nodes;

    public TreeCategory(List<ActDeCategory> list){
        nodes = new ArrayList<Node>();
        for (ActDeCategory menu : list) {
            Node node = new Node();
            node.setId(menu.getId());
            node.setParentId(menu.getParentId());
            node.setText(menu.getName());
            node.setHref(menu.getUrl());
            node.setIcon(menu.getIcon());
            NodeState state = new NodeState();
            node.setState(state);
            nodes.add(node);
        }
        this.nodes = nodes;
    }


    public List<Node> buildTree(){
        for (Node node : nodes) {
            if (node.getParentId() == null) {
                build(node);
                data.add(node);
            }
        }
        return data;
     }

    private void build(Node node){
        List<Node> children = getChildren(node);
        if (!children.isEmpty()) {
            node.setNodes(children);
            for (Node child : children) {
                build(child);
            }

        }
    }


    private List<Node> getChildren(Node node){
        List<Node> children = new ArrayList<Node>();
        for (Node child : nodes) {
            Integer id = node.getId();
            if (id.equals(child.getParentId())) {
                children.add(child);
             }
        }
        return children;
    }
}
