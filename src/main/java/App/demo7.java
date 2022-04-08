package App;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class demo7 {
	public static void main(String[] args) {
		
		
		//Obtener los datos de un usuario, segun el codigo 
		
		//Determinamos con que db trabajaremos dentro de nuestra fabrica de persistence xml
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//Creando el manejador de  entidad(administrador)
		EntityManager em = fabrica.createEntityManager();
		
		//FORMA LARGA
		
		TypedQuery<Usuario> consulta = em.createQuery("select u from Usuario u where u.tipo = :xtipo",Usuario.class);
		consulta.setParameter("xtipo",1);
		List<Usuario> lstUsuario = consulta.getResultList();
	
		
		//List<Usuario> lstUsuario = em.createQuery("select a from Usuario a",Usuario.class).getResultList();
		
		for (Usuario u : lstUsuario) {
			System.out.println(u);
		}
		
		em.close();
		
	}
}
