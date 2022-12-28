package um.tds.phototds.dominio;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import um.tds.phototds.controlador.Controlador;

public class Usuario {
	//Constantes
	
	//Atributos
	private int id;
	private String username;
	private String nombre;
	private String email;
	private String password;
	private String fechN;
	private Optional<String> fotoPerfil;
	private Optional<String> presentacion;
	private boolean isPremium; 
	private LinkedList<Publicacion> publicaciones;
	private LinkedList<Usuario> seguidores;
	private LinkedList<Usuario> seguidos;
	private Optional<String> seguidoresString;
	private Optional<String> seguidosString;

	
	//Constructor básico para nuevo usuario
	public Usuario(String username, String nombre, String email, String cont, String fechN, Optional<String> fotoPerfil, Optional<String> presentacion) {
		super();
		this.username = username;
		this.nombre = nombre;
		this.email = email;
		this.password = cont;
		this.fechN = fechN;
		this.fotoPerfil = fotoPerfil;
		this.presentacion = presentacion;
		this.isPremium = false;
		this.publicaciones = new LinkedList<Publicacion>();
		this.seguidores = new LinkedList<Usuario>();
		this.seguidos = new LinkedList<Usuario>();
		this.seguidoresString = Optional.empty();
		this.seguidosString = Optional.empty();
	}
	
	
	//Constructor DAO 
	public Usuario(String username, String nombre, String email, String password, String fechN, String fotoPerfil, String presentacion,
			String isPremium, String seguidores, String seguidos) {
		this.username = username;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.fechN = fechN;
		this.fotoPerfil = fotoPerfil.equals("null") ? Optional.empty() : Optional.of(fotoPerfil);
		this.presentacion = presentacion.equals("null") ? Optional.empty() : Optional.of(presentacion);
		this.isPremium = isPremium.equals("true") ? true : false;
//		this.publicaciones; //No se inicializa porque todavía no tienen por qué existir las fotos
		this.seguidores = new LinkedList<Usuario>();
		this.seguidoresString = Optional.of(seguidores);
		this.seguidos = new LinkedList<Usuario>();
		this.seguidosString = Optional.of(seguidos);
	}
	

	//getters-setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getPassword() {
		return this.password;
	}
		
	public int getNumSeguidores() {
		return this.seguidores.size();
	}
	
	public int getNumSeguidos() {
		return this.seguidos.size();
	}
	
	public String getFechaNacimiento() {
		return this.fechN;
	}
	
	public String getFotoPerfil() {
		if(fotoPerfil.isPresent())
			return fotoPerfil.get();
		else return "null";
	}
	
	public String getPresentacion() {
		if(presentacion.isPresent())
			return presentacion.get();
		else return "null";
	}
	
	public String isPremiumString(){
		if(isPremium)
			return "true";
		else return "false";
	}
	
	public String getSeguidoresString() {
		return listToString(this.seguidores);
	}
	
	public String getSeguidosString() {
		return listToString(this.seguidos);
	}

	
	//Funcionalidad
	private String listToString(LinkedList<Usuario> list) {
		String s = "[";
		for(Usuario u : list) {
			s += u.getUsername()+",";
		}		
		s = s.substring(0, s.length()-1);
		s+="]";
		return s;
	}
	
	public void cargarPublicaciones(LinkedList<Publicacion> p) {
		this.publicaciones = p;
	}
	
	/**
	 * Cuando se cargan los objetos Usuario no podemos crear las listas
	 * de seguidores y seguidos porque puede que algunos usuarios todavía
	 * no estén creados, por lo que los creamos cuando se llama a esta función
	 */
	public void cargarListasUsuarios() {
		cargarSeguidores();
		cargarSeguidos();
	}
	
	private void cargarSeguidores() {
		if(this.seguidoresString.get().equals("[]"))
			return;
		String n = seguidoresString.get().substring(1, seguidoresString.get().length()-1);
		String[] s = n.split(",");
		for(int i=0; i< s.length; i++) {
			Optional<Usuario> u= Controlador.INSTANCE.findUsuario(s[i]);
			if(u.isPresent()) {
				this.seguidores.add(u.get());
			}
			else {
				System.err.println("Usuario "+u.get().getUsername()+": No existe");
			}
		}
	}
	
	private void cargarSeguidos() {
		if(this.seguidosString.get().equals("[]"))
			return;
		String n = seguidosString.get().substring(1, seguidosString.get().length()-1);
		String[] s = n.split(",");
		for(int i=0; i< s.length; i++) {
			Optional<Usuario> u= Controlador.INSTANCE.findUsuario(s[i]);
			if(u.isPresent()) {
				this.seguidores.add(u.get());
			}
			else {
				System.err.println("Usuario "+u.get().getUsername()+": No existe");
			}
		}
	}
	
	
	


	
	
	
	
}
