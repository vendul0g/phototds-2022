package um.tds.phototds.dao;

import java.util.List;

import um.tds.phototds.dominio.Album;
import um.tds.phototds.dominio.Foto;
import um.tds.phototds.dominio.Publicacion;

public interface PublicacionDAO {
	
	void create(Publicacion publicacion);
	boolean delete(Publicacion publicacion);
	void update(Publicacion publicacion);
	Publicacion get(int id);
	List<Publicacion> getAll();
	void addFotosAlbum(Album a, List<Foto> fList);
}
