package co.camcar.aop.servicios;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class MedicionServicio {
	
	public String getServicio() {
		System.out.println("*** El método realizando sus tareas ***");
		try {
		TimeUnit.SECONDS.sleep(4);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "Servicios terminados";
	}

}
