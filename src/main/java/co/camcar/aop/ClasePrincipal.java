package co.camcar.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import co.camcar.aop.dao.ClienteDao;
import co.camcar.aop.servicios.MedicionServicio;

public class ClasePrincipal {

	public static void main(String[] args) {
		// Leer configuración de Spring
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configuracion.class);
		
		//obtener el bean del contenedor de Spring
		
		/*ClienteDao cliente = context.getBean("clienteDao", ClienteDao.class);
		
		boolean param = false;
		try {
			cliente.encuentraClientes( param);
		}catch (Exception e) {
			System.out.println("Excepción lanzada desde la clase principal.");
			System.err.println(e.getMessage());
		}
		
		System.out.println("Continua ejecución del programa.");*/
		
		MedicionServicio servicio = context.getBean("medicionServicio", MedicionServicio.class);
		
		System.out.println("Llamado el método getServicio().");
		
		String datos = servicio.getServicio();
		
		System.out.println("Resultado del servicio: "+datos);
		
		
		//Cerrar el contexto
		context.close();

	}

}
