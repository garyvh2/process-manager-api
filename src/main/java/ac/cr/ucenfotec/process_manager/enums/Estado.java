package ac.cr.ucenfotec.process_manager.enums;

import java.util.HashMap;
import java.util.Map;

public enum Estado {
	
	EN_PROCESO("en proceso"),
	INACTIVO("inactivo"),
	COMPLETADO("completado");
	
	private static Map<String, Estado> map  = new HashMap<String, Estado>();
	
	private String EstadoValue;
	
    static {
        for (Estado estadoEnum : Estado.values()) {
            map.put(estadoEnum.EstadoValue, estadoEnum);
        }
    }
    
    private Estado(String EstadoValue){ 
        this.EstadoValue = EstadoValue;
    }  
  
}
