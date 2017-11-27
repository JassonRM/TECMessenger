package org.tec.datos1.messenger.webapi.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.internal.jdkconnector.*;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.sun.jersey.core.header.FormDataContentDisposition;
@Path("/messages/images")
public class ImageHandler {
	/**
	 * Retorna una imagen segun el nomrbre de la imagen solicitada el cual esta en el json
	 * y el id del usuario el cual lo pondra en la aplicacion
	 * @param name
	 * @param userID
	 * @return
	 */
	@GET
	@Path("/getImage")
	@Produces( "Image/png")
//	public File returnImage(String name,String userID) {
	public File returnImage() {
		String path;//Hay que definir el path a una carpeta y imagen
		
		File file = new File("path" );
		return file;
	}
	/**
	 * Debe de ser solo accesado desde la aplicacion con un parametro en especifico
	 * @param uploadedInputStream
	 * @param filedetails
	 * @return
	 */
@POST
@Path("/upload")
@Consumes(MediaType.MULTIPART_FORM_DATA)
public String uploadFile(@FormDataParam("File") InputStream uploadedInputStream,
		@FormDataParam("File") FormDataContentDisposition   filedetails) {
	saveOnDisk(uploadedInputStream,filedetails);
	return "Done";
}
/**
 * Guarda la imagen en el disco
 * @param uploadedInputStream
 * @param filedetails
 */
static void saveOnDisk(InputStream uploadedInputStream,FormDataContentDisposition filedetails  ) {
	String pathtosave = "lugar que se guardaran" + filedetails.getFileName();
	try {
		OutputStream out = new FileOutputStream(new File(pathtosave));
		int read = 0 ;
		byte[] bytes = new byte[1024];
		out = new FileOutputStream( new File(pathtosave));
		while((read = uploadedInputStream.read(bytes))!= -1) {
			out.write(bytes, 0, read);
			
		}
		out.flush();
		out.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
