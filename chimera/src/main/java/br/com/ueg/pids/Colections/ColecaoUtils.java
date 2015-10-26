package br.com.ueg.pids.Colections;

import java.util.ArrayList;
import java.util.HashMap;


public class ColecaoUtils {
		
	String string;
	
	public  String getAll() {
        return string;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		if(result != null){
			for (HashMap<String, Object> hashMap : result) {
				string = (String) hashMap.get("count");
			}
		}

    }

}
