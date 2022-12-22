package co.camcar.aop.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.camcar.aop.Cliente;

@Component
public class ClienteDao {

	public void insertaCliente(Cliente cliente, String tipo) {
		System.out.println("Cliente insertado correctamente.");
	}
	
	public List<Cliente> encuentraClientes(boolean param){
		
		if(param) throw new RuntimeException("Error!!. No se ha podido procesar la petición.");
		
		List<Cliente> clientes = new ArrayList<>();
		//simular clientes devueltos por la BBDD
		
		Cliente cl1 = new Cliente("María", "Normal");
		Cliente cl2 = new Cliente("Ana", "Normal");
		Cliente cl3 = new Cliente("Sandra", "VIP");
		Cliente cl4 = new Cliente("Antonio", "Normal");
		
		clientes.add(cl1);
		clientes.add(cl2);
		clientes.add(cl3);
		clientes.add(cl4);
		
		System.out.println("Ejecución finalizada del método encuentra clientes.");
		
		return clientes;
	}

}
