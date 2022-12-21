package co.camcar.aop.aspectos;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class RequisitosTabla {
	
	@Before("co.camcar.aop.aspectos.LoginConAspecto.paraClientes()")
	public void requisitosTabla() {
		System.out.println("La tabla cumple con los requisitos. Menos de 3000 registros");
	}

}
