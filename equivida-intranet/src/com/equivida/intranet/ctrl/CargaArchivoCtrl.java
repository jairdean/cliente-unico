package com.equivida.intranet.ctrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import com.equivida.intranet.model.LoaderFilesResponseDetail;
import com.equivida.dto.PersonaDto;
import com.equivida.intranet.clientws.PersonaDtoArray;
import com.equivida.intranet.clientws.RcsWs;
import com.equivida.intranet.clientws.RcsWsImplService;
import com.equivida.intranet.model.LoaderFilesResponse;
import com.equivida.intranet.util.BaseCtrl;
import com.google.gson.Gson;

@ManagedBean(name = "cargaArchivoCtrl")
@SessionScoped
public class CargaArchivoCtrl extends BaseCtrl {

	private static final long serialVersionUID = 148679455234L;
	private String mensajeCarga = "";
	private List<LoaderFilesResponse> resposeData;
	private int actualizados = 0;
	private int insertados = 0;
	private int error = 0;
	private Boolean cliente = true;
	
	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}
	
	public Boolean getCliente() {
		return cliente;
	}

	public void setCliente(Boolean cliente) {
		this.cliente = cliente;
	}

	public int getActualizados() {
		return actualizados;
	}

	public void setActualizados(int actualizados) {
		this.actualizados = actualizados;
	}

	public int getInsertados() {
		return insertados;
	}

	public void setInsertados(int insertados) {
		this.insertados = insertados;
	}
	
	public List<LoaderFilesResponse> getResposeData() {
		return resposeData;
	}

	public void setResposeData(List<LoaderFilesResponse> resposeData) {
		this.resposeData = resposeData;
	}

	public String getMensajeCarga() {
		return mensajeCarga;
	}

	public void setMensajeCarga(String mensajeCarga) {
		this.mensajeCarga = mensajeCarga;
	}

	public void listenerUpload(FileUploadEvent event) {
		try {
			
			FacesContext ctx = FacesContext.getCurrentInstance();
			String ipService =
			    ctx.getExternalContext().getInitParameter("ipLoaderFileService");
			
			this.mensajeCarga = "";
			this.insertados = 0;
			this.actualizados = 0;
			this.error = 0;
			
			this.resposeData = null;
			
			UploadedFile item = event.getUploadedFile();
			if (item != null) {
		        
				File tempFile = File.createTempFile(item.getName(), ".tmp");
				
				FileOutputStream fos = new FileOutputStream(tempFile);
				fos.write(item.getData());
				fos.close();
		        
				String ipAddress = this.getHttpServletRequest().getRemoteAddr();
				
		        CloseableHttpClient httpClient = HttpClients.createDefault();
		        HttpPost uploadFile = new HttpPost(ipService);
		        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		        
		        String esCliente = "N";
		        if (this.cliente)
		        	esCliente = "S";
		        
		        builder.addTextBody("Parameters", "[\r\n" + 
		        		"   {\r\n" + 
		        		"      \"Key\":\"usuario\",\r\n" + 
		        		"      \"Value\":\"" + this.getRemoteUser() + "\"\r\n" + 
		        		"   },\r\n" + 
		        		"   {\r\n" + 
		        		"      \"Key\":\"ip\",\r\n" + 
		        		"      \"Value\":\"" + ipAddress + "\"\r\n" + 
		        		"   },\r\n" + 
		        		"   {\r\n" + 
		        		"      \"Key\":\"ES_CLIENTE\",\r\n" + 
		        		"      \"Value\":\"" + esCliente + "\"\r\n" + 
		        		"   }\r\n" + 
		        		"]", ContentType.TEXT_PLAIN);
		        builder.addTextBody("ConfigId", "cargas_vida_vitality", ContentType.TEXT_PLAIN);

		        builder.addBinaryBody(
		            "File",
		            new FileInputStream(tempFile),
		            ContentType.DEFAULT_BINARY,
		            "Archivo"
		        );

		        HttpEntity multipart = builder.build();
		        uploadFile.setEntity(multipart);
		        CloseableHttpResponse response = httpClient.execute(uploadFile);
		        HttpEntity responseEntity = response.getEntity();
		        
		        //System.err.println(response.getStatusLine().getStatusCode());
		        //System.err.println(response.getEntity().toString());
		        
		        Scanner s = new Scanner(responseEntity.getContent()).useDelimiter("\\A");
		        String result = s.hasNext() ? s.next() : "";
		        
		        if (response.getStatusLine().getStatusCode() == 200) {
		        	
		        	List<Integer> listaValidar = new ArrayList<Integer>();
		        	
		        	Gson gson = new Gson(); 		        	
		        	LoaderFilesResponse[] mcArray = gson.fromJson(result, LoaderFilesResponse[].class);
		        	
		        	Sheet sheet = null;
		        	
		        	try {
		        		FileInputStream inputStream = new FileInputStream(tempFile);

					    Workbook workbook = null;

					    String fileExtensionName = item.getName().substring(item.getName().indexOf("."));

					    if(fileExtensionName.equals(".xlsx")) {
					    	workbook = new XSSFWorkbook(inputStream);
					    }
					    else if(fileExtensionName.equals(".xls")){
					        workbook = new HSSFWorkbook(inputStream);
					    }

					    sheet = workbook.getSheetAt(workbook.getFirstVisibleTab());	
		        	} catch (Exception e) {
		        		e.printStackTrace();
		        	}
		        					    
				    
		        	List<LoaderFilesResponse> temp = new ArrayList<LoaderFilesResponse>();
		        	String pattern = "dd-MM-yyyy";
		        	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		        	
		        	for (LoaderFilesResponse loaderFilesResponse : mcArray) {
		        		if (loaderFilesResponse.getDetails() != null) {
		        			for (LoaderFilesResponseDetail detail : loaderFilesResponse.getDetails()) {
								if (detail.getLevel() == 1) {
									if (detail.getName().indexOf("Actualiza") >= 0) {
										this.actualizados++;
									} else {
										this.insertados++;
									}
								}							
							}	
		        		} else {
		        			this.error++;
		        		}
		        		
		        		
		        		LoaderFilesResponse tempModel = new LoaderFilesResponse();
		        		tempModel.setRow(loaderFilesResponse.getRow());
		        		
		        		if (loaderFilesResponse.getMessage().equalsIgnoreCase("OK") && loaderFilesResponse.getDetails() != null) {
		        			
		        			listaValidar.add(loaderFilesResponse.getRow());
		        			
		        			for (LoaderFilesResponseDetail detail : loaderFilesResponse.getDetails()) {
								if (detail.getLevel() == 1) {
									if (detail.getName().indexOf("Actualiza") >= 0) {
										tempModel.setMessage("Cliente ya existe");
									} else {
										tempModel.setMessage("Cliente creado");
									}
								}
								if (detail.getLevel() == 2) {
									if (detail.getName().indexOf("correo") >= 0) {
										if (detail.getName().indexOf("Actualiza") >= 0) {
											tempModel.setMessage(tempModel.getMessage() + " / Correo actualizado");
										} else {
											tempModel.setMessage(tempModel.getMessage() + " / Correo creado");
										}
									}
									if (detail.getName().indexOf("tel") >= 0) {
										if (detail.getName().indexOf("Actualiza") >= 0) {
											tempModel.setMessage(tempModel.getMessage() + " / Teléfono actualizado");
										} else {
											tempModel.setMessage(tempModel.getMessage() + " / Teléfono creado");
										}
									}
									
								}
							}
		        			
						} else {
							tempModel.setMessage(loaderFilesResponse.getMessage());													
						}		        		
		        		
		        		try {
		        			if (sheet != null) {
		        				Row row = sheet.getRow(loaderFilesResponse.getRow()-1);
		        				String primerNombre = row.getCell(4) != null ? row.getCell(4).getStringCellValue() : "";
		        				String segundoNombre = row.getCell(5) != null ? row.getCell(5).getStringCellValue() : "";
		        				String primerApellido = row.getCell(2) != null ? row.getCell(2).getStringCellValue() : "" ;
		        				String segundoApellido = row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "";
		        				String identificacion = row.getCell(1) != null ? row.getCell(1).getStringCellValue() : "";
		        				String correo = row.getCell(13) != null ? row.getCell(13).getStringCellValue() : "";
		        				String telefono = row.getCell(12) != null ? row.getCell(12).getStringCellValue() : "";
		        				String fechaNacimiento = row.getCell(8) != null ? simpleDateFormat.format(row.getCell(8).getDateCellValue()) : "";
		        				
		        				tempModel.setMessageDetails(
										    primerNombre 
		        				    + " " + segundoNombre
									+ "," + primerApellido
									+ " " + segundoApellido
									+ "," + identificacion
									+ "," + correo
									+ "," + telefono);
		        				
		        				tempModel.setPrimerNombre(primerNombre);
		        				tempModel.setSegundoNombre(segundoNombre);
		        				tempModel.setPrimerApellido(primerApellido);
		        				tempModel.setSegundoApellido(segundoApellido);
		        				tempModel.setIdentificacion(identificacion);
		        				tempModel.setCorreo(correo);
		        				tempModel.setTelefono(telefono);
		        				tempModel.setFechaNacimiento(fechaNacimiento);
		        			}								
						} catch (Exception e) {
							e.printStackTrace();
							tempModel.setMessageDetails("Error en el formato de la fila");
						}	
		        		temp.add(tempModel);		        		
					}
		        	
		        	this.resposeData = temp;
		        	this.mensajeCarga = "Resultados de la carga";
		        	
		        	//Llamar a listas reservadas		        	
		        	//checkListaReservada(tempFile, item.getName(), listaValidar);
		        	
		        } else {
		        	this.mensajeCarga = "Se ha producido un error en la carga";
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.mensajeCarga = "Error al conectarse al servicio de carga";
		}        
    }
	
	public void limpiarMensajes(AjaxBehaviorEvent event) {
		this.mensajeCarga = "";
		
		this.resposeData = null;
		
		this.cliente = true;
		
		this.actualizados = 0;
		this.insertados = 0;
		this.error = 0;
	}
	
	public void updateCliente(ValueChangeEvent event) {		
		this.cliente = (Boolean) event.getNewValue();
		
	}
	
	public void generateReport() {
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
         
        HSSFRow row;
         
        row = sheet.createRow(0);
        row.createCell(0).setCellValue("Fila");
        row.createCell(1).setCellValue("Mensaje");
        row.createCell(2).setCellValue("Apellido paterno");
        row.createCell(3).setCellValue("Apellido materno");
        row.createCell(4).setCellValue("Primer nombre");
        row.createCell(5).setCellValue("Segundo nombre");
        row.createCell(6).setCellValue("Identificación");
        row.createCell(7).setCellValue("Fecha de nacimiento");
        row.createCell(8).setCellValue("Correo");
        row.createCell(9).setCellValue("Teléfono");
         
        for (int i = 0; i < this.resposeData.size(); i++){
            row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(resposeData.get(i).getRow());
            row.createCell(1).setCellValue(resposeData.get(i).getMessage()); 
            row.createCell(2).setCellValue(resposeData.get(i).getPrimerApellido());
            row.createCell(3).setCellValue(resposeData.get(i).getSegundoApellido());
            row.createCell(4).setCellValue(resposeData.get(i).getPrimerNombre());
            row.createCell(5).setCellValue(resposeData.get(i).getSegundoNombre());
            row.createCell(6).setCellValue(resposeData.get(i).getIdentificacion());
            row.createCell(7).setCellValue(resposeData.get(i).getFechaNacimiento());
            row.createCell(8).setCellValue(resposeData.get(i).getCorreo());
            row.createCell(9).setCellValue(resposeData.get(i).getTelefono());
        }
        HttpServletResponse res = this.getHttpServletResponse();
        res.setContentType("application/vnd.ms-excel");
        res.setHeader("Content-disposition",  "attachment; filename=reporte.xls");
         
         
        try {
            ServletOutputStream out = res.getOutputStream();
   
             wb.write(out);
             out.flush();
             out.close();
       } catch (IOException ex) { 
               ex.printStackTrace();
       }
  
         
      FacesContext faces = FacesContext.getCurrentInstance();
      faces.responseComplete();  
	}
	
	public void checkListaReservada(File archivo,  String nombreArchivo, List<Integer> listaValidar) {
		try {
			FileInputStream inputStream = new FileInputStream(archivo);

		    Workbook workbook = null;

		    String fileExtensionName = nombreArchivo.substring(nombreArchivo.indexOf("."));

		    if(fileExtensionName.equals(".xlsx")) {
		    	workbook = new XSSFWorkbook(inputStream);
		    }
		    else if(fileExtensionName.equals(".xls")){
		        workbook = new HSSFWorkbook(inputStream);
		    }

		    Sheet sheet = workbook.getSheetAt(workbook.getFirstVisibleTab());

		    int rowCount = sheet.getLastRowNum()- sheet.getFirstRowNum();
		    PersonaDtoArray personaDtoArray = new PersonaDtoArray();
		    List<com.equivida.intranet.clientws.PersonaDto> listaPersonas = personaDtoArray.getItem();
		    
		    for (int i = 1; i < rowCount+1; i++) {
		    	if (listaValidar.contains(i)) {
		    		com.equivida.intranet.clientws.PersonaDto persona = new com.equivida.intranet.clientws.PersonaDto();
			        Row row = sheet.getRow(i);
			        System.err.println(row.getCell(0).getStringCellValue().substring(0, 1));
			        System.err.println(row.getCell(1).getStringCellValue());
			        System.err.println(row.getCell(2).getStringCellValue());
			        System.err.println(row.getCell(3).getStringCellValue());
			        System.err.println(row.getCell(4).getStringCellValue());
			        System.err.println(row.getCell(5).getStringCellValue());
			        
			        System.err.println("================================");
			        
			        persona.setTipoIndentificacion(row.getCell(0).getStringCellValue().substring(0, 1));
			        persona.setIdentificacion(row.getCell(1).getStringCellValue());
			        persona.setApellidoPaterno(row.getCell(2).getStringCellValue());
			        persona.setApellidoMaterno(row.getCell(3).getStringCellValue());
			        persona.setPrimerNombre(row.getCell(4).getStringCellValue());
			        persona.setSegundoNombre(row.getCell(5).getStringCellValue());
			        
			        listaPersonas.add(persona);	
		    	}		    			        
		    } 
		    
		    if (listaPersonas.size() > 0) {
		    	FacesContext ctx = FacesContext.getCurrentInstance();
			    String ipService =
					    ctx.getExternalContext().getInitParameter("listaRevervadaService");
			    
				RcsWsImplService rcsWsImplService = new RcsWsImplService(new URL(ipService));
		    	
		    	RcsWs rcsWs = rcsWsImplService.getRcsWsImplPort();
		    	
		    	rcsWs.buscarRcs(personaDtoArray, "?");	
		    }	    
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
