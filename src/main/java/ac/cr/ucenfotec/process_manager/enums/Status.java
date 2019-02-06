package ac.cr.ucenfotec.process_manager.enums;

import java.util.HashMap;
import java.util.Map;

public enum Status {
	
	EN_PROCESO("en proceso"),
	INACTIVO("inactivo"),
	COMPLETADO("completado");
	
	private static Map<String, Status> map  = new HashMap<String, Status>();
	
	private String EstadoValue;
	
    static {
        for (Status estadoEnum : Status.values()) {
            map.put(estadoEnum.EstadoValue, estadoEnum);
        }
    }
    
    private Status(String EstadoValue){ 
        this.EstadoValue = EstadoValue;
    }  
  
}
