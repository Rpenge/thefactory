package com.systemk.thefactor2.Config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 공통 상수코드
public class ConstansConfig {

	public final static int ERROR = 3000;

	public final static int BAD_PARAMETER = 3001;

	public final static int NOT_FIND_USER = 3002;

	public final static int NOT_VALID_PASSWORD = 3003;

	public final static int NOT_USE_USER = 3004;

	public final static int NOT_CHECK_ADMIN = 3005;

	public final static int NOT_USE_COMPANY = 3006;

	public final static int LOGOUT_FAIL = 3007;

	public final static int NOT_FIND_DATA = 3008;

	public final static int NOT_VALID_STORAGE_SCHEDULE = 3009;

	public final static int NOT_FIND_BOX = 3010;

	public final static int VERSION_UPDATE = 3011;

	public final static int NOT_FIND_RFID_TAG = 3012;

	public final static int NOT_VALID_REPERENCE_NO = 3013;

	public final static int NOT_FIND_BARTAG = 3014;

	public final static int NOT_FIND_DISTRIBUTION_BARTAG = 3015;

	public final static int NOT_ENOUGH_STOCK_AMOUNT = 3016;

	public final static int NOT_VALID_BOX_BARCODE = 3017;

	public final static int ONLY_PRODUCTION_BOX_POSSIBLE = 3018;

	public final static int ONLY_RELEASE_READY_BOX_POSSIBLE = 3019;

	public final static int NOT_FIND_STORE_RELEASE_SCHEDULE = 3020;

	public final static int DUPLICATION_BOX_BARCODE = 3021;

	public final static int NOT_FIND_START_COMPANY = 3022;

	public final static int NOT_FIND_END_COMPANY = 3023;

	public final static int NOT_VALID_STORE_RELEASE_SCHEDULE_KEY = 3024;

	public final static int NOT_FIND_TEMP_HEADER = 3025;

	public final static int NOT_COMPLETE_BEFORE_BARTAG_ORDER = 3026;

	public final static int COMPLETE_BARTAG_ORDER = 3027;

	public final static int PROCESS_BARTAG = 3028;

	public final static int END_BARTAG_ORDER = 3029;

	public final static int COMPLETE_BOX = 3030;

	public final static int DISUSE_BOX = 3031;

	public final static int MAPPING_BOX = 3032;

	public final static int EQAUL_COMPANY_BOX_POSSIBLE = 3033;

	public final static int ONLY_STOCK_RFID_TAG_POSSIBLE = 3034;

	public final static int ONLY_PRODUCTION_COMPANY = 3035;

	public final static int NOT_FIND_WMS_BARCODE = 3036;

	public final static int INCLUDE_DISUSE_RFID_TAG = 3037;

	public final static int NOT_VALID_RFID_TAG = 3038;

	public final static int DISUSE_RFID_TAG = 3039;

	public final static int ONLY_MAPPING_BOX = 3040;

	public final static int CONFIRM_BOX = 3041;

	public final static int ONLY_READY_TO_BOX_POSSIBLE = 3042;

	public final static int NOT_FIND_COMPANY = 3043;

	public final static int DUPLICATION_RFID_TAG = 3044;

	public final static int NOT_FIND_REISSUE_TAG_REQEUST = 3045;

	public final static int CONFIRM_REISSUE_TAG_REQUEST = 3046;

	public final static int COMPLETE_REISSUE_TAG_REQUEST = 3047;

	public final static int NOT_VALID_RFID_TAG_BARCODE = 3048;

	public final static int NOT_VALID_REQUEST_TAG = 3049;

	public final static int ROLE_VIOLATION = 3050;

	public final static int CONFIRM_ORDER = 3051;

	public final static int COMPLETE_ORDER = 3052;

	public final static int NOT_FIND_STORE_MOVE_REQUEST = 3053;

	public final static int CONFIRM_STORE_MOVE_REQUEST = 3054;

	public final static int UPDATE_ERP_STORE_MOVE_REQUEST = 3055;

	public final static int DELETE_ERP_STORE_MOVE_REQUEST = 3056;

	public final static int NOT_WORK_START_STORE_MOVE_REQUEST = 3057;

	public final static int NOT_CONFIRM_START_STORE_MOVE_REQUEST = 3058;

	public final static int NOT_WORK_END_STORE_MOVE_REQUEST = 3059;

	public final static int NOT_CONFIRM_END_STORE_MOVE_REQUEST = 3060;

	public final static int COMPLETE_WORK_START_STORE_MOVE_REQUEST = 3061;

	public final static int COMPLETE_CONFIRM_START_STORE_MOVE_REQUEST = 3062;

	public final static int COMPLETE_WORK_END_STORE_MOVE_REQUEST = 3063;

	public final static int COMPLETE_CONFIRM_END_STORE_MOVE_REQUEST = 3064;

	public final static int ONLY_ERP_STORE_MOVE_REQUEST = 3065;

	public final static int DISUSE_STORE_MOVE_REQUEST = 3066;

	public final static int EXIST_BOX_BARCODE = 3067;

	public final static int NOT_FIND_INVENTORY_SCHEDULE = 3068;

