package org.tec.datos1.messenger.estructures;

import java.util.ArrayList;

public class NodoGrafo<T> {
public T valor;
ArrayList<Arco<NodoGrafo<T>>> arcos = new ArrayList<>();
public NodoGrafo(T valor) {
	this.valor= valor;
}
/**
 * Conecta los nodos mediante arcos
 * @param elemento nodo a conectar
 * @param peso peso del arco
 */
public void connect(NodoGrafo<T>elemento,int peso) {
	Arco<NodoGrafo<T>> arco = new Arco<>(this,elemento,true,peso);
	arcos.add(arco);
}
/**
 * metodo de busqueda de nodo
 * @param valor
 * @param Nodo
 * @return
 */
public NodoGrafo<T> search(T valor,NodoGrafo<T> Nodo){
	if(Nodo == null) {
		return null;
	}else if(Nodo.valor.equals(valor)) {
		return Nodo;
	}
	ArrayList<NodoGrafo<T>> visitados = new ArrayList<>();
	Cola<NodoGrafo<T>> examinar = new Cola<>();
	examinar.queue(Nodo);
	return sarch2(valor,visitados,examinar);
		
	}
/**
 * Metodo auxiliar para la busqueda
 * @param valor a buscar
 * @param visitados lista de visitados
 * @param examinar cola con  nodos a examinar
 * @return
 */
private NodoGrafo<T> sarch2(T valor, ArrayList<NodoGrafo<T>> visitados, Cola<NodoGrafo<T>> examinar) {
	if(examinar.lista.isEmpty()) {
		return null;
	}
	else {
		NodoGrafo<T> nodo = examinar.dequeue();
		visitados.add(nodo);
		for(Arco<NodoGrafo<T>> arco: nodo.arcos) {
			if(arco.direccion) {
			if(!examinar.lista.contains(arco.llegada) &&!visitados.contains(arco.llegada) ) {
				examinar.queue(arco.llegada);
			}
			}
		}
		if(compruebavalor(nodo,valor)) {
			return nodo;
		}
		else {
			return sarch2(valor,visitados,examinar);
		}
	}
	
}


/**
 * Metodo auxiliar para comprobar el valor de un nodo u otro
 * @param nodo
 * @param valor2
 * @return
 */
private boolean compruebavalor(NodoGrafo<T> nodo, T valor2) {
	try {
	if(nodo.valor.equals(valor2)) {
		return true;
	}
	return false;}catch(Exception e) {
		if(nodo.valor == valor2) {
			return true;
		}
		return false;
	}
}
/**
 * Elimina un nodo junto a todas sus conecciones con los demas nodos
 * @param valor valor del nodo a elminar(Puede ser cambiado por un id)
 * @param Nodo Nodo de partida
 * @return
 */
public NodoGrafo<T> delete(T valor,NodoGrafo<T> Nodo){
	try {
	if(Nodo.valor.equals(valor)) {
	}}
	catch(Exception e) {
		if(Nodo.valor == valor) {
		}
	}
	ArrayList<NodoGrafo<T>> visitados = new ArrayList<>();
	Cola<NodoGrafo<T>> examinar = new Cola<>();
	examinar.queue(Nodo);
	return delete2(valor,visitados,examinar);
		
	}
/**
 * 
 * @param valor valor a eliminar
 * @param visitados es una lista que contiene los nodos visitados
 * @param examinar son los nodos a examinar
 * @return
 */
private NodoGrafo<T> delete2(T valor, ArrayList<NodoGrafo<T>> visitados, Cola<NodoGrafo<T>> examinar) {
	if(examinar.lista.isEmpty()) {
		return null;
	}
	else {
		NodoGrafo<T> nodo = examinar.dequeue();
		visitados.add(nodo);
		for(Arco<NodoGrafo<T>> arco: nodo.arcos) {
			if(arco.llegada.valor.equals(valor) || arco.origen.valor.equals(valor)) {
				nodo.arcos.remove(arco);
			}
			if(arco.direccion) {
			if(!examinar.lista.contains(arco.llegada) &&!visitados.contains(arco.llegada) ) {
				examinar.queue(arco.llegada);
			}
			}
		}
			return sarch2(valor,visitados,examinar);
	}
	
}
}
