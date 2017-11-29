package org.tec.datos1.messenger.webapi.resources;

import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.tec.datos1.messenger.webapi.dto.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/messages/files")
public class MultiFileHandler {
	private static final String UPLOAD_FOLDER = "C:\\Users\\kenne\\Desktop\\lol\\";
	@Context
	private UriInfo context;

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	/**
	 * Hace que se suba cualquier file
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @return
	 */
	@Path("/upload")
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		if (uploadedInputStream == null || fileDetail == null)
			return Response.status(400).entity("Tipo invalido").build();
		try {
			createFolderIfNotExists(UPLOAD_FOLDER);
		} catch (SecurityException se) {
			return Response.status(500)
					.entity("No se puede crear folder en el server")
					.build();
		}
		String uploadedFileLocation = UPLOAD_FOLDER + fileDetail.getFileName();
		try {
			saveToFile(uploadedInputStream, uploadedFileLocation);
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(500).entity("No se pudo guardar").build();
		}
		return Response.status(200)
				.entity("Guardado en " + uploadedFileLocation).build();
	}
/**
 * Crea el file a partir de lo obtenido
 * @param inStream
 * @param target
 * @throws IOException
 */
	private void saveToFile(InputStream inStream, String target)
			throws IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		out = new FileOutputStream(new File(target));
		while ((read = inStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	}

	private void createFolderIfNotExists(String dirName)
			throws SecurityException {
		File theDir = new File(dirName);
		if (!theDir.exists()) {
			theDir.mkdir();
		}
	}
	
	
	@GET
	@Produces("multipart/mixed")
	public Response getFile(@Context HttpServletRequest request) {
		if(request ==null) {
			System.out.println("ERROR");
			return null;
		}
		
		User usuario = Auth.users.searchByIpAddress(request.getRemoteAddr());
		try {
			
			File objFile = new File(usuario.getFiles().get(0));
			usuario.getFiles().remove(0);
			
			MultiPart objMultiPart = new MultiPart();
			
			objMultiPart.type(new MediaType("multipart", "mixed"));
			
			objMultiPart.bodyPart(objFile.getName(), new MediaType("text", "plain"));
			
			objMultiPart.bodyPart(objFile.length(), new MediaType("text", "plain"));
			
			objMultiPart.bodyPart(objFile, new MediaType("multipart", "mixed"));
	
			return Response.ok(objMultiPart).build();
		}catch(Exception e) {
			return Response.noContent().build();
		}
	}
		
}
