package org.tec.datos1.messenger.estructures;

public class Arco<T> {
public T origen;
public boolean direccion;
public int peso;
public T llegada;
public Arco(T org,T llegad,boolean direct,int pesos){
	this.origen =org;
	this.llegada = llegad;
	this.direccion= direct;
	this.peso=pesos;
}



}
