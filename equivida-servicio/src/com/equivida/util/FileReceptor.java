/**
 * 
 */
package com.equivida.util;

import java.io.File;

/**
 * @author Juan Ochoa
 * 
 */
public class FileReceptor {
	private String name;
	private String mime;
	private Long length;
	private byte[] data;
	private File file;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public int hashCode() {
		int resp = 0;
		if (name != null)
			resp += name.hashCode();

		if (mime != null)
			resp += mime.hashCode();

		if (length != null)
			resp += length.hashCode();

		if (data != null)
			for (int i = 0; i < data.length; i++)
				resp += new Byte(data[i]).hashCode();

		return resp;
	}
}
