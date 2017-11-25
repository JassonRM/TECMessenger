package org.tec.datos1.messenger.estructures;

import java.util.ArrayList;

import org.tec.datos1.messenger.webapi.dto.User;

public class Network extends NodoGrafo<User> {

	public Network(User user) {
		super(user);
	}

	/**
	 * Busca un nodo por el username
	 * @param username Nombre del usuario a buscar
	 * @param Nodo Nodo inicial
	 * @return Nodo buscado
	 */
	public Network searchByUsername(String username,Network Nodo){
		if(Nodo == null) {
			return null;
		}else if(Nodo.valor.getUsername().equals(username)) {
			return Nodo;
		}
		
		ArrayList<Network> visitados = new ArrayList<>();
		Cola<Network> examinar = new Cola<Network>();
		examinar.queue(Nodo);
		return searchByUsernameAux(username,visitados,examinar);
			
		}
	/**
	 * Metodo auxiliar para la busqueda por nombre de usuario
	 * @param valor a buscar
	 * @param visitados lista de visitados
	 * @param examinar cola con  nodos a examinar
	 * @return
	 */
	private Network searchByUsernameAux(String username, ArrayList<Network> visitados, Cola<Network> examinar) {
		if(examinar.lista.isEmpty()) {
			return null;
		}
		else {
			Network nodo = examinar.dequeue();
			visitados.add(nodo);
			for(Arco<NodoGrafo<User>> arco: nodo.arcos) {
				if(arco.direccion) {
				if(!examinar.lista.contains(arco.llegada) &&!visitados.contains(arco.llegada) ) {
					examinar.queue((Network) arco.llegada);
				}
				}
			}
			if(checkValueByUsername(nodo,username)) {
				return nodo;
			}
			else {
				return searchByUsernameAux(username,visitados,examinar);
			}
		}
		
	}
	
	/**
	 * Metodo auxiliar para comprobar el valor de un nodo u otro
	 * @param nodo
	 * @param valor2
	 * @return
	 */
	private boolean checkValueByUsername(Network nodo, String username) {
		if(nodo.valor.getUsername().equals(username)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Busca un nodo por el ipAddress
	 * @param ipAddress Nombre del usuario a buscar
	 * @param Nodo Nodo inicial
	 * @return Nodo buscado
	 */
	public Network searchByIpAddress(String ipAddress,Network Nodo){
	  if(Nodo == null) {
	    return null;
	  }else if(Nodo.valor.getIpAddress().equals(ipAddress)) {
	    return Nodo;
	  }
	  
	  ArrayList<Network> visitados = new ArrayList<>();
	  Cola<Network> examinar = new Cola<Network>();
	  examinar.queue(Nodo);
	  return searchByIpAddressAux(ipAddress,visitados,examinar);
	    
	  }
	/**
	 * Metodo auxiliar para la busqueda por nombre de usuario
	 * @param valor a buscar
	 * @param visitados lista de visitados
	 * @param examinar cola con  nodos a examinar
	 * @return
	 */
	private Network searchByIpAddressAux(String ipAddress, ArrayList<Network> visitados, Cola<Network> examinar) {
	  if(examinar.lista.isEmpty()) {
	    return null;
	  }
	  else {
	    Network nodo = examinar.dequeue();
	    visitados.add(nodo);
	    for(Arco<NodoGrafo<User>> arco: nodo.arcos) {
	      if(arco.direccion) {
	      if(!examinar.lista.contains(arco.llegada) &&!visitados.contains(arco.llegada) ) {
	        examinar.queue((Network) arco.llegada);
	      }
	      }
	    }
	    if(checkValueByIpAddress(nodo,ipAddress)) {
	      return nodo;
	    }
	    else {
	      return searchByIpAddressAux(ipAddress,visitados,examinar);
	    }
	  }
	  
	}

	/**
	 * Metodo auxiliar para comprobar el valor de un nodo u otro
	 * @param nodo
	 * @param valor2
	 * @return
	 */
	private boolean checkValueByIpAddress(Network nodo, String ipAddress) {
	  if(nodo.valor.getIpAddress().equals(ipAddress)) {
	    return true;
	  }
	  return false;
	}
}
