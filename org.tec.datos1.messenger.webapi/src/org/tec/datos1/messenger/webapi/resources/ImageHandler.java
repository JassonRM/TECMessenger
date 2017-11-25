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
import org.glassfish.jersey.media.*;

import com.sun.jersey.core.header.FormDataContentDisposition;
@Path("/messages/images")
public class ImageHandler {
	@GET
	@Path("/getImage")
	@Produces( "Image/png")
	public File returnImage(String name) {
		String path;//Hay que definir el path a una carpeta y imagen
		
		File file = new File("null");
		return file;
	}
@POST
@Path("/upload")
@Consumes(MediaType.MULTIPART_FORM_DATA)
public String uploadFile(@FormParam("File") InputStream uploadedInputStream,
		@FormParam("File") FormDataContentDisposition   filedetails) {
	saveOnDisk(uploadedInputStream,filedetails);
	return "Done";
}
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
