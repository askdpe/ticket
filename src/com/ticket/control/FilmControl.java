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
	// 平台电影库中没有的电影，影院可以自行添加
	public String addNewFilm(@RequestParam("filmp") MultipartFile file, @ModelAttribute("film") Film film,
			HttpServletResponse response, HttpServletRequest request) throws IllegalStateException, IOException {
		System.out.println("##############################################################3");
		// 1.先判断是否在电影库中已添加过此电影，若添加过，则阻止添加,否则2
		List<Film> lstFilm = this.filmMapper.isExistTheFilm(film);
		System.out.println("FilmContorlService.addNewFilm: 查找到相同电影的数量  " + lstFilm.size());
		if (lstFilm.size() == 0) {
			// 2.执行在电影库添加新电影的功能

			// 利用UUID生成每部电影的唯一ID
			UUID uuid = UUID.randomUUID();
			String[] id = uuid.toString().split("-");
			String film_id = id[0] + id[1];
			System.out.println("FilmContorlService.addNewFilm: 为电影生成的唯一标识码  " + film_id);
			film.setFilm_id(film_id);

			// 存储图片
			System.out.println("FilmContorlService.addNewFilm: 上传图片名 " + file.getOriginalFilename());
			String extention = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));

			// 如果要获取WebContent目录下的文件绝对路径怎么办？可以用下面的方法
			// String t =
			// Thread.currentThread().getContextClassLoader().getResource("").getPath();
			// int num = t.indexOf(".metadata");
			// String path = t.substring(1, num).replace('/', '\\') + "项目名\\WebContent\\文件";
			// 结果是：E:\java_web\workspace\项目名\WebContent\文件

			String tPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			System.out.println(tPath);
			int num = tPath.indexOf(".metadata");
			String storePath = "picture\\film\\" + film_id + extention;
			String picturePath = tPath.substring(1, num).replace('/', '\\') + "Ticket\\WebContent\\" + storePath;
			System.out.println("FilmContorl.addNewFilm: 提供图片存储路径  " + picturePath);

			// file.transferTo(new File(realPath));
			file.transferTo(new File(picturePath));
			film.setFilm_picture(storePath);
			System.out.println("FilmContorlService.addNewFilm: 图片上传成功！ ");

			int count = this.getFilmMapper().addNewFilm(film);
			System.out.println("FilmContorlService.addNewFilm: 执行插入语句数量  " + count);

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
	// 获取平台电影库
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
	 * @ResponseBody // 图片上传（测试） public String uploadPicture(@RequestParam("file")
	 * CommonsMultipartFile file, HttpServletRequest request) { // 用来检测程序运行时间 long
	 * startTime = System.currentTimeMillis(); System.out.println("fileName：" +
	 * file.getOriginalFilename());
	 * 
	 * try { // 获取输出流 OutputStream os = new FileOutputStream(
	 * request.getServletContext().getRealPath("/") + "/picture/film/" +
	 * file.getOriginalFilename()); System.out.println(
	 * request.getServletContext().getRealPath("/") + "/picture/film/" +
	 * file.getOriginalFilename()); // 获取输入流 CommonsMultipartFile 中可以直接得到文件的流
	 * InputStream is = file.getInputStream(); int temp; // 一个一个字节的读取并写入 while
	 * ((temp = is.read()) != (-1)) { os.write(temp); } os.flush(); os.close();
	 * is.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } long endTime =
	 * System.currentTimeMillis(); System.out.println("方法一的运行时间：" +
	 * String.valueOf(endTime - startTime) + "ms"); return "/success"; }
	 */

	@RequestMapping("/addNewBeon")
	@ResponseBody
	// 影院添加新的要放映的电影
	public boolean addNewBeon(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, ParseException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();

		// 存在问题：下档后的电影，再次添加上映时，sql语句报错（原因：insert 的主键一样，因为插入过一遍还没有删除）
		// 解决方案：1.进入管理页面时，自动执行一次下档电影从beon删除的工作；2.添加上映前，查询是否存在同一部电影，若存在则执行update

		System.out.println("影院添加新的要放映的电影 : " + request.getParameter("film_id") + request.getParameter("be_on")
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
	// 显示当前影院正在放映的电影
	public List<Map<String, Object>> showMyBeon(HttpServletResponse response, HttpServletRequest request)
			throws UnsupportedEncodingException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		String theatre_id = (String) session.getAttribute("currentUser");
		List<Beon> beons = this.filmMapper.showMyBeon(theatre_id);
		System.out.println("查询出的正在放映的电影数量 ：" + beons.size());
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
		System.out.println("正在放映的电影 : " + lstBeon);
		return lstBeon;
	}

	@RequestMapping("/getFilmInfoNotInMyBeon")
	@ResponseBody
	// 获取该影院没有上映的但是平台电影库存在的电影
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
	// 更改上映和下档时间，注意当前日期一旦超过上映时间则不能再更改上映时间（前端控制，超过上映日期后，上映日期框不可更改
	// <input type="text" name="" disabled="disabled" /）
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
	// 删除影院上映表项
	public boolean deleteMyBeon(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		System.out.println("删除的电影ID : " + request.getParameter("film_id"));
		String theatre_id = (String) session.getAttribute("currentUser");
		int count = this.filmMapper.deleteMyBeon(request.getParameter("film_id"), theatre_id);
		System.out.println("删除的数量：" + count);
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
		System.out.println("删除已经下档的电影的数量 ： " + count);
		return count;
	}
}
