package com.kissdry.jsonviewer.ui;

import java.math.BigDecimal;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author CangYan
 */
public class Kit {

    public final static String SPLIT = " : ";

    public final static String SIGN = "-";

    public final static String NULL_SIGN = "k";
    public final static String NUMBER_SIGN = "n";
    public final static String OBJECT_SIGN = "o";
    public final static String ARRAY_SIGN = "a";
    public final static String STRING_SIGN = "v";
    public final static String BOOL_SIGN = "b";

    public final static String NULL_PREFIX = NULL_SIGN + SIGN;
    public final static String NUMBER_PREFIX = NUMBER_SIGN + SIGN;
    public final static String OBJECT_PREFIX = OBJECT_SIGN + SIGN;
    public final static String ARRAY_PREFIX = ARRAY_SIGN + SIGN;
    public final static String STRING_PREFIX = STRING_SIGN + SIGN;
    public final static String BOOL_PREFIX = BOOL_SIGN + SIGN;

    public final static String ARRAY = "Array";
    public final static String OBJECT = "Object";

    public static DefaultMutableTreeNode nullNode(String key) {
        return treeNode(NULL_PREFIX + key + SPLIT + "<null>");
    }

    public static DefaultMutableTreeNode nullNode(int index) {
        return nullNode(fkey(index));
    }

    public static DefaultMutableTreeNode numNode(String key, String val) {
        return treeNode(NUMBER_PREFIX + key + SPLIT + val);
    }
    
    public static DefaultMutableTreeNode numNode(String key, BigDecimal val) {
        return treeNode(NUMBER_PREFIX + key + SPLIT + val);
    }

    public static DefaultMutableTreeNode numNode(int index, String val) {
        return numNode(fkey(index), val);
    }

    public static DefaultMutableTreeNode boolNode(String key, Boolean val) {
        String sVal = "false";
        if (val) {
            sVal = "true";
        }
        return treeNode(BOOL_PREFIX + key + SPLIT + sVal);
    }

    public static DefaultMutableTreeNode boolNode(int index, Boolean val) {
        return boolNode(fkey(index), val);
    }

    public static DefaultMutableTreeNode strNode(String key, String val) {
        return treeNode(STRING_PREFIX + key + SPLIT + "\"" + val + "\"");
    }

    public static DefaultMutableTreeNode strNode(int index, String val) {
        return strNode(fkey(index), val);
    }

    public static DefaultMutableTreeNode objNode(String key) {
        return treeNode(OBJECT_PREFIX + key);
    }

    public static DefaultMutableTreeNode objNode(int index) {
        return objNode(fkey(index));
    }

    public static DefaultMutableTreeNode arrNode(String key) {
        return treeNode(ARRAY_PREFIX + key);
    }

    public static DefaultMutableTreeNode arrNode(int index) {
        return arrNode(fkey(index));
    }

    public static DefaultMutableTreeNode treeNode(String str) {
        return new DefaultMutableTreeNode(str);
    }

    public static DefaultMutableTreeNode treeNode(String type, int index, String val) {
        return treeNode(type + "[" + index + "]");
    }

    public static String fkey(int index) {
        return "[" + index + "]";
    }

    public static String fArrKey(int index) {
        return ARRAY_PREFIX + fkey(index);
    }

    public static int getIndex(String str) {
        int index = -1;
        if (str == null || str.length() == 0) {
            return index;
        }
        index = str.lastIndexOf("[");
        if (index >= 0) {
            try {
                index = Integer.parseInt(str.substring(index + 1, str.length() - 1));
            } catch (Exception ex) {
                index = -1;
            }
        }
        return index;
    }

    public static String getKey(String str) {
        int index;
        if (str == null || str.length() == 0) {
            return str;
        }
        index = str.lastIndexOf("[");
        if (index >= 0) {
            return str.substring(0, index);
        }
        StringBuffer sb = null;
        return str;

    }

    public static String[] pstr(String str) {
        String arr[] = new String[3];//类型,key,value
        arr[0] = str.substring(0, 1);
        int i = str.indexOf(Kit.SPLIT);
        if (null != arr[0])//        if(i<0) return arr;
        {
            switch (arr[0]) {
                case Kit.ARRAY_SIGN:
                    arr[1] = str.substring(2);
                    arr[2] = Kit.ARRAY;
                    break;
                case Kit.OBJECT_SIGN:
                    arr[1] = str.substring(2);
                    arr[2] = Kit.OBJECT;
                    break;
                case Kit.STRING_SIGN:
                    arr[1] = str.substring(2, i);
                    arr[2] = str.substring(i + 4, str.length() - 1);
                    break;
                default:
                    arr[1] = str.substring(2, i);
                    arr[2] = str.substring(i + 3, str.length());
                    break;
            }
        }
        return arr;
    }

//    public static void main(String[] args) {
//        System.out.println(getIndex("[5]"));
//    }

}
