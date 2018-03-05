package com.ticket.control;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ticket.entity.Beon;
import com.ticket.entity.Film;
import com.ticket.dao.IFilmMapper;

@Controller
@RequestMapping("/film")
public class FilmControl {
	// film/addNewFilm.action
	IFilmMapper filmMapper;
	Film film = new Film();
	Beon beon = new Beon();

	public IFilmMapper getFilmMapper() {
		return filmMapper;
	}

	@Autowired
	public void setFilmMapper(@Qualifier("filmMapper") IFilmMapper filmMapper) {
		this.filmMapper = filmMapper;
	}

	public FilmControl() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
	}

	@RequestMapping("/addNewFilm")
	@ResponseBody
	// ƽ̨��Ӱ����û�еĵ�Ӱ��ӰԺ�����������
	public String addNewFilm(@RequestParam("filmp") MultipartFile file, @ModelAttribute("film") Film film,
			HttpServletResponse response, HttpServletRequest request) throws IllegalStateException, IOException {
		System.out.println("##############################################################3");
		// 1.���ж��Ƿ��ڵ�Ӱ��������ӹ��˵�Ӱ������ӹ�������ֹ���,����2
		List<Film> lstFilm = this.filmMapper.isExistTheFilm(film);
		System.out.println("FilmContorlService.addNewFilm: ���ҵ���ͬ��Ӱ������  " + lstFilm.size());
		if (lstFilm.size() == 0) {
			// 2.ִ���ڵ�Ӱ������µ�Ӱ�Ĺ���

			// ����UUID����ÿ����Ӱ��ΨһID
			UUID uuid = UUID.randomUUID();
			String[] id = uuid.toString().split("-");
			String film_id = id[0] + id[1];
			System.out.println("FilmContorlService.addNewFilm: Ϊ��Ӱ���ɵ�Ψһ��ʶ��  " + film_id);
			film.setFilm_id(film_id);

			// �洢ͼƬ
			System.out.println("FilmContorlService.addNewFilm: �ϴ�ͼƬ�� " + file.getOriginalFilename());
			String extention = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));

			// ���Ҫ��ȡWebContentĿ¼�µ��ļ�����·����ô�죿����������ķ���
			// String t =
			// Thread.currentThread().getContextClassLoader().getResource("").getPath();
			// int num = t.indexOf(".metadata");
			// String path = t.substring(1, num).replace('/', '\\') + "��Ŀ��\\WebContent\\�ļ�";
			// ����ǣ�E:\java_web\workspace\��Ŀ��\WebContent\�ļ�

			String tPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			System.out.println(tPath);
			int num = tPath.indexOf(".metadata");
			String storePath = "picture\\film\\" + film_id + extention;
			String picturePath = tPath.substring(1, num).replace('/', '\\') + "Ticket\\WebContent\\" + storePath;
			System.out.println("FilmContorl.addNewFilm: �ṩͼƬ�洢·��  " + picturePath);

			// file.transferTo(new File(realPath));
			file.transferTo(new File(picturePath));
			film.setFilm_picture(storePath);
			System.out.println("FilmContorlService.addNewFilm: ͼƬ�ϴ��ɹ��� ");

			int count = this.getFilmMapper().addNewFilm(film);
			System.out.println("FilmContorlService.addNewFilm: ִ�в����������  " + count);

			if (count == 1) {
				return "success";
			} else {
				return "failure";
			}
		} else {
			return "exist";
		}
	}

	@RequestMapping("/getFilmInfoAll")
	@ResponseBody
	// ��ȡƽ̨��Ӱ��
	public List<Map<String, Object>> getFilmInfoAll(HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		List<Map<String, Object>> lstFilm = new ArrayList<>();
		List<Film> films = this.filmMapper.getFilmInfoAll();
		Map<String, Object> map = null;
		Iterator<Film> itFilm = films.iterator();
		while (itFilm.hasNext()) {
			film = itFilm.next();
			map = new HashMap<>();
			map.put("film_id", film.getFilm_id());
			map.put("film_name", film.getFilm_name());
			map.put("film_director", film.getFilm_director());
			map.put("film_performer", film.getFilm_performer());
			map.put("film_type", film.getFilm_type());
			map.put("film_area", film.getFilm_area());
			map.put("film_language", film.getFilm_language());
			map.put("film_time", film.getFilm_time());
			map.put("film_synopsis", film.getFilm_synopsis());
			map.put("film_picture", film.getFilm_picture());
			lstFilm.add(map);
		}
		System.out.println("FilmContorl.getFilmInfoAll : " + lstFilm);
		return lstFilm;
	}

	/*
	 * @RequestMapping("/uploadPicture")
	 * 
	 * @ResponseBody // ͼƬ�ϴ������ԣ� public String uploadPicture(@RequestParam("file")
	 * CommonsMultipartFile file, HttpServletRequest request) { // ��������������ʱ�� long
	 * startTime = System.currentTimeMillis(); System.out.println("fileName��" +
	 * file.getOriginalFilename());
	 * 
	 * try { // ��ȡ����� OutputStream os = new FileOutputStream(
	 * request.getServletContext().getRealPath("/") + "/picture/film/" +
	 * file.getOriginalFilename()); System.out.println(
	 * request.getServletContext().getRealPath("/") + "/picture/film/" +
	 * file.getOriginalFilename()); // ��ȡ������ CommonsMultipartFile �п���ֱ�ӵõ��ļ�����
	 * InputStream is = file.getInputStream(); int temp; // һ��һ���ֽڵĶ�ȡ��д�� while
	 * ((temp = is.read()) != (-1)) { os.write(temp); } os.flush(); os.close();
	 * is.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } long endTime =
	 * System.currentTimeMillis(); System.out.println("����һ������ʱ�䣺" +
	 * String.valueOf(endTime - startTime) + "ms"); return "/success"; }
	 */

	@RequestMapping("/addNewBeon")
	@ResponseBody
	// ӰԺ����µ�Ҫ��ӳ�ĵ�Ӱ
	public boolean addNewBeon(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, ParseException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();

		// �������⣺�µ���ĵ�Ӱ���ٴ������ӳʱ��sql��䱨��ԭ��insert ������һ������Ϊ�����һ�黹û��ɾ����
		// ���������1.�������ҳ��ʱ���Զ�ִ��һ���µ���Ӱ��beonɾ���Ĺ�����2.�����ӳǰ����ѯ�Ƿ����ͬһ����Ӱ����������ִ��update

		System.out.println("ӰԺ����µ�Ҫ��ӳ�ĵ�Ӱ : " + request.getParameter("film_id") + request.getParameter("be_on")
				+ request.getParameter("be_off"));

		String theatre_id = (String) session.getAttribute("currentUser");
		beon.setTheatre_id(theatre_id);
		beon.setFilm_id(request.getParameter("film_id"));
		beon.setBe_on(request.getParameter("be_on"));
		beon.setBe_off(request.getParameter("be_off"));
		int count = this.filmMapper.addNewBeon(beon);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("/showMyBeon")
	@ResponseBody
	// ��ʾ��ǰӰԺ���ڷ�ӳ�ĵ�Ӱ
	public List<Map<String, Object>> showMyBeon(HttpServletResponse response, HttpServletRequest request)
			throws UnsupportedEncodingException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		String theatre_id = (String) session.getAttribute("currentUser");
		List<Beon> beons = this.filmMapper.showMyBeon(theatre_id);
		System.out.println("��ѯ�������ڷ�ӳ�ĵ�Ӱ���� ��" + beons.size());
		List<Map<String, Object>> lstBeon = new ArrayList<>();
		Map<String, Object> map = null;
		Iterator<Beon> itBeon = beons.iterator();
		while (itBeon.hasNext()) {
			beon = itBeon.next();
			map = new HashMap<>();
			map.put("film_id", beon.getFilm_id());
			map.put("film_name", beon.getFilm().getFilm_name());
			map.put("film_director", beon.getFilm().getFilm_director());
			map.put("film_performer", beon.getFilm().getFilm_performer());
			map.put("film_picture", beon.getFilm().getFilm_picture());
			map.put("be_on", beon.getBe_on());
			map.put("be_off", beon.getBe_off());
			lstBeon.add(map);
		}
		System.out.println("���ڷ�ӳ�ĵ�Ӱ : " + lstBeon);
		return lstBeon;
	}

	@RequestMapping("/getFilmInfoNotInMyBeon")
	@ResponseBody
	// ��ȡ��ӰԺû����ӳ�ĵ���ƽ̨��Ӱ����ڵĵ�Ӱ
	public List<Map<String, Object>> getFilmInfoNotInMyBeon(HttpServletResponse response, HttpServletRequest request) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		String theatre_id = (String) session.getAttribute("currentUser");
		List<Film> filmsNotBeon = this.filmMapper.getFilmInfoNotInMyBeon(theatre_id);
		Map<String, Object> map = null;
		List<Map<String, Object>> notBeon = new ArrayList<>();
		Iterator<Film> itFilmNotBeon = filmsNotBeon.iterator();
		while (itFilmNotBeon.hasNext()) {
			map = new HashMap<>();
			film = itFilmNotBeon.next();
			map.put("film_id", film.getFilm_id());
			map.put("film_name", film.getFilm_name());
			map.put("film_director", film.getFilm_director());
			map.put("film_performer", film.getFilm_performer());
			map.put("film_type", film.getFilm_type());
			map.put("film_area", film.getFilm_area());
			map.put("film_language", film.getFilm_language());
			map.put("film_time", film.getFilm_time());
			map.put("film_synopsis", film.getFilm_synopsis());
			map.put("film_picture", film.getFilm_picture());
			notBeon.add(map);
		}
		return notBeon;
	}

	@RequestMapping("/updateMyBeon")
	@ResponseBody
	// ������ӳ���µ�ʱ�䣬ע�⵱ǰ����һ��������ӳʱ�������ٸ�����ӳʱ�䣨ǰ�˿��ƣ�������ӳ���ں���ӳ���ڿ򲻿ɸ���
	// <input type="text" name="" disabled="disabled" /��
	public boolean updateMyBeon(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		String theatre_id = (String) session.getAttribute("currentUser");
		beon.setTheatre_id(theatre_id);
		beon.setFilm_id(request.getParameter("film_id"));
		beon.setBe_on(request.getParameter("be_on"));
		beon.setBe_off(request.getParameter("be_off"));
		int result = this.filmMapper.updateMyBeon(beon);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("/deleteMyBeon")
	@ResponseBody
	// ɾ��ӰԺ��ӳ����
	public boolean deleteMyBeon(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		System.out.println("ɾ���ĵ�ӰID : " + request.getParameter("film_id"));
		String theatre_id = (String) session.getAttribute("currentUser");
		int count = this.filmMapper.deleteMyBeon(request.getParameter("film_id"), theatre_id);
		System.out.println("ɾ����������" + count);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("/getMyBeonHistory")
	@ResponseBody
	public List<Map<String, Object>> getMyBeonHistory(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		String theatre_id = (String) session.getAttribute("currentUser");
		List<Beon> beonsHistory = this.filmMapper.getMyBeonHistory(theatre_id);
		Map<String, Object> map = null;
		List<Map<String, Object>> lstBeonHistory = new ArrayList<>();
		Iterator<Beon> itBeonHistory = beonsHistory.iterator();
		while (itBeonHistory.hasNext()) {
			Beon beon = (Beon) itBeonHistory.next();
			map = new HashMap<>();
			map.put("film_id", beon.getFilm_id());
			map.put("film_name", beon.getFilm().getFilm_name());
			map.put("film_director", beon.getFilm().getFilm_director());
			map.put("film_performer", beon.getFilm().getFilm_performer());
			map.put("be_on", beon.getBe_on());
			map.put("be_off", beon.getBe_off());
			lstBeonHistory.add(map);
		}
		return lstBeonHistory;
	}

	@RequestMapping("deleteMyBeoff")
	@ResponseBody
	public int deleteMyBeoff(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		String theatre_id = (String) session.getAttribute("currentUser");

		int count = this.filmMapper.deleteMyBeoff(theatre_id);
		System.out.println("ɾ���Ѿ��µ��ĵ�Ӱ������ �� " + count);
		return count;
	}
}
