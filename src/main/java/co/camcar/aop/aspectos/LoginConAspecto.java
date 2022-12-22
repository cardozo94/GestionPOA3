package co.camcar.aop.aspectos;


import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import co.camcar.aop.Cliente;

@Aspect
@Component
@Order(2)
public class LoginConAspecto {
	
	//@Pointcut("execution(public * insertaCliente(..))") //actua sobre metodo especifico
	@Pointcut("execution(* co.camcar.aop.dao.*.*(..))")// actua sobre todos los metodos en el paquete dado
	public void paraClientes() {}
	
	@AfterReturning(pointcut = "execution(* co.camcar.aop.dao.ClienteDao.encuentraClientes(..))", returning = "clientes")
	public void tareaTrasEncontrarClientes(List<Cliente> clientes) {
		for (Cliente cliente : clientes) {
			if(cliente.getTipo()=="VIP") {
				procesadoDatosAfterReturning(clientes);
				System.out.println("Existen clientes VIP en el listado. Nombre: "+cliente.getNombre());
			}
		}
	}

	
	private void procesadoDatosAfterReturning(List<Cliente> clientes) {
		for (Cliente cliente : clientes) {
			String datosProcesados = "V.I.P. "+cliente.getNombre().toUpperCase();
			cliente.setNombre(datosProcesados);
		}
		
	}

	@Before("paraClientes()")
	public void antesInsertarCliente(JoinPoint joinpoint) {
		System.out.println("El usuario se ha logeado");
		System.out.println("El perfil es valido");
		
		Object[] argumentos =  joinpoint.getArgs();
		
		for(Object temp: argumentos) {
			if(temp instanceof Cliente){
				Cliente cliente = (Cliente) temp;

				System.out.println("Nombre: "+cliente.getNombre()+" - tipo: "+cliente.getTipo());
			}
		}
	}
	
	@AfterThrowing(pointcut = "execution(* co.camcar.aop.dao.ClienteDao.encuentraClientes(..))", throwing = "laExcepcion")
	public void procesandoDatosAfterExceptionEncuentraClientes(Throwable laExcepcion) {
		System.out.println("Aquí se estarían ejecutando de forma automática las tareas tras al excepción.");
	}
	
	@After("execution(* co.camcar.aop.dao.ClienteDao.encuentraClientes(..))")
	public void ejecutandoTareasConYSinExcepcion(JoinPoint point) {
		System.out.println("Ejecutando tareas SIEMPRE!!!");
	}
}
