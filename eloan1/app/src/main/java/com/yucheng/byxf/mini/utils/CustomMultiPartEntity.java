package com.yucheng.byxf.mini.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.io.FilterOutputStream;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;

public class CustomMultiPartEntity extends MultipartEntity {

	private ProgressListener listener;

	public CustomMultiPartEntity(ProgressListener listener) {
		super();
		this.listener = listener;
	}

	public CustomMultiPartEntity(HttpMultipartMode mode,
			ProgressListener listener) {
		super(mode);
		this.listener = listener;
	}

	public CustomMultiPartEntity(HttpMultipartMode mode, String boundary,
			Charset charset, ProgressListener listener) {
		super(mode);
		this.listener = listener;
	}
	
	@Override
	public void writeTo(OutputStream outstream) throws IOException {
		// TODO Auto-generated method stub
		super.writeTo(new CountingOutputStream(outstream,this.listener));
	}
	
	public static interface ProgressListener{
		void transferred(long num);
	}
	
	public static class CountingOutputStream extends FilterOutputStream{

		private final ProgressListener listener;
		private long transferred;
		public CountingOutputStream(OutputStream out, ProgressListener listener) {
			super(out);
			this.listener = listener;
			this.transferred = 0;
		}

		public void write(byte[] buffer, int offset, int length)
				throws IOException {
			// TODO Auto-generated method stub
			out.write(buffer, offset, length);
			this.transferred += length;
			this.listener.transferred(this.transferred);
		}
		
		public void write(int oneByte) throws IOException {
			// TODO Auto-generated method stub
			out.write(oneByte);
			this.transferred ++;
			this.listener.transferred(this.transferred);
		}
	}
}
