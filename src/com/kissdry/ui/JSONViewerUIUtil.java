package com.kissdry.ui;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kissdry.util.PropertiesUtil;
import java.awt.Component;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Administrator
 */
public class JSONViewerUIUtil {

    /**
     * 构造json树结构.
     *
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param obj Object
     * @param pNode DefaultMutableTreeNode
     */
    public static void createJsonTree(Object obj, DefaultMutableTreeNode pNode) {
        if (obj == null) {
            pNode.add(Kit.nullNode("NULL"));
        } else if (obj instanceof JSONArray) {
            createJsonArray((JSONArray) obj, pNode, "[0]");
        } else if (obj instanceof JSONObject) {
            createJsonObject((JSONObject) obj, pNode);
        }
    }

    /**
     * 处理json数组.
     *
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param arr
     * @param pNode
     * @param key
     */
    public static void createJsonArray(JSONArray arr, DefaultMutableTreeNode pNode, String key) {
        int index = 0;
        DefaultMutableTreeNode child = Kit.arrNode(key);
        for (Object val : arr) {
            if ("com.alibaba.fastjson.JSONObject".equals(val.getClass().getName())) {
                DefaultMutableTreeNode node = Kit.objNode(index);
                createJsonObject((JSONObject) val, node);
                child.add(node);
            } else if ("com.alibaba.fastjson.JSONArray".equals(val.getClass().getName())) {
                createJsonArray((JSONArray) val, child, Kit.fkey(index));
            } else if (val == null) {
                child.add(Kit.nullNode(index));
            } else {
                formatJsonPrimitive(Kit.fkey(index), val, child);
            }
            ++index;
        }
        pNode.add(child);
    }

    /**
     * 处理jsoon对象.
     *
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param obj
     * @param pNode
     */
    public static void createJsonObject(JSONObject obj, DefaultMutableTreeNode pNode) {
        obj.entrySet().stream().forEach((el) -> {
            String key = el.getKey();
            Object val = el.getValue();
            if (val == null) {
                pNode.add(Kit.nullNode(key));
            } else if ("com.alibaba.fastjson.JSONArray".equals(val.getClass().getName())) {
                createJsonArray((JSONArray) val, pNode, key);
            } else if ("com.alibaba.fastjson.JSONObject".equals(val.getClass().getName())) {
                DefaultMutableTreeNode node = Kit.objNode(key);
                createJsonObject((JSONObject) val, node);
                pNode.add(node);
            } else {
                formatJsonPrimitive(key, val, pNode);
            }
        });

    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param key
     * @param val
     * @param pNode
     */
    private static void formatJsonPrimitive(String key, Object val, DefaultMutableTreeNode pNode) {
        if (val == null) {
            pNode.add(Kit.nullNode(key));
        } else if (val instanceof Integer) {
            pNode.add(Kit.numNode(key, val.toString()));
        } else if (val instanceof Boolean) {
            pNode.add(Kit.boolNode(key, (Boolean) val));
        } else {
            pNode.add(Kit.strNode(key, (String) val));
        }
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param Path
     * @return
     */
    public static URL getResourceByPath(String Path) {
        return JSONViewerUIUtil.class.getClassLoader().getResource(Path);
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param tree
     */
    public static void setNodeIcon(JTree tree) {
        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                String tmp = node.toString();
                if (tmp.startsWith(Kit.ARRAY_PREFIX)) {
                    this.setIcon(new ImageIcon(getResourceByPath("resources/images/a.gif")));
                    this.setText(tmp.substring(2));
                } else if (tmp.startsWith(Kit.STRING_PREFIX)) {
                    this.setIcon(new ImageIcon(getResourceByPath("resources/images/v.gif")));
                    this.setText(tmp.substring(2));
                } else if (tmp.startsWith(Kit.OBJECT_PREFIX)) {
                    this.setIcon(new ImageIcon(getResourceByPath("resources/images/o.gif")));
                    this.setText(tmp.substring(2));
                } else if (tmp.startsWith(Kit.NUMBER_PREFIX)) {
                    this.setIcon(new ImageIcon(getResourceByPath("resources/images/n.gif")));
                    this.setText(tmp.substring(2));
                } else if (tmp.startsWith(Kit.NULL_PREFIX)) {
                    this.setIcon(new ImageIcon(getResourceByPath("resources/images/k.gif")));
                    this.setText(tmp.substring(2));
                } else if (tmp.startsWith(Kit.BOOL_PREFIX)) {
                    this.setIcon(new ImageIcon(getResourceByPath("resources/images/v.gif")));
                    this.setText(tmp.substring(2));
                } else {
                    this.setIcon(new ImageIcon(getResourceByPath("resources/images/v.gif")));
                    this.setText(tmp.substring(2));
                }
                return this;
            }
        });
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param id
     * @return
     */
    public static String getI18nById(String id) {
        return PropertiesUtil.getConfByKey("zh_cn", id);
    }
}