	public final static int CONFIRM_INVENTORY_SCHEDULE = 3069;

	public final static int COMPLETE_INVENTORY_SCHEDULE = 3070;

	public final static int DISUSE_INVENTORY_SCHEDULE = 3071;

	public final static int NOT_APPLY_RFID_TAG_IN_COMPANY = 3072;

	public final static int NOT_FIND_STORE_BARTAG = 3073;

	public final static int FIND_NOT_CONFIRM_INVENTORY_SCHEDULE = 3074;

	public final static int DUPLICATION_WMS_BOX_BARCODE = 3075;

	public final static int DUPLICATION_WMS_REFERENCENO = 3076;

	public final static int DUPLICATION_ERP_REFERENCENO = 3077;

	public final static int NOT_FIND_RELEASE_RFID_TAG = 3078;

	public final static int STORAGE_RETURN_BOX = 3079;

	public final static int NOT_CONFIRM_BOX = 3080;

	public final static int NOT_COMPLETE_BOX = 3081;

	public final static int NOT_FIND_APP = 3082;

	public final static int VALID_WMS_BARCODE = 3083;

	public final static int COMPLETE_WMS_BARCODE = 3084;

	public final static int NOT_VALID_WMS_BARCODE = 3085;

	public final static int COMPLETE_BARTAG = 3086;

	public final static int DISUSE_BARTAG = 3087;

	public final static int CONVEYOR_NOT_ALL_TAG_READ = 3100;

	public final static int CONVEYOR_NOT_VALID_TAG_READ = 3101;

	public final static int CONVEYOR_NOT_VALID_STYLE_AMOUNT = 3102;

	public final static int CONVEYOR_NOT_VALID_STORAGE_BARCODE = 3103;

	public final static int CONVEYOR_NOT_VALID_RELEASE_BARCODE = 3104;

	public final static int CONVEYOR_CANCEL_EXCEPTION_STORAGE = 3105;

	public final static int NOT_FIND_ERP_RELEASE_ORDER = 3106;

	public final static int SUCCESS = 1000;

	public final static int STORE_PDA_RETURN_WORKED_BOXNUM = 4001;

	public final static int STORE_PDA_RETURN_COMPLETED_WORK = 4002;

	public final static int STORE_PDA_RETURN_NOT_FIND_BOXNUM = 4003;

	public final static String ERROR_MSG = "에러";

	public final static String BAD_PARAMETER_MSG = "검증되지 않은 파라미터입니다.";

	public final static String NOT_FIND_DEVICE_MSG = "등록된 장비가 아닙니다.";

	public final static String NOT_FIND_USER_MSG = "등록된 사용자가 아닙니다.";

	public final static String NOT_VALID_PASSWORD_MSG = "비밀번호를 확인하여 다시 시도해주세요.";

	public final static String NOT_USE_USER_MSG = "사용 중지된 사용자입니다.";

	public final static String NOT_CHECK_ADMIN_MSG = "관리자 승인 이후에 사용하실 수 있습니다.";

	public final static String LOGIN_SUCCESS_MSG = "로그인 성공하였습니다.";

	public final static String LOGOUT_SUCCESS_MSG = "로그아웃 성공하였습니다.";

	public final static String LOGOUT_FAIL_MSG = "로그아웃 실패하였습니다.";

	public final static String NOT_FIND_DATA_MSG = "해당 정보를 찾을 수 없습니다.";

	public final static String NOT_FIND_STOCK_MSG = "해당 재고정보가 등록되지 않았습니다.";

	public final static String EXIST_AC_STOCK_MSG = "이미 등록 된 재고입니다.";

	public final static String NOT_FIND_OUTPUT_MSG = "해당 출고정보를 찾을 수 없습니다.";

	public final static String VERSION_UPDATE_MSG = "버전 업데이트가 필요합니다.";

	public final static String NOT_FIND_RFID_TAG_MSG = "태그 정보를 찾을 수 없습니다.";

	public final static String NOT_FIND_RELEASE_RFID_TAG_MSG = "출고 된 RFID 태그가 없습니다.";

	public final static String PDA_STATE_DATA_NONE_MSG = "작업 코드가 없습니다";



	private final static String nameStr ="{" +
			" \"storeCd\" 	: \"매장코드\"	," +
			" \"storeNm\"	: \"매장명\"		," +
			" \"gub\" 		: \"구분\"		," +
			" \"prdCd\" 	: \"상품코드\"	," +
			" \"prdNm\" 	: \"상품명\"		," +
			" \"brand\" 	: \"브랜드\"		," +
			" \"gender\" 	: \"성별\"		," +
			" \"cls\" 		: \"상품분류\"	," +
			" \"stType\"	: \"등록구분\"	," +
			" \"size\" 		: \"사이즈\"		," +
			" \"barcode\" 	: \"바코드\"		," +
			" \"tagId\" 	: \"태그ID\"		," +
			" \"stDate\" 	: \"등록일\"		," +
			" \"comment\" 	: \"비고\"		," +
			" \"regDate\" 	: \"등록일시\"	 " +
			"}";



	public static Map getNameMap() throws IOException {
		return new ObjectMapper().readValue(nameStr, HashMap.class);
	}

}
