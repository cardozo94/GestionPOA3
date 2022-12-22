package co.camcar.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import co.camcar.aop.dao.ClienteDao;

public class ClasePrincipal {

	public static void main(String[] args) {
		// Leer configuración de Spring
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configuracion.class);
		
		//obtener el bean del contenedor de Spring
		
		ClienteDao cliente = context.getBean("clienteDao", ClienteDao.class);
		
		boolean param = false;
		try {
			cliente.encuentraClientes( param);
		}catch (Exception e) {
			System.out.println("Excepción lanzada desde la clase principal.");
			System.err.println(e.getMessage());
		}
		
		System.out.println("Continua ejecución del programa.");
		
		//Cerrar el contexto
		context.close();

	}

}
