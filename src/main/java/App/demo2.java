package App;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class demo2 {
	public static void main(String[] args) {
		
		//Actualizar los valores de un usuario
		Usuario U = new Usuario(10, "Richard", "Rodas", "jperes@mail.com", "111", "2000/01/15",1,1);
		
		//Grabar la informacion en la tabla --> JPA
		
		//Determinamos con que db trabajaremos dentro de nuestra fabrica de persistence xml
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//Creando el manejador de  entidad(administrador)
		EntityManager em = fabrica.createEntityManager();
		
		//Empezo la transaccion
		em.getTransaction().begin();
		//proceso -> actualizar en la tabla o entidad
		
		em.merge(U);// MERGE SQL: Actualiza si existe el codigo , si no existe lo inserta.
		
		//Confirmar la transaccion.
		em.getTransaction().commit();
		em.close();
		
	}
}
