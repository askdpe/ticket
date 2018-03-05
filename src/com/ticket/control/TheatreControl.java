package com.ticket.control;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ticket.entity.Theatre;
import com.ticket.dao.ITheatreMapper;

@Controller
@RequestMapping("/theatre")
public class TheatreControl {
	ITheatreMapper theatreMapper;
	Theatre theatre = new Theatre();

	public ITheatreMapper getTheatreMapper() {
		return theatreMapper;
	}

	@Autowired
	public void setTheatreMapper(@Qualifier("theatreMapper") ITheatreMapper theatreMapper) {
		this.theatreMapper = theatreMapper;
	}

	@RequestMapping("/signUpForTheatre")
	@ResponseBody
	public String signUpForTheatre(@RequestParam("file1") MultipartFile file1,
			@RequestParam("file2") MultipartFile file2, @ModelAttribute("theatre") Theatre theatre,
			HttpServletRequest request) throws IllegalStateException, IOException {
		// ���ӰԺ�Ƿ��ظ�ע��
		List<Theatre> lstTheatre = this.theatreMapper.isExistTheTheatre(theatre);
		System.out.println("��⵽��ͬӰԺ������ ��" + lstTheatre.size());
		if (lstTheatre.size() == 0) {
			// ӰԺδ�ظ���ִ������ע�Ჽ��
			UUID uuid = UUID.randomUUID();
			String[] randomString = uuid.toString().split("-");
			String theatre_id = randomString[4];
			String theatre_pwd = randomString[0];
			System.out.println("ΪӰԺ���ɵ�Ψһ��ʶ��  : " + theatre_id);
			theatre.setTheatre_id(theatre_id);
			theatre.setTheatre_account(theatre_id);
			theatre.setTheatre_pwd(theatre_pwd);

			// �洢��ȡӰԺ��ͼƬtheatre_picture��Ӫҵִ��theatre_license,������·��
			System.out.println("ӰԺ��ͼƬ�� " + file1.getOriginalFilename() + "ӰԺ��ִ�գ� " + file2.getOriginalFilename());
			String extention1 = file1.getOriginalFilename().substring(file1.getOriginalFilename().indexOf("."));
			String extention2 = file2.getOriginalFilename().substring(file2.getOriginalFilename().indexOf("."));

			String tPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			int num = tPath.indexOf(".metadata");
			String storePath1 = "\\picture\\theatre\\pictures\\" + theatre_id + extention1;
			String storePath2 = "\\picture\\theatre\\pictures\\" + theatre_id + extention2;

			String realPath = tPath.substring(1, num).replace('/', '\\') + "TicketsCircle\\WebContent\\";
			String path1 = realPath + storePath1;
			String path2 = realPath + storePath2;
			File file01 = new File(path1);
			File file02 = new File(path2);
			if (!file01.exists()) {
				file01.getParentFile().mkdirs();
			}
			if (!file02.exists()) {
				file02.getParentFile().mkdirs();
			}
			file1.transferTo(file01);
			file2.transferTo(file02);
			theatre.setTheatre_picture(path1);
			theatre.setTheatre_licence(path2);

			// ��ȡӰԺע���ʱ��
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
			String theatre_registertime = sdf.format(new Date());
			theatre.setTheatre_registertime(theatre_registertime);

			int count = this.theatreMapper.signUpForTheatre(theatre);
			if (count == 1) {
				return "success";
			} else {
				return "failure";
			}
		} else {
			return "exist";
		}
	}

