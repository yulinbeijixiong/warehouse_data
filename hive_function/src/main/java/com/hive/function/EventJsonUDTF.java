package com.hive.function;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * @author annyu
 * @description udtf 函数
 * @date 2020/5/29
 **/
public class EventJsonUDTF extends GenericUDTF {
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        ArrayList<String> fieldsName = new ArrayList<>();
        ArrayList<ObjectInspector> fieldsIos = new ArrayList<>();
        fieldsName.add("event_name");
        fieldsIos.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldsName.add("event_json");
        fieldsIos.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldsName,fieldsIos);
    }

    @Override
    public void process(Object[] args) throws HiveException {
        String input = args[0].toString();
        if(StringUtils.isBlank(input)){
            return;
        }else{
            try {
                JSONArray jsonArray = new JSONArray(input);
                if(jsonArray==null){
                    return;
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    String[] result = new String[2];
                    try {
                        result[0] = jsonArray.getJSONObject(i).getString("en");
                        result[1] = jsonArray.getString(i);
                    } catch (JSONException e) {
                        continue;
                    }
                    forward(result);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void close() throws HiveException {

    }
}
