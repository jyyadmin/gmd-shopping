package com.springcloud.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springcloud.common.UploadUtils;
import com.springcloud.vo.ResultValue;

@RestController
public class FileUploadController {

	//从application.propertis文件中获得指定键的值，并赋值给相应的成员变量
	@Value("${web.user-path}")
	private String userPath;
	
	@Value("${web.goods-path}")
	private String goodsPath;
	
	@RequestMapping(value="/userUpload")
	public ResultValue userUpload(@RequestParam("userPhoto") MultipartFile file) {
		ResultValue rv = new ResultValue();
		
		try {
			Map<String,Object> map = this.uploadFile(userPath, file);
			if(map != null && map.size() > 0) {
				rv.setCode(0);
				rv.setDataMap(map);
				return rv;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户头像上传失败！！！");
		return rv;
	}
	
	@RequestMapping(value="/goodsUpload")
	public ResultValue goodsUpload(@RequestParam("goodsPhoto") MultipartFile file) {
		ResultValue rv = new ResultValue();
		
		try {
			Map<String,Object> map = this.uploadFile(goodsPath, file);
			if(map != null && map.size() > 0) {
				rv.setCode(0);
				rv.setDataMap(map);
				return rv;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品图片上传失败！！！");
		return rv;
	}
	
	/**
	 * 删除商品图片
	 * 
	 * @param goodsPhoto  商品图片
	 * @return
	 */
	@RequestMapping(value="/deleteGoodsPhoto")
	public ResultValue deleteGoodsPhoto(@RequestParam("goodsPhoto") String goodsPhoto) {
		ResultValue rv = new ResultValue();
		
		try {
			//从URL中获得商品图片的名字
			int indexOf = goodsPhoto.lastIndexOf("/");
			if(indexOf != -1) {
				String fileName = goodsPhoto.substring(indexOf + 1);
				//System.out.println(fileName);
				File file = new File(this.goodsPath + fileName);
				//判断文件或目录是否存在
				if(file.exists()) {
					if(file.isFile()) {
						file.delete();
						rv.setCode(0);
						return rv;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
		
	}
	
	/**
	 * 删除用户头像
	 * 
	 * @param userPhoto  用户图片
	 * @return
	 */
	@RequestMapping(value="/deleteUserPhoto")
	public ResultValue deleteUserPhoto(@RequestParam("userPhoto") String userPhoto) {
		ResultValue rv = new ResultValue();
		
		try {
			//从URL中获得用户图片的名字
			int indexOf = userPhoto.lastIndexOf("/");
			if(indexOf != -1) {
				String fileName = userPhoto.substring(indexOf + 1);
				//System.out.println(fileName);
				File file = new File(this.userPath + fileName);
				//判断文件或目录是否存在
				if(file.exists()) {
					if(file.isFile()) {
						file.delete();
						rv.setCode(0);
						return rv;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
		
	}
	
	
	/**
	 * 上传文件
	 * 
	 * @param path	上传文件路径
	 * @param file  上传的文件
	 * @return		成功返回java.util.Map类型实例，否则返回null
	 */
	private Map<String,Object> uploadFile(String path,MultipartFile file) throws IOException{
		//获得新的文件名
		String fileName = UploadUtils.getFileName();
		//获得上传文件的文件名的获得文件的拓展名
		String extendedName = UploadUtils.getExendeName(file.getOriginalFilename());
		
		//上传文件
		//1.将文件转换为字节类型的数组
		byte[] bytes = file.getBytes();
		//2.创建file类对象，并设置文件名上传文件路径及文件名
		File saveFile = new File(path + fileName + extendedName);
		//3.上传文件
		FileCopyUtils.copy(bytes, saveFile);
		
		Map<String,Object> map = new HashMap<>();
		map.put("fileName", fileName);
		map.put("extendedName", extendedName);
		return map;
		
	}
	
}
