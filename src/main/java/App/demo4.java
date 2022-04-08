package App;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class demo4 {
	public static void main(String[] args) {
		
		
		//Obtener los datos de un usuario, segun el codigo 
		
		//Determinamos con que db trabajaremos dentro de nuestra fabrica de persistence xml
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//Creando el manejador de  entidad(administrador)
		EntityManager em = fabrica.createEntityManager();
		
		//Empezo la transaccion
		em.getTransaction().begin();
		
		//proceso -> eliminar usuario en la tabla o entidad
		
		//Forma 1) Borrado Logico---> Solo cambia el estado (activo/inactivo)
		//ES UN MERGE em.merge(U);
		
		//Forma 2)Borrado Fisico --> eliminar el registro
		
		Usuario U = em.find(Usuario.class, 10);
		
		System.out.println(U);
		
		//em.remo(U);//eliminar un usuario
		
		//Confirmar la transaccion.
		em.getTransaction().commit();
		em.close();
		
	}
}
