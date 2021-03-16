package com.systemk.thefactor2.Service.Impl;



import com.systemk.thefactor2.Config.ConstansConfig;
import com.systemk.thefactor2.Mapper.TfApplicationMapper;
import com.systemk.thefactor2.Mapper.TfDeviceMapper;
import com.systemk.thefactor2.Service.UserService;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Mapper.PageMapper;
import com.systemk.thefactor2.Mapper.TfUserMapper;
import com.systemk.thefactor2.Util.ResultUtil;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.TfApplicationVO;
import com.systemk.thefactor2.VO.TfDeviceVO;
import com.systemk.thefactor2.VO.TfUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TfUserMapper tfUserMapper;

	@Autowired
	private TfDeviceMapper tfDeviceMapper;

	@Autowired
	private TfApplicationMapper tfApplicationMapper;

	@Autowired
	private PageMapper pageMapper;

	//사용자 리스트 조회, 검색
	@Override
	public Map<String, Object> findList(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("tf_user");

		for(Object key : param.keySet()) {    //분류 처리
			if (key.equals("word")) {
				mu.addLike("USER_ID", (String)param.get(key));
				mu.addORLike("USER_NM", (String)param.get(key));
			}
			if (key.equals("size") || key.equals("page") || key.equals("word")) {
				continue;
			} else {
				mu.addEqual(StringUtil.camelToSnake((String)key), (String)param.get(key));
			}
		}
		mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
		if(param.get("page")!=null)
			mu.setPage(Integer.parseInt((String)param.get("page")));
		if(param.get("size")!=null)
			mu.setSize(Integer.parseInt((String)param.get("size")));
		mu.setContent(tfUserMapper.userList(mu.getTableSearch())); //리스트 조회
		return mu.getList();
	}

	// 사용자 등록
	@Override
	public Map<String, Object> userSave(Map param) throws Exception {
		Map map = new HashMap();
		param.put("userPwd", passwordEncoder.encode((String) param.get("userPwd")));
		if(tfUserMapper.userSave(param) == 1){
			map.put("resultCode", "S");
		}else{
			map.put("resultCode", "E");
		}
		return map;
	}

	// 사용자 정보변경
	@Override
	public Map<String, Object> userUpdate(Map param) throws Exception {
		Map map = new HashMap();
		if(tfUserMapper.userUpdate(param) == 1){
			map.put("resultCode", "S");
		}else{
			map.put("resultCode", "E");
		}
		return map;
	}

	// 비밀번호 변경
	@Override
	public Map<String, Object> userPwUpdate(Map param) throws Exception {
		Map map = new HashMap();
		param.put("userPwd", passwordEncoder.encode((String) param.get("userPwd")));
		if(tfUserMapper.userPwUpdate(param) == 1){
			map.put("resultCode", "S");
		}else{
			map.put("resultCode", "E");
		}
		return map;
	}

	//사용자 탈퇴
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Map<String, Object> userWd(List list) throws Exception {
		Map map = new HashMap();
		for(int i=0;i<list.size(); i++){
			//tfUserMapper.userWd((int) list.get(i));
			if(tfUserMapper.userWd((int) list.get(i)) != 1){
				map.put("resultCode", "E");
				return map;
			}
		}
		map.put("resultCode", "S");
		return map;
	}

	@Override
	public Map<String, Object> PdaLogin(Map param) throws Exception {
		HashMap item = new HashMap();
		item.put("userId", param.get("userId"));

		TfDeviceVO device = tfDeviceMapper.serialSearch(param);
		TfApplicationVO lastVer = tfApplicationMapper.appLastVs();

		if(device == null){																				// 장비 등록 여부
			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_FIND_DEVICE_MSG);
		}
		if(!lastVer.getVersion().equals(param.get("version"))){											//어플 버전 확인
			item = new HashMap();
			item.put("appDownUrl", "http://"+param.get("appDownUrl")+"/#/member/appDown");
			return ResultUtil.setCommonResult("U",ConstansConfig.VERSION_UPDATE_MSG, item);
		}

		TfUserVO user = tfUserMapper.login(item);
		item.put("userNm", user.getUserNm());
		item.put("storeCd", user.getStoreCd());
		item.put("grade", user.getGrade());
		if(user == null){																				// ID 등록 여부
			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_FIND_USER_MSG);
		} else if(!passwordEncoder.matches((CharSequence) param.get("userPwd"), user.getUserPwd())){ 	// 비밀번호 검증 실패
			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_VALID_PASSWORD_MSG);
		} else if(user.getUserStat().equals("N")){ 														// 계정 사용가능 여부
			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_USE_USER_MSG);
		} else if(user.getPdaUseYn().equals("N")){ 														// PDA 사용가능 여부
			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_CHECK_ADMIN_MSG);
		}

		return ResultUtil.setCommonResult("S",ConstansConfig.LOGIN_SUCCESS_MSG, item);
	}





}
