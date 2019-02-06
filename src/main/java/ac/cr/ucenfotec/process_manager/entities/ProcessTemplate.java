package ac.cr.ucenfotec.process_manager.entities;


import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection ="processes")
public class ProcessTemplate extends RootProcess{
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProcessTemplate))
			return false;
		ProcessTemplate other = (ProcessTemplate) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (numeroTramite == null) {
			if (other.numeroTramite != null)
				return false;
		} else if (!numeroTramite.equals(other.numeroTramite))
			return false;
		return true;
	}	
	
}
