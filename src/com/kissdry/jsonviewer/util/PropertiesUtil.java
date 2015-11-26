package com.kissdry.jsonviewer.util;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class PropertiesUtil {

    public static Map<String, Map<String, String>> conf;

    //写入properties信息
    public static void writeProperties(String filePath, String parameterName, String parameterValue) {
        Properties prop = new Properties();
        try {
            InputStream fis = new FileInputStream(filePath);
            prop.load(fis);
            OutputStream fos = new FileOutputStream(filePath);
            prop.setProperty(parameterName, parameterValue);
            prop.store(fos, "Update '" + parameterName + "' value");
        } catch (IOException e) {
            System.err.println("Visit " + filePath + " for updating " + parameterName + " value error");
        }
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param sourceFile
     * @param targetFile
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null) {
                inBuff.close();
            }
            if (outBuff != null) {
                outBuff.close();
            }
        }
    }

    /**
     * 写入properties信息
     *
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param filePath
     * @param properties
     * @param jarFilePath
     * @param newJarFilePath
     * @return
     * @throws URISyntaxException
     */
    public static boolean writeProperties(String filePath, Map<String, String> properties, String jarFilePath, String newJarFilePath) throws URISyntaxException {
        Properties prop = new Properties();
        try {
            InputStream fis = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
            prop.load(fis);
            fis.close();

            URL url = PropertiesUtil.class.getClassLoader().getResource(filePath);
            int beginIndex = url.getPath().lastIndexOf("/") + 1;
            String fileName = url.getPath().substring(beginIndex);
            String dstPath = System.getProperty("java.io.tmpdir");
            dstPath = dstPath + fileName;
            File dstFile = new File(dstPath);
            OutputStream fos = new FileOutputStream(dstFile);
            String parameterName, parameterValue;
            for (Entry<String, String> entry : properties.entrySet()) {
                parameterName = entry.getKey();
                parameterValue = entry.getValue();
                prop.setProperty(parameterName, parameterValue);
            }
            System.out.println(filePath);
            prop.store(fos, "Update value");
            fos.close();

            //copy 一个新的jar文件
            File jarFilePathFile = new File(jarFilePath);
            File newJarFilePathFile = new File(newJarFilePath);
            if (!newJarFilePathFile.exists()) {
                copyFile(jarFilePathFile, newJarFilePathFile);
            }

        } catch (IOException e) {
            System.err.println("Visit " + filePath + " for updating value error:" + e.getMessage());
            //MessageUtil.showMessageDialogMessage("Visit " + filePath + " for updating value error:" + e.getMessage());
        }

        return true;
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param env
     * @return
     */
    public static String getPropertyFilePath(String env) {
        return "resources/properties/" + env + ".conf.properties";
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param env
     * @return
     */
    public static Properties getProperties(String env) {
        String propFile = PropertiesUtil.getPropertyFilePath(env);
        InputStream fis = PropertiesUtil.class.getClassLoader().getResourceAsStream(propFile);
        Properties prop = new Properties();
        try {
            prop.load(fis);
        } catch (Exception ex) {
           // MessageUtil.showMessageDialogMessage("load " + propFile + " error:" + ex.getMessage());
        }
        return prop;
    }

    /**
     *
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @return
     */
    public static Properties getProperties() {
        InputStream fis = PropertiesUtil.class.getClassLoader().getResourceAsStream("resources/properties/conf.properties");
        Properties prop = new Properties();
        try {
            prop.load(fis);
        } catch (IOException ex) {
            System.exit(-1);
        }
        return prop;
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param langEnv
     */
    public static void buildConf(String langEnv) {
        buildConf(langEnv, false, "", "");
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param langEnv
     * @param convertEncoding
     * @param fromEncoding
     * @param toEncoding
     */
    public static void buildConf(String langEnv, boolean convertEncoding, String fromEncoding, String toEncoding) {
        if (conf == null || !conf.containsKey(langEnv)) {
            Map<String, String> currentConf = new HashMap<>();
            Properties prop = getProperties(langEnv);
            if (conf == null) {
                conf = new HashMap<>();
            }
            Set<String> names = prop.stringPropertyNames();
            Iterator<String> it = names.iterator();
            if (convertEncoding) {
                if ("".equals(fromEncoding)) {
                    fromEncoding = "ISO-8859-1";
                }
                if ("".equals(toEncoding)) {
                    toEncoding = "ISO-8859-1";
                }
                while (it.hasNext()) {
                    try {
                        String key = it.next();
                        String value = new String(prop.getProperty(key).getBytes(fromEncoding), toEncoding);
                        currentConf.put(key, value);
                    } catch (UnsupportedEncodingException ex) {
                    }
                }
            } else {
                while (it.hasNext()) {
                    String key = it.next();
                    String value = prop.getProperty(key);
                    currentConf.put(key, value);
                }
            }
            conf.put(langEnv, currentConf);
        }
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param langEnv
     * @return
     */
    public static Map<String, String> getConfByLangEnv(String langEnv) {
        if (langEnv.isEmpty()) {
            langEnv = "en_us";
        }
        if (conf == null || !conf.containsKey(langEnv)) {
            buildConf(langEnv);
        }
        return conf.get(langEnv);
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param langEnv
     * @param key
     * @return
     */
    public static String getConfByKey(String langEnv, String key) {
        return PropertiesUtil.getConfByLangEnv(langEnv).get(key);
    }
}