	@RequestMapping("/verifyTheTheatre")
	@ResponseBody
	// ��֤ӰԺ���ܣ���ϵͳ����Ա��������ͨ��������ִ�иú�����״̬Ϊ1��ʾͨ�������������䷢�ͻ�ӭ�ʼ����ܡ�����Ҫ�����˺ź�Ĭ��������Ϣ��
	public boolean verifyTheTheatre(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		String theatre_id = request.getParameter("theatre_id");
		List<Theatre> lstTheatre = this.theatreMapper.getMyTheatreInfoAll(theatre_id);
		Iterator<Theatre> itTheatre = lstTheatre.iterator();
		while (itTheatre.hasNext()) {
			theatre = itTheatre.next();
		}

		int count0 = this.theatreMapper.updateTheTheatreStatust("1", theatre_id);
		System.out.println("����ӰԺ״̬��Ϣ���� : " + count0);
		int count1 = this.theatreMapper.putTheatreIntoLogin(theatre);
		System.out.println("����ӰԺ��¼Ȩ������ : " + count1);

		if ((count0 + count1) == 2) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("/getMyTheatreInfoAll")
	@ResponseBody
	public List<Map<String, Object>> getMyTheatreInfoAll(HttpServletResponse response, HttpServletRequest request) {
		response.setCharacterEncoding("utf_8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		String theatre_id = (String) session.getAttribute("currentUser");
		List<Theatre> myTheatre = this.theatreMapper.getMyTheatreInfoAll(theatre_id);
		Map<String, Object> map = null;
		List<Map<String, Object>> myInfo = new ArrayList<>();
		Iterator<Theatre> iTheatre = myTheatre.iterator();
		while (iTheatre.hasNext()) {
			theatre = iTheatre.next();
			map = new HashMap<>();
			map.put("theatre_id", theatre.getTheatre_id());
			map.put("theatre_name", theatre.getTheatre_name());
			map.put("theatre_address", theatre.getTheatre_address());
			map.put("zone_id", theatre.getZone_id());
			map.put("theatre_head", theatre.getTheatre_head());
			map.put("theatre_phone", theatre.getTheatre_phone());
			map.put("theatre_email", theatre.getTheatre_email());
			map.put("theatre_account", theatre.getTheatre_account());
			map.put("theatre_pwd", theatre.getTheatre_pwd());
			map.put("theatre_licence", theatre.getTheatre_licence());
			map.put("theatre_status", theatre.getTheatre_status());
			map.put("theatre_registertime", theatre.getTheatre_registertime());
			myInfo.add(map);
		}
		return myInfo;
	}

	@RequestMapping("/getTheatreInfoAll")
	@ResponseBody
	public List<Map<String, Object>> getTheatreInfoAll(HttpServletResponse response) {
		List<Theatre> theatres = this.theatreMapper.getTheatreInfoAll();
		Map<String, Object> map = null;
		List<Map<String, Object>> theatreInfo = new ArrayList<>();
		Iterator<Theatre> iTheatres = theatres.iterator();
		while (iTheatres.hasNext()) {
			theatre = iTheatres.next();
			map = new HashMap<>();
			map.put("theatre_id", theatre.getTheatre_id());
			map.put("theatre_name", theatre.getTheatre_name());
			map.put("theatre_address", theatre.getTheatre_address());
			map.put("zone_id", theatre.getZone_id());
			map.put("theatre_head", theatre.getTheatre_head());
			map.put("theatre_phone", theatre.getTheatre_phone());
			map.put("theatre_email", theatre.getTheatre_email());
			map.put("theatre_account", theatre.getTheatre_account());
			map.put("theatre_pwd", theatre.getTheatre_pwd());
			map.put("theatre_licence", theatre.getTheatre_licence());
			map.put("theatre_statust", theatre.getTheatre_status());
			map.put("theatre_registertime", theatre.getTheatre_registertime());
			theatreInfo.add(map);
		}
		return theatreInfo;
	}

	// ����ӰԺ����Ϣ��ӰԺ������Ϣ���ٱ�ϵͳ����Ա��ˣ�ӰԺ״̬-1��˵��ӰԺ���ڵ���Ϣ�����⣬��ӰԺֻ�ܵ�¼���޸Ļ�����Ϣ�����ܷ����������ݣ��޸���ɲ�ͨ����˺�ɷ���������Ϣ����������������ʼ�����
}
