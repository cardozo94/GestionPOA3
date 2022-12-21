package co.camcar.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import co.camcar.aop.dao.ClienteDao;

public class ClasePrincipal {

	public static void main(String[] args) {
		// Leer configuración de Spring
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configuracion.class);
		
		//obtener el bean del contenedor de Spring
		
		ClienteDao cliente = context.getBean("clienteDao", ClienteDao.class);
		cliente.encuentraClientes();
		
		System.out.println("Continua ejecuccón del programa.");
		
		//Cerrar el contexto
		context.close();

	}

}
