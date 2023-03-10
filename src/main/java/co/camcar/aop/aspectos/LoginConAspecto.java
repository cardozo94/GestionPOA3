package co.camcar.aop.aspectos;


import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
		System.out.println("Aqu?? se estar??an ejecutando de forma autom??tica las tareas tras al excepci??n.");
	}
	
	@After("execution(* co.camcar.aop.dao.ClienteDao.encuentraClientes(..))")
	public void ejecutandoTareasConYSinExcepcion(JoinPoint point) {
		System.out.println("Ejecutando tareas SIEMPRE!!!");
	}
	
	@Around("execution(* co.camcar.aop.servicios.*.getServicio(..))")
	public Object ejecutarServicio(ProceedingJoinPoint point) throws Throwable {
		System.out.println("--- Comienzo de acciones antes de llamada ---");
		long comienzo = System.currentTimeMillis();
		Object resultado = point.proceed();// point apunta el m??todo destino y se ejecuta
		System.out.println("--- tareas despu??s de llamada ---");
		long fin = System.currentTimeMillis();
		long duracion = fin -comienzo;
		System.out.println("El m??todo tardo "+duracion/1000+" seg");
		return resultado;
	}
}
