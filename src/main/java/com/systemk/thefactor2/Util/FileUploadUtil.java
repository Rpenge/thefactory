package com.systemk.thefactor2.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public void upload(MultipartFile mf, String name, String path) throws FileNotFoundException {
		String originalFileName = mf.getOriginalFilename();
		File fi = new File(path+originalFileName);
		fi.getPath();
		try {
			mf.transferTo(fi);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public void fileDelete(String fileName) {
    	List<String> ext = new ArrayList<String>();
    	ext.add(fileName + ".png");
    	ext.add(fileName + ".jpg");
    	ext.add(fileName + ".jpeg");

    	for(int i = 0 ;i<ext.size() ;i++) {
    		File file = new File(ext.get(i));
        	if(file.exists()){
        		file.delete();
        	}
    	}
    }

    public String findFileNameExt(String dir, String name) {
		File path = new File(dir);
		final String fatternName = name;

		String fileList[] = path.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith(fatternName)&&(name.endsWith(".png") ||name.endsWith(".jpg")||name.endsWith(".jpeg")
						|| name.endsWith(".PNG") || name.endsWith(".JPG") || name.endsWith(".JPEG"));
			}
		});

		if(fileList.length != 0) {
			return fileList[0];
		}else {
			return null;
		}
	}


}
