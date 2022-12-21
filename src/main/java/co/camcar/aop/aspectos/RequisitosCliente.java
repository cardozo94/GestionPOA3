package co.camcar.aop.aspectos;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class RequisitosCliente {
	
	@Before("co.camcar.aop.aspectos.LoginConAspecto.paraClientes()")	
	public void requisitosCliente() {
		System.out.println("Cliente cumple con los requisitos para ser insertado en la BBDD.");
	}
}
