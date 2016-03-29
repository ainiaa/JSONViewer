/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.json.*;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrator
 */
public class TestJSON {

    public static void main(String[] args) {
        try {
            String origin = "{\n"
                    + "    \"retCode\": 1,\n"
                    + "    \"retDesc\": \"查询成功\",\n"
                    + "    \"clerkInfo\": {\n"
                    + "        \"MemberID\": \"\",\n"
                    + "        \"StatusCode\": \"2011\",\n"
                    + "        \"StatusText\": \"缺少必要参数\",\n"
                    + "        \"MemberText\": \"\",\n"
                    + "        \"MemberPhone\": \"\",\n"
                    + "        \"MemberName\": \"\",\n"
                    + "        \"IntegralName\": \"\",\n"
                    + "        \"TradingType\": \"\",\n"
                    + "        \"ResidualIntegral\": \"\",\n"
                    + "        \"CardType\": \"\"\n"
                    + "    }\n"
                    + "}";
            
            JSONObject jsonObj = new JSONObject(origin);
            String clerkInfo = jsonObj.getString("clerkInfo");
            JSONObject jsonObjArray = new JSONObject(clerkInfo);
            JSONArray jsonArray = jsonObjArray.getJSONArray(clerkInfo);
            System.out.println(clerkInfo);
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
